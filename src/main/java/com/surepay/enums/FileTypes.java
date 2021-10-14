package com.surepay.enums;

public enum FileTypes {
	    CSV("csv"),
	    JSON("json");
	    
	    private final String displayValue;
	    
	    private FileTypes(String displayValue) {
	        this.displayValue = displayValue;
	    }
	    
	    public String getDisplayValue() {
	        return displayValue;
	    }

}
