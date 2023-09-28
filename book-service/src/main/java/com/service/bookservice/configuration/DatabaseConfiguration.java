package com.service.bookservice.configuration;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.google.gson.Gson;

@Configuration
public class DatabaseConfiguration {
	
	private final Gson gson = new Gson();
	
	@Bean
	public DataSource datasource() {
		
		final AwsSecret dbCredentials = getSecret();
		return DataSourceBuilder
				.create()
				.driverClassName("com.mysql.jdbc.Driver")
				.url("jdbc:"+dbCredentials.getEngine()+"://"
				+dbCredentials.getHost()+":"+dbCredentials.getPort()+"/abcd")
				.username(dbCredentials.getUsername())
				.password(dbCredentials.getPassword())
				.build();
		
	}

	private AwsSecret getSecret() {
		
		String secretName = "bookservice-mysql-db";
		
		AWSSecretsManager client = AWSSecretsManagerClientBuilder.standard().withRegion("us-east-1").build();
		
		String secret;
		GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest().withSecretId(secretName);
		GetSecretValueResult getSecretValueResult = null;
		
		
		try {
			getSecretValueResult = client.getSecretValue(getSecretValueRequest);
		}catch(Exception e) {
			throw e;
		}
		if(getSecretValueResult.getSecretString() != null) {
			secret = getSecretValueResult.getSecretString();
			return gson.fromJson(secret, AwsSecret.class);
		}
		return null;
	}

}
