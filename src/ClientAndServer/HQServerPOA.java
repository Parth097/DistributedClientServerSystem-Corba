package ClientAndServer;


/**
 * Generated from IDL interface "HQServer".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public abstract class HQServerPOA
	extends org.omg.PortableServer.Servant
	implements org.omg.CORBA.portable.InvokeHandler, ClientAndServer.HQServerOperations
{
	static private final java.util.HashMap<String,Integer> m_opsHash = new java.util.HashMap<String,Integer>();
	static
	{
		m_opsHash.put ( "reg_lms", Integer.valueOf(0));
		m_opsHash.put ( "show_data", Integer.valueOf(1));
	}
	private String[] ids = {"IDL:ClientAndServer/HQServer:1.0"};
	public ClientAndServer.HQServer _this()
	{
		org.omg.CORBA.Object __o = _this_object() ;
		ClientAndServer.HQServer __r = ClientAndServer.HQServerHelper.narrow(__o);
		return __r;
	}
	public ClientAndServer.HQServer _this(org.omg.CORBA.ORB orb)
	{
		org.omg.CORBA.Object __o = _this_object(orb) ;
		ClientAndServer.HQServer __r = ClientAndServer.HQServerHelper.narrow(__o);
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
			case 0: // reg_lms
			{
				java.lang.String _arg0=_input.read_string();
				java.lang.String _arg1=_input.read_string();
				_out = handler.createReply();
				reg_lms(_arg0,_arg1);
				break;
			}
			case 1: // show_data
			{
				int _arg0=_input.read_long();
				java.lang.String _arg1=_input.read_string();
				java.lang.String _arg2=_input.read_string();
				int _arg3=_input.read_long();
				java.lang.String _arg4=_input.read_string();
				_out = handler.createReply();
				show_data(_arg0,_arg1,_arg2,_arg3,_arg4);
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
