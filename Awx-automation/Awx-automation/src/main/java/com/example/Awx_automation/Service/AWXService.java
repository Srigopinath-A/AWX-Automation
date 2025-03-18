package com.example.Awx_automation.Service;



import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Awx_automation.Entity.JobTemplateRequest;
import com.example.Awx_automation.Entity.ProjectRequest;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.Value;
import feign.*;

@Service
public class AWXService {

    private final RestTemplate restTemplate;
    private final String awxApiUrl;
    private final String apiToken;

    public AWXService(RestTemplate restTemplate, @org.springframework.beans.factory.annotation.Value("${awx.api.url}") String awxApiUrl, @org.springframework.beans.factory.annotation.Value("${awx.api.token}") String apiToken) {
        this.restTemplate = restTemplate;
        this.awxApiUrl = awxApiUrl;
        this.apiToken = apiToken;
    }

    public String createProject(String name, String scmType, String scmUrl, String scmBranch, int scmCredential, int organization) {
        ProjectRequest projectRequest = new ProjectRequest(name, scmType, scmUrl, scmBranch, scmCredential, organization);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);

        HttpEntity<ProjectRequest> request = new HttpEntity<>(projectRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(awxApiUrl + "/projects/", request, String.class);

        return response.getBody();
    }

    public String createJobTemplate(String name, int projectId, String playbook, int inventory, int organization) {
        JobTemplateRequest jobTemplateRequest = new JobTemplateRequest(name, projectId, playbook, inventory, organization);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiToken);

        HttpEntity<JobTemplateRequest> request = new HttpEntity<>(jobTemplateRequest, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(awxApiUrl + "/job_templates/", request, String.class);

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
}
