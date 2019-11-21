package edu.mum.reservetrip.cassandraConfig;

import com.datastax.driver.core.exceptions.NoHostAvailableException;
import com.datastax.driver.core.exceptions.TransportException;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;

@Log4j2
public class RetryingCassandraClusterFactoryBean extends CassandraClusterFactoryBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
    }

    private void connect() throws Exception {
        try {
            super.afterPropertiesSet();
        } catch (TransportException | IllegalArgumentException | NoHostAvailableException e) {
            log.warn(e.getMessage());
            log.warn("Retrying connection in 10 seconds");
            sleep();
            connect();
        }
    }

    private void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ignored) {
        }
    }
}
