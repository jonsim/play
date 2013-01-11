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

import java.util.Date;
import java.util.Random;

abstract class Launcher extends Item
{
    static BufferedImage fish_dead_left;
    static BufferedImage fish_dead_right;
    
    static Random random;
    boolean direction;
    
    float life = 1;
    boolean dying = false;
    
    Launcher(World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (random == null)
        {
            Date date = new Date();
            random = new Random(date.getTime());
        }
        
        if (fish_dead_left == null)
        {
            fish_dead_left = loadImage("fish_dead_left.png");
            fish_dead_right = loadImage("fish_dead_right.png");
        }
        
        direction = random.nextBoolean();
    }
    
    BufferedImage loadImage(String s)
    {
        return super.loadImage("launchers/" + s);
    }
    
    abstract int multiplier();
    
    abstract int score();
    
    abstract BufferedImage fish();
    
    BufferedImage fishDead()
    {
        return direction ? fish_dead_right : fish_dead_left;
    }
    
    void draw(Graphics2D g)
    {
        BufferedImage fish = dying ? fishDead() : fish();
        g.drawImage(fish, x(), world.abs_y() - y(), null);
    }
    
    void update(double time_delta)
    {
        super.update(time_delta);
        
        if (dying)
        {
            life -= time_delta;
            
            if (life <= 0)
            {
                purge = true;
            }
        }
        else if (collision())
        {
            launchPlayer();
            world.hud.addScore(score());
            
            dying = true;
            fixed = false;
        }
    }
    
    void launchPlayer()
    {
        if ((world.player.VPPS * multiplier()) > world.player.y_speed)
        {
            world.player.y_speed = (world.player.VPPS * multiplier()); 
        }
    }
    
    boolean collision()
    {
        Player p = world.player;
        
        int t = p.y();
        int b = p.y() + p.height;
        int l = p.x();
        int r = p.x() + p.width;
        
        if (inShape(x(), y(), t, b, l, r))
        {
            return true;
        }
        
        if (inShape(x() + width, y(), t, b, l, r))
        {
            return true;
        }
        
        if (inShape(x(), y() + height, t, b, l, r))
        {
            return true;
        }
        
        if (inShape(x() + width, y() + height, t, b, l, r))
        {
            return true;
        }
        
        return false;
    }
    
    boolean inShape (int x, int y, int top, int bottom, int left, int right)
    {
        return (x > left && x < right) && (y > top && y < bottom);
    }
}
