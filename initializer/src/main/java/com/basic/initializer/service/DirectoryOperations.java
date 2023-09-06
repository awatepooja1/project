package com.basic.initializer.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.basic.initializer.interfaces.IDirectoryOperations;
import com.basic.initializer.model.ProjectData;

@Service
public class DirectoryOperations implements IDirectoryOperations {

	@Value("${folderPath}")
	String folderPath;

	@Value("${mvnwPath1}")
	String mvnwPath1;

	@Value("${mvnwPath2}")
	String mvnwPath2;

	@Value("${helpPath}")
	String helpPath;

	@Value("${mvn_wrapper_jar}")
	String mvn_wrapper_jar;

	@Value("${mvn_wrapper_prop}")
	String mvn_wrapper_prop;

	private static final Logger logger = LoggerFactory.getLogger(ProjectOperations.class);

	@Override
	public void createDirectory(ProjectData data) {

		String group = data.getGroup();
		String[] groups = group.split("\\.");
		String subPath = groups[0] + "\\" + groups[1] + "\\" + data.getName();

		try {
		List<String> paths = new ArrayList<>();
		Path checkPath = Paths.get(folderPath + data.getArtifact());
		if(Files.exists(checkPath, LinkOption.NOFOLLOW_LINKS)) {
			Files
            .walk(checkPath) // Traverse the file tree in depth-first order
            .sorted(Comparator.reverseOrder())
            .forEach(path -> {
                try {
                    logger.debug("Deleting: " + path);
                    Files.delete(path);  //delete each file or directory
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
		}
		paths.add(folderPath + data.getArtifact() + "\\src\\main\\java\\" + subPath);
		paths.add(folderPath + data.getArtifact() + "\\src\\main\\resources\\" + subPath);
		paths.add(folderPath + data.getArtifact() + "\\src\\test\\java\\" + subPath);
		paths.add(folderPath + data.getArtifact() + "\\\\.mvn\\wrapper");
		

			for (String path : paths) {
				File f = new File(path);
				if (f.mkdirs()) {
					logger.debug("Directory created with the name : " + data.getArtifact());
				} else
					throw new IOException("Directory not created");
			}
		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	@Override
	public void addFiles(ProjectData data) {
		// File mvnw = new File(mvnwPath1);
		try {
			Path mvnwfolder = Paths.get(folderPath + data.getArtifact() +"\\mvnw" );
			Path mvnwdfolder = Paths.get(folderPath + data.getArtifact() +"\\mvnwd" );
			Path helpfile = Paths.get(folderPath + data.getArtifact() +"\\help.md" );
			Path wrapperJarFolder = Paths.get(folderPath + data.getArtifact() + "\\\\.mvn\\wrapper\\maven-wrapper.jar");
			Path wrapperPropFolder = Paths.get(folderPath + data.getArtifact() + "\\\\.mvn\\wrapper\\maven-wrapper.properties");
			Path mvnw = Paths.get(mvnwPath1);
			Path mvnwd = Paths.get(mvnwPath2);
			Path help = Paths.get(helpPath);
			Path mvnWrapperJar = Paths.get(mvn_wrapper_jar);
			Path mvnWrapperProp = Paths.get(mvn_wrapper_prop);
			Files.copy(mvnw, mvnwfolder);
			/*
			 * if(Files.exists(mvnwfolder, LinkOption.NOFOLLOW_LINKS)) { Files.copy(mvnw,
			 * mvnwfolder, StandardCopyOption.REPLACE_EXISTING); } else{
			 * Files.createFile(mvnwfolder); Files.copy(mvnw, mvnwfolder,
			 * StandardCopyOption.REPLACE_EXISTING); }
			 */
			Files.copy(mvnwd, mvnwdfolder);
			Files.copy(help, helpfile);
			Files.copy(mvnWrapperJar, wrapperJarFolder);
			Files.copy(mvnWrapperProp, wrapperPropFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void zipDirectory(ProjectData data) {
		try {

			FileOutputStream fos = new FileOutputStream(data.getName() + ".zip");
			ZipOutputStream zipOut = new ZipOutputStream(fos);

			File fileToZip = new File(folderPath + data.getArtifact());
			zipFile(fileToZip, fileToZip.getName(), zipOut);
			zipOut.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}

}
