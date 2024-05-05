package com.openwebinars.java.model.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Test {
	
	private int id;
	private String nombre;
	private String curso;
	private List<Pregunta> preguntas;
	
	public Test() {
		preguntas = new ArrayList<>();
	}
	
	public Test(int id, String nombre, String curso) {
		this();
		this.id = id;
		this.nombre = nombre;
		this.curso = curso;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	public void addPregunta(Pregunta p) {
		this.preguntas.add(p);
	}

	public void addVariasPreguntas(List<Pregunta> p) {
		this.preguntas.addAll(p);
	}
	
	public List<Pregunta> getPreguntas() {
		return Collections.unmodifiableList(preguntas);
	}
	
//	public void printTest() {
//		System.out.println("Test: %s (nº %d) ".formatted(this.getNombre(), this.getId()));
//		System.out.println("Curso: " + this.getCurso());
//		System.out.println("=========================");
//		System.out.println();
//		System.out.println("PREGUNTAS");
//		System.out.println("--------------");
//		System.out.println();
//		preguntas
//			.forEach(p -> {
//				System.out.println("%d %s".formatted(p.getId(), p.getTexto()));
//				System.out.println();
//				p.getRespuestas()
//					.forEach(r -> System.out.println("%s %s".formatted(r.getId(), r.getTexto())));
//				System.out.println();
//			});
//	}
//	
//	public void printPlantillaCorreccion() {
//		System.out.println("Respuestas correctas");
//		System.out.println("--------------");
//		preguntas.
//			forEach(p -> 
//				System.out.println("%d %s"
//						.formatted(p.getId(), p.getIdsRespuestasCorrectas()
//												.stream()
//												.collect(Collectors.joining(", ")))));
//	}

	@Override
	public int hashCode() {
		return Objects.hash(curso, id, nombre, preguntas);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Test other = (Test) obj;
		return Objects.equals(curso, other.curso) && id == other.id && Objects.equals(nombre, other.nombre)
				&& Objects.equals(preguntas, other.preguntas);
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", nombre=" + nombre + ", curso=" + curso + ", preguntas=" + preguntas + "]";
	}
	
	
}
