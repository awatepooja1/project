package com.basic.initializer.model;

import java.util.List;

import javax.persistence.Entity;

import com.basic.initializer.entity.MavenDependencies;



public class ProjectData {
	
	int id;
	String group;
	String artifact;
	String name;
	String description;
	PackagingType packagingType;
	JavaVersion javaVersion;
	List<String> mavenDependencies;
	
	
	public ProjectData(int id, String group, String artifact, String name, String description,
			PackagingType packagingType, JavaVersion javaVersion, List<String> mavenDependencies) {
		super();
		this.id = id;
		this.group = group;
		this.artifact = artifact;
		this.name = name;
		this.description = description;
		this.packagingType = packagingType;
		this.javaVersion = javaVersion;
		this.mavenDependencies = mavenDependencies;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	public PackagingType getPackagingType() {
		return packagingType;
	}
	public void setPackagingType(PackagingType packagingType) {
		this.packagingType = packagingType;
	}
	public JavaVersion getJavaVersion() {
		return javaVersion;
	}
	public void setJavaVersion(JavaVersion javaVersion) {
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
		return "ProjectData [id=" + id + ", group=" + group + ", artifact=" + artifact + ", name=" + name
				+ ", description=" + description + ", packagingType=" + packagingType + ", javaVersion=" + javaVersion
				+ ", mavenDependencies=" + mavenDependencies + "]";
	}

}
