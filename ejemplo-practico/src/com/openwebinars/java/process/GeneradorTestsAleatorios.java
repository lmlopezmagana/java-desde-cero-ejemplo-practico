package com.openwebinars.java.process;

import java.util.ArrayList;
import java.util.List;

import com.openwebinars.java.model.plantilla.PlantillaTest;
import com.openwebinars.java.model.test.Test;

public interface GeneradorTestsAleatorios {
	
		
	Test generarTest(int id, PlantillaTest plantilla, int cantidadPreguntas);
	
	default Test generarTest(PlantillaTest plantilla, int cantidadPreguntas) {
		return generarTest(1, plantilla, cantidadPreguntas);
	}
	
	default Test generarTest(PlantillaTest plantilla) {
		return generarTest(plantilla, plantilla.getPreguntas().size());
	}
	
	default Test generarTest(int id, PlantillaTest plantilla) {
		return generarTest(id, plantilla, plantilla.getPreguntas().size());
	}
	
	default List<Test> generarTests(PlantillaTest plantilla, int cantidadTest, int cantidadPreguntas) {
		List<Test> result = new ArrayList<>();
		for(int i = 0; i < cantidadTest; i++) {
			result.add(generarTest(i+1,plantilla, cantidadPreguntas));
		}
		return result;
	}
	
	default List<Test> generarTests(PlantillaTest plantilla, int cantidadTest) {
		List<Test> result = new ArrayList<>();
		for(int i = 0; i < cantidadTest; i++) {
			result.add(generarTest(i+1, plantilla));
		}
		return result;
	}

}
