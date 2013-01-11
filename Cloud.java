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

import java.util.ArrayList;
import java.util.Random;
import java.util.Date;

class Cloud extends Item
{
    static ArrayList<BufferedImage> clouds = new ArrayList<BufferedImage>();
    
    Date date = new Date();
    static Random random;
    
    int cloud;
    
    Cloud (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (clouds.size() == 0)
        {
            for (int i = 0; i < 10; i++)
            {
                clouds.add(loadImage("cloud" + i + ".png"));
            }
            
            random = new Random(date.getTime());
        }
        
        cloud = random.nextInt(clouds.size());
    }
    
    BufferedImage loadImage(String s)
    {
        return super.loadImage("clouds/" + s);
    }
    
    void draw(Graphics2D g)
    {
        g.drawImage(clouds.get(cloud), x(), world.abs_y() - y(), null);
    }
}
