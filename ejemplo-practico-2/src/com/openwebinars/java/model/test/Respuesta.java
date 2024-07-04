package com.openwebinars.java.model.test;

import java.util.Objects;

import com.openwebinars.java.model.plantilla.PlantillaRespuesta;

public class Respuesta {

	private String id;
	private String texto;
	private boolean esCorrecta;
	
	
	public Respuesta(String id, String texto, boolean esCorrecta) {
		this.id = id;
		this.texto = texto;
		this.esCorrecta = esCorrecta;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public boolean esCorrecta() {
		return esCorrecta;
	}


	public void setEsCorrecta(boolean esCorrecta) {
		this.esCorrecta = esCorrecta;
	}

	public static Respuesta of(String id, PlantillaRespuesta plantilla, boolean esCorrecta) {
		return new Respuesta(id, plantilla.getTexto(), esCorrecta);
	}
	
	public static Respuesta of(PlantillaRespuesta plantilla, boolean esCorrecta) {
		return new Respuesta(null, plantilla.getTexto(), esCorrecta);
	}

	@Override
	public int hashCode() {
		return Objects.hash(esCorrecta, id, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Respuesta other = (Respuesta) obj;
		return esCorrecta == other.esCorrecta && Objects.equals(id, other.id) && Objects.equals(texto, other.texto);
	}

	@Override
	public String toString() {
		return "Respuesta [id=" + id + ", texto=" + texto + ", esCorrecta=" + esCorrecta + "]";
	}
	

	
	
	
}
