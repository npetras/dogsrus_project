import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Cat class for cat objects, sub-class of Animal
 * @author Nicolas Petras
 * @version 1.0
 */
public class Cat extends Animal
{
    private boolean canShareRun; // Can the cat share a run with other cats, does the cat fight

    /**
     * Default constructor - sets default values for Cat instance variables
     */
    public Cat()
    {
        super();
        canShareRun = false;
    }

    /**
     * Main Constructor for Cat
     * @param name Name of the cat
     * @param favFood Cat's favourite food
     * @param foodPerDay Amount of the times the cat is fed per day
     * @param canShareRun Can the cat share runs with other cats (does the cat fight with other cats)
     */
    public Cat(String name, String favFood, int foodPerDay, boolean canShareRun)
    {
        super(name, favFood, foodPerDay);
        this.canShareRun = canShareRun;
    }

    /**
     * Gets the value of canShareRun
     * @return if the cat can share a run (true/false)
     */
    public boolean getCanShareRun()
    {
        return canShareRun;
    }

    /**
     * Sets if the cat share a run or not
     * @param canShareRun If the cat can share a run
     */
    public void setCanShareRun(boolean canShareRun)
    {
        this.canShareRun = canShareRun;
    }

    /**
     * Loads Cat data from a text file  (including cat specific data)
     * @param infile The file scanner being used
     */
    public void load(Scanner infile)
    {
        super.load(infile);
        canShareRun = infile.nextBoolean();
    }

    /**
     * Saves Cat data to a text file (including cat specific data)
     * @param pw The print writer being used
     */
    public void save(PrintWriter pw)
    {
        super.save(pw);
        pw.println(canShareRun);
    }

    /**
     * Returns the current state of the Cat
     * @return All data (instance variables) associated with the current Cat
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(250);
        sb.append(super.toString()).append("Can share a run: ").append(canShareRun).append('\n');
        return sb.toString();
    }
}