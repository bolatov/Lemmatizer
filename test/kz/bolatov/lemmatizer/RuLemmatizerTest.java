package kz.bolatov.lemmatizer;

import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RuLemmatizerTest {

    private Lemmatizer lemmatizer;

    @Before
    public void setUp() throws IOException, XMLStreamException {
        this.lemmatizer = new RuLemmatizer();
    }

    @Test
    public void testGetLemma() {
        assertEquals("зелёный", lemmatizer.getLemma("зелёная"));
    }
}
