import java.util.Scanner;

/**
 * Utility Class for taking input from  a user, and making sure it's valid
 * @author Nicolas Petras
 * @version 1.0
 */
public class TakeInput
{
    private Scanner scan;

    /**
     * Constructor to initialise the object - Creates a scanner object to handle the input from the user
     */
    TakeInput()
    {
        scan = new Scanner(System.in);
    }

    /**
     * Takes in boolean input from the user and ensures it's valid
     * @param prompt Prompt to provide to the user
     * @return The boolean vale equivalent to what the user entered - Yes being 'true' and No being 'false'
     */
    public boolean booleanInput(String prompt)
    {
        String input;
        boolean boolValue = false;
        System.out.println(prompt + " (Y/N)");
        input = scan.nextLine().toUpperCase();
        // Makes sure input is valid - ensuring that one of the following strings has been entered
        while (!(input.equals("Y") || input.equals("YES") || input.equals("N") || input.equals("NO")))
        {
            // re-prompt the user if the input was invalid
            System.out.println("Invalid value entered: please enter one of the following (in upper or " +
                    "lower case):");
            System.out.println("Y, N, Yes, No");
            input = scan.nextLine().toUpperCase();
        }
        if (input.equals("Y") || input.equals("YES"))
        {
            boolValue = true;
        }
        return (boolValue);
    }

    /**
     * Takes in int input from the user and ensures it's valid
     * @param prompt The prompt to present to the user
     * @return Valid int value the user has entered
     */
    public int intInput(String prompt)
        {
            int inputInt;
            System.out.println(prompt);
            // re-prompt the user if invalid input was entered
            while (!scan.hasNextInt())
            {
                scan.next();
                System.out.println("Invalid value, entered please enter an integer:");

            }
            inputInt = scan.nextInt();
            scan.nextLine();
            return (inputInt);
    }

    /**
     * Takes in positive int input from the user and ensures it's valid
     * @param prompt The prompt to present to the user
     * @return Valid int value the user has entered
     */
    public int positiveIntInput(String prompt)
    {
        int inputInt;
        System.out.println(prompt);
        while (!scan.hasNextInt())
        {
            scan.next();
            System.out.println("Invalid value, entered please enter a positive integer:");
        }
        inputInt = scan.nextInt();
        scan.nextLine();
        return (inputInt);
    }
    /**
     * Takes in String input from the user and ensures it's valid
     * @param prompt The prompt to present to the user
     * @return Valid String value the user has entered
     */
    public String stringInput(String prompt)
    {
        String input = null;
        int i = 0;
        do
        {
            // check if an empty (invalid) String was entered
            if (i > 0 && (input.equals("")))
            {
                System.out.println("An empty piece of input was entered, please enter a valid piece of input");
            }
            System.out.println(prompt);
            input = scan.nextLine();
            i++;
        } while (input.equals(""));
        return input;
    }
}
