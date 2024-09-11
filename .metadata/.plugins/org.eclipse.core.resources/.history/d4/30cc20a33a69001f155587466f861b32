package com.aurionpro.batchprocessing.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job exportUserJob;

    @GetMapping("/batch")
    public ResponseEntity<String> startBatchJob() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("startAt", System.currentTimeMillis()).toJobParameters();
            jobLauncher.run(exportUserJob, jobParameters);
            return ResponseEntity.ok("Batch job has been invoked.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to start the batch job.");
        }
    }

}
