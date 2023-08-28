package com.sezin.findwiseproject;

import com.sezin.findwiseproject.controller.SearchEngineController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FindwiseprojectApplication {

    private final SearchEngineController searchEngineController;

    public FindwiseprojectApplication(SearchEngineController searchEngineController) {
        this.searchEngineController = searchEngineController;
        searchEngineController.init();

    }
    public static void main(String[] args) {
        SpringApplication.run(FindwiseprojectApplication.class, args);
    }
}
