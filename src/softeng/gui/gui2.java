package softeng.gui;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.CaretListener;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import java.awt.Panel;
import javax.swing.JLayeredPane;
import java.awt.SystemColor;
import javax.swing.JRadioButton;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import trafficManager.Car;
import trafficManager.Traffic;

import java.awt.Checkbox;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPopupMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import java.awt.Scrollbar;
import map.Map;
import java.awt.Font;

public class gui2 {
	private Image CityMap;
	private JFrame frame;
	private JTextField textField;
	private JCheckBox checkBox;
	public int checkboxRet[] = new int[24];
	static List<Car> activeCars = new ArrayList<Car>(); 
	static List<Car> carChange = new ArrayList<Car>(); 
	static int allArrived = 0;
	int checkOn;
	private List<JLabel> carNums = new ArrayList<JLabel>();
	private List<Car> cars = new ArrayList<Car>();
	Map map;
	int chThread = 0;
	private JTextField textField_1;
	private Icon carE;
	private Icon carW;
	private Icon carN;
	private Icon carS;
	private Image carEast;
	private Image carWest;
	private Image carNorth;
	private Image carSouth;
	private int timer;
	private int pauseCheck = 0;
	private Traffic traffic1;
	
	public static List<Car> getActiveCars() {
		return activeCars;
	}
	
	public static int getAllArrived() {
		return allArrived;
	}
	
	public static void setActiveCars(List<Car> newList) {
		activeCars = newList;
	}
	
