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
    ArrayList<Item> items = new ArrayList<Item>();
    
    float abs_y = 0;
    
    int width;
    int height;
    
    int gravity = -1000;
    
    Player player;
    Hud hud;
    
    int cloud_height;
    int launcher_height;
    
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
        Iterator background_iterator = background.iterator();
        
        while (background_iterator.hasNext())
        {
            item = (Item) background_iterator.next();
            item.update(time_delta);
            
            // Purge
            if (!(item instanceof Background) && (player.y - item.y) > 600)
            {
                background_iterator.remove();
            }
        }
        
        Iterator items_iterator = items.iterator();
        
        while (items_iterator.hasNext())
        {
            item = (Item) items_iterator.next();
            item.update(time_delta);
            
            // Purge
            if (item.purge || (player.y - item.y) > 600)
            {
                items_iterator.remove();    
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
        
        for (Object o : background.toArray())
        {
            item = (Item) o;
            item.draw(canvas);
        }
        
        for (Object o : items.toArray())
        {
            item = (Item) o;
            
            try
            {
                item.draw(canvas);
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
        int dy = 180;
        float c  = 0f;
        
        float height_from_start = (player.y() - player.y_initial) / 50;
        
        Date date = new Date();
        Random random = new Random(date.getTime());
        
        //dx = (int) Math.round(height_from_start * 0.25) + 50;
        dy = (int) Math.round(height_from_start * 0.15) + 75;
        
        while (py < to)
        {
            x = px + random.nextInt(dx * 2) - dx;
            if (x < 0) x = random.nextInt(dx);
            else if (x > 470) x = (width - dx - 30) + random.nextInt(dx);
            y = py  + random.nextInt(Math.round(dy + dy*c)) - Math.round(dy*c);
            
            if (random.nextInt(200) == 0)
            {
                items.add(new UberLauncher(this, x, y, 30, 15));
            }
            else if (random.nextInt(40) == 0)
            {
                items.add(new MegaLauncher(this, x, y, 30, 15));
            }
            else if (random.nextInt(10) == 0)
            {
                items.add(new MovingLauncher(this, x, y, 30, 15));
            }
            else
            {
                items.add(new Launcher(this, x, y, 30, 15));
            }   
            
            px = x;
            py = y;
        }    
        launcher_height = py;
        
        /*
        int min_gap = 50;
        int max_gap = 195;
        
        int max_difficulty_height = 50000;
        int height_from_start = player.y() - player.y_initial;
        
        float difficulty = (float) height_from_start / max_difficulty_height;
        
        int gap = Math.round((difficulty * (max_gap - min_gap)) + min_gap);
        
        int n = 5;
        
        Date date = new Date();
        Random random = new Random(date.getTime());
        
        int i;
        int max_y = 0;
        
        for (i = from; i < (to - gap); i = max_y + gap)
        {
            for (int j = 0; j < n; j++)
            {
                int x = random.nextInt(width - 30);
                int y = i + random.nextInt(gap);
                
                if (y > max_y)
                {
                    max_y = y;
                }
                
                if (max_y > 25000 && random.nextInt(10) == 0)
                {
                    items.add(new UberLauncher(this, x, y, 30, 15));
                }
                else if (random.nextInt(200) == 0)
                {
                    items.add(new UberLauncher(this, x, y, 30, 15));
                }
                else if (random.nextInt(40) == 0)
                {
                    items.add(new MegaLauncher(this, x, y, 30, 15));
                }
                else
                {
                    items.add(new Launcher(this, x, y, 30, 15));
                }
            }
        }
        
        launcher_height = max_y + gap;*/
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
