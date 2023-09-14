package com.example.SignUpPage.Controller;
import com.example.SignUpPage.Model.Jobs.JobDescriptionModel;
import com.example.SignUpPage.Service.JobPosting.JobPostingService;
import com.example.SignUpPage.Model.Jobs.BriefJobDescriptionModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/jobs")
@CrossOrigin("*")
public class ApplicationJobPostingController {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationRegistrationController.class);

    @Autowired
    private JobPostingService service;

    @PostMapping("/add")
    public ResponseEntity<?> addJobDescription(@RequestBody JobDescriptionModel informations){
        service.addJobDescriptions(informations);
        return ResponseEntity.status(HttpStatus.OK).body("Added successfully ");
    }

    @PostMapping("/list")
    public ResponseEntity<List<BriefJobDescriptionModel>> listJobDescriptions(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listBriefJobDescriptions());
    }

    @PostMapping("/get")
    public Optional<JobDescriptionModel> getJobById(@RequestBody BriefJobDescriptionModel jobDescriptionModel) {
        System.out.println("it is fucking here");
        return service.getJobById(String.valueOf(jobDescriptionModel.getId()));

    }
}
