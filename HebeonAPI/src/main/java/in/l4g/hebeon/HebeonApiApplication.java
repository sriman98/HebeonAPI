package in.l4g.hebeon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = { "in.l4g.hebeon" })
//@ImportResource("classpath:springContext.xml")
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, 	ManagementWebSecurityAutoConfiguration.class  })
public class HebeonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HebeonApiApplication.class, args);
	}

}
