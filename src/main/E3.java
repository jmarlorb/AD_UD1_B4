package main;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class E3 {

	public static void main(String[] args) {
		/// Lectura XML - DOM
		// 1.Instanciar el DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 2.Crear un DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 3.Crear el Document a partir del archivo
			File f = new File(System.getProperty("user.home") + "\\AD", "productosAntiguos.xml");
			Document document = builder.parse(f); // Uso del Flujo
			// 4.Leer la información en el Document.
			Element raiz = document.getDocumentElement(); // Nodo raiz del documento
			recursiveChangeALaVenta(raiz); //Al usar getAttributes(), los nodos atributo se almacenan en un NamedNodeMap, clase que no garantiza que su orden se preserve.
			File f1 = new File(System.getProperty("user.home") + "\\AD", "productosAgotados.xml");
			DOMSource source = new DOMSource(document); // Origen DOM
			StreamResult result = new StreamResult(f1); // Destino Archivo
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result); // Flujo de datos
			System.out.println("Escritura terminada.");

		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void recursiveChangeALaVenta(Element e) {
		if (e.getChildNodes().getLength()>1) {
			NodeList auxiliarList = e.getChildNodes();
			for (int i = 0; i<auxiliarList.getLength(); i++) {
				if (auxiliarList.item(i).getNodeType()==Node.ELEMENT_NODE) {
					if(auxiliarList.item(i).getNodeName().equals("Stock")) {
						int aux = Integer.parseInt(auxiliarList.item(i).getTextContent().strip());
						if (aux<5) auxiliarList.item(i).getParentNode().getAttributes().getNamedItem("aLaVenta").setNodeValue("false");
					} else {
						recursiveChangeALaVenta((Element)auxiliarList.item(i));
					}
				}
			}
		} 
	}
	
	

}
