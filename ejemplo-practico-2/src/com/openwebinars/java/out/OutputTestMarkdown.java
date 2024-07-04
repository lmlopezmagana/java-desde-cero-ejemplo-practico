package com.openwebinars.java.out;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.openwebinars.java.model.test.Test;

public class OutputTestMarkdown extends AbstractOutputTestFile{
	
	public OutputTestMarkdown(boolean sameFile) {
		super(".md");
		this.sameFile = sameFile;
	}
	
	
	
	public List<String> formatTest(Test t) {
		List<String> result = new ArrayList<>();
		
		result.add("# Test: %s (nº %d) ".formatted(t.getNombre(), t.getId()));
		result.add("");
		result.add("### Preguntas");
		result.add("");
		t.getPreguntas()
		.forEach(p -> {
			result.add("%d. %s".formatted(p.getId(), p.getTexto()));
			result.add("");
			p.getRespuestas()
				.forEach(r -> result.add("- [ ] %s) %s".formatted(r.getId(), r.getTexto())));
			result.add("");
		});
		result.add("");
		return result;
	}
	
	public List<String> formatCorreccion(Test t) {
		List<String> result = new ArrayList<>();
		
		result.add("# Test: %s (nº %d) ".formatted(t.getNombre(), t.getId()));
		result.add("");
		result.add("### Respuestas correctas");
		result.add("");
		t.getPreguntas().
		forEach(p -> 
			result.add("%d. %s"
					.formatted(p.getId(), p.getIdsRespuestasCorrectas()
											.stream()
											.collect(Collectors.joining(", ")))));
		return result;
	}


}