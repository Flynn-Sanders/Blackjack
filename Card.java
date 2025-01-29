
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
        try{
            suitImage = ImageIO.read(new File(suit+".png"));
        }
        catch(IOException e){
            System.out.println(e);
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
        g.drawImage(suitImage, x+5, y+5, 35, 35, null);
        if(value==10){
            g.drawString(name, x+20, y+90);
        } else{
            g.drawString(name, x+35, y+90);
        }
    }

    public int getValue(){
        return value;
    }
}
