import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Dog class for dog objects
 * @author Chris Loftus, Nicolas Petras
 * @version 1.2 (14th March 2018)
 * Version 1.1 code created by Chris Loftus, but has been reworked extensively by Nicolas Petras.
 */
public class Dog extends Animal
{
    // instance variables
    private boolean likesBones;
    private boolean needsWalks;

    /**
     * Default constructor that sets default values for the instance variables
     */
    public Dog()
    {
        this("unknown", "unknown", 1, false, false);
    }

    /**
     * Main Constructor for Dog
     * @param name          The dog's name
     * @param likeBones     Does the dog like bones?
     * @param favFood       The kind of food it eats
     * @param foodPerDay    Number of feeds per day
     */
    public Dog(String name, String favFood, int foodPerDay, boolean likeBones, boolean needsWalks)
    {
        super(name, favFood, foodPerDay);
        this.likesBones = likeBones;
        this.needsWalks = needsWalks;
    }

    /**
     * Gets if the dog likes bones or not
     * @return True or false value
     */
    public boolean getLikesBones()
    {
        return likesBones;
    }

    /**
     * Sets if the dog likes or dislikes bones
     * @param likesBones True/false value
     */
    public void setLikesBones(boolean likesBones)
    {
        this.likesBones = likesBones;
    }

    /**
     * Gets if the dog needs walks or not
     * @return A true/false value
     */
    public boolean getNeedsWalks()
    {
        return needsWalks;
    }

    /**
     * Sets if the dog needs walks or not
     * @param needsWalks True/false value
     */
    public void setNeedsWalks(boolean needsWalks)
    {
        this.needsWalks = needsWalks;
    }

    /**
     * Loads Cat data from a text file  (including cat specific data)
     * @param infile The file scanner being used
     */
    public void load(Scanner infile)
    {
        super.load(infile);
        likesBones = infile.nextBoolean();
        needsWalks = infile.nextBoolean();
    }

    /**
     *  Saves Dog data to a text file (including dog specific data)
     * @param pw The print writer being used
     */
    public void save(PrintWriter pw)
    {
        super.save(pw);
        pw.println(likesBones);
        pw.println(needsWalks);
    }

    /**
     * Returns the current state of the Dog
     * @return All data (instance variables) associated with the current Dog
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(300);
        sb.append(super.toString()).append("Likes Bones: ").append(likesBones).append("\nNeeds Walks: ")
                .append(needsWalks).append('\n');
        return sb.toString();
    }
}
