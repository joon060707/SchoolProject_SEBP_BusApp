package GUI;

import javax.swing.*;
import java.awt.*;

public class Resources {

    // Image
    static final String IMG_BUS1 = "bus1.png";
    static final String IMG_BUS2 = "bus2.png";
    static final String IMG_STOP1 = "stop1.png";
    static final String IMG_STOP2 = "stop2.png";
    static final String IMG_EMPTY = "empty.png";

    // Icon
    // 정상 비율 아이콘: 버튼, 라벨
    public static ImageIcon getBtImage(String imgName, int width) {
        ImageIcon ico = new ImageIcon("src/main/resources/" + imgName); //src/main/resources 하위 폴더
        Image image = ico.getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH);  // 두 값 중 하나가 음수면 원본 비율 유지
        ico.setImage(image);
        return ico;
    }

    // 자유 비율 아이콘: 버튼, 라벨
    public static ImageIcon getBtImage(String imgName, int width, int height) {
        ImageIcon ico = new ImageIcon("src/main/resources/" + imgName); //src/main/resources 하위 폴더
        Image image = ico.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);  // 두 값 중 하나가 음수면 원본 비율 유지
        ico.setImage(image);
        return ico;
    }

    // Image: 창 아이콘
    public static Image getWindowIco(String imgName) {
        return new ImageIcon("src/main/resources/" + imgName).getImage(); //src/main/resources 하위 폴더
    }

    // Color
    static final Color COLOR_SKY = new Color(58, 184, 255);
    static final Color COLOR_GRAY = new Color(176, 176, 176);
    static final Color COLOR_PURPLE = new Color(112, 48, 160);
    static final Color COLOR_BLUE_DARK = new Color(0, 112, 192);
    static final Color COLOR_PINK = new Color(238,150,200);

    static final Color COLOR_RED_BUS = new Color(255, 84, 84);
    static final Color COLOR_YELLOW_BUS = new Color(255, 189, 14);
    static final Color COLOR_GREEN_BUS = new Color(102, 192, 0);
    static final Color COLOR_TOWN_BUS = new Color(164, 255, 61);
    static final Color COLOR_AIR_BUS = new Color(158, 82, 255);


    // Font
    static final String FONT_NanumSq = "나눔스퀘어";
    static final String FONT_NanumSqBold = "나눔스퀘어 Bold";

    static final String FONT_BOLD = "Bold";
    static final String FONT_NORMAL = "Normal";

    static Font nsq(String type, int size) {
        if (type.equals(FONT_BOLD))
            return new Font(FONT_NanumSqBold, Font.PLAIN, size);
        else return new Font(FONT_NanumSq, Font.PLAIN, size);
    }

}
