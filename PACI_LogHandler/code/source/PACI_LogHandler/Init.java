package PACI_LogHandler;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.*;
import java.io.*;
import org.apache.log4j.*;
import org.xml.sax.*;
// --- <<IS-END-IMPORTS>> ---

public final class Init

{
	// ---( internal utility methods )---

	final static Init _instance = new Init();

	static Init _newInstance() { return new Init(); }

	static Init _cast(Object o) { return (Init)o; }

	// ---( server methods )---




	public static final void logConfig (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(logConfig)>> ---
		// @sigtype java 3.5
		// [o] field:0:required Status
		// [o] field:0:required filepath
		//Retrieve input parameters from the pipeline
		IDataHashCursor pipelineCursor = pipeline.getHashCursor();
		String	loggingPropertiesFileLocation = "."+File.separator+"packages"+File.separator+"PACI_LogHandler"+File.separator+"config"+File.separator+"Log4jConfig"+File.separator+"PACI_LOG.properties";
		//if (pipelineCursor.first( "loggingPropertiesFileLocation" ))
			//loggingPropertiesFileLocation = (String) pipelineCursor.getValue();
		String msg = "";
		String status = "";
		
		/*
		  Configure the logger by retrieving a log4j properties file.  
		  Alter logging behavior by modifying the properties file
		*/
		
		//Attempt to locate log4j properties file
		File log4j_config_file = new File(loggingPropertiesFileLocation);
		if (log4j_config_file.exists()) {
		  PropertyConfigurator.configure(loggingPropertiesFileLocation);
		  org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger("PACIDEFAULTLOG");
		  msg = "PACI loggers started. Logging Config file used: " + loggingPropertiesFileLocation;
		  logger.info(msg); 	  
		 		  status = "OK";
		}
		else {
		  		  msg = "WARN: Logging Properties file not found ";	  
		  //set status
		  status = "KO";
		}
		
		//Put status into pipeline
		pipelineCursor.insertAfter("Status",status);
		pipelineCursor.insertAfter("filepath",loggingPropertiesFileLocation);
		
		//Always destroy cursors
		pipelineCursor.destroy();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---
	//To Do: Print in server log msg and status
		
	// --- <<IS-END-SHARED>> ---
}

