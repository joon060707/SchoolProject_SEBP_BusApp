package Parse;

import org.json.simple.JSONObject;

public class StopList {
    int curStopId;
    String curStopName;
    String nextStopName;

    public StopList(JSONObject jsonObject){
        this.curStopId = Integer.parseInt(jsonObject.get("BUSSTOP_ID").toString());
//        this.nextStopName = jsonObject.get("NEXT_BUSSTOP_NAME").toString();
        this.curStopName =jsonObject.get("BUSSTOP_NAME").toString();
    }
    //"RESULT_CODE":"SUCCSS"},"STATION_LIST":[{"STATION_NUM":1,
    // "BUSSTOP_NAME":"동원촌",
    // "ARS_ID":"5396",
    // "NEXT_BUSSTOP":"비아동주민센터(앞)",
}

