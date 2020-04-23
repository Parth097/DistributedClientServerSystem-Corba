import ClientAndServer.*;
import ClientAndServer.Record;

import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;


class HQServant extends HQServerPOA {
	private HQServer parent;
	public ORB orb;
	public ClientAndServer.LMS lms_Server;

	HQServant(HQServer parentGUI, ORB orb_value) {

		parent = parentGUI;
		orb = orb_value;

	}

	@Override
	public void show_data(int value, String areaName, String locationName, int timeStamp, String currentDate) {

		System.out.println("Value: " + value + " Area Name: " + areaName + " Location Name: " + locationName + " Time Stamp: " + timeStamp + " Date: " + currentDate);

		String result = "Value: ".concat(String.valueOf(value)) + " Area Name: " + areaName + " Location Name: " + locationName + " Time: " + timeStamp + " Date: " + currentDate;

		parent.defaultListModel.addElement(result);

	}

	@Override
	public void reg_lms(String serverName, String ior) {
		parent.LMSAndIORList.addElement(serverName);
	}
}

public class HQServer {

	private JFrame frame;
	public DefaultListModel<String> defaultListModel;
	public DefaultListModel<String> LMSAndIORList;
	public DefaultListModel<String> sensorDefaultListModel;
	public ClientAndServer.HQServer HQ;
	private JMenuItem mntmSeeSensors;
	private String lmsServerName;
	private String sensorServerName;
	private JList sensorJList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HQServer window = new HQServer(args);
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
	public HQServer(String[] args) {
		initialize(args);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String[] args) {

		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// get reference to rootpoa & activate the POAManager
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();

			// create servant and register it with the ORB
			HQServant helloRef = new HQServant(this, orb);

			// get the 'stringified IOR'
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloRef);
			String stringified_ior = orb.object_to_string(ref);

			// Save IOR to file
			BufferedWriter out = new BufferedWriter(new FileWriter("server.ref"));
			out.write(stringified_ior);
			out.close();

			// wait for invocations from clients
			System.out.println("Head Quaters Server started.  Waiting for information from Local Monitoring System...");
			//orb.run();

			defaultListModel = new DefaultListModel<>();
			LMSAndIORList = new DefaultListModel<>();
			sensorDefaultListModel = new DefaultListModel<>();

			frame = new JFrame();
			frame.setBounds(100, 100, 950, 906);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			JPopupMenu popupMenu = new JPopupMenu();
			addPopup(frame.getContentPane(), popupMenu);
			
