package kz.bolatov.lemmatizer;

import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class UtilTest {

    @Test(expected = IllegalArgumentException.class)
    public void testReadAffixesNull() throws IOException, XMLStreamException {
        Util.readAffixes(null);
    }

    @Test
    public void testReadAffixesEng() throws IOException, XMLStreamException {
        InputStream stream = getClass().getResourceAsStream("affix_en.xml");
        List<Affix> affixes = Util.readAffixes(stream);
        assertNotNull(affixes);
        assertFalse(affixes.isEmpty());

        // Test first affix
        assertEquals(".", affixes.get(0).getRegex());
        assertEquals("", affixes.get(0).getBase());
        assertEquals("re", affixes.get(0).getEnding());

        // Test last affix
        int last = affixes.size() - 1;
        assertEquals(".", affixes.get(last).getRegex());
        assertEquals("", affixes.get(last).getBase());
        assertEquals("'s", affixes.get(last).getEnding());
    }

    @Test
    public void testReadAffixesRus() throws IOException, XMLStreamException {
        InputStream stream = getClass().getResourceAsStream("affix_ru.xml");
        List<Affix> affixes = Util.readAffixes(stream);
        assertNotNull(affixes);
        assertFalse(affixes.isEmpty());

        // Test first affix
        assertEquals("[^зс]ть", affixes.get(0).getRegex());
        assertEquals("ть", affixes.get(0).getBase());
        assertEquals("л", affixes.get(0).getEnding());

        // Test last affix
        int last = affixes.size() - 1;
        assertEquals("ец", affixes.get(last).getRegex());
        assertEquals("ец", affixes.get(last).getBase());
        assertEquals("це", affixes.get(last).getEnding());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReadDictionaryNull() throws IOException, XMLStreamException {
        Util.readDictionary(null);
    }

    @Test
    public void testReadDictionaryEng() throws IOException {
        InputStream stream = getClass().getResourceAsStream("dict_en.txt");
        List<String> words = Util.readDictionary(stream);
        Dictionary dictionary = new Dictionary();
        dictionary.put(words);

        assertTrue(dictionary.contains("aachen"));
        assertTrue(dictionary.contains("abate"));
    }

    @Test
    public void testReadDictionaryRus() throws IOException {
        InputStream stream = getClass().getResourceAsStream("dict_ru.txt");
        List<String> words = Util.readDictionary(stream);
        Dictionary dictionary = new RuDictionary();
        for (String word : words) {
            dictionary.put(word);
        }

        assertTrue(dictionary.contains("абажурчик"));
        assertTrue(dictionary.contains("абиетин"));
    }

    @Test
    public void testCyrillicToAscii() {
        String c = "абажурчик";
        String a = Util.cyrillicToAscii(c);
        assertEquals(c, Util.asciiToCyrillic(a));
    }
}