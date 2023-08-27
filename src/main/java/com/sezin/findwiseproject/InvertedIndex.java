package com.sezin.findwiseproject;

import com.sezin.findwiseproject.model.Document;

import java.util.*;

public class InvertedIndex {

    private Map<String, Set<Document>> index;

    public InvertedIndex() {
        index = new HashMap<>();
    }

    public void addDocument(Document document, List<String> wordList ) {
        for (String word : wordList) {
            index.computeIfAbsent(word, k -> new HashSet<>()).add(document);
        }
    }

    public Set<Document> search(String query) {
        return index.getOrDefault(query, Collections.emptySet());
    }
}
