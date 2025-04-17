package com.example.Awx_automation.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Document(collection = "Notification_templates")
public class Notification_templates {
	@Id
	private int id;
	private String name;
}

