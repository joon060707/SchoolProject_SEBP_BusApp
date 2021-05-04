package Parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Arrive {

    public ArriveLine[] arriveLines;
    public int size;
    public String stopName;


    public Arrive(int id) throws IOException, ParseException {
        JSONObject jsonObject = new GetApiData("ARRIVE", id).getData();
        size=Integer.parseInt(jsonObject.get("ROW_COUNT").toString());
        arriveLines = new ArriveLine[size];
        JSONArray jsonArray=(JSONArray) jsonObject.get("BUSSTOP_LIST");
        for(int i=0; i<size; i++)
           arriveLines[i]=new ArriveLine((JSONObject) jsonArray.get(i));
    }

    public void print(){
        System.out.println(stopName+" 도착 정보");
        System.out.println("노선\t현재 위치\t\t남은 시간\t남은 정거장");
        for (ArriveLine l : arriveLines)
            System.out.println(l.lineName+"\t"+l.curStopName+"\t"+l.remainMin+"\t\t"+l.remainStop);
    }

}

