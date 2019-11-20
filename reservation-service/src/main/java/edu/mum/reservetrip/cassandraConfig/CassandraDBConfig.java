package edu.mum.reservetrip.cassandraConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
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
            @Value("${spring.data.cassandra.replication-factor}") String replication_factor) {
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
        return new String[]{"edu.mum.reservetrip.model"};
    }

//    @Override
//    protected boolean getMetricsEnabled() { return false; }
}
