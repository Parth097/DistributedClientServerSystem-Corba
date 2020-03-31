package ClientAndServer;


/**
 * Generated from IDL interface "LMS".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public abstract class LMSHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(LMSHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:ClientAndServer/LMS:1.0", "LMS");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final ClientAndServer.LMS s)
	{
			any.insert_Object(s);
	}
	public static ClientAndServer.LMS extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:ClientAndServer/LMS:1.0";
	}
	public static LMS read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(ClientAndServer._LMSStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final ClientAndServer.LMS s)
	{
		_out.write_Object(s);
	}
	public static ClientAndServer.LMS narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ClientAndServer.LMS)
		{
			return (ClientAndServer.LMS)obj;
		}
		else if (obj._is_a("IDL:ClientAndServer/LMS:1.0"))
		{
			ClientAndServer._LMSStub stub;
			stub = new ClientAndServer._LMSStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static ClientAndServer.LMS unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ClientAndServer.LMS)
		{
			return (ClientAndServer.LMS)obj;
		}
		else
		{
			ClientAndServer._LMSStub stub;
			stub = new ClientAndServer._LMSStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
