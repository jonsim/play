import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;

import java.util.Date;
import java.util.Random;

class World extends JPanel
{
	ArrayList<Item> background = new ArrayList<Item>();
	ArrayList<Item> items = new ArrayList<Item>();
	
	float abs_y = 0;
	
	int width;
	int height;
	
	int gravity = -15;
	
	Player player;
	
	int cloud_height;
	int launcher_height;
	
	World(int width, int height)
	{
	    this.width = width;
	    this.height = height;
	    
	    // (500 - 48) / 2 = 226
	    // 300 + 48 = 348
	    player = new Player(this, 226, 348);
	    
	    setPreferredSize(new Dimension(this.width, this.height));
	    
	    background.add(new Background(this, 0, 1200, 500, 1200));
	    
	    cloud_height = height * 2;
        spawnClouds(500, cloud_height);
        
        launcher_height = height * 2;
        spawnLaunchers(400, launcher_height);
	}
	
	// Borked
	void remove(Item item)
	{
	    for (int i = 0; i < items.size(); i++)
	    {
	        if (items.get(i) == item) {
	            items.remove(i);
	            break;
	        }
	    }
	    
	    System.err.println("Item trying to remove doesn't exist in items array!");
	}
	
	void update(float time_delta)
	{	    
	    if ((cloud_height - player.y) < 600)
	    {
	        System.out.println("Updating clouds");
	        spawnClouds(cloud_height, cloud_height + (height * 2));    
	    }
	    
	    if ((launcher_height - player.y) < 600)
	    {
	        System.out.println("Updating launchers");
	        spawnLaunchers(launcher_height, launcher_height + (height * 2));    
	    }
	    
	    Item item;
	    Iterator background_iterator = background.iterator();
	    
	    while (background_iterator.hasNext())
	    {
	        item = (Item) background_iterator.next();
	        item.update(time_delta);
	    }
	    
	    Iterator items_iterator = items.iterator();
	    
	    while (items_iterator.hasNext())
	    {
	        item = (Item) items_iterator.next();
	        item.update(time_delta);
	    }
	    
	    player.update(time_delta);
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
	        
	        if (!item.dead)
	            item.draw(canvas);
	    }
	    
	    player.draw(canvas);
    }

	int abs_y()
	{
	    return Math.round(abs_y);
	}
	
	void spawnLaunchers(int from, int to)
	{	    
	    int gap = 200;
	    int n = 4;
	    
	    Date date = new Date();
	    Random random = new Random(date.getTime());
	    
	    int i;
	    for (i = from + 15; i < (to - gap); i += gap)
	    {    
	        for (int j = 0; j < n; j++)
	        {
	            // 15 = width of launcher
	            int x = random.nextInt(width - 30);
	            int y = i + random.nextInt(gap);
	            
	            items.add(new Launcher(this, x, y, 30, 15));
	        }
	    }
	    
	    launcher_height = i;
	}
	
	void spawnClouds(int from, int to)
	{
	    int gap = 200;
	    
	    Date date = new Date();
	    Random random = new Random(date.getTime());
	    
	    int y = 0;
	    
	    for (int i = from + 100; i < (to - gap); i = y + gap)
	    {    
            int x = -130 + random.nextInt(width + 120); // -130 to 490
            y = i + random.nextInt(gap);

            background.add(new Cloud(this, x, y, 140, 100));
	    }
	    
	    cloud_height = y;
	}
}