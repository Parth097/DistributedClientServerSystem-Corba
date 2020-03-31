package ClientAndServer;

/**
 * Generated from IDL interface "LMS".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public final class LMSHolder	implements org.omg.CORBA.portable.Streamable{
	 public LMS value;
	public LMSHolder()
	{
	}
	public LMSHolder (final LMS initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return LMSHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = LMSHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		LMSHelper.write (_out,value);
	}
}
