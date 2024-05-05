package com.openwebinars.java.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.openwebinars.java.model.test.Test;

public abstract class AbstractOutputTestFile implements OutputTest {
	
	protected String testFilenamePrefix;
	protected Map<Test, String> pathsForTests;
	protected String basePath;
	protected Path path;
	protected boolean folderCreated;
	protected String extension;
	protected boolean sameFile;
	
	
	public AbstractOutputTestFile(String extension) {
		//this.testFilenamePrefix = testFilenamePrefix;
		this.pathsForTests = new HashMap<>();
		this.folderCreated = false;
		this.extension = extension;
		this.sameFile = true;
	}
	
	public AbstractOutputTestFile() {
		this(".out");
	}
	
	
	public String getTestFilenamePrefix() {
		return testFilenamePrefix;
	}


	public void setTestFilenamePrefix(String testFilenamePrefix) {
		if (testFilenamePrefix != this.testFilenamePrefix) {
			this.testFilenamePrefix = testFilenamePrefix;
			this.folderCreated = false;
		}
		
	}
	
	
	public boolean isSameFile() {
		return sameFile;
	}

	public void setSameFile(boolean sameFile) {
		this.sameFile = sameFile;
	}

	public void prepareFolder() throws IOException {
		
		if (testFilenamePrefix == null) {
			throw new UnsupportedOperationException("No se puede preparar la carpeta de destino de los test sin establecer el prefijo de dicha carpeta");
		}
		
		String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
		basePath = "./" + testFilenamePrefix + "-" + datetime;

		path = Paths.get(basePath);
		
		if (!Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			Files.createDirectory(path);
			this.folderCreated = true;
		}
		
		
	}

	@Override
	public void outputTest(Test t) throws IOException {
		outputTest(t, true);
	}
	
	private void outputTest(Test t, boolean doPrepareFolder) throws IOException {
		
		if (this.testFilenamePrefix == null)
			this.testFilenamePrefix = calculateTestFilenamePrefix(t);
		
		if (doPrepareFolder && !folderCreated) {
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
	//public void outputTests(List<Test> list, boolean sameFile) throws IOException {
	public void outputTests(List<Test> list) throws Exception {
		
		if (this.testFilenamePrefix == null)
			this.testFilenamePrefix = calculateTestFilenamePrefix(list.get(0));

		
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
	//public void outputCorrecciones(List<Test> list, boolean sameFile) throws IOException {
	public void outputCorrecciones(List<Test> list) throws Exception {
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
		
	}


	private String calculateTestFilenamePrefix(Test t) {
		String nombre = t.getNombre().toLowerCase().replaceAll("\\s", "");
		String curso = t.getCurso().toLowerCase().replaceAll("\\s", "");
		return nombre + curso;
	}
	
	public abstract List<String> formatTest(Test t);
	
	public abstract List<String> formatCorreccion(Test t);


	
	
	

}
