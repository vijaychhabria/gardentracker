
/*
* [Your Vijay Chhabria]
* [Your-vrc34@drexel.edu]
* CS530:GUI, Final Project
*/

import javax.swing.*;              
import javax.swing.border.EtchedBorder;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;
import javax.swing.JFileChooser;
import java.io.File;
import java.sql.*;
import java.awt.*;

import javax.sql.*;

// This class is a subclass of JFrame.
// This class instantiates the 3 panels
// 1 . Button Panel which contains the Add, Change and Remove button
// 2. ShapesList Panel which contain the list for the objects in canvas
// 3. ShapesPanel which contains the canvas to draw the objects.
// Border layout is used to place all the panels in place
class MyFrame extends JFrame {
    public MyFrame() {
        super("GardenTracker");
        //setSize(240, 400);    
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        getContentPane().setLayout(new BorderLayout());
        this.setSize(500,800);
        
        //HomePagePanel mHomePagePanel = new HomePagePanel();
        HomeJDialog mHomePageDialog = new HomeJDialog();
        mHomePageDialog.setPreferredSize(new Dimension(300,500));
        mHomePageDialog.setVisible(true);
        this.getContentPane().add( mHomePageDialog );// adding to content pane will work here. Please read the comment bellow.
		// Place the 3 panels in Borderlayout
		setLayout(new BorderLayout());

		add(mHomePageDialog, BorderLayout.CENTER);
	

		show();   
    }
}

// main class which instantiated MyFrame
public class GardenTrackerPanel {
    
    public static void main(String[] args) {
        MyFrame frame = new MyFrame();

        frame.pack();
        frame.setLocationRelativeTo(null);  
        frame.setResizable(false);
        frame.setVisible(true);

    }
}

//This class is the modal dialog for Shapes. It implements the different tabbed panel for each shape
class HomeJDialog extends JPanel implements ActionListener,ListSelectionListener  {

	// Look up for different charts
	final int ADD_MODE = 1;
	final int MODIFY_MODE = 2;
	final int DELETE_MODE = 3;
	
	String[] statusLabels = { "None", "Dry", "Normal"};

    //headers for the table
    String[] AreaDispalyColumns = new String[] {
        "Id", "Area"
    };
     
    //actual data for the table in a 2d array

    //actual data for the table in a 2d array
    Object[][] AreaDisplayData = new Object[][] {

    };
    
    //headers for the table
    String[] PlantDispalyColumns = new String[] {
        "Name", "Area", "Status"
    };
     
    //actual data for the table in a 2d array
    Object[][] PlantDisplayData = new Object[][] {

    };
	// Different shapes panel 
	 private javax.swing.JPanel PlantLogPanel;
	 private javax.swing.JPanel PlantLogBoxPanel;
	 private javax.swing.JPanel GeoTitlePanel;
	 private javax.swing.JPanel GeoPanel;
	 private javax.swing.JPanel GeoPanelData;
	 private javax.swing.JPanel GeoRadioPanel;
	 private javax.swing.JPanel GeoBoxPanel;
	 private javax.swing.JPanel PlantTitlePanel;
	 private javax.swing.JPanel PlantPanel;
	 private javax.swing.JPanel PlantPanelData;
	 private javax.swing.JPanel PlantRadioPanel;
	 private javax.swing.JPanel PlantBoxPanel;
	 private javax.swing.JPanel AlertPanel;
	 private javax.swing.JPanel AlertBoxPanel;
	 private javax.swing.JPanel HelpPanel;
	 private javax.swing.JPanel HelpTitlePanel;
	 private javax.swing.JPanel HelpBoxPanel;
	 private javax.swing.JPanel MainPanel;
 
	 // Define the tabbed panel
	 private javax.swing.JTabbedPane jTabbedPane1;
	
	 
	 JLabel MainTitlelabel = new JLabel("Garden Tracker");
	 
	 ImageIcon gardenImage;
	 JLabel gardenBackground;
	 Image garden;
	 
	 // Plant log panel field definitions

	 JLabel PlantlogTitlelabel = new JLabel("Enter Plant Log");
	 JLabel  PlantLogGeoArealabel= new JLabel("Area: ", JLabel.LEFT);
	 final JTextField PlantLogGeoAreaText = new JTextField(1);
	 final JComboBox PlantLogGeoAreaCombo = new JComboBox();
	 JLabel  PlantLogSelectPlantlabel= new JLabel("Select Plant: ", JLabel.LEFT);
	 final JTextField PlantLogSelectPlantText = new JTextField(1);
	 final JComboBox PlantLogPlantCombo = new JComboBox();
	 JLabel  PlantLogAddLogEntrylabel= new JLabel("Add Log Entry: ", JLabel.LEFT);
	 final JTextField PlantLogAddLogEntryText = new JTextField(1);
	 JLabel  PlantLogUploadPhotolabel= new JLabel("Upload Photo: ", JLabel.LEFT);
	 JButton PlantLogUploadPhotoText = new JButton("Upload image");
	 JLabel  PlantLogStatuslabel= new JLabel("Status: ", JLabel.LEFT);
	 JComboBox PlantLogStatusFillBox = new JComboBox(statusLabels);  
	 JFileChooser fileChooser = new JFileChooser();
	 
	 // Buttons for Plant Log panel
	 final JButton PlantLogSaveButton = new JButton("Save");
	 final JButton PlantLogCancelButton = new JButton("Cancel");
	 final JButton PlantLogHelpButton = new JButton("Help");
	 final JButton PlantLogHomeButton = new JButton("Home");
	 
	 // Plant panel field definitions
	 JLabel PlantTitlelabel = new JLabel("Plant Management");
	 private int PlantOption = 0;
	 JRadioButton PlantAddOption = new JRadioButton("Add");
	 JRadioButton PlantModifyOption = new JRadioButton("Modify");
	 JRadioButton PlantDeleteOption = new JRadioButton("Delete");
	 
	 ButtonGroup bgroup = new ButtonGroup();
	  
	 JLabel  PlantDisplaylabel= new JLabel("Plant List: ", JLabel.LEFT);
	 JTable PlantEnterTable = new JTable(new DefaultTableModel(PlantDisplayData, PlantDispalyColumns));
	 
	 DefaultTableModel defaultModel = new DefaultTableModel();
	 
