package com.example.Awx_automation.Entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobTemplateTrigger {
	@Id
	private int jobTemplateId;
}
