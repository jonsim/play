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

import java.awt.image.BufferedImage;

class MovingLauncher extends Launcher
{
    static BufferedImage fish4_left;
    static BufferedImage fish4_right;
    
    MovingLauncher(World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
        
        if (fish4_left == null)
        {
            fish4_left = loadImage("fish4_left.png");
            fish4_right = loadImage("fish4_right.png");
        }
    }
    
    int score()
    {
        return 10;
    }
    
    int multiplier()
    {
        return 2;
    }
    
    BufferedImage fish()
    {
        return direction ? fish4_right : fish4_left;
    }
    
    void update(double time_delta)
    {
        super.update(time_delta);
        
        if (!dying)
        {
            if (direction)
            {
                x += 100 * time_delta;
            }
            else
            {
                x -= 100 * time_delta;
            }
            
            if (x < 0)
            {
                x = 0;
                direction = true;
            }
            
            if (x > 470)
            {
                x = 470;
                direction = false;
            }
        }
    }
}
