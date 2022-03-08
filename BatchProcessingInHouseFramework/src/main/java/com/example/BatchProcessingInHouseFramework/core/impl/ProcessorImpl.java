package com.example.BatchProcessingInHouseFramework.core.impl;

import com.example.BatchProcessingInHouseFramework.core.Processor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/*@Service
public class ProcessorImpl implements Processor {

    @Autowired
    BatchContext context;

    @Override
    public Object process() {
        context.setProcessed(Collections.singletonList(context.getInput()));
        return null;
    }
}*/

@Service
public class ProcessorImpl implements Processor<Object,String> {

    @Override
    public CompletableFuture<String> process(Object item) throws Exception {
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + "ENDED");
        return CompletableFuture.completedFuture(item.toString());
    }
}