	 JScrollPane PlantScrollPane = new JScrollPane(PlantEnterTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	 ListSelectionModel PlantSelectionModel = PlantEnterTable.getSelectionModel();
	 JLabel  PlantEnterlabel= new JLabel("Plant : ", JLabel.LEFT);
	 final JTextField PlantText = new JTextField(10);
	 JLabel  AreaEnterlabel= new JLabel(" Area : ", JLabel.LEFT);
	 //final JTextField AreaText = new JTextField(10);	 
	 final JComboBox PlantAreaCombo = new JComboBox();
	 
	// Buttons for Plant panel
	 final JButton PlantConfirmButton = new JButton("Save");
	 final JButton PlantCancelButton = new JButton("Cancel");
	 final JButton PlantHelpButton = new JButton("Help");
	 final JButton PlantHomeButton = new JButton("Home");
	 
	 // Controls for Area panel
	 private int GeoOption = 0;
	 JLabel GeoTitlelabel = new JLabel("Area Management");
	 JRadioButton GeoAddOption = new JRadioButton("Add");
	 JRadioButton GeoModifyOption = new JRadioButton("Modify");
	 JRadioButton GeoDeleteOption = new JRadioButton("Delete");
	 
	 ButtonGroup ggroup = new ButtonGroup();
	  
	 JLabel  GeoEnterlabel= new JLabel("Enter Area: ", JLabel.LEFT);
	 final JTextField GeoText = new JTextField(10);
	 
	 JLabel  AreaDisplaylabel= new JLabel("Area List: ", JLabel.LEFT);
	 JTable GeoTable = new JTable(new DefaultTableModel(AreaDisplayData, AreaDispalyColumns));
	 DefaultTableModel GeoTablemodel;// = (DefaultTableModel) GeoTable.getModel();
	 ListSelectionModel GeoSelectionModel = GeoTable.getSelectionModel();
	 JScrollPane GeoScrollPane = new JScrollPane(GeoTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);	 
	// Buttons for Geo panel
	 final JButton GeoConfirmButton = new JButton("Save");
	 final JButton GeoCancelButton = new JButton("Cancel");
	 final JButton GeoHelpButton = new JButton("Help");
	 final JButton GeoHomeButton = new JButton("Home");
 
	 // Controls for Alert panel
	 
	 JLabel SetupAlertlabel = new JLabel("Setup Alerts");
	 JLabel  AlertTimeofDaylabel= new JLabel("Time of Day alert : ", JLabel.LEFT);
	 final JTextField AlertTimeofDayText = new JTextField(10);
	 JLabel  AlertWeeklylabel= new JLabel("Weekly alert : ", JLabel.LEFT);
	 final JTextField AlertWeeklyText = new JTextField(10);
	 JLabel  AlertMonthlylabel= new JLabel("Monthly alert : ", JLabel.LEFT);
	 final JTextField AlertMonthlyText = new JTextField(10);
	// Buttons for Rectangle panel
	 final JButton AlertOkButton = new JButton("Save");
	 final JButton AlertCancelButton = new JButton("Cancel");
	 final JButton AlertHomeButton = new JButton("Home");
	 final JButton AlertHelpButton = new JButton("Help");
	
	 // Controls for Help panel
	 JLabel HelpTitlelabel = new JLabel("Help");
	 JLabel  Helplabel= new JLabel("This area would contain the help description. ", JLabel.LEFT);  
	// Buttons for Help panel
	 final JButton  HelpOkButton = new JButton("Ok");
	 final JButton  HelpCancelButton = new JButton("Cancel");
	 final JButton  HelpHomeButton = new JButton("Home");
	 
	 // Database definitions
     Connection connect = null;

     private Statement statementPlantLog = null;
     private ResultSet resultSetPlantLog = null;
     private PreparedStatement preparedStatementPlantLog = null;
     private int maxPlantLog = 0;
     
     private Statement statementPlant = null;
     private ResultSet resultSetPlant = null;
     private PreparedStatement preparedStatementPlant = null;
     private int maxPlant = 0;
     
     private Statement statementArea = null;
     private ResultSet resultSetArea = null;
     private PreparedStatement preparedStatementArea = null;
     private int maxArea = 0;
     
     private Statement statementAlert = null;
     private ResultSet resultSetAlert = null;
     private PreparedStatement preparedStatementAlert = null;
     private int maxAlert = 0;
     
	//Buttons for Main panel
	final JButton PlantLogButton = new JButton("Enter Plant Log");
	final JButton GeoButton = new JButton("Area Management");
	final JButton PlantButton = new JButton("Plant Management");
	final JButton AlertButton = new JButton("Alert Management");
	final JButton HelpButton = new JButton("Help");

 
 // Constructor
 public HomeJDialog() {
	 //super(new GridLayout(1, 1));
	 setSize(500, 800); 
     initComponents();
 }

 // Constructor	
 public HomeJDialog(java.awt.Frame parent, boolean modal) {
     //super(parent, modal);
     initComponents();
 }



 // Helper function to select a tab for change button functionality
 public void SelectTabbedPanel(int index){
 	jTabbedPane1.setSelectedIndex(index);
 }

	
 @SuppressWarnings("unchecked")
 // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
 private void initComponents() {

 	// Create the tabbed pane and the different shapes panel
	 //setLayout(new BorderLayout());

	 //garden = getImage(getCodeBase(), "garden.jpg");
	 
	 InitGardenTrackerPanels();

	 // Main Panel
     InitMainPanel();
   
     // Plant Log Panel
     InitPlantLogPanel();
     
  	// Plant Panel
	InitPlantPanel();
	
	// Area Panel
	InitAreaPanel();
	
	// Alert Panel
	InitAlertPanel();
	
	// Help Panel
	InitHelpPanel();
	
     try {

    // This will load the MySQL driver, each DB has its own driver
    Class.forName("com.mysql.jdbc.Driver");
    // Setup the connection with the DB
    connect = DriverManager
        .getConnection("jdbc:mysql://localhost/feedback?"
            + "user=vijay&password=vijay");
    

     	System.out.print("you made connection");
  	
	 
     } //end try

	     catch(ClassNotFoundException e) {
	     e.printStackTrace();
	     System.err.println(e);
     }
	 	 catch(SQLException sqle) {
	 		sqle.printStackTrace();
	     System.err.println(sqle);
 	 }
     

     add(MainPanel);
     add(PlantLogPanel);
     add(PlantLogBoxPanel);
     add(GeoTitlePanel);
     add(GeoPanel);
     add(GeoRadioPanel);
     add(GeoPanelData);
     add(GeoBoxPanel);
     add(PlantTitlePanel);
     add(PlantPanel);
     add(PlantRadioPanel);
     add(PlantPanelData);
     add(PlantBoxPanel);
     add(AlertPanel);
     add(AlertBoxPanel);
     add(HelpTitlePanel);
     add(HelpPanel);
     add(HelpBoxPanel);
     
     MainPanel.setVisible(true);


     //pack();
     //setVisible(true);
 }// </editor-fold>                        

 // Initialize Garden Tracker Panel
 public void InitGardenTrackerPanels()
 {
	 MainPanel = new javax.swing.JPanel();
     jTabbedPane1 = new javax.swing.JTabbedPane();
     PlantLogPanel = new javax.swing.JPanel();
     PlantLogBoxPanel = new javax.swing.JPanel();
     PlantLogBoxPanel.setAlignmentX(1f);
     PlantLogBoxPanel.setLayout(new BoxLayout(PlantLogBoxPanel, BoxLayout.X_AXIS));
     add(PlantLogBoxPanel, BorderLayout.SOUTH);
     
     //PlantLogPanel.setBackground(java.awt.Color.GREEN);
     
     GeoPanel = new javax.swing.JPanel();
     
     GeoPanelData = new javax.swing.JPanel();
     GeoTitlePanel = new javax.swing.JPanel();
     GeoRadioPanel = new javax.swing.JPanel();
     GeoBoxPanel = new javax.swing.JPanel();
     GeoBoxPanel.setAlignmentX(1f);
     GeoBoxPanel.setLayout(new BoxLayout(GeoBoxPanel, BoxLayout.X_AXIS));     
    
     add(GeoBoxPanel, BorderLayout.SOUTH);
     
     PlantPanel = new javax.swing.JPanel();
     PlantPanelData = new javax.swing.JPanel();
     PlantTitlePanel = new javax.swing.JPanel();
     PlantRadioPanel = new javax.swing.JPanel();
     PlantBoxPanel = new javax.swing.JPanel();
     PlantBoxPanel.setAlignmentX(1f);
     PlantBoxPanel.setLayout(new BoxLayout(PlantBoxPanel, BoxLayout.X_AXIS));    
     add(PlantBoxPanel, BorderLayout.SOUTH);
     
     AlertPanel = new javax.swing.JPanel();
     AlertBoxPanel = new javax.swing.JPanel();
     AlertBoxPanel.setAlignmentX(1f);
     AlertBoxPanel.setLayout(new BoxLayout(AlertBoxPanel, BoxLayout.X_AXIS));   
     add(AlertBoxPanel, BorderLayout.SOUTH);
     
     HelpTitlePanel = new javax.swing.JPanel();
     HelpPanel = new javax.swing.JPanel();
     HelpBoxPanel = new javax.swing.JPanel();
     HelpBoxPanel.setAlignmentX(1f);
     HelpBoxPanel.setLayout(new BoxLayout(HelpBoxPanel, BoxLayout.X_AXIS)); 
     add(HelpBoxPanel, BorderLayout.SOUTH);
 }
 
 // Initialize Plant Log Panel
 public void InitPlantLogPanel()
 {
	PlantlogTitlelabel.setHorizontalAlignment(JLabel.CENTER);
	PlantlogTitlelabel.setFont(new Font("Serif", Font.BOLD, 16));
	PlantLogPanel.add(PlantlogTitlelabel);
	//PlanlogTitlelabel.setBorder(PlanlogTitletitled);
    PlantLogPanel.setLayout(new BoxLayout(PlantLogPanel , BoxLayout.Y_AXIS));
    PlantLogPanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));     
 
 
    
