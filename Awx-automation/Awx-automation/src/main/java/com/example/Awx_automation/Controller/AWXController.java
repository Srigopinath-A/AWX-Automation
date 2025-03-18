package com.example.Awx_automation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	public String createProject(@RequestParam String name, @RequestParam String scmType, @RequestParam String scmUrl,
			@RequestParam String scmBranch, @RequestParam int scmCredential, @RequestParam int organization) {
		return awxService.createProject(name, scmType, scmUrl, scmBranch, scmCredential, organization);
	}

	@PostMapping("/create-job-template")
	public String createJobTemplate(@RequestParam String name, @RequestParam int projectId,
			@RequestParam String playbook, @RequestParam int inventory, @RequestParam int organization) {
		return awxService.createJobTemplate(name, projectId, playbook, inventory, organization);
	}

	@PostMapping("/trigger-job")
	public String triggerJob(@RequestParam int jobTemplateId) {
		return awxService.triggerJob(jobTemplateId);
	}
}