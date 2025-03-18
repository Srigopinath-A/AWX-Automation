package com.example.Awx_automation.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobTemplateRequest {
	
	 private String name;
	    private int project;
	    private String playbook;
	    private int inventory;
	    private int organization;
}
