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
	
	float abs_x = 0;
	float abs_y = 0;
	
	int width;
	int height;
	
	int gravity = -15;
	
	Player player;
	
	World(int width, int height)
	{
	    this.width = width;
	    this.height = height;
	    
	    // (500 - 48) / 2 = 226
	    // 300 + 48 = 348
	    player = new Player(this, 226, 348);
	    
	    setPreferredSize(new Dimension(this.width, this.height));
	    
	    background.add(new Background(this, 0, 1200, 500, 1200));
	    
	    spawnClouds();
	    spawnLaunchers();
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
        
        //abs_x = x;
        abs_y = height + (player.y() - (300 + player.height));

        Graphics2D canvas = (Graphics2D) g;
        
        Item item;
        
        Iterator background_iterator = background.iterator();
	    
	    while (background_iterator.hasNext())
	    {
	        item = (Item) background_iterator.next();
	        item.draw(canvas);
	    }
        
        Iterator items_iterator = items.iterator();
	    
	    while (items_iterator.hasNext())
	    {	        
	        item = (Item) items_iterator.next();
	        
	        if (!item.dead)
	            item.draw(canvas); 
	    }
	    
	    player.draw(canvas);
    }

    int abs_x()
	{
	    return Math.round(abs_x);
	}

	int abs_y()
	{
	    return Math.round(abs_y);
	}
	
	void spawnLaunchers()
	{	    
	    int gap = 200;
	    int n = 4;
	    
	    Date date = new Date();
	    Random random = new Random(date.getTime());
	    
	    // 300 + 100 = 400
	    for (int i = 400; i < (1200 - gap); i += gap)
	    {    
	        for (int j = 0; j < n; j++)
	        {
	            // 15 = width of launcher
	            int x = random.nextInt(width - 15);
	            int y = i + random.nextInt(gap);
	            
	            items.add(new Launcher(this, x, y, 15, 15));
	        }
	    }
	}
	
	void spawnClouds()
	{
	    int gap = 200;
	    
	    Date date = new Date();
	    Random random = new Random(date.getTime());
	    
	    for (int i = 500; i > (1200 - gap); i += gap)
	    {    
            int x = random.nextInt(width + 140) - 139; // Check this
            int y = i + random.nextInt(gap);
            
            background.add(new Cloud(this, x, y, 140, 100));
	    }
	}
}