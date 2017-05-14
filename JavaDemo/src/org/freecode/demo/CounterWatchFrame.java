package org.freecode.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CounterWatchFrame extends JFrame{
    private CounterWatch theWatch = null;

    public static void main(String[] args) {
        CounterWatchFrame frm = new CounterWatchFrame();
        frm.setVisible(true);
    }
    
    public CounterWatchFrame() {
        setTitle("Counter Watch");
        setSize(640, 480);
        setLocation(10, 10);
        init();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void init() {
        theWatch = new CounterWatch();
        
        JPanel panel = new JPanel();
        final JLabel label = new JLabel();
        panel.add(label);
        
        JButton btnCountDown = new JButton("Count Down");
        btnCountDown.addActionListener(new ActionListener() {
           public void actionPerformed( ActionEvent e ) {
               theWatch.countDown( label);
           }
        });
        panel.add(btnCountDown);
        
        JButton btnCountUp = new JButton("Count Up");
        btnCountUp.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                theWatch.countUp(label);
            }
         });
        panel.add(btnCountUp);
        
        JButton btnStop = new JButton("Stop");
        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed( ActionEvent e ) {
                theWatch.stop();
            }
        });
        panel.add(btnStop);
        add(panel);
    }
}
