import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs a Kennel
 *
 * @author Lynda Thomas and Chris Loftus
 * @version 1.2 (23rd February 2018)
 */
public class KennelDemo
{
    private String filename; // holds the name of the file
    private Kennel kennel; // holds the kennel
    private Scanner scan; // so we can read from keyboard

    /*
     * Notice how we can make this constructor private, since we only call from main which
     * is in this class. We don't want this class to be used by any other class.
     */
    private KennelDemo()
    {
        scan = new Scanner(System.in);
        System.out.print("Please enter the filename of kennel information: ");
        filename = scan.nextLine();

        kennel = new Kennel();
    }

    /*
     * initialise() method runs from the main and reads from a file
     */
    private void initialise()
    {
        System.out.println("Using file " + filename);

        try
        {
            kennel.load(filename);
        } catch (FileNotFoundException e)
        {
            System.err.println("The file: " + filename + " does not exist. Assuming first use and an empty file." +
                    " If this is not the first use then have you accidentally deleted the file?");
        } catch (IOException e)
        {
            System.err.println("An unexpected error occurred when trying to open the file " + filename);
            System.err.println(e.getMessage());
        }
    }

    /*
     * runMenu() method runs from the main and allows entry of data etc
     */
    private void runMenu()
    {
        String response;
        do
        {
            printMenu();
            System.out.println("What would you like to do:");
            scan = new Scanner(System.in);
            response = scan.nextLine().toUpperCase();
            switch (response)
            {
                case "1":
                    admitDog();
                    break;
                case "2":
                    changeKennelName();
                    break;
                case "3":
                    printAll();
                    break;
                case "4":
                    searchForDog();
                    break;
                case "5":
                    removeDog();
                    break;
                case "6":
                    setKennelCapacity();
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Try again");
            }
        } while (!(response.equals("Q")));
    }

    private void printMenu()
    {
        System.out.println("1 -  add a new Dog ");
        System.out.println("2 -  set up Kennel name");
        System.out.println("3 -  display all inmates");
        System.out.println("4 -  search for a dog");
        System.out.println("5 -  remove a dog");
        System.out.println("6 -  set kennel capacity");
        System.out.println("q - Quit");
    }

    private void setKennelCapacity()
    {
        System.out.print("Enter max number of dogs: ");
        int max = scan.nextInt();
        scan.nextLine();
        kennel.setCapacity(max);
    }

    /*
     * printAll() method runs from the main and prints status
     */
    private void printAll()
    {
        System.out.println(kennel);
    }

    /*
     * save() method runs from the main and writes back to file
     */
    private void save()
    {
        try
        {
            kennel.save(filename);
        } catch (IOException e)
        {
            System.err.println("Problem when trying to write to file: " + filename);
        }
    }

    private void removeDog()
    {
        System.out.println("which dog do you want to remove");
        String dogtoberemoved;
        dogtoberemoved = scan.nextLine();
        kennel.removeDog(dogtoberemoved);
    }

    private void searchForDog()
    {
        System.out.println("which dog do you want to search for");
        String name = scan.nextLine();
        Dog dog = kennel.search(name);
        if (dog != null)
        {
            System.out.println(dog.toString());
        } else
        {
            System.out.println("Could not find dog: " + name);
        }
    }

    private void changeKennelName()
    {
        String name = scan.nextLine();
        kennel.setName(name);
    }

    private void admitDog()
    {
        boolean lb = false;
        System.out
                .println("enter on separate lines: name, likeBones?, favourite food, number of times fed, owner-name, owner-phone, ");
        String name = scan.nextLine();
        System.out.println("Does he like bones? (Y/N)");
        String likeBones;
        likeBones = scan.nextLine().toUpperCase();
        if (likeBones.equals("Y"))
        {
            lb = true;
        }
        System.out.println("What is his/her favourite food?");
        String fav;
        fav = scan.nextLine();
        System.out.println("How many times is he/she fed a day? (as a number)");
        int numTimes;
        numTimes = scan.nextInt(); // This can be improved (InputMismatchException?)
        scan.nextLine(); // Clear the end of line characters because I didn't use a delimiter
        Dog newDog = new Dog(name, lb, fav, numTimes);
        ArrayList<Owner> owners = getOwners();
        for (Owner o : owners)
        {
            newDog.addOriginalOwner(o);
        }

        kennel.addDog(newDog);
    }

    private ArrayList<Owner> getOwners()
    {
        ArrayList<Owner> owners = new ArrayList<Owner>();
        String answer;
        do
        {
            System.out
                    .println("Enter on separate lines: owner-name owner-phone");
            String ownName = scan.nextLine();
            String ownPhone = scan.nextLine();
            Owner own = new Owner(ownName, ownPhone);
            owners.add(own);
            System.out.println("Another owner (Y/N)?");
            answer = scan.nextLine().toUpperCase();
        } while (!answer.equals("N"));
        return owners;
    }


    // /////////////////////////////////////////////////
    public static void main(String args[])
    {
        System.out.println("**********HELLO***********");
        KennelDemo demo = new KennelDemo();
        demo.initialise();
        demo.runMenu();
        demo.printAll();
        // MAKE A BACKUP COPY OF dogsrus.txt JUST IN CASE YOU CORRUPT IT
        demo.save();
        System.out.println("***********GOODBYE**********");
    }
}
