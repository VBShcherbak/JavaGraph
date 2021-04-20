package graph;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        ArrayList<Point> points = new ArrayList<Point>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("input.json"));
        JSONArray array = (JSONArray) obj;
        array.forEach(item -> {
            JSONObject obj1 = (JSONObject) item;
            Long name = (long)obj1.get("name");
            Long total = (long)obj1.get("total");
            JSONArray jsonArray = (JSONArray)obj1.get("next");
            int[] arr = new int[total.intValue()];
            for (int i = 0; i < total; i++) {
                Long temp = (long)jsonArray.get(i);
                arr[i] = temp.intValue();
            };
            points.add(new Point(name.intValue(), total.intValue(), arr));
        });

        Graph graph = new Graph(points);
        graph.addWeight(3, 17, 8, 0, 10, 0, 0, 30);
        graph.addWeight(3, 17, 10, 0, 11, 0, 0, 15);
        graph.addWeight(17, 4, 8, 10, 9, 0, 0, 30);
        graph.addWeight(7, 8, 8, 0, 10, 0, 0, 30);
        graph.addWeight(11, 18, 9, 0, 10, 0, 0, 45);

        graph.bestWeight(9, 15, 3, 16);
        graph.bestWeight(9, 15, 1, 9);

    }
}