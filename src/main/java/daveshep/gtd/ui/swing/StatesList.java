package daveshep.gtd.ui.swing;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class StatesList
	extends JFrame {

	protected JList m_statesList;

	protected JRadioButton m_verticalRb;
	protected JRadioButton m_verticalWrapRb;
	protected JRadioButton m_horizontalWrapRb;

      protected JRadioButton m_longRb;
      protected JRadioButton m_shortRb;  

	public static ArrayModel LONG_MODEL =
		new ArrayModel(new String[] {
		"AK\tAlaska\tJuneau",
		"AL\tAlabama\tMontgomery",
		"AR\tArkansas\tLittle Rock",
		"AZ\tArizona\tPhoenix",
		"CA\tCalifornia\tSacramento",
		"CO\tColorado\tDenver",
		"CT\tConnecticut\tHartford",
		"DE\tDelaware\tDover",
		"FL\tFlorida\tTallahassee",
		"GA\tGeorgia\tAtlanta",
		"HI\tHawaii\tHonolulu",
		"IA\tIowa\tDes Moines",
		"ID\tIdaho\tBoise",
		"IL\tIllinois\tSpringfield",
		"IN\tIndiana\tIndianapolis",
		"KS\tKansas\tTopeka",
		"KY\tKentucky\tFrankfort",
		"LA\tLouisiana\tBaton Rouge",
		"MA\tMassachusetts\tBoston",
		"MD\tMaryland\tAnnapolis",
		"ME\tMaine\tAugusta",
		"MI\tMichigan\tLansing",
		"MN\tMinnesota\tSt.Paul",
		"MO\tMissouri\tJefferson City",
		"MS\tMississippi\tJackson",
		"MT\tMontana\tHelena",
		"NC\tNorth Carolina\tRaleigh",
		"ND\tNorth Dakota\tBismarck",
		"NE\tNebraska\tLincoln",
		"NH\tNew Hampshire\tConcord",
		"NJ\tNew Jersey\tTrenton",
		"NM\tNew Mexico\tSantaFe",
		"NV\tNevada\tCarson City",
		"NY\tNew York\tAlbany",
		"OH\tOhio\tColumbus",
		"OK\tOklahoma\tOklahoma City",
		"OR\tOregon\tSalem",
		"PA\tPennsylvania\tHarrisburg",
		"RI\tRhode Island\tProvidence",
		"SC\tSouth Carolina\tColumbia",
		"SD\tSouth Dakota\tPierre",
		"TN\tTennessee\tNashville",
		"TX\tTexas\tAustin",
		"UT\tUtah\tSalt Lake City",
		"VA\tVirginia\tRichmond",
		"VT\tVermont\tMontpelier",
		"WA\tWashington\tOlympia",
		"WV\tWest Virginia\tCharleston",
		"WI\tWisconsin\tMadison",
		"WY\tWyoming\tCheyenne"
	});

	public static ArrayModel SHORT_MODEL =
		new ArrayModel(new String[] {
		"AK", "AL", "AR", "AZ", "CA",
		"CO", "CT", "DE", "FL", "GA",
		"HI", "IA", "ID", "IL", "IN",
		"KS", "KY", "LA", "MA", "MD",
		"ME", "MI", "MN", "MO", "MS",
		"MT", "NC", "ND", "NE", "NH",
		"NJ", "NM", "NV", "NY", "OH",
		"OK", "OR", "PA", "RI", "SC",
		"SD", "TN", "TX", "UT", "VA",
		"VT", "WA", "WV", "WI", "WY"
	});

	public StatesList() {
		super("States List");
		setSize(450, 250);

		m_statesList = new JList();
		m_statesList.setModel(LONG_MODEL);

		TabListCellRenderer renderer = new TabListCellRenderer();
		renderer.setTabs(new int[] {50, 200, 300});
		m_statesList.setCellRenderer(renderer);
		m_statesList.addKeyListener(new ListSearcher(m_statesList)); // NEW

		JScrollPane ps = new JScrollPane();
		ps.getViewport().add(m_statesList);
		getContentPane().add(ps, BorderLayout.CENTER);

		JPanel pp = new JPanel(new GridLayout(2,3));	

		ButtonGroup bg1 = new ButtonGroup();
		m_verticalRb = new JRadioButton("VERTICAL", true);
		pp.add(m_verticalRb);
		bg1.add(m_verticalRb);
		m_verticalWrapRb = new JRadioButton("VERTICAL_WRAP");
		pp.add(m_verticalWrapRb);
		bg1.add(m_verticalWrapRb);
		m_horizontalWrapRb = new JRadioButton("HORIZONTAL_WRAP");
		pp.add(m_horizontalWrapRb);
		bg1.add(m_horizontalWrapRb);

		ButtonGroup bg2 = new ButtonGroup();
		m_longRb = new JRadioButton("Long Model", true);
		pp.add(m_longRb);
		bg2.add(m_longRb);
		m_shortRb = new JRadioButton("Short Model");
		pp.add(m_shortRb);
		bg2.add(m_shortRb);

                getContentPane().add(pp, BorderLayout.NORTH);

		ActionListener modelListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (m_longRb.isSelected()) {
					m_statesList.setPrototypeCellValue(
						"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
					m_statesList.setModel(LONG_MODEL);
				}
				else if (m_shortRb.isSelected()) {
					m_statesList.setPrototypeCellValue(
						"xxxx");
					m_statesList.setModel(SHORT_MODEL);
				}
			}
		};
		m_longRb.addActionListener(modelListener);
		m_shortRb.addActionListener(modelListener);

		ActionListener layoutListener = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if (m_verticalRb.isSelected()) {
					m_statesList.setLayoutOrientation(JList.VERTICAL);
				}
				else if (m_verticalWrapRb.isSelected()) {
					m_statesList.setLayoutOrientation(JList.VERTICAL_WRAP);
				}
				else if (m_horizontalWrapRb.isSelected()) {
					m_statesList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
				}
			}
		};
		m_verticalRb.addActionListener(layoutListener);
		m_verticalWrapRb.addActionListener(layoutListener);
		m_horizontalWrapRb.addActionListener(layoutListener);
	}

	public static void main(String argv[]) {
		StatesList frame = new StatesList();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class TabListCellRenderer
	extends    JLabel
	implements ListCellRenderer {

    protected static Border m_noFocusBorder;
	protected FontMetrics m_fm = null;
    protected Insets m_insets = new Insets(0, 0, 0, 0);

	protected int m_defaultTab = 50;
	protected int[] m_tabs = null;

	public TabListCellRenderer() {
		m_noFocusBorder = new EmptyBorder(1, 1, 1, 1);
		setOpaque(true);
		setBorder(m_noFocusBorder);
	}

	public Component getListCellRendererComponent(JList list,
		Object value, int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.toString());

		setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
		setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());

		setFont(list.getFont());
		setBorder((cellHasFocus) ? UIManager.getBorder("List.focusCellHighlightBorder") : m_noFocusBorder);

		return this;
	}

	public void setDefaultTab(int defaultTab) {
		m_defaultTab = defaultTab;
	}

	public int getDefaultTab() {
		return m_defaultTab;
	}

	public void setTabs(int[] tabs) {
		m_tabs = tabs;
	}

	public int[] getTabs() {
		return m_tabs;
	}

	public int getTab(int index) {
		if (m_tabs == null)
			return m_defaultTab*index;

		int len = m_tabs.length;
		if (index>=0 && index<len)
			return m_tabs[index];

		return m_tabs[len-1] + m_defaultTab*(index-len+1);
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Color colorRetainer = g.getColor();
		m_fm = g.getFontMetrics();

		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		getBorder().paintBorder(this, g, 0, 0, getWidth(), getHeight());

		g.setColor(getForeground());
		g.setFont(getFont());
		m_insets = getInsets();
		int x = m_insets.left;
		int y = m_insets.top + m_fm.getAscent();

		StringTokenizer	st = new StringTokenizer(getText(), "\t");
		while (st.hasMoreTokens()) {
			String sNext = st.nextToken();
			g.drawString(sNext, x, y);
			x += m_fm.stringWidth(sNext);

			if (!st.hasMoreTokens())
				break;
			int index = 0;
			while (x >= getTab(index))
				index++;
			x = getTab(index);
		}

		g.setColor(colorRetainer);
	}

}

class ArrayModel extends AbstractListModel {
	Object[] m_data;

	public ArrayModel(Object[] data) {
		m_data = data;
	}

	public int getSize() {
		return m_data.length;
	}

	public Object getElementAt(int index) {
		if (index < 0 || index >= getSize())
			return null;
		return m_data[index];
	}
}

// NEW
class ListSearcher extends KeyAdapter
{
  protected JList m_list;
  protected ListModel m_model;
  protected String m_key = "";
  protected long m_time = 0;
    
  public static int CHAR_DELTA = 1000;

  public ListSearcher(JList list) {
    m_list = list;
    m_model = m_list.getModel();
  }

  public void keyTyped(KeyEvent e) {
    char ch = e.getKeyChar();
    if (!Character.isLetterOrDigit(ch))
      return;
    if (m_time+CHAR_DELTA < System.currentTimeMillis())
      m_key = "";
    m_time = System.currentTimeMillis();
    m_key += Character.toLowerCase(ch);
    for (int k=0; k<m_model.getSize(); k++) {
      String str = ((String)m_model.getElementAt(k)).toLowerCase();
      if (str.startsWith(m_key)){
        m_list.setSelectedIndex(k);
        m_list.ensureIndexIsVisible(k);
        break;
      }
    }
  }
}
