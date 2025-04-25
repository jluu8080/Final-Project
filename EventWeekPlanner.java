//different imports
import javax.swing.*;
import java.awt.*;

//import java.awt.event.*;
import java.util.*;
import java.util.List;


//list of all the days in the week 
public class EventWeekPlanner extends JFrame {
    
    ///Member Variables///

    //Array for days
    private final String[] days = 
    {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };


    //color of the days, I picked pastle colors.
    //Adding Color for the day boxes
    private final Color[] dayColors = {
        new Color(255, 204, 204),  // Monday 
        new Color(204, 229, 255),  // Tuesday 
        new Color(204, 255, 229),  // Wednesday 
        new Color(255, 255, 204),  // Thursday 
        new Color(255, 229, 204),  // Friday 
        new Color(229, 204, 255),  // Saturday 
        new Color(204, 255, 255)   // Sunday 
    };

    // Event class
    static class Event {
        private final String name;
        private final String time;
        private final String amPm;

        public Event(String name, String time, String amPm) {
            this.name = name;
            this.time = time;
            this.amPm = amPm;
        }

        //comvert military time, make sure to put events in chronological order.
        public String get24HourTime() {
            try {
                String[] parts = time.split(":");
                int hour = Integer.parseInt(parts[0]);
                int min = Integer.parseInt(parts[1]);
                if (amPm.equals("PM") && hour != 12) hour += 12;
                if (amPm.equals("AM") && hour == 12) hour = 0;
                return String.format("%02d:%02d", hour, min);
            }
            catch (Exception e)
            {
                return "99:99"; // fallback
            }
        }

        @Override
        public String toString() {
            return name + " at " + time + " " + amPm;
        }
    }

    //Stores event/activity data for each day, think of it like a safe.
    //Monday = Key, Activities like Running, sleeping, etc. on Monday (Key) = "Running at PM" (Safe Contents)
    private final Map<String, List<Event>> eventMap = new HashMap<>();

    
    //Adds Button Functionality for all the Days/ Day Buttons
    private void handleDayClick(String day) {
        List<Event> events = eventMap.get(day); //Pulls the Arraylist (Safe Contents) for the day (Key) and now events points to the day's Arraylist *Doesn't make a new ArrayList*
        
        //If events/ day's Arraylist is empty
        if (events.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No events for " + day); //Pops up a new window inside your current/main window with a message via the "this" keyword

        } 

        //If the events/ day's Arraylist isn't empty
        else {
            StringBuilder msg = new StringBuilder("Events for " + day + ":\n"); //Makes on long string to be built off of, it's super clean and efficient.

            //Loops through each event and appends it to msg for printing later
            for (Event e : events) {
                msg.append("- ").append(e).append("\n");
            }
            JOptionPane.showMessageDialog(this, msg.toString()); //Prints the msg in its entirety
        }
    }

