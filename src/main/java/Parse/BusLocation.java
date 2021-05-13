package Parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class BusLocation {

    private BusLineMap busLineMap;
    private StopListSet stopList;
    private GetApiData getApiData;
    private int id;
    private String lineName;
    private String curStopId;
    private int busCount;
    private ArrayList<String> busNumberList = new ArrayList<>();
    private ArrayList<String> curStopNameList = new ArrayList<>();
    private ArrayList<String> isLowBusList = new ArrayList<>();

    public BusLocation(int id) throws IOException, ParseException {
        this.id = id;
        this.busLineMap = new BusLineMap(id);
        this.stopList = new StopListSet();
        this.getApiData = new GetApiData("BUS_LOCATION", id);
        this.lineName = busLineMap.getLineName();
        setData();
    }


    private void setData() throws IOException, ParseException {
        JSONObject data= getApiData.getData();
        JSONObject bus;
        busCount = Integer.parseInt(data.get("ROW_COUNT").toString());

        JSONArray busArray = (JSONArray) data.get("BUSLOCATION_LIST");

        for(int i=0; i<busCount; i++){
             bus = (JSONObject)busArray.get(i);
             curStopId = bus.get("CURR_STOP_ID").toString();
             busNumberList.add(bus.get("CARNO").toString());
//             curStopNameList.add(getCurStopName(bus.get("CURR_STOP_ID").toString()));
             isLowBusList.add(isLowBus(bus.get("LOW_BUS").toString()));
        }

    }
    private String isLowBus(String flag){
        if(flag.equals("0"))
            return "일반";
        else
            return "저상";
    }
//    private String getCurStopName(String curStopId){
//        return stopList.getStop(curStopId);
//    }

    public int getBusCount(){
        return busCount;
    }
    public String getLineName(){
        return lineName;
    }
    public ArrayList<String> getBusNumberList(){
        return busNumberList;
    }
    public ArrayList<String> getCurStopNameList(){
        return curStopNameList;
    }
    public ArrayList<String> isLowBusList(){
        return isLowBusList;
    }






}
