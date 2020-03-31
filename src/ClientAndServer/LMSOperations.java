package ClientAndServer;


/**
 * Generated from IDL interface "LMS".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public interface LMSOperations
{
	/* constants */
	/* operations  */
	ClientAndServer.Record[] log();
	void recordedInfo(ClientAndServer.Record reading);
	void send_data();
	java.lang.String getNoxReading(java.lang.String sensorName);
}
