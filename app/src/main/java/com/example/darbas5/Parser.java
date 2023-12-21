package com.example.darbas5;

import android.widget.Toast;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Parser {
    public void parse(InputStream inputStream, CurrencyAdapter currencyAdapter) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(inputStream);

            org.w3c.dom.Document document = builder.parse(inputSource);
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("Cube");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                if (element.hasAttribute("currency") && element.hasAttribute("rate")) {
                    String currency = element.getAttribute("currency");
                    String rate = element.getAttribute("rate");

                    Currency newCurrency = new Currency(currency, rate);
                    currencyAdapter.add(newCurrency);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}