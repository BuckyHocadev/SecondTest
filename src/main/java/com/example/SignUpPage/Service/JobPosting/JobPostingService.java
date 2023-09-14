package com.example.SignUpPage.Service.JobPosting;

import com.example.SignUpPage.Model.Jobs.BriefJobDescriptionModel;
import com.example.SignUpPage.Model.Jobs.JobDescriptionModel;

import java.util.List;
import java.util.Optional;

public interface JobPostingService {
    void addJobDescriptions(JobDescriptionModel informations);
    List<BriefJobDescriptionModel> listBriefJobDescriptions();
    Optional<JobDescriptionModel> getJobById(String id);
}
