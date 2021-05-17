package com.example.workouttrackertoothbrook.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class kilometersCompetition extends Competition {
    public kilometersCompetition(String cat) {
        super(cat);
    }

    @Override
    public List<HashMap> getRanking(List<HashMap> contestants) {
        List<HashMap> list = new ArrayList<>(contestants);
        Collections.sort(list,new kilometersCompetition.sortByKilometers());
        Collections.reverse(list);
        return list;
    }

    class sortByKilometers implements Comparator<HashMap> {

        @Override
        public int compare(HashMap o1, HashMap o2) {
            return (int) ((int)o1.get("kilometeres") - (int) o2.get("kilometeres"));
        }
    }
}
