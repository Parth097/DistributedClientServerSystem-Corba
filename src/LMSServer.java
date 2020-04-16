import ClientAndServer.*;
import ClientAndServer.LMSPackage.readings_logHolder;
import ClientAndServer.Record;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import org.omg.CORBA.*;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


class LMSServant extends LMSPOA {

	private ORB orb;
	private ClientAndServer.HQServer server;
	private ClientAndServer.Sensor sensorServer;
	private ArrayList<Record> recordArrayList;
	readings_logHolder readingsHolder = new readings_logHolder();

	LMSServant(ORB orb_val) {
		recordArrayList = new ArrayList<>();
		// store reference to ORB
		orb = orb_val;
	}

	public void registerServer(String serverName, String ior){
		// look up the server
		try {
			// read in the 'stringified IOR'
			BufferedReader in = new BufferedReader(new FileReader("server.ref"));
			String stringified_ior = in.readLine();

			// get object reference from stringified IOR
			org.omg.CORBA.Object server_ref =
					orb.string_to_object(stringified_ior);
			server = ClientAndServer.HQServerHelper.narrow(server_ref);
		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}

		server.reg_lms(serverName, ior);
	}


	@Override
	public Record[] log() {
		Record[] recordsArray = new Record[recordArrayList.size()];
		recordsArray = recordArrayList.toArray(recordsArray);
		return recordsArray;
	}


	@Override
	public void recordedInfo(Record reading) {
		recordArrayList.add(reading);
	}
	int num = 0;

	@Override
	public void send_data() {

		//create a new arraylist

		ArrayList<Record> tempRecord = new ArrayList<>();

		Record lastRecord = recordArrayList.get(recordArrayList.size()-1);

		String lastAreaName = lastRecord.areaName;

		if(!recordArrayList.isEmpty() && recordArrayList.size()>=2){

			for (Record record : recordArrayList) {
				String currentAreaName = record.areaName;
				if (!currentAreaName.equals(lastAreaName)) {
					tempRecord.add(record);
				}
			}
            if(!tempRecord.isEmpty()){
            	if(Math.abs(tempRecord.get(tempRecord.size()-1).timeStamp) - Math.abs(recordArrayList.get(recordArrayList.size()-1).timeStamp) <= 10000){
					Record reading = recordArrayList.get(recordArrayList.size()-1);
					server.show_data(reading.value, reading.areaName, reading.locationName, reading.timeStamp, reading.currentDate);
				}
			}
		}
	}

	@Override
	public String getNoxReading(String sensorName) {
		// look up the server
		try {
			// read in the 'stringified IOR'
			BufferedReader in = new BufferedReader(new FileReader(sensorName + "Sensor.ref"));
			String stringified_ior = in.readLine();

			// get object reference from stringified IOR
			org.omg.CORBA.Object server_ref = orb.string_to_object(stringified_ior);
			sensorServer = ClientAndServer.SensorHelper.narrow(server_ref);
		} catch (Exception e) {
			System.out.println("ERROR : " + e) ;
			e.printStackTrace(System.out);
		}
		Record reading = sensorServer.getNoxReading();
		return  "Area Name: " + reading.areaName + "\n" + "Location Name: " + reading.locationName +"\n" + "Nox Value: " + reading.value +"\n" + "Time: " + reading.timeStamp +"\n" + "Date: " + reading.currentDate;
	}

}

public class LMSServer {

	public static void main(String[] args) {
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			LMSServant relayRef = new LMSServant(orb);

			// Get the 'stringified IOR'
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(relayRef);
			String stringified_ior = orb.object_to_string(ref);

			String lmsLocation = JOptionPane.showInputDialog("Enter Location of Local Monitoring System");

			// Save IOR to file
			BufferedWriter out = new BufferedWriter(new FileWriter(lmsLocation+"localMonitoringSystem.ref"));
			out.write(stringified_ior);
			out.close();

			relayRef.registerServer(lmsLocation, stringified_ior);

			// wait for invocations from clients
			System.out.println("Local Monitoring System started.  Waiting for information from sensors...");
			orb.run();

		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}
}

