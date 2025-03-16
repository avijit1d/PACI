package sandbox;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import FabricJavaClient.FabricESBCallSDK;
import FabricJavaClient.EnrollRegisterClientUser;
// --- <<IS-END-IMPORTS>> ---

public final class BChainConsumer

{
	// ---( internal utility methods )---

	final static BChainConsumer _instance = new BChainConsumer();

	static BChainConsumer _newInstance() { return new BChainConsumer(); }

	static BChainConsumer _cast(Object o) { return (BChainConsumer)o; }

	// ---( server methods )---




	public static final void CallBlockChainSDK (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(CallBlockChainSDK)>> ---
		// @sigtype java 3.5
		// [o] field:0:required out
		IDataCursor cursor = pipeline.getCursor();	 
		FabricESBCallSDK obj=new FabricESBCallSDK();
		String res=obj.registerMarriage("3333333333333", "00000011111", "12-dec-2020", "", "jhssgfdhdsgfhgsf", "", "REF_001", "MARRIED", "A12345", "B54321", "Marriage Registered successfully");
		System.out.println("Returned response: " + res);
		cursor.insertAfter("out", res); 
		
		 cursor.destroy();
		// --- <<IS-END>> ---

                
	}



	public static final void FabricEnroll (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(FabricEnroll)>> ---
		// @sigtype java 3.5
		EnrollRegisterClientUser obj=new EnrollRegisterClientUser();
		// --- <<IS-END>> ---

                
	}

	// --- <<IS-START-SHARED>> ---


	
	// --- <<IS-END-SHARED>> ---
}

