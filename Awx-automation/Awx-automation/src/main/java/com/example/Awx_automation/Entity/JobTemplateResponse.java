package com.example.Awx_automation.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document( collection = "job_templates")
public class JobTemplateResponse {
	
	@Id
	private String id;
    private String name;
}
