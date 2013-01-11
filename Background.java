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

import java.awt.*;

class Background extends Item
{
    Background (World world, int x, int y, int width, int height)
    {
        super(world, x, y, width, height);
    }
    
    void draw(Graphics2D g)
    {
        int abs_y = world.abs_y() - y();
        
        Color top = new Color(74, 139, 238);
        Color bottom = new Color(184, 206, 239);
        
        g.setColor(top);
        g.fillRect(0, 0, world.width, world.height);
        
        g.setPaint(new GradientPaint(0, abs_y + height, bottom, 0, abs_y, top));
        g.fillRect(0, abs_y, world.width, height);
    }
}
