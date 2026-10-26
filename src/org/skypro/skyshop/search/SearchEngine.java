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
            if (item.getSearchTerm().contains(query)) {
                result[found] = item;
                found++;
                if (found == 5) break;
            }
        }
        return result;
    }
}