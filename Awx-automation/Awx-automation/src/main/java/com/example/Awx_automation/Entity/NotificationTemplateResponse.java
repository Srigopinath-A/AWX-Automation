package com.example.Awx_automation.Entity;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationTemplateResponse {
	private Long id;
	private String name;
	private String description;
	private Long organization;
	private String notificationType;
	private Map<String, Object> notificationConfiguration;
}
