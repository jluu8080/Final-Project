import java.util.Comparator;
import java.util.List;
import java.util.Map;

import java.awt.*;

//different imports
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

    //Validates the time input
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
    static void addEventDialog(Component comp,String[] days, Map<String, List<Event>> eventMap) {
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
                }

            } 
            //add another segment of code that excludes hour exceding 12, minute exceeding 60.
            else {
                JOptionPane.showMessageDialog(comp, "Please enter a valid name and time (e.g. 3:30).");
            }
        }
    }
    

    //Delete Button Functionality
    static void deleteEventDialog(Component comp,String[] days, Map<String, List<Event>> eventMap) {
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
                JOptionPane.showMessageDialog(comp, "No events to delete for " + selectedDay);
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
        }
    }


    //Replace Button Functionality
    static void replaceEventDialog(Component comp, String[] days, Map<String, List<Event>> eventMap) {

        //Makes a pop-up window
        String selectedDay = (String) JOptionPane.showInputDialog(
                comp, "Select Day:", "Replace Event",
                JOptionPane.QUESTION_MESSAGE, null, days, days[0]);

        //If user picks a day and doesn't close the window
        if (selectedDay != null) {
            List<Event> dayEvents = eventMap.get(selectedDay);
            if (dayEvents.isEmpty()) {
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

                //exception handling, make sure new event is not the same as the original one.
                if (original != null) {
                    String newName = JOptionPane.showInputDialog(
                            comp, "Enter new event name:", original.getName());
                    if (newName != null && !newName.trim().isEmpty()) {
                        // Replace with new event name, same time/AMPM
                        String newTime = JOptionPane.showInputDialog(
                                comp, "Enter new event time (HH:MM):", original.getTime());
                        if (newTime != null && !newTime.trim().isEmpty()) {
                            if (validTimeInput(comp, newTime)) {
                                Event updated = new Event(newName.trim(), newTime.trim(), original.getAM_PM());

                                dayEvents.remove(original);
                                dayEvents.add(updated);
                                //Sorts the events for the day
                                sortEvents(selectedDay, eventMap);
                                JOptionPane.showMessageDialog(comp, "Event updated.");
                            }
                            else {
                                JOptionPane.showMessageDialog(comp, "Invalid time format. Event not updated.");
                            }
                        } 
                    } 
                    else {
                        JOptionPane.showMessageDialog(comp, "Event time cannot be empty.");
                    }
                } 
                else {
                    JOptionPane.showMessageDialog(comp, "Event name cannot be empty.");
                }
                
            }
        }
    }


    //Save Button Functionality
    /*
    static void replaceEventDialog(Component comp, String[] days, Map<String, List<Event>> eventMap)
    {
        JFileChooser = 
    {
    */

}