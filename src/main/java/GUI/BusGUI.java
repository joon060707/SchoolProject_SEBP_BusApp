package GUI;

import Parse.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

public class BusGUI extends JFrame {

    Container mainContainer=getContentPane();

    BusGUI(int width, int height, String title, String ico){
        setTitle(title);
        setSize(width, height);
        setIconImage(Resources.getWindowIco(ico));
        mainContainer.setBackground(Color.white);
    }

    BusGUI(int width, int height, String title, String ico, int startX, int startY){
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
        BusGUI window=new BusGUI(1280, 720, "Bus Information System", Resources.IMG_BUS1, 320, 180);
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
        panel.add(stopBt);

        JButton lineBt=new JButton(" 노선", Resources.getBtImage(Resources.IMG_BUS1, 120));
        lineBt.setMargin(new Insets(0,0,0,0));
        lineBt.setBackground(Resources.COLOR_SKY);
        lineBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        lineBt.setForeground(Color.white);
        lineBt.addActionListener(e -> BusGUI.lineSelection().start());
        panel.add(lineBt);

        for (int i=0; i<5; i++) panel.add(new JLabel());

        return panel;
    }

    private static JPanel mainTop(){
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout(0,0));
        panel.setBackground(Resources.COLOR_BLUE_DARK);

        JLabel title=new JLabel("Gwangju City Bus Information System   ");
        title.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        title.setForeground(Color.white);
        panel.add(title, BorderLayout.EAST);

        JPanel titleImg=new JPanel();
        titleImg.setLayout(new FlowLayout());
        titleImg.setBackground(Resources.COLOR_BLUE_DARK);

        titleImg.add(emptyLabel(40, 10));
        JLabel busImg=new JLabel(Resources.getBtImage(Resources.IMG_BUS2, -1, 80));
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
            if(new Arrive(1253).getSize()!=0)
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
        yellowBt.setBackground(Resources.COLOR_YELLOW_BUS);
        yellowBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        yellowBt.setForeground(Color.white);
        road1.add(yellowBt);
        for(int i=0; i<4; i++)road1.add(emptyLabel(80,130));


        JPanel road2=new JPanel();
        road2.setBackground(Color.yellow);
        road2.add(emptyLabel(10, 900));


        JPanel road3=new JPanel();
        road3.setLayout(new GridLayout(6,1));
        road3.setBackground(Resources.COLOR_GRAY);

        JButton redBt = new JButton("통계");
        redBt.setBackground(Resources.COLOR_RED_BUS);
        redBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        redBt.setForeground(Color.white);
        road3.add(redBt);

