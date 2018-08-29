import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Animal class for implementing different types of animals (i.e. Cats and Dogs)
 * @author Nicolas Petras
 * @version 1.0
 */
public class Animal implements Comparable<Animal>
{
    // instance variables
    String name;
    ArrayList<Owner> originalOwners;
    String favFood;
    int foodPerDay;

    /**
     * Default Constructor - sets the default values to all instance variables
     */
    public Animal()
    {
        this.name = "No name";
        this.originalOwners = new ArrayList<>();
        this.favFood = "No favourite food";
        this.foodPerDay = 0;
    }

    /**
     * Constructor, initialises Animal object's instance variables
     * @param name Name of the animal
     * @param favFood Animal's favourite food
     * @param foodPerDay How many times a day is the animal fed
     */
    public Animal(String name, String favFood, int foodPerDay)
    {
        this.name = name;
        this.originalOwners = new ArrayList<>();
        this.favFood = favFood;
        this.foodPerDay = foodPerDay;
    }

    /**
     * Gets the name of the animal
     * @return The animal's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the animal
     * @param name Name of the animal
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns a copy of the original owners
     * @return A copy of the original owners as an array
     */
    public Owner[] getOriginalOwners()
    {
        Owner[] result = new Owner[originalOwners.size()];
        result = originalOwners.toArray(result);
        return result;
    }

    /**
     * Adds an original owner to the Animal
     * @param o An original owner
     */
    public void addOriginalOwner(Owner o)
    {
        originalOwners.add(o);
    }

    /**
     * Gets the animal's favourite food
     * @return Animal's favourite food
     */
    public String getFavFood()
    {
        return favFood;
    }

    /**
     * Sets the animal's favourite food
     * @param favFood Animal's favourite food
     */
    public void setFavFood(String favFood)
    {
        this.favFood = favFood;
    }

    /**
     * Gets the amount of times the animal is fed per day
     * @return Amount of times the animal is fed per day
     */
    public int getFoodPerDay()
    {
        return foodPerDay;
    }

    /**
     * Sets the amount of times the animal is fed per day
     * @param foodPerDay Amount of times the animal is fed per day
     */
    public void setFoodPerDay(int foodPerDay)
    {
        this.foodPerDay = foodPerDay;
    }


    /**
     * Compares if two Animals have the same name - only compares the names, no other attributes are considered
     * @param obj The other animal to compare against.
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Animal other = (Animal) obj;
        if (name == null)
        {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    /**
     * Loads Animal data from a file - creating new objects with the relevant states
     * @param infile The file scanner being used
     */
    public void load(Scanner infile)
    {
        name = infile.next();
        int numOwners = infile.nextInt();
        originalOwners = new ArrayList<>();
        for (int oCount = 0; oCount < numOwners; oCount++)
        {
            String name = infile.next();
            String phone = infile.next();
            Owner owner = new Owner(name, phone);
            originalOwners.add(owner);
        }
        foodPerDay = infile.nextInt();
        favFood = infile.next();
    }

    /**
     * Saves Animal data to a file
     * @param pw The print writer being used
     */
    public void save(PrintWriter pw)
    {
        pw.println(name);
        pw.println(originalOwners.size());
        for (Owner o : originalOwners)
        {
            pw.println(o.getName());
            pw.println(o.getPhone());
        }
        pw.println(foodPerDay);
        pw.println(favFood);
    }

    /**
     * Compares the name of the current Animal to another Animal (passed in as other)
     * @param other Other Animal being compared to the current Animal
     * @return
     */
    @Override
    public int compareTo(Animal other)
    {
        return name.compareTo(other.name);
    }

    /**
     * Returns the Animal's current state
     * @return Data (instance variables) associated with the current animal
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(400);
        sb.append("Name: ").append(name).append("\nOriginal Owners: ").append(originalOwners)
                .append("\nFavourite Food: ").append(favFood).append("\nFood per day: ")
                .append(foodPerDay).append('\n');
        return sb.toString();
    }
}
