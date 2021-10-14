package com.surepay.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.surepay.model.BankStatement;
import com.surepay.model.ExceptionReport;
import com.surepay.utils.Utils;

@Component("jsonImplmentation")
public class JsonFileStrategyImplimentation  implements FileStrategy{
	
	private Utils utility; 
	private Logger logger = LoggerFactory.getLogger(JsonFileStrategyImplimentation.class);
	
	@Autowired
	public JsonFileStrategyImplimentation(Utils utility) {
		this.utility = utility;
	}
	
	
	@Override
	public ArrayList<ExceptionReport> processRecords(Path path) {
		
		HashMap<BigInteger, String> trackingReport = new HashMap<>();
		ArrayList <ExceptionReport> exceptionReportList = new ArrayList <>();
		  try (
		            InputStream inputStream = Files.newInputStream(path); 
		            JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
		    ) {            
		        reader.beginArray();
		        while (reader.hasNext()) {
		            BankStatement bankStatement = new Gson().fromJson(reader, BankStatement.class);
		            utility.generateExceptionReport(bankStatement, trackingReport, exceptionReportList);
		        }
		        reader.endArray();
		    } catch (IOException e) {
		    	 logger.error("IOException opening file: " + path.toAbsolutePath().toString() + " " + e.getMessage());
			}
		return exceptionReportList;
		
	}


}