 	PlantLogPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    PlantLogPanel.add(PlantLogGeoArealabel);
    PlantLogPanel.add(Box.createRigidArea(new Dimension(5, 0)));
 	//PlantLogPanel.add(PlantLogGeoAreaText);
 	PlantLogPanel.add(PlantLogGeoAreaCombo);
 	
 	PlantLogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
 	PlantLogPanel.add(PlantLogSelectPlantlabel);
 	//PlantLogPanel.add(PlantLogSelectPlantText);
 	PlantLogPanel.add(PlantLogPlantCombo);
 	PlantLogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
 	PlantLogPanel.add(PlantLogAddLogEntrylabel);
 	PlantLogPanel.add(PlantLogAddLogEntryText);
 	PlantLogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
 	PlantLogPanel.add(PlantLogUploadPhotolabel);

 	PlantLogPanel.add(PlantLogUploadPhotoText);
 	
 	PlantLogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
 	PlantLogPanel.add(PlantLogStatuslabel);    	
 	PlantLogPanel.add(PlantLogStatusFillBox);
 	PlantLogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
 	
 	fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
 	
 	PlantLogBoxPanel.add(Box.createRigidArea(new Dimension(0, 200)));
 	PlantLogBoxPanel.add(PlantLogSaveButton);
 	PlantLogBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
 	PlantLogBoxPanel.add(PlantLogHelpButton);
 	PlantLogBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
 	PlantLogBoxPanel.add(PlantLogHomeButton);
 	PlantLogBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
	
 	// Define the action listener for Plant Log Panel
 	PlantLogUploadPhotoText.addActionListener(this);
 	PlantLogSaveButton.addActionListener(this);
 	PlantLogCancelButton.addActionListener(this);
 	PlantLogHelpButton.addActionListener(this);
 	PlantLogHomeButton.addActionListener(this);
 	PlantLogPanel.setVisible(false);
 	PlantLogBoxPanel.setVisible(false);	 
 }

 // Initialize Plant Panel
 public void InitPlantPanel()
 {
     PlantTitlelabel.setHorizontalAlignment(JLabel.CENTER);
	 PlantTitlelabel.setFont(new Font("Serif", Font.BOLD, 16));
	 PlantTitlePanel.add(Box.createRigidArea(new Dimension(0, 10)));
	 PlantTitlePanel.add(PlantTitlelabel);
	 PlantTitlePanel.add(Box.createRigidArea(new Dimension(0, 25)));
	 PlantPanel.setLayout(new BoxLayout(PlantPanel , BoxLayout.Y_AXIS));
	 //PlantPanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));  
     PlantPanel.add(PlantDisplaylabel);
     PlantPanel.add(Box.createRigidArea(new Dimension(0, 10)));
     PlantPanel.add(PlantEnterTable);
     PlantPanel.add(PlantScrollPane);
     PlantPanel.add(Box.createRigidArea(new Dimension(0, 10)));
	 PlantRadioPanel.add(PlantAddOption);
	 PlantRadioPanel.add(PlantModifyOption);
	 PlantRadioPanel.add(PlantDeleteOption);

     
     bgroup.add(PlantAddOption);
     bgroup.add(PlantModifyOption);
     bgroup.add(PlantDeleteOption);
     
     PlantModifyOption.setEnabled(false);
     PlantDeleteOption.setEnabled(false);
     
     PlantPanelData.setLayout(new BoxLayout(PlantPanelData , BoxLayout.Y_AXIS));
     PlantPanelData.setBorder(new EmptyBorder(new Insets(20, 60, 30, 60)));      

     PlantPanelData.add(PlantEnterlabel);
     PlantPanelData.add(PlantText);
     PlantPanelData.add(Box.createRigidArea(new Dimension(0,15)));
     PlantPanelData.add(AreaEnterlabel);
     PlantPanelData.add(PlantAreaCombo);
     PlantPanelData.add(Box.createRigidArea(new Dimension(0, 5)));
     PlantEnterTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
     PlantBoxPanel.add(Box.createRigidArea(new Dimension(0, 150)));
     PlantBoxPanel.add(PlantConfirmButton);
     PlantBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
     PlantBoxPanel.add(PlantHelpButton);
     PlantBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
     PlantBoxPanel.add(PlantHomeButton);
     PlantBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));

  	
     PlantTitlePanel.setVisible(false);
     PlantPanel.setVisible(false);  
     PlantPanelData.setVisible(false);  
     PlantRadioPanel.setVisible(false);  
     PlantBoxPanel.setVisible(false);  
     
     // Define the action listener for Plant Panel
 	 PlantSelectionModel.addListSelectionListener( this );
     PlantAddOption.addActionListener(this);
     PlantModifyOption.addActionListener(this);
     PlantDeleteOption.addActionListener(this);
     
     PlantConfirmButton.addActionListener(this);
     PlantCancelButton.addActionListener(this);
     PlantHelpButton.addActionListener(this);
     PlantHomeButton.addActionListener(this);
	 
 }

 // Initialize Area Panel
