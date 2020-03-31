package ClientAndServer;

/**
 * Generated from IDL struct "Record".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public final class RecordHolder
	implements org.omg.CORBA.portable.Streamable
{
	public ClientAndServer.Record value;

	public RecordHolder ()
	{
	}
	public RecordHolder(final ClientAndServer.Record initial)
	{
		value = initial;
	}
	public org.omg.CORBA.TypeCode _type ()
	{
		return ClientAndServer.RecordHelper.type ();
	}
	public void _read(final org.omg.CORBA.portable.InputStream _in)
	{
		value = ClientAndServer.RecordHelper.read(_in);
	}
	public void _write(final org.omg.CORBA.portable.OutputStream _out)
	{
		ClientAndServer.RecordHelper.write(_out, value);
	}
}
