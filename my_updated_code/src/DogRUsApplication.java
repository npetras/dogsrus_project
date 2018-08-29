import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class runs the Application, running two AnimalHousing objects: cattery and a kennel
 * @author Lynda Thomas, Chris Loftus, and Nicolas Petras
 * @version 1.3 (29th August 2018)
 * Version 1.1 (originally name 'KennelDemo') code created by Lynda Thomas and Chris Loftus, but has been reworked
 * extensively by Nicolas Petras.
 * Version 1.2 was submitted in March, and version 1.3 has added some comments to make the code clearer.
 */
public class DogRUsApplication
{
    // instance variables
    private String kennelFilename;// holds the name of the kennel file
    private String catteryFilename; // holds the name of the cattery file
    private AnimalHousing kennel; // holds the kennel
    private AnimalHousing cattery; // holds the cattery
    private Scanner scan; // Scanner to read from the keyboard
    private TakeInput takeInput;

    // constructor that initialises all the instance variables
    private DogRUsApplication()
    {
        scan = new Scanner(System.in);
        takeInput = new TakeInput();
        // get the filenames of the files to be loaded
        System.out.println("Please enter the filename with the kennel information: ");
        kennelFilename = scan.nextLine();
        System.out.println("Please enter the filename with the cattery information: ");
        catteryFilename = scan.nextLine();

        // creates the kennel and cattery objects
        kennel = new AnimalHousing();
        cattery = new AnimalHousing();
    }

    
    // loads the data from both the files provided, adding it to the respective AnimalHousing objects
    private void initialise()
    {
        System.out.println("Using kennel file " + kennelFilename);
        System.out.println("Using cattery file " + catteryFilename);

        initialiseCattery();
        initialiseKennel();
    }
    
    // loads the kennel file
    private void initialiseKennel()
    {
        try
        {
            cattery.load(catteryFilename, AnimalType.CAT);
        } catch (FileNotFoundException e)
        {
            System.err.println("The cattery file: " + cattery + " does not exist. Assuming first use and an empty file."
                    + " If this is not the first use then have you accidentally deleted the file?");
        } catch (IOException e)
        {
            System.err.println("An unexpected error occurred when trying to open the file " + catteryFilename);
            System.err.println(e.getMessage());
        } catch (Exception e)
        {
            System.err.println("An unexpected error occurred when trying to open the file " + catteryFilename);
            System.err.println(e.getMessage());
        }
    }

    // loads the cattery file
    private void initialiseCattery()
    {
        try
        {
            kennel.load(kennelFilename, AnimalType.DOG);
        } catch (FileNotFoundException e)
        {
            System.err.println("The kennel file: " + kennelFilename + " does not exist. Assuming first use and an " +
                    "empty. file. If this is not the first use then have you accidentally deleted the file?");
        } catch (IOException e)
        {
            System.err.println("An unexpected error occurred when trying to open the file " + kennelFilename);
            System.err.println(e.getMessage());
        } catch (Exception e)
        {
            System.err.println("An unexpected error occurred when trying to open the file " + kennelFilename);
            System.err.println(e.getMessage());
        }
    }

    /* This method prints out the menu and handles the user input, and depending on that input takes the appropriate
    action - performing what the user requested */
    private void runMenu()
    {
        String response;
        // run the menu until the user decides to quit, by selecting the quit option 'Q'
        do
        {
            printMenu();
            // get the selection/response from the user
            System.out.println("What would you like to do:");
            response = scan.nextLine().toUpperCase();

            // perform the appropriate action relevant to the selection that was made
            switch (response)
            {
                case "1":
                    admitAnimal(AnimalType.DOG);
                    break;
                case "2":
                    admitAnimal(AnimalType.CAT);
                    break;
                case "3":
                    changeHousingName(AnimalType.DOG);
                    break;
                case "4":
                    changeHousingName(AnimalType.CAT);
                    break;
                case "5":
                    printAll(AnimalType.DOG);
                    break;
                case "6":
                    printAll(AnimalType.CAT);
                    break;
                case "7":
                    searchForAnimal(AnimalType.DOG);
                    break;
                case "8":
                    searchForAnimal(AnimalType.CAT);
                    break;
                case "9":
                    removeAnimal(AnimalType.DOG);
                    break;
                case "10":
                    removeAnimal(AnimalType.CAT);
                    break;
                case "11":
                    setHousingCapacity(AnimalType.DOG);
                    break;
                case "12":
                    setHousingCapacity(AnimalType.CAT);
                    break;
                case "Q":
                    break;
                default:
                    System.out.println("Invalid selection made, please try again");
            }
        } while (!(response.equals("Q")));
    }

