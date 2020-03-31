package ClientAndServer;


/**
 * Generated from IDL interface "HQServer".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public abstract class HQServerHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(HQServerHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_interface_tc("IDL:ClientAndServer/HQServer:1.0", "HQServer");
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final ClientAndServer.HQServer s)
	{
			any.insert_Object(s);
	}
	public static ClientAndServer.HQServer extract(final org.omg.CORBA.Any any)
	{
		return narrow(any.extract_Object()) ;
	}
	public static String id()
	{
		return "IDL:ClientAndServer/HQServer:1.0";
	}
	public static HQServer read(final org.omg.CORBA.portable.InputStream in)
	{
		return narrow(in.read_Object(ClientAndServer._HQServerStub.class));
	}
	public static void write(final org.omg.CORBA.portable.OutputStream _out, final ClientAndServer.HQServer s)
	{
		_out.write_Object(s);
	}
	public static ClientAndServer.HQServer narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ClientAndServer.HQServer)
		{
			return (ClientAndServer.HQServer)obj;
		}
		else if (obj._is_a("IDL:ClientAndServer/HQServer:1.0"))
		{
			ClientAndServer._HQServerStub stub;
			stub = new ClientAndServer._HQServerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
		else
		{
			throw new org.omg.CORBA.BAD_PARAM("Narrow failed");
		}
	}
	public static ClientAndServer.HQServer unchecked_narrow(final org.omg.CORBA.Object obj)
	{
		if (obj == null)
		{
			return null;
		}
		else if (obj instanceof ClientAndServer.HQServer)
		{
			return (ClientAndServer.HQServer)obj;
		}
		else
		{
			ClientAndServer._HQServerStub stub;
			stub = new ClientAndServer._HQServerStub();
			stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
			return stub;
		}
	}
}
