import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Container;

import java.util.Date;

class Play implements Runnable
{
    JFrame w;
    JPanel panel;
    
    World world1 = new World(500, 600);
    World world2 = new World(500, 600);
    
    Title title;
    Controls controls;
    
    boolean two_player = false;
    
    public static void main(String[] args)
    {
        Play program = new Play();
        SwingUtilities.invokeLater(program);
        program.animate();
    }
    
    public void run()
    {
        w = new JFrame();
        w.setDefaultCloseOperation(w.EXIT_ON_CLOSE);
        w.setTitle("Penguin Jump");
        w.setResizable(false);
        
        title = new Title(this, 500, 600);
        
        panel = new JPanel();
        panel.add(title);
        w.add(panel);
        
        w.pack();
        w.setLocationByPlatform(true);
        w.setVisible(true);
    }
    
    void show_world()
    {
        panel.remove(title);
        panel.add(world1);
        
        if (two_player)
        {
            world1.player.setPlayerImages(1);
            world2.player.setPlayerImages(2);
            panel.add(world2);
            controls = new Controls(world1, world2);
        }
        else
        {
            world1.player.setPlayerImages(0);
            controls = new Controls(world1, world1);
        }
        
        w.addKeyListener(controls);
        
        w.pack();
    }
    
    void animate()
    {        
        // Milliseconds
        long delta = 10, begin, end;
        Date date;
        
        try
        {
            while (true)
            {
                //System.out.println(1000 / (double) delta);
                
                date = new Date();
                begin = date.getTime();
                
                world1.update((double) delta / 1000);
                
                if (two_player)
                {
                    world2.update((double) delta / 1000);
                }
                
                date = new Date();
                end = date.getTime();
                
                if ((end - begin) < delta)
                {
                    Thread.sleep(delta - (end - begin));
                }
                
                delta = end - begin;
                
                // Minimum delta
                if (delta < 10)
                {
                    delta = 10;
                }

                // Maximum delta
                if (delta > 100)
                {
                    delta = 100;
                }
            }
        }
        catch (InterruptedException e)
        {
            System.err.println("Caught interrupt!");
        }
    }
}
