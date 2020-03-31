package ClientAndServer;


/**
 * Generated from IDL interface "LMS".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public abstract class LMSPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, ClientAndServer.LMSOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "recordedInfo", Integer.valueOf(0));
		m_opsHash.put ( "getNoxReading", Integer.valueOf(1));
		m_opsHash.put ( "_get_log", Integer.valueOf(2));
		m_opsHash.put ( "send_data", Integer.valueOf(3));
	}
	private String[] ids = {"IDL:ClientAndServer/LMS:1.0"};
	public ClientAndServer.LMS _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		ClientAndServer.LMS __r = ClientAndServer.LMSHelper.narrow(__o);
		return __r;
	}
	public ClientAndServer.LMS _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		ClientAndServer.LMS __r = ClientAndServer.LMSHelper.narrow(__o);
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
			case 0: // recordedInfo
			{
				ClientAndServer.Record _arg0=ClientAndServer.RecordHelper.read(_input);
				_out = handler.createReply();
				recordedInfo(_arg0);
				break;
			}
			case 1: // getNoxReading
			{
				java.lang.String _arg0=_input.read_string();
				_out = handler.createReply();
				java.lang.String tmpResult9 = getNoxReading(_arg0);
_out.write_string( tmpResult9 );
				break;
			}
			case 2: // _get_log
			{
			_out = handler.createReply();
			ClientAndServer.LMSPackage.readings_logHelper.write(_out,log());
				break;
			}
			case 3: // send_data
			{
				_out = handler.createReply();
				send_data();
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
