import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class EventWeekPlanner extends JFrame {
    private final String[] days = {
        "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday", "Sunday"
    };

    private final Color[] dayColors = {
        new Color(255, 204, 204),  // Monday - Light Pink
        new Color(204, 229, 255),  // Tuesday - Light Blue
        new Color(204, 255, 229),  // Wednesday - Mint
        new Color(255, 255, 204),  // Thursday - Light Yellow
        new Color(255, 229, 204),  // Friday - Peach
        new Color(229, 204, 255),  // Saturday - Lavender
        new Color(204, 255, 255)   // Sunday - Cyan
    };

    // Stores events per day
    private final Map<String, List<Event>> eventMap = new HashMap<>();

    public EventWeekPlanner() {

        setTitle("Weekly Event Planner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Weekly Event Planner", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setOpaque(false);
        add(titleLabel, BorderLayout.NORTH);

        // Grid Panel with padding
        JPanel gridPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        gridPanel.setOpaque(false);
        gridPanel.setPreferredSize(new Dimension(700, 300));
        JPanel paddedGrid = new JPanel(new BorderLayout());
        paddedGrid.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        paddedGrid.setOpaque(false);
        paddedGrid.add(gridPanel, BorderLayout.CENTER);
        add(paddedGrid, BorderLayout.CENTER);

        // Initialize event map
        for (String day : days) {
            eventMap.put(day, new ArrayList<>());
        }

        // Create day buttons
        for (int i = 0; i < days.length; i++) {
            String day = days[i];
            JButton dayButton = new JButton("<html><center><b>" + day + "</b><br/></center></html>");
            dayButton.setBackground(dayColors[i]);
            dayButton.setOpaque(true);
            dayButton.setBorderPainted(false);
            dayButton.setFont(new Font("Times New Roman", Font.PLAIN, 34));
            dayButton.addActionListener(e -> handleDayClick(day));
            gridPanel.add(dayButton);
        }

        // Final block for Add/Delete functionality
        JPanel controlPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        controlPanel.setOpaque(false);
        JButton addButton = new JButton("Add Event");
        JButton deleteButton = new JButton("Delete Event");
        JButton replaceButton = new JButton("Replace Event");
        addButton.addActionListener(e -> addEventDialog());
        deleteButton.addActionListener(e -> deleteEventDialog());
        replaceButton.addActionListener(e -> replaceEventDialog());
        controlPanel.add(addButton);
        controlPanel.add(deleteButton);
        controlPanel.add(replaceButton);
        gridPanel.add(controlPanel);

        setVisible(true);
    }

    // Add event dialog
    private void addEventDialog() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField nameField = new JTextField();
        JTextField timeField = new JTextField();
        JComboBox<String> amPmBox = new JComboBox<>(new String[]{"AM", "PM"});
        JComboBox<String> dayBox = new JComboBox<>(days);

        panel.add(new JLabel("Event Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Event Time (HH:MM):"));
        panel.add(timeField);
        panel.add(new JLabel("AM/PM:"));
        panel.add(amPmBox);

        int result = JOptionPane.showConfirmDialog(this, new Object[]{dayBox, panel},
                "Add New Event", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String time = timeField.getText().trim();
            String amPm = (String) amPmBox.getSelectedItem();
            String day = (String) dayBox.getSelectedItem();

            if (!name.isEmpty() && time.matches("\\d{1,2}:\\d{2}")) {
                Event event = new Event(name, time, amPm);
                eventMap.get(day).add(event);
                sortEvents(day);
                JOptionPane.showMessageDialog(this, "Event added to " + day + ".");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid name and time (e.g. 3:30).");
            }
        }
    }

    // Delete event dialog
    private void deleteEventDialog() {
        JComboBox<String> dayBox = new JComboBox<>(days);
        String selectedDay = (String) JOptionPane.showInputDialog(
                this, "Select Day:", "Delete Event",
                JOptionPane.QUESTION_MESSAGE, null, days, days[0]);

        if (selectedDay != null) {
            List<Event> dayEvents = eventMap.get(selectedDay);
            if (dayEvents.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No events to delete for " + selectedDay);
                return;
            }

            String[] eventStrings = dayEvents.stream().map(Event::toString).toArray(String[]::new);
            String selectedEvent = (String) JOptionPane.showInputDialog(
                    this, "Select event to delete:",
                    "Delete Event", JOptionPane.PLAIN_MESSAGE,
                    null, eventStrings, eventStrings[0]);

            if (selectedEvent != null) {
                dayEvents.removeIf(e -> e.toString().equals(selectedEvent));
                JOptionPane.showMessageDialog(this, "Event deleted.");
            }
        }
    }
    // Replace event dialog
    private void replaceEventDialog() {
        String selectedDay = (String) JOptionPane.showInputDialog(
                this, "Select Day:", "Replace Event",
                JOptionPane.QUESTION_MESSAGE, null, days, days[0]);

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

    // Handle day button click: show events
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

    // Sort events for a day
    private void sortEvents(String day) {
        eventMap.get(day).sort(Comparator.comparing(Event::get24HourTime));
    }

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

        public String get24HourTime() {
            try {
                String[] parts = time.split(":");
                int hour = Integer.parseInt(parts[0]);
                int min = Integer.parseInt(parts[1]);
                if (amPm.equals("PM") && hour != 12) hour += 12;
                if (amPm.equals("AM") && hour == 12) hour = 0;
                return String.format("%02d:%02d", hour, min);
            } catch (Exception e) {
                return "99:99"; // fallback
            }
        }

        @Override
        public String toString() {
            return name + " at " + time + " " + amPm;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventWeekPlanner::new);
    }
}
