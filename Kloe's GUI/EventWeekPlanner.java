//different imports
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;



public class EventWeekPlanner extends JFrame {
    //Array for days
    private final String[] days = 
    {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };



    //color of the days, I picked pastle colors.
    //Adding Color for the day boxes
    static final Color[] dayColors = {
        new Color(255, 204, 204),  // Monday 
        new Color(204, 229, 255),  // Tuesday 
        new Color(204, 255, 229),  // Wednesday 
        new Color(255, 255, 204),  // Thursday 
        new Color(255, 229, 204),  // Friday 
        new Color(229, 204, 255),  // Saturday 
        new Color(204, 255, 255)   // Sunday 
    };

    //Stores event/activity data for each day, think of it like a safe.
    //Monday = Key, Activities like Running, sleeping, etc. on Monday (Key) = "Running at PM" (Safe Contents)
    private final Map<String, List<Event>> eventMap = new HashMap<>();

    Map<String, JPanel> dayPanels = new HashMap<>(); //Map for the day panels
    ArrayList<JPanel> dayPanelList = new ArrayList<>();

    //The GUI for Weekly Planner
    public EventWeekPlanner() {
        
        setLayout(new BorderLayout()); //Defines what Layout should be used for the window, in this case Border Layout

        //Title Label inside the Window
        JLabel titleLabel = new JLabel("Weekly Event Planner", SwingConstants.CENTER); //Declares titlelabel and centers the text in the middle of the labelbox
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26)); //Sets Font and Size for titlelabel
        titleLabel.setForeground(Color.BLACK); //Sets the Font Color to Black
        add(titleLabel, BorderLayout.NORTH); //Adds the Title label to the North Border/Region


        //PaddedPanel formats for placement for the days & buttons aka the Frame for the Center Panel
        JPanel paddedGrid = new JPanel(new BorderLayout());
        paddedGrid.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); //Specifies the amount of pixels away from the window for top, left, bottom, right for each box/panel
        //paddedGrid.setOpaque(false);

        //Makes a GridLayout in the Padded Panel: 2 Rows, 4 Columns of boxes/panels & make every panel 10 pixels apart horizontally & vertically for the days and Add/Delete Buttons
        JPanel gridPanel = new JPanel(new GridLayout(2, 4, 10, 10)); //Declares & adds a Gridlayout inside a JPanel

        paddedGrid.add(gridPanel, BorderLayout.CENTER); //Adding gridPanel inside paddedGrid Panel to the center of its layout
        add(paddedGrid, BorderLayout.CENTER); //Adding paddedGrid to the main window


        //Gives each day (Key) an arrayList (safe Contents)
        for (String day : days) 
        {
            eventMap.put(day, new ArrayList<>()); //Adds the keys/days which unlocks an ArrayList aka Safe Contents
        }

        //Adds Day buttons and their functionality, adding them to gridPanel aka the Artwork inside a frame
        for (int i = 0; i < days.length; i++) { 
            String day = days[i];

            JButton dayButton = new JButton(day); //Makes each day a button

            //Day Button Appearances//
            dayButton.setBackground(dayColors[i]); //Allows the Buttons to blend with the background color of their boxes/panel
            dayButton.setBorderPainted(false); //Removes Black Border around buttons
            dayButton.setFont(new Font("Times New Roman", Font.BOLD, 34));//Sets the Font for the buttons/day button
            dayButton.setMaximumSize(new Dimension(210, 40)); //Forces this size to be used


            //Adds Button Functionality for each day
            dayButton.addActionListener(e -> PlannerUtilities.handleDayClick((Component) this, day,eventMap));


            JTextArea eventTextArea = new JTextArea(3,20);
            eventTextArea.setEditable(false); //Makes the text area not editable
            eventTextArea.setLineWrap(true); //Allows the text to wrap around the text area
            eventTextArea.setWrapStyleWord(true); //Allows the text to wrap around the text area
            eventTextArea.setBackground(dayColors[i]); //Makes the text area blend in with the dayPanel
            eventTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 16)); //Sets the font for the text area

            //Adds the Day Buttons to the Top of their respective boxes
            JPanel dayPanel = new JPanel(); //Made a Panel for each day to go inside the GridPanel to prevent autosizing from the gridPanel.
            dayPanel.setLayout(new BoxLayout(dayPanel, BoxLayout.Y_AXIS)); //Vertical allignment for the Day Buttons
            dayPanel.setBackground(dayColors[i]); //Make the dayPanels blend in the gridPanel
            //dayPanel.add(eventTextArea); //Adds the text area to the dayPanel
            
            eventTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
            dayButton.setAlignmentX(Component.CENTER_ALIGNMENT);


            
            //Adds the Button & day panels together
            dayPanel.add(dayButton);
            dayPanel.add(eventTextArea);

            dayPanelList.add(dayPanel);

            gridPanel.add(dayPanelList.get(i));

            //dayPanelList.put(day, dayPanel);
            
        }

        JPanel mondayPanel = dayPanels.get("Monday"); // Get the panel for Monday
        JPanel tuesdayPanel = dayPanels.get("Tuesday"); // Get the panel for Tuesday
        JPanel wednesdayPanel = dayPanels.get("Wednesday"); // Get the panel for Wednesday
        JPanel thursdayPanel = dayPanels.get("Thursday"); // Get the panel for Thursday
        JPanel fridayPanel = dayPanels.get("Friday"); // Get the panel for Friday
        JPanel saturdayPanel = dayPanels.get("Saturday"); // Get the panel for Saturday
        JPanel sundayPanel = dayPanels.get("Sunday"); // Get the panel for Sunday

        JTextArea eventTextArea = new JTextArea(3,20);
        eventTextArea.setEditable(false); //Makes the text area not editable
        eventTextArea.setLineWrap(true); //Allows the text to wrap around the text area
        eventTextArea.setWrapStyleWord(true); //Allows the text to wrap around the text area
        eventTextArea.setFont(new Font("Times New Roman", Font.PLAIN, 16)); //Sets the font for the text area

        //Make another panel inside that gridPanel
        JPanel controlPanel = new JPanel(new GridLayout(4, 1, 5, 5)); //Make the panel with 4 rows & 1 Column for the buttons

        //Declare all the Add/Delete/Replace Buttons
        JButton addButton = new JButton("Add Event");
        JButton deleteButton = new JButton("Delete Event");
        JButton replaceButton = new JButton("Replace Event");
        JButton saveButton = new JButton("Save");

        //Adds all the add/delete/replace buttons into the Control Panel
        controlPanel.add(addButton);
        controlPanel.add(deleteButton);
        controlPanel.add(replaceButton);
        controlPanel.add(saveButton);

        //Adds the controlPanel into the gridPanel aka into the Artwork
        gridPanel.add(controlPanel);


        //Adds Functionality for all these buttons
        addButton.addActionListener(e -> {
            PlannerUtilities.addEventDialog((Component) this, days, eventMap, gridPanel, controlPanel, dayPanelList);
            //String currentText = eventTextArea.getText(); // Get the current text in the text area
            //List newText = eventMap.get(days); // Get the new text from the event map
            //eventTextArea.setText(currentText + "\n" + newText); // Set the new text in the text area
        
        });
        
        deleteButton.addActionListener(e -> PlannerUtilities.deleteEventDialog((Component) this, days, eventMap, gridPanel, controlPanel, dayPanelList));
        replaceButton.addActionListener(e -> PlannerUtilities.replaceEventDialog((Component) this, days, eventMap, gridPanel, controlPanel, dayPanelList));
        saveButton.addActionListener(e -> PlannerUtilities.saveEventDialog((Component) this, days, eventMap));

        /*
        new javax.swing.Timer(5000, e -> {
            // Code after 5 seconds
            gridPanel.removeAll();
            gridPanel.revalidate();
            gridPanel.repaint();

            for (int i = 0; i< dayPanelList.size(); i++)
            {
                for (Component comp : (dayPanelList.get(i)).getComponents())
                {
                    if (comp instanceof JTextArea)
                    {
                        ((JTextArea) comp).setText("Long Johnson");
                       
                    }
                }
                gridPanel.add(dayPanelList.get(i));
            }
        }).start();
        */
        //Window Set-up
        setTitle("Weekly Event Planner"); //Sets the Title for the Window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Adds Functionality for "X" button on the window
        setSize(975, 600); // (before)Sets the size to 900 x 600 Pixels, (after) I increased the width by 75 pixels because Wednesday got cut offed. 
        setVisible(true); //Sets the entire Frame to true
    }

    //This where stuff runs
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventWeekPlanner::new);
    }

}
