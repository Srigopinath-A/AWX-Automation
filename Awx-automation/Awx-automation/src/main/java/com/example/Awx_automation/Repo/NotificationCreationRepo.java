package com.example.Awx_automation.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Awx_automation.Entity.Notification_Template_Creation;

@Repository
public interface NotificationCreationRepo extends MongoRepository<Notification_Template_Creation, Long>{

}
