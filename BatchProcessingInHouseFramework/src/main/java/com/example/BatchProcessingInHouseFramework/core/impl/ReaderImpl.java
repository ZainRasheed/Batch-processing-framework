package com.example.BatchProcessingInHouseFramework.core.impl;

import com.example.BatchProcessingInHouseFramework.core.Reader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.BatchProcessingInHouseFramework.util.TransactionPool.getTransactions;
import static java.util.Objects.isNull;
import static org.springframework.util.ObjectUtils.isEmpty;

/*@Service
public class ReaderImpl implements Reader {

    @Autowired
    BatchContext context;

    @Override
    public boolean read() {
        context.setInput(getTransactions(context.getJobRepeatCount()));
        if (isNull(context.getInput()) || isEmpty(context.getInput())) {
            return false;
        }
        return true;
    }
}*/

@Service
public class ReaderImpl implements Reader<Object>{

    @Override
    public List<Object> read(int page) throws Exception {
        return getTransactions(page);
    }
}
