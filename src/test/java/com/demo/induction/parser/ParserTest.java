package com.demo.induction.parser;

import com.demo.induction.entity.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;

import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
public class ParserTest {

    private Parser<Transaction> csvParser;
    private Parser<Transaction> xmlParser;

    @Before
    public void setup() {
        ClassLoader loader = getClass().getClassLoader();
        InputStream is1 = loader.getResourceAsStream("data.csv");
        csvParser = new CSVParser(is1);

        InputStream is2 = loader.getResourceAsStream("data.xml");
        xmlParser = new XMLParser(is2);
    }

    @Test
    public void shouldReturnListOfSize5WhenParsingCSV() {
        List<Transaction> list = csvParser.parse();
        assertEquals(5, list.size());
    }

    @Test
    public void shouldReturnListOfSize5WhenParsingXML() {
        List<Transaction> list = xmlParser.parse();
        assertEquals(5, list.size());
    }

}