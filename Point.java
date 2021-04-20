package graph;

/**
 * Created by ps on 29/03/2021.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Point {
    public Point(int name, int total, int[] num){
        this.name = name;
        node = new HashMap<Integer, ArrayList<Weight>>();
        for (int n: num) {
            node.put(n, new ArrayList<Weight>());
        }
        this.prev = 0;
        this.bestWeight = 0;
    }

    public void addWeight(int name, int begin, int end, int value) {
        for(Map.Entry<Integer, ArrayList<Weight>> item : node.entrySet()){
            if ( item.getKey() == name ) {
                this.node.get(name).add(new Weight(begin, end, value));
            }
        }
    }

    public int getWeight(int name, int time) {
        time %= 1440;  //minutes a day
        for (Map.Entry<Integer, ArrayList<Weight>> item : node.entrySet()) {
            if ( item.getKey() == name ) {
                for (Weight n: node.get(name)) {
                    if (n.begin <= time && time <= n.end) {
                        return n.value + defWeight;
                    }
                }
            }
        }
        return defWeight;
    }

    Map<Integer, ArrayList<Weight>> getNode() {
        return node;
    }
    int getName() {
        return name;
    }
    int getPrev() {
        return prev;
    }
    int getBestWeight() {
        return bestWeight;
    }
    void setPrev(int value) {
        prev = value;
    }
    void setBestWeight(int value) {
        bestWeight = value;
    }

    private int name;
    private int prev;       //for task 2
    private int bestWeight; //for task 2
    private int defWeight = 30;
    private HashMap<Integer, ArrayList<Weight>> node;
}
