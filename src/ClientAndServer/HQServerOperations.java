package ClientAndServer;


/**
 * Generated from IDL interface "HQServer".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public interface HQServerOperations
{
	/* constants */
	/* operations  */
	void show_data(int value, java.lang.String areaName, java.lang.String locationName, int timeStamp, java.lang.String currentDate);
	void reg_lms(java.lang.String serverName, java.lang.String ior);
}
