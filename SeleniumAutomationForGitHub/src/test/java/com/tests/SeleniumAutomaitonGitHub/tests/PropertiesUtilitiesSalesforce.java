package com.tests.SeleniumAutomaitonGitHub.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//import com.automation.utility.Constants;


public class PropertiesUtilitiesSalesforce {
	 public static  Properties getPropertyObject() throws IOException {
			//FileInputStream fp=new FileInputStream("C:\\Users\\Anna\\eclipse-workspace\\SeleniumAutomation\\src\\test\\configSalesforce.properties");//(Constants.APPLICATION_PROPERTIES);
			FileInputStream fp=new FileInputStream(Constants.APPLICATION_PROPERTIES);
			Properties prop=new Properties();
		    prop.load(fp);
		    return prop;

}
	 public static  String getUrl() throws IOException{
			return getPropertyObject().getProperty("url");
		}
		public static  String getUsername() throws IOException{
			return getPropertyObject().getProperty("username");
		}

		public static  String getPassword() throws IOException{
			return getPropertyObject().getProperty("password");
		}
		
		public static  String getInvalidUsername() throws IOException{
			return getPropertyObject().getProperty("login.invalid.username");
		}

		public static  String getInvalidPassword() throws IOException{
			return getPropertyObject().getProperty("login.invalid.password");
		}
		
	 
}
