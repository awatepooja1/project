package com.basic.initializer.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.basic.initializer.entity.MavenDependencies;
import com.basic.initializer.entity.ProjectMetadata;
import com.basic.initializer.interfaces.IDirectoryOperations;
import com.basic.initializer.interfaces.IProjectOperations;
import com.basic.initializer.interfaces.IValidation;
import com.basic.initializer.service.DirectoryOperations;

import com.basic.initializer.service.ProjectOperations;
import com.basic.initializer.service.Validation;

@Configuration
public class InitializerConfiguration {
	
	@Bean
	@Primary
	public IProjectOperations getProject() {
		return new ProjectOperations();
	}

	@Bean
	public IValidation getValidation() {
		return new Validation();
	}
	
	@Bean
	public ProjectMetadata getProjectMetadata() {
		return new ProjectMetadata();
	}
	
	@Bean
	public MavenDependencies getMavenDependencies() {
		return new MavenDependencies();
	}

	@Bean
	@Primary
	public IDirectoryOperations getDirectory() {
		return new DirectoryOperations();
	}
}
