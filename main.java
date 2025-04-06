import java.util.Scanner;

/*
<<<<<<< Updated upstream
Issues:
1. We can't write a full day out without days being close together, need to manually format days. Please fix 



n+1. teamwork with Jason is hard lets work on that.
 */
=======
I made a class to make static methods for the weekly overview, you can think of methods like functions
//The reason why there's no public for the static void method is because it's only ever going to be used here
In the main file.
*/

class oveviewFunctions
{
    public static void printWeeklyOverview(String[][] overview)
    {
        //Prints the Weekly Overview
        for (int i = 0; i<overview.length;i++)
        {
            for (int j = 0; j<overview[i].length;j++)
            {
                System.out.print(overview[i][j]);
            }
        }
    }
}
>>>>>>> Stashed changes


public class main 
{

    public static void main(String[] args)
    {
<<<<<<< Updated upstream
        String[] daysOfTheWeek = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        Scanner input = new Scanner(System.in);

        /*
=======
        Scanner input = new Scanner(System.in); //Makes an input class

        //Kloe's Array: Idk why but when I print the days, the days are misaligned
        //String[] daysOfTheWeek = {"   ","  Mon  ","  Tue  ","  Wed  ","  Thu  ","  Fri  ","  Sat  ","  Sun  "};
        
        //Jason's Arrays:
        String[][] weeklyOverview =
        {
            {"\t\t","  Mon  ","\t\t  Tue  ","\t\t  Wed  ","\t\t  Thu  ","\t\t  Fri  ","\t\t  Sat  ","\t\t  Sun  \n"},
            {"12:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"01:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"02:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"03:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"04:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"05:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"06:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"07:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"08:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"09:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"10:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 AM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},

        };

>>>>>>> Stashed changes
        System.out.println("Menu");
        System.out.println("Enter Day (1-7): ");     
        int dayInput = input.nextInt();


        System.out.println("What time (Hour): ");
        int timeInput = input.nextInt();

        input.nextLine(); //Buffer line that must be used after asking for an Integer input and immediately a string input.

        System.out.println("What activity?: ");
        String activityInput = input.nextLine();
<<<<<<< Updated upstream


        
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
        for (int i = 0; i<7; i++)
        {
            System.out.print("\t" + daysOfTheWeek[i] + "\t");

        }
        
        
=======
        


>>>>>>> Stashed changes
        System.out.println(); //Spacer
        oveviewFunctions.printWeeklyOverview(weeklyOverview);

        //Prints Hours
        for (int i = 1; i<6; i++)
        {
            System.out.println(i);

<<<<<<< Updated upstream
        }
=======
        /*
    //Kloe's Loop for hourly print and blank activity spaces
    //print out time from 12 AM to 11 PM

    /*
    for (int hour = 0; hour < 24; hour++) {

        int displayHour = hour % 12;
        if (displayHour == 0) displayHour = 12;
        String period = (hour < 12) ? "AM" : "PM";
>>>>>>> Stashed changes
        
        /*
        for (int h = 1; h<7; i++)
        {
            System.out.print("\t" + "activity" + "\t");

            for (int d = 0; d<6; d++)
            System.out.println();
        }
        */



        
    }
<<<<<<< Updated upstream
    


=======
    //Jason's Loop for hours
    for (int i = 0; i<hours.length; i++)
    {
        System.out.print(hours[i]);

        for (int j = 0; j < 7; j++) {
            System.out.print("\t--------");
        }
        System.out.println();

    }
    */
}
>>>>>>> Stashed changes
}
