package ClientAndServer;

/**
 * Generated from IDL interface "HQServer".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public final class HQServerHolder	implements org.omg.CORBA.portable.Streamable{
	 public HQServer value;
	public HQServerHolder()
	{
	}
	public HQServerHolder (final HQServer initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type()
	{
		return HQServerHelper.type();
	}
	public void _read (final org.omg.CORBA.portable.InputStream in)
	{
		value = HQServerHelper.read (in);
	}
	public void _write (final org.omg.CORBA.portable.OutputStream _out)
	{
		HQServerHelper.write (_out,value);
	}
}
