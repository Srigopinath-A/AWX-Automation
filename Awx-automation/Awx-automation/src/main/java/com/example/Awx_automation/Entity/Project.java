package com.example.Awx_automation.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "project")
public class Project {
	
	@Id
	private int id;
	private String name;
	private String description;
	private int organization;
	private String scmType;
}
