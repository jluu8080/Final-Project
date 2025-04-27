public class Event {
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


            if (amPm.equals("PM") && hour != 12)
            {
                hour += 12;
            }

            if (amPm.equals("AM") && hour == 12)
            {
                hour = 0;
            }

            return String.format("%02d:%02d", hour, min);
        }
        catch (Exception e) {
            return "99:99"; // fallback if something goes wrong
        }
    }

    public String getName()
    {
        return name;
    }

    public String getTime()
    {
        return time;
    }

    public String getAM_PM()
    {
        return amPm;
    }



    @Override
    public String toString() {
        return name + " at " + time + " " + amPm;
    }
    
}