	public static void setAllArrived(int newAll) {
		allArrived = newAll;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui2 window = new gui2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public gui2() throws InterruptedException {
		initialize();
	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws InterruptedException {
		map = new Map();
		traffic1 = new Traffic();
		try {		//loads the images to display the cars on the map
			carEast = ImageIO.read(new File("./Images/carEast.png"));
			carWest = ImageIO.read(new File("./Images/carWest.png"));
			carNorth = ImageIO.read(new File("./Images/carNorth.png"));
			carSouth = ImageIO.read(new File("./Images/carSouth.png"));
		} catch (IOException e3) {
			System.out.println("Unable to open the image to display the cars.");
		}
		//resizes the images
		carEast = carEast.getScaledInstance(20, 15, Image.SCALE_DEFAULT);
		carWest = carWest.getScaledInstance(20, 15, Image.SCALE_DEFAULT);
		carNorth = carNorth.getScaledInstance(15, 20, Image.SCALE_DEFAULT);
		carSouth = carSouth.getScaledInstance(15, 20, Image.SCALE_DEFAULT);
		//creates a new icon using the image
		carE = new ImageIcon(carEast);
		carW = new ImageIcon(carWest);
		carN = new ImageIcon(carNorth);
		carS = new ImageIcon(carSouth);
		//int timer = 0;
		for(int i = 0; i < 24; i++) {
			checkboxRet[i]=0;
			map.addIntersection(i);
		}
		frame = new JFrame();
		frame.setTitle("City Traffic Simulator");
		frame.setBackground(Color.GRAY);
		frame.setBounds(100, 100, 815, 1035);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	   // frame.getContentPane().add(new background());
	   frame.setVisible(true);
	   frame.getContentPane().setLayout(new BorderLayout(0, 0));
	   
	   background background_ = new background();
	   frame.getContentPane().add(background_);
	   
	   JLayeredPane layeredPane = new JLayeredPane();
	   GroupLayout gl_background_ = new GroupLayout(background_);
	   gl_background_.setHorizontalGroup(
	   	gl_background_.createParallelGroup(Alignment.LEADING)
	   		.addComponent(layeredPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
	   );
	   gl_background_.setVerticalGroup(
	   	gl_background_.createParallelGroup(Alignment.LEADING)
	   		.addComponent(layeredPane, GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
	   );
	   
	   ArrayList<JComboBox> comboBoxes = new ArrayList<JComboBox>();   //arraylist to hold all JComboBox objects
	   
	   JComboBox comboBox = new JComboBox();
	   comboBox.setModel(new DefaultComboBoxModel(new String[] {"Timer Traffic Light", "E-W Stop Signs", "S Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox.setBounds(146, 37, 107, 19);
	   comboBox.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox.getSelectedIndex() != -1) {                     
	               checkboxRet[0] = comboBox.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(0, comboBox.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
	   textField_1 = new JTextField();
	   textField_1.setBounds(0, 0, 800, 22);
	   layeredPane.add(textField_1);
	   textField_1.setBackground(SystemColor.window);
	   textField_1.setText("            1       2       3       4       5       6       7       8       9       10      11      12     13     14    15    16     17      18     19      20     21     22     23    24   25     26");
	   textField_1.setColumns(10);
	   textField_1.setVisible(false);
	   
	   JTextArea textArea = new JTextArea();
	   textArea.setFont(new Font("Times New Roman", Font.BOLD, 12));
	   textArea.setText("\r\n1\r\n\r\n2\r\n\r\n3\r\n\r\n\r\n4\r\n\r\n5\r\n\r\n6\r\n\r\n7\r\n\r\n\r\n8\r\n\r\n9\r\n\r\n10\r\n\r\n11\r\n\r\n\r\n12\r\n\r\n13\r\n\r\n14\r\n\r\n15\r\n\r\n16\r\n\r\n\r\n17\r\n\r\n18\r\n\r\n19\r\n\r\n20\r\n\r\n21\r\n\r\n\r\n22\r\n\r\n23\r\n\r\n24\r\n\r\n25\r\n\r\n26\r\n\r\n\r\n27\r\n\r\n28\r\n\r\n29");
	   textArea.setBounds(780, 24, 19, 930);
	   layeredPane.add(textArea);
	   layeredPane.add(comboBox);
	   comboBoxes.add(comboBox);
	   textArea.setVisible(false);
	   
	   ArrayList<JPanel> panels = new ArrayList<JPanel>();   //arraylist to hold all JComboBox objects
	   int[][] count = new int[24][4]; // 0 = West, 1 = South, 2 = East, 3 = North
	   
	   JPanel panel = new JPanel();
	   panel.setBackground(Color.RED);
	   panel.setForeground(Color.RED);
	   panel.setBounds(156, 26, 10, 47);
	   panel.setVisible(false);
   	   layeredPane.add(panel);
   	   panels.add(panel);
   	   count[0][0] = 1;
   	   
   	  JPanel panel_1 = new JPanel();
	   panel_1.setBackground(Color.RED);
	   panel_1.setForeground(Color.RED);
	   panel_1.setBounds(167, 63, 47, 10);
	   panel_1.setVisible(false);
 	   layeredPane.add(panel_1);
 	   panels.add(panel_1);
 	   count[0][2] = 1;
 	  
 	   
 	   JPanel panel_2 = new JPanel();
	   panel_2.setBounds(204, 26, 10, 47);
	   panel_2.setBackground(Color.RED);
	   panel_2.setForeground(Color.RED);
	   panel_2.setVisible(false);
	   layeredPane.add(panel_2);
	   panels.add(panel_2);
	   count[0][1] = 1;
	   
	   JPanel panel_3 = new JPanel();
	   panel_3.setBounds(772, 0, 10, 10);
	   panel_3.setVisible(false);
	   layeredPane.add(panel_3);
	   panels.add(panel_3);
	   count[0][3]=0;
	   
	   JComboBox comboBox_1 = new JComboBox();
	   comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "S Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_1.setBounds(562, 37, 112, 19);
	   comboBox_1.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_1.getSelectedIndex() != -1) {                     
	               checkboxRet[1] = comboBox_1.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(1, comboBox_1.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
	   JPanel panel_4 = new JPanel();
	   panel_4.setBounds(589, 26, 10, 47);
	   panel_4.setBackground(Color.RED);
	   panel_4.setForeground(Color.RED);
	   panel_4.setVisible(false);
	   layeredPane.add(panel_4);
	   panels.add(panel_4);
   	   count[1][0] = 1;
   	   
	   JPanel panel_5 = new JPanel();
	   panel_5.setBounds(589, 63, 47, 10);
	   panel_5.setBackground(Color.RED);
	   panel_5.setForeground(Color.RED);
	   panel_5.setVisible(false);
	   layeredPane.add(panel_5);
	   panels.add(panel_5);
   	   count[1][1] = 1;
	   
	   JPanel panel_6 = new JPanel();
	   panel_6.setBounds(626, 26, 10, 47);
	   panel_6.setBackground(Color.RED);
	   panel_6.setForeground(Color.RED);
	   panel_6.setVisible(false);
	   layeredPane.add(panel_6);
	   panels.add(panel_6);
   	   count[1][2] = 1;
	   
   	   JPanel panel_7 = new JPanel();
	   panel_7.setBounds(589, 20, 47, 10);
	   panel_7.setBackground(Color.RED);
	   panel_7.setForeground(Color.RED);
	   panel_7.setVisible(false);
	   layeredPane.add(panel_7);
	   panels.add(panel_7);
	   count[1][3] = 0;
   	   
	   layeredPane.add(comboBox_1);
	   comboBoxes.add(comboBox_1);
	   
	   JComboBox comboBox_2 = new JComboBox();
	   comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "S Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_2.setBounds(226, 155, 92, 19);
	   comboBox_2.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_2.getSelectedIndex() != -1) {                     
	               checkboxRet[4] = comboBox_2.getSelectedIndex();		//for debugging purposes
	               map.changeIntersection(4, comboBox_2.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	               
	            }            
	         }   
	   });
	   
	   JPanel panel_8 = new JPanel();
	   panel_8.setBounds(243, 150, 10, 47);
	   panel_8.setBackground(Color.RED);
	   panel_8.setForeground(Color.RED);
	   panel_8.setVisible(false);
	   
	   JPanel panel_24 = new JPanel();
	   panel_24.setBounds(772, 60, 10, 10);
	   panel_24.setVisible(false);
	   layeredPane.add(panel_24);
	   panels.add(panel_24);
	   
	   JPanel panel_25 = new JPanel();
	   panel_25.setBounds(30, 138, 47, 10);
	   panel_25.setBackground(Color.RED);
	   panel_25.setForeground(Color.RED);
	   panel_25.setVisible(false);
	   layeredPane.add(panel_25);
	   panels.add(panel_25);
	   
   	JPanel panel_26 = new JPanel();
   	panel_26.setBounds(71, 138, 10, 47);
   	panel_26.setBackground(Color.RED);
   	panel_26.setForeground(Color.RED);
   	panel_26.setVisible(false);
   	layeredPane.add(panel_26);
   	panels.add(panel_26);
	   
	   JPanel panel_27 = new JPanel();
	   panel_27.setBounds(30, 175, 47, 10);
	   panel_27.setBackground(Color.RED);
	   panel_27.setForeground(Color.RED);
	   panel_27.setVisible(false);
	   layeredPane.add(panel_27);
	   panels.add(panel_27);
	   
	   JComboBox comboBox_6 = new JComboBox();
	   comboBox_6.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E Stop Sign", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_6.setBounds(12, 155, 99, 19);
	   comboBox_6.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_6.getSelectedIndex() != -1) {                     
	               checkboxRet[2] = comboBox_6.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(2, comboBox_6.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
	   layeredPane.add(comboBox_6);
	   comboBoxes.add(comboBox_6);
	   
	   JPanel panel_31 = new JPanel();
	   panel_31.setBounds(146, 138, 10, 47);
	   panel_31.setBackground(Color.RED);
	   panel_31.setForeground(Color.RED);
	   panel_31.setVisible(false);
	   layeredPane.add(panel_31);
	   panels.add(panel_31);
	   
	   JPanel panel_28 = new JPanel();
	   panel_28.setBounds(146, 135, 47, 10);
	   panel_28.setBackground(Color.RED);
	   panel_28.setForeground(Color.RED);
	   panel_28.setVisible(false);
	   layeredPane.add(panel_28);
	   panels.add(panel_28);
	   
   	JPanel panel_30 = new JPanel();
   	panel_30.setBounds(189, 138, 10, 47);
   	panel_30.setBackground(Color.RED);
   	panel_30.setForeground(Color.RED);
   	panel_30.setVisible(false);
   	layeredPane.add(panel_30);
   	panels.add(panel_30);
	   
	   JPanel panel_29 = new JPanel();
	   panel_29.setBounds(152, 175, 47, 10);
	   panel_29.setBackground(Color.RED);
	   panel_29.setForeground(Color.RED);
	   panel_29.setVisible(false);
	   layeredPane.add(panel_29);
	   panels.add(panel_29);
	   
	   JComboBox comboBox_7 = new JComboBox();
	   comboBox_7.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_7.setBounds(118, 155, 107, 19);
	   comboBox_7.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_7.getSelectedIndex() != -1) {                     
	               checkboxRet[3] = comboBox_7.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(3, comboBox_7.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
   	
	   
	   
	   
	   
	   layeredPane.add(comboBox_7);
	   comboBoxes.add(comboBox_7);
	   layeredPane.add(panel_8);
	   panels.add(panel_8);
	   
	   JPanel panel_9 = new JPanel();
	   panel_9.setBounds(248, 187, 47, 10);
	   panel_9.setBackground(Color.RED);
	   panel_9.setForeground(Color.RED);
	   panel_9.setVisible(false);
	   layeredPane.add(panel_9);
	   panels.add(panel_9);
	   
	   JPanel panel_10 = new JPanel();
	   panel_10.setBounds(285, 150, 10, 47);
	   panel_10.setBackground(Color.RED);
	   panel_10.setForeground(Color.RED);
	   panel_10.setVisible(false);
	   layeredPane.add(panel_10);
	   panels.add(panel_10);
	   
	   JPanel panel_11 = new JPanel();
	   panel_11.setBounds(772, 20, 10, 10);
	   panel_11.setVisible(false);
	   layeredPane.add(panel_11);
	   panels.add(panel_11);
	   layeredPane.add(comboBox_2);
	   comboBoxes.add(comboBox_2);
   	   count[2][0] = 0;
   	   count[2][1] = 1;
   	   count[2][2] = 1;
	   count[2][3]= 1;
	   
	   JComboBox comboBox_3 = new JComboBox();
	   comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_3.setBounds(412, 155, 89, 19);
	   comboBox_3.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_3.getSelectedIndex() != -1) {                     
	               checkboxRet[6] = comboBox_3.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(6, comboBox_3.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
	   JPanel panel_12 = new JPanel();
	   panel_12.setBounds(422, 138, 10, 47);
	   panel_12.setBackground(Color.RED);
	   panel_12.setForeground(Color.RED);
	   panel_12.setVisible(false);
	   
	   JPanel panel_16 = new JPanel();
	   panel_16.setBounds(330, 135, 10, 47);
	   panel_16.setBackground(Color.RED);
	   panel_16.setForeground(Color.RED);
	   panel_16.setVisible(false);
	   layeredPane.add(panel_16);
	   panels.add(panel_16);
	   
	   JPanel panel_17 = new JPanel();
	   panel_17.setBounds(330, 138, 47, 10);
	   panel_17.setBackground(Color.RED);
	   panel_17.setForeground(Color.RED);
	   panel_17.setVisible(false);
	   layeredPane.add(panel_17);
	   panels.add(panel_17);
	   
	   JPanel panel_18 = new JPanel();
	   panel_18.setBounds(367, 138, 10, 47);
	   panel_18.setBackground(Color.RED);
	   panel_18.setForeground(Color.RED);
	   panel_18.setVisible(false);
	   layeredPane.add(panel_18);
	   panels.add(panel_18);
	   
	   JPanel panel_19 = new JPanel();
	   panel_19.setBounds(772, 40, 10, 10);
	   panel_19.setVisible(false);
	   layeredPane.add(panel_19);
	   panels.add(panel_19);
	   
	   JComboBox comboBox_4 = new JComboBox();
	   comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_4.setBounds(324, 155, 84, 19);
	   comboBox_4.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_4.getSelectedIndex() != -1) {                     
	               checkboxRet[5] = comboBox_4.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(5, comboBox_4.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
	   layeredPane.add(comboBox_4);
	   comboBoxes.add(comboBox_4);
	   layeredPane.add(panel_12);
	   panels.add(panel_12);
   	   count[3][0] = 1;
   	   
	   JPanel panel_13 = new JPanel();
	   panel_13.setBounds(422, 132, 47, 10);
	   panel_13.setBackground(Color.RED);
	   panel_13.setForeground(Color.RED);
	   panel_13.setVisible(false);
	   layeredPane.add(panel_13);
	   panels.add(panel_13);
   	   count[3][2] = 1;
	   
	   JPanel panel_14 = new JPanel();
	   panel_14.setBounds(470, 138, 10, 47);
	   panel_14.setBackground(Color.RED);
	   panel_14.setForeground(Color.RED);
	   panel_14.setVisible(false);
	   layeredPane.add(panel_14);
	   panels.add(panel_14);
   	   count[3][1] = 1;
	   
	   JPanel panel_15 = new JPanel();
	   panel_15.setBounds(772, 30, 10, 10);
	   panel_15.setVisible(false);
	   layeredPane.add(panel_15);
	   panels.add(panel_15);
	   count[3][3]=1;
	   layeredPane.add(comboBox_3);
	   comboBoxes.add(comboBox_3);
   	   count[4][0] = 1;
   	   count[4][2] = 1;
   	   count[4][1] = 1;
	   count[4][3]= 0;
	   
	   JComboBox comboBox_5 = new JComboBox();
	   comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "S Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_5.setBounds(504, 155, 89, 19);
	   comboBox_5.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_5.getSelectedIndex() != -1) {                     
	               checkboxRet[7] = comboBox_5.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(7, comboBox_5.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
	   JPanel panel_20 = new JPanel();
	   panel_20.setBounds(492, 155, 10, 47);
	   panel_20.setBackground(Color.RED);
	   panel_20.setForeground(Color.RED);
	   panel_20.setVisible(false);
	   layeredPane.add(panel_20);
	   panels.add(panel_20);
   	   count[5][0] = 1;
   	   
	   JPanel panel_21 = new JPanel();
	   panel_21.setBounds(492, 187, 47, 10);
	   panel_21.setBackground(Color.RED);
	   panel_21.setForeground(Color.RED);
	   panel_21.setVisible(false);
	   layeredPane.add(panel_21);
	   panels.add(panel_21);
   	   count[5][2] = 1;
	   
	   JPanel panel_22 = new JPanel();
	   panel_22.setBounds(538, 155, 10, 47);
	   panel_22.setBackground(Color.RED);
	   panel_22.setForeground(Color.RED);
	   panel_22.setVisible(false);
	   layeredPane.add(panel_22);
	   panels.add(panel_22);
   	   count[5][1] = 1;
	   
	   JPanel panel_23 = new JPanel();
	   panel_23.setBounds(729, 87, 10, 10);
	   panel_23.setVisible(false);
	   layeredPane.add(panel_23);
	   panels.add(panel_23);
	   count[5][3]=0;
	   
	   layeredPane.add(comboBox_5);
	   comboBoxes.add(comboBox_5);
	   count[6][0]= 1;
   	   count[6][2] = 1;
	   count[7][1] = 1;
	   count[6][1] = 1;
   	   count[6][3] = 0;
   	   count[7][3] = 0;
	   count[7][0]= 1;
   	   count[7][2] = 1;
	   
	   JComboBox comboBox_8 = new JComboBox();
	   comboBox_8.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_8.setBounds(594, 155, 84, 19);
	   comboBox_8.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_8.getSelectedIndex() != -1) {                     
	               checkboxRet[8] = comboBox_8.getSelectedIndex();		//for debugging purposes
	               map.changeIntersection(8, comboBox_8.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   
	   JPanel panel_32 = new JPanel();
	   panel_32.setBounds(604, 141, 10, 47);
	   panel_32.setBackground(Color.RED);
	   panel_32.setForeground(Color.RED);
	   panel_32.setVisible(false);
	   layeredPane.add(panel_32);
	   panels.add(panel_32);
   	   count[8][3] = 1;
	   
   	JPanel panel_33 = new JPanel();
   	panel_33.setBounds(647, 141, 10, 47);
   	panel_33.setBackground(Color.RED);
   	panel_33.setForeground(Color.RED);
   	panel_33.setVisible(false);
   	count[8][0]=1;
   	
   	JPanel panel_35 = new JPanel();
   	panel_35.setBounds(610, 178, 47, 10);
   	panel_35.setBackground(Color.RED);
   	panel_35.setForeground(Color.RED);
   	panel_35.setVisible(false);
   	layeredPane.add(panel_35);
   	panels.add(panel_35);
   	layeredPane.add(panel_33);
   	panels.add(panel_33);
   	count[8][1]=1;
   	
	   JPanel panel_34 = new JPanel();
	   panel_34.setBounds(604, 138, 47, 10);
	   panel_34.setBackground(Color.RED);
	   panel_34.setForeground(Color.RED);
	   panel_34.setVisible(false);
	   layeredPane.add(panel_34);
	   panels.add(panel_34);
   	   count[8][2] = 1;
   	   
	   layeredPane.add(comboBox_8);
	   comboBoxes.add(comboBox_8);
	   
	   JComboBox comboBox_9 = new JComboBox();
	   comboBox_9.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "W Stop Sign", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_9.setBounds(683, 155, 99, 19);
	   comboBox_9.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_9.getSelectedIndex() != -1) {                     
	               checkboxRet[9] = comboBox_9.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(9, comboBox_9.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   layeredPane.add(comboBox_9);
	   comboBoxes.add(comboBox_9);
	   
	   JPanel panel_36 = new JPanel();
	   panel_36.setBounds(729, 141, 10, 47);
	   panel_36.setBackground(Color.RED);
	   panel_36.setForeground(Color.RED);
	   panel_36.setVisible(false);
	   layeredPane.add(panel_36);
	   panels.add(panel_36);
	   count[9][0] = 1;
   	
   	
   	JPanel panel_38 = new JPanel();
   	panel_38.setBounds(735, 178, 47, 10);
   	panel_38.setBackground(Color.RED);
   	panel_38.setForeground(Color.RED);
   	panel_38.setVisible(false);
   	
   	layeredPane.add(panel_38);
   	panels.add(panel_38);
   	count[9][1]=1;
	   count[9][2]=0;
	   
   	JPanel panel_37 = new JPanel();
   	panel_37.setBounds(772, 141, 10, 47);
   	panel_37.setBackground(Color.RED);
   	panel_37.setForeground(Color.RED);
   	panel_37.setVisible(false);
   	layeredPane.add(panel_37);
   	panels.add(panel_37); 
   	count[9][3] = 1;
   	   
	   JComboBox comboBox_10 = new JComboBox();
	   comboBox_10.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "W Stop Sign", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_10.setBounds(146, 268, 107, 19);
	   comboBox_10.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_10.getSelectedIndex() != -1) {                     
	               checkboxRet[10] = comboBox_10.getSelectedIndex(); 		//for debugging purposes
	               map.changeIntersection(10, comboBox_10.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   JPanel panel_39 = new JPanel();
	   panel_39.setBounds(729, 138, 47, 10);
	   panel_39.setBackground(Color.RED);
	   panel_39.setForeground(Color.RED);
	   panel_39.setVisible(false);
	   layeredPane.add(panel_39);
	   panels.add(panel_39);
	   
	   JPanel panel_40 = new JPanel();
	   panel_40.setBounds(156, 260, 10, 47);
	   panel_40.setBackground(Color.RED);
	   panel_40.setForeground(Color.RED);
	   panel_40.setVisible(false);
	   
	  
	   layeredPane.add(panel_40);
	   panels.add(panel_40);
	   count[10][0] = 1;
   	
   	
   	JPanel panel_41 = new JPanel();
   	panel_41.setBounds(162, 297, 47, 10);
   	panel_41.setBackground(Color.RED);
   	panel_41.setForeground(Color.RED);
   	panel_41.setVisible(false);
   	
   	layeredPane.add(panel_41);
   	panels.add(panel_41);
   	count[10][1]=1;
	   count[10][2]=0;
	   
   	JPanel panel_43 = new JPanel();
   	panel_43.setBounds(199, 260, 10, 47);
   	panel_43.setBackground(Color.RED);
   	panel_43.setForeground(Color.RED);
   	panel_43.setVisible(false);
   	layeredPane.add(panel_43);
   	panels.add(panel_43); 
   	count[10][3] = 1;
	   
	   JPanel panel_42 = new JPanel();
	   panel_42.setBounds(156, 257, 47, 10);
	   panel_42.setBackground(Color.RED);
	   panel_42.setForeground(Color.RED);
	   panel_42.setVisible(false);
	   layeredPane.add(panel_42);
	   panels.add(panel_42);
   	
	   layeredPane.add(comboBox_10);
	   comboBoxes.add(comboBox_10);
	   
	   JComboBox comboBox_11 = new JComboBox();
	   comboBox_11.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E Stop Sign", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_11.setBounds(562, 268, 99, 19);
	   comboBox_11.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_11.getSelectedIndex() != -1) {                     
	               checkboxRet[11] = comboBox_11.getSelectedIndex(); 	//for debugging purposes
	               map.changeIntersection(11, comboBox_11.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
	   JPanel panel_44 = new JPanel();
	   panel_44.setBounds(583, 249, 10, 47);
	   panel_44.setBackground(Color.RED);
	   panel_44.setForeground(Color.RED);
	   panel_44.setVisible(false);
	   layeredPane.add(panel_44);
	   panels.add(panel_44);
	   count[11][0] = 0;
   	
   	
   	JPanel panel_45 = new JPanel();
   	panel_45.setBounds(589, 286, 47, 10);
   	panel_45.setBackground(Color.RED);
   	panel_45.setForeground(Color.RED);
   	panel_45.setVisible(false);
   	
   	layeredPane.add(panel_45);
   	panels.add(panel_45);
   	count[11][1]=1;
	   
	   
   	JPanel panel_46 = new JPanel();
   	panel_46.setBounds(626, 249, 10, 47);
   	panel_46.setBackground(Color.RED);
   	panel_46.setForeground(Color.RED);
   	panel_46.setVisible(false);
   	layeredPane.add(panel_46);
   	panels.add(panel_46); 
   	count[11][2]=1;
   	
	   
	   JPanel panel_47 = new JPanel();
	   panel_47.setBounds(583, 246, 47, 10);
	   panel_47.setBackground(Color.RED);
	   panel_47.setForeground(Color.RED);
	   panel_47.setVisible(false);
	   layeredPane.add(panel_47);
	   panels.add(panel_47);
	   count[11][3] = 1;
	   
	   layeredPane.add(comboBox_11);
	   comboBoxes.add(comboBox_11);
	   
	   JComboBox comboBox_12 = new JComboBox();
	   comboBox_12.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_12.setBounds(146, 635, 107, 19);
	   comboBox_12.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_12.getSelectedIndex() != -1) {                     
	               checkboxRet[12] = comboBox_12.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(12, comboBox_12.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   
	   JPanel panel_48 = new JPanel();
	   panel_48.setBounds(156, 622, 10, 47);
	   panel_48.setBackground(Color.RED);
	   panel_48.setForeground(Color.RED);
	   panel_48.setVisible(false);
	   layeredPane.add(panel_48);
	   panels.add(panel_48);
	   count[12][0] = 1;
   	
   	
   	JPanel panel_49 = new JPanel();
   	panel_49.setBounds(162, 659, 47, 10);
   	panel_49.setBackground(Color.RED);
   	panel_49.setForeground(Color.RED);
   	panel_49.setVisible(false);
   	
   	layeredPane.add(panel_49);
   	panels.add(panel_49);
   	count[12][1]=1;
	   
	   
   	JPanel panel_50 = new JPanel();
   	panel_50.setBounds(199, 622, 10, 47);
   	panel_50.setBackground(Color.RED);
   	panel_50.setForeground(Color.RED);
   	panel_50.setVisible(false);
   	layeredPane.add(panel_50);
   	panels.add(panel_50); 
   	count[12][2]=1;
   	
	   
	   JPanel panel_51 = new JPanel();
	   panel_51.setBounds(156, 619, 47, 10);
	   panel_51.setBackground(Color.RED);
	   panel_51.setForeground(Color.RED);
	   panel_51.setVisible(false);
	   layeredPane.add(panel_51);
	   panels.add(panel_51);
	   count[12][3] = 1;
	   
	   layeredPane.add(comboBox_12);
	   comboBoxes.add(comboBox_12);
	   
	   JComboBox comboBox_13 = new JComboBox();
	   comboBox_13.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_13.setBounds(562, 633, 107, 19);
	   comboBox_13.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_13.getSelectedIndex() != -1) {                     
	               checkboxRet[13] = comboBox_13.getSelectedIndex(); 	//for debugging purposes
	               map.changeIntersection(13, comboBox_13.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   
	   JPanel panel_52 = new JPanel();
	   panel_52.setBounds(583, 625, 10, 47);
	   panel_52.setBackground(Color.RED);
	   panel_52.setForeground(Color.RED);
	   panel_52.setVisible(false);
	   layeredPane.add(panel_52);
	   panels.add(panel_52);
	   count[13][0] = 1;
   	
   	
   	JPanel panel_53 = new JPanel();
   	panel_53.setBounds(589, 662, 47, 10);
   	panel_53.setBackground(Color.RED);
   	panel_53.setForeground(Color.RED);
   	panel_53.setVisible(false);
   	
   	layeredPane.add(panel_53);
   	panels.add(panel_53);
   	count[13][1]=1;
	   
	   
   	JPanel panel_54 = new JPanel();
   	panel_54.setBounds(626, 625, 10, 47);
   	panel_54.setBackground(Color.RED);
   	panel_54.setForeground(Color.RED);
   	panel_54.setVisible(false);
   	layeredPane.add(panel_54);
   	panels.add(panel_54); 
   	count[13][2]=1;
   	
	   
	   JPanel panel_55 = new JPanel();
	   panel_55.setBounds(583, 622, 47, 10);
	   panel_55.setBackground(Color.RED);
	   panel_55.setForeground(Color.RED);
	   panel_55.setVisible(false);
	   layeredPane.add(panel_55);
	   panels.add(panel_55);
	   count[13][3] = 1;
	   
	   layeredPane.add(comboBox_13);
	   comboBoxes.add(comboBox_13);
	   
	   JComboBox comboBox_14 = new JComboBox();
	   comboBox_14.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E Stop Sign", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_14.setBounds(118, 717, 107, 19);
	   comboBox_14.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_14.getSelectedIndex() != -1) {                     
	               checkboxRet[14] = comboBox_14.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(14, comboBox_14.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   
	   JPanel panel_56 = new JPanel();
	   panel_56.setBounds(161, 704, 10, 47);
	   panel_56.setBackground(Color.RED);
	   panel_56.setForeground(Color.RED);
	   panel_56.setVisible(false);
	   layeredPane.add(panel_56);
	   panels.add(panel_56);
	   count[14][0] = 0;
   	
   	
   	JPanel panel_57 = new JPanel();
   	panel_57.setBounds(167, 741, 47, 10);
   	panel_57.setBackground(Color.RED);
   	panel_57.setForeground(Color.RED);
   	panel_57.setVisible(false);
   	
   	layeredPane.add(panel_57);
   	panels.add(panel_57);
   	count[14][1]=1;
	   
	   
   	JPanel panel_58 = new JPanel();
   	panel_58.setBounds(204, 704, 10, 47);
   	panel_58.setBackground(Color.RED);
   	panel_58.setForeground(Color.RED);
   	panel_58.setVisible(false);
   	layeredPane.add(panel_58);
   	panels.add(panel_58); 
   	count[14][2]=1;
   	
	   
	   JPanel panel_59 = new JPanel();
	   panel_59.setBounds(161, 701, 47, 10);
	   panel_59.setBackground(Color.RED);
	   panel_59.setForeground(Color.RED);
	   panel_59.setVisible(false);
	   layeredPane.add(panel_59);
	   panels.add(panel_59);
	   count[14][3] = 1;
	   
	   layeredPane.add(comboBox_14);
	   comboBoxes.add(comboBox_14);
	   
	   JComboBox comboBox_15 = new JComboBox();
	   comboBox_15.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "S Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_15.setBounds(226, 717, 92, 19);
	   comboBox_15.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_15.getSelectedIndex() != -1) {                     
	               checkboxRet[15] = comboBox_15.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(15, comboBox_15.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   
	   JPanel panel_60 = new JPanel();
	   panel_60.setBounds(259, 707, 10, 47);
	   panel_60.setBackground(Color.RED);
	   panel_60.setForeground(Color.RED);
	   panel_60.setVisible(false);
	   layeredPane.add(panel_60);
	   panels.add(panel_60);
	   count[15][0] = 1;
   	
   	
   	JPanel panel_61 = new JPanel();
   	panel_61.setBounds(265, 744, 47, 10);
   	panel_61.setBackground(Color.RED);
   	panel_61.setForeground(Color.RED);
   	panel_61.setVisible(false);
   	
   	layeredPane.add(panel_61);
   	panels.add(panel_61);
   	count[15][1]=1;
	   
	   
   	JPanel panel_62 = new JPanel();
   	panel_62.setBounds(302, 707, 10, 47);
   	panel_62.setBackground(Color.RED);
   	panel_62.setForeground(Color.RED);
   	panel_62.setVisible(false);
   	layeredPane.add(panel_62);
   	panels.add(panel_62); 
   	count[15][2]=1;
   	
	   
	   JPanel panel_63 = new JPanel();
	   panel_63.setBounds(259, 704, 47, 10);
	   panel_63.setBackground(Color.RED);
	   panel_63.setForeground(Color.RED);
	   panel_63.setVisible(false);
	   layeredPane.add(panel_63);
	   panels.add(panel_63);
	   count[15][3] = 0;
	   
	   layeredPane.add(comboBox_15);
	   comboBoxes.add(comboBox_15);
	   
	   JComboBox comboBox_16 = new JComboBox();
	   comboBox_16.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_16.setBounds(324, 717, 84, 19);
	   comboBox_16.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_16.getSelectedIndex() != -1) {                     
	               checkboxRet[16] = comboBox_16.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(16, comboBox_16.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   layeredPane.add(comboBox_16);
	   comboBoxes.add(comboBox_16);
	   
	   JPanel panel_64 = new JPanel();
	   panel_64.setBounds(334, 707, 10, 47);
	   panel_64.setBackground(Color.RED);
	   panel_64.setForeground(Color.RED);
	   panel_64.setVisible(false);
	   layeredPane.add(panel_64);
	   panels.add(panel_64);
	   count[16][0] = 1;
   	
   	
   	JPanel panel_65 = new JPanel();
   	panel_65.setBounds(340, 744, 47, 10);
   	panel_65.setBackground(Color.RED);
   	panel_65.setForeground(Color.RED);
   	panel_65.setVisible(false);
   	
   	layeredPane.add(panel_65);
   	panels.add(panel_65);
   	count[16][1]=0;
	   
	   
   	JPanel panel_66 = new JPanel();
   	panel_66.setBounds(377, 707, 10, 47);
   	panel_66.setBackground(Color.RED);
   	panel_66.setForeground(Color.RED);
   	panel_66.setVisible(false);
   	layeredPane.add(panel_66);
   	panels.add(panel_66); 
   	count[16][2]=1;
   	
	   
	   JPanel panel_67 = new JPanel();
	   panel_67.setBounds(334, 704, 47, 10);
	   panel_67.setBackground(Color.RED);
	   panel_67.setForeground(Color.RED);
	   panel_67.setVisible(false);
	   layeredPane.add(panel_67);
	   panels.add(panel_67);
	   count[16][3] = 1;
	   
	   JComboBox comboBox_17 = new JComboBox();
	   comboBox_17.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_17.setBounds(412, 717, 89, 19);
	   comboBox_17.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_17.getSelectedIndex() != -1) {                     
	               checkboxRet[17] = comboBox_17.getSelectedIndex(); 	//for debugging purposes
	               map.changeIntersection(17, comboBox_17.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice
	            }            
	         }   
	   });
	   layeredPane.add(comboBox_17);
	   comboBoxes.add(comboBox_17);
	   
	   JPanel panel_68 = new JPanel();
	   panel_68.setBounds(422, 707, 10, 47);
	   panel_68.setBackground(Color.RED);
	   panel_68.setForeground(Color.RED);
	   panel_68.setVisible(false);
	   layeredPane.add(panel_68);
	   panels.add(panel_68);
	   count[17][0] = 1;
   	
   	
   	JPanel panel_69 = new JPanel();
   	panel_69.setBounds(428, 744, 47, 10);
   	panel_69.setBackground(Color.RED);
   	panel_69.setForeground(Color.RED);
   	panel_69.setVisible(false);
   	
   	layeredPane.add(panel_69);
   	panels.add(panel_69);
   	count[17][1]=0;
	   
	   
   	JPanel panel_70 = new JPanel();
   	panel_70.setBounds(465, 707, 10, 47);
   	panel_70.setBackground(Color.RED);
   	panel_70.setForeground(Color.RED);
   	panel_70.setVisible(false);
   	layeredPane.add(panel_70);
   	panels.add(panel_70); 
   	count[17][2]=1;
   	
	   
	   JPanel panel_71 = new JPanel();
	   panel_71.setBounds(422, 704, 47, 10);
	   panel_71.setBackground(Color.RED);
	   panel_71.setForeground(Color.RED);
	   panel_71.setVisible(false);
	   layeredPane.add(panel_71);
	   panels.add(panel_71);
	   count[17][3] = 1;
	   
	   JComboBox comboBox_18 = new JComboBox();
	   comboBox_18.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "S Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_18.setBounds(504, 717, 89, 19);
	   comboBox_18.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_18.getSelectedIndex() != -1) {                     
	               checkboxRet[18] = comboBox_18.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(18, comboBox_18.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   
	   
	   layeredPane.add(comboBox_18);
	   comboBoxes.add(comboBox_18);
	   
	   JPanel panel_72 = new JPanel();
	   panel_72.setBounds(514, 707, 10, 47);
	   panel_72.setBackground(Color.RED);
	   panel_72.setForeground(Color.RED);
	   panel_72.setVisible(false);
	   layeredPane.add(panel_72);
	   panels.add(panel_72);
	   count[18][0] = 1;
   	
   	
   	JPanel panel_73 = new JPanel();
   	panel_73.setBounds(520, 744, 47, 10);
   	panel_73.setBackground(Color.RED);
   	panel_73.setForeground(Color.RED);
   	panel_73.setVisible(false);
   	
   	layeredPane.add(panel_73);
   	panels.add(panel_73);
   	count[18][1]=1;
	   
	   
   	JPanel panel_74 = new JPanel();
   	panel_74.setBounds(557, 707, 10, 47);
   	panel_74.setBackground(Color.RED);
   	panel_74.setForeground(Color.RED);
   	panel_74.setVisible(false);
   	layeredPane.add(panel_74);
   	panels.add(panel_74); 
   	count[18][2]=1;
   	
	   
	   JPanel panel_75 = new JPanel();
	   panel_75.setBounds(514, 704, 47, 10);
	   panel_75.setBackground(Color.RED);
	   panel_75.setForeground(Color.RED);
	   panel_75.setVisible(false);
	   layeredPane.add(panel_75);
	   panels.add(panel_75);
	   count[18][3] = 1;
	   
	   JComboBox comboBox_19 = new JComboBox();
	   comboBox_19.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "W Stop Sign", "N-S Stop Signs", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_19.setBounds(594, 717, 84, 19);
	   comboBox_19.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_19.getSelectedIndex() != -1) {                     
	               checkboxRet[19] = comboBox_19.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(19, comboBox_19.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   layeredPane.add(comboBox_19);
	   comboBoxes.add(comboBox_19);
	   
	   JPanel panel_76 = new JPanel();
	   panel_76.setBounds(589, 707, 10, 47);
	   panel_76.setBackground(Color.RED);
	   panel_76.setForeground(Color.RED);
	   panel_76.setVisible(false);
	   layeredPane.add(panel_76);
	   panels.add(panel_76);
	   count[19][0] = 1;
   	
   	
   	JPanel panel_77 = new JPanel();
   	panel_77.setBounds(595, 744, 47, 10);
   	panel_77.setBackground(Color.RED);
   	panel_77.setForeground(Color.RED);
   	panel_77.setVisible(false);
   	
   	layeredPane.add(panel_77);
   	panels.add(panel_77);
   	count[19][1]=1;
	   
	   
   	JPanel panel_78 = new JPanel();
   	panel_78.setBounds(632, 707, 10, 47);
   	panel_78.setBackground(Color.RED);
   	panel_78.setForeground(Color.RED);
   	panel_78.setVisible(false);
   	layeredPane.add(panel_78);
   	panels.add(panel_78); 
   	count[19][2]=0;
   	
	   
	   JPanel panel_79 = new JPanel();
	   panel_79.setBounds(589, 704, 47, 10);
	   panel_79.setBackground(Color.RED);
	   panel_79.setForeground(Color.RED);
	   panel_79.setVisible(false);
	   layeredPane.add(panel_79);
	   panels.add(panel_79);
	   count[19][3] = 1;
	   JComboBox comboBox_20 = new JComboBox();
	   comboBox_20.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_20.setBounds(660, 814, 89, 19);
	   comboBox_20.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_20.getSelectedIndex() != -1) {                     
	               checkboxRet[20] = comboBox_20.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(21, comboBox_20.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   
	   JComboBox comboBox_21 = new JComboBox();
	   comboBox_21.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_21.setBounds(48, 812, 89, 19);
	   comboBox_21.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_21.getSelectedIndex() != -1) {                     
	               checkboxRet[20] = comboBox_21.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(20, comboBox_21.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   layeredPane.add(comboBox_21);
	   comboBoxes.add(comboBox_21);
	   
	   JPanel panel_84 = new JPanel();
	   panel_84.setBounds(69, 805, 10, 47);
	   panel_84.setBackground(Color.RED);
	   panel_84.setForeground(Color.RED);
	   panel_84.setVisible(false);
	   layeredPane.add(panel_84);
	   panels.add(panel_84);
	   
	   
	   JPanel panel_85 = new JPanel();
	   panel_85.setBounds(75, 842, 47, 10);
	   panel_85.setBackground(Color.RED);
	   panel_85.setForeground(Color.RED);
	   panel_85.setVisible(false);
	   
	   layeredPane.add(panel_85);
	   panels.add(panel_85);
	   
	   
   	JPanel panel_86 = new JPanel();
   	panel_86.setBounds(112, 805, 10, 47);
   	panel_86.setBackground(Color.RED);
   	panel_86.setForeground(Color.RED);
   	panel_86.setVisible(false);
   	layeredPane.add(panel_86);
   	panels.add(panel_86); 
	   
	   
	   JPanel panel_87 = new JPanel();
	   panel_87.setBounds(69, 802, 47, 10);
	   panel_87.setBackground(Color.RED);
	   panel_87.setForeground(Color.RED);
	   panel_87.setVisible(false);
	   layeredPane.add(panel_87);
	   panels.add(panel_87);
	   layeredPane.add(comboBox_20);
	   comboBoxes.add(comboBox_20);
	   
	   JPanel panel_80 = new JPanel();
	   panel_80.setBounds(683, 802, 10, 47);
	   panel_80.setBackground(Color.RED);
	   panel_80.setForeground(Color.RED);
	   panel_80.setVisible(false);
	   layeredPane.add(panel_80);
	   panels.add(panel_80);
	   count[20][0] = 1;
   	
   	
   	JPanel panel_81 = new JPanel();
   	panel_81.setBounds(689, 839, 47, 10);
   	panel_81.setBackground(Color.RED);
   	panel_81.setForeground(Color.RED);
   	panel_81.setVisible(false);
   	
   	layeredPane.add(panel_81);
   	panels.add(panel_81);
   	count[20][1]=0;
	   
	   
   	JPanel panel_82 = new JPanel();
   	panel_82.setBounds(726, 802, 10, 47);
   	panel_82.setBackground(Color.RED);
   	panel_82.setForeground(Color.RED);
   	panel_82.setVisible(false);
   	layeredPane.add(panel_82);
   	panels.add(panel_82); 
   	count[20][2]=1;
   	
	   
	   JPanel panel_83 = new JPanel();
	   panel_83.setBounds(683, 799, 47, 10);
	   panel_83.setBackground(Color.RED);
	   panel_83.setForeground(Color.RED);
	   panel_83.setVisible(false);
	   layeredPane.add(panel_83);
	   panels.add(panel_83);
	   count[20][3] = 1;
	   count[21][0] = 1;
   	count[21][1]=0;
   	count[21][2]=1;
	   count[21][3] = 1;
	   
	   JComboBox comboBox_22 = new JComboBox();
	   comboBox_22.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_22.setBounds(324, 904, 84, 19);
	   comboBox_22.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_22.getSelectedIndex() != -1) {                     
	               checkboxRet[22] = comboBox_22.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(22, comboBox_22.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   layeredPane.add(comboBox_22);
	   comboBoxes.add(comboBox_22);
	   
	   JPanel panel_88 = new JPanel();
	   panel_88.setBounds(334, 889, 10, 47);
	   panel_88.setBackground(Color.RED);
	   panel_88.setForeground(Color.RED);
	   panel_88.setVisible(false);
	   layeredPane.add(panel_88);
	   panels.add(panel_88);
	   count[22][0] = 1;
   	
   	
   	JPanel panel_89 = new JPanel();
   	panel_89.setBounds(340, 926, 47, 10);
   	panel_89.setBackground(Color.RED);
   	panel_89.setForeground(Color.RED);
   	panel_89.setVisible(false);
   	
   	layeredPane.add(panel_89);
   	panels.add(panel_89);
   	count[22][1]=0;
	   
	   
   	JPanel panel_90 = new JPanel();
   	panel_90.setBounds(377, 889, 10, 47);
   	panel_90.setBackground(Color.RED);
   	panel_90.setForeground(Color.RED);
   	panel_90.setVisible(false);
   	layeredPane.add(panel_90);
   	panels.add(panel_90); 
   	count[22][2]=1;
   	
	   
	   JPanel panel_91 = new JPanel();
	   panel_91.setBounds(334, 886, 47, 10);
	   panel_91.setBackground(Color.RED);
	   panel_91.setForeground(Color.RED);
	   panel_91.setVisible(false);
	   layeredPane.add(panel_91);
	   panels.add(panel_91);
	   count[22][3] = 1;
	   
	   JComboBox comboBox_23 = new JComboBox();
	   comboBox_23.setModel(new DefaultComboBoxModel(new String[] {"Traffic Light", "E-W Stop Signs", "N Stop Sign", "All-Way Stop Signs", "Sensor-Based Traffic Light"}));
	   comboBox_23.setBounds(412, 904, 89, 19);
	   comboBox_23.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e) { 
	            String data = "";
	            if (comboBox_23.getSelectedIndex() != -1) {                     
	               checkboxRet[23] = comboBox_23.getSelectedIndex();	//for debugging purposes
	               map.changeIntersection(23, comboBox_23.getSelectedIndex());     //changes the intersection in the map class to reflect the users choice 
	            }            
	         }   
	   });
	   layeredPane.add(comboBox_23);
	   comboBoxes.add(comboBox_23);
	   
	   JPanel panel_92 = new JPanel();
	   panel_92.setBounds(427, 889, 10, 47);
	   panel_92.setBackground(Color.RED);
	   panel_92.setForeground(Color.RED);
	   panel_92.setVisible(false);
	   layeredPane.add(panel_92);
	   panels.add(panel_92);
	   count[23][0] = 1;
   	
   	
   	JPanel panel_93 = new JPanel();
   	panel_93.setBounds(433, 926, 47, 10);
   	panel_93.setBackground(Color.RED);
   	panel_93.setForeground(Color.RED);
   	panel_93.setVisible(false);
   	
   	layeredPane.add(panel_93);
   	panels.add(panel_93);
   	count[23][1]=0;
	   
	   
   	JPanel panel_94 = new JPanel();
   	panel_94.setBounds(470, 889, 10, 47);
   	panel_94.setBackground(Color.RED);
   	panel_94.setForeground(Color.RED);
   	panel_94.setVisible(false);
   	layeredPane.add(panel_94);
   	panels.add(panel_94); 
   	count[23][2]=1;
   	
	   
	   JPanel panel_95 = new JPanel();
	   panel_95.setBounds(427, 886, 47, 10);
	   panel_95.setBackground(Color.RED);
	   panel_95.setForeground(Color.RED);
	   panel_95.setVisible(false);
	   layeredPane.add(panel_95);
	   panels.add(panel_95);
	   count[23][3] = 1;
	   
	   background_.setLayout(gl_background_);
	   background_.setVisible(true);
	   
	   JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		checkOn = 0;
		
		JButton addCar = new JButton("Add Cars");	//button to allow the user to add cars
		addCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				
				if (command.equals("Add Cars")) {
					String[] options = {"Input existing file", "Input by hand"};
					int x = JOptionPane.showOptionDialog(null, "Choose whether to use existing file or Input manually here",
			                "Click a button",
			                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
					if(x == 0) {
					JFileChooser chooser = new JFileChooser();
				    chooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
				    chooser.setDialogTitle("Choose Source File");
				    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				    chooser.setAcceptAllFileFilterUsed(true);
				    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				        System.out.println("getCurrentDirectory(): " + chooser.getCurrentDirectory());
				        System.out.println("getSelectedFile() : " + chooser.getSelectedFile());
				        File file = chooser.getSelectedFile();
				        String fileName = file.getPath();
				        createCars(fileName, layeredPane);
				      }
					}
					else if(x == 1) {
						textField_1.setVisible(true);
						textArea.setVisible(true);
						int count = 0;
						File file1 = new File(".\\src\\resources\\save.txt");
						try {
							FileWriter fw2 = new FileWriter(file1);
							fw2.write("");
							fw2.close();
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						int catch1 = 0;
						while(catch1 == 0) {
						File file2 = new File(".\\src\\resources\\save.txt");
						String coordinate1 = JOptionPane.showInputDialog(frame, "Input the initial x-coordinate", null);
						String coordinate2 = JOptionPane.showInputDialog(frame, "Input the initial y-coordinate", null);
						String coordinate3 = JOptionPane.showInputDialog(frame, "Input the final x-coordinate", null);
						String coordinate4 = JOptionPane.showInputDialog(frame, "Input the final y-coordinate", null);
						String output = coordinate1+","+coordinate2+","+coordinate3+","+coordinate4;
						if(coordinate1.matches("\\d+") == true && coordinate2.matches("\\d+") == true && coordinate3.matches("\\d+") == true && coordinate4.matches("\\d+") == true) {
						try {
							FileWriter fw = new FileWriter(file2, true);
							fw.write(output);
							fw.write("\n");
							fw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						String[] options1 = {"Add Another Input", "Finished"};
						int y = JOptionPane.showOptionDialog(null, "Do you wish to enter another car?",
				                "Another Car?",
				                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
						
						catch1=y;
						count++;
						}
						else {
							String[] options1 = {"Add Another Input", "Finished"};
							int y = JOptionPane.showOptionDialog(null, "Invalid input. Do you wish to continue?",
					                "Another Car?",
					                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options1, options1[0]);
							
							catch1=y;
						}
						}
						if(count>0) {
							createCars(".\\src\\resources\\save.txt", layeredPane);
						}
						textField_1.setVisible(false);
						textArea.setVisible(false);
					}
				}
			}
	    });
		
		int reset = 0;
		int count1 = 0;
		Date date = new Date();
		DateFormat time1 = new SimpleDateFormat("mm:ss");
		JTextPane txtpnTimer = new JTextPane();
		txtpnTimer.setText("Timer: " + time1.format(timer));
		
		JButton btnStart = new JButton("Start");
		btnStart.setHorizontalAlignment(SwingConstants.LEFT);
		btnStart.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) { 
				   String command = e.getActionCommand(); 
				   if(command.equals("Start")) {
					   addCar.setVisible(false);
					   btnStart.setActionCommand("Pause");
					   btnStart.setText("Pause");
					   checkOn = 1;
					   /*
					    * Makes all comboBoxes invisible so the user can't change the intersection
					    * while the simulation is running.  Also makes the addCar button invisible
					    * so the user can't add cars during the simulation
					    */
					   for (int i = 0; i < comboBoxes.size(); i++) {
						   comboBoxes.get(i).setVisible(false);
					   }
					   for(int i = 0; i < comboBoxes.size(); i++) {
						   for(int j = 0; j < 4; j++) {
							 
							   if((checkboxRet[i] == 1 || checkboxRet[i] == 3)  && (j==0 || j == 2) && count[i][j] == 1 ) {
								   panels.get((i*4)+j).setVisible(true);
								   panels.get((i*4)+j).setBackground(Color.RED);
								   panels.get((i*4)+j).setForeground(Color.RED);
					   			}
							   else if((checkboxRet[i] == 2 || checkboxRet[i] == 3) && (j==1 || j == 3) && count[i][j] == 1 ) {
								   panels.get((i*4)+j).setVisible(true);
								   panels.get((i*4)+j).setBackground(Color.RED);
								   panels.get((i*4)+j).setForeground(Color.RED);
					   			}
							   else if((checkboxRet[i] == 0 ||  checkboxRet[i] == 4) &&  count[i][j] == 1 ) {
								   panels.get((i*4)+j).setVisible(true);
								   if(j==1 || j==3) {
								   panels.get((i*4)+j).setBackground(Color.GREEN);
								   panels.get((i*4)+j).setForeground(Color.GREEN);
								   }
								   else {
									   panels.get((i*4)+j).setBackground(Color.RED);
									   panels.get((i*4)+j).setForeground(Color.RED);
								   }
							   }
							   
						   }
					   }
					   
					   
					   
					   //Insert traffic code here
					   /*checks if all the vehicles have reached their destination, 0 means false
						1 means true*/
						
						//instantiate all the cars
					   chThread = 1;
					  new Thread(new Runnable() {
							public void run() {
								Traffic traffic = new Traffic();
								int count1 = 0;
								int interChange = -1;
								if (pauseCheck == 0) {
									timer = 0;
								}
								allArrived = 0;
								pauseCheck = 0;
								int[] intersections = new int[24];
								int[] sensorSwitch = new int[24];
								int justSwitched = 0;
								for (int i = 0; i < intersections.length; i++) {
									intersections[i] = map.getIntersection(i).getType();
								}
								for (int i = 0; i < sensorSwitch.length; i++) {
									sensorSwitch[i] = 0;
								}
								while(allArrived == 0 && chThread == 1) {
									allArrived = 1;
									if(timer % 120 == 0) {
										justSwitched = 1;
										for(int i=0;i<intersections.length;i++) {
											if(intersections[i] == 3 && checkboxRet[i] == 0) {
												intersections[i] = 4;
											}else if(intersections[i] == 4 && checkboxRet[i] == 0) {
												intersections[i] = 3;
											}
										}
									}

									 for(int i = 0; i < comboBoxes.size(); i++) {
										   for(int j = 0; j < 4; j++) {
											   if((checkboxRet[i] == 0 ||  checkboxRet[i] == 4) && intersections[i] == 3 && count[i][j] == 1 ) {
												   panels.get((i*4)+j).setVisible(true);
												   if(j==1 || j==3) {
												   panels.get((i*4)+j).setBackground(Color.GREEN);
												   panels.get((i*4)+j).setForeground(Color.GREEN);
												   }
												   else {
													   panels.get((i*4)+j).setBackground(Color.RED);
													   panels.get((i*4)+j).setForeground(Color.RED);
												   }
											   }
											   if((checkboxRet[i] == 0 ||  checkboxRet[i] == 4) && intersections[i] == 5 && count[i][j] == 1 ) {
												  
												   panels.get((i*4)+j).setVisible(true);
												   if(j==1 || j==3) {
												   panels.get((i*4)+j).setBackground(Color.GREEN);
												   panels.get((i*4)+j).setForeground(Color.GREEN);
												   }
												   else {
													   panels.get((i*4)+j).setBackground(Color.RED);
													   panels.get((i*4)+j).setForeground(Color.RED);
												   }
											   }
											if((checkboxRet[i] == 0 ||  checkboxRet[i] == 4) && intersections[i] == 4 && count[i][j] == 1 ) {
												
												   panels.get((i*4)+j).setVisible(true);
												  if(j==1 || j==3) {
												   panels.get((i*4)+j).setBackground(Color.RED);
												   panels.get((i*4)+j).setForeground(Color.RED);
												  }
												  else {
												    panels.get((i*4)+j).setBackground(Color.GREEN);
												    panels.get((i*4)+j).setForeground(Color.GREEN);
											      }
											}
											if((checkboxRet[i] == 0 ||  checkboxRet[i] == 4) && intersections[i] == 6 && count[i][j] == 1 ) {
												
												   panels.get((i*4)+j).setVisible(true);
												  if(j==1 || j==3) {
												   panels.get((i*4)+j).setBackground(Color.RED);
												   panels.get((i*4)+j).setForeground(Color.RED);
												  }
												  else {
												    panels.get((i*4)+j).setBackground(Color.GREEN);
												    panels.get((i*4)+j).setForeground(Color.GREEN);
											      }
											}
										 }
									 }
									for(Car currCar : cars) {
										if(currCar.getCurrX() == currCar.getEndX() && currCar.getCurrY() == currCar.getEndY()) {
											currCar.setIsActive(0);
										} 
										if(currCar.getIsActive() == 1) {
											if(currCar.getStopped() == 1) {
												//System.out.println("Car " + currCar.getCarIndex() + " is stopped at " + currCar.getIntersectAt() + " type:" + intersections[currCar.getIntersectAt()]);
												//lets the car go if it is stuck at a traffic light and it just switched
												if(currCar.getIntersectAt() >= 0) {
													//handles 4-way intersections
													//TODO: update for east west and north south intersections
													if(intersections[currCar.getIntersectAt()] < 3) {
														//moves car when it is at "front" of intersection
														//System.out.println("Car " + currCar.getCarIndex() + " is moving up");
														if(currCar.getIntersectPlace()<=0) {
															currCar.setStopped(0);
														}
														//System.out.println("Place is: " + currCar.getIntersectPlace());
														//brings cars closer to the "front" of the intersection
														currCar.setIntersectPlace(currCar.getIntersectPlace()-1);
													
													
													//handles lights intersections
													}else if(sensorSwitch[currCar.getIntersectAt()] == 1) {
														currCar.setStopped(0);
														sensorSwitch[currCar.getIntersectAt()] = 0;
													}else if(justSwitched == 1) {
														
														if(intersections[currCar.getIntersectAt()] == 3 ||intersections[currCar.getIntersectAt()] == 4) {
															currCar.setStopped(0);
														}
													}
												}
												currCar.setSlowed(1);
											} 
											if(currCar.getStopped() == 0) {
												//confirms that the car is not currently at an intersection
												currCar.setIntersectAt(-1);
												interChange=traffic.move(currCar, intersections);
												if(interChange >= 0) {
													//Manages Sensor Based intersection changes
													if(intersections[interChange] == 5) {
														System.out.println("Intersection " + interChange +" Has changed, type "+intersections[interChange]);
														System.out.println("Current Car is at " + currCar.getIntersectAt() + " type: " + intersections[currCar.getIntersectAt()]);
														intersections[interChange] =6;
														//The interseciton has changed
														sensorSwitch[interChange] = 1;
													}
													else if(intersections[interChange] == 6) {
														System.out.println("Intersection " + interChange +" Has changed, type "+intersections[interChange]);
														System.out.println("Current Car is at " + currCar.getIntersectAt() + " type: " + intersections[currCar.getIntersectAt()]);
														intersections[interChange] =5;
														//The interseciton has changed
														sensorSwitch[interChange] = 1;
													}
												}
												
											}
											//System.out.println(currCar.getCarIndex() + " " + currCar.getCurrDirection());
											//System.out.println("End is at X:" + currCar.getEndX() + " Y:" + currCar.getEndY());
											//System.out.println("Car " + currCar.getCarIndex() + " Is currently at X:" + currCar.getCurrX() + " Y:" + currCar.getCurrY() + " Direction:" + currCar.getCurrDirection());
											//at least one car has not reached its destination
											allArrived = 0;
										}
									}
									justSwitched = 0;
									if(allArrived == 0) {
										timer += 15;
										count1 += 15;
										//System.out.println("Current Time Passed in seconds: " + timer);
										//System.out.println("A round is done");
										
									}
									
									//Thread.sleep(10);
									
									Traffic newTraffic = new Traffic();
									/*try {
										//newTraffic.traffic();
									} catch (InterruptedException e2) {
										e2.printStackTrace();
									}*/
									displayCars(cars, carNums);
									//System.out.println("A round is done");
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e1) {
										e1.printStackTrace();
									}
									//timer += 15;
									//System.out.println("Current Time Passed in seconds: " + timer);
									txtpnTimer.setText("Timer: " + time1.format(timer*1000));
								}
							}
					   }).start();
					  btnStart.setActionCommand("Start");
					   btnStart.setText("Start");
				   }
				   else if(command.equals("Pause")) {
					   btnStart.setActionCommand("Start");
					   btnStart.setText("Start");
					   allArrived = 0;
					   chThread = 0;
					   displayCars(cars, carNums);
					   pauseCheck = 1;
				   }
				   else if(command.equals("Reset")) {
					   btnStart.setActionCommand("Start");
					   btnStart.setText("Start");
					   timer = 0;
					   allArrived = 0;
					   chThread = 1;
					   txtpnTimer.setText("Timer: " + time1.format(0));
					   //timer = 0;
					   /* 
					    * Makes all combo boxes reappear so the types of intersections
					    * can be changed again.  Also makes the addCar button reappear
					    */
					   for (int i = 0; i < comboBoxes.size(); i++) {
						   comboBoxes.get(i).setVisible(true);
					   }
					   for(int i = 0; i < panels.size(); i++) {
						   panels.get(i).setVisible(false);
					   }
					   addCar.setVisible(true);
					   
					   for (Car car : cars) {
						   car.reset();
					   }
					   displayCars(cars, carNums);
				   }
			   }
			});
		menuBar.add(btnStart);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) { 
				   String command = e.getActionCommand();
					checkOn=0;
				   if(command.equals("Reset")) {
					   btnStart.setActionCommand("Start");
					   btnStart.setText("Start");
					   allArrived = 0;
					   chThread = 0;
					   pauseCheck = 0;
					   txtpnTimer.setText("Timer: " + time1.format(0));
					   //timer = 0;
					   /* 
					    * Makes all combo boxes reappear so the types of intersections
					    * can be changed again.  Also makes the addCar button reappear
					    */
					   for (int i = 0; i < comboBoxes.size(); i++) {
						   comboBoxes.get(i).setVisible(true);
					   }
					   for(int i = 0; i < panels.size(); i++) {
						   panels.get(i).setVisible(false);
					   }
					   addCar.setVisible(true);
					   
					   for (Car car : cars) {
						   car.reset();
					   }
					   displayCars(cars, carNums);
				   }
		   }
		});
		menuBar.add(btnReset);
		menuBar.add(txtpnTimer);
		
		/*
		 * action listener for addCar button
		 * displays a dialog box prompting user to input a file name containing
		 * the start and end points of cars they want to add then calls a method
		 * to read the file and create the car objects with the provided info 
		 * from the file
		 */
		menuBar.add(addCar);
		
		JButton help = new JButton("Help");
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String command = e.getActionCommand();
				if (command.contentEquals("Help")) {
					JOptionPane.showMessageDialog(frame, "Start Button: When the start button is pressed,\n"
							+ "the timer will begin counting, and the cars\n will set off from their starting points."
							+ "\nThe simulation will run until all cars have \nreached their destination, or the reset\n"
							+ "button is pressed.\n"
							+ "Reset Button:  When the reset button is pressed,\n the timer will be reset to 0, and \n"
							+ "all cars will be returned to their\n starting points.  Additionally, all intersections\n"
							+ "will be returned to their default setting.\n"
							+ "Add Car:  When the add car button is pressed, \na prompt will appear asking for the"
							+ "\n of a file that contains start and \nend points for each car you want to simulate.\n"
							+ "Acceptable start and end points are represented \nby the dots located on the map.\n"
							+ "These dots can be thought of as points\n on a grid, with the dot in the top left\n "
							+ "corner having an index of 1,1.  \nNote: the start and end points of a single car should\n"
							+ "be entered on the same line of the file,\n with each number separated by a comma. \n"
							+ "An example of a properly formatted entry is: 1,1,1,10. \n Additionally, when this button"
							+ "is pressed, all cars that\n were previously entered will be deleted.");
				}
			}
		});
		menuBar.add(help);
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
	
	/*
	 * This method is responsible for reading the file of start and end points specified by the user
	 * and creating all of the necessary Car objects with the given start and end points.  Additionally,
	 * it deletes the old cars that may still exist from a previous simulation
	 */
	private void createCars(String file, JLayeredPane pane) {
		for (Car car : cars) {
			car = null;
		}
		try {
			FileReader reader = new FileReader(file);
			BufferedReader bReader = new BufferedReader(reader);
			String[] points = new String[4];
			String line;
			int i = 0;
			int startX, startY, endX, endY;
			while ((line = bReader.readLine()) != null) {
				points = line.split(",");
				startX = Integer.parseInt(points[0].trim());
				startY = Integer.parseInt(points[1].trim());
				endX = Integer.parseInt(points[2].trim());
				endY = Integer.parseInt(points[3].trim());
				if (traffic1.checkCityMap(startX, startY, endX, endY)) {
					Car car = new Car(startX, startY, endX, endY, i);
					cars.add(car);
					JLabel carNum = new JLabel();
					carNum.setBackground(Color.white);
					carNum.setForeground(Color.white);
					carNum.setBounds(0, 0, 20, 20);
					carNum.setVisible(false);
					carNum.setIcon(carE);
					pane.add(carNum);
					carNums.add(carNum);
					i++;
				}
				else {
					JOptionPane.showMessageDialog(frame, "Unable to add car number " + i++ + ", please check the start and end coordinates.");
				}
			}
			if (cars.size() != 0) {
				displayCars(cars, carNums);
			}
		}
		catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + file + "'");
		}
		catch (IOException ex) {
			System.out.println("Error reading file '" + file + "'");
		}
	}
	
	/*
	 * This method converts the current x and current y positions into the x and y coords that 
	 * represent where they should be displayed on the map of pacopolis
	 */
	private int[] getLocation(int x, int y) {
		x = x - 1;
		y = y - 1;
		int[] location = new int[2];
		int xAdjusted = 38; 	//41
		int yAdjusted = 43;		//47
		int xDist = 29;
		int yDist = 31;
		
		if((x == y) &&  (x == 0)) {
			location[0] = xAdjusted;
			location[1] = yAdjusted;
		}
		
		else if (x == 0) {
			location[0] = xAdjusted;
			location[1] = (yDist * y) + yAdjusted;
		}
		
		else if (y == 0) {
			location[0] = (xDist * x) + xAdjusted;
			location[1] = yAdjusted;
		}
		
		else {
			location[0] = (xDist * x) + xAdjusted;
			location[1] = (yDist * y) + yAdjusted;
		}
		return location;
	}
	/*
	 * This method creates text boxes that display the number of the car, kind of like a license plate,
	 * so the user can easily identify each car, and positions them at their current position on the map
	 */
	private void displayCars(List<Car> cars, List<JLabel> carNums) {
		int[] location = new int[2];
		int i = 0;
		for (Car car : cars) {
			location = getLocation(car.getCurrX(), car.getCurrY());
			carNums.get(i).setVisible(false);
			
			if (car.getCurrDirection() == 1) {
				location[0] = location[0] + 12;
				carNums.get(i).setIcon(carN);
			}
			else if (car.getCurrDirection() == 2) {
				location[1] = location[1] + 12;
				carNums.get(i).setIcon(carE);
			}
			else if (car.getCurrDirection() == 3) {
				location[0] = location[0] - 18;
				carNums.get(i).setIcon(carS);
			}
			else if (car.getCurrDirection() == 4) {
				location[1] = location[1] - 18;
				carNums.get(i).setIcon(carW);
			}
	
			carNums.get(i).setLocation(location[0], location[1]);
			carNums.get(i).setVisible(true);
			i++;
		}
	}
}