    //The GUI for Weekly Planner
    public EventWeekPlanner() {

        //title of the planner, set position and font size as needed
        setTitle("Weekly Event Planner"); //Sets the Title for the Window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Adds Functionality for "X" button on the window
        setSize(975, 600); // (before)Sets the size to 900 x 600 Pixels, (after) I increased the width by 75 pixels because Wednesday got cut offed. Didn't happen in the original because of the HTML stuff
        setLayout(new BorderLayout()); //Defines what Layout should be used for the window, in this case Border Layout


        //Title Label inside the Window
        JLabel titleLabel = new JLabel("Weekly Event Planner", SwingConstants.CENTER); //Declares titlelabel and centers the text in the middle of the labelbox
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26)); //Sets Font and Size for titlelabel
        titleLabel.setForeground(Color.BLACK); //Sets the Font Color to Black
        //titleLabel.setOpaque(false); //Literally does nothing
        add(titleLabel, BorderLayout.NORTH); //Adds the Title label to the North Border/Region


        // Grid Panel with padding
        //Adds Panels/Boxes for the Days & Buttons

        /*
        Analogy for Jason's Understanding - Jason
        If you imagine paddedGrid like a frame on a painting:
        BorderLayout.CENTER is the canvas
        The EmptyBorder(30, 50, 30, 50) is the size of the frame around it
        And gridPanel is the artwork inside that frame
        */

        //PaddedPanel formats for placement for the days & buttons aka the Frame for the Center Panel
        JPanel paddedGrid = new JPanel(new BorderLayout());
        paddedGrid.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); //Specifies the amount of pixels away from the window for top, left, bottom, right for each box/panel
        //paddedGrid.setOpaque(false);

        //Makes a GridLayout in the Padded Panel: 2 Rows, 4 Columns of boxes/panels & make every panel 10 pixels apart horizontally & vertically for the days and Add/Delete Buttons
        JPanel gridPanel = new JPanel(new GridLayout(2, 4, 10, 10)); //Declares & adds a Gridlayout inside a JPanel

        //gridPanel.setOpaque(false); //Literally does nothing because the background color of the window hasn't been changed
        //gridPanel.setPreferredSize(new Dimension(700, 300)); //Does nothing due to it being a GridLayout and Not Flowlayout
        
        paddedGrid.add(gridPanel, BorderLayout.CENTER); //Adding gridPanel inside paddedGrid Panel to the center of its layout
        add(paddedGrid, BorderLayout.CENTER); //Adding paddedGrid to the main window


        // Initialize event map

        //Gives each day (Key) an arrayList (safe Contents)
        for (String day : days) 
        {
            eventMap.put(day, new ArrayList<>()); //Adds the keys/days which unlocks an ArrayList aka Safe Contents
        }

        // Create day buttons, create them in the center, all 7 should have the same size.

        //Adds Day buttons and their functionality, adding them to gridPanel aka the Artwork inside a frame
        for (int i = 0; i < days.length; i++) { 
            String day = days[i];

            //needs to be fixed, the days should appear on the top, not center.
            
            //JButton dayButton = new JButton("<html><top><b>" + day + "</b><br/></top></html>"); //Makes each day a button also has HTML elements that don't do anything and it doesn't bring the days to the top but the font stuff wasn't applied without this
            JButton dayButton = new JButton(day); //Makes each day a button
            
            //Day Button Appearances//
            dayButton.setBackground(dayColors[i]); //Each day has its own special color
            //dayButton.setOpaque(true); //Does nothing
            dayButton.setBorderPainted(false); //Removes Black Border around buttons
            
            //dayButton.setFont(new Font("Times New Roman", Font.PLAIN, 34)); Not sure why this line didn't override the HTML stuff since it sets it bold up there and plain/unbolded here?
            
            dayButton.setFont(new Font("Times New Roman", Font.BOLD, 34));//Sets the Font for the buttons/day button
            dayButton.addActionListener(e -> handleDayClick(day)); //Adds Button Functionality for each day

            //Adds the button to gridPanel
            gridPanel.add(dayButton);
        }



        // Final block/Panel for Add/Delete/Replace functionality


        //Make another panel inside that panel
        JPanel controlPanel = new JPanel(new GridLayout(3, 1, 5, 5)); //Make the panel with 3 rows & 1 Column for the buttons

        //controlPanel.setOpaque(false); //Does nothing

        //Declare all the Add/Delete/Replace Buttons
        JButton addButton = new JButton("Add Event");
        JButton deleteButton = new JButton("Delete Event");
        JButton replaceButton = new JButton("Replace Event");


        //Adds Functionality for all these buttons
        addButton.addActionListener(e -> addEventDialog());
        deleteButton.addActionListener(e -> deleteEventDialog());
        replaceButton.addActionListener(e -> replaceEventDialog());


        //Adds all the add/delete/replace buttons into the Control Panel
        controlPanel.add(addButton);
        controlPanel.add(deleteButton);
        controlPanel.add(replaceButton);

        //Adds the controlPanel into the gridPanel aka into the Artwork
        gridPanel.add(controlPanel);

        setVisible(true); //Sets the entire Frame to true
    }

    /*
    //Makes no sense why this is down here
    //Adds Button Functionality for all the Days/ Day Buttons
    private void handleDayClick(String day) {

        List<Event> events = eventMap.get(day);

        
        if (events.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No events for " + day);
        } else {
            StringBuilder msg = new StringBuilder("Events for " + day + ":\n");
            for (Event e : events) {
                msg.append("- ").append(e).append("\n");
            }
            JOptionPane.showMessageDialog(this, msg.toString());
        }
    }
    */

    // Add button

    //Add Button Functionality
    private void addEventDialog() {
        //Makes a Panel with 3 rows and 2 columns, 5 pixels apart horizontally and vertically
        /*
         Affects:
         "Event Name: " Label & its TextBox
         "Event Time (HH:MM):" Label & its TextBox
          "AM/PM:" Label & its ComboBox
        */

        
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        
        //Adds a Day ComboBox
        JComboBox<String> dayBox = new JComboBox<>(days);

        //"Event Name: " Label & its TextBox
        JLabel nameLabel = new JLabel("Event Name:");
        JTextField nameField = new JTextField();

        //"Event Time (HH:MM):" Label & its TextBox
        JLabel timeLabel = new JLabel("Event Time (HH:MM):");
        JTextField timeField = new JTextField();

        //"AM/PM:" Label & its ComboBox
        JLabel amPMLabel = new JLabel("AM/PM:");
        JComboBox<String> amPmBox = new JComboBox<>(new String[]{"AM", "PM"});



        //panel.add(new JLabel("Event Name:")); //Just made labels for these, for readability
        panel.add(nameLabel);
        panel.add(nameField);
        
        //panel.add(new JLabel("Event Time (HH:MM):"));
        panel.add(timeLabel);
        panel.add(timeField);
        
        //panel.add(new JLabel("AM/PM:"));
        panel.add(amPMLabel);
        panel.add(amPmBox);


        //Makes the Pop-up Window for the Add Button, it returns integer values for the following
        /*
            JOptionPane.OK_OPTION → user clicked OK (int value: 0)

            JOptionPane.CANCEL_OPTION → user clicked Cancel (int value: 2)

            JOptionPane.CLOSED_OPTION → user closed the dialog (int value: -1)
        */
        int result = JOptionPane.showConfirmDialog(this, new Object[]{dayBox, panel},
        "Add New Event", JOptionPane.OK_CANCEL_OPTION);
        
        //exception handling for the pop-up Window
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim(); //trims trailing/leading spaces
            String time = timeField.getText().trim(); //trims trailin/leading spaces
            String amPm = (String) amPmBox.getSelectedItem(); //Grabs text from the dropdown box for AM & PM
            String day = (String) dayBox.getSelectedItem(); //Grabs the text from the dropdown box for days

            //If the name textbox isn't empty & the time textbox matches the given format: (HH:MM)
            if (!name.isEmpty() && time.matches("\\d{1,2}:\\d{2}")) {
                
                //Fills the arrayList for the days with the name, time, & amPm values
                Event event = new Event(name, time, amPm); 
                eventMap.get(day).add(event);
                
                sortEvents(day); //Sorts the days in order

                JOptionPane.showMessageDialog(this, "Event added to " + day + ".");
            } 
            //add another segment of code that excludes hour exceding 12, minute exceeding 60.
            else {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and time (e.g. 3:30).");
            }
        }
    }

    // Delete event dialog

    //Delete Button Functionality
    private void deleteEventDialog() {
        //JComboBox<String> dayBox = new JComboBox<>(days); //Not used at all in this method

        //Makes an intial Pop-up Window
        String selectedDay = (String) JOptionPane.showInputDialog(
                this, "Select Day:", "Delete Event",
                JOptionPane.QUESTION_MESSAGE, null, days, days[0]);

        //If the user didn't close the window or hit "Cancel".
        if (selectedDay != null) {
            List<Event> dayEvents = eventMap.get(selectedDay); //Grabs the List of events for the day the user selected in the dropdown box
            
            //If there are no events for that day
            if (dayEvents.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No events to delete for " + selectedDay);
                return;
            }

            //Converts the dayEvents objects/activities to an array of strings
            String[] eventStrings = dayEvents.stream().map(Event::toString).toArray(String[]::new);
            
            //Makes another Pop-up window to delete Events for the selected day
            String selectedEvent = (String) JOptionPane.showInputDialog(
                    this, "Select event to delete:",
                    "Delete Event", JOptionPane.PLAIN_MESSAGE,
                    null, eventStrings, eventStrings[0]);

            //After the user deletes the event
            if (selectedEvent != null) {
                dayEvents.removeIf(e -> e.toString().equals(selectedEvent));
                JOptionPane.showMessageDialog(this, "Event deleted.");
            }
        }
    }

    // Replace event dialog

    //Replace Button Functionality
    private void replaceEventDialog() {

        //Makes a pop-up window
        String selectedDay = (String) JOptionPane.showInputDialog(
                this, "Select Day:", "Replace Event",
                JOptionPane.QUESTION_MESSAGE, null, days, days[0]);

        //If user picks a day and doesn't close the window
        if (selectedDay != null) {
            List<Event> dayEvents = eventMap.get(selectedDay);
            if (dayEvents.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No events to replace for " + selectedDay);
                return;
            }

            String[] eventStrings = dayEvents.stream().map(Event::toString).toArray(String[]::new);
            String selectedEvent = (String) JOptionPane.showInputDialog(
                    this, "Select event to replace:",
                    "Replace Event", JOptionPane.PLAIN_MESSAGE,
                    null, eventStrings, eventStrings[0]);

            if (selectedEvent != null) {
                Event original = null;
                for (Event e : dayEvents) {
                    if (e.toString().equals(selectedEvent)) {
                        original = e;
                        break;
                    }
                }
                //exception handling, make sure new event is not the same as the original one.
                if (original != null) {
                    String newName = JOptionPane.showInputDialog(
                            this, "Enter new event name:", original.name);
                    if (newName != null && !newName.trim().isEmpty()) {
                        // Replace with new event name, same time/AMPM
                        Event updated = new Event(newName.trim(), original.time, original.amPm);
                        dayEvents.remove(original);
                        dayEvents.add(updated);
                        sortEvents(selectedDay);
                        JOptionPane.showMessageDialog(this, "Event updated.");
                    } else {
                        JOptionPane.showMessageDialog(this, "Event name cannot be empty.");
                    }
                }
            }
        }
    }

    // Sort events for a day by time
    private void sortEvents(String day) {
        eventMap.get(day).sort(Comparator.comparing(Event::get24HourTime));
    }

    /*

    // Event class *Makes no sense why it's down here*
    static class Event {
        private final String name;
        private final String time;
        private final String amPm;

        public Event(String name, String time, String amPm) {
            this.name = name;
            this.time = time;
            this.amPm = amPm;
        }
        //comvert military time, make sure to put events in chronological order.
        public String get24HourTime() {
            try {
                String[] parts = time.split(":");
                int hour = Integer.parseInt(parts[0]);
                int min = Integer.parseInt(parts[1]);
                if (amPm.equals("PM") && hour != 12) hour += 12;
                if (amPm.equals("AM") && hour == 12) hour = 0;
                return String.format("%02d:%02d", hour, min);
            }
            catch (Exception e)
            {
                return "99:99"; // fallback
            }
        }

        @Override
        public String toString() {
            return name + " at " + time + " " + amPm;
        }
    }
    */

    //This where stuff runs
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventWeekPlanner::new);
    }
}
