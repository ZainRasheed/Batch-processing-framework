package com.example.BatchProcessingInHouseFramework.core.impl;

import com.example.BatchProcessingInHouseFramework.core.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*@Service
public class WriterImpl implements Writer {

    @Autowired
    private BatchContext context;

    @Override
    public void write() {
        context.getProcessed().forEach(System.out::println);
    }
}*/

@Service
public class WriterImpl implements Writer<String> {

    @Override
    public void write(List<? extends String> list) throws Exception {
        list.forEach(System.out::println);
    }
}