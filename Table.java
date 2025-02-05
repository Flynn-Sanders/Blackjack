import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Table extends JPanel implements ActionListener {
	
    private final Blackjack bjack = new Blackjack();
	private final JButton hitButton,stayButton,doubleButton,betButton;
    private final JTextField betText;
    private final Font standardFont = new Font("Arial",Font.BOLD,20);
    private boolean round;
    private int balance,bet;
    private final Color bg, fg, buttons, play;


	public Table() {
		setLayout(null);
        round=false;
        balance=100;

        bg = new Color(15,33,46);
        fg = new Color(33,55,67);
        buttons = new Color(40,62,75);
        play = new Color(1,231,1);


		hitButton = createButton(10, 74, 120,40,"HIT");

        stayButton = createButton(138, 74, 120,40,"STAND");

        doubleButton = createButton(10, 128, 120,40,"DOUBLE");

        betText = new JTextField();
        betText.setBounds(53,28,162,34);
        add(betText);
        betText.setVisible(true);
        betText.setEnabled(true);
        betText.setBackground(bg);
        betText.setForeground(Color.white);

        betButton = createButton(10, 178, 247, 46,"PLAY");
        betButton.setBackground(play);
        betButton.setEnabled(true);

	}

    public JButton createButton(int x, int y, int w, int h,String text){
        JButton btn = new JButton(text);
    	btn.setBounds(x, y, w,h); 
        add( btn ); 
        btn.addActionListener( this );
        btn.setVisible(true);
        btn.setEnabled(false);
        btn.setContentAreaFilled(true);
        btn.setBackground(buttons);
        btn.setForeground(Color.white);
        return btn;
    }

    public void disableBtns(){
        hitButton.setEnabled(false);
        stayButton.setEnabled(false);
        doubleButton.setEnabled(false);
    }
    public void enableBet(){
        betButton.setEnabled(true);
        betText.setEnabled(true);
    }
    public void enableBtns(){
        hitButton.setEnabled(true);
        stayButton.setEnabled(true);
        doubleButton.setEnabled(true);
    }
    public void disableBet(){
        betButton.setEnabled(false);
        betText.setEnabled(false);
    }

    

    @Override
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(1000,600);
	}
    @Override
	public void paintComponent(Graphics g){	
        super.paintComponent(g);
        
        g.setColor(bg);
        g.fillRect(0, 0, 1000, 600);
        g.setColor(fg);
        g.fillRect(0, 0, 267, 600);



        bjack.drawGame(g);
        g.setFont(standardFont);
        g.setColor(Color.BLACK);
        g.drawString("$"+balance, 220, 53);
        g.drawString("AMOUNT:", 90, 22);
        
	}

    @Override
	public void actionPerformed(ActionEvent e){
        if( e.getSource() == hitButton ){
            if( round ) { 
                bjack.hit(); 
                System.out.println("HIT");
                doubleButton.setEnabled(false);
                if(bjack.getTotal()>21){
                    round=false;
                    disableBtns();
                    bjack.evaluate();
                    enableBet();
                    repaint();
                }
            }
            hitButton.revalidate();
            hitButton.repaint();
        }
        else if( e.getSource() == stayButton ){
            disableBtns();
            //lwp
            if(bjack.evaluate()==1){
                balance+=bet*2;
                
            }
            else if(bjack.evaluate()==2){
                balance+=bet;
                
            }
            round=false;
            enableBet();
            stayButton.revalidate();
            stayButton.repaint();
        }
        else if(e.getSource()==doubleButton){
            if(round){
                if(bet>balance){
                    System.out.println("CANNOT DOUBLE - LOW FUNDS");
                }
                else{
                    System.out.println("DOUBLE");
                    balance-=bet;
                    bet*=2;
                    bjack.hit();
                    disableBtns();
                    
                    if(bjack.evaluate()==1){
                        balance+=bet*2;
                        
                    }
                    else if(bjack.evaluate()==2){
                        balance+=bet;
                        
                    }
                    round=false;
                    enableBet();
                    repaint();
                }
            }
            doubleButton.revalidate();
            doubleButton.repaint();
        }
        
        else if(e.getSource()==betButton){
            bet=Integer.parseInt(betText.getText());
            if(bet>balance || bet<=0){
                System.out.println("INVALID AMOUNT");
            }
            else{
                balance-=bet;
                round=true;
                bjack.setStayed(false);
                enableBtns();
                disableBet();

                if(bjack.start()==0){
                    System.out.println("BLACKJACK");
                    round=false;
                    disableBtns();
                    enableBet();
                    repaint();
                    balance+=Math.ceil(bet*2.5);
                }
            
            }
            betButton.revalidate();
            betButton.repaint();
        }
    	repaint();

    }


	
	
}