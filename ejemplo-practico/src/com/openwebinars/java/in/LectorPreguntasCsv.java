package com.openwebinars.java.in;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.openwebinars.java.error.CsvTestFormatException;
import com.openwebinars.java.model.plantilla.PlantillaPregunta;
import com.openwebinars.java.model.plantilla.PlantillaRespuesta;

public class LectorPreguntasCsv implements LectorPreguntas {

	private String path;
	private List<String> content;
	private int preguntaActual;
	private final String separador;

	// Debe ser menor que el nº de letras que el abecedario en inglés
	private static final int NUM_TOTAL_RESPUESTAS = 4;
	private static final int NUM_TOTAL_COLS = NUM_TOTAL_RESPUESTAS + 2;

	private static final int COL_TEXTO = 0;
	private static final int COL_NUM_RESPUESTAS = 1;
	
	// Dentro del CSV, a partir de qué columna comienzan las columnas
	// de respuestas. La 0 es el texto de la pregunta, 
	// y la 1 el nº de respuestas
	private static final int OFFSET_COL_RESPUESTAS = 2;

	/*
	 * 
	 * ESTRUCTURA DEL FICHERO CSV
	 * 
	 * texto de la pregunta ; nº respuestas correctas ; respuesta 1 ; respuesta 2 ;
	 * respuesta 3 ; respuesta 4
	 * 
	 * Sin fila de encabezado
	 * 
	 */
	public LectorPreguntasCsv(String path, String separador) throws IOException {
		this.path = path;
		content = Files.readAllLines(Paths.get(path));
		preguntaActual = -1;
		this.separador = separador;
	}
	
	public LectorPreguntasCsv(String path) throws IOException {
		this(path, ";");
	}
	
	
	public String getPath() {
		return this.path;
	}

	@Override
	public PlantillaPregunta leerSiguientePregunta() {
		preguntaActual++;
		if (hayMasPreguntas())
			return procesarPregunta(content.get(preguntaActual), preguntaActual + 1);

		return null;
	}

	@Override
	public boolean hayMasPreguntas() {
		return preguntaActual < content.size();
	}

	@Override
	public List<PlantillaPregunta> leerTodasPreguntas() {
		List<PlantillaPregunta> result = new ArrayList<>();
		for (int i = 0; i < content.size(); i++) {
			result.add(procesarPregunta(content.get(i), i + 1));
		}

		return result;
	}

	private PlantillaPregunta procesarPregunta(String linea, int id) {
		String[] datos = linea.split(separador);

		if (datos.length != NUM_TOTAL_COLS)
			throw new CsvTestFormatException("El formato del fichero CSV no es correcto");

		PlantillaPregunta result;

		String texto = datos[COL_TEXTO];
		int numRespuestasCorrectas;
		
		try {
			numRespuestasCorrectas = Integer.valueOf(datos[COL_NUM_RESPUESTAS]);
		} catch (NumberFormatException e) {
			throw new CsvTestFormatException("El formato del fichero CSV no es correcto");
		}
		
		result = new PlantillaPregunta(id, texto);

		// En función del valor de NUM_TOTAL_RESPUESTAS,
		// rescatamos el todas las respuestas.
		// Según valga numRespuestasCorrectas, las añadimos
		// con el método que corresponda.

		for (int i = 0; i < NUM_TOTAL_RESPUESTAS; i++) {

			PlantillaRespuesta r = new PlantillaRespuesta(datos[OFFSET_COL_RESPUESTAS + i]);

			if (i < numRespuestasCorrectas) {
				result.addRespuestaCorrecta(r);
			} else {
				result.addRespuestaIncorrecta(r);
			}
		}

		return result;
	}

}
