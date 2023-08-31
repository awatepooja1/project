package com.basic.initializer.entity;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class ProjectMetadata {

	public ProjectMetadata() {
		// TODO Auto-generated constructor stub
	}

	String group;
	String artifact;
	String name;
	String description;
	Enum packagingType;
	Enum javaVersion;
	List<String> mavenDependencies;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getArtifact() {
		return artifact;
	}

	public void setArtifact(String artifact) {
		this.artifact = artifact;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Enum getPackagingType() {
		return packagingType;
	}

	public void setPackagingType(Enum packagingType) {
		this.packagingType = packagingType;
	}

	public Enum getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(Enum javaVersion) {
		this.javaVersion = javaVersion;
	}

	public List<String> getMavenDependencies() {
		return mavenDependencies;
	}

	public void setMavenDependencies(List<String> mavenDependencies) {
		this.mavenDependencies = mavenDependencies;
	}

	@Override
	public String toString() {
		return "ProjectMetadata [group=" + group + ", artifact=" + artifact + ", name=" + name + ", description="
				+ description + ", packagingType=" + packagingType + ", javaVersion=" + javaVersion
				+ ", mavenDependencies=" + mavenDependencies + "]";
	}

}
