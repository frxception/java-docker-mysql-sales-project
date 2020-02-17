package com.zulhke.salesstore.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sales-upload")
public class SalesUploadController {
    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job batchJob;

    @GetMapping
    public String upload() throws JobParametersInvalidException, 
            JobExecutionAlreadyRunningException, 
            JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExec = jobLauncher.run(batchJob, parameters);

        System.out.println("[ Sales Job Execution Status ] - " + jobExec.getStatus());
        System.out.println("[ Job Execution is running ] - " + jobExec.getCreateTime());

        StringBuilder jobMsg = new StringBuilder();
        jobMsg.append("<div style=\"font-family: arial; padding: 50px\">");

        if(jobExec.getStatus() == BatchStatus.COMPLETED){
            jobMsg.append("<h2 style=\"color:green\"> File upload "+jobExec.getStatus()+"</h2>");
            jobMsg.append("<div> Time uploaded: "+jobExec.getCreateTime()+"</div>");
            jobMsg.append("<div> Completed in: "+jobExec.getEndTime()+"</div>");
        }else{
            jobMsg.append("<h2 style=\"color:red\"> File upload "+jobExec.getStatus()+"</h2>");
            jobMsg.append("<div> Upload in: "+jobExec.getEndTime()+"</div>");
        }
        jobMsg.append("<br><br><a href=\"/\" class=\"button\">Back to home</a>");
        jobMsg.append("</div>");

        return jobMsg.toString();
    }
}
