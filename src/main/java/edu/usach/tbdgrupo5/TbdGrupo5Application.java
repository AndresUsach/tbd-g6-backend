package edu.usach.tbdgrupo5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@ComponentScan({"edu.usach.tbdgrupo5", "edu.usach.tbdgrupo5.rest"})
@EntityScan("edu.usach.tbdgrupo5.entities")
@EnableJpaRepositories("edu.usach.tbdgrupo5.repository")
public class TbdGrupo5Application {

	public static void main(String[] args) {
		SpringApplication.run(TbdGrupo5Application.class, args);
	}
}
