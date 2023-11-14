package GUI;



import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;

public class PanelCheckBox extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    JCheckBox checkBox = new JCheckBox();
    
	PanelCheckBox(String text){
		
		checkBox.setText(text);
		checkBox.setFocusable(false);
		
	    GridBagConstraints gbc2 = new GridBagConstraints();
	    gbc2.fill = GridBagConstraints.BOTH;
		gbc2.weightx = 1;
		gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 10, 0, 10);
		
		
		this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240,240,240));        
        this.add(checkBox,gbc2);
		
	}
        
        public boolean isSelected(){
            
            return checkBox.isSelected();
            
        }
	
}
