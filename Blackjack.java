import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Blackjack {
    
    private Card[] deck;
    private ArrayList<Card> hand = new ArrayList<>();
    private ArrayList<Card> dealerHand = new ArrayList<>();
    private int deckIndex;
    private int total=0, dealerTotal=0;;
    private boolean hasAce, dealerHasAce, stayed;
    private BufferedImage cardback;
    


    public Blackjack(){
        total=0;
        stayed=true;
        deckIndex = 0;
        deck = new Card[52];
        String suit = "";
        for(int i = 0; i<4; i++){
            int start = 13*i;
            switch (i){
                case 0 -> suit="spade";
                case 1 -> suit="heart";
                case 2 -> suit="club";
                case 3 -> suit="diamond";
            }
        deck[start] = new Card("A", 11, suit);
        deck[start+1] = new Card("2", 2, suit);
        deck[start+2] = new Card("3", 3, suit);
        deck[start+3] = new Card("4", 4, suit);
        deck[start+4] = new Card("5", 5, suit);
        deck[start+5] = new Card("6", 6, suit);
        deck[start+6] = new Card("7", 7, suit);
        deck[start+7] = new Card("8", 8, suit);
        deck[start+8] = new Card("9", 9, suit);
        deck[start+9] = new Card("10", 10, suit);
        deck[start+10] = new Card("J", 10, suit);
        deck[start+11] = new Card("Q", 10, suit);
        deck[start+12] = new Card("K", 10, suit);
        }

        try{
            cardback = ImageIO.read(new File("cardback.png"));
        }
        catch(IOException e){
            System.out.println(e);
        }
        
    }

    public void drawGame(Graphics g){
        int cardWidth = 100;
        int cardHeight = 140;

        int x,y;

        g.setColor(new Color(15,33,46)); 
        g.fillRect(267, 200, 700, 300);


        x = (int)(663.5-(0.5*hand.size()*cardWidth));
        System.out.println(x);
        y = 315;
        for(Card c : hand){
            c.drawMe(g, x, y);
            x+=cardWidth+10;
        }
        x = (int)(663.5-(0.5*dealerHand.size()*cardWidth)); 
        y-=(cardHeight+30);
        
        for(Card c : dealerHand){
            c.drawMe(g, x, y);
            x+=cardWidth+10;
        }
        x = (int)(663.5-(0.5*dealerHand.size()*cardWidth)); 
        if(!stayed){
            g.drawImage(cardback,x,y,100,140,null);
        }
        g.drawImage(cardback, 900, 468, 80,112,null);
    }
    public void animateDraw(Graphics g){
        int x = 800;
        int y = 275;
        for(int i = 0; x>700; i++){
            x--;
            if(i%4==0){
                y++;
            }
            g.drawImage(cardback,x,y,50,70,null);
            
        }
        
    
    }
    public void shuffle(){
        for(int i = 0; i < deck.length; i++){
            int swapper = (int)(Math.random()*deck.length);
            Card temp = deck[i];
            deck[i] = deck[swapper];
            deck[swapper] = temp;
        }
    }

    public void hit(){
        Card nextCard = deck[deckIndex];
        hand.add(nextCard);
        if(nextCard.getName().equals("A")){
            hasAce=true;
            if(total+11 > 21){
                total+=1;
                hasAce=false;
            }
            else{
                total+=11;
            }

        }
        else{
            total+=nextCard.getValue();
        }
        if(total>21 && hasAce){
            hasAce=false;
            total-=10;
        }
        deckIndex++;
    }
    public void dealerHit(){
        Card nextCard = deck[deckIndex];
        dealerHand.add(nextCard);
        if(nextCard.getName().equals("A")){
            dealerHasAce=true;
            if(dealerTotal+11 > 21){
                dealerTotal+=1;
                dealerHasAce=false;
            }
            else{
                dealerTotal+=11;
            }

        }
        else{
            dealerTotal+=nextCard.getValue();
        }
        if(dealerTotal>21 && dealerHasAce){
            dealerHasAce=false;
            dealerTotal-=10;
        }
        deckIndex++;
    }
    
    public int start(){
        shuffle();
        hand.clear();
        dealerHand.clear();
        total=0;
        dealerTotal=0;
        hit(); 
        hit();
        if(total==21){
            return 0;
        }
        dealerHit();
        dealerHit();
        return 1;
    }
    public int evaluate(){
        stayed=true;
        if(total>21){
            System.out.println("BUST");
            return 0;
        }
        else{
            while(dealerTotal<=16){
                dealerHit();
                if(dealerTotal>21){
                    System.out.println("DEALER BUST");
                    return 1;
                }
                else if(dealerTotal>16){
                    break;
                }
            }
            if (total>dealerTotal || (hand.size()>=6 && total<=21)){
                System.out.println("WIN");
                return 1;
                
            }
            else if(total<dealerTotal){
                System.out.println("LOSS");
                return 0;
            }
            System.out.println("PUSH");
            return 2;
        }
    }
    public int getTotal(){
        return total;
    }
    public int getDealerTotal(){
        if(!stayed){
            return dealerHand.get(1).getValue();
        }
        return dealerTotal;
    }
    public void setStayed(boolean s){
        stayed = s;
    }
}
