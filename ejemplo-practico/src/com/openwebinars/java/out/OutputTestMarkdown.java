package com.openwebinars.java.out;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.openwebinars.java.model.test.Test;

public class OutputTestMarkdown extends AbstractOutputTestFile {
	
	
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

	/*

	@Override
	public void outputTest(Test t) throws IOException {
		outputTest(t, true);
	}
	
	private void outputTest(Test t, boolean doPrepareFolder) throws IOException {
		
		if (doPrepareFolder && ! folderCreated) {
			prepareFolder();
		}
		
		pathsForTests.putIfAbsent(t, basePath);
		
		String filename = testFilenamePrefix +  "-" + t.getId() + extension;

		List<String> result = formatTest(t);
		
		Files.write(path.resolve(filename), result, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

		
	}
	
	

	@Override
	public void outputCorreccion(Test t) throws IOException {
		if (!pathsForTests.containsKey(t)) {
			throw new UnsupportedOperationException("No se puede generar la corrección antes de generar el test");
		}
		
		String filename = testFilenamePrefix +  "-" + t.getId() + "-solucion" + extension;
				
		List<String> result = formatCorreccion(t);
		Files.write(path.resolve(filename), result, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

		
	}


	@Override
	public void outputTests(List<Test> list, boolean sameFile) throws IOException {
		
		if (!folderCreated) {
			prepareFolder();
		}
		
		
		if (sameFile) {
						
			List<String> result = 
					list.stream()
						.peek(t -> pathsForTests.putIfAbsent(t, basePath))
						.map(this::formatTest)
						.flatMap(l -> l.stream())
						.toList();
			
			String filename = testFilenamePrefix +  "-unidos" + extension;
			Files.write(path.resolve(filename), result, 
					StandardOpenOption.CREATE, 
					StandardOpenOption.WRITE, 
					StandardOpenOption.TRUNCATE_EXISTING);

		} else {
			
			for(Test t : list) {
				String filename = testFilenamePrefix +  "-" + t.getId() + extension;
				List<String> result = formatTest(t);				
				Files.write(path.resolve(filename), result, 
						StandardOpenOption.CREATE, 
						StandardOpenOption.WRITE, 
						StandardOpenOption.TRUNCATE_EXISTING);

			}
			
			
		}
		
	}


	@Override
	public void outputCorrecciones(List<Test> list, boolean sameFile) throws IOException {
		if (sameFile) {
			
			List<String> result = 
					list.stream()
						.peek(t -> pathsForTests.putIfAbsent(t, basePath))
						.map(this::formatCorreccion)
						.flatMap(l -> l.stream())
						.toList();
			
			String filename = testFilenamePrefix +  "-soluciones" + extension;
			Files.write(path.resolve(filename), result, 
					StandardOpenOption.CREATE, 
					StandardOpenOption.WRITE, 
					StandardOpenOption.TRUNCATE_EXISTING);

		} else {
			
			for(Test t : list) {
				String filename = testFilenamePrefix +  "-" + t.getId() + "-solucion" + extension;
				List<String> result = formatTest(t);				
				Files.write(path.resolve(filename), result, 
						StandardOpenOption.CREATE, 
						StandardOpenOption.WRITE, 
						StandardOpenOption.TRUNCATE_EXISTING);

			}
			
			
		}
		
	}*/

}
