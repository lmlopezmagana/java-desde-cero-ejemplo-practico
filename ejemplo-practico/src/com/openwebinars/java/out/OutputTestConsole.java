package com.openwebinars.java.out;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.openwebinars.java.model.test.Test;

public class OutputTestConsole implements OutputTest {

	@Override
	public void outputTest(Test t) {
		formatTest(t)
			.forEach(System.out::println);
	}

	@Override
	public void outputCorreccion(Test t) {
		formatCorreccion(t)
			.forEach(System.out::println);
	}

	@Override
	public void outputTests(List<Test> list) throws Exception {
		for(Test t : list) {
			outputTest(t);
		}
		
	}

	@Override
	public void outputCorrecciones(List<Test> list) throws Exception {
		for(Test t: list) {
			outputCorreccion(t);
		}
	}

	@Override
	public List<String> formatTest(Test t) {
		List<String> result = new ArrayList<>();
		
		result.add("Test: %s (nº %d) ".formatted(t.getNombre(), t.getId()));
		result.add("Curso: " + t.getCurso());
		result.add("=========================");
		result.add("");
		result.add("PREGUNTAS");
		result.add("--------------");
		result.add("");
		t.getPreguntas()
			.forEach(p -> {
				result.add("%d. %s".formatted(p.getId(), p.getTexto()));
				result.add("");
				p.getRespuestas()
					.forEach(r -> result.add("%s %s".formatted(r.getId(), r.getTexto())));
				result.add("");
			});
		
		return result;
	}

	@Override
	public List<String> formatCorreccion(Test t) {
		List<String> result = new ArrayList<>();
		result.add("Respuestas correctas");
		result.add("--------------------");
		t.getPreguntas().
			forEach(p -> 
			result.add("%d %s"
						.formatted(p.getId(), p.getIdsRespuestasCorrectas()
												.stream()
												.collect(Collectors.joining(", ")))));
	
		return result;
	} 
	
	

}
