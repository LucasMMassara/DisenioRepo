package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelText extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JLabel texto = new JLabel();
	
	PanelText(String text, int fontSize){
		
        texto.setText(text);

        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240,240,240));
		
        Font customFont = new Font("Arial", Font.PLAIN, fontSize);
        texto.setFont(customFont);

        GridBagConstraints gbc2 = new GridBagConstraints();
        //gbc2.anchor = GridBagConstraints.WEST;
        //gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 10, 0, 10);
        
        this.add(texto,gbc2);

	}
	
	PanelText(String text, String fontType, int fontSize){
		
		texto.setText(text);
		
		this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240,240,240));
		
        Font customFont = null;

        
        switch (fontType){
        
        case "PLAIN":
        	customFont  = new Font("Arial", Font.BOLD, fontSize);
        	break;
        
        case "BOLD": 
        	customFont  = new Font("Arial", Font.BOLD, fontSize);
        	break;
        	
        case "ITALIC":
        	customFont  = new Font("Arial", Font.ITALIC, fontSize);
        	break;
        	
        }
        
        texto.setFont(customFont);

        GridBagConstraints gbc2 = new GridBagConstraints();
		//gbc2.anchor = GridBagConstraints.WEST;
		//gbc2.fill = GridBagConstraints.BOTH;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 10, 0, 0);
        
        this.add(texto,gbc2);

	}
	
	PanelText(String text, String fontType, int fontSize, String position){
		
		texto.setText(text);
		
		this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240,240,240));
		
        Font customFont = null;

        
        switch (fontType){
        
        case "PLAIN":
        	customFont  = new Font("Arial", Font.PLAIN, fontSize);
        	break;
        
        case "BOLD": 
        	customFont  = new Font("Arial", Font.BOLD, fontSize);
        	break;
        	
        case "ITALIC":
        	customFont  = new Font("Arial", Font.ITALIC, fontSize);
        	break;
        	
        }
        
        texto.setFont(customFont);

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
        
		gbc2.fill = GridBagConstraints.NONE;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 10, 0, 0);
        
        this.add(texto,gbc2);

	}
	
	void setTextColor(int r, int g, int b) {
	
    texto.setForeground(new Color(r,g,b));
	
}
	
    void setBackgroundColor(int r, int g, int b, int opacity) {

        this.setBackground(new Color(r,g,b,opacity));

    }
    
    void setBackgroundColor(int r, int g, int b) {

        this.setBackground(new Color(r,g,b));

    }
	
	
}
