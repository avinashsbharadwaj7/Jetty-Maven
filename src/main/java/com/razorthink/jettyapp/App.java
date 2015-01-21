package com.razorthink.jettyapp;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class App
{
	public static void main( String[] args )
	{
		ServletHolder sh = new ServletHolder( ServletContainer.class );
		sh.setInitParameter( "com.sun.jersey.config.property.resourceConfigClass",
				"com.sun.jersey.api.core.PackagesResourceConfig" );
		sh.setInitParameter( "com.sun.jersey.api.json.POJOMappingFeature", "true" );
		sh.setInitParameter( "javax.ws.rs.Application", ConfigClass.class.getCanonicalName() );

		Server server = new Server( Integer.parseInt( AppProperties.getInstance().getProperties()
				.getProperty( "port" ) ) );
		ServletContextHandler context = new ServletContextHandler( server, "/rest", ServletContextHandler.NO_SECURITY );
		context.addServlet( sh, "/*" );
		try
		{
			server.start();
			server.join();
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		finally
		{
			server.destroy();
		}
	}
}

class ConfigClass extends ResourceConfig
{
	public ConfigClass ()
	{
		packages( "com.razorthink.mailgateway.services" );
		register( JacksonJsonProvider.class );
	}
}