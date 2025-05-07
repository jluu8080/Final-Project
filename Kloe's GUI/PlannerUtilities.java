import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import javax.swing.*;


public class PlannerUtilities
{

    //Stores event/activity data for each day, think of it like a safe.
    //Monday = Key, Activities like Running, sleeping, etc. on Monday (Key) = "Running at PM" (Safe Contents)
    //private final Map<String, List<Event>> eventMap = new HashMap<>();

    // Sort events for a day by time
    static void sortEvents(String day, Map<String, List<Event>> eventMap) 
    {
        eventMap.get(day).sort(Comparator.comparing(Event::get24HourTime)); //Calls get24HourTime when needed instead of immediately using :: - Method Reference
    }

    //Validates time input
    static boolean validTimeInput(Component comp, String time) {
        try {
            String[] parts = time.split(":");
            int hour = Integer.parseInt(parts[0]);
            int min = Integer.parseInt(parts[1]);

            if (hour <= 0 || hour > 12 || min < 0 || min > 59) {
                throw new Exception("Hour and or Minute enter has exceed acceptable values: Hour < 12 & min < 59");
            }
            return true;
        }
        catch (NumberFormatException e)
        {
            JOptionPane.showMessageDialog(comp, "Invalid Input for Time");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(comp, "Invalid Input for Time");
        }
        return false;
    }


    //Day Button Functionality
    static void handleDayClick(Component comp, String day, Map<String, List<Event>> eventMap) {
        List<Event> events = eventMap.get(day); //Pulls the Arraylist (Safe Contents) for the day (Key) and now events points to the day's Arraylist *Doesn't make a new ArrayList*
        
        //If events/ day's Arraylist is empty
        if (events.isEmpty()) {
            JOptionPane.showMessageDialog(comp, "No events for " + day); //Pops up a new window inside your current/main window with a message via the "this" keyword

        } 

        //If the events/ day's Arraylist isn't empty
        else {
            StringBuilder msg = new StringBuilder("Events for " + day + ":\n"); //Makes on long string to be built off of, it's super clean and efficient.

            //Loops through each event and appends it to msg for printing later
            for (Event e : events) {
                msg.append("- ").append(e).append("\n");
            }
            JOptionPane.showMessageDialog(comp, msg.toString()); //Prints the msg in its entirety
        }
    }


