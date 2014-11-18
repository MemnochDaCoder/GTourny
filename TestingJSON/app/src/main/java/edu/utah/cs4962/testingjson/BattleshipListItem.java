package edu.utah.cs4962.testingjson;

/**
 * Created by ljohnson on 11/6/14.
 */
public class BattleshipListItem
{
    private int id;
    private String name;
    private Enum status;

    public BattleshipListItem(int id, String name, Enum status)
    {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Enum getStatus()
    {
        return status;
    }

    public void setStatus(Enum status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Battleship Game Info\nID: " + id + "\nGame Name=" + name + "\n" +
                "Status=" + status;
    }
}
