package mum.cs.cs544.finalproject.tripregistrationservice.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


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
}