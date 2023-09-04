package com.basic.initializer.interfaces;

import com.basic.initializer.model.ProjectData;

public interface IDirectoryOperations {
	
	//create directory
	public void createDirectory(ProjectData data);
	
	//populate files to the directory
	public void addFiles(ProjectData data);
	
	
	//zip directory
	public void zipDirectory(ProjectData data);

}
