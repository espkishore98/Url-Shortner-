package com.shortner.constants;

public interface ErrorMessages {
	
	public final String DETAILS_REQUIRED = "Please provide your details";
	public final String FIRST_NAME_REQUIRED = "Please provide your first name";
	public final String LAST_NAME_REQUIRED = "Please provide your last name";
	public final String COMPANY_NAME_REQUIRED = "Please provide your company name";
	public final String EMAIL_REQUIRED = "Please provide your valid e-mail address";
	public final String PASSWORD_REQUIRED = "Please provide your password";
	public final String CHECK_EMAIL_FORMAT = "Please enter a valid e-mail address format";
	public final String EMAIL_COMPANY_EXISTS = "E-mail ID and company name already exists";
	public final String EMAIL_ALREADY_EXISTS = "E-mail ID is already registered";
	public final String COMPANY_ALREADY_EXSITS = "Company name already exists";
	public final String VALID_COUPON_CODE = "Please provide a valid coupon code";
	public final String SOMETHING_WENT_WRONG = "Sorry! Something went wrong. Please try again";
	
	public final String LOGIN_DETAILS = "Please enter your e-mail and password";
	public final String REGISTERED_EMAIL = "Please enter a registered e-mail address";
	public final String NOT_REGISTERED = "You are not currently registered. Please go to the sign-up page to register";
	public final String ENTER_PASSWORD = "Please enter your password";
	public final String PASSWORD_NOT_SET = "You have not set your password.";
	public final String INCORRECT_PASSWORD = "Your password is incorrect";
	
	public final String ENTER_REGISTERED_EMAIL = "Please enter registered E-mail id";
	public final String INVALID_EMAIL_ADDRESS = "The e-mail address entered is invalid";
	public final String EMAIL_REQ_FAILED = "Email request failed";
	
	public final String EMAIL_PASSWORD_REQUIRED = "Please enter your e-mail and password";
	public final String ENTER_NEW_PASSWORD = "Please enter your new password";
	public final String ENTER_OLD_PASSWORD = "Please enter your old password";
	public final String PROVIDE_EMAIL_PASSWORD = "Please provide e-mail and new password";
	public final String CONFIRM_EMAIL_PASSWORD = "Please provide e-mail and confirm new password";
	public final String PWDS_NOT_MATCHED="Your passwords do not match.";
	
	public final String BOTH_PASSWORD_REQUIRED = "Please enter your old password and new password";
	public final String PROVIDE_OLD_PASSWORD = "Please provide your old password";
	public final String PROVIDE_NEW_PASSWORD = "Please provide new password";
	public final String CONFIRM_NEW_PASSWORD = "Please confirm your new password";
	public final String PASSWORD_NOT_MATCHED = "Both passwords do not match";
	public final String USER_NOT_REGISTERED = "User not registered yet!";
	public final String CORRECT_PASSWORD = "Please enter correct password";
	public final String UPDATE_PASSWORD_FAILED = "Your password update has failed. Please try again";
	
	public final String PROVIDE_COMPANY_NAME = "Please provide your Company name";
	public final String CHECK_EMAILID_FORMAT = "Please check the E-mail format";
	
	public final String UPDATE_PROFILE_DETAILS = "Please provide your profile details";
	public final String ACCOUNT_NOT_REGISTERED = "Your e-mail address is not associated with a registered InstantAnalytics account";
	public final String DETAILS_NOT_MATCHED = "Your login details do not match, please try again";
	
	public final String PROVIDE_PICTURE = "Please provide a picture";
	
	public final String PROVIDE_AUTH_TOKEN = "Please provide valid authorization token";
	public final String PROVIDE_VALID_AUTHCODE = "Please provide valid authorization code";
	public final String PROVIDE_ACCESS_TOKEN="Please provide access token";
	public final String CONNECTION_TYPE_REQUIRED="Please provide connection type";
	
	public final String DETAILS_ALREADY_STORED = "Your connection details already exist";
	public final String LONGLIVED_TOKEN_ERROR = "Sorry! unable to get long lived token";
	public final String GRAPH_API_FAILED = "Graph api failed - please try again!";
	
	public final String CONNECT_ACCOUNT_AGAIN = "Please connect to your account again";
	
	public final String AD_ACCOUNT_REQUIRED="Please select an Ad account";
	public final String DASHBOARD_ID_REQUIRED="Please select a dashboard";
	public final String TABLEAU_INFO_EXISTS = "Your Tableau Server information is already stored";
	public final String ADACCOUNT_NOT_FOUND = "No ad accounts found";
	public final String ALREADY_CONNECTED = "You are already connected";
	
	// Error messages for TableauServerService
	public final String PROVIDE_SERVER_DETAILS = "Please provide server details";
	public final String PROVIDE_SERVER_NAME = "Please enter your Tableau Online or Server Name";
	public final String PROVIDE_USERNAME = "Please enter your username";
	public final String PROVIDE_PASSWORD = "Please enter your password";
	public final String PROVIDE_SERVER_VERSION = "Please enter your Tableau environment version";
	public final String PROVIDE_SITE_NAME = "Please enter the Tableau Site you wish to publish the content to";
	public final String SERVER_DETAILS_EXISTS = "Your Tableau environment information is already stored";
	
	public final String PROJECT_ALREADY_CREATED = "Your project is already created";
	public final String INVALID_TABLEAU_URL = "Invalid Tableau URL. Please validate the URL provided";
	public final String INVALID_TABLEAU_CREDS = "Please enter valid Tableau credentials";
	public final String TABLEAU_CREDS_ALREADY_STORED = "Your Tableau credentials already stored!";
	
	public final String SUBSCRIPTION_FAILED = "Your subscription has failed. Please try again";
	public final String COUPON_EXPIRED = "This coupon code is expired";
	public final String COMPLETE_YOUR_PAYMENT = "Please complete your payment to proceed";
	public final String UPDATE_YOUR_PAYMENT = "Please update your payment to proceed";
	
	public final String USER_NOT_FOUND = "User not found in the db";
	public final String INVALID_EMAIL = "Invalid e-mail address";
	public final String DISCONNECT_FAILED = "Disconnect failed";
	public final String INSERTION_FAILED = "User details insertion failed";
	public final String CONNECT_FAILED = "User connect failed";
	public final String VIEWS_NOT_FOUND="Users does not have view id";
	public final String ACCOUNTS_NOT_FOUND = "User does not have ad account id";
	public final String WEBPROPERTY_NOT_FOUND = "Unable to get user web property id";
	public final String USER_ACCOUNTID_REQUIRED = "Please provide user account id";
	public final String ACCOUNTID_NOT_FOUND = "Unable to get user account id";
	public final String GA_USER_ACCOUNT = "User does not have google analytics ad account! Please create one and login again";
	public final String USER_ACCOUNT_NOT_FOUND = "User does not have ad account! Please create one and login again";
	public final String PROVIDE_CORRECT_TYPE = "Please provide valid connection type";
	public final String PROVIDE_ACCOUNT_NAME = "Please provide account name for the connection";

}