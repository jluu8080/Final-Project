//Reading a File Imports
import java.io.File;
import java.io.FileNotFoundException;

//Writing to a File imports
import java.io.FileWriter;
import java.io.IOException;

//Input & Reading a file import
import java.util.Scanner;


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
        //Reading a file
        try
        {
            File saved_schedule = new File("savedSchedule.txt");
            String line;
            Scanner reader = new Scanner(saved_schedule);

            while (reader.hasNextLine())
            {
                line = reader.nextLine();
                System.out.println(line);
            }
            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found...");
            e.printStackTrace();
        }

        //Writing to a file
        try
        {
            FileWriter writer = new FileWriter("savedSchedule.txt"); //Overwrite Mode
            FileWriter writer = new FileWriter("schedule.txt", true); //Append Mode

            writer.write("Stupid Fuck!\n");

            writer.close();
            System.out.println("File Written Successfully! ");
        }
        catch (IOException e)
        {
            System.out.println("Unable to write to file...");
            e.printStackTrace();
        }
        */

        /*
        while (true)
        {
            //Base Menu
            System.out.println("\n\nMenu");
            System.out.println("(1) Add Activity");
            System.out.println("(2) Delete Activity");
            System.out.println("(3) Replace Activity");
            System.out.println("(4) Quit\n");
            
            System.out.print("Select an Option(1 - 4): ");
            String userInput = input.nextLine();
            System.out.println();


            //Add an Activity
            if (userInput.equals("1"))
            {
                int dayInput = overviewFunctions.dayInput();

                int timeInput = overviewFunctions.timeInput();

                String activityInput = overviewFunctions.addActivity();

                if(dayInput==7)
                {
                    activityInput +="\n";
                }

                weeklyOverview[timeInput][dayInput] = activityInput;
            }
            
            //Remove an Activity
            else if(userInput.equals("2"))
            {
                weeklyOverview[2][2] =  "\tSleeping"; //Used for Testing, comment if need be
                weeklyOverview = overviewFunctions.removeActivity(weeklyOverview);
            }

            //Replaces Activity
            else if(userInput.equals("3"))
            {
                weeklyOverview = overviewFunctions.replaceActivity(weeklyOverview);
            }

            else if (userInput.equals("4"))
            {
                break;
            }

            //Invalid Selection of Options/Invalid Input.
            else
            {
            
                System.out.println("Input Not Valid...(Pause Implemented)");

                //Sleep/Pause Screen code for Java, this needs to be done in a try & catch | half-assed explanation, I sowry rushing comments out rn
                try
                {
                    Thread.sleep(2000); //Pauses for 2 seconds/2000 Miliseconds, throws a sleep exception
                }
                catch (InterruptedException e) //Sleep exception caught
                {
                    e.printStackTrace(); //Executes sleep/pause of the screen for the set amount of time
                }
            }

            //int timeInput = 2; //For Testing
            //int dayInput = 2; //For Testing

            //Clears the Screen
            //System.out.print("\033[H\033[2J"); //Clears the Screen
            //System.out.flush(); //Outputs it immediately
            overviewFunctions.printWeeklyOverview(weeklyOverview); //Prints the entire Weekly Overview.
        }
        */
        
        //Clears the Screen
        //System.out.print("\033[H\033[2J");
        //System.out.flush();

        //End of the Loop
        //overviewFunctions.printWeeklyOverview(weeklyOverview); //Uncomment later after testing reaidng & writing

        //Saving the Weekly Overview to savedSchedule(Computer-View).txt
        try
        {
            FileWriter writer = new FileWriter("savedSchedule(Computer-View).txt"); //Overwrite Mode
            String computerFileLine = "";
            //FileWriter writer = new FileWriter("schedule.txt", true); //Append Mode

            
            for (int i = 0; i<weeklyOverview.length; i++)
            {
                for (int j = 0; j< weeklyOverview[i].length; j++)
                {
                    if (weeklyOverview[i][j].indexOf("\n") != -1)
                    {
                        computerFileLine = weeklyOverview[i][j].replace("\n","<br>"); //<br> DOES ABSOLUTELY NOTHING! in the .txt files it's just there as an indicator/a string for a line break/new row
                    }
                    else
                    {
                        computerFileLine = weeklyOverview[i][j] + "||"; // "||" DOES ABSOLUTELY NOTHING! In the .txt files, it's just there as an indictator/a string to indicate a cell & separate cells
                    }
                    writer.write(computerFileLine); //Adds line to File
                }
            }


            writer.close(); //Closes file
            System.out.println("File Written Successfully! ");
        }
        catch (IOException e)
        {
            System.out.println("Unable to write to file...");
            e.printStackTrace();
        }

        
        //Saving the Weekly Overview to savedSchedule(User-View).txt
        try
        {
            FileWriter writer1 = new FileWriter("savedSchedule(User-View).txt"); //Overwrite Mode
            String userFileLine = "";
            //FileWriter writer = new FileWriter("schedule.txt", true); //Append Mode

            
            for (int i = 0; i<weeklyOverview.length; i++)
            {
                for (int j = 0; j< weeklyOverview[i].length; j++)
                {

                    //<br> DOES ABSOLUTELY NOTHING!, in the .txt it's just there as an indicator/a string for a line break
                    //While <br> does nothing, "\n" does make put everything on a new line in the .txt file
                    userFileLine = weeklyOverview[i][j].replace("\n","<br>\n"); //For every "\n" that exists in an index value, will be replaced with "<br>\n"
                    writer1.write(userFileLine); //Writes the Line 
                }
            }


            writer1.close(); //Closes File
            System.out.println("File Written Successfully! ");
        }
        catch (IOException e)
        {
            System.out.println("Unable to write to file...");
            e.printStackTrace();
        }





        //Reading WeeklyOverview(Computer's Version)
        try
        {
            File saved_schedule = new File("savedSchedule(Computer-View).txt");
            String line;
            Scanner reader = new Scanner(saved_schedule);

            while (reader.hasNextLine())
            {
                line = reader.nextLine(); //Stores each line into a String

                /*Splits the line by "||" and turns them into an Array element
                    Ex. String --> "Banana || Apple"

                        Array w/ delimitter/cut off --> {"Banana", "Apple"}
                */
                String[] file_line = line.split("\\|\\|"); // "\\||" - Literally takes only "||"
                
                for (int i = 0; i<file_line.length; i++)
                {
                    file_line[i] = file_line[i].replace("<br>","\n"); //Replaces each line break with a new line ASNI character.
                    System.out.print(file_line[i]); //Prints for tetsing
                }

            }
            reader.close(); //Closes File
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File Not Found..."); //Error Message
            e.printStackTrace();
        }

        input.close(); //Closes Input, to prevent Memory Leak

    }
    
}