public void InitAreaPanel()
{
    // Add the controls to Geo Panel
    GeoPanel.setLayout(new BoxLayout(GeoPanel , BoxLayout.Y_AXIS));
    GeoPanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60))); 
    GeoTitlelabel.setHorizontalAlignment(JLabel.CENTER);
	 GeoTitlelabel.setFont(new Font("Serif", Font.BOLD, 16));
	 GeoTitlePanel.add(Box.createRigidArea(new Dimension(0, 25)));
	 GeoTitlePanel.add(GeoTitlelabel);
	 GeoTitlePanel.add(Box.createRigidArea(new Dimension(0, 25)));
	 
    GeoPanel.add(AreaDisplaylabel);
    GeoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    GeoTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    GeoPanel.add(GeoTable);
    GeoPanel.add(GeoScrollPane);
    
    GeoSelectionModel.addListSelectionListener( this );
    GeoRadioPanel.add(GeoAddOption);
    GeoRadioPanel.add(GeoModifyOption);
    GeoRadioPanel.add(GeoDeleteOption);
   
    ggroup.add(GeoAddOption);
    ggroup.add(GeoModifyOption);
    ggroup.add(GeoDeleteOption);
    
    GeoModifyOption.setEnabled(false);
    GeoDeleteOption.setEnabled(false);
    
    GeoPanelData.add(GeoEnterlabel);
    GeoPanelData.add(GeoText);
    GeoPanelData.add(Box.createRigidArea(new Dimension(0, 25)));

   GeoBoxPanel.add(Box.createRigidArea(new Dimension(0, 200)));
    GeoBoxPanel.add(GeoConfirmButton);
    GeoBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
    GeoBoxPanel.add(GeoHelpButton);
    //GeoBoxPanel.add(GeoCancelButton);
    GeoBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
    GeoBoxPanel.add(GeoHomeButton);
    GeoBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
   
    GeoTitlePanel.setVisible(false); 
    GeoPanel.setVisible(false); 
    GeoPanelData.setVisible(false); 
    GeoRadioPanel.setVisible(false); 
    GeoBoxPanel.setVisible(false); 
    // Define the action listener for Geo Panel
    
    GeoAddOption.addActionListener(this);
    GeoModifyOption.addActionListener(this);
    GeoDeleteOption.addActionListener(this);
    
    GeoConfirmButton.addActionListener(this);
    GeoCancelButton.addActionListener(this);
    GeoHelpButton.addActionListener(this);
    GeoHomeButton.addActionListener(this);
	
}

// Initialize Alert Panel
public void InitAlertPanel()
{
    // Add the line controls to Line Panel
    SetupAlertlabel.setHorizontalAlignment(JLabel.CENTER);
    SetupAlertlabel.setFont(new Font("Serif", Font.BOLD, 16));
    AlertPanel.add(SetupAlertlabel);
 
    AlertPanel.setLayout(new BoxLayout(AlertPanel , BoxLayout.Y_AXIS));
    AlertPanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 60)));

    AlertPanel.add(Box.createRigidArea(new Dimension(0, 25)));
    AlertPanel.add(AlertTimeofDaylabel);
    AlertPanel.add(AlertTimeofDayText);
    AlertPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    AlertPanel.add(AlertWeeklylabel);
    AlertPanel.add(AlertWeeklyText);
    AlertPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    AlertPanel.add(AlertMonthlylabel);
    AlertPanel.add(AlertMonthlyText);
    
    AlertBoxPanel.add(Box.createRigidArea(new Dimension(0, 400)));
    AlertBoxPanel.add(AlertOkButton);
    AlertBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
    AlertBoxPanel.add(AlertHelpButton);
    AlertBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
    AlertBoxPanel.add(AlertHomeButton);
    AlertBoxPanel.add(Box.createRigidArea(new Dimension(5, 0)));
    
    AlertPanel.setVisible(false);   
    AlertBoxPanel.setVisible(false);   
    
    // Define the action listener for Pentagon
    AlertOkButton.addActionListener(this);
    AlertCancelButton.addActionListener(this);
    AlertHomeButton.addActionListener(this);
    AlertHelpButton.addActionListener(this);
	
}

