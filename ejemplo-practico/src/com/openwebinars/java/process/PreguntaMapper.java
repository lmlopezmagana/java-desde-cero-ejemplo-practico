package com.openwebinars.java.process;

import com.openwebinars.java.model.plantilla.PlantillaPregunta;
import com.openwebinars.java.model.test.Pregunta;
import com.openwebinars.java.model.test.Respuesta;

public class PreguntaMapper {
	
	public Pregunta mapPlantillaPreguntaToPregunta(PlantillaPregunta plantilla) {
		return mapPlantillaPreguntaToPregunta(1, plantilla);
	}
	
	public Pregunta mapPlantillaPreguntaToPregunta(int id, PlantillaPregunta plantilla) {
		Pregunta p = new Pregunta(id, plantilla.getTexto());

		plantilla.getRespuestasCorrectas()
			.stream()
			.map(pc -> Respuesta.of(pc, true))
			.forEach(p::addRespuesta);

		plantilla.getRespuestasIncorrectas()
			.stream()
			.map(pi -> Respuesta.of(pi, false))
			.forEach(p::addRespuesta);

		p.desordenarRespuestas();
		
		return p;
	}
	

}
