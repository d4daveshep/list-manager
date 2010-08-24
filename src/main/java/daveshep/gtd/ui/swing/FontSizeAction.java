package daveshep.gtd.ui.swing;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.DefaultListModel;

public class FontSizeAction extends AbstractAction {
	private BasicSwingUI frame;
	
	FontSizeAction(BasicSwingUI frame) {
		super();
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.print("FontSize... ");

		Font font = frame.getItemList().getFont();
		
		// increase font size
		if (event.getActionCommand().equals("=")) {
			frame.getItemList().setFont(new Font(font.getName(),font.getStyle(),font.getSize()+2));
			frame.refreshList();
		}
		if (event.getActionCommand().equals("-")) {
			frame.getItemList().setFont(new Font(font.getName(),font.getStyle(),font.getSize()-2));
			frame.refreshList();
		}
		
		
	}
}
