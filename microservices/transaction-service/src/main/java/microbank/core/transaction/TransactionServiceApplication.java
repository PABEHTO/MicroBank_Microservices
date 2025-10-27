package microbank.core.transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"microbank.core", "microbank.mappers", "microbank.util.http"})
public class TransactionServiceApplication {
	private static final Logger LOG =
			LoggerFactory.getLogger(TransactionServiceApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx =
				SpringApplication.run(TransactionServiceApplication.class, args);
		String myPostgreSqlUri = ctx.getEnvironment().getProperty("spring.datasource.url");
		LOG.info("Connected to PostgreSQL " + myPostgreSqlUri);
	}
}
