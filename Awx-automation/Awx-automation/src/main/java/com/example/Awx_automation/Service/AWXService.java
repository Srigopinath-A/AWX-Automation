package com.example.Awx_automation.Service;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Awx_automation.Entity.AuthRequest;
import com.example.Awx_automation.Entity.AuthResponse;
import com.example.Awx_automation.Entity.Credential;
import com.example.Awx_automation.Entity.CredentialResponse;
import com.example.Awx_automation.Entity.Host;
import com.example.Awx_automation.Entity.HostResponse;
import com.example.Awx_automation.Entity.Inventory;
import com.example.Awx_automation.Entity.InventoryResponse;
import com.example.Awx_automation.Entity.JobTemplateRequest;
import com.example.Awx_automation.Entity.JobTemplateResponse;
import com.example.Awx_automation.Entity.ProjectRequest;
import com.example.Awx_automation.Entity.ProjectResponse;


@Service
public class AWXService {
	 private final RestTemplate restTemplate;
	    private final String awxApiUrl;
	    private final String token;

	    public AWXService(RestTemplate restTemplate, @Value("${awx.api.url}") String awxApiUrl, @Value("${awx.api.token}") String token) {
	        this.restTemplate = restTemplate;
	        this.awxApiUrl = awxApiUrl;
	        this.token = token;
	    }

	    private HttpHeaders createAuthHeaders() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(token);
	        return headers;
	    }

	    public ProjectResponse createProject(ProjectRequest projectRequest) {
	        HttpEntity<ProjectRequest> request = new HttpEntity<>(projectRequest, createAuthHeaders());
	        ResponseEntity<ProjectResponse> response = restTemplate.postForEntity(awxApiUrl + "/projects/", request, ProjectResponse.class);
	        return response.getBody();
	    }

	    public JobTemplateResponse createJobTemplate(JobTemplateRequest jobTemplateRequest) {
	        HttpEntity<JobTemplateRequest> request = new HttpEntity<>(jobTemplateRequest, createAuthHeaders());
	        ResponseEntity<JobTemplateResponse> response = restTemplate.postForEntity(awxApiUrl + "/job_templates/", request, JobTemplateResponse.class);
	        return response.getBody();
	    }

	    public String triggerJob(int jobTemplateId) {
	        HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
	        ResponseEntity<String> response = restTemplate.postForEntity(awxApiUrl + "/job_templates/" + jobTemplateId + "/launch/", request, String.class);
	        return response.getBody();
	    }

	    public List<Inventory> fetchInventories() {
	        HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
	        ResponseEntity<InventoryResponse> response = restTemplate.exchange(
	                awxApiUrl + "/inventories/",
	                HttpMethod.GET,
	                request,
	                InventoryResponse.class
	        );
	        return response.getBody().getResults();
	    }

	    public List<Host> fetchHosts() {
	        HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
	        ResponseEntity<HostResponse> response = restTemplate.exchange(
	                awxApiUrl + "/hosts/",
	                HttpMethod.GET,
	                request,
	                HostResponse.class
	        );
	        return response.getBody().getResults();
	    }

	    public List<Credential> fetchCredentials() {
	        HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
	        ResponseEntity<CredentialResponse> response = restTemplate.exchange(
	                awxApiUrl + "/credentials/",
	                HttpMethod.GET,
	                request,
	                CredentialResponse.class
	        );
	        return response.getBody().getResults();
	    }

	    public List<ProjectResponse> fetchProjects() {
	        HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
	        ResponseEntity<ProjectResponse[]> response = restTemplate.exchange(
	                awxApiUrl + "/projects/",
	                HttpMethod.GET,
	                request,
	                ProjectResponse[].class
	        );
	        return Arrays.asList(response.getBody());
	    }
}
