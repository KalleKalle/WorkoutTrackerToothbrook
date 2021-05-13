package com.example.workouttrackertoothbrook.Data;

import android.os.Build;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.RequiresApi;

public class minutesCompetition extends Competition {
    public minutesCompetition(String cat) {
        super(cat);
    }


    @Override
    public List<User> getRanking(List<User> contestants) {
        List<User> list = new ArrayList<>(contestants);
        Collections.sort(list,new sortByMinutes());
        Collections.reverse(list);
        return list;

    }

    class sortByMinutes implements Comparator<User>{

        @Override
        public int compare(User o1, User o2) {
            return o1.getWorkoutMinutes() - o2.getWorkoutMinutes();
        }
    }


}
