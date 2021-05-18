import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class StopListSet {
    public static HashMap<Integer, StopList> StopLists;
    public static int size;

    public static HashMap<Integer, StopList> getStopLists() throws IOException, ParseException {
        if (StopLists != null) return StopLists;

        JSONObject jsonObject = new GetApiData("STATION").getData();
        JSONArray jsonArray = (JSONArray) jsonObject.get("STATION_LIST");
        size = Integer.parseInt(jsonObject.get("ROW_COUNT").toString());
        StopLists =new HashMap<>();
        for(int i=0;i<size;i++){
            StopList a = new StopList((JSONObject) jsonArray.get(i));
            StopLists.put(a.curStopId, a);
        }

        return StopLists;
    }

    public static void StopListPrint(HashMap<Integer, StopList> StopLists) {
        if (StopLists != null)
            for (int i = 0; i < 9999; i++)
                if (StopLists.get(i) != null)
                    System.out.println("정류장번호:" + StopLists.get(i).curStopId + "현제 정류장:" + StopLists.get(i).curStopName + "다음 정류장:" + StopLists.get(i).nextStopName);


    }
}

