package com.example.workouttrackertoothbrook.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class minutesCompetition extends Competition {
    public minutesCompetition(String cat) {
        super(cat);
    }


    @Override
    public List<HashMap> getRanking(List<HashMap> contestants) {
        List<HashMap> list = new ArrayList<>(contestants);
        Collections.sort(list,new sortByMinutes());
        Collections.reverse(list);
        return list;

    }

    class sortByMinutes implements Comparator<HashMap>{

        @Override
        public int compare(HashMap o1, HashMap o2) {

            return Integer.parseInt(o1.get("workoutMinutes").toString()) - Integer.parseInt(o2.get("workoutMinutes").toString());
        }
    }


}
