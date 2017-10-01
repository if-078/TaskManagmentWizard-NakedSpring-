package com.softserve.if078.tmwSpring.configurations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.RunScript;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:datasource-test.properties")
public class H2DbConfig {

	@Autowired
	Environment environment;

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "datasource-test")
	public DataSourceProperties dataSourceProperties() {
		initDb();

		return new DataSourceProperties();
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getProperty("jdbc.url"));
		dataSource.setUsername(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		return dataSource;
	}

	public void initDb() {
		try {
			File f = new File("src/test/resources/h2_script.sql");
			FileReader reader = null;
			try {
				reader = new FileReader(f);

			} catch (FileNotFoundException ex) {
				System.err.println("File not found excp");
			}
			RunScript.execute(dataSource().getConnection(), reader);
		} catch (SQLException ex) {
			System.err.println("Some exp in script");
		}
	}
}
