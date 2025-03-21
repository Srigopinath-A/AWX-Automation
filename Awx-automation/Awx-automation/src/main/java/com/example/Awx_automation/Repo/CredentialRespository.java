package com.example.Awx_automation.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Awx_automation.Entity.Credential;

public interface CredentialRespository extends MongoRepository<Credential, String> {

}
