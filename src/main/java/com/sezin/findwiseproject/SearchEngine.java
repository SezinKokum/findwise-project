package com.sezin.findwiseproject;

import com.sezin.findwiseproject.model.Document;

import java.util.List;

public interface SearchEngine {
    Document indexDocument(String id, String content);
    List<IndexEntry> search(String term);
}
