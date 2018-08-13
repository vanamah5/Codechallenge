package customer_service.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig {
	 	     
	   /* @Bean
	    public DataSource getDataSource() throws NamingException{
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("jdbc.driverClassName");
	        dataSource.setUrl("jdbc.url");
	        dataSource.setUsername("jdbc.username");
	        dataSource.setPassword("jdbc.password");
	        return dataSource;
	    }*/
}
