package daveshep.gtd;

import java.util.HashSet;
import java.util.List;
//import java.util.Set;
import org.hibernate.Session;
import daveshep.gtd.domain.ListItem;
import daveshep.gtd.util.HibernateUtil;

public class ListManager {
	
//	private Set items;
	
	public ListManager() {
//		items = new HashSet();
	}
	
	public static void main( String args[] ) {
		System.out.println( "GTD List Manager" );
	}
	
	public List getItems() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        List result = session.createQuery("from ListItem").list();
        session.getTransaction().commit();

		return result;
	}
/*
	public void setItems(Set items) {
		this.items = items;
	}
*/
	public void addListItem( ListItem item ) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        session.save(item);
        session.getTransaction().commit();

//		items.add(item);
	}
	
	// TODO: add factory methods for creating list items (set manager in constructor)
}