package com.openwebinars.java.out;

import java.util.List;

import com.openwebinars.java.model.test.Test;

public interface OutputTest {
	
	void outputTest(Test t) throws Exception;
	
	void outputCorreccion(Test t) throws Exception;
	
	void outputTests(List<Test> list) throws Exception;
	
	void outputCorrecciones(List<Test> list) throws Exception;

	
	List<String> formatTest(Test t);
	
	List<String> formatCorreccion(Test t);
	

}
