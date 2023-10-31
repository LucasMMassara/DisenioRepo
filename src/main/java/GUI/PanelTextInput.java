package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class PanelTextInput extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JTextField textField = new JTextField();

	
	PanelTextInput(int fontSize){
		
		Color borderColor = Color.GRAY;
        int borderThickness = 2;
        LineBorder customBorder = new LineBorder(borderColor, borderThickness);
        Insets insets = new Insets(3, 10, 3, 10);
        EmptyBorder emptyBorder = new EmptyBorder(insets);
        CompoundBorder compoundBorder = new CompoundBorder(customBorder, emptyBorder);
        textField.setBorder(compoundBorder);
		
		this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240,240,240));
		
        Font customFont = new Font("Arial", Font.PLAIN, fontSize);
        textField.setFont(customFont);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 10, 0, 10);
        
        this.add(textField,gbc2);
		
	}
	
	PanelTextInput(String text, int fontSize){
		
		textField.setText(text);
		
		Color borderColor = Color.GRAY;
        int borderThickness = 2;
        LineBorder customBorder = new LineBorder(borderColor, borderThickness);
        Insets insets = new Insets(3, 10, 3, 10);
        EmptyBorder emptyBorder = new EmptyBorder(insets);
        CompoundBorder compoundBorder = new CompoundBorder(customBorder, emptyBorder);
        textField.setBorder(compoundBorder);
		
		this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240,240,240));
		
        Font customFont = new Font("Arial", Font.PLAIN, fontSize);
        textField.setFont(customFont);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 10, 0, 10);
        
        this.add(textField,gbc2);
		
	}
	
	PanelTextInput(int fontSize, String  position){
		
		//textField.setText(text);
		
		Color borderColor = Color.GRAY;
        int borderThickness = 2;
        LineBorder customBorder = new LineBorder(borderColor, borderThickness);
        Insets insets = new Insets(3, 10, 3, 10);
        EmptyBorder emptyBorder = new EmptyBorder(insets);
        CompoundBorder compoundBorder = new CompoundBorder(customBorder, emptyBorder);
        textField.setBorder(compoundBorder);
		
		this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240,240,240));
		
        Font customFont = new Font("Arial", Font.PLAIN, fontSize);
        textField.setFont(customFont);

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
        
        this.add(textField,gbc2);
		
	}
	
	void setEditable(Boolean bool) {
		
		textField.setEditable(bool);
        textField.setForeground(new Color(150,150,150));
        textField.setBackground(new Color(250,250,250));

        Color borderColor = Color.LIGHT_GRAY;
        int borderThickness = 2;
        LineBorder customBorder = new LineBorder(borderColor, borderThickness);
        Insets insets = new Insets(3, 10, 3, 10);
        EmptyBorder emptyBorder = new EmptyBorder(insets);
        CompoundBorder compoundBorder = new CompoundBorder(customBorder, emptyBorder);
        textField.setBorder(compoundBorder);

		
		return;
		
	}
	
	void setTextColor(int r, int g, int b) {
		
        textField.setForeground(new Color(r,g,b));
		
	}
	
	void setBackgoundColor(int r, int g, int b) {
		
	    this.setBackground(new Color(r,g,b));
	    
	}
	
}
