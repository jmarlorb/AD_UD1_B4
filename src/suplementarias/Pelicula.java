package suplementarias;

import java.util.ArrayList;

public class Pelicula {

	private String titulo;
	private int duracion;
	private String genero;
	private String sinopsis;
	private ArrayList<String> Actores;
	private int fecha;
	private String director;
	
	public Pelicula() {}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public ArrayList<String> getActores() {
		return Actores;
	}

	public void setActores(ArrayList<String> actores) {
		Actores = actores;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	@Override
	public String toString() {
		String aux ="";
		aux+="Titulo : "+this.titulo+"\n";
		if (this.duracion > 0) aux+="Duración : "+this.duracion+"\n";
		if (this.genero!=null) aux+="Género :" + this.genero+"\n";
		if (this.sinopsis!=null) aux+="Sinopsis : " + this.sinopsis+"\n";
		if (this.Actores!=null && !this.Actores.isEmpty()) {
			aux+="Actores : \n";
			for (String s: this.Actores) {
				aux+="\t"+s+"\n";
			}
		}
		if (this.fecha > 0) aux += "Fecha : "+this.fecha+"\n";
		if (this.director != null) aux += "Director : " + this.director+"\n";
		
		
		return aux;
	}
	
	
}
