package com.example.Awx_automation.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Awx_automation.Entity.Notification_templates;

@Repository
public interface NotificationRep extends MongoRepository<Notification_templates, String>{

}

