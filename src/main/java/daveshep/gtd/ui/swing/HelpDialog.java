package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class HelpDialog extends JDialog {

	public HelpDialog(JFrame frame) {
		super(frame,"Help",true);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBackground(this.getBackground());
		
		textArea.append("Blank screen:\t\tCtrl-B\n\n");
		textArea.append("Delete:\t\tCtrl-D\n\n");
		textArea.append("Edit description:\tF2\n\n");
		textArea.append("Find:\t\tCtrl-F\n");
		textArea.append("Find+Add:\t\tCtrl-Alt-F\n\n");
		textArea.append("Help:\t\tF1, Ctrl-H\n\n");
		textArea.append("Star off:\t\tAlt-*\n");
		textArea.append("Star on:\t\tCtrl-*\n");
		textArea.append("Star toggle:\t\t*\n\n");
		
		JButton closeButton = new JButton("Close");
        ActionListener listener = new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		dispose();
        	}
        };
        closeButton.addActionListener(listener);
        getRootPane().registerKeyboardAction(listener,KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().registerKeyboardAction(listener,KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		getContentPane().add(textArea,BorderLayout.CENTER);
		getContentPane().add(closeButton,BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(frame);
	}
	
}
