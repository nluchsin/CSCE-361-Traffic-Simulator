package softeng.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class background extends JPanel {

    private Image CityMap;

    public background() {

        initBoard();
    }
    
    private void initBoard() {
        
        loadImage();
        
        int w = CityMap.getWidth(this);
        int h =  CityMap.getHeight(this);
        setPreferredSize(new Dimension(w, h));        
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("src/resources/CityMap7.png");
        CityMap = ii.getImage();        
    }

    @Override
    public void paintComponent(Graphics g) {

        g.drawImage(CityMap, 0, 0, null);
    }
}