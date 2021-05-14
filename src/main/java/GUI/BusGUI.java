package GUI;

import Parse.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BusGUI extends JFrame {

    Container mainContainer=getContentPane();

    BusGUI(int width, int height, String title, String ico){
        setTitle(title);
        setSize(width, height);
        setIconImage(Resources.getWindowIco(ico));
        setBackground(Color.white);
    }

    BusGUI(int width, int height, String title, String ico, int startX, int startY){
        this(width, height, title, ico);
        setBounds(startX, startY, width, height);
    }


    // GUI.BusGUI(JFrame): 창
    // JPanel: 구역

    // window.start() : 창 띄우기
    // window.dispose() : 창 닫기

    //////////////////////////////////////////////// Main Screen ///////////////////////////////////////////////////

    public static BusGUI mainMenu(){
        BusGUI window=new BusGUI(1280, 720, "Bus Information System", Resources.IMG_BUS1, 200, 300);
        window.mainContainer.setBackground(Color.white);
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
        panel.add(stopBt);

        JButton lineBt=new JButton(" 노선", Resources.getBtImage(Resources.IMG_BUS1, 120));
        lineBt.setMargin(new Insets(0,0,0,0));
        lineBt.setBackground(Resources.COLOR_SKY);
        lineBt.setFont(Resources.nsq(Resources.FONT_NORMAL, 40));
        lineBt.setForeground(Color.white);
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
        panel.setBackground(Color.gray);

        JPanel road1=new JPanel();
        road1.setBackground(Color.gray);
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
        road3.setBackground(Color.gray);

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


    ///////////////////////////////////////// Stop Notify Screen /////////////////////////////////////////////////


    //////////////////////////////////////// Line Selection Screen ////////////////////////////////////////////////


    /////////////////////////////////////// Line Information Screen ////////////////////////////////////////////////



    ///////////////////////////////////////////// Etc Screen //////////////////////////////////////////////////////

    public static BusGUI alertPopup(String title, String message, Color color, int fontSize){
        BusGUI window = new BusGUI(400, 200, title, Resources.IMG_STOP1, 760, 440);
        window.setMinimumSize(new Dimension(400, 200));
        window.setLayout(null);
        window.mainContainer.setBackground(Color.white);

        JLabel msg=new JLabel(message);
        msg.setFont(Resources.nsq(Resources.FONT_BOLD, 24));
        msg.setForeground(color);
        msg.setHorizontalAlignment(SwingConstants.CENTER);
        msg.setBounds(0,0,400,100);

        JButton button=new JButton("확인");
        button.setFont(Resources.nsq(Resources.FONT_NORMAL, fontSize));
        button.setBackground(Color.yellow);
        button.setForeground(Color.black);
        button.setBounds(100,100,180,60);
        button.addActionListener(e -> window.dispose());   // 창 닫기

        window.add(msg);
        window.add(button);
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

