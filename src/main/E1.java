package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class E1 {

	public static void main(String[] args) {
		// Lectura XML - DOM
		// 1.Instanciar el DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 2.Crear un DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 3.Crear el Document a partir del archivo
			File f = new File(System.getProperty("user.home") + "\\AD", "peliculas.xml");
			Document document = builder.parse(f); // Uso del Flujo
			// 4.Leer la información en el Document.
			Element raiz = document.getDocumentElement(); // Nodo raiz del documento
			recursivePrint(raiz,"");
//			NodeList catalogoPeliculas = raiz.getChildNodes();
//			for (int k = 0; k < catalogoPeliculas.getLength(); k++) {
//				Node nAux = catalogoPeliculas.item(k);
//				if (nAux.getNodeType() == Node.ELEMENT_NODE) {
//					NodeList listaPeliculas = nAux.getChildNodes();
//					for (int i = 0; i < listaPeliculas.getLength(); i++) {
//						Node nodoAux = listaPeliculas.item(i);
//
//						if (nodoAux.getNodeType() == Node.ELEMENT_NODE) {
//
//							NodeList peliculaAtributos = nodoAux.getChildNodes();
//							if (peliculaAtributos.getLength() <= 3)
//								System.out.println(nodoAux.getNodeName() + " : " + nodoAux.getTextContent());
//							else
//								System.out.println(nodoAux.getNodeName());
//							for (int j = 0; j < peliculaAtributos.getLength(); j++) {
//								Node nodoPeliculaAux = peliculaAtributos.item(j);
//								if (nodoPeliculaAux.getNodeType() == Node.ELEMENT_NODE) {
//									System.out.println("\t" + nodoPeliculaAux.getNodeName() + " : "
//											+ nodoPeliculaAux.getTextContent());
//								}
//							}
//						}
//					}
//				}
//				System.out.println();
//			}

		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}

	}
	
	private static void recursivePrint(Element e, String tab) {
		if (e.getChildNodes().getLength()>1) {
			NodeList auxiliarList = e.getChildNodes();
			System.out.println(tab+e.getTagName()+" : ");
			for (int i = 0; i<auxiliarList.getLength(); i++) {
				if (auxiliarList.item(i).getNodeType()==Node.ELEMENT_NODE) {
					recursivePrint((Element)auxiliarList.item(i),tab+"\t");
				}
			}
		} else {
			System.out.println(tab+e.getTagName()+" : "+e.getTextContent());
		}
		
	}

}
