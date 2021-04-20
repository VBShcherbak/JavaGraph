package graph;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ps on 01/04/2021.
 */
public class Graph {
    public Graph(ArrayList<Point> points) {
        this.points = points;
        this.counter = 0;
    }
    public void addPoint(Point point) {
        points.add(point);
    }
    public void addWeight(int from, int to, int beginHour, int beginMin, int endHour, int endMin, int valueHour, int valueMin) {
        int beginTime = beginHour * 60 + beginMin;
        int endTime = endHour * 60 + endMin;
        int value = valueHour * 60 + valueMin;
        for (Point p: points) {
            if (p.getName() == from) {
                p.addWeight(to, beginTime, endTime, value);
                break;
            }
        }
    }
    public int getTime(int timeHour, int timeMin, int total, int ... nums) {
        int time = timeHour * 60 + timeMin;
        int rezult = 0;
        int way[] = new int[total]; //total
        int next = total;
        next++;
        for (int i = 0; i < total; i++, next++) {
            way[i] = next;
        }
        total -= 1;
        for (int i = 0; i < total; i++) {
            for (Point p : points) {
                if (p.getName() == way[i]) {
                    int Weight = p.getWeight(way[i + 1], time);
                    rezult += Weight;
                    time += Weight;
                }
            }
        }
        return rezult;
    }
    public void bestWeight(int timeHour, int timeMin, int start, int stop) {
        int time = timeHour * 60 + timeMin;
        for (Point p : points) {
            p.setPrev(0);
            p.setBestWeight(0);
        }

        Point point = getPoint(start);
        Map<Integer, ArrayList<Weight>> node = point.getNode();
        for (Map.Entry<Integer, ArrayList<Weight>> item : node.entrySet()) {
            int  weight = point.getWeight(item.getKey(), time);
            bestWay(point.getName(), item.getKey(), point.getBestWeight() + weight, time + weight, start, stop);
        }

        System.out.println(stop + " ; ");
        Point p = getPoint(stop);
        int line = p.getPrev();
        System.out.print(line + ":");
        System.out.println(p.getBestWeight() + " ; ");
        while (line != start) {
            Point prev = getPoint(line);
            line = prev.getPrev();
            System.out.print(line + ":");
            System.out.println(prev.getBestWeight() + " ; ");
        }
        System.out.println();
    }

    private ArrayList<Point> points;
    private int counter;
    private void bestWay(int prev, int target, int weight, int time, int start, int stop) {
        if (target == start || counter > 20) return;
        time %= 1440;
        Point point = getPoint(target);
        if (point.getBestWeight() == 0 || point.getBestWeight() > weight) {
            point.setBestWeight(weight);
            point.setPrev(prev);
            if (target == stop) return;
            Map<Integer, ArrayList<Weight>> node = point.getNode();
            for (Map.Entry<Integer, ArrayList<Weight>> item : node.entrySet()) {
                int  weightNext = point.getWeight(item.getKey(), time);
                bestWay(point.getName(), item.getKey(), point.getBestWeight() + weightNext, time + weightNext, start, stop);
            }
        }
    }
    private Point getPoint(int name) {
        for (Point p : points) {
            if (p.getName() == name) {
                return p;
            }
        }
        return null;
    }
}
