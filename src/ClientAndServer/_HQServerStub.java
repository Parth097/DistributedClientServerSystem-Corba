package ClientAndServer;


/**
 * Generated from IDL interface "HQServer".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public class _HQServerStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements ClientAndServer.HQServer
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:ClientAndServer/HQServer:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = ClientAndServer.HQServerOperations.class;
	public void reg_lms(java.lang.String serverName, java.lang.String ior)
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "reg_lms", true);
					java.lang.String tmpResult3 = serverName;
_os.write_string( tmpResult3 );
					java.lang.String tmpResult4 = ior;
_os.write_string( tmpResult4 );
					_is = _invoke(_os);
					return;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "reg_lms", _opsClass );
			if( _so == null )
				continue;
			HQServerOperations _localServant = (HQServerOperations)_so.servant;
			try
			{
				_localServant.reg_lms(serverName,ior);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

	public void show_data(int value, java.lang.String areaName, java.lang.String locationName, int timeStamp, java.lang.String currentDate)
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "show_data", true);
					_os.write_long(value);
					java.lang.String tmpResult5 = areaName;
_os.write_string( tmpResult5 );
					java.lang.String tmpResult6 = locationName;
_os.write_string( tmpResult6 );
					_os.write_long(timeStamp);
					java.lang.String tmpResult7 = currentDate;
_os.write_string( tmpResult7 );
					_is = _invoke(_os);
					return;
				}
				catch( org.omg.CORBA.portable.RemarshalException _rx )
					{
						continue;
					}
				catch( org.omg.CORBA.portable.ApplicationException _ax )
				{
					String _id = _ax.getId();
					try
					{
							_ax.getInputStream().close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
					throw new RuntimeException("Unexpected exception " + _id );
			}
			finally
			{
				if (_os != null)
				{
					try
					{
						_os.close();
					}
					catch (java.io.IOException e)
					{
						throw new RuntimeException("Unexpected exception " + e.toString() );
					}
				}
				this._releaseReply(_is);
			}
		}
		else
		{
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "show_data", _opsClass );
			if( _so == null )
				continue;
			HQServerOperations _localServant = (HQServerOperations)_so.servant;
			try
			{
				_localServant.show_data(value,areaName,locationName,timeStamp,currentDate);
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return;
			}
			catch (RuntimeException re) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(re);
				throw re;
			}
			catch (java.lang.Error err) 
			{
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).exceptionalCompletion(err);
				throw err;
			}
			finally
			{
				_servant_postinvoke(_so);
			}
		}

		}

	}

}
