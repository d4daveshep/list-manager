package daveshep.gtd.util;

import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import daveshep.gtd.domain.ListKey;

public class ListKeyUtils {

	public static SortedMap<String,Set<String>> buildSortedMap(Set<ListKey> listKeys) { 

		SortedMap<String,Set<String>> titles = new TreeMap<String,Set<String>>();
		for (Iterator<ListKey> i=listKeys.iterator();i.hasNext();) {

			ListKey key = i.next();
			String title = key.getTitle();
			String subtitle = key.getSubtitle();

			Set<String> subtitles;
			if (titles.containsKey(title)) {
				subtitles = titles.get(title);
			} else {
				subtitles = new TreeSet<String>();
				titles.put(title, subtitles);
			}

			if (subtitle!=null && subtitle.length()>0) {
				subtitles.add(subtitle);
			}

		}

		return titles;

	}
}