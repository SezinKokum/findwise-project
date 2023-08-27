package com.sezin.findwiseproject.service;

import com.sezin.findwiseproject.IndexEntry;
import com.sezin.findwiseproject.InvertedIndex;
import com.sezin.findwiseproject.SearchEngine;

import java.util.*;

import com.sezin.findwiseproject.model.Document;
import org.springframework.stereotype.Service;

@Service
public class SearchEngineService implements SearchEngine {
    private InvertedIndex invertedIndex;
    private int numberOfDocuments;

    //split a document's content in a wordList as words and saves it in document object
    @Override
    public Document indexDocument(String id, String content) {
        List<String> wordList = splitContent(content);
        Document document = new Document(id, wordList);
        return document;
    }

    //makes the content's letters lowercase and removes whitespace,newline characters
    private List<String> splitContent(String content){
        List<String> wordList = new ArrayList<>(Arrays.asList(content.toLowerCase(Locale.ROOT).split("[\\s\\n]+")));
        return wordList;
    }

    //Creates inverted index from documentList
    public void createInvertedIndex(Set<Document> documentList){
        this.numberOfDocuments = documentList.size();

        InvertedIndex invertedIndex = new InvertedIndex();
        for (Document document : documentList)
            invertedIndex.addDocument(document, document.getWordList());

        this.invertedIndex = invertedIndex;
    }

    //Calculates and returns tf score for searched term and the document
    public double findTf(String term, Document document){
        List<String> wordList = document.getWordList();
        int numberOfTermInDocument = Collections.frequency(wordList, term);

        return (double)numberOfTermInDocument/wordList.size();
    }

    //Searches the term in inverted index and returns list of index entries
    @Override
    public List<IndexEntry> search(String term) {
        Set<Document> relatedDocuments = this.invertedIndex.search(term);

        List<IndexEntry> indexEntryList = new ArrayList<>();

        double idfScore = findIdf(relatedDocuments.size());

        for(Document relatedDocument : relatedDocuments) {
            //System.out.println("relatedDocument id " + relatedDocument.getId());
            double tfScore = findTf(term, relatedDocument);
            IndexEntry indexEntry = new IndexEntry(relatedDocument.getId(), tfScore*idfScore);
            indexEntryList.add(indexEntry);
        }

        return indexEntryList;
    }

    //Calculates and returns idf score with the number of documents that contains the term
    public double findIdf(int numberOfRelatedDocuments) {
        return Math.log((double) this.numberOfDocuments / numberOfRelatedDocuments);
    }
}
