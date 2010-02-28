import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class Play implements Runnable
{        
    World world = new World(500, 600);
    final float FPS = 50;
    
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
        
        //Player player = new Player();        
        //w.addKeyListener(player);
        
        w.addKeyListener(world.player);
        w.add(world);
        
        w.pack();
        w.setLocationByPlatform(true);
        w.setVisible(true);
    }

    void animate()
    {
        // http://gafferongames.com/game-physics/fix-your-timestep/
        try {
            while (true)
            {
            //    System.out.println("Frame");
                world.update(1 / FPS);
                Thread.sleep(1000 / (int) FPS);
            }
        }
        catch (InterruptedException e)
        {
            System.err.println("Caught interrupt!");
        }
    }
}
