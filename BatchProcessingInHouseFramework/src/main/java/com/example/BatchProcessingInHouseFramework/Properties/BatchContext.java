package com.example.BatchProcessingInHouseFramework.Properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Component
public class BatchContext {
//TODO make this a util class and make everything static. add map for jobcontext

    private HashMap batchContext = new HashMap();
}
