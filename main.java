import java.util.Scanner;

/*
Issues:
1. scanner input but no output/closing function


 */


public class main 
{

    public static void main(String[] args)
    {
        String[] daysOfTheWeek = {"   ","  Mon  ","  Tue  ","  Wed  ","  Thu  ","  Fri  ","  Sat  ","  Sun  "};
        Scanner input = new Scanner(System.in);

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
        for (int i = 0; i<8; i++)
        {
            System.out.print("\t" + daysOfTheWeek[i] + "\t");

        }


        System.out.println(); //Spacer
        
    //print out time 
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
}
}
