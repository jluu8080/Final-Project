import java.util.Scanner;

class overviewFunctions
{
    static Scanner input = new Scanner(System.in); //Makes it accessible within the class.

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
    public static int hourInput()
    {
        //While true (infinite loop), asking for an hour continiously until the input is valid.
        while (true)
        {
            //Asks for an hour from 1 AM - 12 AM??
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


        /*
        System.out.println("Menu");
        int dayInput = overviewFunctions.dayInput();

        System.out.println();//Spacer


        System.out.print("What time (1-24): ");
        int timeInput = input.nextInt();

        System.out.println();//Spacer

        input.nextLine(); //Buffer line that must be used after asking for an Integer input and immediately a string input.

        */
        int timeInput = 2;
        int dayInput = 2;

        //System.out.print("What activity?: ");
        String activityInput = "ILoveAnimeGirlsAndICannotLie :P";//input.nextLine();

        System.out.println();//Spacer
        
        activityInput = "\t" + activityInput; //Adds a tabspace to it
        System.out.println(activityInput.length());

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
            activityInput = activityInput.substring(0, 9);
            System.out.println(activityInput);
        }
        
        weeklyOverview[timeInput][dayInput] = activityInput; //Adds the activity to the day and hour the user inputted
        overviewFunctions.printWeeklyOverview(weeklyOverview); //Prints the entire Weekly Overview.

        input.close();
    }
    
}