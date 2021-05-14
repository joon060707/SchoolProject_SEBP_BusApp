package Parse;

import org.json.simple.JSONObject;

import java.util.ArrayList;
import org.json.simple.JSONObject;

public class BusLine {
    private String StopName;
    private int StopId;
    private int flag;

    public BusLine(JSONObject jsonObject){
        StopName = jsonObject.get("BUSSTOP_NAME").toString();
        StopId = Integer.parseInt(jsonObject.get("BUSSTOP_ID").toString());
        flag = Integer.parseInt(jsonObject.get("RETURN_FLAG").toString());
    }

    public int getFlag() { return flag; }
    public String getStopName() { return StopName; }
    public int getStopId() { return StopId; }

    //{"RESULT":{"RESULT_MSG":"정상적으로 처리되었습니다.","RESULT_CODE":"SUCCESS"},
    // "BUSSTOP_LIST":[{"BUSSTOP_NAME":"장등동",
    // "ARS_ID":"4311"
    // "RETURN_FLAG":2,
    // "BUSSTOP_NUM":1,
    // "BUSSTOP_ID":1165,
}