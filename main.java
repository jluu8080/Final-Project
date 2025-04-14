import java.util.Scanner;

class overviewFunctions
{
    static Scanner input = new Scanner(System.in); //Makes it accessible within the class.
    static String emptyActivity = "\t--------"; //Sring Value used within weeklyOverview for empty activity/indexes

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
                return day;
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
            if (hour>=1 && hour<24)
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
            String activityInput = input.nextLine();//"ILoveAnimeGirlsAndICannotLie :P";//input.nextLine();

            System.out.println();//Spacer

            System.out.println("Your Activity is: '" + activityInput + "' Is this Correct?");
            System.out.print("Yes or No?: ");
            String userInput = input.nextLine();

            //If the user says "No", then the loop will re-iterate/loop again. Otherwise, activityInput will be processed
            //To corrrectly format and place itself into the weeklyOverview
            if (userInput.equals("No") || !(userInput.equals("Yes")))
            {
                continue;
            }


            activityInput = "\t" + activityInput; //Adds a tabspace to it
            /*
            If activityInput is less than 8 characters, *\t aka tab space or ASCNI characters are considered 1 character not 2
            Add spaces to fill it up to 8 or leave it be
            Because it won't affect weeklyOverview formatting/spacing
            */
            if (activityInput.length() <=9)
            {
                for (int i = activityInput.length(); i<=9; i++)
                {
                    activityInput += " ";
                }
            }
            //If greater than 8, cut down the string to 8 characters **Would need to format the entire overview with more spaces.
            else
            {
                activityInput = activityInput.substring(0, 9); //Substring(0 - Inclusive, 9 - Exclusive) indexes of the string.
                System.out.println(activityInput); //Prints the input for testing
            }
            return activityInput;
        }
    }

    public static String[][] removeActivity(String[][] weeklyOverview)
    {
        int dayInput = 0;
        int timeInput = 0;

        System.out.println();//Spacer
        overviewFunctions.printWeeklyOverview(weeklyOverview);

        System.out.println("\n\nSelect the Day & Time of the activity, you wish to delete..."); //Double Spacer

        dayInput = overviewFunctions.dayInput();
        timeInput = overviewFunctions.timeInput();

        weeklyOverview[timeInput][dayInput] = emptyActivity;
        return weeklyOverview;

    }


public static String[][] replaceActivity(String[][] weeklyOverview)
{

    System.out.println();//Spacer
    
    overviewFunctions.printWeeklyOverview(weeklyOverview);

    System.out.println("\n\n"); //Double Spacer

    System.out.println("Select the Day & Time of the activity, you wish to delete...");
    int dayInput = dayInput();
    int timeInput = timeInput();
    String newActivity = addActivity();

    weeklyOverview[timeInput][dayInput] = newActivity;
    return weeklyOverview;

}
}

public class main 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        String[][] weeklyOverview = //rows[25] columns[8], 8 hypens for an empty activity
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
            {"12:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"01:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"02:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"03:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"04:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"05:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"06:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"07:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"08:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"09:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"10:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},
            {"11:00 PM", "\t--------","\t--------","\t--------","\t--------","\t--------","\t--------","\t--------\n"},

        };


        System.out.println("Menu");
        System.out.println("(1) Add Activity");
        System.out.println("(2) Delete Activity");
        System.out.println("(3) Replace Activity\n");
        
        System.out.print("Select an Option(1-3): ");
        String userInput = input.nextLine();


        //Add an Activity
        if (userInput.equals("1"))
        {
            int dayInput = overviewFunctions.dayInput();

            int timeInput = overviewFunctions.timeInput();

            String activityInput = overviewFunctions.addActivity();

            weeklyOverview[timeInput][dayInput] = activityInput;
        }
        
        //Remove an Activity
        else if(userInput.equals("2"))
        {
            weeklyOverview[2][2] =  "\tSleeping"; //Used for Testing
            weeklyOverview = overviewFunctions.removeActivity(weeklyOverview);
        }

        //Replace Activity
        else if(userInput.equals("3"))
        {
            weeklyOverview = overviewFunctions.replaceActivity(weeklyOverview);
        }

        //Invalid Selection of Options/Invalid Input
        else
        {
        
            System.out.println("Input Not Valid...(Pause Implemented)");
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        //int timeInput = 2; //For Testing
        //int dayInput = 2; //For Testing

        overviewFunctions.printWeeklyOverview(weeklyOverview); //Prints the entire Weekly Overview.

        input.close(); //Closes Input, to prevent Memory Leak
    }
    
}