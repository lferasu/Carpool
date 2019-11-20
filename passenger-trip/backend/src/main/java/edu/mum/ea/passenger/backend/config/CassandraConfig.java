package edu.mum.ea.passenger.backend.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
//@ConfigurationProperties("spring.data.cassandra")
//@PropertySource(value = {"classpath:cassandra.yml"})
@EnableCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

    private final String keyspaceName;
    private final String hosts;
    private final String replication_factor;

    CassandraConfig(
            @Value("${spring.data.cassandra.keyspace-name}") String keyspace,
            @Value("${spring.data.cassandra.contact-points}") String hosts,
            @Value("${spring.data.cassandra.replication-factor}") String replication_factor) {
        this.keyspaceName = keyspace;
        this.hosts = hosts;
        this.replication_factor = replication_factor;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"edu.mum.ea.passenger.backend.entity"};
    }

    @Override
    public SchemaAction getSchemaAction(){
        return  SchemaAction.CREATE_IF_NOT_EXISTS;
    }


    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return Collections.singletonList(CreateKeyspaceSpecification
                .createKeyspace(keyspaceName).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true)
                .withSimpleReplication());
    }

    /*
     * Creating keyspace if does not exists
     */
    @Override
    protected List<String> getStartupScripts() {
        return Collections.singletonList("CREATE KEYSPACE IF NOT EXISTS \""
                + keyspaceName + "\" WITH replication = {"
                + " 'class': 'SimpleStrategy', "
                + " 'replication_factor': '" + this.replication_factor + "'};");

    }
    @Bean
    @Override
    public CassandraClusterFactoryBean cluster() {

        super.cluster();

        RetryingCassandraClusterFactoryBean bean = new RetryingCassandraClusterFactoryBean();

        bean.setAddressTranslator(getAddressTranslator());
        bean.setAuthProvider(getAuthProvider());
        bean.setClusterBuilderConfigurer(getClusterBuilderConfigurer());
        bean.setClusterName(getClusterName());
        bean.setCompressionType(getCompressionType());
        bean.setContactPoints(getContactPoints());
        bean.setLoadBalancingPolicy(getLoadBalancingPolicy());
        bean.setMaxSchemaAgreementWaitSeconds(getMaxSchemaAgreementWaitSeconds());
        bean.setMetricsEnabled(getMetricsEnabled());
        bean.setNettyOptions(getNettyOptions());
        bean.setPoolingOptions(getPoolingOptions());
        bean.setPort(getPort());
        bean.setProtocolVersion(getProtocolVersion());
        bean.setQueryOptions(getQueryOptions());
        bean.setReconnectionPolicy(getReconnectionPolicy());
        bean.setRetryPolicy(getRetryPolicy());
        bean.setSpeculativeExecutionPolicy(getSpeculativeExecutionPolicy());
        bean.setSocketOptions(getSocketOptions());
        bean.setTimestampGenerator(getTimestampGenerator());

        bean.setKeyspaceCreations(getKeyspaceCreations());
        bean.setKeyspaceDrops(getKeyspaceDrops());
        bean.setStartupScripts(getStartupScripts());
        bean.setShutdownScripts(getShutdownScripts());

        return bean;
    }

    @Override
    protected String getKeyspaceName() {
        return this.keyspaceName;
    }

    @Override
    protected String getContactPoints() {
        return hosts;
    }
    @Override
    protected boolean getMetricsEnabled() { return false; }
}
