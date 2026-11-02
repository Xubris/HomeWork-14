package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;
    private int count = 0;

    public SearchEngine(int size) {
        this.items = new Searchable[size];
    }

    public void add(Searchable item) {
        if (count < items.length) {
            items[count] = item;
            count++;
        }
    }

    public Searchable[] search(String query) {
        Searchable[] result = new Searchable[5];
        int found = 0;
        for (Searchable item : items) {
            if (item == null) continue;
            if (item.getSearchTerm().toLowerCase().contains(query.toLowerCase())) {
                result[found] = item;
                found++;
                if (found == 5) break;
            }
        }
        return result;
    }

    public Searchable findBestMatch(String search) throws BestResultNotFound {
        Searchable bestMatch = null;
        int maxCount = 0;

        for (Searchable item : items) {
            if (item == null) continue;

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