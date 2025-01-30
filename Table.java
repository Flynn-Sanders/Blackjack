import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;




public class Table extends JPanel implements ActionListener {
	
    private final Blackjack bjack = new Blackjack();
	private final JButton hitButton,stayButton,startButton,betButton;
    private final JTextField betText;
    private final Font standardFont = new Font("Arial",Font.BOLD,20);
    private final Font titleFont = new Font("Sans Serif", Font.BOLD, 60);
    private boolean game, round;
    private int balance,bet;


	public Table() {
		setLayout(null);
        game=false;
        round=false;

		hitButton = new JButton("HIT");
    	hitButton.setBounds(50, 500, 100, 30); 
        add( hitButton ); 
        hitButton.addActionListener( this );
        hitButton.setVisible(false);

        stayButton = new JButton("STAY");
    	stayButton.setBounds(200, 500, 100, 30); 
        add( stayButton ); 
        stayButton.addActionListener( this );
        stayButton.setVisible(false);

        betText = new JTextField();
        betText.setBounds(600,500,100,30);
        add(betText);
        betText.setVisible(false);

        betButton = new JButton("PLAY");
    	betButton.setBounds(600, 540, 100, 30); 
        add( betButton ); 
        betButton.addActionListener( this );
        betButton.setVisible(false);

        startButton = new JButton("PLAY");
    	startButton.setBounds(450, 285, 100, 30); 
        add( startButton ); 
        startButton.addActionListener( this );
        startButton.setVisible(true);
	}

    @Override
	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(1000,600);
	}

	public void paintComponent(Graphics g){	
        super.paintComponent(g);
        if(!game){
            drawStart(g);
        }
        else{
		
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
        }
	}

    public void drawStart(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(0, 0, 1000, 600);
        g.setFont(titleFont);
        g.setColor(Color.BLACK);
        g.drawString("BLACKJACK", 300, 200);
    }

    @Override
	public void actionPerformed(ActionEvent e){
        if( e.getSource() == hitButton ){
            if( round ) { 
                bjack.hit(); 
                System.out.println("HIT");
                if(bjack.getTotal()>21){
                    round=false;
                    System.out.println("BUST");
                }
            }
        }
        else if( e.getSource() == stayButton ){
            round=false;
            betText.setVisible(true);
            betButton.setVisible(true);
            hitButton.setVisible(false);
            stayButton.setVisible(false);
        }
        else if(e.getSource()==startButton){
            balance=100;
            game=true;
            startButton.setVisible(false);
            betText.setVisible(true);
            betButton.setVisible(true);
        }
        else if(e.getSource()==betButton){
            

            bet=Integer.parseInt(betText.getText());
            if(bet>balance || bet<=0){
                System.out.println("INVALID AMOUNT");
            }
            else{
                round=true;
            hitButton.setVisible(true);
            stayButton.setVisible(true);
            betText.setVisible(false);
            betButton.setVisible(false);

            bjack.start();
            }
        }
    	repaint();

    }


	
	
}