package com.example.Awx_automation.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Awx_automation.Entity.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

}
