import GUI.BusGUI;
import Parse.*;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException, ParseException {

//        new Arrive(907).print();

//        StopListSet.StopListPrint(StopListSet.getStopLists());

        new BusListSet(0).BusListPrint();

//        new BusLineMap(9).BusLinePrint();

        BusLocation b=new BusLocation(9);
        System.out.println(b.getBusNumberList().toString());
        System.out.println(b.getCurStopNameList().toString());
        System.out.println(b.isLowBusList().toString());

        BusGUI.mainMenu().start();
//        BusGUI.alertPopup("알림창", "알림창 테스트입니다. 알림창 테스트입니다.", Color.red, 20).start();




    }
}
