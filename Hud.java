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

class Hud extends Item
{
    int total_score = 0;
    
    Hud(World world, int x, int y)
    {
        super(world, x, y);
    }
    
    void addScore(int score)
    {
        total_score += score * multiplier();
    }
    
    int totalScore()
    {
        return Math.round(total_score);
    }
    
    int multiplier()
    {
        return Math.round((world.player.max_height - world.player.y_initial) / 500) + 1;
    }
    
    void draw(Graphics2D g)
    {
        g.setFont(new Font("Verdana", Font.BOLD, 24));
        g.setColor(new Color(35, 52, 73));
        g.drawString(totalScore() + " (" + ((world.player.max_height - world.player.y_initial) / 50) + "m)", x, y);
    }
}
