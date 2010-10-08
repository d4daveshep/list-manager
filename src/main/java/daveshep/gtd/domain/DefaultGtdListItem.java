package daveshep.gtd.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import daveshep.gtd.util.DateUtils;

public class DefaultGtdListItem implements GtdListItem {

	private String description;
	private Long id;
	private Date tickleDate;
	private Date dueDate;
	private Date completedDate;
	private Date createdDate;
	private Date updatedDate;
	private GtdListItem parent;
	private Set<GtdListItem> children;
	private Set<GtdListItem> dependors; // what items this item depends on
	private Set<GtdListItem> dependents; // what items depend on this item
	private Set<String> tags;
	private boolean star;
	private String realm;
	private String role;
	private Set<String> personPlace;
	private String duration;
	private String energyRequired;
	private String notes;
	
	DefaultGtdListItem() {
		// set default values
	}
	
	DefaultGtdListItem(Long id) {
		this();
		setId(id);
	}
	
	DefaultGtdListItem(String description) {
		this();
		setDescription(description);
	}
	
	DefaultGtdListItem(Long id, String description) {
		this();
		this.setId(id);
		this.setDescription(description);
	}
	
	DefaultGtdListItem(int id, String description) {
		this();
		this.setId(Long.valueOf(id));
		this.setDescription(description);
	}

