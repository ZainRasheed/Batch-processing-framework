package com.example.BatchProcessingInHouseFramework.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class BatchProcessingCore<I, O> {

    //TODO add chunkSize var somewhere in BatchProcessingCore or jobInitiator.
    private int chunkSize = 35;

    private List<I> readData;

    private O processedData;

    private List<O> writeData;

    private List<I> chunk;

    private int jobRepeatCount;

    private boolean repeatStep;

    @Autowired
    private Reader<I> reader;

    @Autowired
    private Processor<I,O> processor;

    @Autowired
    private Writer<O> writer;

    public String run() throws Exception {
        initVariablesForJob();
        System.out.println("---------------------- Job Start >>> ----------------------");
        while (repeatStep) {
            initVariablesForStep();
            System.out.println("---------------------- Step Start >>> ----------------------");
            System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
            if (runReader()) {
                System.out.println("---------------------- ReaderEmpty");
                System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
                continue;
            }
            System.out.println("---------------------- ReaderDone");
            System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
            while (extractChunkOfData(readData)) {
                System.out.println("---------------------- Chunk Start >>> ----------------------");
                System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
                runProcessor();
                System.out.println("---------------------- ProcessorDone");
                System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
                runWriter();
                System.out.println("---------------------- WriterDone");
                System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
                System.out.println("---------------------- Chunk over ----------------------");
                System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
            }
            System.out.println("---------------------- Step over ----------------------");
            System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
        }
        deleteVariablesForJob();
        System.out.println("---------------------- Job over ----------------------");
        System.out.println("ReadDataCount: " +readData.size() + " WriteDataCount: " + writeData.size() + " chunkCount: " + chunk.size());
        return "Success";
    }

    private void deleteVariablesForJob() {
        jobRepeatCount = 0;
        repeatStep = false;
    }

    private void initVariablesForJob() {
        jobRepeatCount = 0;
        repeatStep = true;
    }

    private void initVariablesForStep() {
        readData = new ArrayList<>();
        processedData = null;
        writeData = new ArrayList<>();
        chunk = new ArrayList<>();
        jobRepeatCount = jobRepeatCount + 1;
    }

    private boolean runReader() throws Exception {
        readData = reader.read(jobRepeatCount);
        if (isNull(readData) || isEmpty(readData)) {
            repeatStep = false;
        }
        return !repeatStep;
    }

    private void runProcessor() throws Exception {
         List<CompletableFuture<O>> data = new ArrayList<>();
            for (I item : chunk) {
                System.out.println("looped");
                data.add(processor.process(item));
            /*processedData = processor.process(i);
            writeData.add(processedData);*/
            }
        /*Waits to collect all the threads data before writing everything*/
        CompletableFuture.allOf(data.toArray(new CompletableFuture[0])).join();
        chunk.clear();
        //TODO try to remove 2 different lists for processed Data
        writeData = data.stream().map(i -> {
            try {
                return i.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
    }

    private boolean extractChunkOfData(List<I> readData) {
        if (readData.size() == 0) {
            chunk = readData;
            return false;
        } else if (readData.size() < chunkSize) {
            chunk = new ArrayList<>(readData);
            readData.clear();
            return true;
        } else {
            chunk = new ArrayList<>(readData.subList(0, chunkSize));
            readData.subList(0, chunkSize).clear();
            return true;
        }
    }

    private void runWriter() throws Exception {
        writer.write(writeData);
        writeData.clear();
    }
}
