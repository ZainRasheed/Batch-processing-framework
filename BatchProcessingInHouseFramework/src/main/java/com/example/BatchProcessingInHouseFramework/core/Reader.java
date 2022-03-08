package com.example.BatchProcessingInHouseFramework.core;

import org.springframework.lang.Nullable;

import java.util.List;

public interface Reader<T> {

    @Nullable
    List<T> read(int page) throws Exception;
    //boolean read() throws Exception;
}
