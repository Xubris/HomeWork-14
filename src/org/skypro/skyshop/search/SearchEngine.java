package org.skypro.skyshop.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final List<Searchable> items = new LinkedList<>();

    public void add(Searchable item) {
        items.add(item);
    }

    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> result = new TreeMap<>();
        for (Searchable item : items) {
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                result.put(item.getName(), item);
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