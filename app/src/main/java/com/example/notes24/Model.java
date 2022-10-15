package com.example.notes24;

public class Model {
    public String name,pdfUrl;

    public Model(String name, String pdfUrl) {
        this.name = name;
        this.pdfUrl = pdfUrl;
    }
    public Model(){

    }

    public String getName() {
        return name;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

}
