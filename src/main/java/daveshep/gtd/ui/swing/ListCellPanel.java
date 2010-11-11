package daveshep.gtd.ui.swing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.domain.GtdListItem;

public class ListCellPanel extends JPanel {

	private GtdListItem listItem;

	public ListCellPanel() {
		super();
	}
	
	public ListCellPanel(GtdListItem listItem) {
		super();
		this.listItem = listItem;
		
		setLayout(new GridLayout(1,2));
		setBorder(new EmptyBorder(5,5,5,5));

		JLabel description = new JLabel(listItem.getDescription());
		description.setMinimumSize(new Dimension(500,description.getPreferredSize().height));
		JLabel owningList = new JLabel(listItem.getOwningList().getName());
		
		
		add(description);
		add(owningList);
		
		validate();
		
	}
	
}
