import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;




public class Table extends JPanel implements ActionListener {
	
    private Blackjack game = new Blackjack();
	private JButton hitButton,stayButton;
    private Font standardFont = new Font("Arial",Font.BOLD,20);


	public Table() {
		setLayout(null);


		hitButton = new JButton("HIT");
    	hitButton.setBounds(50, 500, 100, 30); 
        add( hitButton ); 
        hitButton.addActionListener( this );

        stayButton = new JButton("STAY");
    	stayButton.setBounds(50, 500, 100, 30); 
        add( stayButton ); 
        stayButton.addActionListener( this );


	}

	public Dimension getPreferredSize() {
		//Sets the size of the panel
		return new Dimension(1000,600);
	}


	public void paintComponent(Graphics g){	
		super.paintComponent(g);
        game.drawGame(g);
        g.setFont(standardFont);
        g.drawString(game.getTotal()+"", 20, 20);
	}

	public void actionPerformed(ActionEvent e){
        if( e.getSource() == hitButton ){
            game.hit();
        }
        else if( e.getSource() == stayButton ){
 
        }
    	repaint();

    }


	
	
}