package Parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.IOException;

public class StopListSet {
    public StopList[] StopLists;
    public int size;

    public StopListSet() throws IOException, org.json.simple.parser.ParseException {
        JSONObject jsonObject = new GetApiData("STATION").getData();
        JSONArray jsonArray=(JSONArray) jsonObject.get("STATION_LIST");
        size=Integer.parseInt(jsonObject.get("ROW_COUNT").toString());
        StopLists = new StopList[size];
        for(int i=0;i<size;i++)
            StopLists[i] = new StopList((JSONObject) jsonArray.get(i));

        }
        public void StopListPrint(){
        for(int i =0;i<size;i++)
        System.out.println("정류장번호: " +StopLists[i].getCurStopId() +" 현재 정류장 이름: "+StopLists[i].getCurStopName()+" 다음 정류장 이름: " +StopLists[i].getNextStopName());
        }

//        public String getStop(int id){
//
//        }

}
     /*for(int i =0;i<3;i++){
            JSONObject jsonObjectSub = (JSONObject)  jsonArray.get(i);
            String busNO = jsonObjectSub.get("BUSSTOP_NAME").toString();
            System.out.println(busNO);*/



//"RESULT_CODE":"SUCCSS"},"STATION_LIST":[{"STATION_NUM":1,
// "BUSSTOP_NAME":"동원촌",
// "ARS_ID":"5396",
// "NEXT_BUSSTOP":"비아동주민센터(앞)",


