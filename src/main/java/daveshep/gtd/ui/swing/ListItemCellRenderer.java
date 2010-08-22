package daveshep.gtd.ui.swing;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;
import daveshep.gtd.domain.Task;
import daveshep.gtd.domain.TaskStatus;

public class ListItemCellRenderer extends DefaultListCellRenderer {

	private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private static Color PROJECT_COLOR = Color.BLUE;
    private static Color TASK_COLOR = Color.BLACK;
    private static Color NEXT_ACTION_TASK_COLOR = Color.RED;
    private static Color GOAL_COLOR = Color.GREEN;
    private static Color REF_COLOR = Color.MAGENTA;
    
    
	@Override
	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus)
	{
		setComponentOrientation(list.getComponentOrientation());

		Color bg = null;
		Color fg = null;

//		JList.DropLocation dropLocation = list.getDropLocation();
//		if (dropLocation != null
//				&& !dropLocation.isInsert()
//				&& dropLocation.getIndex() == index) {
//
//			bg = UIManager.getColor("List.dropCellBackground");
//			fg = UIManager.getColor("List.dropCellForeground");
//
//			isSelected = true;
//		}

		ListItem item = null;
		
		if (!(value instanceof ListItem)) {
			item = new ListItem("invalid item!");
		} else {
			item = (ListItem) value;
		}
		
		if (isSelected) {
			setBackground(bg == null ? list.getSelectionBackground() : bg);
			setForeground(fg == null ? list.getSelectionForeground() : fg);
		}
		else {
			setBackground(list.getBackground());
			
			if (item.getType() == ListItemType.PROJECT) {
				fg = PROJECT_COLOR;
			} else if (item.getType() == ListItemType.TASK) {
				if (((Task)item).getStatus() == TaskStatus.NEXT_ACTION ) {
					fg = NEXT_ACTION_TASK_COLOR;
				} else {
					fg = TASK_COLOR;
				}
			} else if (item.getType() == ListItemType.GOAL) {
				fg = GOAL_COLOR;
			} else if (item.getType() == ListItemType.REFERENCE) {
				fg = REF_COLOR;
			} else {
				fg = list.getForeground();
			}
			setForeground(fg);
		}

		if (value instanceof Icon) {
			setIcon((Icon)value);
			setText("");
		}
		else {
			setIcon(null);
			if (value == null) {
				setText("<null>");
			} else {
				if (item.getParentItem()!=null) {
					setText("-> " + item.toString());
				} else {
					setText(item.toString());
				}
			}
		}

		setEnabled(list.isEnabled());
		setFont(list.getFont());

		Border border = null;
		if (cellHasFocus) {
			if (isSelected) {
				border = UIManager.getBorder("List.focusSelectedCellHighlightBorder");
			}
			if (border == null) {
				border = UIManager.getBorder("List.focusCellHighlightBorder");
			}
		} else {
			border = getNoFocusBorder();
		}
		setBorder(border);

		return this;
	}
	
    private Border getNoFocusBorder() {
    	Border border = UIManager.getBorder("List.cellNoFocusBorder");
        if (System.getSecurityManager() != null) {
            if (border != null) return border;
            return SAFE_NO_FOCUS_BORDER;
        } else {
            if (border != null &&
                    (noFocusBorder == null ||
                    noFocusBorder == DEFAULT_NO_FOCUS_BORDER)) {
                return border;
            }
            return noFocusBorder;
        }
    }


}
