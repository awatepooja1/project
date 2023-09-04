package com.basic.initializer.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import com.basic.initializer.entity.MavenDependencies;
import com.basic.initializer.entity.ProjectMetadata;
import com.basic.initializer.interfaces.IProjectOperations;
import com.basic.initializer.model.ProjectData;
import com.basic.initializer.repo.DependencyRepo;
import com.basic.initializer.repo.MetaDataRepo;

@Service
public class ProjectOperations implements IProjectOperations {

	@Autowired
	public MetaDataRepo dataRepo;
	
	@Autowired
	public DependencyRepo dependencyRepo;
	
	@Autowired
	public ProjectMetadata metadata;
	
	@Autowired
	public MavenDependencies dependencyData;

	private static final Logger logger = LoggerFactory.getLogger(ProjectOperations.class);

	//@Transactional
	public void save(ProjectData data) {

		try {
			logger.debug("Inside save method");
			metadata.setGroup(data.getGroup());
			metadata.setArtifact(data.getArtifact());
			metadata.setName(data.getName());
			metadata.setDescription(data.getDescription());
			metadata.setPackagingType(data.getPackagingType().name());
			metadata.setJavaVersion(data.getJavaVersion().name());
			
			dataRepo.save(metadata);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}
	
	public void saveDependencies(MavenDependencies dependency) {

		try {
			//logger.debug("Inside save method");
			dependencyData.setName(dependency.getName());
			dependencyData.setRepoPath(dependency.getRepoPath());
			
			dependencyRepo.save(dependencyData);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
