package com.example.workouttrackertoothbrook.Data;

import java.util.List;

public abstract class Competition {
    String category;
    public Competition(String cat) {
        category=cat;
    }

    public abstract List<User> getRanking(List<User> contestants);

    public String getCategory(){
        return category;
    }

}
