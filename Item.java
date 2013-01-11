/*-----------------------------------------------------------------------*/
/*----------------------------    LICENSE    ----------------------------*/
/*-----------------------------------------------------------------------*/
/* This file is part of the Penguin Jump program (aka the play project)  */
/* (https://github.com/rupert/play).                                     */
/*                                                                       */
/* Penguin Jump is free software: you can redistribute it and/or modify  */
/* it under the terms of the GNU General Public License as published by  */
/* the Free Software Foundation, either version 3 of the License, or     */
/* (at your option) any later version.                                   */
/*                                                                       */
/* Penguin Jump is distributed in the hope that it will be useful,       */
/* but WITHOUT ANY WARRANTY; without even the implied warranty of        */
/* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         */
/* GNU General Public License for more details.                          */
/*                                                                       */
/* You should have received a copy of the GNU General Public License     */
/* along with Penguin Jump.  If not, see <http://www.gnu.org/licenses/>. */
/*-----------------------------------------------------------------------*/


/*-----------------------------------------------------------------------*/
/*----------------------------     ABOUT     ----------------------------*/
/*-----------------------------------------------------------------------*/
/* Penguin Jump is a 1 or 2 player arcade style game written in Java,    */
/* involving jumping between fish to reach the greatest height possible. */
/* Authors: Jonathan Simmonds and Rupert Bedford                         */
/*-----------------------------------------------------------------------*/

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

abstract class Item
{
    World world;
    
    // Position
    float x;
    float y;
    int width;
    int height;
    
    // Speed
    float x_speed = 0;
    float y_speed = 0;
    
    boolean purge = false;
    boolean fixed = true;
    
    Item(World world, int x, int y, int width, int height)
    {
        this.world = world;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    
    Item(World world, int x, int y)
    {
        this.world = world;
        this.x = x;
        this.y = y;
    }
    
    BufferedImage loadImage(String s)
    {
        try
        {
            return ImageIO.read(getClass().getResource("images/" + s));
        }
        catch (IOException e)
        {
            System.err.println("Error reading image!");
            System.exit(1);
        }
        
        return null;
    }
    
    abstract void draw(Graphics2D g);
    
    void update(double time_delta)
    {
        // Gravity
        if (!fixed)
        {
            y_speed += world.gravity * 0.5 * time_delta;
            
            y += y_speed * time_delta;
            
            // Remove items once they hit the ground
            if (y <= (300 + height))
            {
                purge = true;
            }
        }
    }
    
    int x()
    {
        return Math.round(x);
    }
    
    int y()
    {
        return Math.round(y);
    }
}
