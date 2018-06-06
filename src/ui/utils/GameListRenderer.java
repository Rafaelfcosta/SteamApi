/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.utils;

import gamesapi.GamesAPIController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Alisson Steffens
 */
public class GameListRenderer extends DefaultListCellRenderer{
    
    GamesAPIController controller;
    Font font = new Font("consolas", Font.PLAIN, 20);

    public GameListRenderer(GamesAPIController controller) {
        this.controller = controller;
    }
    
    
    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        label.setIcon(controller.getGameImagesMap().get((String) value));
        setBorder(new EmptyBorder(2,2,2,2));
        label.setBorder(new EmptyBorder(2,2,2,2));
        if(isSelected){
            setBackground(new Color(33, 50, 70));
        }
        
        label.setForeground(Color.WHITE);
        label.setHorizontalTextPosition(JLabel.RIGHT);
        label.setFont(font);
        return label;
    }
}