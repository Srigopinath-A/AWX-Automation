package com.example.Awx_automation.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobTemplateResponse {
	
	private int id;
	private String name;
	private int project;
	private String playbook;
	private int inventory;
	private int organization;
}
