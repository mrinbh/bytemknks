package com.ms.wm.cloudathon.web.controller;

import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.logback.ApplicationInsightsAppender;
import com.microsoft.applicationinsights.telemetry.Telemetry;
import com.ms.wm.cloudathon.model.IncomingModel;
import com.ms.wm.cloudathon.repository.FinderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/byteTheCloud")
public class RESTServiceController {

    private Logger logger = LoggerFactory.getLogger(RESTServiceController.class);

    @Autowired
    FinderImpl finder;

    @Autowired
    TelemetryClient telemetryClient;

    @RequestMapping(method = RequestMethod.POST,
            path ="/accept" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String acceptCall(@RequestBody IncomingModel model) {
        try {
            logger.info("Incoming Model is : {}", model);
            logger.trace("Tracing Incoming Model is : {}", model);
            logger.error("Tracing Incoming Model is : {}", model);
            finder.test();
            return model.getSequence() + model.getTimestamp();
        }catch (Exception ex){
            telemetryClient.trackEvent("Exception  while processing model" + model);
            telemetryClient.trackTrace(ex.getMessage());
            return "Error while processing request" + model;
        }
    }

    @GetMapping("/ping")
    public String getPingFromByteTheCloudService() {
        logger.info("Hello Trace");
        //TEST MDC
        MDC.put("TestKey", "value");
        telemetryClient.trackEvent("ping_event");
        return "Hello There, calling from Byte the Cloud Ping";
    }
}
