package daveshep.gtd.ui.swing;

import javax.swing.JComboBox;


/**
 * This class defines a combobox whose values are tied to an Enum
 * Found at http://stuffthathappens.com/blog/2009/02/10/a-swing-jcombobox-for-enums/
 * 
 * @author david
 *
 * @param <E>
 */
public class EnumComboBox<E extends Enum<E>> extends JComboBox {
	private final Class<E> enumClass;

	public EnumComboBox(Class<E> enumClass) {
		this.enumClass = enumClass;

		for (E e : enumClass.getEnumConstants()) {
			addItem(e);
		}
	}

	public E getSelectedItem() {
		return enumClass.cast(super.getSelectedItem());
	}
}
