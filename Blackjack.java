import java.awt.*;
import java.util.ArrayList;

public class Blackjack {
    
    private Card[] deck;
    private ArrayList<Card> hand = new ArrayList<>();
    private int deckIndex;
    private int total=0;
    private boolean hasAce;

    public Blackjack(){
        total=0;
        deckIndex = 0;
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
        
    }

    public void drawGame(Graphics g){
        int x = 20; int y = 200;
        for(Card c : hand){
            c.drawMe(g, x, y);
            x+=110;
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
    public void start(){
        shuffle();
        hand.clear();
        total=0;
        hit(); hit();
    }
    public int getTotal(){
        return total;
    }
}
