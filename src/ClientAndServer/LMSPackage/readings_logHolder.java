package ClientAndServer.LMSPackage;

/**
 * Generated from IDL alias "readings_log".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public final class readings_logHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ClientAndServer.Record[] value;

	public readings_logHolder ()
	{
	}
	public readings_logHolder (final ClientAndServer.Record[] initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return readings_logHelper.type ();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = readings_logHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream out)
	{
		readings_logHelper.write (out,value);
	}
}
