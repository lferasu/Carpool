package edu.mum.ea.searchservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableCaching
@SpringBootApplication
public class DemoApplication {

	// @Bean
    // public WebMvcConfigurer CORSConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/**")
    //                     .allowedOrigins("*")
    //                     .allowedHeaders("*")
    //                     .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD")
    //                     .maxAge(-1)   // add maxAge
    //                     .allowCredentials(false);
    //         }
    //     };
    // }


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
