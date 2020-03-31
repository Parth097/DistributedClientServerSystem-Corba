package ClientAndServer;


/**
 * Generated from IDL struct "Record".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public abstract class RecordHelper
{
	private volatile static org.omg.CORBA.TypeCode _type;
	public static org.omg.CORBA.TypeCode type ()
	{
		if (_type == null)
		{
			synchronized(RecordHelper.class)
			{
				if (_type == null)
				{
					_type = org.omg.CORBA.ORB.init().create_struct_tc(ClientAndServer.RecordHelper.id(),"Record",new org.omg.CORBA.StructMember[]{new org.omg.CORBA.StructMember("areaName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("locationName", org.omg.CORBA.ORB.init().create_string_tc(0), null),new org.omg.CORBA.StructMember("value", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("timeStamp", org.omg.CORBA.ORB.init().get_primitive_tc(org.omg.CORBA.TCKind.from_int(3)), null),new org.omg.CORBA.StructMember("currentDate", org.omg.CORBA.ORB.init().create_string_tc(0), null)});
				}
			}
		}
		return _type;
	}

	public static void insert (final org.omg.CORBA.Any any, final ClientAndServer.Record s)
	{
		any.type(type());
		write( any.create_output_stream(),s);
	}

	public static ClientAndServer.Record extract (final org.omg.CORBA.Any any)
	{
		org.omg.CORBA.portable.InputStream in = any.create_input_stream();
		try
		{
			return read (in);
		}
		finally
		{
			try
			{
				in.close();
			}
			catch (java.io.IOException e)
			{
			throw new RuntimeException("Unexpected exception " + e.toString() );
			}
		}
	}

	public static String id()
	{
		return "IDL:ClientAndServer/Record:1.0";
	}
	public static ClientAndServer.Record read (final org.omg.CORBA.portable.InputStream in)
	{
		ClientAndServer.Record result = new ClientAndServer.Record();
		result.areaName=in.read_string();
		result.locationName=in.read_string();
		result.value=in.read_long();
		result.timeStamp=in.read_long();
		result.currentDate=in.read_string();
		return result;
	}
	public static void write (final org.omg.CORBA.portable.OutputStream out, final ClientAndServer.Record s)
	{
		java.lang.String tmpResult0 = s.areaName;
out.write_string( tmpResult0 );
		java.lang.String tmpResult1 = s.locationName;
out.write_string( tmpResult1 );
		out.write_long(s.value);
		out.write_long(s.timeStamp);
		java.lang.String tmpResult2 = s.currentDate;
out.write_string( tmpResult2 );
	}
}
