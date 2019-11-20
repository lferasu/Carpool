package edu.mum.ea.searchservice.config;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
 import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
 import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
 
 @Configuration
 @EnableElasticsearchRepositories(basePackages = "edu.mum.ea.searchservice.repository")
 public class ElasticConfiguration {
 
    @Value("${elasticsearch.host}")
    private String EsHost;

    @Value("${elasticsearch.port}")
    private int EsPort;

    @Value("${elasticsearch.clustername}")
    private String EsClusterName;
    

     @Bean
     public NodeBuilder builder() {
         return new NodeBuilder();
     }

     @Bean
     public Client client() throws Exception {
         
        Settings esSettings = Settings.settingsBuilder()
                .put("cluster.name", EsClusterName)
                .build();

        TransportClient clientP = TransportClient.builder().settings(esSettings).build();
        clientP.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
        return clientP;
                
     }
 
     @Bean
     public ElasticsearchOperations elasticsearchTemplate() throws Exception {
         //return new ElasticsearchTemplate(builder().local(true).node().client());


        //  File tmpDir = File.createTempFile("elastic", Long.toString(System.nanoTime()));
        //  System.out.println("Temp directory: " + tmpDir.getAbsolutePath());
        //  Settings.Builder elasticsearchSettings =
        //          Settings.settingsBuilder()
        //                  .put("http.enabled", "true") // 1
        //                  .put("index.number_of_shards", "1")
        //                  .put("path.data", new File(tmpDir, "data").getAbsolutePath()) // 2
        //                  .put("path.logs", new File(tmpDir, "logs").getAbsolutePath()) // 2
        //                  .put("path.work", new File(tmpDir, "work").getAbsolutePath()) // 2
        //                  .put("path.home", tmpDir) // 3
        //                  .put("client.transport.sniff",true);
                         

        //  return new ElasticsearchTemplate(builder()
        //          .local(true)
        //          .settings(elasticsearchSettings.build())
        //          .node()
        //          .client());

         return new ElasticsearchTemplate(client());

     }
 }