package ClientAndServer.LMSPackage;

/**
 * Generated from IDL alias "readings_log".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public abstract class readings_logHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;

	public static void insert (org.omg.CORBA.Any any, ClientAndServer.Record[] s)
	{
		any.type (type ());
		write (any.create_output_stream (), s);
	}

	public static ClientAndServer.Record[] extract (final org.omg.CORBA.Any any)
	{
		if ( any.type().kind() == org.omg.CORBA.TCKind.tk_null)
		{
			throw new org.omg.CORBA.BAD_OPERATION ("Can't extract from Any with null type.");
		}
		return read (any.create_input_stream ());
	}

	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(readings_logHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_alias_tc(ClientAndServer.LMSPackage.readings_logHelper.id(), "readings_log",org.omg.CORBA.ORB.init().create_sequence_tc(0, org.omg.CORBA.ORB.init().create_struct_tc(ClientAndServer.RecordHelper.id(),"Record",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("areaName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("locationName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("timeStamp", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("currentDate", org.omg.CORBA.ORB.init().create_string_tc(0), null)})));
				}
			}
		}
		return _type;
	}

	public static String id()
	{
		return "IDL:ClientAndServer/LMS/readings_log:1.0";
	}
	public static ClientAndServer.Record[] read (final org.omg.CORBA.portable.InputStream _in)
	{
		ClientAndServer.Record[] _result;
		int _l_result0 = _in.read_long();
		try
		{
			 int x = _in.available();
			 if ( x > 0 && _l_result0 > x )
				{
					throw new org.omg.CORBA.MARSHAL("Sequence length too large. Only " + x + " available and trying to assign " + _l_result0);
				}
		}
		catch (java.io.IOException e)
		{
		}
		_result = new ClientAndServer.Record[_l_result0];
		for (int i=0;i<_result.length;i++)
		{
			_result[i]=ClientAndServer.RecordHelper.read(_in);
		}

		return _result;
	}

	public static void write (final org.omg.CORBA.portable.OutputStream _out, ClientAndServer.Record[] _s)
	{
		
		_out.write_long(_s.length);
		for (int i=0; i<_s.length;i++)
		{
			ClientAndServer.RecordHelper.write(_out,_s[i]);
		}

	}
}