        road3.add(emptyLabel(80,130));
        JButton greenBt = new JButton("정보");
        greenBt.setBackground(Resources.COLOR_GREEN_BUS);
        greenBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
        greenBt.setForeground(Color.white);
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
            src=Resources.testArray(5000);
        } else{
            labelStr="노선 검색";
            labelColor=Resources.COLOR_SKY;
            src=Resources.testArray(150);
        }

        JPanel panel=new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(new BorderLayout(0, 10));

        JPanel labelBgWrap=new JPanel();
        labelBgWrap.setLayout(new BorderLayout());
        labelBgWrap.setBackground(Color.white);
        JPanel labelBg=new JPanel();
        labelBg.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelBg.setBackground(labelColor);
        JLabel label=new JLabel(labelStr);
        label.setFont(Resources.nsq(Resources.FONT_NORMAL, 30));
        label.setForeground(Color.white);

        labelBg.add(label);
        labelBgWrap.add(labelBg, BorderLayout.WEST);
        panel.add(labelBgWrap, BorderLayout.NORTH);
        panel.add(search(src, type), BorderLayout.CENTER);
        return panel;
    }

    private static JPanel search(String[][] data, int type){

        JPanel center=new JPanel();
        center.setBackground(Color.white);
        center.setLayout(new BorderLayout(0, 10));

        JList<String> list=new JList<>(data[1]);
        list.setFixedCellHeight(40);
        list.setFont(Resources.nsq(Resources.FONT_NORMAL, 24));
        list.setForeground(Color.black);
        if(type==TYPE_LINE) list.setCellRenderer(new Render(data[0]));          // 아래 Render 클래스 참고...
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int[] found = Resources.searchIndex(data, list.getSelectedValue());
                if(type==TYPE_STOP)
                    if(found.length>1) {
                        String lst="";
                        for(int i: found) lst=lst.concat(i+" ");
                    // 보통 다음 정류장이 없는 경우 발생. 창 2개 다 띄울 것인가?
                    alertPopup("여러 항목이 검색되었습니다", "선택된 숫자: "+lst, Color.BLACK, 20).start();
                    } else stopArrive(found[0]).start();
                else lineInfo(found[0]).start();
            }
        });
        JScrollPane scrollList=new JScrollPane(list);

        JTextField textField=new JTextField();
        textField.setFont(Resources.nsq(Resources.FONT_BOLD, 30));
        textField.setForeground(Color.gray);
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String[][] find=Resources.search(data, textField.getText());
//                for(String s: find[0])System.out.print(s+" ");
                if(type==TYPE_LINE) list.setCellRenderer(new Render(find[0]));      // 이게 먼저 와야 함. 왜 그럴까?
                list.setListData(find[1]);
            }});

        center.add(textField, BorderLayout.NORTH);
        center.add(scrollList, BorderLayout.CENTER);

        return center;
    }



    ///////////////////////////////////////// Stop Notify Screen /////////////////////////////////////////////////

    private static BusGUI stopArrive(int id){

        Arrive arrive;
        try {
            arrive=new Arrive(id);
        } catch (Exception e){
            return alertPopup("에러", "오류가 발생했습니다.", Color.red, 20);
        }

        BusGUI window=new BusGUI(900, 900, arrive.getStopNameWithTo(), Resources.IMG_STOP1, 40, 80);
        window.setMinimumSize(new Dimension(320, 360));
        return insetWindow(window, stopArriveInner(arrive), 20, 20, 20, 20);

    }

    private static JPanel stopArriveInner(Arrive arrive){
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout(0, 40));
        panel.setBackground(Color.white);

        JPanel stop=new JPanel();
        stop.setLayout(new BorderLayout(0, 20));
        stop.setBackground(Color.white);

        JLabel stopName=new JLabel(arrive.getStopName());
        stopName.setForeground(Resources.COLOR_BLUE_DARK);
        stopName.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        stopName.setHorizontalAlignment(SwingConstants.CENTER);
        stopName.setVerticalAlignment(SwingConstants.CENTER);

        JLabel stopTo=new JLabel(arrive.getStopTo());
        stopTo.setForeground(Resources.COLOR_GRAY);
        stopTo.setFont(Resources.nsq(Resources.FONT_NORMAL, 28));
        stopTo.setHorizontalAlignment(SwingConstants.CENTER);
        stopTo.setVerticalAlignment(SwingConstants.CENTER);

        stop.add(stopName, BorderLayout.NORTH);
        stop.add(stopTo, BorderLayout.CENTER);

        panel.add(stop, BorderLayout.NORTH);
