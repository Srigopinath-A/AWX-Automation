package com.example.Awx_automation.Service;

import com.example.Awx_automation.Entity.JobTemplateRequest;
import com.example.Awx_automation.Entity.ProjectRequest;

import feign.Headers;
import feign.Param;
import feign.RequestLine;


//main use of feign : -  it is a java lib for simplify the http client Service code for micro Service arch
//it is a declarative way to interact with AWX

public interface AWXClient {
	
	@RequestLine("POST /projects/") // Post method to Projects end point of the AWX api
	@Headers("Content-Type: application/json") // indicates request body is json format.
	void createproject(ProjectRequest projectrequest);
	
	
	
	@RequestLine("POST /job_templates/")
	@Headers("Content-Type: application/json")
	void createjobtemplate(JobTemplateRequest jobtemplaterequest);
	
	
	@RequestLine("Post /job_templates/{JobTemplateid}/launch/")
	@Headers("Conetent-Type : application/json")
	void launchjobtemplate(@Param("JobTemplateid") int JobTemplateid);

}
