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

import java.awt.event.*;

class Controls implements KeyListener
{
    World world1, world2;
    
    Controls(World world1, World world2)
    {
        this.world1 = world1;
        this.world2 = world2;
    }
    
    public void keyTyped (KeyEvent e) {}
    
    public void keyPressed (KeyEvent e)
    {
        // Up
        switch (e.getKeyCode())
        {
            case 87:
                world1.player.key_up = true;
                break;
            case 38:
                world2.player.key_up = true;
                break;
            case 32:
                world1.player.key_up = true;
                world2.player.key_up = true;
                break;
        }
        
        // Left
        switch (e.getKeyCode())
        {
            case 65:
                world1.player.key_left = true;
                break;
            case 37:
                world2.player.key_left = true;
                break;
        }
        
        // Right
        switch (e.getKeyCode())
        {
            case 68:
                world1.player.key_right = true;
                break;
            case 39:
                world2.player.key_right = true;
                break;
        }
    }
    
    public void keyReleased (KeyEvent e)
    {
        // Up
        switch (e.getKeyCode())
        {
            case 87:
                world1.player.key_up = false;
                break;
            case 38:
                world2.player.key_up = false;
                break;
            case 32:
                world1.player.key_up = false;
                world2.player.key_up = false;
                break;
        }
        
        // Left
        switch (e.getKeyCode())
        {
            case 65:
                world1.player.key_left = false;
                break;
            case 37:
                world2.player.key_left = false;
                break;
        }
        
        // Right
        switch (e.getKeyCode())
        {
            case 68:
                world1.player.key_right = false;
                break;
            case 39:
                world2.player.key_right = false;
                break;
        }
    }
}
