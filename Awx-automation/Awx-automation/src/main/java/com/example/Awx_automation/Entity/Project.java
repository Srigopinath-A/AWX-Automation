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
	private Long id;
    private String name;
    private String description = "";
    private String scmType = "";
    private String localPath = "";
    private String scmUrl = "";
    private String scmBranch = "";
    private String scmRefspec = "";
    private boolean scmClean = false;
    private boolean scmTrackSubmodules = false;
    private boolean scmDeleteOnUpdate = false;
    private Long credential;
    private int timeout = 0;
    private Long organization;
    private boolean scmUpdateOnLaunch = false;
    private int scmUpdateCacheTimeout = 0;
    private boolean allowOverride = false;
    private Long defaultEnvironment;
    private Long signatureValidationCredential;
}
