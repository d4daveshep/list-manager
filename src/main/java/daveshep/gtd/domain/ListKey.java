package daveshep.gtd.domain;

public final class ListKey {
	private final String title;
	private final String subtitle;
	
	public ListKey(String title) throws IllegalArgumentException {
		if (title==null || title.length()<1) {
			throw new IllegalArgumentException("ListKey can't have null or empty title");
		}
		this.title = title;
		this.subtitle = "";
	}
	
	public ListKey(String title, String subtitle) throws IllegalArgumentException {
		if (title==null || title.length()<1) {
			throw new IllegalArgumentException("ListKey can't have null or empty title");
		}
		if (subtitle==null ) {
			throw new IllegalArgumentException("ListKey can't have null subtitle");
		}
		this.title = title;
		this.subtitle = subtitle;
	}
	
	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(title);
		if (subtitle!=null && subtitle.length()>0) {
			sb.append(" | ");
			sb.append(subtitle);
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj==null) {
			return false;
		}
		ListKey lk = (ListKey)obj;
		if (this.getTitle().equalsIgnoreCase(lk.getTitle()) && 
				this.getSubtitle().equalsIgnoreCase(lk.getSubtitle()) ) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	

}
