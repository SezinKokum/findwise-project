package com.sezin.findwiseproject.controller;

import com.sezin.findwiseproject.IndexEntry;
import com.sezin.findwiseproject.model.Document;
import com.sezin.findwiseproject.service.SearchEngineService;
import org.springframework.stereotype.Controller;

import java.io.*;
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

        /*Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a filepath:");
        String filePath = scanner.nextLine();

        //Creates a documentList set with documents that are given by user
        Set<Document> documentList = new HashSet<>();
        try {
            File[] allfiles = new File(filePath).listFiles();
            BufferedReader in = null;
            for (File f : allfiles) {
                if (f.getName().endsWith(".txt")) {
                    in = new BufferedReader(new FileReader(f));
                    StringBuilder sb = new StringBuilder();
                    String s = null;
                    while ((s = in.readLine()) != null) {
                        sb.append(s);
                    }
                    documentList.add(searchEngineService.indexDocument(f.getName(), String.valueOf(sb)));
                }
                else {
                    continue;
                }
            }
        }catch(FileNotFoundException exception) {
            System.out.println("File not found "+exception);
        }
        catch(NullPointerException exception) {
            System.out.println("Directory name is empty "+exception);
        }
        catch(IOException exception) {
            System.out.println("IOException "+exception);
        }*/

        String document1 = "the brown fox jumped over the brown dog";
        String document2 = "the lazy brown dog sat in the corner";
        String document3 = "the red fox bit the lazy dog";

        //Creates a documentList set with documents that are given by user
        Set<Document> documentList = new HashSet<>();

        //Calls indexDocument method in order to split and parse documents into a wordList list and stores this list into a document objects
        documentList.add(searchEngineService.indexDocument("document 1", document1));
        documentList.add(searchEngineService.indexDocument("document 2", document2));
        documentList.add(searchEngineService.indexDocument("document 3", document3));

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