import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;

import java.awt.event.*;

class Title extends JPanel implements ActionListener
{
    BufferedImage title;
    
    Play w;
    
    int width;
    int height;
    
    JButton start;
    JButton start_2p;
    
    Title(Play w, int width, int height)
    {
        this.w = w;
        this.width = width;
        this.height = height;
        
        setPreferredSize(new Dimension(this.width, this.height));
        
        start = new JButton("1 Player");
        start_2p = new JButton("2 Player");
        
        start.setFocusable(false);
        start_2p.setFocusable(false);
        
        start.addActionListener(this);
        start_2p.addActionListener(this);
        
        setLayout(new BorderLayout());
        
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
        
        start.setFocusable(false);
        w.show_world();
    }
}