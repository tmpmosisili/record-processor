package com.surepay.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import com.surepay.model.BankStatement;
import com.surepay.model.ExceptionReport;

public interface UtilityService {

	ExceptionReport generateExceptionReport(BankStatement bankStatment, HashMap<BigInteger, String> trackingReport,
	ArrayList<ExceptionReport> exceptionReportList);

}
