package com.greatwhite.pickaflick.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    static {
        // Add 3 sample items.
//        addItem(new DummyItem("1", "Kangaroo Jack", "http://www.imdb.com/title/tt0257568/?ref_=fn_al_tt_1"));
//        addItem(new DummyItem("2", "Nexium", "http://www.drugs.com/nexium.html"));
//        addItem(new DummyItem("3", "Plavix", "http://www.drugs.com/plavix.html"));

        //code to add movies to the list

    }

    public static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public String id;
        public String movieTitle;
        public String url;

        public DummyItem(String id, String movieTitle, String url) {
            this.id = id;
            this.movieTitle = movieTitle;
            this.url = url;
        }

        @Override
        public String toString() {
            return movieTitle;
        }
    }
}
