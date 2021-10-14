package com.surepay.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigInteger;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.surepay.model.BankStatement;
import com.surepay.model.ExceptionReport;
import com.surepay.utils.Utils;
import com.univocity.parsers.common.ParsingContext;
import com.univocity.parsers.common.processor.AbstractRowProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

//@ComponentScan(basePackages = {"com.surepay.model"})
@Component("csvImplmentation")
public class CsvFileStrategyImplimentation implements FileStrategy {
	
	 private Logger logger = LoggerFactory.getLogger(CsvFileStrategyImplimentation.class);
	 private Utils utility; 
	 
	 @Autowired
		public CsvFileStrategyImplimentation(Utils utility) {
			this.utility = utility;
		}
		
	    public CsvFileStrategyImplimentation() {
	    	super();
	    }
	    
	@Override
	public List <ExceptionReport> processRecords(Path path) {
		HashMap<BigInteger, String> trackingReport = new HashMap<>();
		ArrayList <ExceptionReport> exceptionReportList = new ArrayList <>();
		 
		CsvParserSettings settings = new CsvParserSettings();
		Reader inputReader = null;
		settings.getFormat().setLineSeparator("\n"); 
		settings.getFormat().setLineSeparator("\n");
		settings.setIgnoreLeadingWhitespaces(false);
		settings.setIgnoreTrailingWhitespaces(false);
		settings.setSkipEmptyLines(false);
		settings.setColumnReorderingEnabled(false);
		settings.setHeaderExtractionEnabled(true);

		settings.setProcessor(new AbstractRowProcessor() {
			@Override
			public void rowProcessed(String[] row, ParsingContext context) {
				
				convertArrayToObject (row,new BankStatement());
				utility.generateExceptionReport(convertArrayToObject (row,new BankStatement()), trackingReport, exceptionReportList);
			}
		});
	
		CsvParser parser = new CsvParser(settings);
		try {
			inputReader = new InputStreamReader(new FileInputStream(path.toFile()));
		} catch (FileNotFoundException e) {
			logger.error("IOException opening file: " + path.toAbsolutePath().toString() + " " + e.getMessage());
		}
		
		 parser.parse(inputReader);
		return exceptionReportList;
	}
	
	private  BankStatement convertArrayToObject (String row[], BankStatement bankStatement) {
		bankStatement.setReference(row[0]);
		bankStatement.setAccountNumber(row[1]);
		bankStatement.setDescription(row[2]);
		bankStatement.setStartBalance(Double.parseDouble(row[3]));
		bankStatement.setMutation(Double.parseDouble(row[4]));
		bankStatement.setEndBalance(Double.parseDouble(row[5]));
		return bankStatement;
	}
	


}
