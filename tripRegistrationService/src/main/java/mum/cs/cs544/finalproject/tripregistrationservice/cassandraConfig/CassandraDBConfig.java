package mum.cs.cs544.finalproject.tripregistrationservice.cassandraConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableCassandraRepositories
public class CassandraDBConfig extends AbstractCassandraConfiguration {

    private final String KEYSPACE;
    private final String hosts;
    private final String replication_factor;

    CassandraDBConfig(
            @Value("${spring.data.cassandra.keyspace-name}") String keyspace,
            @Value("${spring.data.cassandra.contact-points}") String hosts,
            @Value("${spring.data.cassandra.replication-factor:1}") String replication_factor) {
        this.KEYSPACE = keyspace;
        this.hosts = hosts;
        this.replication_factor = replication_factor;
    }

//    public static final String KEYSPACE = "reservetripdb";

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification
                .createKeyspace(KEYSPACE).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true).withSimpleReplication();
        return Arrays.asList(specification);
    }

//    @Override
//    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
//        return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
//    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"mum.cs.cs544.finalproject.tripregistrationservice.model"};
//        return new String[]{"edu.mum.reservetrip.model"};
    }

    //    @Override
//    protected boolean getMetricsEnabled() { return false; }
    @Override
    protected List<String> getStartupScripts() {
        return Collections.singletonList("CREATE KEYSPACE IF NOT EXISTS \""
                + KEYSPACE + "\" WITH replication = {"
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
//
//    @Override
//    protected String getKeyspaceName() {
//        return this.KEYSPACE;
//    }

    @Override
    protected String getContactPoints() {
        return hosts;
    }

    @Override
    protected boolean getMetricsEnabled() {
        return false;
    }
}
