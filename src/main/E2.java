package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import suplementarias.Pelicula;

public class E2 {

	public static void main(String[] args) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			// 2.Crear un DocumentBuilder
			ArrayList<Pelicula> pelis = new ArrayList<Pelicula>();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// 3.Crear el Document a partir del archivo
			File f = new File(System.getProperty("user.home") + "\\AD", "peliculas.xml");
			Document document = builder.parse(f); // Uso del Flujo
			// 4.Leer la información en el Document.
			Element raiz = document.getDocumentElement(); // Nodo raiz del documento
			NodeList catalogoPeliculas = raiz.getChildNodes();
			for (int k = 0; k < catalogoPeliculas.getLength(); k++) {
				Node nAux = catalogoPeliculas.item(k);
				if (nAux.getNodeType() == Node.ELEMENT_NODE) {
					
					Pelicula p = new Pelicula();
					Element pelicula = (Element) nAux;
					Node helper = pelicula.getElementsByTagName("Titulo").item(0);
					if(helper!=null)p.setTitulo(helper.getTextContent());
					helper = pelicula.getElementsByTagName("Duracion").item(0);
					if(helper!=null)p.setDuracion(Integer.parseInt(helper.getTextContent()));
					helper = pelicula.getElementsByTagName("Genero").item(0);
					if(helper!=null)p.setGenero(helper.getTextContent());
					helper = pelicula.getElementsByTagName("sinopsis").item(0);
					if(helper!=null)p.setSinopsis(helper.getTextContent());
					helper = pelicula.getElementsByTagName("Actores").item(0);
					if(helper!=null) {ArrayList<String> actoresAux = new ArrayList<String>();
					for (int l = 0; l<pelicula.getElementsByTagName("Actor").getLength(); l++) {
						actoresAux.add(pelicula.getElementsByTagName("Actor").item(l).getTextContent());
					}
					p.setActores(actoresAux);}
					helper = pelicula.getElementsByTagName("Fecha").item(0);
					if(helper!=null)p.setFecha(Integer.parseInt(helper.getTextContent()));
					helper = pelicula.getElementsByTagName("Director").item(0);
					if(helper!=null)p.setDirector(helper.getTextContent());
					pelis.add(p);
					
				}
				
				
				}
				System.out.println();
			
			for (Pelicula p: pelis) {
				System.out.println(p);
			}

		} catch (ParserConfigurationException | IOException | SAXException e) {
			e.printStackTrace();
		}

	}

}
