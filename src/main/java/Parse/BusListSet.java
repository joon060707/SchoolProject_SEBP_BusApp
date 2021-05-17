package Parse;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class BusListSet {
    public String lineName;
    
    public BusList[] buslist;
    public int size;

    public BusListSet(int id) throws IOException, ParseException {
    	JSONObject jsonObject = new GetApiData("LINE").getData();
    	JSONArray jsonArray=(JSONArray) jsonObject.get("LINE_LIST");
        size = Integer.parseInt(jsonObject.get("ROW_COUNT").toString());
        buslist = new BusList[size];
        
        for(int i=0; i<size; i++)
        	buslist[i]=new BusList((JSONObject) jsonArray.get(i));
    }
    public void BusListPrint() {
        for(int i=0; i<size; i++)
            System.out.println("노선번호: "+buslist[i].getLineId()+"노선이름: "+buslist[i].getLineName()+"종점(상행): "+buslist[i].getDirUp()+"종점(하행): "+buslist[i].getDirDown()+"노선종류: "+buslist[i].getlinkKind());
    }
}   

	
