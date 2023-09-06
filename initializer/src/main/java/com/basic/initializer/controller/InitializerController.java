package com.basic.initializer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.basic.initializer.interfaces.IDirectoryOperations;
import com.basic.initializer.interfaces.IProjectOperations;
import com.basic.initializer.interfaces.IValidation;
import com.basic.initializer.model.ProjectData;



@RestController
@RequestMapping("/init")
public class InitializerController {
	
	@Autowired
	public IProjectOperations generate;
	
	@Autowired
	public IDirectoryOperations dir;
	
	@Autowired
	public IValidation valid;
	
	
	
	@GetMapping("/getData")
	public ResponseEntity<String> getMetaData(@RequestBody ProjectData data){
		
		try {
			valid.validateDetails(data);
			generate.save(data);
			return new ResponseEntity<>("Data Saved Successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping("/createDirectory")
	public ResponseEntity<String> createDirectory(@RequestBody ProjectData data){
		
		try {
			valid.validateDetails(data);
			dir.createDirectory(data);
			return new ResponseEntity<>("Directory created", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@PostMapping("/addFiles")
	public ResponseEntity<String> addFiles(@RequestBody ProjectData data){
		
		try {
			valid.validateDetails(data);
			dir.addFiles(data);
			return new ResponseEntity<>("Files added successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@PostMapping("/zipFolder")
	public ResponseEntity<String> zipFolder(@RequestBody ProjectData data){
		
		try {
			valid.validateDetails(data);
			dir.zipDirectory(data);
			return new ResponseEntity<>("Project zip folder ready", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@PostMapping("/createBasicApp")
	public ResponseEntity<String> createBasicApp(@RequestBody ProjectData data){
		
		try {
			valid.validateDetails(data);
			generate.save(data);
			dir.createDirectory(data);
			dir.addFiles(data);
			dir.zipDirectory(data);
			return new ResponseEntity<>("Project zip folder ready", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	

}
