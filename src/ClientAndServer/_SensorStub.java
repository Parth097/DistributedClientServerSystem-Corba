package ClientAndServer;


/**
 * Generated from IDL interface "Sensor".
 *
 * @author JacORB IDL compiler V 3.9
 * @version generated at 31 Mar 2020, 21:21:03
 */

public class _SensorStub
	extends org.omg.CORBA.portable.ObjectImpl
	implements ClientAndServer.Sensor
{
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private String[] ids = {"IDL:ClientAndServer/Sensor:1.0"};
	public String[] _ids()
	{
		return ids;
	}

	public final static java.lang.Class _opsClass = ClientAndServer.SensorOperations.class;
	public ClientAndServer.Record getNoxReading()
	{
		while(true)
		{
			if(! this._is_local())
			{
				org.omg.CORBA.portable.InputStream _is = null;
				org.omg.CORBA.portable.OutputStream _os = null;
				try
				{
					_os = _request( "getNoxReading", true);
					_is = _invoke(_os);
					ClientAndServer.Record _result = ClientAndServer.RecordHelper.read(_is);
					return _result;
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
			org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke( "getNoxReading", _opsClass );
			if( _so == null )
				continue;
			SensorOperations _localServant = (SensorOperations)_so.servant;
			ClientAndServer.Record _result;
			try
			{
				_result = _localServant.getNoxReading();
				if ( _so instanceof org.omg.CORBA.portable.ServantObjectExt) 
					((org.omg.CORBA.portable.ServantObjectExt)_so).normalCompletion();
				return _result;
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
