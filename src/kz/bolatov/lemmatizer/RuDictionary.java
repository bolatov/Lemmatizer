package kz.bolatov.lemmatizer;

/**
 * Created by almer on 7/15/15.
 */
public class RuDictionary extends Dictionary {

    public void put(String word) {
        if (word == null || word.isEmpty()) {
            throw new IllegalArgumentException("Word must not be null");
        }
        word = Util.cyrillicToAscii(word);
        trie.put(word.toLowerCase(), true);
    }

    public boolean contains(String word) {
        word = Util.cyrillicToAscii(word);
        Object value = trie.get(word);
        return value != null;
    }
}
