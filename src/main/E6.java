package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class E6 {

	public static void main(String[] args) {
		// Lectura XML – SAX (Ejemplo1 – Implementacion sobre la marcha)
		// 1. Instanciar el SAXParserFactory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
		// 2. Crear el SAXParser
		SAXParser parser = factory.newSAXParser();
		// 3. Crear el XMLReader
		XMLReader reader = parser.getXMLReader();
		// 4. Implementar el DefaultHandler Y configurar el XMLReader
		reader.setContentHandler(new DefaultHandler() {
			private boolean item = false;
			private boolean newsTitle = false;
			private boolean newsDate = false;
		@Override
		public void startDocument(){System.out.println("Comienzo a leer el documento");}
		@Override
		public void endDocument() {System.out.println("Final del documento");}
		
		@Override
		public void startElement(String uri, String localName,
		String qName, Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		switch (qName) {
		case "item":
			item = true;
			break;
		case "title":
			newsTitle = true;
			if (item) System.out.print("Titulo : ");
			break;
		case "pubDate":
			newsDate = true;
			if (item) System.out.print("Fecha de Publicación : ");
			break;
		}
		
		}
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
		super.endElement(uri, localName, qName);
		switch (qName) {
		case "item":
			item = false;
			break;
		case "title":
			newsTitle = false;
			
			break;
		case "pubDate":
			newsDate = false;
			
			break;
		}
		}
		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		if (item && (newsTitle || newsDate)) {System.out.println(new String(ch, start, length));}
		}
		}); // FIN DefaultHandler
		// 5. Leer el archivo (Uso del Flujo)
		reader.parse(System.getProperty("user.home") + "\\AD\\rss.aspx.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {e.printStackTrace();}}
	


}
