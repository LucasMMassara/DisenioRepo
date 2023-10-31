package GUI;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;


public class Boton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	Boton(){
		
		setCustomPressedColor(this, new Color(230,230,230));
		this.setFont(new Font("Arial", Font.PLAIN, 25));
		this.setForeground(new Color(75,75,75));
		this.setBackground(new Color(255,255,255));
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
	    this.setFocusable(false);
		
	}
	
	Boton(String text){
		
		this.setText("<html><div style='text-align: center; padding: 10px;'>" + text + "</div></html>");
        setCustomPressedColor(this, new Color(230,230,230));
		this.setFont(new Font("Arial", Font.PLAIN, 25));
		this.setForeground(new Color(75,75,75));
		this.setBackground(new Color(255,255,255));
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
	    this.setFocusable(false);
		
	}
	
	
	
	
	public static void setCustomPressedColor(JButton button, Color pressedColor) {
        button.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                AbstractButton b = (AbstractButton) c;
                ButtonModel model = b.getModel();

                if (model.isPressed() && model.isArmed()) {
                    g.setColor(pressedColor);
                    g.fillRect(0, 0, c.getWidth(), c.getHeight());
                }

                super.paint(g, c);
            }
        });
    }
	
}
