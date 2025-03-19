package com.example.Awx_automation.Repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.Awx_automation.Entity.Inventory;

public interface InventoryRepository extends MongoRepository<Inventory, String> {

}
