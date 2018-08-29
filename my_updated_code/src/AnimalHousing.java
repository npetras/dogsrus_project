import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Used to create catteries, kennel, and possible other housing in the future
 * @author Lynda Thomas, Chris Loftus, and Nicolas Petras
 * @version 1.1
 * Version 1.0 (originally name 'Kennel') code was created by Lynda Thomas and Chris Loftus, but has been reworked
 * extensively by Nicolas Petras.
 */

public class AnimalHousing
{
    private String name;
    private ArrayList<Animal> animals;
    private int nextFreeLocation;
    private int capacity;
    private TakeInput takeInput;

    /**
     * Default Constructor - create AnimalHousing with a default size 20
     */
    public AnimalHousing()
    {
        this(20);
        takeInput = new TakeInput();
    }

    /**
     * Main Constructor - create a kennel initialises kennel's attributes
     * @param capacity The capacity of the kennel
     */
    public AnimalHousing(int capacity)
    {
        nextFreeLocation = 0; // no Animals in collection at start
        this.capacity = capacity;
        animals = new ArrayList<Animal>(capacity);
        takeInput = new TakeInput();
    }

    /**
     * Sets the name of the AnimalHousing
     * @param name Name of the Animal housing
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the value for the name attribute
     * @return String The name of the AnimalHousing
     */
    public String getName()
    {
        return name;
    }

    /**
     * Set the size of the Animal Housing
     * @param capacity The max animals that the housing can house
     */
    public void setCapacity(int capacity)
    {
        if (capacity < nextFreeLocation)
        {
            do
            {
                System.out.println("Invalid value entered: this capacity is lower than the current amount of animals" +
                        " housed in this housing");
                capacity = takeInput.intInput("Please enter a valid value, greater than or equal to " +
                        nextFreeLocation + ":");
            } while ( capacity < nextFreeLocation);
            this.capacity = capacity;
        }
    }

    /**
     * Obtain the maximum capacity of the animal housing
     * @return The max size of the animal housing
     */
    public int getCapacity()
    {
        return capacity;
    }

    /**
     * This method returns the number of animals in a kennel
     * @return Current number of animals in the kennel
     */
    public int getNumOfAnimals()
    {
        return nextFreeLocation;
    }


    /**
     * Enables a user to add a Animal to the Kennel, while specifying its type
     * @param animal New animal to add to the kennel
     * @param type   The type of animal being added (e.g. Cat, Dog)
     */
    public void addAnimal(Animal animal, AnimalType type)
    {
        // if the housing is full report an erro
        if (nextFreeLocation >= capacity)
        {
            switch (type)
            {
                case DOG:
                    System.out.println("Sorry the kennel is full - cannot add another dog");
                    break;
                case CAT:
                    System.out.println("Sorry the cattery is full - cannot add another cat");
                    break;
                default:
                    System.out.println("Error: Invalid animal type entered, please enter a valid type (Cat or Dog) ");
            }
        } else // if not then add the animal to the kennel
        {
            animals.add(animal);
            // now increment index ready for next one
            nextFreeLocation = nextFreeLocation + 1;
        }
    }

    /**
     * Enables a user to delete a Animal from the Housing.
     * @param who The animal to be removed
     */
    public void removeAnimal(String who)
    {
        Animal which = null; // animal to be removed
        // Search for the animal by name
        for (Animal a : animals)
        {
            if (who.equals(a.getName()))
            {
                which = a;
            }
        }
        if (which != null)
        {
            animals.remove(which);
            System.out.println("removed " + who);
            nextFreeLocation = nextFreeLocation - 1;
        } else
        {
            System.out.println("Cannot remove - this animal doesn't exist");
        }
    }

    @Override
    /**
     * @return String showing all the information in the animal housing
     */
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        result.append("Data in this Animal Housing ").append(name).append(" is: \n");
        for (Animal a : animals)
        {
            result.append(a.toString()).append("\n");
        }
        return result.toString();
    }

    /**
     * Returns an array of the inmates in this animal housing
     * @return An array of the correct size
     */
    public Animal[] obtainAllOccupants()
    {
        Animal[] result = new Animal[animals.size()];
        result = animals.toArray(result);
        return result;
    }


    /**
     * Searches for and returns the inmate, if found
     * @param name The name of the inmate
     * @return The inmate or else null if not found
     */
    public Animal search(String name)
    {
        for (Animal a : animals)
        {
            if (a.getName().equals(name))
            {
                return a;
            }
        }
        return null;
    }

    public void sort()
    {
        Collections.sort(animals);
    }

    /**
     * Reads in Kennel information from the file
     * @param infileName The file to read from
     * @throws FileNotFoundException    if file doesn't exist
     * @throws IOException              if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void load(String infileName, AnimalType type) throws IOException
    {
        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br))
        {
            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            name = infile.next();
            capacity = infile.nextInt();

            int numAnimals = infile.nextInt();
            for (int i = 0; i < numAnimals; i++)
            {
                if (type == AnimalType.DOG)
                {
                    Dog dog = new Dog();
                    dog.load(infile);
                    this.addAnimal(dog, AnimalType.DOG);
                } else if (type == AnimalType.CAT)
                {
                    Cat cat = new Cat();
                    cat.load(infile);
                    this.addAnimal(cat, AnimalType.CAT);
                } else
                {
                    System.err.println("Invalid animal type entered");
                }
            }
        }
    }

        /**
         * Saves the Animal Housing information
         * @param filename The file to save to
         * @throws IOException If some IO error occurs
         */
        public void save (String filename) throws IOException
        {
            // Again using try-with-resource so that I don't need to close the file explicitly
            try (FileWriter fw = new FileWriter(filename);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter outfile = new PrintWriter(bw);)
            {

                outfile.println(name);
                outfile.println(capacity);
                outfile.println(this.getNumOfAnimals());
                for (Animal a : animals)
                {
                    a.save(outfile);
                }
            }
        }

    /**
     * Prints out all the details of the occupants within this AnimalHousing
     */
    public void printOccupants()
        {
            this.sort();
            for(Animal a: animals)
            {
               System.out.println(a.toString());
            }
        }

}
