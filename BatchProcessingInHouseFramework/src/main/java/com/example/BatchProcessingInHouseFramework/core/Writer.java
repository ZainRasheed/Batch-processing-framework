package com.example.BatchProcessingInHouseFramework.core;

import java.util.List;

public interface Writer<T> {

    void write(List<? extends T> list) throws Exception;
    //void write() throws Exception;
}
