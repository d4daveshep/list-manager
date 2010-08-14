package daveshep.gtd.ui.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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

public class FilterDialog extends JDialog implements ActionListener {

	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	public FilterDialog(JFrame frame) {
		super(frame,"Filter",true);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(5,5,5,5));

		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);

        okButton.addActionListener(this);
        cancelButton.addActionListener(this);
        
        
        // TODO use input maps instead
        getRootPane().registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        getRootPane().registerKeyboardAction(this,KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),JComponent.WHEN_IN_FOCUSED_WINDOW);
        panel.add(buttonPanel,BorderLayout.SOUTH);
		
		getContentPane().add(panel,BorderLayout.CENTER);
		
		pack();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println(event.toString());
		String actionCommand = event.getActionCommand();
		if (actionCommand!= null && actionCommand.equals("Cancel")) {
			System.out.print("cancel... ");
			setVisible(false);
		}
		if (event.getID()==KeyEvent.VK_ESCAPE) {
			System.out.print("cancel... ");
			setVisible(false);
			
		}
	}
	
}
