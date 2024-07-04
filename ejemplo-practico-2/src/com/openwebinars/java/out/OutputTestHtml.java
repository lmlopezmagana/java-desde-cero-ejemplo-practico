package com.openwebinars.java.out;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.openwebinars.java.model.test.Test;

public class OutputTestHtml extends AbstractOutputTestFile{

	private static final List<String> INICIO = List.of(
			"<html>",
			"<body>"
			);
	private static final List<String> FIN = List.of(
			"</body>","</html>");
	
	
	public OutputTestHtml(boolean sameFile) {
		super(".html");
		this.sameFile = sameFile;
	}

	@Override
	public List<String> formatTest(Test t) {
		List<String> result = new ArrayList<>();
		result.addAll(INICIO);
		result.add("<h1>Test: %s (nº %d)</h1>".formatted(t.getNombre(), t.getId()));
		result.add("");
		result.add("<h3>Preguntas</h3>");
		result.add("<br/>");
		result.add("<ol>");
		t.getPreguntas()
		.forEach(p -> {
			result.add("<li>%s".formatted(p.getTexto()));
			result.add("<ul>");
			p.getRespuestas()
				.forEach(r -> result.add("<li> %s) %s</li>".formatted(r.getId(), r.getTexto())));
			result.add("</ul>");
			result.add("</li>");
			result.add("<br/><br/>");

		});
		result.add("</ol>");
		result.add("<br/>");
		result.addAll(FIN);
		return result;
	}



	@Override
	public List<String> formatCorreccion(Test t) {
		List<String> result = new ArrayList<>();
		result.addAll(INICIO);
		result.add("<h1>Test: %s (nº %d)</h1>".formatted(t.getNombre(), t.getId()));
		result.add("");
		result.add("<h3>Respuestas correctas</h3>");
		result.add("<br/>");
		result.add("<ul>");

		t.getPreguntas().
		forEach(p -> 
			result.add("<li>%d. %s</li>"
					.formatted(p.getId(), p.getIdsRespuestasCorrectas()
											.stream()
											.collect(Collectors.joining(", ")))));


		result.add("</ul>");
		result.add("<br/>");
		result.addAll(FIN);
		return result;
	}
}
