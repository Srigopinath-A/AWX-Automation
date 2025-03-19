package com.example.Awx_automation.Entity;

import java.util.List;

public class CredentialResponse {
	private List<Credential> results;

    public List<Credential> getResults() {
        return results;
    }

    public void setResults(List<Credential> results) {
        this.results = results;
    }
}

