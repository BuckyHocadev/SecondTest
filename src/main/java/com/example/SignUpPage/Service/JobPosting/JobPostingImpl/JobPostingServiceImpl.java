package com.example.SignUpPage.Service.JobPosting.JobPostingImpl;
import com.example.SignUpPage.Model.Jobs.JobDescriptionModel;
import com.example.SignUpPage.Service.JobPosting.JobPostingService;
import com.example.SignUpPage.Model.Jobs.BriefJobDescriptionModel;
import com.example.SignUpPage.Repository.JobPosting.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobPostingServiceImpl implements JobPostingService {

    @Autowired
    private JobPostingRepository repository;

    @Override
    public void addJobDescriptions(JobDescriptionModel informations) {
        JobDescriptionModel reponse = repository.save(informations);
    }

    @Override
    public List<BriefJobDescriptionModel> listBriefJobDescriptions() {
        return repository.listBriefJobDescriptions();
    }

    @Override
    public Optional<JobDescriptionModel> getJobById(String id) {
        return repository.findById(Long.valueOf(id));
    }
}
