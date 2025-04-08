package com.example.Awx_automation.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Awx_automation.Entity.Credential;
import com.example.Awx_automation.Entity.Host;
import com.example.Awx_automation.Entity.Inventory;
import com.example.Awx_automation.Entity.JobTemplateRequest;
import com.example.Awx_automation.Entity.JobTemplateResponse;
import com.example.Awx_automation.Entity.JobTemplateTrigger;
import com.example.Awx_automation.Entity.Organization;
import com.example.Awx_automation.Entity.Project;
import com.example.Awx_automation.Entity.ProjectResponse;
import com.example.Awx_automation.Service.AWXService;
import com.fasterxml.jackson.databind.JsonNode;


@RestController
@RequestMapping("/api/awx")
public class AWXController {
	
	 private final AWXService awxService;

	    @Autowired
	    public AWXController(AWXService awxService) {
	        this.awxService = awxService;
	    }

	    @GetMapping("/hosts")
	    public List<Host> getHosts() {
	        return awxService.fetchHosts();
	    }

	    @GetMapping("/inventories")
	    public List<Inventory> getInventories() {
	        return awxService.fetchInventories();
	    }

	    @GetMapping("/organizations")
	    public List<Organization> getOrganizations() {
	        return awxService.fetchOrganizations();
	    }
	    
	    @GetMapping("/credentials")
	    public List<Credential> getCredential(){
	    	return awxService.fetchCredential();
	    }

	    @GetMapping("/projects")
	    public List<Project> getProjects() {
	        return awxService.fetchProjects();
	    }

	    @GetMapping("/job-templates")
	    public List<JobTemplateResponse> getJobTemplates() {
	        return awxService.fetchJobTemplates();
	    }

	    @PostMapping("/create-project")
	    public ProjectResponse createProject(@RequestBody Project project) throws Exception {
	        return awxService.createProject(project);
	    }

	    @PostMapping("/create-job-template")
	    public JobTemplateResponse createJobTemplate(@RequestBody JobTemplateRequest jobTemplateRequest) {
	        return awxService.createJobTemplate(jobTemplateRequest);
	    }
	    @PostMapping("/trigger-job")
	    public Integer triggerJobTemplate(@RequestBody JobTemplateTrigger jobTriggerRequest) {
	        return awxService.triggerJobTemplate(jobTriggerRequest);
	    }

	    @GetMapping("/job-result/{jobId}")
	    public JsonNode getJobResult(@PathVariable Long jobId) {
	        return awxService.getJobResult(jobId);
	    }

	    @GetMapping("/job-output/{jobId}")
	    public String getJobOutput(@PathVariable Long jobId) {
	        return awxService.getJobOutput(jobId);
	    }
	    
	    @PostMapping("/delete-job/{jobId}")
	    public ResponseEntity<String> deleteJob(@PathVariable Long jobId) {
	        String result = awxService.Deletejob(jobId);
	        if (result.equals("Job deleted successfully")) {
	            return new ResponseEntity<>(result, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	    
	    @PostMapping("/webhook")
	    public void handleWebhook(@RequestBody Map<String, Object> payload) {
	        Long jobId = parseLong(payload.get("job_id"));
	        String jobStatus = (String) payload.get("status");
	        awxService.sendoutput("mailid", jobId, jobStatus);
	    }

	    @PostMapping("/create-notification")
	    public ResponseEntity<String> createNotification(@RequestBody Map<String, Object> payload) {
	        String result = awxService.manageNotificationTemplate(
	        		parseLong(payload.get("job_template_id")),
	        		parseLong(payload.get("notification_template_id")),
	        		(String) payload.get("type"),
	        		(Boolean)payload.get("disassociate")
	        );
	        return new ResponseEntity<>(result, HttpStatus.OK);
	    }

	    private Long parseLong(Object value) {
	        if (value instanceof Number) {
	            return ((Number) value).longValue();
	        }
	        if (value instanceof String) {
	            return Long.parseLong((String) value);
	        }
	        throw new IllegalArgumentException("Cannot parse value to Long: " + value);
	    }
	    
}