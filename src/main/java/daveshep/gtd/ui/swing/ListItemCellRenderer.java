package daveshep.gtd.ui.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.StringTokenizer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import daveshep.gtd.domain.Goal;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.domain.ListItemType;
import daveshep.gtd.domain.Project;
import daveshep.gtd.domain.Task;
import daveshep.gtd.domain.TaskStatus;
import daveshep.gtd.util.DateUtils;

public class ListItemCellRenderer extends DefaultListCellRenderer {

	private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);

    private static Color PROJECT_COLOR = Color.BLUE;
    private static Color TASK_COLOR = Color.BLACK;
    private static Color NEXT_ACTION_TASK_COLOR = Color.RED;
    private static Color GOAL_COLOR = Color.GREEN;
    private static Color REF_COLOR = Color.MAGENTA;
    
    private int[] tabs = { 12,30,60,100,650,850,1000,1100 };
    private int defaultTab = 20;
	private FontMetrics fontMetrics;
	private Insets insets;
	
	private BasicSwingUI frame;
    
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
				setText(getLabelText(item));
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
	
    private String getLabelText(ListItem item) {
    	
    	StringBuffer output = new StringBuffer();
		if (!item.isDone()) {
			output.append("-");
		} else {
			output.append("+");
		}
		output.append("\t");
		
		if (item.isStarflag()) {
			output.append("*");
		} else {
			output.append(" ");
		}
		output.append("\t");
			
		output.append(item.getTypeCode());
		output.append("\t");

		if (item.hasChildren()) {
			output.append("p");
		} else {
			output.append(" ");
		}
		if (item.getParentItem()!=null) {
			output.append("c");
		} else {
			output.append(" ");
		}
		output.append("\t");
		
		if (frame.getViewSettings().showSubItemsNested && item.getParentItem()!=null) {
			output.append("-> ");
		}
		
		String description = item.getDescription();
		if (description!=null) {
			output.append(description);
		} else {
			output.append(" ");
			
		}
		output.append("\t");
		
		String folder = item.getFolder();
		if (folder != null) {
			output.append(folder);
		} else {
			output.append(" ");
		}
		output.append("\t");
		
		switch (item.getType()) {
		case GOAL:
			output.append(((Goal)item).getStatus());
			output.append("\t\t");
			break;
		case PROJECT:
			output.append(((Project)item).getStatus());
			output.append("\t\t");
			break;
		case TASK:
			output.append(((Task)item).getStatus());
			output.append("\t");
			String context = ((Task)item).getContext();
			if (context !=null && context.length()>0) {
				output.append(context);
			} else {
				output.append(" ");
			}
			output.append("\t");
			break;
		default:
			break;
		}
		
		if (item.getDueDate() != null) {
			output.append(DateUtils.dateFormat.format(item.getDueDate()));
		} else {
			output.append(" ");
		}
	
		output.append("\t");
//		output.append(getId());
//		output.append("\t");

		return output.toString();
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

	public int[] getTabs() {
		return tabs;
	}

	public void setTabs(int[] tabs) {
		this.tabs = tabs;
	}

	public int getDefaultTab() {
		return defaultTab;
	}

	public void setDefaultTab(int defaultTab) {
		this.defaultTab = defaultTab;
	}
	
	public int getTab(int index) {
		if (tabs==null) {
			return defaultTab*index;
		}
		int len = tabs.length;
		if (index>=0 && index<len) {
		  return tabs[index];
		} else {
			return tabs[len-1] +defaultTab*(index-len+1);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color colorRetainer = g.getColor();
		fontMetrics = g.getFontMetrics();
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		getBorder().paintBorder(this, g,0, 0, getWidth(), getHeight());
		g.setColor(getForeground());
		g.setFont(getFont());
		insets = getInsets();
		int x = insets.left;
		int y = insets.top + fontMetrics.getAscent();
		StringTokenizer st = new StringTokenizer(getText(), "\t");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			g.drawString(token, x,y);
			x += fontMetrics.stringWidth(token);
			if (!st.hasMoreTokens())
				break;
			int index = 0;
			while (x >= getTab(index))
				index++;
			x = getTab(index);
		}
		g.setColor(colorRetainer);
	}

	public BasicSwingUI getFrame() {
		return frame;
	}

	public void setFrame(BasicSwingUI frame) {
		this.frame = frame;
	}

}
