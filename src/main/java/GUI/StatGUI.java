package GUI;

import Parse.BusListSet;
import Parse.StopListSet;

import javax.swing.*;
import java.awt.*;

public class StatGUI extends BusGUI {

    public StatGUI(int width, int height, String title, String ico) {
        super(width, height, title, ico);
    }

    public StatGUI(int width, int height, String title, String ico, int startX, int startY) {
        super(width, height, title, ico, startX, startY);
    }

    public static StatGUI stat() {

       /* StatGUI window = new StatGUI(300, 300, "통계", null);
        JLabel label = new JLabel("총 정류장 수:"+StopListSet.size+"총 노선 수:"+BusListSet.size);
        window.setLayout(new BorderLayout());
        window.add(label,BorderLayout.NORTH);

        window.add(label);
        return window; */
        JFrame frame = new JFrame("통계");
        Container window = frame.getContentPane();

        JLabel label1 = new JLabel("총 정류장 수: ",SwingConstants.CENTER);
        JLabel label2 = new JLabel("총 노선 수: ",SwingConstants.CENTER);
        JLabel label3 = new JLabel(BusListSet.sizeString,SwingConstants.CENTER);
        JLabel label4 = new JLabel(StopListSet.sizeString,SwingConstants.CENTER);
        label1.setFont(Resources.nsq(Resources.FONT_NORMAL,30));
        label2.setFont(Resources.nsq(Resources.FONT_NORMAL,30));
        label3.setFont(Resources.nsq(Resources.FONT_NORMAL,30));
        label4.setFont(Resources.nsq(Resources.FONT_NORMAL,30));
        window.setLayout(new BorderLayout());
        window.setLayout(new GridLayout(2,2));
        window.add(label1);
        window.add(label3);
        window.add(label2);
        window.add(label4);

        frame.setLocation(500,400);
        frame.setPreferredSize(new Dimension(400,300));
        frame.pack();
        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return (StatGUI) frame;
    }



}

    /*public static void main(String[] args)  {
        StatGUI window = new StatGUI(300,400,"통계",null);
        JLabel label1 = new JLabel("총 정류장 수:"+StopListSet.save()+"   총 노선 수:"+BusListSet.save());;
        window.add(label1);
        window.start();*/


