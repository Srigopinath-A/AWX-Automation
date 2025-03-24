package com.example.Awx_automation.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="job_Template")
public class JobTemplateRequest {
	
	@Id
	private String name;
	private String description;
	private String jobType;
	private Long inventory;
	private Long project;
	private String playbook;
	private Long credentials;
}
