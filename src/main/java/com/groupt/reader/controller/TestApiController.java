package com.groupt.reader.controller;

import com.groupt.reader.dto.TestDto;
import com.groupt.reader.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    @Autowired
    private TestService testService;

    @RequestMapping("/count")
    public TestDto visit() {
        TestDto ret;
        try {
            ret = testService.count();
            ret.setStatus("ok");
        } catch (Exception e) {
            e.printStackTrace();
            ret = new TestDto();
            ret.setStatus("error");
        }
        return ret;
    }

    @RequestMapping("/init")
    public String initialize_visit() {
        testService.init();
        return "Success";
    }
}
