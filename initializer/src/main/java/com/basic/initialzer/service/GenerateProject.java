package com.basic.initialzer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.initializer.entity.ProjectMetadata;
import com.basic.initializer.repo.MetaDataRepo;

@Service
public class GenerateProject {

	@Autowired
	public MetaDataRepo dataRepo;

	private static final Logger logger = LoggerFactory.getLogger(GenerateProject.class);

	public void save(ProjectMetadata projectMetadata) {

		try {
			logger.debug("Inside save method");
			dataRepo.save(projectMetadata);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
