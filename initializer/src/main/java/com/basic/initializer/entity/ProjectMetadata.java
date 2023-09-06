package com.basic.initializer.entity;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projectdata")
public class ProjectMetadata implements Serializable{

	public ProjectMetadata() {

	}

	public ProjectMetadata(int id, String group, String artifact, String name, String description, String packagingType,
			String javaVersion) {
		super();
		this.id = id;
		this.group = group;
		this.artifact = artifact;
		this.name = name;
		this.description = description;
		this.packagingType = packagingType;
		this.javaVersion = javaVersion;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;

	@Column(name = "GROUP_NAME")
	String group;

	String artifact;

	String name;

	String description;

	@Column(name = "PACKAGING_TYPE")
	String packagingType;

	@Column(name = "JAVA_VERSION")
	String javaVersion;
	
	

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

	public String getPackagingType() {
		return packagingType;
	}

	public void setPackagingType(String packagingType) {
		this.packagingType = packagingType;
	}

	public String getJavaVersion() {
		return javaVersion;
	}

	public void setJavaVersion(String javaVersion) {
		this.javaVersion = javaVersion;
	}

	

	@Override
	public String toString() {
		return "ProjectMetadata [group=" + group + ", artifact=" + artifact + ", name=" + name + ", description="
				+ description + ", packagingType=" + packagingType + ", javaVersion=" + javaVersion
				+ "]";
	}

}
