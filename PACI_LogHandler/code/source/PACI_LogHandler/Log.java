package PACI_LogHandler;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;
// --- <<IS-END-IMPORTS>> ---

public final class Log

{
	// ---( internal utility methods )---

	final static Log _instance = new Log();

	static Log _newInstance() { return new Log(); }

	static Log _cast(Object o) { return (Log)o; }

	// ---( server methods )---




	public static final void init (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(init)>> ---
		// @sigtype java 3.5
		// [o] field:0:required out
		IDataCursor pipelineCursor = pipeline.getCursor();
		File log4jConfigFile = new File(LOG4J_CFG);
				
		if (log4jConfigFile.exists() && log4jConfigFile.canRead()) {
		
			ConfigurationFactory factory =  XmlConfigurationFactory.getInstance();
			ConfigurationSource configurationSource = null;
			
			try {
				configurationSource = new ConfigurationSource(new FileInputStream(log4jConfigFile));
				Configuration configuration = factory.getConfiguration(logCtx, configurationSource);
				// Get context instance
				logCtx = new LoggerContext(PACKAGE_NAME);
				
				// Start context
				logCtx.start(configuration);
				pipelineCursor.insertAfter("out", "Successfully started logger context");
				 pipelineCursor.destroy();
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				
				pipelineCursor.insertAfter("out",e.getLocalizedMessage());
				 pipelineCursor.destroy();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				pipelineCursor.insertAfter("out",e.getLocalizedMessage());
				 pipelineCursor.destroy();
			}
			
			
			
		} else {
			throw new ServiceException("Configuration file '" + LOG4J_CFG + "' does not exist or cannot be read");
			
		}
		pipelineCursor.destroy();	
		// --- <<IS-END>> ---

                
	}



	public static final void logMessage (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(logMessage)>> ---
		// @sigtype java 3.5
		// [i] field:0:required loggerName
		// [i] field:0:required logSeverity {"DEBUG","INFO","WARN","ERROR","FATAL"}
		// [i] field:0:required logMessage
		// [o] field:0:required Status
		// [o] field:0:required ErrorText
		// pipeline
		IDataCursor pipelineCursor = pipeline.getCursor();
		try{
		String	loggerName="PACI_DEFAULT";
		String	logSeverity="INFO"; // PACI default level if no match from available values
		String	logMessage="";
		
			 loggerName = IDataUtil.getString(pipelineCursor,"loggerName");		
			 logSeverity = IDataUtil.getString(pipelineCursor,"logSeverity");		
			 logMessage = IDataUtil.getString(pipelineCursor,"logMessage");
		
			Level level = Level.getLevel(logSeverity);
			Logger logger = logCtx.getLogger(loggerName);
			//pipelineCursor.insertAfter("logCtx",logger);
			//pipelineCursor.insertAfter("error_level",logger.isEnabled(Level.ERROR));
			//pipelineCursor.insertAfter("info_level",logger.isEnabled(Level.INFO));
		
			
			if (logger.isEnabled(level)) {
				logger.log(level, logMessage);			
				pipelineCursor.insertAfter("Status","OK");
			
				
			}
			else 
			{
				pipelineCursor.insertAfter("Status","Error");
				
			}
		pipelineCursor.destroy();
		
		}
			catch (Exception e)
			{
		      pipelineCursor.insertAfter("Status","KO");
		      IDataUtil.put(pipelineCursor, "ErrorText", e.toString());
		      pipelineCursor.destroy();		  e.printStackTrace();
			}	
			
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	
	private static LoggerContext logCtx  = null;
	private static final String PACKAGE_NAME = "PACI_LogHandler";
	//private static final String LOG4J_CFG = "./packages/" + PACKAGE_NAME + "/config/Log4jConfig/log4j2.xml";
	private static final String LOG4J_CFG ="C:\\SoftwareAG\\IntegrationServer\\instances\\default\\packages\\PACI_LogHandler\\config\\Log4jConfig\\log4j2.xml";
	// --- <<IS-END-SHARED>> ---
}

