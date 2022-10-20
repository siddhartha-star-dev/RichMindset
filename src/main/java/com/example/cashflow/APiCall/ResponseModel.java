package com.example.cashflow.APiCall;

public class ResponseModel  {


    String description;

    public ResponseModel() {
    }

    public ResponseModel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

