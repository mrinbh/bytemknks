package com.ms.wm.cloudathon.web.controller;

import com.ms.wm.cloudathon.model.IncomingModel;
import com.ms.wm.cloudathon.repository.FinderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/byteTheCloud")
public class RESTServiceController {

    @Autowired
    FinderImpl finder;

    @RequestMapping(method = RequestMethod.POST,
            path ="/accept" ,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String acceptCall(@RequestBody IncomingModel model) {
        finder.test();
        return model.getSequence() + model.getTimestamp();
    }
}
