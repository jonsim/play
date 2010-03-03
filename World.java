import javax.swing.JPanel;
import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.util.Date;
import java.util.Random;

class World extends JPanel
{
    ArrayList<Item> foreground = new ArrayList<Item>();
    ArrayList<Item> background = new ArrayList<Item>();
    ArrayList<Launcher> launchers = new ArrayList<Launcher>();
    
    float abs_y = 0;
    
    int width;
    int height;
    
    int gravity = -1000;
    
    Player player;
    Hud hud;
    
    int cloud_height;
    int launcher_height;
    
    boolean new_game = false;
    
    World(int width, int height)
    {
        this.width = width;
        this.height = height;
        
        player = new Player(this, 226, 340);
        
        setPreferredSize(new Dimension(this.width, this.height));
        
        background.add(new Background(this, 0, 2400, 500, 2150));
        foreground.add(new Foreground(this, 0, 350, 500, 350));
        
        hud = new Hud(this, 20, 30);
        foreground.add(hud);
        
        cloud_height = 500;
        spawnClouds(cloud_height, cloud_height + height);
        
        //launcher_height = 415;
        launcher_height = 400;
        spawnLaunchers(launcher_height, launcher_height + height);
    }
    
    void update(double time_delta)
    {
        //System.out.print(items.size() + " " + background.size());
        
        if ((cloud_height - player.y) < 1200)
        {
            spawnClouds(cloud_height, cloud_height + height);
        }
        
        if ((launcher_height - player.y) < 600)
        {
            spawnLaunchers(launcher_height, launcher_height + height);
        }
        
        player.update(time_delta);
        
        Item item;
        Launcher launcher;
        Iterator background_iterator = background.iterator();
        
        while (background_iterator.hasNext())
        {
            item = (Item) background_iterator.next();
            item.update(time_delta);
            
            // Purge
            if (!(item instanceof Background) && (player.y - item.y) > 1500 && item.y > 900)
            {
                background_iterator.remove();
            }
        }
        
        Iterator launchers_iterator = launchers.iterator();
        
        while (launchers_iterator.hasNext())
        {
            launcher = (Launcher) launchers_iterator.next();
            launcher.update(time_delta);
            
            // Purge
            if (launcher.purge || (player.y - launcher.y) > 600)
            {
                launchers_iterator.remove();    
            }
            
            if (player.dying)
            {
                launcher.dying = true;
                launcher.fixed = false;
            }
        }
        
        repaint();
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        abs_y = height + (player.y() - (300 + player.height));
        
        Graphics2D canvas = (Graphics2D) g;
        
        Item item;
        Launcher launcher;
        
        for (Object o : background.toArray())
        {
            item = (Item) o;
            item.draw(canvas);
        }
        
        for (Object o : launchers.toArray())
        {
            launcher = (Launcher) o;
            
            try
            {
                launcher.draw(canvas);
            }
            catch (NullPointerException e) {}
        }
        
        player.draw(canvas);
        
        for (Object o : foreground.toArray())
        {
            item = (Item) o;
            
            try
            {
                item.draw(canvas);
            }
            catch (NullPointerException e) {}
        }
    }
    
    int abs_y()
    {
        return Math.round(abs_y);
    }
    
    void spawnLaunchers(int from, int to)
    {
        int x = 0;
        int y = 0;
        int px = 150;
        int py = from;
        int dx = 300;
        int dy;
        float c  = 0f;
        
        float height_from_start = (player.y() - player.y_initial) / 50;
        
        Date date = new Date();
        Random random = new Random(date.getTime());
        
        //dx = (int) Math.round(height_from_start * 0.25) + 50;
        dy = (int) Math.round(height_from_start * 0.15) + 75;
        if (dy > 180)
        {
            dy = 180;
        }
        
        while (py < to)
        {
            x = px + random.nextInt(dx * 2) - dx;
            if (x < 0) x = random.nextInt(dx);
            else if (x > 470) x = (width - dx - 30) + random.nextInt(dx);
            y = py  + random.nextInt(Math.round(dy + dy*c)) - Math.round(dy*c);
            
            int which_launcher = random.nextInt(200);
            
            if (which_launcher == 199)
            {
                launchers.add(new UberLauncher(this, x, y, 30, 15));
            }
            else if (which_launcher > 180)
            {
                launchers.add(new MegaLauncher(this, x, y, 30, 15));
            }
            else if (which_launcher > 160)
            {
                launchers.add(new MovingLauncher(this, x, y, 30, 15));
            }
            else
            {
                launchers.add(new Launcher(this, x, y, 30, 15));
            }   
            
            px = x;
            py = y;
        }    
        launcher_height = py;
    }
    
    void spawnClouds(int from, int to)
    {
        int gap = 200;
        
        Date date = new Date();
        Random random = new Random(date.getTime());
        
        int y = 0;
        
        for (int i = from + 100; i < (to - gap); i = y + gap)
        {
            // x = -130 to 490
            int x = -130 + random.nextInt(width + 120);
            y = i + random.nextInt(gap);
            
            background.add(new Cloud(this, x, y, 140, 100));
        }
        
        cloud_height = y;
    }
}
