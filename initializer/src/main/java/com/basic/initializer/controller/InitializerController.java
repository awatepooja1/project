package com.basic.initializer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.basic.initializer.entity.ProjectMetadata;
import com.basic.initialzer.service.GenerateProject;

@RestController
@RequestMapping("/init")
public class InitializerController {
	
	@Autowired
	public GenerateProject generate;
	
	
	@GetMapping("/getData")
	public ResponseEntity<String> getMetaData(@RequestBody ProjectMetadata data){
		
		try {
			generate.save(data);
			return new ResponseEntity<>("Data Saved Successfully", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	

}
