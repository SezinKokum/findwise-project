package com.sezin.findwiseproject.model;

import java.util.List;


public class Document {

    private String id;
    private String text;
    private List<String> wordList;

    public Document(String id, String text, List<String> wordList) {
        this.id = id;
        this.text = text;
        this.wordList = wordList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getWordList() {
        return wordList;
    }

    public void setWordList(List<String> wordList) {
        this.wordList = wordList;
    }
}
