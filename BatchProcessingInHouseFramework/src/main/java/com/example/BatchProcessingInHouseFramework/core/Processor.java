package com.example.BatchProcessingInHouseFramework.core;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface Processor<I,O> {

    @Nullable
    @Async
    CompletableFuture<O> process(I item) throws Exception;
    //O process() throws Exception;
}
