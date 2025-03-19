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
public class JobTemplateRequest {

	private String name;
    private String description;
    private String jobType;
    private int inventory;
    private int project;
    private String playbook;
}
