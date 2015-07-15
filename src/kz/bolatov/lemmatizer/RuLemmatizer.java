package kz.bolatov.lemmatizer;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Lemmatizer for the Russian language
 */
public class RuLemmatizer extends Lemmatizer {

    public RuLemmatizer() throws IOException, XMLStreamException {
        initAffixes();
        initDictionary();
    }

    @Override
    protected void initAffixes() throws IOException, XMLStreamException {
        InputStream stream = getClass().getResourceAsStream("affix_ru.xml");
        this.affixes = Util.readAffixes(stream);
    }

    @Override
    protected void initDictionary() throws IOException {
        InputStream stream = getClass().getResourceAsStream("dict_ru.txt");
        List<String> words = Util.readDictionary(stream);
        this.dictionary = new RuDictionary();
        this.dictionary.put(words);
    }
}
