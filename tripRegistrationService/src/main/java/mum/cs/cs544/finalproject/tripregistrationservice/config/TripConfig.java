package mum.cs.cs544.finalproject.tripregistrationservice.config;

import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

public class TripConfig  extends AbstractCassandraConfiguration {
    public  static  final String KEYSPACE= "tripregistrationdb";
    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;

    }
}