//        arrive.print();

        JScrollPane arriveInfo = new JScrollPane(arriveInfo(arrive));
        arriveInfo.setBorder(null);

        panel.add(arriveInfo, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel arriveInfo(Arrive arrive){

        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout(0,0));
        panel.setBackground(Color.white);

        JPanel legend=new JPanel();
        legend.setLayout(new GridLayout(1, 3));
        legend.setBackground(Resources.COLOR_PURPLE);
//        legend.setBorder(new EmptyBorder(30,0,30,0));

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

//        panel.add(legend);
        panel.add(borderPanel(legend), BorderLayout.NORTH);
        panel.add(arriveInfoInner(arrive), BorderLayout.CENTER);

        return panel;
    }

    private static JPanel arriveInfoInner(Arrive arrive){

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
        // BusList의 kind를 불러와서 해당하는 색상 반환
//        switch(BusList.getBusList().get(id).getKind())
        return Resources.COLOR_YELLOW_BUS;
    }


    //////////////////////////////////////// Line Selection Screen ////////////////////////////////////////////////

    private static BusGUI lineSelection(){
        BusGUI window=new BusGUI(640, 720, "노선 검색", Resources.IMG_BUS1, 960, 180);
        window.setMinimumSize(new Dimension(320, 360));
        return insetWindow(window, SelectionInner(TYPE_LINE), 1, 10, 10, 10);
    }

    /////////////////////////////////////// Line Information Screen ////////////////////////////////////////////////

    private static BusGUI lineInfo(int id) {
        BusLocation busLocation;
        BusLineMap busLineMap;
        try {
            busLineMap = new BusLineMap(id);
            busLocation = new BusLocation(id);
        } catch (Exception e) {
            return alertPopup("에러", "오류가 발생했습니다.", Color.red, 20);
        }

        BusGUI window = new BusGUI(900, 900, busLineMap.getLineName(), Resources.IMG_STOP1, 1000, 80);
        window.setMinimumSize(new Dimension(320, 360));
        return insetWindow(window, lineInfoInner(busLineMap, busLocation), 20, 20, 20, 20);
    }

    private static JPanel lineInfoInner(BusLineMap busLineMap, BusLocation busLocation) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel lineDesc = new JPanel();
        lineDesc.setLayout(new BorderLayout());
        lineDesc.setBackground(Color.white);

        JLabel lineName = new JLabel(busLineMap.getLineName());
        lineName.setForeground(busColor(busLineMap.getLineId()));
        lineName.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        lineName.setHorizontalAlignment(SwingConstants.CENTER);
        lineName.setVerticalAlignment(SwingConstants.CENTER);
        lineName.setBorder(new EmptyBorder(10, 0, 30, 0));

        JPanel lineInfo = new JPanel();
        lineInfo.setLayout(new GridLayout(2, 2));
        lineInfo.setBackground(Color.white);

        String[] lineInfoRes = {"기점: "+busLineMap.getBusLines()[0].getStopName(), "종점: 종점", "첫차: 00:00", "막차: 23:59"};
        for(int i = 0; i < 4; i++) {
            JLabel lineInfos = new JLabel(lineInfoRes[i]);
            lineInfos.setForeground(Resources.COLOR_GRAY);
            lineInfos.setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
            lineInfos.setHorizontalAlignment(SwingConstants.CENTER);
            lineInfos.setVerticalAlignment(SwingConstants.CENTER);
            lineInfos.setBorder(new EmptyBorder(10, 20, 10, 20));
            lineInfo.add(lineInfos);
        }

        lineDesc.add(lineName, BorderLayout.NORTH);
        lineDesc.add(lineInfo, BorderLayout.CENTER);
        panel.add(lineDesc, BorderLayout.NORTH);

        JScrollPane lineStopList = new JScrollPane(lineStopList(busLineMap, busLocation));
        lineStopList.setBorder(null);
        panel.add(lineStopList, BorderLayout.CENTER);

        return panel;
    }

    private static JPanel lineStopList(BusLineMap busLineMap, BusLocation busLocation) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel legend1 = new JPanel();
        legend1.setLayout(new GridLayout(1, 2));
        legend1.setBackground(Resources.COLOR_SKY);

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
        legend2.setBackground(Resources.COLOR_SKY);

        legend1.add(legendLab[0]);
        for(int i = 1; i < 4; i++) legend2.add(legendLab[i]);
        legend1.add(legend2);

        panel.add(legend1, BorderLayout.NORTH);
        panel.add(lineStopListInner(busLineMap, busLocation), BorderLayout.CENTER);
        return panel;
    }

    private static JPanel lineStopListInner(BusLineMap busLineMap, BusLocation busLocation) {

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
            String[] str = new String[]{"차량 번호", "예", busLineMap.flagData(l.getFlag())};
            JLabel[] labels = new JLabel[3];

            for(int i = 0; i < 3; i++) {
                labels[i] = new JLabel(str[i]);
                labels[i].setFont(Resources.nsq(Resources.FONT_NORMAL, 20));
                labels[i].setHorizontalAlignment(0);
                labels[i].setBorder(new EmptyBorder(30, 0, 30, 0));
                stopPanel2.add(labels[i]);
            }

            if (!labels[2].getText().equals("일반")) {
                labels[2].setFont(Resources.nsq(Resources.FONT_BOLD, 25));
                labels[2].setForeground(Color.red);
            }

            stopPanel.add(stopBt);
            stopPanel.add(stopPanel2);
            panel.add(stopPanel);
        }

        return panel;
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

    //END

    ///////////////////////////////////////////// Tools //////////////////////////////////////////////////////


    // Jlist 아이템별 내부 색상을 다르게 하려면 이렇게...
    // 출처: https://www.codejava.net/java-se/swing/jlist-custom-renderer-example
    // Android에서 RecyclerView와 비슷한 역할을 함
    static class Render extends JLabel implements ListCellRenderer<String>{
        // extends 원하는 요소 implements ListCellRenderer<원하는 클래스(String, BusList, StopList...)>

        String[] indexList;
        Render(String[] indexList){
            this.indexList=indexList;
            setHorizontalAlignment(LEFT);
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {

            setText(value);
            setFont(Resources.nsq(Resources.FONT_NORMAL, 24));
            setForeground(Color.black);

            switch (Integer.parseInt(indexList[index])%10){
                case 0: setForeground(Resources.COLOR_YELLOW_BUS);
                    break;
                case 1: setForeground(Resources.COLOR_GREEN_BUS);
                    break;
                case 2: setForeground(Resources.COLOR_RED_BUS);
                    break;
                case 3: setForeground(Resources.COLOR_TOWN_BUS);
                    break;
                case 4: setForeground(Resources.COLOR_AIR_BUS);
                    break;
                default: break;
            }
            if(isSelected)
                setBackground(Resources.COLOR_GRAY);
            else setBackground(list.getBackground());
            return this;
        }
    }


    // 원하는 높이로 바꿔주는 패널
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

    // END

    public void start(){ setVisible(true);}

}

