package mum.cs.cs544.finalproject.tripregistrationservice.cassandraconfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableCassandraRepositories
public class TripRegistrationCassandraConfig extends AbstractCassandraConfiguration {

    public  static  final String KEYSPACE= "tripregistration";



    @Override
    public SchemaAction getSchemaAction(){
        return  SchemaAction.CREATE_IF_NOT_EXISTS;
    }



    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }


    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"mum.cs.cs544.finalproject.tripregistrationservice.model"};
    }

  /*  @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

        CreateKeyspaceSpecification specification = CreateKeyspaceSpecification
                .createKeyspace(KEYSPACE).ifNotExists()
                .with(KeyspaceOption.DURABLE_WRITES, true).withSimpleReplication();
        return Arrays.asList(specification);
    }*/
}