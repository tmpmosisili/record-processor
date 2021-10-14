package com.surepay.service;

import java.nio.file.Path;
import java.util.List;

import com.surepay.model.ExceptionReport;



public interface FileStrategy {
	
	List<ExceptionReport> processRecords(Path path);

}
