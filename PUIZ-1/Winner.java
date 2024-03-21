import greenfoot.*;
import greenfoot.Color;
public class Winner extends Actor
{
    public void addedToWorld(World MyWorld){
        GreenfootImage image=new GreenfootImage("You Win",100,Color.BLACK,null);
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
}
