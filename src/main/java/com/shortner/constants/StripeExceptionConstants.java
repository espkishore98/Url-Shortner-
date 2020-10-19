package com.shortner.constants;

public interface StripeExceptionConstants {

	public static final String CVC_ERROR = "Your card's security code is incorrect.";
	
	public static final String LOW_FUNDS_ERROR = "Your payment method has insufficient funds";
	
	public static final String PROCESSING_ERROR = "An error occurred while processing your card. Try again!";
	
	public static final String CARD_DECLINED_ERROR = "Your payment method was declined. Please try again";
	
	public static final String CARD_EXPIRED_ERROR = "Your payment method has expired";
}
