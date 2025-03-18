package com.example.Awx_automation.Entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectRequest {
	
	   private String name;
	    private String scmType;
	    private String scmUrl;
	    private String scmBranch;
	    private int scmCredential;
	    private int organization;
	
	
	
	
	
}
