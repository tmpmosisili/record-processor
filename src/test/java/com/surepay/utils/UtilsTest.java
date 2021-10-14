package com.surepay.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.surepay.model.BankStatement;
import com.surepay.model.ExceptionReport;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
public class UtilsTest {
	
	@Autowired
	private Utils utility; 
	
	@Test
	public void testCurrencyFormat() throws Exception {
		 assertTrue((double)ReflectionTestUtils.invokeMethod(utility,"currencyFormat",400.023) == 400.02);
	}
	
	@Test
	public void testCurrencyFormatRouding() throws Exception {
		 assertTrue((double)ReflectionTestUtils.invokeMethod(utility,"currencyFormat",400.239999) == 400.24);
	}
	
	@Test
	public void testCurrencyFormatIncorrect() throws Exception {
		 assertNotEquals((double)ReflectionTestUtils.invokeMethod(utility,"currencyFormat", 400.023) , 400.03);
	}
	
	@Test
	public void testBalanceCorrect() throws Exception {
		BankStatement bankStatement = new BankStatement();
		bankStatement.setReference("194261");
		bankStatement.setAccountNumber("NL91RABO0315273637");
		bankStatement.setDescription("Book John Smith");
		bankStatement.setStartBalance(21.6);
		bankStatement.setMutation(-41.83);
		bankStatement.setEndBalance(-20.23);
		assertTrue(ReflectionTestUtils.invokeMethod(utility,"isBalanceCorrect",bankStatement));	
	}
	
	@Test
	public void testBalanceinCorrect() throws Exception {
		BankStatement bankStatement = new BankStatement();
		bankStatement.setReference("194261");
		bankStatement.setAccountNumber("NL91RABO0315273637");
		bankStatement.setDescription("Book John Smith");
		bankStatement.setStartBalance(21.6);
		bankStatement.setMutation(-41.83);
		bankStatement.setEndBalance(-20.20);
		assertFalse(ReflectionTestUtils.invokeMethod(utility,"isBalanceCorrect",bankStatement));	
	}
	
	@Test
	public void testgenerateExceptionReport() throws Exception {
		BankStatement bankStatement = new BankStatement();
		List<ExceptionReport> exceptionReportList = new ArrayList<>();
		HashMap<BigInteger, String> trackingReport = new HashMap<>();
		bankStatement.setReference("294261");
		bankStatement.setAccountNumber("NL91RABO0315273637");
		bankStatement.setDescription("Book John Smith");
		bankStatement.setStartBalance(21.6);
		bankStatement.setMutation(-41.83);
		bankStatement.setEndBalance(-20.20);
		ExceptionReport exceptionReport = new ExceptionReport();
		exceptionReport.setReferenceNumber(new BigInteger("194261"));
		exceptionReport.setDescription("Book John Smith22");
		
		exceptionReportList.add(exceptionReport);
		
		exceptionReport.setReferenceNumber(new BigInteger("1942655"));
		exceptionReport.setDescription("Peter Pan Sword");
		
		exceptionReportList.add(exceptionReport);
		trackingReport.put(new BigInteger("1942655"), "Peter Pan Sword");
		
		assertFalse(ReflectionTestUtils.invokeMethod(utility,"generateExceptionReport",bankStatement,trackingReport,exceptionReportList));	
	}
	
	
	

}