	DefaultGtdListItem(int id) {
		this.setId(Long.valueOf(id));
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getTickleDate() {
		return tickleDate;
	}
	public void setTickleDate(Date tickleDate) {
		this.tickleDate = tickleDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public GtdListItem getParent() {
		return parent;
	}
	public void setParent(GtdListItem parent) {
		this.parent = parent;
	}
	public Set<GtdListItem> getChildren() {
		return children;
	}
	public void setChildren(Set<GtdListItem> children) {
		this.children = children;
	}
	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	public boolean isStar() {
		return star;
	}
	public void setStar(boolean star) {
		this.star = star;
	}
	public String getRealm() {
		return realm;
	}
	public void setRealm(String realm) {
		this.realm = realm;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Set<String> getPersonPlace() {
		return personPlace;
	}
	public void setPersonPlace(Set<String> personPlace) {
		this.personPlace = personPlace;
	}
	public boolean isDone() {
		if (this.completedDate==null) {
			return false;
		}
		else {
			return true;
		}
	}
	public void setDone(boolean done) {
		if (done==true) {
			this.completedDate = DateUtils.today();
		}
		else {
			this.completedDate = null;
		}
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getEnergyRequired() {
		return energyRequired;
	}
	public void setEnergyRequired(String energyRequired) {
		this.energyRequired = energyRequired;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	@Override
	public boolean equals(Object obj) {
		GtdListItem li = (GtdListItem)obj;
		return this.getId().equals(li.getId());
	}

	@Override
	public int hashCode() {
		return this.getId().intValue();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public boolean isAncestorOf(GtdListItem child) {
		// is this item an ancestor of param child item
		GtdListItem ancestor = child.getParent();
		while (ancestor!=null) {
			if (this.equals(ancestor)) {
				return true;
			}
			ancestor = ancestor.getParent();
		}
		return false;
		
	}

	@Override
	public void addChild(GtdListItem child) {
		if (child==null) {
			throw new IllegalArgumentException("Null child");
		}
		if (child==this) {
			throw new IllegalArgumentException("Can't add self to self");
		}
		if (child.isAncestorOf(this)) {
			throw new IllegalArgumentException("Can't add circular references");
		}
		
		if (child.getParent()!=null) {
			child.getParent().removeChild(child);
		}
		child.setParent(this);
		getChildren().add(child);
	}

	@Override
	public void addTag(String tag) {
		tags.add(tag);
	}

	@Override
	public String getTagsString() {
		StringBuffer tagString = new StringBuffer();
		String[] tagsArray = tags.toArray(new String[0]);
		for (int i=0;i<tagsArray.length;i++) {
			if (i>0) {
				tagString.append(", ");
			}
			tagString.append(tagsArray[i]);
		}
		return tagString.toString();
	}

	@Override
	public boolean hasTag(String tag) {
		return this.tags.contains(tag);
	}

	@Override
	public void removeChild(GtdListItem child) {
		if (child==null) {
			throw new IllegalArgumentException("Null child");
		}
		if (getChildren()==null || getChildren().contains(child)==false) {
			throw new IllegalArgumentException("Not a child of this item");
		}
		child.setParent(null);
		getChildren().remove(child);
	}

	@Override
	public void removeTag(String tag) {
		tags.remove(tag);
	}

	@Override
	public void setTagsString(String tagString) {
		tags.clear();
		StringTokenizer tokens = new StringTokenizer(tagString, ",", false);
		while(tokens.hasMoreElements()) {
			tags.add(tokens.nextToken().trim());
		}
	}

	@Override
	public int compareTo(GtdListItem o) {
		GtdListItem item = (GtdListItem)o;
		
		if (getId()==null) {
			if (item.getId()==null) {
				return 0; // items are equal if neither has an id
			} else {
				return 1; // return 1 since item id is set so is greater then this with no id 
			}
		} else if (item.getId()==null) {
			return -1; // return -1 since this has id set but a item doesn't
			
		} else {
			// both items have a id so compare 
			return getId().compareTo(((GtdListItem)o).getId());
		}
	}

	public Set<GtdListItem> getDependors() {
		// set up the containers if needed
		setupDependencyContainers();
		return dependors;
	}

	public void setDependors(Set<GtdListItem> dependors) {
		this.dependors = dependors;
	}

	@Override
	public void addDependor(GtdListItem dependor) {
		if (dependor==null) {
			throw new IllegalArgumentException("Null dependor");
		}
		if (dependor==this) {
			throw new IllegalArgumentException("Items can't depend on themself");
		}
		// set up the containers if needed
		setupDependencyContainers();
		
		// check for circular dependencies
		if (this.getDependents().contains(dependor) || dependor.getDependors().contains(this)) {
			throw new IllegalArgumentException("Circular dependencies are illegal");
		}
		
		// add the two way relationship ("this" is the dependee)
		if (!this.getDependors().contains(dependor)) {
			this.getDependors().add(dependor);
			dependor.addDependent(this);
		}
		
	}

	@Override
	public boolean isDependentOn(GtdListItem dependor) {
		// set up the containers if needed
		setupDependencyContainers();
		return getDependors().contains(dependor);
	}

	@Override
	public void removeDependor(GtdListItem dependor) {
		if (dependor==null) {
			throw new IllegalArgumentException("Null dependor");
		}
		// set up the containers if needed
		setupDependencyContainers();

		// remove the two way relationship ("this" is the dependent)
		if (getDependors().contains(dependor)) {
			this.getDependors().remove(dependor);
			dependor.removeDependent(this);
		}
	}

	public void setDependents(Set<GtdListItem> dependents) {
		this.dependents = dependents;
	}

	public Set<GtdListItem> getDependents() {
		// set up the containers if needed
		setupDependencyContainers();
		return dependents;
	}

	@Override
	public void addDependent(GtdListItem dependent) {
		if (dependent==null) {
			throw new IllegalArgumentException("Null dependee");
		}
		if (dependent==this) {
			throw new IllegalArgumentException("Items can't depend on themself");
		}

		// set up the containers if needed
		setupDependencyContainers();
		
		// check for circular dependencies
		if (this.getDependors().contains(dependent) || dependent.getDependents().contains(this)) {
			throw new IllegalArgumentException("Circular dependencies are illegal");
		}
		
		// add two way relationship ("this" is the dependor) 
		if (!this.getDependents().contains(dependent)) {
			this.getDependents().add(dependent);
			dependent.addDependor(this);
		}
	}

	void setupDependencyContainers() {
		if (dependents==null) {
			setDependents(new HashSet<GtdListItem>());
		}
		if (dependors==null) {
			setDependors(new HashSet<GtdListItem>());
		}
	}

	@Override
	public boolean hasDependent(GtdListItem dependent) {
		// set up the containers if needed
		setupDependencyContainers();
		return getDependents().contains(dependent);
	}

	@Override
	public void removeDependent(GtdListItem dependent) {
		if (dependent==null) {
			throw new IllegalArgumentException("Null dependee");
		}

		// set up the containers if needed
		setupDependencyContainers();

		// remove two way relationship ("this" is the dependor)
		if (getDependents().contains(dependent)) {
			this.getDependents().remove(dependent);
			dependent.removeDependor(this);
		}
	}

	@Override
	public boolean hasDependents() {
		if (dependents==null || dependents.size()==0) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean hasDependors() {
		if (dependors==null || dependors.size()==0) {
			return false;
		} else {
			return true;
		}
	}


	
}
