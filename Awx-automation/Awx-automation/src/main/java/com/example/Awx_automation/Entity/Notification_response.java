package com.example.Awx_automation.Entity;

import java.util.List;

public class Notification_response {
	
	private List<Notification_templates> results;

    public List<Notification_templates> getResults() {
        return results;
    }

    public void setResults(List<Notification_templates> results) {
        this.results = results;
    }
}