// Initialize Help Panel
public void InitHelpPanel()
{
    // Add the line controls to Line Panel
    HelpPanel.setLayout(new BoxLayout(HelpPanel , BoxLayout.Y_AXIS));
    HelpPanel.setBorder(new EmptyBorder(new Insets(40, 60, 40, 160)));  
    HelpPanel.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(5.0f)));
    Helplabel.setHorizontalAlignment(JLabel.CENTER);
    Helplabel.setFont(new Font("Serif", Font.BOLD, 16));
    HelpTitlelabel.setHorizontalAlignment(JLabel.CENTER);
    HelpTitlelabel.setFont(new Font("Serif", Font.BOLD, 16));     
    HelpTitlePanel.add(HelpTitlelabel);
    HelpTitlePanel.add(Box.createRigidArea(new Dimension(0, 50)));
    
    HelpPanel.add(Helplabel);	
    HelpPanel.add(Box.createRigidArea(new Dimension(0, 50)));
    HelpBoxPanel.add(Box.createRigidArea(new Dimension(0, 600)));
    HelpBoxPanel.add(HelpHomeButton);
    
    HelpTitlePanel.setVisible(false); 
    HelpPanel.setVisible(false);   
    HelpBoxPanel.setVisible(false);   
    // Define the action listener for Octagon
    HelpOkButton.addActionListener(this);
    HelpCancelButton.addActionListener(this);
    HelpHomeButton.addActionListener(this);
	
}
// Validation for Plant Log parameters
 public boolean validatePlantLogParameters()
 {
 	boolean returnFlag = false;

 	try {
	
	 		String Plant = (String)PlantLogPlantCombo.getSelectedItem();
	 		String Area = (String)PlantLogGeoAreaCombo.getSelectedItem();
	 		String Status = (String)PlantLogStatusFillBox.getSelectedItem();
	 		if ((Plant.equals(""))	|| (Area.equals("")) ||
					(PlantLogAddLogEntryText.getText().toString().equals("")))	
				returnFlag = false;
			else
			{
				returnFlag =  true;
				preparedStatementPlantLog = connect
			              .prepareStatement("insert into  gardentrackerdb.plantlog values (?, ?, ?, ?, ?)");
				preparedStatementPlantLog.setInt(1, maxPlantLog + 1);
				preparedStatementPlantLog.setString(2, Plant);
				preparedStatementPlantLog.setString(3, Area);
				preparedStatementPlantLog.setString(4, PlantLogAddLogEntryText.getText().toString());
				preparedStatementPlantLog.setString(5, Status);
				preparedStatementPlantLog.executeUpdate();
			}
			
 			
		} catch (NumberFormatException nfe) {
			returnFlag = false;
		}
 		catch ( Exception e) {
 			returnFlag = false;
 			JOptionPane.showMessageDialog (null, e.getMessage());
 		}
 	
 	return returnFlag;
 }
 
 public void InitMainPanel()
 {
     MainPanel.setLayout(new BoxLayout(MainPanel , BoxLayout.Y_AXIS));
     MainPanel.setBorder(new EmptyBorder(new Insets(60, 90, 60, 90)));

     
   	 MainTitlelabel.setHorizontalAlignment(JLabel.CENTER);
 	 MainTitlelabel.setFont(new Font("Serif", Font.BOLD, 24));
 	 MainPanel.add(MainTitlelabel);
 	 MainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
     MainPanel.add(PlantLogButton);
     MainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
     MainPanel.add(GeoButton);
     MainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
     MainPanel.add(PlantButton);
     MainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
     MainPanel.add(AlertButton);
     MainPanel.add(Box.createRigidArea(new Dimension(0, 50)));
     MainPanel.add(HelpButton);
     //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

     PlantLogButton.addActionListener(this);
     GeoButton.addActionListener(this);
     PlantButton.addActionListener(this);
     AlertButton.addActionListener(this);
     HelpButton.addActionListener(this);
	 
 }
 
 
 // Validation for Plant parameters
 public boolean validatePlantParameters()
 {
 	boolean returnFlag = false;

 	try {
 			
	 		String Area = (String)PlantAreaCombo.getSelectedItem();
	 		if ((PlantText.getText().toString().equals(""))	|| (Area.equals("")))
	 			returnFlag = false;
	 		else
	 		{
	 			if (PlantOption == ADD_MODE)
	 			{
		 			preparedStatementPlant = connect
				              .prepareStatement("insert into  gardentrackerdb.plants values (?, ?, ?)");
		 			preparedStatementPlant.setInt(1, maxPlant + 1);
		 			preparedStatementPlant.setString(2, PlantText.getText().toString());
		 			preparedStatementPlant.setString(3, Area);
		 			preparedStatementPlant.executeUpdate();
				
		 			//PlantEnterTable.revalidate();
					returnFlag =  true;
	 			}
	 			else if (PlantOption == MODIFY_MODE)
	 			{
					returnFlag =  true;		 				
	 			}
	 			else if (PlantOption == DELETE_MODE)
	 			{
		 			preparedStatementPlant = connect
				              .prepareStatement("delete from  gardentrackerdb.plants where name = ? ;");
		 			preparedStatementPlant.setString(1, PlantText.getText().toString());
		 			preparedStatementPlant.executeUpdate();
					
		 			//PlantEnterTable.revalidate();
					returnFlag =  true;	 				
	 			}
	 			
	 			LoadPlantTable();
	 		}
	} catch (NumberFormatException nfe) {
		returnFlag = false;
	}
	catch ( Exception e) {
		returnFlag = false;
		JOptionPane.showMessageDialog (null, e.getMessage());
	}
 	
 	return returnFlag;
 }  
 
 // Validation for Geographical Area parameters
 public boolean validateGeographicalAreaParameters()
 {
 	boolean returnFlag = false;
 	try {
 		if (GeoText.getText().toString().equals(""))
 			returnFlag = false;
 		else
 		{
 			if (GeoOption == ADD_MODE)
 			{
	 			preparedStatementArea = connect
			              .prepareStatement("insert into  gardentrackerdb.areas values (?, ?)");
	 			preparedStatementArea.setInt(1, maxArea + 1);
	 			preparedStatementArea.setString(2, GeoText.getText().toString());
	 			preparedStatementArea.executeUpdate();
	 			
	 			//GeoTable.repaint();
	 			//GeoTable.revalidate();
				
				returnFlag =  true;
 			}
 			else if (GeoOption == MODIFY_MODE)
 			{ 
				returnFlag =  true;		
 			}
 			else if (GeoOption == DELETE_MODE)
 			{ 
	 			preparedStatementPlant = connect
			              .prepareStatement("delete from  gardentrackerdb.areas where name = ? ;");
	 			preparedStatementPlant.setString(1, GeoText.getText().toString());
	 			preparedStatementPlant.executeUpdate();
				
	 			//GeoTable.revalidate();
	 			
				returnFlag =  true;	 				
 			}			
 			
 			 LoadPlantArea();
 		}
	} catch (NumberFormatException nfe) {
		returnFlag = false;
	}
	catch ( Exception e) {
		returnFlag = false;
		JOptionPane.showMessageDialog (null, e.getMessage());
	}
 	return returnFlag;
 }    
 
 // Validation for Setup alert parameters
 public boolean validateSetupAlertsParameters()
 {
 	boolean returnFlag = false;
 	try {
 		if ((AlertTimeofDayText.getText().toString().equals(""))	|| (AlertWeeklyText.getText().toString().equals("")) ||
 					(AlertMonthlyText.getText().toString().equals("")))
 			returnFlag = false;
 		else		
			returnFlag =  true;
	} catch (NumberFormatException nfe) {
		returnFlag = false;
	}
 	
 	return returnFlag;
 }
 
// Handler for list selection changes for Plant and Area screens
 
