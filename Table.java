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
    private final Color bg, bgDisabled, fg, buttons, buttonsDisabled, play,playDisabled;


	public Table() {
		setLayout(null);
        round=false;
        balance=100;

        bg = new Color(15,33,46);
        bgDisabled = new Color(15,33,46,150);
        fg = new Color(33,55,67);
        buttons = new Color(40,62,75);
        buttonsDisabled = new Color(40,62,75,150);
        play = new Color(1,231,1);
        playDisabled = new Color(1,231,1,150);


		hitButton = new JButton("HIT");
    	hitButton.setBounds(10, 74, 120,40); 
        add( hitButton ); 
        hitButton.addActionListener( this );
        hitButton.setVisible(true);
        hitButton.setEnabled(false);
        hitButton.setContentAreaFilled(false);
        hitButton.setBackground(buttonsDisabled);

        stayButton = new JButton("STAND");
    	stayButton.setBounds(138, 74, 120,40); 
        add( stayButton ); 
        stayButton.addActionListener( this );
        stayButton.setVisible(true);
        stayButton.setEnabled(false);
        stayButton.setContentAreaFilled(false);
        stayButton.setBackground(buttonsDisabled);

        doubleButton = new JButton("DOUBLE");
    	doubleButton.setBounds(10, 128, 120,40); 
        add( doubleButton ); 
        doubleButton.addActionListener( this );
        doubleButton.setVisible(true);
        doubleButton.setEnabled(false);
        doubleButton.setContentAreaFilled(false);
        doubleButton.setBackground(buttonsDisabled);

        betText = new JTextField();
        betText.setBounds(53,28,162,34);
        add(betText);
        betText.setVisible(true);
        betText.setEnabled(true);
        betText.setBackground(bg);

        betButton = new JButton("PLAY");
    	betButton.setBounds(10, 178, 247, 46); 
        add( betButton ); 
        betButton.addActionListener( this );
        betButton.setVisible(true);
        betButton.setEnabled(true);
        betButton.setContentAreaFilled(false);
        betButton.setBackground(play);

	}

    public void disableBtns(){
        hitButton.setEnabled(false);
        hitButton.setBackground(buttonsDisabled);
        stayButton.setEnabled(false);
        stayButton.setBackground(buttonsDisabled);
        doubleButton.setEnabled(false);
        doubleButton.setBackground(buttonsDisabled);
    }
    public void enableBet(){
        betButton.setEnabled(true);
        betButton.setBackground(play);
        betText.setEnabled(true);
        betText.setBackground(bg);
    }
    public void enableBtns(){
        hitButton.setEnabled(true);
        hitButton.setBackground(buttons);
        stayButton.setEnabled(true);
        stayButton.setBackground(buttons);
        doubleButton.setEnabled(true);
        doubleButton.setBackground(buttons);
    }
    public void disableBet(){
        betButton.setEnabled(true);
        betButton.setBackground(playDisabled);
        betText.setEnabled(false);
        betText.setBackground(bgDisabled);
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
        if(bjack.getTotal()>21){
            g.setColor(Color.RED);
        }
        else if(bjack.getTotal()==21){
            g.setColor(Color.green);
        }
        
        g.drawString(bjack.getTotal()+"", 20, 20);
        if(bjack.getDealerTotal()>21){
            g.setColor(Color.RED);
        }
        else if(bjack.getDealerTotal()==21){
            g.setColor(Color.green);
        }
        g.drawString(bjack.getDealerTotal()+"",20,40);
        g.setColor(Color.black);
        g.drawString("$"+balance, 100, 20);
        
	}

    @Override
	public void actionPerformed(ActionEvent e){
        if( e.getSource() == hitButton ){
            if( round ) { 
                bjack.hit(); 
                System.out.println("HIT");
                doubleButton.setEnabled(false);
                doubleButton.setBackground(buttonsDisabled);
                if(bjack.getTotal()>21){
                    round=false;
                    
                }
            }
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
            repaint();
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
                    balance+=Math.ceil(bet*2.5);
                }
            
            }
        }
    	repaint();

    }


	
	
}