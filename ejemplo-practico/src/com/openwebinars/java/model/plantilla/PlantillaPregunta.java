package com.openwebinars.java.model.plantilla;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PlantillaPregunta {
	
	private int id;
	private String texto;
	private List<PlantillaRespuesta> respuestasCorrectas;
	private List<PlantillaRespuesta> respuestasIncorrectas;
	
	public PlantillaPregunta() {
		respuestasCorrectas = new ArrayList<>();
		respuestasIncorrectas = new ArrayList<>();
	}

	public PlantillaPregunta(int id, String texto) {
		this();
		this.id = id;
		this.texto = texto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	public int getNumTotalRespuestas() {
		return this.respuestasCorrectas.size() + this.respuestasIncorrectas.size();
	}
	
	public int getNumRespuestasCorrectas() {
		return this.respuestasCorrectas.size();
	}
	
	public int getNumRespuestasIncorrectas() {
		return this.respuestasIncorrectas.size();
	}

	public List<PlantillaRespuesta> getRespuestasCorrectas() {
		return Collections.unmodifiableList(respuestasCorrectas);
	}

	public List<PlantillaRespuesta> getRespuestasIncorrectas() {
		return Collections.unmodifiableList(respuestasIncorrectas);
	}
	
	public List<PlantillaRespuesta> getTodasRespuestas() {
		List<PlantillaRespuesta> r = new ArrayList<>(respuestasCorrectas);
		r.addAll(respuestasIncorrectas);
		return Collections.unmodifiableList(r);
	}
	
	public List<PlantillaRespuesta> getTodasRespuestasDesordenadas() {
		List<PlantillaRespuesta> todas = new ArrayList<>(respuestasCorrectas);
		todas.addAll(respuestasIncorrectas);
		Collections.shuffle(todas, new Random(System.currentTimeMillis()));
		List<PlantillaRespuesta> result = new ArrayList<>();
		result.addAll(todas);
//		for(int i = 0; i < getNumTotalRespuestas(); i++) {
//			// Renumeramos las respuestas
//			//result.get(i).setId(i+1);
//		}
		return result;
	}
	
	public void addRespuestaCorrecta(PlantillaRespuesta r) {
		this.respuestasCorrectas.add(r);
	}
	
	public void addRespuestaIncorrecta(PlantillaRespuesta r) {
		this.respuestasIncorrectas.add(r);
	}
	
	public boolean removeRespuestaCorrecta(PlantillaRespuesta r) {
		return this.respuestasCorrectas.remove(r);
	}
	
	public boolean removeRespuestaIncorrecta(PlantillaRespuesta r) {
		return this.respuestasIncorrectas.remove(r);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, respuestasCorrectas, respuestasIncorrectas, texto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlantillaPregunta other = (PlantillaPregunta) obj;
		return id == other.id && Objects.equals(respuestasCorrectas, other.respuestasCorrectas)
				&& Objects.equals(respuestasIncorrectas, other.respuestasIncorrectas)
				&& Objects.equals(texto, other.texto);
	}

	@Override
	public String toString() {
		return "Pregunta [id=" + id + ", texto=" + texto + ", respuestasCorrectas=" + respuestasCorrectas
				+ ", respuestasIncorrectas=" + respuestasIncorrectas + "]";
	}
	
	

}
