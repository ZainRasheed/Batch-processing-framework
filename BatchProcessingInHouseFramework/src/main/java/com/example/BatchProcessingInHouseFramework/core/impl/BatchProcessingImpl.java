package com.example.BatchProcessingInHouseFramework.core.impl;

import com.example.BatchProcessingInHouseFramework.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*@Component
public class BatchProcessingImpl implements BatchProcessing {
    //TODO(mohammed s2) : define step and job flow here
    @Autowired
    private Reader reader;

    @Autowired
    private Processor processor;

    @Autowired
    private Writer writer;

    @Autowired
    private BatchContext context;


    @Override
    public String run() {
        try {
            return setupJob();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Fail";
    }

    private String setupJob() throws Exception{
        context.setJobRepeatCount(1);
        while (reader.read()) {
            processor.process();
            writer.write();
            context.setJobRepeatCount(context.getJobRepeatCount()+1);
        }
        return "Success";
    }

}*/

@Component
public class BatchProcessingImpl implements BatchProcessing {

    @Autowired
    private BatchProcessingCore core;

    @Override
    public String dummyJobStart() {
        try {
            return core.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed";
    }
}