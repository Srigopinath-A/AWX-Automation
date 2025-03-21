package com.example.Awx_automation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Awx_automation.Entity.Credential;
import com.example.Awx_automation.Entity.Host;
import com.example.Awx_automation.Entity.Inventory;
import com.example.Awx_automation.Entity.JobTemplateRequest;
import com.example.Awx_automation.Entity.JobTemplateResponse;
import com.example.Awx_automation.Entity.Organization;
import com.example.Awx_automation.Entity.Project;
import com.example.Awx_automation.Entity.ProjectResponse;
import com.example.Awx_automation.Service.AWXService;


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
}