    //Add Button Functionality
    static void addEventDialog(Component comp, String[] days, Map<String, List<Event>> eventMap, JPanel gridPanel, JPanel controlPanel, ArrayList<JPanel> dayPanelList)
    {
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

        //Adds name Label & Its Textbox
        panel.add(nameLabel);
        panel.add(nameField);
    
        //Adds time label & its TextBox
        panel.add(timeLabel);
        panel.add(timeField);
        
        //Adds amPm Label & its ComboBox
        panel.add(amPMLabel);
        panel.add(amPmBox);

        int result = JOptionPane.showConfirmDialog(comp, new Object[]
        {dayBox, panel},"Add New Event", JOptionPane.OK_CANCEL_OPTION);
        
        //exception handling for the pop-up Window
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim(); //trims trailing/leading spaces
            String time = timeField.getText().trim(); //trims trailin/leading spaces
            String amPm = (String) amPmBox.getSelectedItem(); //Grabs text from the dropdown box for AM & PM
            String day = (String) dayBox.getSelectedItem(); //Grabs the text from the dropdown box for days
            int selectedDayIndex = dayBox.getSelectedIndex(); //Grabs the day the user selected

            //If the name textbox isn't empty & the time textbox matches the given format: (HH:MM)
            if (!name.isEmpty() && time.matches("\\d{1,2}:\\d{2}")) {
                
                if (validTimeInput(comp, time) == true)
                {
                    //Fills the arrayList for the days with the name, time, & amPm values
                    Event event = new Event(name, time, amPm); 

                    //Shouldn't I return this? No! Passes by Reference gets updated in real-time
                    eventMap.get(day).add(event);
                    
                    //Sorts the days in order
                    sortEvents(day, eventMap);

                    JOptionPane.showMessageDialog(comp, "Event added to " + day + ".");

                    //Refreshes gridPanel with updated events
                    if (gridPanel instanceof JPanel)
                    {
                        gridPanel.removeAll(); //Removes all the components from the gridPanel
                        gridPanel.revalidate(); //Revalidates the gridPanel to update the layout
                        gridPanel.repaint(); //Repaints the gridPanel to show the updated layout

                        for (Component dayPanel : (dayPanelList.get(selectedDayIndex)).getComponents())
                        {
                            if (dayPanel instanceof JTextArea)
                            {
                                ((JTextArea) dayPanel).setText(" "); //Clears the text area for the day selected
                                for (Event event1 : eventMap.get(day))
                                {
                                    ((JTextArea) dayPanel).append("\n" + "- " + event1.toString()); //Adds the event to the text area for the day selected
                                    
                                }
                                /* 
                                JTextArea textArea = (JTextArea) dayPanel; //Casts the component to a JTextArea
                                String currentText = textArea.getText(); //Gets the current text in the text area
                                textArea.setText(currentText + " \n" +  "- " + event.toString()); //Sets the new text in the text area with the new event
                                break;
                                */
                            }
                        }
                        
                        for (int i = 0; i< dayPanelList.size(); i++)
                        {
                            gridPanel.add(dayPanelList.get(i)); //Adds the dayPanel to the gridPanel
                        }
                        gridPanel.add(controlPanel); //Adds the controlPanel to the gridPanel
                    }
                    
                }

            } 
            //add another segment of code that excludes hour exceding 12, minute exceeding 60.
            else {
                JOptionPane.showMessageDialog(comp, "Please enter a valid name and time (e.g. 3:30)."); //If the name textbox is empty or the time textbox doesn't match the given format, it will pop up a message saying so.
            }
        }
    }
    

    //Delete Button Functionality
    static void deleteEventDialog(Component comp,String[] days, Map<String, List<Event>> eventMap, JPanel gridPanel, JPanel controlPanel, ArrayList<JPanel> dayPanelList) {
        //JComboBox<String> dayBox = new JComboBox<>(days); //Not used at all in this method

        //Makes an intial Pop-up Window
        String selectedDay = (String) JOptionPane.showInputDialog(
            comp, "Select Day:", "Delete Event",JOptionPane.QUESTION_MESSAGE, null, days, days[0]
        );

        //If the user didn't close the window or hit "Cancel".
        if (selectedDay != null) {
            List<Event> dayEvents = eventMap.get(selectedDay); //Grabs the List of events for the day the user selected in the dropdown box
            
            //If there are no events for that day
            if (dayEvents.isEmpty()) {
                JOptionPane.showMessageDialog(comp, "No events to delete for " + selectedDay); //If the day is empty, it will pop up a message saying so.
                return;
            }

            //Converts the dayEvents objects/activities to an array of strings
            String[] eventStrings = dayEvents.stream().map(Event::toString).toArray(String[]::new);
            
            //Makes another Pop-up window to delete Events for the selected day
            String selectedEvent = (String) JOptionPane.showInputDialog(
                    comp, "Select event to delete:",
                    "Delete Event", JOptionPane.PLAIN_MESSAGE,
                    null, eventStrings, eventStrings[0]);

            //After the user deletes the event
            if (selectedEvent != null) {
                dayEvents.removeIf(e -> e.toString().equals(selectedEvent));
                JOptionPane.showMessageDialog(comp, "Event deleted.");
            }
            if (gridPanel instanceof JPanel) {
                gridPanel.removeAll();
                gridPanel.revalidate();
                gridPanel.repaint();
            
                for (int i = 0; i < dayPanelList.size(); i++) {
                    JPanel dayPanel = dayPanelList.get(i);
                    String day = days[i];
                    for (Component comp1 : dayPanel.getComponents()) {
                        if (comp1 instanceof JTextArea) {
                            JTextArea textArea = (JTextArea) comp1;
                            textArea.setText(" "); // Clear first
                            for (Event e : eventMap.get(day)) {
                                textArea.append("\n- " + e.toString());
                            }
                        }
                    }
                    gridPanel.add(dayPanel);
                }
            
                gridPanel.add(controlPanel);
            }
        /*
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

        //Adds name Label & Its Textbox
        panel.add(nameLabel);
        panel.add(nameField);
    
        //Adds time label & its TextBox
        panel.add(timeLabel);
        panel.add(timeField);
        
        //Adds amPm Label & its ComboBox
        panel.add(amPMLabel);
        panel.add(amPmBox);

        int result = JOptionPane.showConfirmDialog(comp, new Object[]
        {dayBox, panel},"Add New Event", JOptionPane.OK_CANCEL_OPTION);
        
        
        //exception handling for the pop-up Window
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim(); //trims trailing/leading spaces
            String time = timeField.getText().trim(); //trims trailin/leading spaces
            String amPm = (String) amPmBox.getSelectedItem(); //Grabs text from the dropdown box for AM & PM
            String day = (String) dayBox.getSelectedItem(); //Grabs the text from the dropdown box for days
            int selectedDayIndex = dayBox.getSelectedIndex(); //Grabs the day the user selected

            //If the name textbox isn't empty & the time textbox matches the given format: (HH:MM)
            if (!name.isEmpty() && time.matches("\\d{1,2}:\\d{2}")) {
                
                if (validTimeInput(comp, time) == true)
                {
                    //Fills the arrayList for the days with the name, time, & amPm values
                    Event event = new Event(name, time, amPm); 

                    //Shouldn't I return this? No! Passes by Reference gets updated in real-time
                    eventMap.get(day).add(event);
                    
                    //Sorts the days in order
                    sortEvents(day, eventMap);

                    JOptionPane.showMessageDialog(comp, "Event added to " + day + ".");

            for (Component dayPanel : (dayPanelList.get(selectedDayIndex)).getComponents())
                        {
                            if (dayPanel instanceof JTextArea)
                            {
                                ((JTextArea) dayPanel).setText(" "); //Clears the text area for the day selected
                                for (Event event1 : eventMap.get(day))
                                {
                                    ((JTextArea) dayPanel).append("\n" + "- " + event1.toString()); //Adds the event to the text area for the day selected
                                    
                                }
                                /* 
                                JTextArea textArea = (JTextArea) dayPanel; //Casts the component to a JTextArea
                                String currentText = textArea.getText(); //Gets the current text in the text area
                                textArea.setText(currentText + " \n" +  "- " + event.toString()); //Sets the new text in the text area with the new event
                                break;
                                */
                            //}
                        //}
                        /* 
                        for (int i = 0; i< dayPanelList.size(); i++)
                        {
                            gridPanel.add(dayPanelList.get(i)); //Adds the dayPanel to the gridPanel
                        }
                        gridPanel.add(controlPanel); //Adds the controlPanel to the gridPanel

                    }
                        */
                }
            }
        //}
            //}
        //}
    //}
