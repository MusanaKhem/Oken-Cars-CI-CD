package configuration;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
		properties = {
		"spring.datasource.driver-class-name=org.hsqldb.jdbc.JDBCDriver",
		"spring.datasource.url=jdbc:hsqldb:mem:okendb;sql.syntax_pgs=true",
		"spring.datasource.username=okenuser",
		"spring.datasource.password=12345",
		"spring.jpa.database-platform=org.hibernate.dialect.HSQLDialect",
		"spring.jpa.show-sql=true",
		"spring.jpa.hibernate.ddl-auto=update"
		}
)
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class ConfigIntegrationTests {


	
}
