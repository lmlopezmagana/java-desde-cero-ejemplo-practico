package com.openwebinars.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.openwebinars.java.error.TestFormatException;
import com.openwebinars.java.in.LectorPreguntas;
import com.openwebinars.java.in.LectorPreguntasCsv;
import com.openwebinars.java.model.plantilla.PlantillaTest;
import com.openwebinars.java.model.test.Test;
import com.openwebinars.java.out.OutputTest;
import com.openwebinars.java.out.OutputTestConsole;
import com.openwebinars.java.out.OutputTestHtml;
import com.openwebinars.java.out.OutputTestMarkdown;
import com.openwebinars.java.process.GeneradorTestsAleatorios;
import com.openwebinars.java.process.GeneradorTestsAleatoriosImpl;

public class App {
	
	static Opciones opciones;


	public static void main(String[] args) {

		// Procesamos los argumentos
		procesarArgumentos(args);
		
		// Generamos la plantilla de test
		PlantillaTest t = new PlantillaTest(opciones.getNombreTest(), opciones.getCurso());
		
		// Comenzamos con la lectura de preguntas
		LectorPreguntas lector;
		try {
			// Añadimos las preguntas leídas a la plantilla del Test
			lector = new LectorPreguntasCsv(opciones.getPathFicheroPreguntas());
			t.addPreguntas(lector.leerTodasPreguntas());
		}  catch (IOException e) { // Gestionamos posibles errores
			e.printStackTrace();
			System.err.println("Error al intentar leer el fichero con las preguntas");
			System.exit(-1);
		} catch (TestFormatException e) {
			e.printStackTrace();
			System.err.println("Error en el formato del fichero de preguntas");
			System.exit(-1);
		}		
		
		// Obtenemos el generador de tests aleatorios
		GeneradorTestsAleatorios gen = 
				GeneradorTestsAleatoriosImpl.getInstance();
		
		// Si la cantidad de preguntas no se ha proporcionado como opción
		// los tests generados incluyen todas las preguntas de la plantilla
		int cantidadPreguntas = opciones.getCantidadPreguntas() == -1 ? t.getPreguntas().size() : opciones.getCantidadPreguntas();
		
		// Se generan los tests con las opciones indicadas
		List<Test> tests = gen.generarTests(t, opciones.getCantidadTest(), cantidadPreguntas);
		
		// Se procesan los test usando el formato especificado
		OutputTest out = switch(opciones.getFormato().toLowerCase()) {
			case "html" -> new OutputTestHtml(opciones.isUnicoFichero());
			case "md" -> new OutputTestMarkdown(opciones.isUnicoFichero());
			default -> new OutputTestConsole();
		};
				
		
		try {
			out.outputTests(tests);
			out.outputCorrecciones(tests);
			System.out.print("Los tests se han generado correctamente");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.print("Error al producir los tests aleatorios");
			System.exit(-1);
		}
	

		
	}
	
	private static void procesarArgumentos(String args[]) {
		
		String ayuda = """
				java -jar generate-test <nombre> <curso> <fichero preguntas> <cantidad tests> <OPCIONES>
				
				OPCIONES
						-q <cantidad preguntas>
	                    -d carpeta destino, si no la misma donde se invoca
						-f formato, a elegir entre cli, html, md. Por defecto cli
						-s si aparece esta opcion, salida en un mismo fichero, si no, ficheros diferentes
				""";
		
		if (args.length < 4) { // argumentos obligatorios
			System.err.println("Error en el número de argumentos.");
			System.err.println(ayuda);
			System.exit(-1);
		}
		
		int cantidadTests = 1; // Cantidad por defecto
		
		try {
			cantidadTests = Integer.valueOf(args[3]); // Si hay algún error al procesar el número, se finaliza el programa
		} catch (NumberFormatException e) {
			System.err.println("El número de tests a generar no es un número válido. Se generará 1 test");
			System.err.println(ayuda);
			System.exit(-1);
		}
		
		opciones = new Opciones(args[0], args[1], args[2], cantidadTests);
		
		// Procesamos el resto de argumentos
		List<String> restoOpciones = new ArrayList<>(
				Arrays.asList(Arrays.copyOfRange(args, 4, args.length)));
		
		/*
		 * Un ArrayList no se puede procesar mediante un bucle for
		 * mientras se va modificando dicha colección. Esto solamente
		 * lo podemos hacer usando un iterador
		 */
		
		while(!restoOpciones.isEmpty()) {
			Iterator<String> iterator = restoOpciones.iterator();
			boolean printAyuda = false;
			while(iterator.hasNext()) {
				String opcion = iterator.next();
				switch(opcion.toLowerCase()) {
				case "-q": iterator.remove();
						   String cantidadPreguntas = iterator.next();
						   try {
							   opciones.setCantidadPreguntas(Integer.valueOf(cantidadPreguntas));
							   iterator.remove();
						   } catch (NumberFormatException e) {
								System.err.println("La cantidad de preguntas no número válido");
								printAyuda = true;
							}
						   break;
				case "-d": iterator.remove();
				   		   String destino = iterator.next();
				   		   opciones.setPathDestino(destino);
				   		   iterator.remove();
						   break;
				case "-f": iterator.remove();
						   String formato = iterator.next();
						   if (!List.of("cli", "html", "md").contains(formato.toLowerCase())) {
							   System.err.println("Formato de salida no soportado");
							   printAyuda = true;
						   } else {
							   opciones.setFormato(formato);
						   }
						   iterator.remove();
						   break;
				case "-s": opciones.setUnicoFichero(true);
						   iterator.remove();
						   break;
				default: 
						   iterator.remove();
						   System.err.println("%s opción no soportada".formatted(opcion));
						   printAyuda = true;
				}
			}
			
			if (printAyuda)
				System.err.println(ayuda);
		}
		
		
		
	}

}
