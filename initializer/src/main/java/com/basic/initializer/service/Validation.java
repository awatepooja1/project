package com.basic.initializer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.basic.initializer.entity.ProjectMetadata;
import com.basic.initializer.exception.ValidNameException;
import com.basic.initializer.interfaces.IValidation;
import com.basic.initializer.model.JavaVersion;
import com.basic.initializer.model.PackagingType;
import com.basic.initializer.model.ProjectData;

public class Validation implements IValidation {

	@Autowired
	public ProjectMetadata data;

	private static final Logger logger = LoggerFactory.getLogger(Validation.class);

	public void validateDetails(ProjectData pm) throws Exception {

		// Group
		try {
			if (pm.getGroup() != null) {
				logger.debug("Entered valid group name");
			} else {
				throw new ValidNameException("Enter valid group name");
			}
		} catch (Exception e) {

		}
		// Artifact
		try {
			if (pm.getArtifact() != null) {
				logger.debug("Entered valid artifact name");
			} else {
				throw new ValidNameException("Enter valid artifact name");
			}
		} catch (Exception e) {

		}
		// Description
		try {
			if (pm.getDescription() != null) {
				logger.debug("Entered valid description");
			} else {
				throw new ValidNameException("Enter valid description");
			}
		} catch (Exception e) {

		}
		// PackagingType

		try {
			if (pm.getPackagingType() != null
					&& (pm.getPackagingType() == PackagingType.Jar || pm.getPackagingType() == PackagingType.War)) {
				logger.debug("Entered valid packaging type");
			} else {
				throw new ValidNameException("Valid packaging types are Jar and War");
			}
		} catch (Exception e) {

		}
		// JavaVersion
		try {
			if (pm.getJavaVersion() != null && (pm.getJavaVersion() == JavaVersion.Java8
					|| pm.getJavaVersion() == JavaVersion.Java11 || pm.getJavaVersion() == JavaVersion.Java17
					|| pm.getJavaVersion() == JavaVersion.Java20)) {
				logger.debug("Entered valid packaging type");
			} else {
				throw new ValidNameException("Valid packaging types are Jar and War");
			}
		} catch (

		Exception e) {

		}

	}

}
