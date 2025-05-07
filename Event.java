public class Event {
    private String name;
    private String time;
    private String amPm;

    public Event(String name, String time, String amPm) {
        this.name = name;
        this.time = time;
        this.amPm = amPm;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getAM_PM() {
        return amPm;
    }

    public String get24HourTime() {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        if ("PM".equalsIgnoreCase(amPm) && hour != 12) {
            hour += 12;
        } else if ("AM".equalsIgnoreCase(amPm) && hour == 12) {
            hour = 0;
        }
        return String.format("%02d:%02d", hour, minute);
    }

    @Override
    public String toString() {
        return name + " at " + time + " " + amPm;
    }
}