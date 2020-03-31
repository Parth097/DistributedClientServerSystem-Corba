package ClientAndServer;


/**
 * Generated from IDL interface "Sensor".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public abstract class SensorPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, ClientAndServer.SensorOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "getNoxReading", Integer.valueOf(0));
	}
	private String[] ids = {"IDL:ClientAndServer/Sensor:1.0"};
	public ClientAndServer.Sensor _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		ClientAndServer.Sensor __r = ClientAndServer.SensorHelper.narrow(__o);
		return __r;
	}
	public ClientAndServer.Sensor _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		ClientAndServer.Sensor __r = ClientAndServer.SensorHelper.narrow(__o);
		return __r;
	}
	public org.omg.CORBA.portable.OutputStream _invoke(String method, org.omg.CORBA.portable.InputStream _input, org.omg.CORBA.portable.ResponseHandler handler)
		throws org.omg.CORBA.SystemException
	{
		org.omg.CORBA.portable.OutputStream _out = null;
		// do something
		// quick lookup of operation
		java.lang.Integer opsIndex = (java.lang.Integer)m_opsHash.get ( method );
		if ( null == opsIndex )
			throw new org.omg.CORBA.BAD_OPERATION(method + " not found");
		switch ( opsIndex.intValue() )
		{
			case 0: // getNoxReading
			{
				_out = handler.createReply();
				ClientAndServer.RecordHelper.write(_out,getNoxReading());
				break;
			}
		}
		return _out;
	}

	public String[] _all_interfaces(org.omg.PortableServer.POA poa, byte[] obj_id)
	{
		return ids;
	}
}
