package com.demo.induction.parser;

import com.demo.induction.entity.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.demo.induction.entity.DataType.*;


public class XMLParser implements Parser<Transaction> {
    private InputStream mInputStream;

    public XMLParser(InputStream mInputStream) {
        this.mInputStream = mInputStream;
    }


    /**
     * Parses XML files received from {@link XMLParser#mInputStream}
     *
     * @return the list of {@link Transaction} in the file
     */
    @Override
    public List<Transaction> parse() {
        List<Transaction> transactions = new ArrayList<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(mInputStream);
            doc.getDocumentElement().normalize();
            NodeList nodes = doc.getElementsByTagName(TRANSACTION.getXMLNodeName());
            for (int i = 0; i < nodes.getLength(); i++) {
                Node nNode = nodes.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;
                    transactions.add(new Transaction(element.getAttribute(TYPE.getXMLNodeName()),
                            new BigDecimal(element.getAttribute(AMOUNT.getXMLNodeName())),
                            element.getAttribute(NARRATION.getXMLNodeName())));
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
