import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * To model a Kennel - a collection of dogs
 *
 * @author Chris Loftus
 * @version 1.1 (26th February 2018)
 */
public class Kennel
{
    private String name;
    private ArrayList<Dog> dogs;
    private int nextFreeLocation;
    private int capacity;

    /**
     * Creates a kennel with a default size 20
     */
    public Kennel()
    {
        this(20);
    }

    /**
     * Create a kennel
     *
     * @param maxNoDogs The capacity of the kennel
     */
    public Kennel(int maxNoDogs)
    {
        nextFreeLocation = 0; // no Dogs in collection at start
        capacity = maxNoDogs;
        dogs = new ArrayList<Dog>(capacity); // set up default. This can
        // actually be exceeded
        // when using ArrayList but we
        // won't allow that
        // to happen.
    }

    /**
     * This method sets the value for the name attribute. The purpose of the
     * attribute is: The name of the kennel e.g. "DogsRUs"
     *
     * @param theName
     */
    public void setName(String theName)
    {
        name = theName;
    }

    /**
     * Set the size of the kennel
     *
     * @param capacity The max dogs we can house
     */
    public void setCapacity(int capacity)
    {
        // This should really check to see if we already have dogs
        // in the kennel and reducing the capacity would lead to evictions!
        this.capacity = capacity;
    }

    /**
     * Maximum capacity of the kennels
     *
     * @return The max size of the kennel
     */
    public int getCapacity()
    {
        return capacity;
    }

    /**
     * This method gets the value for the name attribute. The purpose of the
     * attribute is: The name of the Kennel e.g. "DogsRUs"
     *
     * @return String The name of the kennel
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method returns the number of dogs in a kennel
     *
     * @return int Current number of dogs in the kennel
     */
    public int getNumOfDogs()
    {
        return nextFreeLocation;
    }

    /**
     * Enables a user to add a Dog to the Kennel
     *
     * @param theDog A new dog to home
     */
    public void addDog(Dog theDog)
    {
        if (nextFreeLocation >= capacity)
        {
            System.out.println("Sorry kennel is full - cannot add team");
            return;
        }
        // we add in the position indexed by nextFreeLocation
        // This starts at zero
        dogs.add(theDog);

        // now increment index ready for next one
        nextFreeLocation = nextFreeLocation + 1;
    }

    /**
     * Enables a user to delete a Dog from the Kennel.
     *
     * @param who The dog to remove
     */
    public void removeDog(String who)
    {
        Dog which = null;
        // Search for the dog by name
        for (Dog d : dogs)
        {
            if (who.equals(d.getName()))
            {
                which = d;
            }
        }
        if (which != null)
        {
            dogs.remove(which); // Requires that Dog has an equals method
            System.out.println("removed " + who);
            nextFreeLocation = nextFreeLocation - 1;
        } else
        {
            System.err.println("cannot remove - not in kennel");
        }
    }

    /**
     * @return String showing all the information in the kennel
     */
    public String toString()
    {
        String results = "Data in Kennel " + name + " is: \n";
        for (Dog d : dogs)
        {
            results = results + d.toString() + "\n";
        }
        return results;
    }

    /**
     * Returns an array of the inmates in the kennels
     *
     * @return An array of the correct size
     */
    public Dog[] obtainAllInmates()
    {
        // ENTER CODE HERE (POSSIBLY CHANGE SOME, YOU MAY CHANGE THE SIGNATURE TO DEAL
        // WITH ALL KINDS OF ANIMALS: CATS AND DOGS)
        // SEE Dog.getOriginalOwners METHOD FOR SIMILAR CODE
        Dog[] result = null;
        return result;
    }


    /**
     * Searches for and returns the inmate, if found
     *
     * @param name The name of the inmate
     * @return The inmate or else null if not found
     */
    public Dog search(String name)
    {
        // ENTER CODE HERE (POSSIBLY CHANGE SOME, YOU MAY CHANGE THE SIGNATURE TO DEAL
        // WITH ALL KINDS OF ANIMALS: CATS AND DOGS)
        Dog result = null;

        return result;
    }

    /**
     * Reads in Kennel information from the file
     *
     * @param infileName The file to read from
     * @throws FileNotFoundException    if file doesn't exist
     * @throws IOException              if some other IO error occurs
     * @throws IllegalArgumentException if infileName is null or empty
     */
    public void load(String infileName) throws IOException
    {
        // Using try-with-resource. We will cover this in workshop 15, but
        // what it does is to automatically close the file after the try / catch ends.
        // This means we don't have to worry about closing the file.
        try (FileReader fr = new FileReader(infileName);
             BufferedReader br = new BufferedReader(fr);
             Scanner infile = new Scanner(br))
        {

            // Use the delimiter pattern so that we don't have to clear end of line
            // characters after doing a nextInt or nextBoolean
            infile.useDelimiter("\r?\n|\r");

            name = infile.next();
            capacity = infile.nextInt();

            int numDogs = infile.nextInt();
            for (int i = 0; i < numDogs; i++)
            {
                Dog dog = new Dog();
                dog.load(infile);
                this.addDog(dog);
            }
        }
    }

    /**
     * Saves the kennel information
     *
     * @param filename The file to save to
     * @throws IOException If some IO error occurs
     */
    public void save(String filename) throws IOException
    {
        // Again using try-with-resource so that I don't need to close the file explicitly
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter outfile = new PrintWriter(bw);)
        {

            outfile.println(name);
            outfile.println(capacity);
            outfile.println(this.getNumOfDogs());
            for (Dog d : dogs)
            {
                d.save(outfile);
            }
        }
    }
}
