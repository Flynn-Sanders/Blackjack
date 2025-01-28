
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;



public class Card {
    private String name, suit;
    private int value;
    private BufferedImage suitImage;
    private Font cardFont = new Font("Arial", Font.BOLD, 50);


    
    public Card(String nm, int val, String s){
        name=nm; value=val; suit=s;
        
        switch(suit){
            case "heart": 
                try{
                    suitImage = ImageIO.read(new File("heart.png"));
                }
                catch(IOException e){
                    System.out.println(e);
                }
                break;
        }
    }

    public void drawMe(Graphics g, int x, int y){
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 100, 140);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, 100, 140);
        g.setFont(cardFont);
        switch(suit){
            case "spade": g.setColor(Color.black); break;
            case "heart": g.setColor(Color.red); break;
            case "club": g.setColor(Color.black); break;
            case "diamond": g.setColor(Color.red); break;
        }

        g.drawImage(suitImage, x, y,null);
        g.drawString(name, x+45, y+65);
    }

    public int getValue(){
        return value;
    }
}
