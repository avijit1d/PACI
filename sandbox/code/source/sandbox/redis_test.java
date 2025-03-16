package sandbox;

// -----( IS Java Code Template v1.2

import com.wm.data.*;
import com.wm.util.Values;
import com.wm.app.b2b.server.Service;
import com.wm.app.b2b.server.ServiceException;
// --- <<IS-START-IMPORTS>> ---
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisException;
// --- <<IS-END-IMPORTS>> ---

public final class redis_test

{
	// ---( internal utility methods )---

	final static redis_test _instance = new redis_test();

	static redis_test _newInstance() { return new redis_test(); }

	static redis_test _cast(Object o) { return (redis_test)o; }

	// ---( server methods )---




	public static final void redisConnectthroughSentinel (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(redisConnectthroughSentinel)>> ---
		// @sigtype java 3.5
		IDataCursor cursor = pipeline.getCursor();
		String masterName = "mymaster";
		Set<String> sentinels = new HashSet<String>();
		sentinels.add("127.0.0.1:26379");
		
		// Replace with your Sentinel hosts and ports
		JedisSentinelPool sentinelPool= new JedisSentinelPool(masterName, sentinels);
		
		try  {
		    // Perform write operations using the jedis instance
			 
			Jedis jedis = sentinelPool.getResource();
			jedis.auth("P@ssw0rd123");
		    jedis.set("name", "PACI_KUWAIT");
		    //jedis.hset("hashKey", "field", "fieldValue");
		    // ... other write operations
		} catch (JedisException e) {
		    // Handle exceptions
			//String txt=e.getStackTrace();
					//e.printStackTrace();
		    cursor.insertAfter("out",e.getStackTrace() ); 
			
			 cursor.destroy();
		} finally {
		    sentinelPool.close();
		}
		
		
			
		// --- <<IS-END>> ---

                
	}



	public static final void redisconnectTest (IData pipeline)
        throws ServiceException
	{
		// --- <<IS-START(redisconnectTest)>> ---
		// @sigtype java 3.5
		// [o] field:0:required out
		IDataCursor cursor = pipeline.getCursor();
		Jedis jedis = new Jedis("127.0.0.1",6379);  
		jedis.set("param2", "some-data"); 
		jedis.expire("param2", 60); 
		cursor.insertAfter("out", "Success"); 
		
		 cursor.destroy();
		// --- <<IS-END>> ---

                
	}
}

