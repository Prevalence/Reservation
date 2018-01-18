package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import util.HQLTools;

@Configuration
@ComponentScan({ "config", "service", "dao", "servlet", "util" })
public class SpringConfig {
	@Bean
	public HQLTools HQLTools() {
		return new HQLTools();
	}
}
