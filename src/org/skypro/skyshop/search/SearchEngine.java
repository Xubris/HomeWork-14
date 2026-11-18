package org.skypro.skyshop.search;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public Set<Searchable> search(String query) {
        Comparator<Searchable> comparator = (s1, s2) -> {
            int lengthCompare = Integer.compare(s2.getName().length(), s1.getName().length());
            if (lengthCompare == 0) {
                return s1.getName().compareTo(s2.getName());
            }
            return lengthCompare;
        };

        Set<Searchable> result = new TreeSet<>(comparator);
        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable item : items) {
            String searchTerm = item.getSearchTerm().toLowerCase();
            String lowerSearch = search.toLowerCase();
            int currentCount = countSubstring(searchTerm, lowerSearch);

            if (currentCount > maxCount) {
                maxCount = currentCount;
                bestMatch = item;
            }
        }

        if (bestMatch == null) {
            throw new BestResultNotFound(search);
        }
        return bestMatch;
    }

    private int countSubstring(String str, String sub) {
        if (sub == null || sub.isEmpty()) return 0;
        int count = 0;
        int index = 0;
        int subIndex = str.indexOf(sub, index);

        while (subIndex != -1) {
            count++;
            index = subIndex + sub.length();
            subIndex = str.indexOf(sub, index);
        }
        return count;
    }
}