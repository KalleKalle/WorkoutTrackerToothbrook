package com.example.workouttrackertoothbrook.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class kilometersCompetition extends Competition {
    public kilometersCompetition(String cat) {
        super(cat);
    }

    @Override
    public List<User> getRanking(List<User> contestants) {
        List<User> list = new ArrayList<>(contestants);
        quickSortUser(list,0,contestants.size()-1);
        return list;
    }

    private void quickSortUser(List<User> contestants,int start, int end){
        if(start<end){

            int left= start+1;
            int right= end;
            double pivot= contestants.get(start).getKilometers();
            while (left<=right){
                while (left <= end && pivot >= contestants.get(left).getKilometers()){
                    left++;
                }
                while (right <= end && pivot >= contestants.get(right).getKilometers()){
                    right--;
                }
                if(left<right){
                    Collections.swap(contestants,left,right);
                }
            }
            Collections.swap(contestants,start,left-1);
            quickSortUser(contestants,start,right-1);
            quickSortUser(contestants,right+1,end);
        }
    }
}
