/**
 * Represents Owners, which are associated with Animals
 * @author Lynda Thomas, Chris Loftus and Nicolas Petras
 * @version 1.2 (14th March 2018)
 * Version 1.1 code created by Lynda Thomas and Chris Loftus, but has been reworked by Nicolas Petras.
 */
public class Owner
{
    private String name;
    private String phone;

    /**
     * Main Constructor
     * @param name  Name of the owner
     * @param phone Phone number of the owner
     */
    public Owner(String name, String phone)
    {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Gets the name of the owner
     * @return Owner's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Sets the name of the owner
     * @param name Owner's name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the owner's phone number
     * @return Owner's phone number
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * Sets the owner's phone number
     * @param phone Owner's phone number
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * Checks if two owner objects are the same, comparing the name and phone number
     * @param obj Object to be compared to current object
     * @return If the two objects equal each other or not (true or false)
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
        Owner other = (Owner) obj;
        if (name == null)
        {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phone == null)
        {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        return true;
    }

    /**
     * Returns the current state of the Owner
     * @return All data (instance variables) associated with the current Owner
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder(80);
        sb.append(name).append(" ").append(phone);
        return sb.toString();
    }

}