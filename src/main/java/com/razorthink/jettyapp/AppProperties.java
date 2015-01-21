package com.razorthink.jettyapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties
{
	private static AppProperties instance = null;
	private Properties props = null;
	private AppProperties ()
	{
		try
		{
			InputStream propInput = getClass().getClassLoader().getResourceAsStream("mailgateway.properties");
			
			props = new Properties();
			props.load( propInput );
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch( IOException e )
		{
			e.printStackTrace();
		}
		
	}
	
	public static AppProperties getInstance(){
		if (instance == null){
			instance = new AppProperties();
		}
		return instance;
	}
	
	public Properties getProperties(){
		return props;
	}
}
