package GUI;

import Dto.FavoriteBusRequestDto;
import Dto.FavoriteStopRequestDto;
import Parse.*;
import domain.busline.FavoriteBus;
import domain.busstop.FavoriteStop;
import service.FavoritesService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.List;

public class BusGUI extends JFrame {

    Container mainContainer=getContentPane();

    static HashMap<Integer, StopList> stopListSet;
    static HashMap<Integer, BusList> busListSet;

    static FavoritesService favoritesService=new FavoritesService();

    public BusGUI(int width, int height, String title, String ico){
        setTitle(title);
        setSize(width, height);
        setIconImage(Resources.getWindowIco(ico));
        mainContainer.setBackground(Color.white);
    }

    public BusGUI(int width, int height, String title, String ico, int startX, int startY){
        this(width, height, title, ico);
        setBounds(startX, startY, width, height);
    }


    // GUI.BusGUI(JFrame): 창
    // JPanel: 구역
    // window.start() : 창 띄우기
    // window.dispose() : 창 닫기
    // setBorder(new EmptyBorder(위, 왼, 아래, 오른)) : 여백 추가.... ㅠㅠ

    //////////////////////////////////////////////// Main Screen ///////////////////////////////////////////////////

    public static BusGUI mainMenu(){
        BusGUI window=new BusGUI(1280, 720, "Bus Information System", Resources.IMG_BUS_ORANGE, 320, 180);
        window.mainContainer.setLayout(new BorderLayout(0,0));

        window.add(mainCenter(), BorderLayout.CENTER);
        window.add(mainTop(), BorderLayout.NORTH);
        window.add(mainBottom(), BorderLayout.SOUTH);
        window.add(mainRight(), BorderLayout.EAST);

        window.setDefaultCloseOperation(EXIT_ON_CLOSE); // 창 닫으면 프로그램 종료

        return window;
    }

