package com.example.Awx_automation.Service;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Awx_automation.Entity.Credential;
import com.example.Awx_automation.Entity.Host;
import com.example.Awx_automation.Entity.Inventory;
import com.example.Awx_automation.Entity.JobTemplateRequest;
import com.example.Awx_automation.Entity.JobTemplateResponse;
import com.example.Awx_automation.Entity.ProjectRequest;
import com.example.Awx_automation.Entity.ProjectResponse;


@Service
public class AWXService {

	   private final RestTemplate restTemplate;
	    private final String awxApiUrl;
	    private final String apiToken;

	    public AWXService(RestTemplate restTemplate, @Value("${awx.api.url}") String awxApiUrl, @Value("${awx.api.token}") String apiToken) {
	        this.restTemplate = restTemplate;
	        this.awxApiUrl = awxApiUrl;
	        this.apiToken = apiToken;
	    }

	    public ProjectResponse createProject(ProjectRequest projectRequest) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(apiToken);

	        HttpEntity<ProjectRequest> request = new HttpEntity<>(projectRequest, headers);
	        ResponseEntity<ProjectResponse> response = restTemplate.postForEntity(awxApiUrl + "/projects/", request, ProjectResponse.class);

	        return response.getBody();
	    }

	    public JobTemplateResponse createJobTemplate(JobTemplateRequest jobTemplateRequest) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(apiToken);

	        HttpEntity<JobTemplateRequest> request = new HttpEntity<>(jobTemplateRequest, headers);
	        ResponseEntity<JobTemplateResponse> response = restTemplate.postForEntity(awxApiUrl + "/job_templates/", request, JobTemplateResponse.class);

	        return response.getBody();
	    }

	    public String triggerJob(int jobTemplateId) {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        headers.setBearerAuth(apiToken);

	        HttpEntity<Void> request = new HttpEntity<>(headers);
	        ResponseEntity<String> response = restTemplate.postForEntity(awxApiUrl + "/job_templates/" + jobTemplateId + "/launch/", request, String.class);

	        return response.getBody();
	    }

	    public List<Inventory> fetchInventories() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(apiToken);
	        HttpEntity<Void> request = new HttpEntity<>(headers);

	        ResponseEntity<Inventory[]> response = restTemplate.exchange(
	                awxApiUrl + "/constructed_inventories/",
	                HttpMethod.GET,
	                request,
	                Inventory[].class
	        );

	        return Arrays.asList(response.getBody());
	    }

	    public List<Host> fetchHosts() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(apiToken);
	        HttpEntity<Void> request = new HttpEntity<>(headers);

	        ResponseEntity<Host[]> response = restTemplate.exchange(
	                awxApiUrl + "/hosts/",
	                HttpMethod.GET,
	                request,
	                Host[].class
	        );

	        return Arrays.asList(response.getBody());
	    }

	    public List<Credential> fetchCredentials() {
	        HttpHeaders headers = new HttpHeaders();
	        headers.setBearerAuth(apiToken);
	        HttpEntity<Void> request = new HttpEntity<>(headers);

	        ResponseEntity<Credential[]> response = restTemplate.exchange(
	                awxApiUrl + "/credentials/",
	                HttpMethod.GET,
	                request,
	                Credential[].class
	        );

	        return Arrays.asList(response.getBody());
	    }
}
