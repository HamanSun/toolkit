package org.relaxation.dynamicdatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class TkDynamicdatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TkDynamicdatasourceApplication.class, args);
	}

}
