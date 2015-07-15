package kz.bolatov.lemmatizer;

import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class EnLemmatizerTest {

    private Lemmatizer lemmatizer;

    @Before
    public void setUp() throws IOException, XMLStreamException {
        this.lemmatizer = new EnLemmatizer();
    }

    @Test
    public void testGetLemma() {
        assertEquals("walk", lemmatizer.getLemma("walking"));
        assertEquals("participate", lemmatizer.getLemma("participated"));
        assertEquals("stone", lemmatizer.getLemma("stones"));

//        assertEquals("mouse", lemmatizer.getLemma("mice"));
//        assertEquals("finished", lemmatizer.getLemma("finish"));
    }
}
