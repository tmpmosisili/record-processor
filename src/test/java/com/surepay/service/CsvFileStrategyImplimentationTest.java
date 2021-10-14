package com.surepay.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.surepay.model.ExceptionReport;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class CsvFileStrategyImplimentationTest {
	
	@Autowired
	@Qualifier("csvImplmentation")
	private FileStrategy csvFileStrategy;
	
	@Test
	public void testProcessRecords() throws Exception {
			
			Path currentDir = Paths.get("");
			String absolutePath = currentDir.toAbsolutePath()+"\\src\\test\\resources\\records.csv";
			Path path = Paths.get(absolutePath);
			String exceptionRecord = csvFileStrategy.processRecords(path).toString();
			String testExceptionRecord = "[ExceptionReport [referenceNumber=112806, description=Book Richard Tyson], "
					+ "ExceptionReport [referenceNumber=112806, description=Book Richard Tyson]]";
			assertTrue(testExceptionRecord.equals(exceptionRecord));
	}


	
	
}
