package com.example.Awx_automation.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Awx_automation.Entity.Organization;

public interface OrganizationRepository extends MongoRepository<Organization,String>{

}