public void valueChanged( ListSelectionEvent event )
{
	// See if this is a valid table selection
	if( event.getSource() == PlantEnterTable.getSelectionModel()
					&& event.getFirstIndex() >= 0 )
	{
		// Get the data model for this table
		TableModel model = (TableModel)PlantEnterTable.getModel();

		// Determine the selected item
		String string = (String)model.getValueAt(
				PlantEnterTable.getSelectedRow(),
				PlantEnterTable.getSelectedColumn() );

		if (PlantEnterTable.getSelectedColumn() == 0)
		{			
			//PlantText.setText(string);
			PlantModifyOption.setEnabled(true);
			PlantDeleteOption.setEnabled(true);
		}
		else if (PlantEnterTable.getSelectedColumn() == 1)
		{
			 PlantText.setText(string);
			 PlantModifyOption.setEnabled(true);
			 PlantDeleteOption.setEnabled(true);
		}
		
		// Display the selected item
		System.out.println( "Value selected = " + string );
	}
	else 	if( event.getSource() == GeoTable.getSelectionModel()
			&& event.getFirstIndex() >= 0 )
	{
		// Get the data model for this table
		TableModel model = (TableModel)GeoTable.getModel();
		
		// Determine the selected item
		String string = (String)model.getValueAt(
				GeoTable.getSelectedRow(),
				GeoTable.getSelectedColumn() );
		
		
		if (GeoTable.getSelectedColumn() == 1)
		{			
			GeoText.setText(string);
			GeoModifyOption.setEnabled(true);
			GeoDeleteOption.setEnabled(true);
		}

		// Display the selected item
		System.out.println( "Value selected = " + string );
	}
}
	
 // action handler for different buttons on shapes dialog
 public void actionPerformed(ActionEvent event) {

 	// event handler for Line OK
 	if(event.getSource() == PlantLogButton) {
 		//JOptionPane.showMessageDialog (null, "Enter all Circle attributes. No negative number allowed");	
 		
 		OnPlantLogButtonPressed();
 		
 	}
 	else if(event.getSource() == GeoButton) {

 		OnGeoButtonPressed();
 	}
 	// event handler for Circle OK
 	else if (event.getSource() == PlantButton) {
	
 		OnPlantButtonPressed();

 	}    	
 	else if (event.getSource() == AlertButton) {
	 		
 		OnAlertButtonPressed();     

 	}    	
 	// event handler for Help
 	else if (event.getSource() == HelpButton) {
 		
 		OnHelpButtonPressed();
 		
 	}    	
	else if (event.getSource() == PlantLogHomeButton) {
		
		OnPlantHomeButton();
		
 	}  	

	else if (event.getSource() == GeoHomeButton) {
	     MainPanel.setVisible(true);
	     PlantLogPanel.setVisible(false);
	     PlantLogBoxPanel.setVisible(false);
	     GeoTitlePanel.setVisible(false);
	     GeoPanel.setVisible(false);
 	     GeoPanelData.setVisible(false);
	     GeoRadioPanel.setVisible(false);
 	     GeoBoxPanel.setVisible(false);
 	     PlantTitlePanel.setVisible(false);
 	     PlantPanel.setVisible(false);
 	     PlantPanelData.setVisible(false);
 	     PlantRadioPanel.setVisible(false);
 	     PlantBoxPanel.setVisible(false);
 	     AlertPanel.setVisible(false);
 	     AlertBoxPanel.setVisible(false);
   	     HelpTitlePanel.setVisible(false);
 	     HelpPanel.setVisible(false);
 	     HelpBoxPanel.setVisible(false); 

 	}   
	else if (event.getSource() == PlantLogUploadPhotoText)
	{
		System.out.println("PlantLogUploadPhotoText");
		int result = fileChooser.showOpenDialog(this);
		
		if (result == JFileChooser.APPROVE_OPTION) {
		    // user selects a file
			File selectedFile = fileChooser.getSelectedFile();
			 System.out.println("Selected file: " + selectedFile.getAbsolutePath());
		}
		
	}
	else if (event.getSource() == PlantLogSaveButton)
	{
 		if (validatePlantLogParameters())
 		{	 			
 			JOptionPane.showMessageDialog (null, "Plant Log values updated");	 
 		}
		else
		{
			JOptionPane.showMessageDialog (null, "Enter all Plant Log values. No negative number allowed");	   
		}		
	}
	else if (event.getSource() == GeoConfirmButton)
	{
 		if (validateGeographicalAreaParameters())
 		{	
 			JOptionPane.showMessageDialog (null, "Geographical area values updated");	 
 		}
		else
		{
			JOptionPane.showMessageDialog (null, "Enter all Geo values. No negative number allowed");	  
		}	
	}
	else if (event.getSource() == PlantConfirmButton)
	{
 		if (validatePlantParameters())
 		{	
 			JOptionPane.showMessageDialog (null, "Plant values updated");	
 		}
 		else
 		{
    		JOptionPane.showMessageDialog (null, "Enter all Plant values. No negative number allowed");	    				    			
		}
	}
	else if (event.getSource() == AlertOkButton)
	{
 		if (validateSetupAlertsParameters())
 		{
 			JOptionPane.showMessageDialog (null, "Alert values updated");	
 		}
 		else
 		{
    		JOptionPane.showMessageDialog (null, "Enter all Alert values. No negative number allowed");	    				    			
		}	 	
	}
	else if ((event.getSource() == PlantLogHelpButton) ||
			(event.getSource() == PlantHelpButton) ||
			(event.getSource() == AlertHelpButton) ||
			(event.getSource() == GeoHelpButton)) {
	     MainPanel.setVisible(false);
	     PlantLogPanel.setVisible(false);
	     PlantLogBoxPanel.setVisible(false);
	     GeoTitlePanel.setVisible(false);
	     GeoPanel.setVisible(false);
 	     GeoPanelData.setVisible(false);
	     GeoRadioPanel.setVisible(false);
	     GeoBoxPanel.setVisible(false);
	     PlantTitlePanel.setVisible(false);
	     PlantBoxPanel.setVisible(false);
 	     PlantPanelData.setVisible(false);
	     AlertPanel.setVisible(false);
	     AlertBoxPanel.setVisible(false);
	     PlantPanel.setVisible(false);
	     PlantRadioPanel.setVisible(false);
	     HelpTitlePanel.setVisible(true);
	     HelpPanel.setVisible(true);
	     HelpBoxPanel.setVisible(true); 

	}  	

	else if (event.getSource() == PlantHomeButton) {
	     MainPanel.setVisible(true);
	     PlantLogPanel.setVisible(false);
	     PlantLogBoxPanel.setVisible(false);
	     GeoTitlePanel.setVisible(false);
	     GeoPanel.setVisible(false);
 	     GeoPanelData.setVisible(false);
	     GeoRadioPanel.setVisible(false);
	     GeoBoxPanel.setVisible(false);
	     PlantTitlePanel.setVisible(false);
	     PlantBoxPanel.setVisible(false);
 	     PlantPanelData.setVisible(false);
	     AlertPanel.setVisible(false);
	     AlertBoxPanel.setVisible(false);
	     PlantPanel.setVisible(false);
	     PlantRadioPanel.setVisible(false);
	     HelpTitlePanel.setVisible(false);
	     HelpPanel.setVisible(false);
	     HelpBoxPanel.setVisible(false); 

	}  	
	else if (event.getSource() == AlertHomeButton) {
		
	     MainPanel.setVisible(true);
	     PlantLogPanel.setVisible(false);
	     PlantLogBoxPanel.setVisible(false);
	     GeoTitlePanel.setVisible(false);
	     GeoPanel.setVisible(false);
 	     GeoPanelData.setVisible(false);
	     GeoRadioPanel.setVisible(false);
	     GeoBoxPanel.setVisible(false);
	     PlantTitlePanel.setVisible(false);
	     PlantPanel.setVisible(false); 
 	     PlantPanelData.setVisible(false);
	     PlantRadioPanel.setVisible(false);
	     PlantBoxPanel.setVisible(false);
	     AlertPanel.setVisible(false);
	     AlertBoxPanel.setVisible(false);
	     HelpTitlePanel.setVisible(false);
	     HelpPanel.setVisible(false);
	     HelpBoxPanel.setVisible(false); 

	}  	
	else if (event.getSource() == HelpHomeButton) {
	     MainPanel.setVisible(true);
	     PlantLogPanel.setVisible(false);
	     PlantLogBoxPanel.setVisible(false);
	     GeoTitlePanel.setVisible(false);
	     GeoPanel.setVisible(false);
 	     GeoPanelData.setVisible(false);
	     GeoRadioPanel.setVisible(false);
	     GeoBoxPanel.setVisible(false);
	     PlantTitlePanel.setVisible(false);
	     PlantPanel.setVisible(false);
 	     PlantPanelData.setVisible(false);
	     PlantRadioPanel.setVisible(false);
	     PlantBoxPanel.setVisible(false);
	     AlertPanel.setVisible(false);
	     AlertBoxPanel.setVisible(false);
	     HelpTitlePanel.setVisible(false);
	     HelpPanel.setVisible(false);
	     HelpBoxPanel.setVisible(false); 

	}  	
	else if (event.getSource() == PlantAddOption) {
		
		if (PlantAddOption.isSelected() == true) {
			PlantOption = ADD_MODE;
			//JOptionPane.showMessageDialog (null, "PlantAddOption");	
		}
		
	}
	else if (event.getSource() == PlantModifyOption) {
		
		if (PlantModifyOption.isSelected() == true) {
			PlantOption = MODIFY_MODE;
			//JOptionPane.showMessageDialog (null, "PlantModifyOption");	
		}
	}
	else if (event.getSource() == PlantDeleteOption) {
	
		if (PlantDeleteOption.isSelected() == true) {
			PlantOption = DELETE_MODE;
			//JOptionPane.showMessageDialog (null, "PlantDeleteOption");	
		}
	}    
	else if (event.getSource() == GeoAddOption) {
		if (GeoAddOption.isSelected() == true) {
			GeoOption = ADD_MODE;
			//JOptionPane.showMessageDialog (null, "GeoAddOption");	
		}
	}
	else if (event.getSource() == GeoModifyOption) {
		if (GeoModifyOption.isSelected() == true) {
			GeoOption = MODIFY_MODE;
			//JOptionPane.showMessageDialog (null, "GeoModifyOption");	
		}
	}
	else if (event.getSource() == GeoDeleteOption) {
		if (GeoDeleteOption.isSelected() == true) {
			GeoOption = DELETE_MODE;
			//JOptionPane.showMessageDialog (null, "GeoDeleteOption");	
		}
	}    

 }
 
 // Load the data for Plant Log Panel
 public void OnPlantLogButtonPressed()
 {
     try 
     {

			MainPanel.setVisible(false);
			 PlantLogPanel.setVisible(true);
			 PlantLogBoxPanel.setVisible(true);
			 GeoTitlePanel.setVisible(false);
			 GeoPanel.setVisible(false);
			 GeoPanelData.setVisible(false);
			 GeoRadioPanel.setVisible(false);
			 GeoBoxPanel.setVisible(false);
			 PlantTitlePanel.setVisible(false);
			 PlantPanel.setVisible(false);
			 PlantPanelData.setVisible(false);
			 PlantRadioPanel.setVisible(false);
			 PlantBoxPanel.setVisible(false);
			 AlertPanel.setVisible(false);
			 AlertBoxPanel.setVisible(false);
			 HelpTitlePanel.setVisible(false);
			 HelpPanel.setVisible(false);
			 HelpBoxPanel.setVisible(false);	 
			
			 statementPlant = connect.createStatement();
			 resultSetPlant = statementPlant.executeQuery("select * from gardentrackerdb.plants");
	    	 
		    while (resultSetPlant.next()) {
			      int id = resultSetPlant.getInt("id");
			      String name = resultSetPlant.getString("name");
			      String area = resultSetPlant.getString("areaname");
	
			      PlantLogPlantCombo.addItem(name);
			      System.out.println("Id: " + id);
			      System.out.println("Plant Name: " + name);
			      System.out.println("Plant Area: " + area);
	
			      maxPlantLog = maxPlantLog + 1;
			      
			    } 	    
		    
		    
	     } 
	     catch (Exception e) {
	       //throw e;
	     } 
	     finally 
	     {
	    	 try {
	 	        if (resultSetPlant != null) {
	 	        	resultSetPlant.close();
	        }
	
	        if (statementPlant != null) {
	        	statementPlant.close();
	        }
	    	    } catch (Exception e) {

	    	    }
	     }	 			
	     
	     try 
	     {
	      	 statementArea = connect.createStatement();
	    	 resultSetArea = statementArea.executeQuery("select * from gardentrackerdb.areas");
	    	 
		    while (resultSetArea.next()) {
			      int id = resultSetArea.getInt("id");
			      String name = resultSetArea.getString("name");
	
			      PlantLogGeoAreaCombo.addItem(name);
			      
			      System.out.println("Id: " + id);
			      System.out.println("Area Name: " + name);
			      
			      maxArea = maxArea + 1;
	
			    } 	    	 
	     } 
	     catch (Exception e) {
	       //throw e;
	     } 
	     finally 
	     {
	    	 try {
		 	        if (resultSetArea != null) {
		        	resultSetArea.close();
		 	        }
	
			        if (statementArea != null) {
			        	statementArea.close();
			        }
	    	    } catch (Exception e) {

	    	    }
	     }	 			     
 }
 
