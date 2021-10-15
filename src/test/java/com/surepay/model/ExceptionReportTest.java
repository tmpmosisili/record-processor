package com.surepay.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

public class ExceptionReportTest {
	
	@Test
	public void TestGettersAndSetters() {
		ExceptionReport exceptionReport = new ExceptionReport();
		
		 ReflectionTestUtils.setField(exceptionReport, "referenceNumber", new BigInteger("1"));
		 ReflectionTestUtils.setField(exceptionReport, "description", "123");
		 
		 assertEquals(exceptionReport.getReferenceNumber(), new BigInteger("1")); 
		 assertEquals(exceptionReport.getDescription(),"123");
	}

}
