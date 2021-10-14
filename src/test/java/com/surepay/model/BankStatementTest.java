package com.surepay.model;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.Assert.*;

public class BankStatementTest {
	BankStatement bankStatement;
	@Before
    public void setUp() throws Exception {
		 this.bankStatement = new BankStatement();
    }
	
	@Test
	public void whenNonPublicField_thenReflectionTestUtilsSetField() {
		BankStatement bankStatement = new BankStatement();
	    ReflectionTestUtils.setField(bankStatement, "reference", "1");
	    ReflectionTestUtils.setField(bankStatement, "accountNumber", "123");
	    ReflectionTestUtils.setField(bankStatement, "description", "SurePay");
	    ReflectionTestUtils.setField(bankStatement, "startBalance", 843.00);
	    ReflectionTestUtils.setField(bankStatement, "mutation", 941.00);
	    ReflectionTestUtils.setField(bankStatement, "endBalance", 650.00);
	 
	    assertTrue(bankStatement.getReference().equals("1"));
	    assertTrue(bankStatement.getAccountNumber().equals("123"));
	    assertTrue(bankStatement.getDescription().equals("SurePay"));
	    assertTrue(bankStatement.getStartBalance() == 843.00);
	    assertTrue(bankStatement.getMutation() == 941.00);
	    assertTrue(bankStatement.getEndBalance() == 650.00);
	}
	
 
 

}
