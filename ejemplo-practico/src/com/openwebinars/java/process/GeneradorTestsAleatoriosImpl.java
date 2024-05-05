package com.openwebinars.java.process;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.openwebinars.java.error.TestFormatException;
import com.openwebinars.java.model.plantilla.PlantillaPregunta;
import com.openwebinars.java.model.plantilla.PlantillaTest;
import com.openwebinars.java.model.test.Test;

public class GeneradorTestsAleatoriosImpl implements GeneradorTestsAleatorios {
	
	
	private static GeneradorTestsAleatoriosImpl INSTANCE;
	
	private PreguntaMapper preguntaMapper;
	
	private GeneradorTestsAleatoriosImpl() {
		preguntaMapper = new PreguntaMapper();
	}
	
	public static GeneradorTestsAleatoriosImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new GeneradorTestsAleatoriosImpl();
		}
		
		return INSTANCE;
	}
	
	
	@Override
	public Test generarTest(int id, PlantillaTest plantilla, int cantidadPreguntas) {

		if (cantidadPreguntas > plantilla.getPreguntas().size())
			throw new TestFormatException("El nº de preguntas del test no puede ser superior al de la plantilla");

		Test t = new Test(id, plantilla.getNombre(), plantilla.getCurso());

		// Obtenemos el subconjunto aleatorio de preguntas del test
 
		List<PlantillaPregunta> preguntas = plantilla.getPreguntas()
				.stream()
				.sorted(Shuffle.shuffle())
				.limit(cantidadPreguntas).toList();

		for (int i = 0; i < cantidadPreguntas; i++) {
			t.addPregunta(preguntaMapper.mapPlantillaPreguntaToPregunta(i + 1, preguntas.get(i)));

		}
		return t;

	}

	/*
	 * Una forma de poder ordenar aleatoriamente el contenido
	 * de un stream pero sin romper el contrato definido por 
	 * la interfaz Comparator puede ser asignar a cada elemento
	 * del stream un UUID aleatorio, y almacenarlos en un IdentityHashMap.
	 * La diferencia entre un HashMap y un IdentityHashMap es que en un
	 * HashMap dos claves k1 y k2 son iguales si k1.equals(k2), y en
	 * un IdentityHashMap son iguales si k1 == k2.
	 */
	static class Shuffle {
		public static <T> Comparator<T> shuffle() {
			final Map<Object, UUID> uniqueIds = new IdentityHashMap<>();
			return Comparator.comparing(e -> uniqueIds.computeIfAbsent(e, k -> UUID.randomUUID()));
		}
	}

}
