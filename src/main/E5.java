package main;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class E5 {

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
			
		@Override
		public void startDocument(){System.out.println("Comienzo a leer el documento");}
		@Override
		public void endDocument() {System.out.println("Final del documento");}
		@Override
		public void startElement(String uri, String localName, String qName,
		Attributes attributes){
		System.out.println("<"+qName+">"); }
		@Override
		public void endElement(String uri, String localName, String qName){
		System.out.println("</"+qName+">"); }
		@Override
		public void characters(char[] ch, int start, int length){
		String texto = new String(ch,start,length);
		
		//texto = texto.replaceAll("[\t\n]", ""); //Quitar saltos de linea
		if(texto.length()>0){
			for (int i = 0; i<texto.length(); i++) {
				if (texto.charAt(i)!='\t' && texto.charAt(i)!='\n') System.out.print(texto.charAt(i)); 
			}
			System.out.println();
		}; 
		
		}
		}); // FIN DefaultHandler
		// 5. Leer el archivo (Uso del Flujo)
		reader.parse(System.getProperty("user.home") + "\\AD\\peliculas.xml");
		} catch (ParserConfigurationException | SAXException | IOException e) {e.printStackTrace();}}
	
	

	}


