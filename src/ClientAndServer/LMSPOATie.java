package ClientAndServer;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "LMS".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public class LMSPOATie
	extends LMSPOA
{
	private LMSOperations _delegate;

	private POA _poa;
	public LMSPOATie(LMSOperations delegate)
	{
		_delegate = delegate;
	}
	public LMSPOATie(LMSOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public LMSOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(LMSOperations delegate)
	{
		_delegate = delegate;
	}
	public POA _default_POA()
	{
		if (_poa != null)
		{
			return _poa;
		}
		return super._default_POA();
	}
	public void recordedInfo(ClientAndServer.Record reading)
	{
_delegate.recordedInfo(reading);
	}

	public java.lang.String getNoxReading(java.lang.String sensorName)
	{
		return _delegate.getNoxReading(sensorName);
	}

	public ClientAndServer.Record[] log()
	{
		return _delegate.log();
	}

	public void send_data()
	{
_delegate.send_data();
	}

}
