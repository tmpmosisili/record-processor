package com.surepay.utils;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Component;

import com.surepay.model.BankStatement;
import com.surepay.model.ExceptionReport;

@Component
public class Utils implements UtilityService {
	
	@Override
	public  ExceptionReport generateExceptionReport(BankStatement bankStatment, HashMap<BigInteger, String> trackingReport, 
			ArrayList<ExceptionReport> exceptionReportList ) {
		 String description = bankStatment.getDescription();
		 BigInteger transactionReference = new BigInteger(bankStatment.getReference());
		 ExceptionReport exceptionReport = new ExceptionReport();
		
		 if (trackingReport.containsKey(transactionReference) || !isBalanceCorrect(bankStatment) ) {
			 exceptionReport.setReferenceNumber(transactionReference);
			 exceptionReport.setDescription(description);
			 exceptionReportList.add(exceptionReport); 
		 }
		 trackingReport.put(transactionReference, description);
		return exceptionReport;
	}
	
	private boolean isBalanceCorrect(BankStatement bankStatment) {
		DecimalFormat df = new DecimalFormat("#.##");
		
		double startBalance = currencyFormat(bankStatment.getStartBalance());
		double mutation = currencyFormat(bankStatment.getMutation());
		double statementEndBalance = currencyFormat(bankStatment.getEndBalance());
		double calculatedEndBalance = Double.parseDouble(df.format(startBalance + mutation));
		
		 return  calculatedEndBalance == statementEndBalance;  
	}
	
	private double currencyFormat(double amount) {
		DecimalFormat df = new DecimalFormat("#.##");
		return  Double.valueOf(df.format(amount));
	}
	

}
