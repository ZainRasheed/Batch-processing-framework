package com.example.BatchProcessingInHouseFramework.core.impl;

import com.example.BatchProcessingInHouseFramework.core.BatchProcessing;
import com.example.BatchProcessingInHouseFramework.core.JobInitiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JobInitiatorImpl implements JobInitiator {

    //init variables, This class should define how the Batch JOB should run

    @Autowired
    private BatchProcessing batchProcessing;

    @Override
    public String scheduleChunkBatchProcessing() {
        return batchProcessing.dummyJobStart();
    }

}
