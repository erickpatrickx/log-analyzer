package com.ef.enums;

public enum EnumDuration {
	hourly("hourly"), daily("daily");
	
	private final String text;
	
	EnumDuration(String text) {
		this.text = text;
	}
	
	@Override
    public String toString() {
        return text;
    }
}
