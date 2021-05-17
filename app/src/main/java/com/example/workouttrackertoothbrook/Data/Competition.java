package com.example.workouttrackertoothbrook.Data;

import java.util.HashMap;
import java.util.List;

public abstract class Competition {
    String category;
    public Competition(String cat) {
        category=cat;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public abstract List<HashMap> getRanking(List<HashMap> contestants);

    public String getCategory(){
        return category;
    }

}
