package com.example.Awx_automation.Entity;

import java.util.List;

public class InventoryResponse {
	
	   private List<Inventory> results;

	    public List<Inventory> getResults() {
	        return results;
	    }

	    public void setResults(List<Inventory> results) {
	        this.results = results;
	    }
}