    // prints the full menu - with all the possible options
    private void printMenu()
    {
        System.out.println("1 - Add a new Dog");
        System.out.println("2 - Add a new Cat");
        System.out.println("3 -  Set up/change Kennel name");
        System.out.println("4 -  Set up/change Cattery name");
        System.out.println("5 -  Display all Dogs in the Kennel");
        System.out.println("6 -  Display all Cats in the Cattery");
        System.out.println("7 -  Search for a Dog");
        System.out.println("8 -  Search for a Cat");
        System.out.println("9 -  Remove a Dog");
        System.out.println("10 -  Remove a Cat");
        System.out.println("11 -  Set Kennel capacity");
        System.out.println("12 - Set Cattery capacity");
        System.out.println("Q - Quit");
    }

    // used to set the housing capacity of catteries and kennels
    private void setHousingCapacity(AnimalType type)
    {
        if(type == AnimalType.DOG)
        {
            int max = takeInput.intInput("Enter max number of dogs: ");
            kennel.setCapacity(max);
        } else if(type == AnimalType.CAT)
        {
            int max = takeInput.intInput("Enter max number of cats: ");
            cattery.setCapacity(max);
        } else
        {
            System.err.println("Error: Invalid Animal Type entered");
        }
    }

    // prints out all the animals in the particular animal housing
    private void printAll(AnimalType type)
    {
        if(type == AnimalType.DOG)
        {
            System.out.println("Dogs in the Kennel (sorted in ascending order):");
            kennel.printOccupants();
        } else if(type == AnimalType.CAT)
        {
            System.out.println("Cats in the Cattery (sorted in ascending order):");
            cattery.printOccupants();
        } else
        {
            System.err.println("Error: Invalid Animal Type entered");
        }
    }

    // saves the information relevant to each animal housing - preserving the existing information
    private void save()
    {
        try
        {
            kennel.save(kennelFilename);
        } catch (IOException e)
        {
            System.err.println("Problem when trying to write to file: " + kennelFilename);
        } catch (Exception e)
        {
            System.err.println("Problem when trying to write to file: " + kennelFilename);
        }
        try
        {
            cattery.save(catteryFilename);
        } catch (IOException e)
        {
            System.err.println("Problem when trying to write to file: " + catteryFilename);
        } catch (Exception e)
        {
            System.err.println("Problem when trying to write to file: " + catteryFilename);
        }
    }

    // removes animals, depending on their name
    private void removeAnimal(AnimalType type)
    {
        System.out.println();
        if(type == AnimalType.DOG)
        {
            String animalToBeRemoved = takeInput.stringInput("Which dog do you want to remove?");
            kennel.removeAnimal(animalToBeRemoved);
        } else if(type == AnimalType.CAT)
        {
            String animalToBeRemoved = takeInput.stringInput("Which cat do you want to remove?");
            cattery.removeAnimal(animalToBeRemoved);
        } else
        {
            System.err.println("Error: Invalid Animal Type entered");
        }
    }

    // searches for an animal that is specified and outputs all the details relating to that animal
    private void searchForAnimal(AnimalType type)
    {
        String name = null;
        Animal animal = null;
        if(type == AnimalType.DOG)
        {
            name = takeInput.stringInput("Which dog do you want to search for?");
            animal = kennel.search(name);
        } else if(type == AnimalType.CAT)
        {
            name = takeInput.stringInput("Which cat do you want to search for?");
            animal = cattery.search(name);
        } else
        {
            System.err.println("Error: Invalid Animal Type entered");
        }

        if (animal != null)
        {
            System.out.println(animal.toString());
        } else
        {
            System.out.println("Could not find animal: " + name);
        }
    }