    // 메인 화면 시작
    private static JPanel mainCenter(){
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,4, 10, 10));
        panel.setBackground(Color.white);

        for (int i=0; i<5; i++) panel.add(new JLabel());

        JButton stopBt=new JButton(" 정류장", Resources.getBtImage(Resources.IMG_STOP1, 100));
        stopBt.setMargin(new Insets(0,0,0,0));
        stopBt.setBackground(Resources.COLOR_PURPLE);
        stopBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        stopBt.setForeground(Color.white);
        stopBt.addActionListener(e -> BusGUI.stopSelection().start());
        stopBt.setBorder(null);
        panel.add(stopBt);

        JButton lineBt=new JButton(" 노선", Resources.getBtImage(Resources.IMG_BUS_ORANGE, 120));
        lineBt.setMargin(new Insets(0,0,0,0));
        lineBt.setBackground(Resources.COLOR_SKY);
        lineBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        lineBt.setForeground(Color.white);
        lineBt.addActionListener(e -> BusGUI.lineSelection().start());
        lineBt.setBorder(null);
        panel.add(lineBt);

        for (int i=0; i<4; i++) panel.add(new JLabel());

        JPanel favWrap=new JPanel();
        favWrap.setLayout(new GridLayout(2,1));
        favWrap.setBackground(Color.white);
        favWrap.setBorder(new EmptyBorder(10,10,10,10));
        favWrap.add(new JLabel());

        JButton favBt=new JButton("즐겨찾기", Resources.getBtImage(Resources.IMG_FAV_YES, 60));
        favBt.setBackground(Color.green);
        favBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 25));
        favBt.setForeground(Color.black);
        favBt.addActionListener(e -> favorite().start());
        favBt.setBorder(null);
        favWrap.add(favBt);

        panel.add(favWrap);
        return panel;
    }

    private static JPanel mainTop(){
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout(0,0));
        panel.setBackground(Resources.COLOR_BLUE_DARK);

        JLabel title=new JLabel("Gwangju City Bus Information System");
        title.setBorder(new EmptyBorder(30, 0, 30, 40));
        title.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        title.setForeground(Color.white);
        panel.add(title, BorderLayout.EAST);

        JPanel titleImg=new JPanel();
        titleImg.setLayout(new FlowLayout());
        titleImg.setBackground(Resources.COLOR_BLUE_DARK);

        titleImg.add(emptyLabel(40, 10));
        JLabel busImg=new JLabel(Resources.getBtImage(Resources.IMG_BUS_ORANGE2, -1, 80));
        titleImg.add(busImg);
        titleImg.add(emptyLabel(30, 10));
        JLabel stopImg=new JLabel(Resources.getBtImage(Resources.IMG_STOP2, -1, 100));
        titleImg.add(stopImg);

        panel.add(titleImg, BorderLayout.WEST);

        return panel;
    }

    private static JPanel mainBottom(){
        JPanel panel=new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
        panel.setBackground(Resources.COLOR_PINK);

        JLabel signal= new JLabel("수신 상태: 확인 중");
        signal.setFont(Resources.nsq(Resources.FONT_NORMAL, 30));

        try{
            stopListSet=StopListSet.getStopLists();
            busListSet=BusListSet.getBusLists();
            if(stopListSet.size()!=0)
            signal.setText("수신 상태: 양호");
            else signal.setText("수신 상태: 버스 끊김");
        } catch (Exception e){
            signal.setText("수신 상태: 오류");
        }
        
        panel.add(signal);

        return panel;
    }

    private static JPanel mainRight(){

        JPanel panel=new JPanel();
        panel.setBackground(Resources.COLOR_GRAY);

        JPanel road1=new JPanel();
        road1.setBackground(Resources.COLOR_GRAY);
        road1.setLayout(new GridLayout(6,1));
        road1.add(emptyLabel(80,130));

        JButton yellowBt = new JButton("도움말");
        yellowBt.setMargin(new Insets(50, 10, 50, 10));
        yellowBt.setBackground(Resources.COLOR_YELLOW_BUS);
        yellowBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        yellowBt.setForeground(Color.white);
        yellowBt.addActionListener(e -> HelpGUI.help().start());
        road1.add(yellowBt);
        for(int i=0; i<4; i++)road1.add(emptyLabel(80,130));


        JPanel road2=new JPanel();
        road2.setBackground(Color.yellow);
        road2.setBorder(new EmptyBorder(450,5,450,5));


        JPanel road3=new JPanel();
        road3.setLayout(new GridLayout(6,1));
        road3.setBackground(Resources.COLOR_GRAY);

        JButton redBt = new JButton("통계");
        redBt.setMargin(new Insets(50, 20, 50, 20));
        redBt.setBackground(Resources.COLOR_RED_BUS);
        redBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        redBt.setForeground(Color.white);
        redBt.addActionListener(e -> StatGUI.stat().start());
        road3.add(redBt);

        road3.add(emptyLabel(80,130));
        JButton greenBt = new JButton("정보");
        greenBt.setBackground(Resources.COLOR_GREEN_BUS);
        greenBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        greenBt.setForeground(Color.white);
        greenBt.addActionListener(e -> AboutGUI.about().start());
        road3.add(greenBt);
        for(int i=0; i<3; i++)road3.add(emptyLabel(80,130));

        panel.add(road1);
        panel.add(road2);
        panel.add(road3);

        return panel;
    }
    // END

    //////////////////////////////////////// Stop Selection Screen ////////////////////////////////////////////////

    private static BusGUI stopSelection(){
        BusGUI window=new BusGUI(640, 720, "정류장 검색", Resources.IMG_STOP1, 320, 180);
        window.setMinimumSize(new Dimension(320, 360));
        return insetWindow(window, SelectionInner(TYPE_STOP), 1, 10, 10, 10);
    }

    final static int TYPE_STOP=0;
    final static int TYPE_LINE=1;

    private static JPanel SelectionInner(int type){

        String labelStr;
        Color labelColor;
        String[][] src;

        if(type==TYPE_STOP){
            labelStr="정류장 검색";
            labelColor=Resources.COLOR_PURPLE;
//            src=Resources.testArray(5000);
            src=new String[stopListSet.size()][2];      // 수정
            for(int i=0, j=0; i<10000; i++){
                StopList s = stopListSet.get(i);
                if(s!=null){
                    src[j][0]=String.valueOf(s.getCurStopId());
                    src[j][1]=s.getCurStopName()+"("+s.getNextStopName()+" 방향)";
                    j++;
                }
            }
        } else{
            labelStr="노선 검색";
            labelColor=Resources.COLOR_SKY;
//            src=Resources.testArray(222);
            src=new String[busListSet.size()][2];
            for(int i=0, j=0; i<1000; i++){
                BusList b = busListSet.get(i);
                if(b!=null){
                    src[j][0]=String.valueOf(b.getLineId());
                    if(b.getlineKind().equals("광역버스") && !b.getLineName().contains("나주"))
                        src[j][1]=b.getLineName()+"("+b.getDirUp()+"→"+b.getDirDown()+")";
                    else
                        src[j][1]=b.getLineName()+"("+b.getDirDown()+"→"+b.getDirUp()+")";
                    j++;
                }
            }
        }

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout(0, 10));

        panel.add(colorLabel(labelStr, labelColor), BorderLayout.NORTH);
        panel.add(search(src, type), BorderLayout.CENTER);
        return panel;
    }

    private static JPanel search(String[][] data, int type){

        JPanel center=new JPanel();
        center.setBackground(Color.white);
        center.setLayout(new BorderLayout(0, 10));

        JList<String[]> list=new JList<>(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(40);
        list.setFont(Resources.nsq(Resources.FONT_NORMAL, 24));
        list.setForeground(Color.black);
        if(type==TYPE_LINE) list.setCellRenderer(new RenderLine());          // 아래 Render 클래스 참고...
        else list.setCellRenderer(new RenderStop());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String[][] temp = new String[list.getModel().getSize()][2];
                for(int i=0; i<list.getModel().getSize(); i++) temp[i]=list.getModel().getElementAt(i);

                int index = list.getSelectedIndex();
                System.out.println(temp[index][0]);
                if(type==TYPE_STOP)stopArrive(Integer.parseInt(temp[index][0])).start();
                        else lineInfo(Integer.parseInt(temp[index][0])).start();


            }
        });
        JScrollPane scrollList=new JScrollPane(list);

        JTextField textField=new JTextField();
        textField.setFont(Resources.nsq(Resources.FONT_BOLD, 30));
        textField.setForeground(Color.gray);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) { list.setListData(Resources.search(data, textField.getText())); }});
        center.add(textField, BorderLayout.NORTH);
        center.add(scrollList, BorderLayout.CENTER);

        return center;
    }


    ///////////////////////////////////////// Stop Notify Screen /////////////////////////////////////////////////

    private static BusGUI stopArrive(int id){

        Arrive arrive;
        try {
            arrive=new Arrive(id);
            arrive.setStopName(stopListSet.get(id).getCurStopName());
            arrive.setStopTo(stopListSet.get(id).getNextStopName());
        } catch (NullPointerException e){
//            e.printStackTrace();
            return alertPopup("에러", "광주광역시 정류장이 아닌 것 같습니다.", Color.red, 20);
        }

        BusGUI window=new BusGUI(900, 900, arrive.getStopNameWithTo(), Resources.IMG_STOP1, 40, 80);
        window.setMinimumSize(new Dimension(320, 360));
        return insetWindow(window, stopArriveInner(arrive, window), 20, 20, 20, 20);

    }

    private static JPanel stopArriveInner(Arrive arrive, BusGUI window){
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout(0, 0));
        panel.setBackground(Color.white);

        JPanel stop=new JPanel();
        stop.setLayout(new BorderLayout(0, 20));
        stop.setBackground(Color.white);

        JPanel stopNameWrapper=new JPanel();
        stopNameWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        stopNameWrapper.setBackground(Color.white);

        JLabel stopName=new JLabel(arrive.getStopName());
        stopName.setForeground(Resources.COLOR_BLUE_DARK);
        stopName.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        stopName.setHorizontalAlignment(SwingConstants.CENTER);
        stopName.setVerticalAlignment(SwingConstants.CENTER);

        // Favorite
        FavoriteStopRequestDto requestDto = new FavoriteStopRequestDto.Builder()
                .id(arrive.getId())
                .name(arrive.getStopNameWithTo()).build();

        JButton stopFav=new JButton();
        if(favoritesService.find(requestDto))
            stopFav.setIcon(Resources.getBtImage(Resources.IMG_FAV_YES, 40));
        else
            stopFav.setIcon(Resources.getBtImage(Resources.IMG_FAV_NO, 40));
        stopFav.setBorder(null);
        stopFav.setBackground(Color.white);

        // Add
        stopFav.addActionListener(e -> {
            if(favoritesService.find(requestDto)) {
                favoritesService.delById(requestDto);
                stopFav.setIcon(Resources.getBtImage(Resources.IMG_FAV_NO, 40));
            }
            else {
                favoritesService.save(requestDto);
                stopFav.setIcon(Resources.getBtImage(Resources.IMG_FAV_YES, 40));
            }
        });

        JButton reset=new JButton(Resources.getBtImage(Resources.IMG_FAV_REFRESH, 50));
        reset.setBorder(null);
        reset.setBackground(null);
        reset.addActionListener(e ->{
            window.dispose();
            stopArrive(arrive.getId()).start();
        });

        stopNameWrapper.add(reset);
        stopNameWrapper.add(stopName);
        stopNameWrapper.add(stopFav);


        JLabel stopTo=new JLabel(arrive.getStopTo());
        stopTo.setForeground(Resources.COLOR_GRAY);
        stopTo.setFont(Resources.nsq(Resources.FONT_NORMAL, 28));
        stopTo.setHorizontalAlignment(SwingConstants.CENTER);
        stopTo.setVerticalAlignment(SwingConstants.CENTER);


        // legend
        JPanel legend=new JPanel();
        legend.setLayout(new GridLayout(1, 3));
        legend.setBackground(Resources.COLOR_PURPLE);
        if(arrive.getLines().length>=7) legend.setBorder(new EmptyBorder(0,0,0,20));

        JLabel[] legendLab=new JLabel[4];
        String[] legendStr={"노선", "현 위치", "시간", "정류장 수"};
        for(int i=0; i<4; i++){
            legendLab[i]=new JLabel(legendStr[i]);
            legendLab[i].setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
            legendLab[i].setForeground(Color.white);
            legendLab[i].setHorizontalAlignment(SwingConstants.CENTER);
            legendLab[i].setBorder(new EmptyBorder(30,0,30,0));   // 여백
        }
        for(int i=0; i<2; i++) legend.add(legendLab[i]);

        JPanel legendTime=new JPanel();
        legendTime.setLayout(new GridLayout(1, 2));
        legendTime.setBackground(Resources.COLOR_PURPLE);
        for(int i=2; i<4; i++) legendTime.add(legendLab[i]);
        legend.add(legendTime);

        stop.add(stopNameWrapper, BorderLayout.NORTH);
        stop.add(stopTo, BorderLayout.CENTER);
        stop.add(legend, BorderLayout.SOUTH);


        panel.add(stop, BorderLayout.NORTH);

        JScrollPane arriveInfo = new JScrollPane(arriveInfo(arrive));
        arriveInfo.setBorder(null);

        panel.add(arriveInfo, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel arriveInfo(Arrive arrive){

        ArriveLine[] lines = arrive.getLines();
        JPanel arriveInfo=new JPanel();
        arriveInfo.setBackground(Color.white);
        arriveInfo.setLayout(new GridLayout(lines.length, 1));

        for(ArriveLine l: lines){
            JPanel lineInfo=new JPanel();
            lineInfo.setLayout(new GridLayout(1, 3));

            JButton lineBt=new JButton(l.getLineName());
            JButton stopBt=new JButton(l.getCurStopName());
            lineBt.setFont(Resources.nsq(Resources.FONT_BOLD, 25));
            stopBt.setFont(Resources.nsq(Resources.FONT_BOLD, 20));
            lineBt.setForeground(Color.white);

            lineBt.addActionListener(e -> lineInfo(l.getLineId()).start());
            stopBt.addActionListener(e -> stopArrive(l.getCurStopId()).start());

            JPanel times=new JPanel();
            times.setLayout(new GridLayout(1, 2));
            times.setBackground(Color.white);
            JLabel time=new JLabel(l.getRemainMin()+"분");
            JLabel stop=new JLabel(l.getRemainStop()+"정류장");
            for(JLabel lab : new JLabel[]{time, stop}){
                lab.setFont(Resources.nsq(Resources.FONT_BOLD, 20));
                lab.setHorizontalAlignment(SwingConstants.CENTER);
            }

            for(JButton c:new JButton[]{lineBt, stopBt})
                c.setMargin(new Insets(25,0,25,0));                 // 선을 넣고 여백을 주려면 이것
            for(JComponent c:new JComponent[]{time, stop})
                c.setBorder(new EmptyBorder(25,0,25,0));            // 선을 빼고 여백을 주려면 이것

            if(l.getRemainStop()<2)
                for(Component c:new Component[]{lineBt, stopBt, time, stop, times}) {
                    c.setBackground(Color.yellow);
                    c.setForeground(Color.red);
                    time.setText("곧 도착!");
                }
            else {
                for(Component c:new Component[]{lineBt, stopBt, time, stop}) c.setBackground(Color.white);
                lineBt.setBackground(busColor(l.getLineId()));
            }

            times.add(time);
            times.add(stop);
            lineInfo.add(lineBt);
            lineInfo.add(stopBt);
            lineInfo.add(times);

            arriveInfo.add(borderPanel(lineInfo));
        }

        return arriveInfo;
    }

    private static Color busColor(int id){
//         BusList의 kind를 불러와서 해당하는 색상 반환
        switch(busListSet.get(id).getlineKind()){
            case "급행간선": return Resources.COLOR_RED_BUS;
            case "간선": return Resources.COLOR_YELLOW_BUS;
            case "지선": return Resources.COLOR_GREEN_BUS;
            case "마을버스": return Resources.COLOR_TOWN_BUS;
            case "공항버스": case "광역버스": return Resources.COLOR_WIDE_BUS;
            default: return Color.gray;
        }
    }


    //////////////////////////////////////// Line Selection Screen ////////////////////////////////////////////////

    private static BusGUI lineSelection(){
        BusGUI window=new BusGUI(640, 720, "노선 검색", Resources.IMG_BUS_ORANGE, 960, 180);
        window.setMinimumSize(new Dimension(320, 360));
        return insetWindow(window, SelectionInner(TYPE_LINE), 1, 10, 10, 10);
    }

    /////////////////////////////////////// Line Information Screen ////////////////////////////////////////////////

    private static BusGUI lineInfo(int id) {
        BusLocationMap busLocationMap;
        BusLineMap busLineMap;
        BusList b=busListSet.get(id);
        try {
            busLineMap = new BusLineMap(id);
            busLocationMap = new BusLocationMap(id);
        } catch (Exception e) {
            e.printStackTrace();
            return alertPopup("에러", "인터넷 상태를 확인해 보세요.", Color.red, 20);
        }

        String title;
        if(b.getlineKind().equals("광역버스") && !b.getLineName().contains("나주"))
            title=b.getLineName()+"("+b.getDirUp()+"→"+b.getDirDown()+")";
        else
            title=b.getLineName()+"("+b.getDirDown()+"→"+b.getDirUp()+")";

        BusGUI window = new BusGUI(900, 900, title, Resources.IMG_BUS_ORANGE, 1000, 80);
        window.setMinimumSize(new Dimension(320, 360));
        return insetWindow(window, lineInfoInner(busLineMap, busLocationMap, b, window), 20, 20, 20, 20);
    }

    private static JPanel lineInfoInner(BusLineMap busLineMap, BusLocationMap busLocationMap, BusList busList, BusGUI window) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel lineDesc = new JPanel();
        lineDesc.setLayout(new BorderLayout());
        lineDesc.setBackground(Color.white);

        JPanel lineNameWrapper = new JPanel();
        lineNameWrapper.setLayout(new FlowLayout(FlowLayout.CENTER));
        lineNameWrapper.setBackground(Color.white);

        JLabel lineName = new JLabel(busLineMap.getLineName());
        lineName.setForeground(busColor(busLineMap.getLineId()));
        lineName.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        lineName.setHorizontalAlignment(SwingConstants.CENTER);
        lineName.setVerticalAlignment(SwingConstants.CENTER);

        // favorite
        FavoriteBusRequestDto requestDto = new FavoriteBusRequestDto.Builder()
                .id(busLineMap.getLineId())
                .name(busLineMap.getLineName()+"("+busList.getDirDown()+"→"+busList.getDirUp()+")").build();

        JButton lineFav=new JButton();
        if(favoritesService.find(requestDto))
            lineFav.setIcon(Resources.getBtImage(Resources.IMG_FAV_YES, 40));
        else
            lineFav.setIcon(Resources.getBtImage(Resources.IMG_FAV_NO, 40));
        lineFav.setBorder(null);
        lineFav.setBackground(Color.white);

        // Add
        lineFav.addActionListener(e -> {
            if(favoritesService.find(requestDto)) {
                favoritesService.delById(requestDto);
                lineFav.setIcon(Resources.getBtImage(Resources.IMG_FAV_NO, 40));
            }
            else {
                favoritesService.save(requestDto);
                lineFav.setIcon(Resources.getBtImage(Resources.IMG_FAV_YES, 40));
            }
        });

        JButton reset=new JButton(Resources.getBtImage(Resources.IMG_FAV_REFRESH, 50));
        reset.setBorder(null);
        reset.setBackground(null);
        reset.addActionListener(e ->{
            window.dispose();
            lineInfo(busLineMap.getLineId()).start();
        });

        lineNameWrapper.add(reset);
        lineNameWrapper.add(lineName);
        lineNameWrapper.add(lineFav);


        JPanel lineInfo = new JPanel();
        lineInfo.setLayout(new GridLayout(2, 3));
        lineInfo.setBackground(Color.white);
        lineInfo.setBorder(new EmptyBorder(20,0,0,0));

        String[] lineInfoRes;
        if(busList.getlineKind().equals("광역버스") && !busList.getLineName().contains("나주"))
            lineInfoRes = new String[]{"기점: "+busList.getDirUp(), "운행 차량 수: "+busLocationMap.getBusCount()+"대", "종점: "+busList.getDirDown(), "첫차: "+busList.getfirstTime(),  "배차간격: "+busList.getinterval(), "막차: "+busList.getlastTime()};
        else lineInfoRes = new String[]{"기점: "+busList.getDirDown(), "운행 차량 수: "+busLocationMap.getBusCount()+"대", "종점: "+busList.getDirUp(), "첫차: "+busList.getfirstTime(),  "배차간격: "+busList.getinterval(), "막차: "+busList.getlastTime()};
        for(int i = 0; i < 6; i++) {
            JLabel lineInfos = new JLabel(lineInfoRes[i]);
            lineInfos.setForeground(Color.gray);
            lineInfos.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
            lineInfos.setHorizontalAlignment(SwingConstants.CENTER);
            lineInfos.setVerticalAlignment(SwingConstants.CENTER);
            lineInfos.setBorder(new EmptyBorder(10, 10, 10, 10));
            lineInfo.add(lineInfos);
        }



        // legend
        JPanel legend1 = new JPanel();
        legend1.setLayout(new GridLayout(1, 2));
        legend1.setBackground(busColor(busLineMap.getLineId()));
        if(busLineMap.getBusLines().length>=7) legend1.setBorder(new EmptyBorder(0,0,0,15));

        String[] legendStr = new String[]{"정류장 이름", "버스", "저상", "비고"};
        JLabel[] legendLab = new JLabel[4];
        for(int i = 0; i < 4; i++) {
            legendLab[i] = new JLabel(legendStr[i]);
            legendLab[i].setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
            legendLab[i].setForeground(Color.white);
            legendLab[i].setHorizontalAlignment(0);
            legendLab[i].setBorder(new EmptyBorder(30, 0, 30, 0));
        }

        JPanel legend2 = new JPanel();
        legend2.setLayout(new GridLayout(1, 3));
        legend2.setBackground(busColor(busLineMap.getLineId()));

        legend1.add(legendLab[0]);
        for(int i = 1; i < 4; i++) legend2.add(legendLab[i]);
        legend1.add(legend2);


        lineDesc.add(lineNameWrapper, BorderLayout.NORTH);
        lineDesc.add(lineInfo, BorderLayout.CENTER);
        lineDesc.add(legend1, BorderLayout.SOUTH);

        panel.add(lineDesc, BorderLayout.NORTH);

        JScrollPane lineStopList = new JScrollPane(lineStopList(busLineMap, busLocationMap));
        lineStopList.setBorder(null);
        panel.add(lineStopList, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel lineStopList(BusLineMap busLineMap, BusLocationMap busLocationMap) {

        BusLine[] busLines = busLineMap.getBusLines();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(busLines.length, 1));
        panel.setBackground(Color.white);

        for(BusLine l : busLines) {
            JPanel stopPanel = new JPanel();
            stopPanel.setLayout(new GridLayout(1, 2));

            JButton stopBt = new JButton(l.getStopName());
            stopBt.setFont(Resources.nsq(Resources.FONT_BOLD, 25));
            stopBt.setBackground(Color.white);
            stopBt.setForeground(Resources.COLOR_PURPLE);
            stopBt.addActionListener((e) -> stopArrive(l.getStopId()).start());

            JPanel stopPanel2 = new JPanel();
            stopPanel2.setLayout(new GridLayout(1, 3));
            stopPanel2.setBackground(Color.white);
            String[] str = new String[]{
                    busLocationMap.getBusLocation(l.getStopId()).getBusNumber(),
                    busLocationMap.getBusLocation(l.getStopId()).getIsLowBus(),
                    l.getFlag()};
            JLabel[] labels = new JLabel[3];

            for(int i = 0; i < 3; i++) {
                labels[i] = new JLabel(str[i]);
                labels[i].setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
                labels[i].setHorizontalAlignment(0);
                labels[i].setBorder(new EmptyBorder(30, 0, 30, 0));
                stopPanel2.add(labels[i]);
            }

            for(int j = 1; j < 3; j++){
                if (!labels[j].getText().equals("일반")) {
                    labels[j].setFont(Resources.nsq(Resources.FONT_BOLD, 25));
                    labels[j].setForeground(Color.red);
                }
            }


            stopPanel.add(stopBt);
            stopPanel.add(stopPanel2);
            panel.add(stopPanel);
        }

        return panel;
    }

    ///////////////////////////////////////// Favorite Screen ////////////////////////////////////////////////////

    public static BusGUI favorite(){
        BusGUI window = new BusGUI(1280, 720, "즐겨찾기", Resources.IMG_FAV_YES, 320, 180);
        window.setMinimumSize(new Dimension(800, 400));

        if(getFav(TYPE_LINE).length == 0 && getFav(TYPE_STOP).length == 0)
            return alertPopup("오류", "등록된 즐겨찾기가 없습니다.", Color.red, 20);


        return insetWindow(window, favoriteInner(window), 1, 10, 10, 10);
    }


    final static int TYPE_ALL = 2;

    private static JPanel favoriteInner(BusGUI window){

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(1,2, 10, 0));

        //Stop
        JPanel favStop=new JPanel();
        favStop.setLayout(new BorderLayout(0,10));
        favStop.setBackground(Color.white);

        JPanel favStopTop=new JPanel();
        favStopTop.setLayout(new BorderLayout(0,10));
        favStopTop.setBackground(Color.white);
        favStopTop.add(colorLabel("즐겨찾기", Resources.COLOR_PINK_DARK), BorderLayout.NORTH);
        favStopTop.add(colorLabel("정류장", Resources.COLOR_PURPLE), BorderLayout.CENTER);

        favStop.add(favStopTop, BorderLayout.NORTH);
        favStop.add(favoriteList(TYPE_STOP, getFav(TYPE_STOP)), BorderLayout.CENTER);

        //Line
        JPanel favLine=new JPanel();
        favLine.setLayout(new BorderLayout(0,10));
        favLine.setBackground(Color.white);

        JPanel favLineTop=new JPanel();
        favLineTop.setLayout(new BorderLayout(0,10));
        favLineTop.setBackground(Color.white);

        JPanel menu=new JPanel();
        menu.setLayout(new GridLayout(1, 6));
        menu.setBackground(Color.white);

        JPanel resetWrapper=new JPanel();
        resetWrapper.setBackground(Color.white);
        resetWrapper.setLayout(new GridLayout(1,2));

        JButton reset=new JButton(Resources.getBtImage(Resources.IMG_FAV_REFRESH, 50));
        reset.setBorder(null);
        reset.setBackground(null);
        reset.addActionListener(e ->{
            window.dispose();
            favorite().start();
        });
        resetWrapper.add(reset);
        resetWrapper.add(new JLabel());
        menu.add(resetWrapper);
        menu.add(colorLabel(" ", Color.white));

        JLabel label=new JLabel("전체 삭제");
        label.setFont(Resources.nsq(Resources.FONT_BOLD, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        menu.add(label);

        JButton delStop=new JButton("정류장");
        delStop.setBackground(Resources.COLOR_PURPLE);
        delStop.setFont(Resources.nsq(Resources.FONT_BOLD, 16));
        delStop.setForeground(Color.white);
        delStop.addActionListener(e -> confirm(window, TYPE_STOP).start());
        delStop.setMargin(new Insets(0,0,0,0));

        JButton delLine=new JButton("노선");
        delLine.setBackground(Resources.COLOR_SKY);
        delLine.setFont(Resources.nsq(Resources.FONT_BOLD, 16));
        delLine.setForeground(Color.white);
        delLine.addActionListener(e -> confirm(window, TYPE_LINE).start());
        delLine.setMargin(new Insets(0,0,0,0));

        JButton delAll=new JButton("둘 다");
        delAll.setBackground(Color.red);
        delAll.setFont(Resources.nsq(Resources.FONT_BOLD, 16));
        delAll.setForeground(Color.white);
        delAll.addActionListener(e -> confirm(window, TYPE_ALL).start());
        delAll.setMargin(new Insets(0,0,0,0));

        menu.add(delStop);
        menu.add(delLine);
        menu.add(delAll);

        favLineTop.add(menu, BorderLayout.NORTH);
        favLineTop.add(colorLabel("노선", Resources.COLOR_SKY), BorderLayout.CENTER);

        favLine.add(favLineTop, BorderLayout.NORTH);
        favLine.add(favoriteList(TYPE_LINE, getFav(TYPE_LINE)), BorderLayout.CENTER);


        panel.add(favStop);
        panel.add(favLine);

        return panel;
    }


    private static void delFav(BusGUI window, int type){
        switch (type){
            case TYPE_ALL:
//                delFavStop();
                favoritesService.deleteAllStop();
            case TYPE_LINE:
//                delFavLine();
                favoritesService.deleteAllLine();
                break;
            default:
//                delFavStop();
                favoritesService.deleteAllStop();
                break;
        }
        window.dispose();
        favorite().start();
//        alertPopup("삭제됨", "즐겨찾기가 삭제되었습니다. 창을 새로 여세요.", Color.blue, 20).start();
    }

    // custom del favorite
    private static void delFavLine(){
        String[][] lineFav = getFav(TYPE_LINE);
        for(String[] single : lineFav){
            FavoriteBusRequestDto dto=new FavoriteBusRequestDto.Builder()
                    .id(Integer.parseInt(single[0]))
                    .name(single[1]).build();
            favoritesService.delById(dto);
        }
    }

    private static void delFavStop(){
        String[][] lineFav = getFav(TYPE_STOP);
        for(String[] single : lineFav){
            FavoriteStopRequestDto dto=new FavoriteStopRequestDto.Builder()
                    .id(Integer.parseInt(single[0]))
                    .name(single[1]).build();
            favoritesService.delById(dto);
        }
    }


    public static String[][] getFav(int type){

        String[][] res;

        if(type==TYPE_LINE) {
            List<FavoriteBus>  list = favoritesService.findAllLines();
            res=new String[list.size()][2];

            for (int i=0; i< list.size(); i++){
                res[i][0] = String.valueOf(list.get(i).getId());
                res[i][1] = list.get(i).getName();
            }
        } else {
            List<FavoriteStop>  list = favoritesService.findAllStops();
            res=new String[list.size()][2];

            for (int i=0; i< list.size(); i++){
                res[i][0] = String.valueOf(list.get(i).getId());
                res[i][1] = list.get(i).getName();
            }
        }
        return res;
    }

    private static JScrollPane favoriteList(int type, String[][] data){

        JList<String[]> list=new JList<>(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFixedCellHeight(40);
        list.setFont(Resources.nsq(Resources.FONT_NORMAL, 24));
        list.setForeground(Color.black);
        if(type==TYPE_LINE) list.setCellRenderer(new RenderLine());
        else list.setCellRenderer(new RenderStop());
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int index = list.getSelectedIndex();
                if(type==TYPE_STOP)stopArrive(Integer.parseInt(data[index][0])).start();
                else lineInfo(Integer.parseInt(data[index][0])).start();

            }
        });
        return new JScrollPane(list);
    }




    ///////////////////////////////////////////// Etc Screen //////////////////////////////////////////////////////

    public static BusGUI alertPopup(String title, String message, Color color, int fontSize){
        BusGUI window = new BusGUI(400, 200, title, Resources.IMG_STOP1, 760, 440);
        window.setMinimumSize(new Dimension(400, 200));
        window.setLayout(null);
        window.mainContainer.setBackground(Color.white);

        JLabel msg=new JLabel(message);
        msg.setFont(Resources.nsq(Resources.FONT_BOLD, fontSize));
        msg.setForeground(color);
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        msg.setBounds(0,0,380,100);

        JButton button=new JButton("확인");
        button.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        button.setBackground(Color.yellow);
        button.setForeground(Color.black);
        button.setBounds(90,100,190,60);
        button.addActionListener(e -> window.dispose());   // 창 닫기

        window.add(msg);
        window.add(button);
        return window;
    }


    public static BusGUI confirm(BusGUI fav, int type){
        BusGUI window = new BusGUI(600, 200, "경고", Resources.IMG_STOP1, 660, 440);
        window.setMinimumSize(new Dimension(600, 200));
        window.setLayout(null);
        window.mainContainer.setBackground(Color.white);

        JLabel msg=new JLabel("정말 삭제하시겠습니까? 이 작업은 취소할 수 없습니다!");
        msg.setFont(Resources.nsq(Resources.FONT_BOLD, 20));
        msg.setForeground(Color.red);
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        msg.setBounds(0,0,580,100);

        JButton yes=new JButton("예");
        yes.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        yes.setBackground(Color.yellow);
        yes.setForeground(Color.black);
        yes.setBounds(40,100,200,60);
        yes.addActionListener(e -> {
            switch (type){
                case TYPE_STOP:
                    delFav(fav, TYPE_STOP);
                    break;
                case TYPE_LINE:
                    delFav(fav, TYPE_LINE);
                    break;
                case TYPE_ALL:
                    delFav(fav, TYPE_ALL);
                    break;
                default: break;
            }
            window.dispose();
        });


        JButton no=new JButton("아니오");
        no.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        no.setBackground(Color.orange);
        no.setForeground(Color.black);
        no.setBounds(330,100,200,60);
        no.addActionListener(e -> window.dispose());

        window.add(msg);
        window.add(yes);
        window.add(no);

        return window;
    }

    //END

    ///////////////////////////////////////////// Tools //////////////////////////////////////////////////////



    // Jlist 아이템별 내부 색상을 다르게 하려면 이렇게...
    // 출처: https://www.codejava.net/java-se/swing/jlist-custom-renderer-example
    // Android에서 RecyclerView와 비슷한 역할을 함
    static class RenderLine extends JLabel implements ListCellRenderer<String[]>{
        // extends 원하는 요소 implements ListCellRenderer<원하는 클래스(String, BusList, StopList...)>

        RenderLine(){
            setHorizontalAlignment(LEFT);
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String[]> list, String[] value, int index, boolean isSelected, boolean cellHasFocus) {

            setText("(#"+value[0]+") "+value[1]);
            setFont(Resources.nsq(Resources.FONT_NORMAL, 24));
            setForeground(Color.black);

            switch (busListSet.get(Integer.parseInt(value[0])).getlineKind()){
                case "간선":
                    setForeground(Resources.COLOR_YELLOW_BUS);
                    setIcon(Resources.getBtImage(Resources.IMG_BUS_ORANGE, 50));
                    break;
                case "지선":
                    setForeground(Resources.COLOR_GREEN_BUS);
                    setIcon(Resources.getBtImage(Resources.IMG_BUS_GREEN, 50));
                    break;
                case "급행간선":
                    setForeground(Resources.COLOR_RED_BUS);
                    setIcon(Resources.getBtImage(Resources.IMG_BUS_RED, 50));
                    break;
                case "마을버스":
                    setForeground(Resources.COLOR_TOWN_BUS);
                    setIcon(Resources.getBtImage(Resources.IMG_BUS_TOWN, 50));
                    break;
                case "공항버스": case "광역버스":
                    setForeground(Resources.COLOR_WIDE_BUS);
                    setIcon(Resources.getBtImage(Resources.IMG_BUS_WIDE, 50));
                    break;
                default:
                    setIcon(null);
                    break;
            }
            if(isSelected)
                setBackground(Resources.COLOR_GRAY);
            else setBackground(list.getBackground());
            return this;
        }
    }

    static class RenderStop extends JLabel implements ListCellRenderer<String[]>{
        // extends 원하는 요소 implements ListCellRenderer<원하는 클래스(String, BusList, StopList...)>

        RenderStop(){
            setHorizontalAlignment(LEFT);
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String[]> list, String[] value, int index, boolean isSelected, boolean cellHasFocus) {

            setText("(#"+value[0]+") "+value[1]);
            setFont(Resources.nsq(Resources.FONT_NORMAL, 24));
            setForeground(Color.black);

            if(isSelected)
                setBackground(Resources.COLOR_GRAY);
            else setBackground(list.getBackground());
            return this;
        }
    }


    // BorderLayout으로 바꿔주는 패널
    private static JPanel borderPanel(JPanel panel){
        JPanel wrapper=new JPanel();
        wrapper.setLayout(new BorderLayout(0,0));
//        wrapper.add(emptyPanel(1, height, bg), BorderLayout.WEST);
        wrapper.add(panel, BorderLayout.CENTER);
        return wrapper;
    }
    
    // 바깥 여백을 주는 패널
    private static JPanel insetPanel(int top, int bottom, int left, int right){
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout(30,30));

        panel.add(emptyPanel(left, 1, Color.white), BorderLayout.WEST);
        panel.add(emptyPanel(right, 1, Color.white), BorderLayout.EAST);
        panel.add(emptyPanel(1, top, Color.white), BorderLayout.NORTH);
        panel.add(emptyPanel(1, bottom, Color.white), BorderLayout.SOUTH);

        return panel;
    }

    // 바깥 여백을 주는 창
    private static BusGUI insetWindow(BusGUI window, JPanel center, int top, int bottom, int left, int right){

        window.add(emptyPanel(left, 1, Color.white), BorderLayout.WEST);
        window.add(emptyPanel(right, 1, Color.white), BorderLayout.EAST);
        window.add(emptyPanel(1, top, Color.white), BorderLayout.NORTH);
        window.add(emptyPanel(1, bottom, Color.white), BorderLayout.SOUTH);
        window.add(center, BorderLayout.CENTER);

        return window;
    }


    // 여백 전용 빈 패널
    private static JPanel emptyPanel(int width, int height, Color color){
        JPanel panel = new JPanel();

        panel.setBackground(color);
        panel.add(new JLabel(new ImageIcon(Resources.getWindowIco(Resources.IMG_EMPTY).getScaledInstance(width, height, Image.SCALE_FAST))));
        return panel;
    }

    // 여백 전용 빈 라벨
    private static JLabel emptyLabel(int width, int height){
        return new JLabel(Resources.getBtImage(Resources.IMG_EMPTY, width, height));
    }

    // 컬러 라벨
    private static JPanel colorLabel(String text, Color color){

        JPanel labelBgWrap=new JPanel();
        labelBgWrap.setLayout(new BorderLayout());
        labelBgWrap.setBackground(Color.white);
        JPanel labelBg=new JPanel();
        labelBg.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelBg.setBackground(color);
        JLabel label=new JLabel(text);
        label.setFont(Resources.nsq(Resources.FONT_NORMAL, 30));
        label.setForeground(Color.white);
        labelBg.add(label);
        labelBgWrap.add(labelBg, BorderLayout.WEST);

        return labelBgWrap;
    }


    // END

    public void start(){ setVisible(true);}

}

