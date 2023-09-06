package com.basic.initializer.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MAVEN_DEPENDENCY")
public class MavenDependencies implements Serializable {
	@Id
	@GeneratedValue
	int id;

	@Column
	String name;

	@Column
	String repoPath;

	
	public MavenDependencies(int iD, String name, String repo_path, ProjectMetadata projectMetadata) {
		super();
		id = iD;
		this.name = name;
		this.repoPath = repo_path;
		
	}


	public MavenDependencies() {

	}

	public int getID() {
		return id;
	}

	public void setID(int iD) {
		id = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRepoPath() {
		return repoPath;
	}

	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}


	
	
	@Override
	public String toString() {
		return "MavenDependencies [ID=" + id + ", name=" + name + ", repo_path=" + repoPath + "]";
	}

}
