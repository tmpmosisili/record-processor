package com.surepay.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class JsonFileStrategyImplementationTest {
	
	@Autowired
	@Qualifier("jsonImplmentation")
	private FileStrategy jsonFileStrategy;
	
	@Test
	public void testProcessRecords() throws Exception {
		
		Path currentDir = Paths.get("");
		String absolutePath = currentDir.toAbsolutePath()+"\\src\\test\\resources\\records.json";
		Path path = Paths.get(absolutePath);
		String exceptionRecord = jsonFileStrategy.processRecords(path).toString();
		String expectedResults = "[ExceptionReport [referenceNumber=167875, description=Toy Greg Alysha], ExceptionReport [referenceNumber=165102, description=Book Shevaun Taylor]]";
		assertEquals(expectedResults,exceptionRecord); 
	}

}
