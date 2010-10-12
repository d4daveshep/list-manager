package daveshep.gtd.domain;

public final class ListKey {
	private final String title;
	private final String subtitle;
	
	public ListKey(String title, String subtitle) {
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
	

}
