import java.awt.*;

public class Blackjack {
    
    private Card[] deck;
    private int handSize=2,total=0;

    public Blackjack(){
        total=0;
        deck = new Card[52];
        String suit = "";
        for(int i = 0; i<4; i++){
            int start = 13*i;
            switch (i){
                case 0: suit="spade"; break;
                case 1: suit="heart"; break;
                case 2: suit="club"; break;
                case 3: suit="diamond"; break;
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
        
        shuffle();
    }

    public void drawGame(Graphics g){
        int x = 20; int y = 200;
        total=0;
        for(int i = 0; i<handSize; i++){
            deck[i].drawMe(g, x, y);
            total+=deck[i].getValue();
            x+=105;
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
        handSize++;
    }
    public int getTotal(){
        return total;
    }
}
