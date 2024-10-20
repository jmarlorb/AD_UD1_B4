package main;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import suplementarias.Ingrediente;
import suplementarias.Receta;

public class E4 {

	public static void main(String[] args) {
		Ingrediente i1 = new Ingrediente("Pollo","750gr");
		Ingrediente i2 = new Ingrediente("Especias","Una pizca");
		ArrayList<Ingrediente> ingredientes= new ArrayList<Ingrediente>();
		ingredientes.add(i1);
		ingredientes.add(i2);
		Receta r = new Receta("Pollo al chilindrón",ingredientes, "Asar el pollo y echarle especias","30 min");
		//Escribir XML - DOM
		//1.Instanciar el DocumentBuilderFactory
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
		//2.Crear un DocumentBuilder
		DocumentBuilder builder = factory.newDocumentBuilder();
		//3.Crear un DOMImplementation
		DOMImplementation implementation = builder.getDOMImplementation();
		//4.Crear el Document vacio
		Document document = implementation.createDocument(null, "Receta", null);
		//5.Escribir la información en el Document
		document.setXmlVersion("1.0");
		document.setXmlStandalone(true);
		Element raiz = document.getDocumentElement(); //Nodo raiz del documento
		annadeElemento(document,raiz,"titulo",r.getTitulo());
		Element ingres = document.createElement("ingredientes");
		for (Ingrediente i : r.getIngredientes()) {
			Element e = annadeElemento(document,ingres,"ingrediente",i.getNombreIngrediente());
			e.setAttribute("cantidad", i.getCantidad());
		}
		raiz.appendChild(ingres);
		annadeElemento(document, raiz, "procedimiento", r.getProcedimiento());
		annadeElemento(document, raiz, "tiempo", r.getTiempo());
		//6.Transformar el DOM en un archivo (Guardar)
		File f = new File(System.getProperty("user.home") + "\\AD", "miReceta.xml");
		
		DOMSource source = new DOMSource(document); //Origen DOM
		StreamResult result = new StreamResult(f); //Destino Archivo
		Transformer transformer= TransformerFactory.newInstance().newTransformer();
		transformer.transform(source, result); //Flujo de datos
		} catch (ParserConfigurationException
		| TransformerException
		| TransformerFactoryConfigurationError e) { e.printStackTrace(); }

	}

	private static Element annadeElemento(Document doc, Element raiz, String clave, String valor) {
		
		Element e = doc.createElement(clave); 
		
		anadeNodoTextual(doc, e, valor); 
		
		raiz.appendChild(e);
		return e;
		}
	private static Element anadeNodoTextual(Document doc, Element raiz, String valor) {
		
		raiz.appendChild(doc.createTextNode(valor)); 
		return raiz;
		}
}