//}
         
        


    //Replace Button Functionality
    static void replaceEventDialog(Component comp, String[] days, Map<String, List<Event>> eventMap, JPanel gridPanel, JPanel controlPanel, ArrayList<JPanel> dayPanelList) {
        // Makes a pop-up window
        String selectedDay = (String) JOptionPane.showInputDialog(
                comp, "Select Day:", "Replace Event",
                JOptionPane.QUESTION_MESSAGE, null, days, days[0]);
    
        // If user picks a day and doesn't close the window
        if (selectedDay != null) {
            List<Event> dayEvents = eventMap.get(selectedDay); // Grabs the List of events for the selected day
            if (dayEvents == null || dayEvents.isEmpty()) {
                JOptionPane.showMessageDialog(comp, "No events to replace for " + selectedDay);
                return;
            }
    
            String[] eventStrings = dayEvents.stream().map(Event::toString).toArray(String[]::new);
            String selectedEvent = (String) JOptionPane.showInputDialog(
                    comp, "Select event to replace:",
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
    
                if (original != null) {
                    // Create a panel to combine all inputs (name, time, AM/PM, and day)
                    JPanel inputPanel = new JPanel(new GridLayout(4, 2));
    
                    // Input for new event name
                    inputPanel.add(new JLabel("Enter new event name:"));
                    JTextField nameField = new JTextField(original.getName());
                    inputPanel.add(nameField);
    
                    // Input for new event time
                    inputPanel.add(new JLabel("Enter new event time (HH:MM):"));
                    JTextField timeField = new JTextField(original.getTime());
                    inputPanel.add(timeField);
    
                    // Dropdown for AM/PM selection
                    inputPanel.add(new JLabel("Select AM or PM:"));
                    JComboBox<String> amPmBox = new JComboBox<>(new String[]{"AM", "PM"});
                    amPmBox.setSelectedItem(original.getAM_PM());
                    inputPanel.add(amPmBox);
    
                    // Dropdown for selecting a new day
                    inputPanel.add(new JLabel("Select new day:"));
                    JComboBox<String> dayBox = new JComboBox<>(days);
                    dayBox.setSelectedItem(selectedDay);
                    inputPanel.add(dayBox);
    
                    // Show the combined dialog
                    int result = JOptionPane.showConfirmDialog(
                            comp, inputPanel, "Update Event Details", JOptionPane.OK_CANCEL_OPTION);
    
                    if (result == JOptionPane.OK_OPTION) {
                        // Get user inputs
                        String newName = nameField.getText().trim();
                        String newTime = timeField.getText().trim();
                        String selectedAmPm = (String) amPmBox.getSelectedItem();
                        String newDay = (String) dayBox.getSelectedItem();
    
                        // Validate inputs
                        if (newName.isEmpty()) {
                            JOptionPane.showMessageDialog(comp, "Event name cannot be empty.");
                            return;
                        }
                        if (newTime.isEmpty() || !validTimeInput(comp, newTime)) {
                            JOptionPane.showMessageDialog(comp, "Invalid time format. Event not updated.");
                            return;
                        }
    
                        // Create the updated event
                        Event updated = new Event(newName, newTime, selectedAmPm);
    
                        // Remove the original event and add the updated event to the new day
                        if (!newDay.equals(selectedDay)) {
                            dayEvents.remove(original);
                            if (eventMap.get(newDay) == null) {
                                eventMap.put(newDay, new ArrayList<>());
                            }
                            eventMap.get(newDay).add(updated);
                            sortEvents(newDay, eventMap);
                        } else {
                            dayEvents.remove(original);
                            dayEvents.add(updated);
                            sortEvents(selectedDay, eventMap);
                        }
    
                        JOptionPane.showMessageDialog(comp, "Event updated.");
    
                        // Refresh the gridPanel with updated events
                        if (gridPanel instanceof JPanel) {
                            gridPanel.removeAll(); // Removes all components from the gridPanel
                            gridPanel.revalidate(); // Revalidates the gridPanel to update the layout
                            gridPanel.repaint(); // Repaints the gridPanel to show the updated layout
    
                            for (int i = 0; i < dayPanelList.size(); i++) {
                                JPanel dayPanel = dayPanelList.get(i);
                                for (Component component : dayPanel.getComponents()) {
                                    if (component instanceof JTextArea) {
                                        JTextArea textArea = (JTextArea) component;
                                        textArea.setText(""); // Clears the text area
                                        String currentDay = days[i];
                                        for (Event event : eventMap.get(currentDay)) {
                                            textArea.append("\n- " + event.toString()); // Updates the text area with events
                                        }
                                    }
                                }
                                gridPanel.add(dayPanel); // Adds the dayPanel back to the gridPanel
                            }
                            gridPanel.add(controlPanel); // Adds the controlPanel back to the gridPanel
                        }
                    } else {
                        JOptionPane.showMessageDialog(comp, "Event update canceled.");
                    }
                }
            }
        }
    }
    //Save Button Functionality
    static void saveEventDialog(Component comp, String[] days, Map<String, List<Event>> eventMap)
    {
        JFileChooser fileChooser = new JFileChooser();//Creates a new file chooser window
        int result = fileChooser.showSaveDialog(comp);//Shows the save dialog window

        //If the user chose to save a file
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File saveFile = fileChooser.getSelectedFile(); //Grabs the file the user chose to save to

            //If the file saved by the user, doesn't have .txt add the end, it will add it for them.
            if (!saveFile.getName().toLowerCase().endsWith(".txt")) 
            {
                saveFile = new File(saveFile.getAbsolutePath() + ".txt");//Adds .txt to the end of the file name
            }

            //Saving the Weekly Overview to savedSchedule(User-View).txt
            try
            {
                FileWriter writer1 = new FileWriter(saveFile); //Overwrite Mode
                
                for(int i = 0; i <days.length; i++)
                {
                    writer1.write(days[i] + "\n");//Adds the day to the file
                    
                    String dayKey = days[i];//Grabs the key for the day
                    List<Event> eventsList = eventMap.get(dayKey);//Grabs the events for the day


                    //If the events are empty
                    if (eventsList != null)
                    {
                        for(Event event : eventsList)
                        {
                            writer1.write(" - " + event.toString() + "\n");//Adds the event to the file
                        }
                        
                    }
                    writer1.write("\n\n"); //Spacer


                }
                writer1.close();//Closes the file writer
                System.out.println("File Written Successfully! ");//Prints to the console that the file was written successfully

                //Closes Main Window/Program
                if (comp instanceof Window)
                {
                    ((Window) comp).dispose();//Closes the main window
                }
            }
            catch (IOException e)
            {
                System.out.println("Unable to write to file...");//Prints to the console that the file was not written successfully
                e.printStackTrace();//Prints the stack trace for the exception
            }

        }

    }

}