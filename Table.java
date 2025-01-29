import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;




public class Table extends JPanel implements ActionListener {
	
    private Blackjack bjack = new Blackjack();
	private JButton hitButton,stayButton,startButton;
    private Font standardFont = new Font("Arial",Font.BOLD,20);
    private Font titleFont = new Font("Sans Serif", Font.BOLD, 60);
    private boolean game, round;


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

        startButton = new JButton("PLAY");
    	startButton.setBounds(450, 285, 100, 30); 
        add( startButton ); 
        startButton.addActionListener( this );
        startButton.setVisible(true);
	}

	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(1000,600);
	}

	public void paintComponent(Graphics g){	
        if(!game){
            drawStart(g);
        }
        else{
		super.paintComponent(g);
        bjack.drawGame(g);
        g.setFont(standardFont);
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

	public void actionPerformed(ActionEvent e){
        if( e.getSource() == hitButton ){
            if( game ) { bjack.hit(); }
            if(bjack.getTotal()>21){
                round=false;
                System.out.println("BUST");
            }
        }
        else if( e.getSource() == stayButton ){
            round=false;
        }
        else if(e.getSource()==startButton){
            game=true;
            startButton.setVisible(false);
            hitButton.setVisible(true);
            stayButton.setVisible(true);
        }
    	repaint();

    }


	
	
}