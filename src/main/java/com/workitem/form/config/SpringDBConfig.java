package com.workitem.form.config;

import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;



@Configuration
@ComponentScan(basePackages = "com.workitem.form")
@PropertySource(value = { "classpath:database.properties" })
public class SpringDBConfig {

	@Autowired
	DataSource dataSource;

	/*@Bean
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}*/

	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	@Bean
	public DataSource getDataSource() {
		/*EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setName("testdb").setType(EmbeddedDatabaseType.HSQL)
				.addScript("db/sql/create-db.sql").addScript("db/sql/insert-data.sql").build();
		return db;*/
		
		ResourceBundle dbProperties = ResourceBundle.getBundle("database");
		String dbDriver = dbProperties.getString("database.driver");
		String dbUrl=dbProperties.getString("database.url");
		String dbUser=dbProperties.getString("database.user");
		String dbPwd=dbProperties.getString("database.password");
		/*Properties props = new Properties();
		String dbDriver=props.getProperty("database.driver");
		String dbUrl=props.getProperty("database.url");
		String dbUser=props.getProperty("database.user");
		String dbPwd=props.getProperty("database.password");*/
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName(dbDriver);
	    dataSource.setUrl(dbUrl);
	    dataSource.setUsername(dbUser);
	    dataSource.setPassword(dbPwd);
	    return dataSource;
	}

	@PostConstruct
	public void startDBManager() {

		// hsqldb
		// DatabaseManagerSwing.main(new String[] { "--url",
		// "jdbc:hsqldb:mem:testdb", "--user", "sa", "--password", "" });

	}

}