//Load the data for Area Panel
 public void OnGeoButtonPressed()
 {
  	 
	 MainPanel.setVisible(false);
     PlantLogPanel.setVisible(false);
     PlantLogBoxPanel.setVisible(false);
     GeoTitlePanel.setVisible(true);
     GeoPanel.setVisible(true);
     GeoPanelData.setVisible(true);
     GeoRadioPanel.setVisible(true);
     GeoBoxPanel.setVisible(true);
     PlantTitlePanel.setVisible(false);
     PlantPanel.setVisible(false);
     PlantPanelData.setVisible(false);
     PlantRadioPanel.setVisible(false);
     PlantBoxPanel.setVisible(false);
     AlertPanel.setVisible(false);
     AlertBoxPanel.setVisible(false);
     HelpTitlePanel.setVisible(false);
     HelpPanel.setVisible(false);
     HelpBoxPanel.setVisible(false); 

     LoadPlantArea();
	     
}
 
 public void LoadPlantArea()
 {
	try {
		
	     DefaultTableModel defaultModel = (DefaultTableModel) GeoTable.getModel();
	      
	     defaultModel.setRowCount(0);

	      	 statementArea = connect.createStatement();
	    	 resultSetArea = statementArea.executeQuery("select * from gardentrackerdb.areas");
	    	 
		    while (resultSetArea.next()) {
			      // It is possible to get the columns via name
			      // also possible to get the columns via the column number
			      // which starts at 1
			      // e.g. resultSet.getSTring(2);
			      int id = resultSetArea.getInt("id");
			      String name = resultSetArea.getString("name");
			      
			      defaultModel.addRow(new Object[]{Integer.toString(id),name});	
			      GeoTable.setModel(defaultModel);
			      
			      System.out.println("Id: " + id);
			      System.out.println("Area Name: " + name);
			      
			      maxArea = maxArea + 1;
	
			    } 	    	 
	     } 
	     catch (Exception e) {
	       //throw e;
	     } 
	     finally 
	     {
	    	 try {
	 	        if (resultSetArea != null) {
	        	resultSetArea.close();
	        }
	
	        if (statementArea != null) {
	        	statementArea.close();
	        }
	    } catch (Exception e) {

	   }

	}	 			     
}
//Load the data for Plant Panel
 	public void OnPlantButtonPressed()
 	{
	     MainPanel.setVisible(false);
 	     PlantLogPanel.setVisible(false);
 	     PlantLogBoxPanel.setVisible(false);
 	     GeoTitlePanel.setVisible(false);
 	     GeoPanel.setVisible(false);
 	     GeoPanelData.setVisible(false);
 	     GeoRadioPanel.setVisible(false);
 	     GeoBoxPanel.setVisible(false);
 	     PlantTitlePanel.setVisible(true);
 	     PlantPanel.setVisible(true);
 	     PlantPanelData.setVisible(true); 	     
 	     PlantRadioPanel.setVisible(true);
 	     PlantBoxPanel.setVisible(true);
 	     AlertPanel.setVisible(false);
 	     AlertBoxPanel.setVisible(false);
 	     HelpTitlePanel.setVisible(false);
 	     HelpPanel.setVisible(false);
 	     HelpBoxPanel.setVisible(false);  	
 	     
 	     LoadPlantTable();
 	     
 	}

 	public void LoadPlantTable()
 	{
	     DefaultTableModel defaultModel = (DefaultTableModel) PlantEnterTable.getModel();
	     
	     defaultModel.setRowCount(0);
	     
	     // Load Plant records
	     try 
	     {
	      	 statementPlant = connect.createStatement();
	    	 resultSetPlant = statementPlant.executeQuery("select * from gardentrackerdb.plants");
	    	 
		    while (resultSetPlant.next()) {
			      // It is possible to get the columns via name
			      // also possible to get the columns via the column number
			      // which starts at 1
			      // e.g. resultSet.getSTring(2);
			      int id = resultSetPlant.getInt("id");
			      String name = resultSetPlant.getString("name");
			      String AreaName = resultSetPlant.getString("areaname");
	
			      defaultModel.addRow(new Object[]{Integer.toString(id),name,AreaName});	
			      PlantEnterTable.setModel(defaultModel);
			      
			      System.out.println("Id: " + id);
			      System.out.println("Plant Name: " + name);
			      System.out.println("Plant Area Name: " + AreaName);
			      
			      maxPlant = maxPlant + 1;
	
			    } 	    	 
	     } 
	     catch (Exception e) {
	       //throw e;
	     } 
	     finally 
	     {
	    	 try {
		        if (resultSetPlant != null) {
		        	resultSetPlant.close();
		        }
	
		        if (statementPlant != null) {
		        	statementPlant.close();
		        }
	    	 } catch (Exception e) {

   	    }
	     }	 		
	     
	     
	     try 
	     {
	      	 statementArea = connect.createStatement();
	    	 resultSetArea = statementArea.executeQuery("select * from gardentrackerdb.areas");
	    	 
		    while (resultSetArea.next()) {
			      // It is possible to get the columns via name
			      // also possible to get the columns via the column number
			      // which starts at 1
			      // e.g. resultSet.getSTring(2);
			      int id = resultSetArea.getInt("id");
			      String name = resultSetArea.getString("name");
	
			      PlantAreaCombo.addItem(name);
			      
			      System.out.println("Id: " + id);
			      System.out.println("Area Name: " + name);
			      
			      maxArea = maxArea + 1;
	
			    } 	    	 
	     } 
	     catch (Exception e) {
	       //throw e;
	     } 
	     finally 
	     {
	    	  try 
	    	  {
	 	        if (resultSetArea != null) {
	        	resultSetArea.close();
	 	        }
	
		        if (statementArea != null) {
		        	statementArea.close();
		        }
   	    } catch (Exception e) {

   	    }
     }	 			     
 		
 	}
 // Load the data for Alert Panel
 	public void OnAlertButtonPressed()
 	{
	     try 
	     {

			MainPanel.setVisible(false);
			 PlantLogPanel.setVisible(false);
			 PlantLogBoxPanel.setVisible(false);
			 GeoTitlePanel.setVisible(false);
			 GeoPanel.setVisible(false);
			 GeoPanelData.setVisible(false);
			 GeoRadioPanel.setVisible(false);
			 GeoBoxPanel.setVisible(false);
			 PlantTitlePanel.setVisible(false);
			 PlantPanel.setVisible(false);
			 PlantPanelData.setVisible(false);
			 PlantRadioPanel.setVisible(false);
			 PlantBoxPanel.setVisible(false);
			 AlertPanel.setVisible(true);
			 AlertBoxPanel.setVisible(true);
			 HelpTitlePanel.setVisible(false);
			 HelpPanel.setVisible(false);
			 HelpBoxPanel.setVisible(false); 	
	     
	      	 statementAlert = connect.createStatement();
	    	 resultSetAlert = statementAlert.executeQuery("select * from gardentrackerdb.alerts");
	    	 
		    while (resultSetAlert.next()) {
			      String TimeOfDay = resultSetAlert.getString("timeofday");
			      String WeeklyAlert = resultSetAlert.getString("weeklyalert");
			      String MonthlyAlert = resultSetAlert.getString("monthlyalert");
	
			      System.out.println("TimeOfDay: " + TimeOfDay);
			      System.out.println("Weekly Alert: " + WeeklyAlert);
			      System.out.println("Monthly Alert: " + MonthlyAlert);
			      
			      maxAlert = maxAlert + 1;
	
			    } 	    	 
	     } 
	     catch (Exception e) {
	       //throw e;
	     } 
	     finally 
	     {
	    	 try
	    	 {
		    	 if (resultSetAlert != null) {
		        	resultSetAlert.close();
		    	 }
		
		        if (statementAlert != null) {
		        	statementAlert.close();
		        }
	    	} catch (Exception e) {

    	    }
	     }	 			     	     
 	}
 	

 // Load the data for Help Panel
	 public void OnHelpButtonPressed()
	 {
	     MainPanel.setVisible(false);
	     PlantLogPanel.setVisible(false);
	     PlantLogBoxPanel.setVisible(false);
	     GeoTitlePanel.setVisible(false);
	     GeoPanel.setVisible(false);
	     GeoPanelData.setVisible(false);
	     GeoRadioPanel.setVisible(false);
	     GeoBoxPanel.setVisible(false);
	     PlantTitlePanel.setVisible(false);
	     PlantPanel.setVisible(false);
	     PlantPanelData.setVisible(false);
	     PlantRadioPanel.setVisible(false);
	     PlantBoxPanel.setVisible(false);
	     AlertPanel.setVisible(false);
	     AlertBoxPanel.setVisible(false);
	     HelpTitlePanel.setVisible(true);
	     HelpPanel.setVisible(true);
	     HelpBoxPanel.setVisible(true); 
		 
	 }
	 
	 public void OnPlantHomeButton()
	 {
		MainPanel.setVisible(true);
	     PlantLogPanel.setVisible(false);
	     PlantLogBoxPanel.setVisible(false);
	     GeoTitlePanel.setVisible(false);
	     GeoPanel.setVisible(false);
 	     GeoPanelData.setVisible(false);
	     GeoRadioPanel.setVisible(false);
 	     GeoBoxPanel.setVisible(false);
 	     PlantTitlePanel.setVisible(false);
 	     PlantPanel.setVisible(false);
 	     PlantPanelData.setVisible(false);
 	     PlantRadioPanel.setVisible(false);
 	     PlantBoxPanel.setVisible(false);
 	     AlertPanel.setVisible(false);
 	     AlertBoxPanel.setVisible(false);
 	     HelpTitlePanel.setVisible(false);
 	     HelpPanel.setVisible(false);
 	     HelpBoxPanel.setVisible(false); 
		 
	 }
}

