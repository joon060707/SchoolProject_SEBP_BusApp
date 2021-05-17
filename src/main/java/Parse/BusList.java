package Parse;

import org.json.simple.JSONObject;

public class BusList {
	public GetApiData getApiData;
	public int lineId;
    public String lineName;
    public String dirDown;
    public String dirUp;
    public String lineKind;
    
    
    public BusList(JSONObject jsonObject) {
        lineId = Integer.parseInt(jsonObject.get("LINE_ID").toString());
        lineName = jsonObject.get("LINE_NAME").toString();
        dirDown = jsonObject.get("DIR_DOWN_NAME").toString();
        dirUp = jsonObject.get("DIR_UP_NAME").toString();
        lineKind = lineKindData(jsonObject.get("LINE_KIND").toString());
    }


    public String lineKindData(String flag) {
        if(flag.equals("1"))
            return "급행간선";
        else if(flag.equals("2"))
            return "간선";
        else if(flag.equals("3"))
            return "지선";
        else if(flag.equals("4"))
            return "마을버스";
        else if(flag.equals("5"))
            return "공항버스";
        else
            return "광역버스";
    }

    public int getLineId() { return lineId; }
    public String getLineName() { return lineName; }
    public String getDirDown() { return dirDown; }
    public String getDirUp() { return dirUp; }
    public String getlinkKind() { return lineKind; }
    
}