    // changes the name of the housing specified
    private void changeHousingName(AnimalType type)
    {
        if(type == AnimalType.DOG)
        {
            String name = takeInput.stringInput("Please enter a name for the Kennel:");
            kennel.setName(name);
        } else if(type == AnimalType.CAT)
        {
            String name = takeInput.stringInput("Please enter a name for the Cattery:");
            cattery.setName(name);
        } else
        {
            System.err.println("Error: Invalid Animal Type entered");
        }
    }

    // adds/admits an animal to a housing
    private void admitAnimal(AnimalType type)
    {
        if (type == AnimalType.DOG)
        {
            System.out.println("Please enter the following information about the dog:");
        } else if(type == AnimalType.CAT)
        {
            System.out.println("Please enter the following information about the cat:");
        } else
        {
            System.err.println("Error: Invalid Animal Type");
        }

        // get name of the animal from the user, making sure an empty string is not entered
        String name = takeInput.stringInput("Name:");
        // get dog's favourite food from the user, making sure an empty string is not entered
        String fav = takeInput.stringInput("What is his/her favourite food?");
        // find out how many times the dog is fed per day from the user
        int numFedPerDay = takeInput.positiveIntInput("How many times is he/she fed a day? (positive integer)");

        admitAnimalSpecificData(type, name, fav, numFedPerDay);
    }

    /* used to get animal specific data from the user, and create an instance of an animal, based on the information
    provided by the user */
    private void admitAnimalSpecificData(AnimalType type, String name, String fav, int numFedPerDay)
    {
        if (type == AnimalType.DOG)
        {
            // find out if the dog likes bones or not from the user, ensuring Y, Yes, N, or No has been entered
            boolean likesBones = takeInput.booleanInput("Does he/she like bones?");
            // find out if the dog needs walks or not from the user, ensuring Y, Yes, N, or No has been entered
            boolean needsWalks = takeInput.booleanInput("Does he/she need walks?");
            // initialise a Cat object to the values the user has provided
            Dog newDog = new Dog(name, fav, numFedPerDay, likesBones, needsWalks);
            // get the list of original owners from the user
            ArrayList<Owner> owners = getOwners();

            // assign each original owner provided by the user to the new dog
            for (Owner o : owners)
            {
                newDog.addOriginalOwner(o);
            }
            kennel.addAnimal(newDog, AnimalType.DOG);
        } else if(type == AnimalType.CAT)
        {
            // find out from the user if the cat can share a run or not, ensuring Y, Yes, N, or No has been entered
            boolean canShareRun = takeInput.booleanInput("Can the cat share runs with other cats?");
            // initialise a Cat object to the values the user has provided
            Cat newCat = new Cat(name, fav, numFedPerDay, canShareRun);
            // get the list of original owners from the user
            ArrayList<Owner> owners = getOwners();

            // assign each original owner provided by the user to the new cat
            for (Owner o : owners)
            {
                newCat.addOriginalOwner(o);
            }
            cattery.addAnimal(newCat, AnimalType.CAT);
        } else
        {
            System.err.println("Error: Invalid Animal Type");
        }
    }

    /* used to get the information of the original owners of the animal from the user, which is then added to the
    relevant animal when it is being admitted */
    private ArrayList<Owner> getOwners()
    {
        ArrayList<Owner> owners = new ArrayList<Owner>();
        boolean answer;
        // Get owner information until there are more owners to enter
        do
        {
            // Get owner's information from the user
            System.out.println("Enter the owner's information:");
            String ownName = takeInput.stringInput("Name:");
            String ownPhone = takeInput.stringInput("Phone Number:");
            // Create an owner object and initialise it with the values provided by the user
            Owner own = new Owner(ownName, ownPhone);
            owners.add(own);
            // Ask the user if they want to add more owners
            answer = takeInput.booleanInput("Another owner?");
        } while (answer);
        return (owners);
    }

    /**
     * Initialises the running of the application
     * @param args Command line arguments
     */
    public static void main(String args[])
    {
        System.out.println("**********HELLO***********");
        DogRUsApplication dogRUsApplication = new DogRUsApplication();
        dogRUsApplication.initialise();
        dogRUsApplication.runMenu();
        dogRUsApplication.printAll(AnimalType.DOG);
        dogRUsApplication.printAll(AnimalType.CAT);
        dogRUsApplication.save();
        System.out.println("***********GOODBYE**********");

        Dog d = new Dog();
        Cat c = new Cat();
    }
}