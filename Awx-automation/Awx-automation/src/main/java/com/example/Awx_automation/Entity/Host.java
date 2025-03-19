package com.example.Awx_automation.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Document(collection = "hosts")
public class Host {
	@Id
	private int id;
	private String name;
	
}
