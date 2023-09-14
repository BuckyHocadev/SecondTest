package com.example.SignUpPage.Repository.JobPosting;
import com.example.SignUpPage.Model.Jobs.JobDescriptionModel;
import com.example.SignUpPage.Model.Jobs.BriefJobDescriptionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostingRepository extends JpaRepository<JobDescriptionModel, Long> {
    @Query(value="SELECT id, position, company_name, skills FROM job_descriptions", nativeQuery = true)
    List<BriefJobDescriptionModel> listBriefJobDescriptions();

}
