package daveshep.gtd.ui.swing;

import java.awt.event.KeyEvent;
import java.util.logging.Logger;

import javax.swing.JComboBox;

public class MyJComboBox extends JComboBox {
	private static Logger logger = Logger.getLogger("daveshep.gtd");
	
	@Override
	public void processKeyEvent(KeyEvent e) {
		logger.info(e.paramString());
		if((e.getKeyCode() == KeyEvent.VK_ESCAPE) && !super.isPopupVisible()){
			return; //don't process the event
		}
		super.processKeyEvent(e);
	}

}
