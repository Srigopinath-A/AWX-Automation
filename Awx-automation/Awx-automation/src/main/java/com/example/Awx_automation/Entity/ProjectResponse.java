package com.example.Awx_automation.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectResponse {
	
	private int id;
	private String name;
	private String scmType;
	private String scmUrl;
	private String scmBranch;
	private int scmCredential;
	private int organization;
}
