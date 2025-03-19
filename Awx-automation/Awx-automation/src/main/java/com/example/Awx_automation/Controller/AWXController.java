package com.example.Awx_automation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.Awx_automation.Entity.Credential;
import com.example.Awx_automation.Entity.Host;
import com.example.Awx_automation.Entity.Inventory;
import com.example.Awx_automation.Entity.JobTemplateRequest;
import com.example.Awx_automation.Entity.JobTemplateResponse;
import com.example.Awx_automation.Entity.ProjectRequest;
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

    @PostMapping("/create-project")
    public ProjectResponse createProject(@RequestBody ProjectRequest projectRequest) {
        return awxService.createProject(projectRequest);
    }

    @PostMapping("/create-job-template")
    public JobTemplateResponse createJobTemplate(@RequestBody JobTemplateRequest jobTemplateRequest) {
        return awxService.createJobTemplate(jobTemplateRequest);
    }

    @PostMapping("/trigger-job")
    public String triggerJob(@RequestParam int jobTemplateId) {
        return awxService.triggerJob(jobTemplateId);
    }

    @GetMapping("/inventories")
    public List<Inventory> getInventories() {
        return awxService.fetchInventories();
    }

    @GetMapping("/hosts")
    public List<Host> getHosts() {
        return awxService.fetchHosts();
    }

    @GetMapping("/credentials")
    public List<Credential> getCredentials() {
        return awxService.fetchCredentials();
    }

    @GetMapping("/projects")
    public List<ProjectResponse> getProjects() {
        return awxService.fetchProjects();
    }
}