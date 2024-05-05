package com.openwebinars.java;

public class Opciones {
	
	private String pathFicheroPreguntas;
	private int cantidadTest;
	private int cantidadPreguntas;
	private String pathDestino;
	private String formato;
	private boolean unicoFichero;
	private String nombreTest;
	private String curso;
	
	public Opciones(String nombreTest, String curso, String pathFicheroPreguntas, int cantidadTest) {
		this(nombreTest, curso, pathFicheroPreguntas, cantidadTest, -1, "./", "cli", false);
	}

	public Opciones(String nombreTest, String curso, String pathFicheroPreguntas, int cantidadTest, int cantidadPreguntas, String pathDestino,
			String formato, boolean unicoFichero) {
		this.pathFicheroPreguntas = pathFicheroPreguntas;
		this.cantidadTest = cantidadTest;
		this.cantidadPreguntas = cantidadPreguntas; // Si es -1, son todas las preguntas de la plantilla del test
		this.pathDestino = pathDestino;
		this.formato = formato;
		this.unicoFichero = unicoFichero;
		this.nombreTest = nombreTest;
		this.curso = curso;
	}

	public String getPathFicheroPreguntas() {
		return pathFicheroPreguntas;
	}

	public void setPathFicheroPreguntas(String pathFicheroPreguntas) {
		this.pathFicheroPreguntas = pathFicheroPreguntas;
	}

	public int getCantidadTest() {
		return cantidadTest;
	}

	public void setCantidadTest(int cantidadTest) {
		this.cantidadTest = cantidadTest;
	}

	public int getCantidadPreguntas() {
		return cantidadPreguntas;
	}

	public void setCantidadPreguntas(int cantidadPreguntas) {
		this.cantidadPreguntas = cantidadPreguntas;
	}

	public String getPathDestino() {
		return pathDestino;
	}

	public void setPathDestino(String pathDestino) {
		this.pathDestino = pathDestino;
	}

	public String getFormato() {
		return formato;
	}

	public void setFormato(String formato) {
		this.formato = formato;
	}

	public boolean isUnicoFichero() {
		return unicoFichero;
	}

	public void setUnicoFichero(boolean unicoFichero) {
		this.unicoFichero = unicoFichero;
	}

	public String getNombreTest() {
		return nombreTest;
	}

	public void setNombreTest(String nombreTest) {
		this.nombreTest = nombreTest;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	
	
	

}
