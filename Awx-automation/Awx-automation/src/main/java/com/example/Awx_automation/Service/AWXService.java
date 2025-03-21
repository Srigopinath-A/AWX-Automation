package com.example.Awx_automation.Service;



import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Awx_automation.Entity.Credential;
import com.example.Awx_automation.Entity.CredentialResponse;
import com.example.Awx_automation.Entity.Host;
import com.example.Awx_automation.Entity.HostResponse;
import com.example.Awx_automation.Entity.Inventory;
import com.example.Awx_automation.Entity.InventoryResponse;
import com.example.Awx_automation.Entity.JobTemplateRequest;
import com.example.Awx_automation.Entity.JobTemplateResponse;
import com.example.Awx_automation.Entity.JobtemplateResponseWrapper;
import com.example.Awx_automation.Entity.Organization;
import com.example.Awx_automation.Entity.OrganizationResponse;
import com.example.Awx_automation.Entity.Project;
import com.example.Awx_automation.Entity.ProjectResponse;
import com.example.Awx_automation.Entity.ProjectResponseWrapper;
import com.example.Awx_automation.Repo.CredentialRespository;
import com.example.Awx_automation.Repo.HostRepository;
import com.example.Awx_automation.Repo.InventoryRepository;
import com.example.Awx_automation.Repo.JobTemplateRepository;
import com.example.Awx_automation.Repo.OrganizationRepository;
import com.example.Awx_automation.Repo.ProjectRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class AWXService {
	private static final Logger logger = Logger.getLogger(AWXService.class.getName());

    private final RestTemplate restTemplate;
    private final String awxApiUrl;
    private final String token;

    @Autowired
    private HostRepository hostRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private JobTemplateRepository jobTemplateRepository;
    
    @Autowired
    private CredentialRespository credentialRepository;

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

    public List<Host> fetchHosts() {
        try {
            HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
            ResponseEntity<HostResponse> response = restTemplate.exchange(
                    awxApiUrl + "/hosts/",
                    HttpMethod.GET,
                    request,
                    HostResponse.class
            );
            List<Host> hosts = response.getBody().getResults();
            hostRepository.saveAll(hosts);
            return hosts;
        } catch (Exception e) {
            logger.severe("Error fetching hosts: " + e.getMessage());
            throw e;
        }
    }
    
    public List<Credential> fetchCredential() {
        try {
            HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
            ResponseEntity<CredentialResponse> response = restTemplate.exchange(
                    awxApiUrl + "/credentials/",
                    HttpMethod.GET,
                    request,
                    CredentialResponse.class
            );
            List<Credential> credentials = response.getBody().getResults();
            credentialRepository.saveAll(credentials);
            return credentials;
        } catch (Exception e) {
            logger.severe("Error fetching credentials: " + e.getMessage());
            throw e;
        }
    }

    public List<Inventory> fetchInventories() {
        try {
            HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
            ResponseEntity<InventoryResponse> response = restTemplate.exchange(
                    awxApiUrl + "/inventories/",
                    HttpMethod.GET,
                    request,
                    InventoryResponse.class
            );
            List<Inventory> inventories = response.getBody().getResults();
            inventoryRepository.saveAll(inventories);
            return inventories;
        } catch (Exception e) {
            logger.severe("Error fetching inventories: " + e.getMessage());
            throw e;
        }
    }

    public List<Organization> fetchOrganizations() {
        try {
            HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
            ResponseEntity<OrganizationResponse> response = restTemplate.exchange(
                    awxApiUrl + "/organizations/",
                    HttpMethod.GET,
                    request,
                    OrganizationResponse.class
            );
            List<Organization> organizations = response.getBody().getResults();
            organizationRepository.saveAll(organizations);
            return organizations;
        } catch (Exception e) {
            logger.severe("Error fetching organizations: " + e.getMessage());
            throw e;
        }
    }

    public List<Project> fetchProjects() {
        try {
            HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
            ResponseEntity<ProjectResponseWrapper> response = restTemplate.exchange(
                    awxApiUrl + "/projects/",
                    HttpMethod.GET,
                    request,
                    ProjectResponseWrapper.class
            );
            List<ProjectResponse> projectResponses = response.getBody().getResults();
            
            // Map ProjectResponse to Project
            List<Project> projects = projectResponses.stream().map(pr -> {
                Project project = new Project();
                project.setId(pr.getId());
                project.setName(pr.getName());
                // Set other fields if needed
                return project;
            }).collect(Collectors.toList());

            projectRepository.saveAll(projects);
            return projects;
        } catch (Exception e) {
            logger.severe("Error fetching projects: " + e.getMessage());
            throw e;
        }
    }

    public List<JobTemplateResponse> fetchJobTemplates() {
        try {
            HttpEntity<Void> request = new HttpEntity<>(createAuthHeaders());
            ResponseEntity<JobtemplateResponseWrapper> response = restTemplate.exchange(
                    awxApiUrl + "/job_templates/",
                    HttpMethod.GET,
                    request,
                    JobtemplateResponseWrapper.class
            );
            List<JobTemplateResponse> jobTemplates = response.getBody().getResults();
            jobTemplateRepository.saveAll(jobTemplates);
            return jobTemplates;
        } catch (Exception e) {
            logger.severe("Error fetching job templates: " + e.getMessage());
            throw e;
        }
    }

    public ProjectResponse createProject(Project project) throws Exception {
        try {
            logger.info("Creating project with SCM type: " + project.getScmType());
            
            // Check if a project with the same name and organization already exists
            List<Project> existingProjects = fetchProjects();
            for (Project existingProject : existingProjects) {
                if (existingProject.getName().equals(project.getName()) &&
                    existingProject.getOrganization().equals(project.getOrganization())) {
                    throw new IllegalArgumentException("Project with this (name, organization) combination already exists.");
                }
            }

            HttpEntity<Project> request = new HttpEntity<>(project, createAuthHeaders());
            
            // Log the request body for debugging
            logger.info("Request body: " + new ObjectMapper().writeValueAsString(project));
            
            ResponseEntity<ProjectResponse> response = restTemplate.postForEntity(awxApiUrl + "/projects/", request, ProjectResponse.class);
            ProjectResponse projectResponse = response.getBody();
            project.setId(projectResponse.getId());
            projectRepository.save(project);
            return projectResponse;
        } catch (IllegalArgumentException e) {
            logger.warning(e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.severe("Error creating project: " + e.getMessage());
            throw e;
        }
    }

    public JobTemplateResponse createJobTemplate(JobTemplateRequest jobTemplateRequest) {
        try {
            HttpEntity<JobTemplateRequest> request = new HttpEntity<>(jobTemplateRequest, createAuthHeaders());
            ResponseEntity<JobTemplateResponse> response = restTemplate.postForEntity(awxApiUrl + "/job_templates/", request, JobTemplateResponse.class);
            JobTemplateResponse jobTemplateResponse = response.getBody();
            jobTemplateRepository.save(jobTemplateResponse);
            return jobTemplateResponse;
        } catch (Exception e) {
            logger.severe("Error creating job template: " + e.getMessage());
            throw e;
        }
    }
}
