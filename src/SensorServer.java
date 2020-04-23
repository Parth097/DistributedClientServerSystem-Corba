import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;

import javax.swing.*;

import ClientAndServer.SensorPOA;
import org.omg.CORBA.ORB;

import ClientAndServer.Record;
import org.omg.GSSUP.GSSUPMechOID;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.FileWriter;
import java.util.Date;


class SensorServant extends SensorPOA {

	public SensorServer parent;

	SensorServant(SensorServer parentGUI){

		parent = parentGUI;
	}

	@Override
	public Record getNoxReading() {
		Record reading = new Record();
		reading.value = SensorServer.value;
		reading.areaName = SensorServer.sensorName;
		reading.locationName = SensorServer.sensorLoc;
		reading.timeStamp = SensorServer.time;
		reading.currentDate = SensorServer.currentDate;

		return reading;
	}

	@Override
	public void turnOn() {

		parent.setStatus(true);

	}

	@Override
	public void turnOff() {

		parent.setStatus(false);

	}

	@Override
	public boolean findCurrentStatus() {
		return parent.getStatus();
	}
}

public class SensorServer {

	private JFrame frame;
	private JTextField textFieldValue;
	private JTextField textFieldLocation;
	private JTextField textFieldArea;

	public static String sensorLoc;
	public static String sensorName;
	public static int value;
	public static int time;
	public static String currentDate;
	private Boolean status;

	//public ClientAndServer.Sensor sensor;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SensorServer window = new SensorServer(args);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Create the application.
	 */
	public SensorServer(String[] args) {
	    sensorLoc = JOptionPane.showInputDialog("Enter LMS to connect Sensor to");
		sensorName = JOptionPane.showInputDialog("Enter name of the Sensor");
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) {
		try {
			this.status = true;
            // create and initialize the ORB
            ORB orb = ORB.init(args, null);

            //create sensor ref file
			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			// create servant and register it with the ORB
			SensorServant sensorRef = new SensorServant(this);
			// get the 'stringified IOR'
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(sensorRef);
			String sensor_ior = orb.object_to_string(ref);
			// Save IOR to file
			BufferedWriter out = new BufferedWriter(new FileWriter(sensorName+"Sensor.ref"));
			out.write(sensor_ior);
			out.close();
			//End of sensor ref file creation


            // Connection to LMS
            BufferedReader in = new BufferedReader(new FileReader(sensorLoc+"localMonitoringSystem.ref"));
            String stringified_ior = in.readLine();
            // get object reference from stringified IOR
            org.omg.CORBA.Object server_ref =
                    orb.string_to_object(stringified_ior);
            ClientAndServer.LMS locaMonitoringSystem = ClientAndServer.LMSHelper.narrow(server_ref);

            locaMonitoringSystem.addSensor(sensorName);
			System.out.println("Connected to Local Monitoring System...");
			// End of the connection code


            frame = new JFrame();
    		frame.setBounds(100, 100, 238, 262);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		frame.getContentPane().setLayout(null);
    		
    		JLabel lblSensorTitle = new JLabel("WY Sensor 1");
    		lblSensorTitle.setBounds(81, 6, 80, 16);
    		frame.getContentPane().add(lblSensorTitle);
    		
    		JLabel lblValue = new JLabel("Value");
    		lblValue.setBounds(13, 47, 61, 16);
    		frame.getContentPane().add(lblValue);
    		
    		JLabel lblLocation = new JLabel("Area");
    		lblLocation.setBounds(13, 92, 54, 16);
    		frame.getContentPane().add(lblLocation);
    		
    		JLabel lblArea = new JLabel("Location");
    		lblArea.setBounds(13, 144, 61, 16);
    		frame.getContentPane().add(lblArea);
    		
    		textFieldValue = new JTextField();
    		textFieldValue.setHorizontalAlignment(SwingConstants.CENTER);
    		textFieldValue.setEditable(false);
    		textFieldValue.setBounds(145, 42, 36, 26);
    		frame.getContentPane().add(textFieldValue);
    		textFieldValue.setColumns(10);
    		
    		textFieldLocation = new JTextField();
    		textFieldLocation.setHorizontalAlignment(SwingConstants.CENTER);
    		textFieldLocation.setText(sensorName);
    		textFieldLocation.setEditable(false);
    		textFieldLocation.setBounds(98, 87, 130, 26);
    		frame.getContentPane().add(textFieldLocation);
    		textFieldLocation.setColumns(10);
    		
    		textFieldArea = new JTextField();
    		textFieldArea.setText(sensorLoc);
    		textFieldArea.setHorizontalAlignment(SwingConstants.CENTER);
    		textFieldArea.setEditable(false);
    		textFieldArea.setBounds(98, 139, 130, 26);
    		frame.getContentPane().add(textFieldArea);
    		textFieldArea.setColumns(10);

			JSlider slider = new JSlider();
			slider.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					if(status){
						JSlider jSlider = (JSlider) e.getSource();
						if(!jSlider.getValueIsAdjusting()){
							value = jSlider.getValue();

							Date date = new Date();
							long time_long = date.getTime();
							time = (int) time_long;
							currentDate = date.toString();

							Record record = new Record();

							record.areaName = sensorName;
							record.locationName = sensorLoc;
							record.value = value;
							record.timeStamp = time;
							record.currentDate = currentDate;
							;

							locaMonitoringSystem.recordedInfo(record);

							if(record.value>=50){
								locaMonitoringSystem.send_data();
							}

							textFieldValue.setText(String.valueOf(value));
						}
					}
					}

			});
			slider.setBounds(24, 193, 190, 29);
			frame.getContentPane().add(slider);


        } catch (Exception e) {
            System.out.println("ERROR : " + e) ;
            e.printStackTrace(System.out);
        }
    }

    public void setStatus(Boolean status){
		this.status = status;

		System.out.println(status);
	}

	public boolean getStatus(){
		return status;
	}
}
