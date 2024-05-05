package com.openwebinars.java.in;

import java.util.List;

import com.openwebinars.java.model.plantilla.PlantillaPregunta;

public interface LectorPreguntas {
	
	PlantillaPregunta leerSiguientePregunta();
	
	boolean hayMasPreguntas();
	
	List<PlantillaPregunta> leerTodasPreguntas();

}
