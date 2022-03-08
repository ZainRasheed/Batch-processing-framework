package com.example.BatchProcessingInHouseFramework.controller;

import com.example.BatchProcessingInHouseFramework.core.JobInitiator;
import com.example.BatchProcessingInHouseFramework.core.impl.JobInitiatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class ApiController {

    @Autowired
    private JobInitiator jobInitiator;

    @GetMapping("/startChunkJob")
    public String startChunkJob() {
        return jobInitiator.scheduleChunkBatchProcessing();
    }
}
