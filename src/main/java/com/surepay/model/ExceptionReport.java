package com.surepay.model;

import java.math.BigInteger;


public class ExceptionReport {
	
	public ExceptionReport () {
		super();
		
	}
	
	BigInteger referenceNumber;
	String description;
	
	public BigInteger getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(BigInteger referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "ExceptionReport [referenceNumber=" + referenceNumber + ", description=" + description + "]";
	}
	


}
