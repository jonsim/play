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

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

class Title extends JPanel implements ActionListener
{
    Play w;
    
    int width;
    int height;
    
    BufferedImage title;
    
    JButton start;
    JButton start_2p;
    
    Title(Play w, int width, int height)
    {
        this.w = w;
        this.width = width;
        this.height = height;
        
        setPreferredSize(new Dimension(this.width, this.height));
        setLayout(new BorderLayout());
        
        start = new JButton("1 Player");
        start_2p = new JButton("2 Player");
        
        start.setFocusable(false);
        start_2p.setFocusable(false);
        
        start.addActionListener(this);
        start_2p.addActionListener(this);
        
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.add(start);
        panel.add(start_2p);
        
        add(panel, BorderLayout.SOUTH);
        
        try
        {
            title = ImageIO.read(getClass().getResource("images/title.png"));
        }
        catch (IOException e)
        {
            System.err.println("Error reading image!");
            System.exit(1);
        }
    }
    
    public void paintComponent(Graphics g)
    {
        Graphics2D canvas = (Graphics2D) g;
        canvas.drawImage(title, 0, 0, null);
    }
    
    public void actionPerformed(ActionEvent e)
    {    
        if (e.getSource() == start)
        {
            w.two_player = false;
        }
        else
        {
            w.two_player = true;
        }
        
        w.showWorld();
    }
}
