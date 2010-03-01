import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.util.Date;

class Play implements Runnable
{        
    World world = new World(500, 600);
    
    public static void main(String[] args)
    {
        Play program = new Play();
        SwingUtilities.invokeLater(program);
        program.animate();
    }

    public void run()
    {
        JFrame w = new JFrame();
        w.setDefaultCloseOperation(w.EXIT_ON_CLOSE);
        w.setTitle("Penguin");
        w.setResizable(false);
        
        w.addKeyListener(world.player);
        w.add(world);
        
        w.pack();
        w.setLocationByPlatform(true);
        w.setVisible(true);
    }

    void animate()
    {
        long delta = 20, begin, end; // milliseconds
        Date date;
        
        try {
            while (true)
            {                
                if (delta != 20)
                    System.out.println((1 / ((double) delta / 1000)) + " fps");
                
                date = new Date();
                begin = date.getTime();
                
                world.update((double) delta / 1000);
                
                date = new Date();
                end = date.getTime();
                
                if ((end - begin) < delta)
                {
                    Thread.sleep(delta - (end - begin));
                }
                
                delta = end - begin;
                
                // Minimum delta
                if (delta < 20)
                    delta = 20;
                
                // Maximum delta    
                if (delta > 100)
                    delta = 100;
            }
        }
        catch (InterruptedException e)
        {
            System.err.println("Caught interrupt!");
        }
    }
}
