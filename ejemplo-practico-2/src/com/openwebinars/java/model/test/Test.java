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