			mntmSeeSensors = new JMenuItem("See Sensors");
			mntmSeeSensors.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					populateSensorList(args);
				}
			});
			popupMenu.add(mntmSeeSensors);

			JLabel lblHeadQuaters = new JLabel("Head Quaters");
			lblHeadQuaters.setFont(new Font("Didot", Font.BOLD, 20));
			lblHeadQuaters.setBounds(406, 6, 137, 34);
			frame.getContentPane().add(lblHeadQuaters);

			JList list = new JList();
			list.setModel(defaultListModel);
			//list.setBounds(26, 59, 898, 213);
			//frame.getContentPane().add(list);

			JLabel lblAlarmList = new JLabel("Alarm List");
			lblAlarmList.setFont(new Font("Didot", Font.PLAIN, 16));
			lblAlarmList.setBounds(26, 48, 81, 16);
			frame.getContentPane().add(lblAlarmList);
			
			JScrollPane scrollPane = new JScrollPane(list);
			scrollPane.setBounds(22, 76, 906, 117);
			frame.getContentPane().add(scrollPane);
			
			JLabel lblLocalMonitoringSystem = new JLabel("Connected Local Monitoring System");
			lblLocalMonitoringSystem.setFont(new Font("Didot", Font.PLAIN, 16));
			lblLocalMonitoringSystem.setBounds(26, 205, 274, 27);
			frame.getContentPane().add(lblLocalMonitoringSystem);
			
			JList listLMSAndIOR = new JList();
			listLMSAndIOR.setModel(LMSAndIORList);


			JScrollPane scrollPane_LMS_IOR = new JScrollPane(listLMSAndIOR);
			scrollPane_LMS_IOR.setBounds(31, 244, 379, 296);
			frame.getContentPane().add(scrollPane_LMS_IOR);
			
			JLabel lblLogs = new JLabel("Log Output");
			lblLogs.setFont(new Font("Didot", Font.PLAIN, 16));
			lblLogs.setBounds(473, 206, 99, 25);
			frame.getContentPane().add(lblLogs);
			
			JList listLogs = new JList();
			DefaultListModel logsModel = new DefaultListModel<>();
			listLogs.setModel(logsModel);
			
			JScrollPane scrollPane_Logs = new JScrollPane(listLogs);
			scrollPane_Logs.setBounds(478, 244, 450, 296);
			frame.getContentPane().add(scrollPane_Logs);
			
			JButton btnViewLogs = new JButton("View Logs");
			btnViewLogs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String location = JOptionPane.showInputDialog("Enter LMS Location");
						logsModel.removeAllElements();
						// create and initialize the ORB
						ORB orb = ORB.init(args, null);

						// read in the 'stringified IOR' of the Relay

						BufferedReader in = new BufferedReader(new FileReader(location+"localMonitoringSystem.ref"));
						String stringified_ior = in.readLine();

						// get object reference from stringified IOR
						org.omg.CORBA.Object server_ref = orb.string_to_object(stringified_ior);

						ClientAndServer.LMS relay = ClientAndServer.LMSHelper.narrow(server_ref);

						Record[] myLogs = relay.log();
						for(Record s : myLogs) 
							logsModel.addElement("Value: " + s.value + " Area Name: " + s.areaName + " Location Name: " + s.locationName + " Time Stamp: " + s.timeStamp + " Date: " + s.currentDate);
						
					} catch (Exception e1) {
						System.out.println("ERROR : " + e1);
						e1.printStackTrace(System.out);
					}
	        
				}
			});
			btnViewLogs.setBounds(645, 552, 117, 29);
			frame.getContentPane().add(btnViewLogs);
			
			JLabel noxReadinglabel = new JLabel("Sensor Rox Reading");
			noxReadinglabel.setFont(new Font("Didot", Font.PLAIN, 16));
			noxReadinglabel.setBounds(26, 564, 163, 27);
			frame.getContentPane().add(noxReadinglabel);
			
			JTextArea textAreaNoxReading = new JTextArea();
			textAreaNoxReading.setEditable(false);
			textAreaNoxReading.setBounds(31, 603, 384, 152);
			frame.getContentPane().add(textAreaNoxReading);
			
			JButton btnNoxReading = new JButton("Nox Reading");
			btnNoxReading.setBounds(30, 768, 117, 29);
			frame.getContentPane().add(btnNoxReading);
			
			JLabel lblSensors = new JLabel("Sensors");
			lblSensors.setBounds(524, 557, 61, 16);
			frame.getContentPane().add(lblSensors);
			
			 sensorJList = new JList();
			sensorJList.setBounds(499, 603, 137, 131);
			frame.getContentPane().add(sensorJList);
			
			JPopupMenu popupMenu_1 = new JPopupMenu();
			popupMenu_1.setBounds(73, 14, 200, 50);
			frame.getContentPane().add(popupMenu_1);
			
			JMenuItem mntmTurnOn = new JMenuItem("Turn On");
			mntmTurnOn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					turnSensorOn(args);
				}
			});
			popupMenu_1.add(mntmTurnOn);
			
			JMenuItem mntmViewStatus = new JMenuItem("View Status");
			mntmViewStatus.setBounds(0, 0, 153, 19);
			frame.getContentPane().add(mntmViewStatus);
			
			JMenuItem mntmTurnOff = new JMenuItem("Turn off");
			mntmTurnOff.setBounds(0, 0, 153, 19);
			frame.getContentPane().add(mntmTurnOff);

			popupMenu_1.add(mntmTurnOff);
			popupMenu_1.add(mntmViewStatus);

			mntmTurnOff.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					turnSensorOff(args);
				}
			});

			mntmViewStatus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					checkStatus(args);
				}
			});


			listLMSAndIOR.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					if (SwingUtilities.isRightMouseButton(me)    // if right mouse button clicked
							&& !listLMSAndIOR.isSelectionEmpty()            // and list selection is not empty
							&& listLMSAndIOR.locationToIndex(me.getPoint()) // and clicked point is
							== listLMSAndIOR.getSelectedIndex()) {       //   inside selected item bounds
						lmsServerName = LMSAndIORList.get(listLMSAndIOR.getSelectedIndex());
						popupMenu.show(listLMSAndIOR, me.getX(), me.getY());
					}
				}
			});

			sensorJList.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent me) {
					if (SwingUtilities.isRightMouseButton(me)    // if right mouse button clicked
							&& !sensorJList.isSelectionEmpty()            // and list selection is not empty
							&& sensorJList.locationToIndex(me.getPoint()) // and clicked point is
							== sensorJList.getSelectedIndex()) {       //   inside selected item bounds
						sensorServerName = sensorDefaultListModel.get(sensorJList.getSelectedIndex());
						popupMenu_1.show(sensorJList, me.getX(), me.getY());
					}
				}
			});








			btnNoxReading.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String lms_Name = JOptionPane.showInputDialog("Enter The Local Monitoring Station Name:");
					String sensor_Name = JOptionPane.showInputDialog("Enter The Sensor Name:");
					try {
						// create and initialize the ORB
						ORB orb = ORB.init(args, null);

						// read in the 'stringified IOR' of the Relay

						BufferedReader in = new BufferedReader(new FileReader(lms_Name+"localMonitoringSystem.ref"));
						String stringified_ior = in.readLine();

						// get object reference from stringified IOR
						org.omg.CORBA.Object server_ref = orb.string_to_object(stringified_ior);

						ClientAndServer.LMS relay = ClientAndServer.LMSHelper.narrow(server_ref);

						String noxReading = relay.getNoxReading(sensor_Name);

						textAreaNoxReading.setText(noxReading);


					} catch (Exception e1) {
						System.out.println("ERROR : " + e1);
						e1.printStackTrace(System.out);
					}
				}
				});

		} catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
	}

	private void populateSensorList(String[] args){
		try {
			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// read in the 'stringified IOR' of the Relay

			BufferedReader in = new BufferedReader(new FileReader(lmsServerName+"localMonitoringSystem.ref"));
			String stringified_ior = in.readLine();

			// get object reference from stringified IOR
			org.omg.CORBA.Object server_ref = orb.string_to_object(stringified_ior);

			ClientAndServer.LMS relay = ClientAndServer.LMSHelper.narrow(server_ref);

			sensorDefaultListModel.clear();


			ArrayList<String> stringList = new ArrayList<>(Arrays.asList(relay.sensorList()));

			for (int i= 0; i<stringList.size(); i++){
				sensorDefaultListModel.addElement(stringList.get(i));
			}

			sensorJList.setModel(sensorDefaultListModel);







		} catch (Exception e1) {
			System.out.println("ERROR : " + e1);
			e1.printStackTrace(System.out);
		}

	}

	public void turnSensorOn(String[] args){

		try{

			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// read in the 'stringified IOR' of the Relay

			BufferedReader in = new BufferedReader(new FileReader(lmsServerName+"localMonitoringSystem.ref"));
			String stringified_ior = in.readLine();

			// get object reference from stringified IOR
			org.omg.CORBA.Object server_ref = orb.string_to_object(stringified_ior);

			ClientAndServer.LMS relay = ClientAndServer.LMSHelper.narrow(server_ref);

			relay.turnOnSensor(sensorServerName);


		}catch (Exception ex){
			ex.printStackTrace();
		}

	}


	public void turnSensorOff(String[] args){
		try{

			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// read in the 'stringified IOR' of the Relay

			BufferedReader in = new BufferedReader(new FileReader(lmsServerName+"localMonitoringSystem.ref"));
			String stringified_ior = in.readLine();

			// get object reference from stringified IOR
			org.omg.CORBA.Object server_ref = orb.string_to_object(stringified_ior);

			ClientAndServer.LMS relay = ClientAndServer.LMSHelper.narrow(server_ref);

			relay.turnOffSensor(sensorServerName);


		}catch (Exception ex){
			ex.printStackTrace();
		}
	}


	public void checkStatus(String[] args){

		try{

			// create and initialize the ORB
			ORB orb = ORB.init(args, null);

			// read in the 'stringified IOR' of the Relay

			BufferedReader in = new BufferedReader(new FileReader(lmsServerName+"localMonitoringSystem.ref"));
			String stringified_ior = in.readLine();

			// get object reference from stringified IOR
			org.omg.CORBA.Object server_ref = orb.string_to_object(stringified_ior);

			ClientAndServer.LMS relay = ClientAndServer.LMSHelper.narrow(server_ref);

			Boolean status = relay.checkStatus(sensorServerName);

			JOptionPane.showMessageDialog(null, "The current status is: " + status);



		}catch (Exception ex){
			ex.printStackTrace();
		}



	}


	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	}
