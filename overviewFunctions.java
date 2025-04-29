import java.util.Scanner;

class overviewFunctions
{
    static Scanner input = new Scanner(System.in); //Makes it accessible within the class.
    static String emptyActivity = "\t--------"; //Sring Value used within weeklyOverview for empty activity/indexes


    //Prints the Weekly Overview (In its entirety)
    public static void printWeeklyOverview(String[][] weeklyOverview)
    {
        //Prints the Weekly Overview
        for (int i = 0; i<weeklyOverview.length;i++) //Why weeklyOverview.length is like this? Because it's an array
        {
            for (int j = 0; j<weeklyOverview[i].length;j++)
            {
                System.out.print(weeklyOverview[i][j]);
            }
        }
    }

    //Asks for Day Input
    public static int dayInput()
    {
        //While true (infinite loop), asking for a day.
        while (true)
        {
            //Asks for a day from Monday(1) - Sunday(7)
            System.out.print("Enter Day (1-7): ");
            int day = input.nextInt();
            
            System.out.println(); //Spacer
            
            //If day is greater than or equal 1 & less than 8
            if (day>=1 && day<8)
            {
                return day; //Returns day
            }
            else
            {
                System.out.println("Your Input is invalid...\n"); //Error Message
            }
        }
    }

    //Asks for hour input
    public static int timeInput()
    {
        //While true (infinite loop), asking for an hour continiously until the input is valid.
        while (true)
        {
            //Asks for an hour from 12 AM - 11 PM
            System.out.print("What time (1-24): ");
            int hour = input.nextInt();
            
            System.out.println(); //Spacer
            
            //If day is greater than or equal 1 & less than 8
            if (hour>=1 && hour<=24)
            {
                return hour; //Returns hour and breaks out of loop because inputted value is valid.
            }
            else
            {
                System.out.println("Your Input is invalid...\n"); //Error Message
            }
        }
    }

    //Adds Activity to the weekly overview
    public static String addActivity()
    {
        input.nextLine(); //Buffer Line, used after right after doing a numerical/integer/double input
        while (true)
        {

            System.out.print("What activity? (Ex. Running): ");
            String activityInput = input.nextLine();//"ILoveAnimeGirlsAndICannotLie :P";//input.nextLine(); used for testing

            System.out.println();//Spacer

            System.out.println("Your Activity is: '" + activityInput + "' Is this Correct?"); //Prints a message asking if this is what the user inputted
            System.out.print("Yes or No?: ");
            String userInput = input.nextLine(); //User Input for yes or no

            //If the user says "No", then the loop will re-iterate/loop again.
            if (userInput.equals("No") || !(userInput.equals("Yes")))
            {
                continue;
            }

            /*
            Otherwise aka else, activityInput will be processed, adds a tabspace to correctly format itself
            and \t aka tab space or ASCNI characters are considered as 1 character not 2.
            */
            activityInput = "\t" + activityInput; 


            /*
            If activityInput is less than 9 characters because emptyActivty or "\t--------" is 9 characters in length
            then spaces need to be added for it to correctly place and format itself inside weeklyOverview
            */
            if (activityInput.length() <=9)
            {

                for (int i = activityInput.length(); i<=9; i++) //why activityInput.length() is like that is because it's a String
                {
                    activityInput += " ";
                }
            }
            //If greater than 8, cut down the string to 8 characters **Would need to format the entire overview with more spaces.
            else
            {
                activityInput = activityInput.substring(0, 9); //Substring(0 - Inclusive, 9 - Exclusive) indexes of the string.
               //System.out.println(activityInput); //Prints the input for testing
            }
            return activityInput;
        }
    }

    //Removes an activity from the weeklyOverview
    public static String[][] removeActivity(String[][] weeklyOverview)
    {
        overviewFunctions.printWeeklyOverview(weeklyOverview); //Prints out the Weekly Overview to allow the user to see where and which activity to remove

        System.out.println("\n\nSelect the Day & Time of the activity, you wish to delete..."); //Double Spacer

        int dayInput = overviewFunctions.dayInput(); //Asks for the Day of the activity to be removed
        int timeInput = overviewFunctions.timeInput(); //Asks for the hour of the activity to be removed.

        weeklyOverview[timeInput][dayInput] = emptyActivity; //Deletes or in this case, replaces the desired activity to be deleted/replaced with "\t--------"
        return weeklyOverview; //Returns the updated Weekly Overview

    }

    //Replaces an activity
    public static String[][] replaceActivity(String[][] weeklyOverview)
    {
        
        overviewFunctions.printWeeklyOverview(weeklyOverview); //Prints out the Weekly Overview to allow the user to see where and which activity to replace

        System.out.println("\n\n"); //Double Spacer

        System.out.println("Select the Day & Time of the activity, you wish to delete...");
        int dayInput = dayInput();
        int timeInput = timeInput();
        //input.nextLine(); //Buffer Line; if Input gets fucked in this method, uncomment this to fix it
        String newActivity = addActivity();

        weeklyOverview[timeInput][dayInput] = newActivity; //Replaces the Activity at the user's desired day and hour.
        return weeklyOverview;

    }

    
}
