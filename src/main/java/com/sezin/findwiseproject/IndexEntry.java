package com.sezin.findwiseproject;

public class IndexEntry {
    private String id;
    private double tfIdfScore;

    public IndexEntry(String id, double tfIdfScore) {
        this.id = id;
        this.tfIdfScore = tfIdfScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTfIdfScore() {
        return tfIdfScore;
    }

    public void setTfIdfScore(double tfIdfScore) {
        this.tfIdfScore = tfIdfScore;
    }
}
