package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class PanelDropDown extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

         // Create a JComboBox with the list of items
	       JComboBox<String> comboBox = new JComboBox<>();
        
	PanelDropDown(String[] items){

		 // Create a JComboBox with the list of items
	       comboBox = new JComboBox<>(items);

	       // Create a custom renderer for the JComboBox
	       DefaultListCellRenderer renderer = new DefaultListCellRenderer() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
	           public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	               super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	               
	               if (isSelected) {
	                   setBackground(Color.GRAY);
	                   setForeground(Color.WHITE);
	               } else {
	                   setBackground(Color.WHITE);
	                   setForeground(Color.BLACK);
	               }
	               return this;
	           }
	           
	       };

	       comboBox.setRenderer(renderer);

	       // Create a custom ComboBoxUI to change the appearance of the dropdown button
	       comboBox.setUI(new BasicComboBoxUI() {
	           @Override
	           protected JButton createArrowButton() {
	               JButton button = new JButton();
	               button.setText("▼");
	               button.setForeground(Color.BLACK);
	               button.setBackground(Color.WHITE);
	               button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
	               return button;
	           }
	       });
	       
	       Color borderColor = Color.GRAY;
	       int borderThickness = 1;
	       LineBorder customBorder = new LineBorder(borderColor, borderThickness);
	       comboBox.setBorder(customBorder);
	       comboBox.setBackground(Color.WHITE);
	       
	       GridBagConstraints gbc2 = new GridBagConstraints();
	       
			gbc2.fill = GridBagConstraints.BOTH;
			gbc2.weightx = 1;
			gbc2.weighty = 1;
	        gbc2.insets = new Insets(0, 10, 0, 10);
	       
	       this.setLayout(new GridBagLayout());
	       this.setBackground(new Color(240,240,240));        
	       this.add(comboBox,gbc2);

	}
	
	PanelDropDown(String position, String[] items){

       // Create a JComboBox with the list of items
       comboBox = new JComboBox<>(items);

       // Create a custom renderer for the JComboBox
       DefaultListCellRenderer renderer = new DefaultListCellRenderer() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
           public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
               super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
               
               if (isSelected) {
                   setBackground(Color.GRAY);
                   setForeground(Color.WHITE);
               } else {
                   setBackground(Color.WHITE);
                   setForeground(Color.BLACK);
               }
               return this;
           }
           
       };

       comboBox.setRenderer(renderer);

       // Create a custom ComboBoxUI to change the appearance of the dropdown button
       comboBox.setUI(new BasicComboBoxUI() {
           @Override
           protected JButton createArrowButton() {
               JButton button = new JButton();
               button.setText("▼");
               button.setForeground(Color.BLACK);
               button.setBackground(Color.WHITE);
               button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 0));
               return button;
           }
       });
       
       Color borderColor = Color.GRAY;
       int borderThickness = 1;
       LineBorder customBorder = new LineBorder(borderColor, borderThickness);
       comboBox.setBorder(customBorder);
       comboBox.setBackground(Color.WHITE);
       		
       
       GridBagConstraints gbc2 = new GridBagConstraints();
       
       switch(position) {
       case "CENTER":
       	gbc2.anchor = GridBagConstraints.CENTER;
       	break;
       case "NORTH":
       	gbc2.anchor = GridBagConstraints.NORTH;
       	break;
       case "SOUTH":
       	gbc2.anchor = GridBagConstraints.SOUTH;
       	break;
       case "WEST":
       	gbc2.anchor = GridBagConstraints.WEST;
       	break;
       case "EAST":
       	gbc2.anchor = GridBagConstraints.EAST;
       	break; 	
       case "NORTHEAST":
       	gbc2.anchor = GridBagConstraints.NORTHEAST;
       	break;     	
       case "NORTHWEST":
       	gbc2.anchor = GridBagConstraints.NORTHWEST;
       	break;       	
       case "SOUTHEAST":
       	gbc2.anchor = GridBagConstraints.SOUTHEAST;
       	break;
       case "SOUTHWEST":
       	gbc2.anchor = GridBagConstraints.SOUTHWEST;
       	break;
       }
       
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 10, 0, 10);
       
       this.setLayout(new GridBagLayout());
       this.setBackground(new Color(240,240,240));        
       this.add(comboBox,gbc2);

	}
	
	void setBackgoundColor(int r, int g, int b) {
		
	    this.setBackground(new Color(r,g,b));
	    
	}
        
        String getSelectedItem(){
           return (String)comboBox.getSelectedItem();
        }
}
