import java.awt.event.*;

class Controls implements KeyListener
{
    World world1, world2;
    
    Controls(World world1, World world2)
    {
        this.world1 = world1;
        this.world2 = world2;
    }
    
    public void keyTyped (KeyEvent e)
    {
    }
    
    public void keyPressed (KeyEvent e)
    {
        // Up
        switch (e.getKeyCode())
        {
            case 87:
                world1.player.key_up = true;
                break;
            case 38:
                world2.player.key_up = true;
                break;
            case 32:
                world1.player.key_up = true;
                world2.player.key_up = true;
                break;
        }
        
        // Left
        switch (e.getKeyCode())
        {
            case 65:
                world1.player.key_left = true;
                break;
            case 37:
                world2.player.key_left = true;
                break;
        }
        
        // Right
        switch (e.getKeyCode())
        {
            case 68:
                world1.player.key_right = true;
                break;
            case 39:
                world2.player.key_right = true;
                break;
        }
    }
    
    public void keyReleased (KeyEvent e)
    {
        // Up
        switch (e.getKeyCode())
        {
            case 32:
                world1.player.key_up = false;
                break;
            case 38:
                world2.player.key_up = false;
                break;
            case 87:
                world1.player.key_up = false;
                world2.player.key_up = false;
                break;
        }
        
        // Left
        switch (e.getKeyCode())
        {
            case 65:
                world1.player.key_left = false;
                break;
            case 37:
                world2.player.key_left = false;
                break;
        }
        
        // Right
        switch (e.getKeyCode())
        {
            case 68:
                world1.player.key_right = false;
                break;
            case 39:
                world2.player.key_right = false;
                break;
        }
    }
}
