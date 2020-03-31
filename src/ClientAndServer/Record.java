package ClientAndServer;

/**
 * Generated from IDL struct "Record".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public final class Record
	implements org.omg.CORBA.portable.IDLEntity
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	public Record(){}
	public java.lang.String areaName = "";
	public java.lang.String locationName = "";
	public int value;
	public int timeStamp;
	public java.lang.String currentDate = "";
	public Record(java.lang.String areaName, java.lang.String locationName, int value, int timeStamp, java.lang.String currentDate)
	{
		this.areaName = areaName;
		this.locationName = locationName;
		this.value = value;
		this.timeStamp = timeStamp;
		this.currentDate = currentDate;
	}
}
