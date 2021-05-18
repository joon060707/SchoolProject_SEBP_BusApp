package Parse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;

public class BusLocationMap {

    private BusLineMap busLineMap;
//    private StopListSet stopList;
    private GetApiData getApiData;
    private int id;
    private String lineName;
    private String curStopId;
    private int busCount;
    private String busNumber;
//    private String curStopName;
    private String isLowBus;

    private HashMap<String, BusLocation> busLocationMap = new HashMap<>();
    BusLocation busLocation;



    public BusLocationMap(int id) throws IOException, ParseException {
        this.id = id;
        this.busLineMap = new BusLineMap(id);
//        this.stopList = new StopListSet();
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
             busNumber = (bus.get("CARNO").toString());
//            curStopName = (getCurStopName(bus.get("CURR_STOP_ID").toString()));
             isLowBus =(isLowBus(bus.get("LOW_BUS").toString()));

             busLocation = new BusLocation(busNumber, isLowBus);
             busLocationMap.put(curStopId, busLocation);
        }

    }
    private String isLowBus(String flag){
        if(flag.equals("0") || flag == null)
            return "일반";
        else
            return "저상";
    }
//    private String getCurStopName(String curStopId){
//        return stopList.getStop(curStopId);
//    }
    public BusLocation getBusLocation(String stopId){
        return busLocationMap.get(stopId);
    }

    public int getBusCount(){
        return busCount;
    }
    public String getLineName(){
        return lineName;
    }






}
