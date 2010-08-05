package daveshep.gtd.ui.swing;

import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CommandPanel extends JPanel implements ActionListener, KeyListener {

	private JFrame frame; // parent frame
	JTextField commandTextField = new JTextField("type here", 40);
	private JButton commandButton = new JButton("Go");
	private JLabel commandLabel = new JLabel("<none>");

	CommandPanel(JFrame frame) {
		super();
		this.frame = frame;
		
//		setLayout(new BorderLayout());

		add(new JLabel("Command: "));
		add(commandLabel);
		add(new JLabel("Action: "));
		add(commandTextField);
		add(commandButton);
		
		commandButton.addActionListener(this);
		commandTextField.addKeyListener(this);
	
		commandTextField.setFocusable(true);
		
		
		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// System.out.println(event.toString());
		if (event.getActionCommand().equals(commandButton.getActionCommand())) {
			System.out.println("Go");
		}
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		 System.out.println(event.toString());
		 
	}

	@Override
	public void keyPressed(KeyEvent event) {
//		 System.out.println(event.toString());		
	}

	@Override
	public void keyReleased(KeyEvent event) {
//		 System.out.println(event.toString());		
	}
	
}
