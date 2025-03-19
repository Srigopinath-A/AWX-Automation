package com.example.Awx_automation.Repo;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Awx_automation.Entity.Host;

public interface HostRepository extends MongoRepository<Host, String> {
}
