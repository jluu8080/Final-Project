import java.util.Scanner;
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


public class main 
{

    public static void main(String[] args)
    {
        String[] daysOfTheWeek = {"Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
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
        System.out.print("Enter Day (1-7): ");     
        int dayInput = input.nextInt();

        System.out.println();//Spacer


        System.out.print("What time (1-24): ");
        int timeInput = input.nextInt();

        System.out.println();//Spacer

        input.nextLine(); //Buffer line that must be used after asking for an Integer input and immediately a string input.

        System.out.print("What activity?: ");
        String activityInput = input.nextLine();

        System.out.println();//Spacer
        */
        
        //weeklyOverview[timeInput][dayInput] = activityInput;
        String activityInput = "\tDumb";
        int activityInputLength = activityInput.length();

        if (activityInput.length() <=8)
        {
            for (int i = activityInput.length(); i<=8; i++)
            {
                activityInput += " ";
            }
        }
        //activityInput += "\t";
        
        System.out.println(activityInput.length());
        System.out.println(weeklyOverview[2][2]);

        weeklyOverview[2][2] = activityInput;
        System.out.println(weeklyOverview[2][2]);


        /*
        if (dayInput<7 && dayInput >0){
            System.out.println(daysOfTheWeek[dayInput]);
        }
        else
        {
            System.out.println("That is not a day of the week.");
        }
        */

        oveviewFunctions.printWeeklyOverview(weeklyOverview);

    }
    
}
