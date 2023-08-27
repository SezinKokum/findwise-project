package com.sezin.findwiseproject.controller;

import com.sezin.findwiseproject.IndexEntry;
import com.sezin.findwiseproject.model.Document;
import com.sezin.findwiseproject.service.SearchEngineService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Scanner;

@Controller
public class SearchEngineController {

    private final SearchEngineService searchEngineService;

    public SearchEngineController(SearchEngineService searchEngineService) {
        this.searchEngineService = searchEngineService;
    }

    public Set<Document> createDocuments(){
        String documents1 = "the brown fox jumped over the brown dog";
        String documents2 = "the lazy brown dog sat in the corner";
        String documents3 = "the red fox bit the lazy dog";

        //Creates a documentList set with documents that are given by user
        Set<Document> documentList = new HashSet<>();

        //Calls indexDocument method in order to split and parse documents into a wordList list and stores this list into a document objects
        documentList.add(searchEngineService.indexDocument("documents1", documents1));
        documentList.add(searchEngineService.indexDocument("documents2", documents2));
        documentList.add(searchEngineService.indexDocument("documents3", documents3));

        return documentList;

    }

    public List<String> init(){
        //Creates a documentList and then creates inverted index from this documentList
        Set<Document> documentList = createDocuments();
        searchEngineService.createInvertedIndex(documentList);

        List<String> resultList = takeTermFromUser();
        return resultList;
    }

    private List<String> takeTermFromUser() {

        List<String> resultList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Enter a search query (or 'q' to exit): ");
            String query = scanner.nextLine();
            if (query.equalsIgnoreCase("q")) {
                System.out.println("SearchEngine ended!.");
                scanner.close();
                break;
            }
            List<IndexEntry> foundDocuments = searchEngineService.search(query);

            //Creates a result list that holds document names sorted by their tfidf scores
            resultList = searchEngineService.createResultList(foundDocuments);
            if(resultList.isEmpty()) {
                System.out.println("No results found.");
            }else{
                System.out.println(resultList.toString());
            }
        }
        return resultList;
    }

}