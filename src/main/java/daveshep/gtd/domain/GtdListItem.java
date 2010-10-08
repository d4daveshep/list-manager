package daveshep.gtd.domain;

import java.util.Date;
import java.util.Set;

public interface GtdListItem extends Comparable<GtdListItem> {
	public String getDescription();
	public void setDescription(String description);
	
	public Long getId();
	public void setId(Long id);
	
	public Date getTickleDate();
	public void setTickleDate(Date tickleDate);
	
	public Date getDueDate();
	public void setDueDate(Date dueDate);
	
	public Date getCompletedDate();
	public void setCompletedDate(Date completedDate);
	
	public Date getCreatedDate();
	public void setCreatedDate(Date createdDate);
	
	public Date getUpdatedDate();
	public void setUpdatedDate(Date updatedDate);
	
	public GtdListItem getParent();
	public void setParent(GtdListItem parent);
	
	public Set<GtdListItem> getChildren();
	public void setChildren(Set<GtdListItem> children);
	
	public Set<GtdListItem> getDependors();
	public void setDependors(Set<GtdListItem> dependsOn);
	public void addDependor(GtdListItem dependor);
	public void removeDependor(GtdListItem dependor);
	/**
	 * @param dependor
	 * @return true if this item is dependent on the dependor, otherwise return false
	 */
	public boolean isDependentOn(GtdListItem dependor);
	
	
	public boolean isStar();
	public void setStar(boolean star);
	
	public String getRealm();
	public void setRealm(String realm);
	
	public String getRole();
	public void setRole(String role);
	
	public Set<String> getPersonPlace();
	public void setPersonPlace(Set<String> personPlace);
	
	public boolean isDone();
	public void setDone(boolean done);
	
	public String getDuration();
	public void setDuration(String duration);
	
	public String getEnergyRequired();
	public void setEnergyRequired(String energyRequired);
	
	public void setNotes(String notes);
	public String getNotes();
	
	public void addChild(GtdListItem child);
	public void removeChild(GtdListItem child);
	public boolean isAncestorOf(GtdListItem child);
	
	public Set<String> getTags();
	public void setTags(Set<String> tags);
	public void addTag(String tag);
	public String getTagsString();
	public void setTagsString(String tagString);
	public boolean hasTag(String tag);
	public void removeTag(String tag);

	public void setDependents(Set<GtdListItem> dependents);
	public Set<GtdListItem> getDependents();
	public void addDependent(GtdListItem dependee);
	public void removeDependent(GtdListItem dependee);
	public boolean hasDependent(GtdListItem dependee);
	public boolean hasDependors();
	public boolean hasDependents();
	
}