import java.util.Scanner;

/*
Issues:
1. scanner input but no output/closing function
    What do you mean? - Jason L.


 */


public class main 
{

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in); //Makes an input class

        //Kloe's Array: Idk why but when I print the days, the days are misaligned
        //String[] daysOfTheWeek = {"   ","  Mon  ","  Tue  ","  Wed  ","  Thu  ","  Fri  ","  Sat  ","  Sun  "};
        
        //Jason's Arrays: daysOfTheWeek & hours
        String[] daysOfTheWeek = {"\t\t  Mon  ","\t\t  Tue  ","\t\t  Wed  ","\t\t  Thu  ","\t\t  Fri  ","\t\t  Sat  ","\t\t  Sun  "};

        String[] hours = 
        {
            "12:00 AM", "01:00 AM", "02:00 AM","03:00 AM","04:00 AM", "05:00 AM", "06:00 AM", "07:00 AM","08:00 AM", "09:00 AM", "10:00 AM","11:00 AM",
            "12:00 PM", "01:00 PM", "02:00 PM","03:00 PM","04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM","08:00 PM", "09:00 PM", "10:00 PM","11:00 PM",
    
        };


        /*
        System.out.println("Menu");
        System.out.println("Enter Day (1-7): ");     
        int dayInput = input.nextInt();


        System.out.println("What time (Hour): ");
        int timeInput = input.nextInt();

        input.nextLine(); //Buffer line that must be used after asking for an Integer input and immediately a string input.

        System.out.println("What activity?: ");
        String activityInput = input.nextLine();
        
        
        if (dayInput<7 && dayInput >0){
            System.out.println(daysOfTheWeek[dayInput]);
        }
        else
        {
            System.out.println("That is not a day of the week.");
        }
        */

        System.out.println(); //Spacer

        //Prints Days of The Week
        for (int i = 0; i<daysOfTheWeek.length; i++)
        {
            System.out.print(daysOfTheWeek[i]);

        }

        System.out.println(); //Spacer


    //Kloe's Loop for hourly print and blank activity spaces
    //print out time from 12 AM to 11 PM

    /*
    for (int hour = 0; hour < 24; hour++) {

        int displayHour = hour % 12;
        if (displayHour == 0) displayHour = 12;
        String period = (hour < 12) ? "AM" : "PM";
        

        // Print time label (example 01:00 PM)
        System.out.printf("%02d:00 %s", displayHour, period);

        // Print 7 empty activity slots (one for each day of the hour)
        for (int i = 0; i < 7; i++) {
            System.out.print("\t--------");
        }
        System.out.println();
    }
    */

    //Jason's Loop for hours
    for (int i = 0; i<hours.length; i++)
    {
        System.out.print(hours[i]);

        for (int j = 0; j < 7; j++) {
            System.out.print("\t--------");
        }
        System.out.println();

    }
}
}
