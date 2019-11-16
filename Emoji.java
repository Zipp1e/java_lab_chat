package com.company;

import java.util.HashMap;

public class Emoji {
    private Integer id = 0;
    static private HashMap<Integer, String> emojiTable = new HashMap<>();

    Emoji(int n) {
        id = n;
        emojiTable.put(0, ":)");
        emojiTable.put(1, ":(");
        emojiTable.put(2, ":/");
        emojiTable.put(3, ")))");
        emojiTable.put(4, "(((");
        emojiTable.put(5, "///");
        emojiTable.put(6, "0_0");
        emojiTable.put(7, "-_-");
        emojiTable.put(8, "xP");
    }

    public String toString() {
        return emojiTable.get(id);
    }
}
