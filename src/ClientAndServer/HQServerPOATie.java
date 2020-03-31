package ClientAndServer;

import org.omg.PortableServer.POA;

/**
 * Generated from IDL interface "HQServer".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public class HQServerPOATie
	extends HQServerPOA
{
	private HQServerOperations _delegate;

	private POA _poa;
	public HQServerPOATie(HQServerOperations delegate)
	{
		_delegate = delegate;
	}
	public HQServerPOATie(HQServerOperations delegate, POA poa)
	{
		_delegate = delegate;
		_poa = poa;
	}
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
	public HQServerOperations _delegate()
	{
		return _delegate;
	}
	public void _delegate(HQServerOperations delegate)
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
	public void reg_lms(java.lang.String serverName, java.lang.String ior)
	{
_delegate.reg_lms(serverName,ior);
	}

	public void show_data(int value, java.lang.String areaName, java.lang.String locationName, int timeStamp, java.lang.String currentDate)
	{
_delegate.show_data(value,areaName,locationName,timeStamp,currentDate);
	}

}
