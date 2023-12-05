/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author valec
 */
public class PanelTextInputNroCliente extends JPanel{
    
    int borderThickness = 2;
    Insets insets = new Insets(3, 10, 3, 10);
    EmptyBorder emptyBorder = new EmptyBorder(insets);

    JFormattedTextField formattedTextField;
    
    private static final long serialVersionUID = 1L;
    
    PanelTextInputNroCliente(int fontSize){
        


        this.setLayout(new GridBagLayout());
        this.setBackground(new Color(240, 240, 240));
        
        MaskFormatter formatter;
            try {
                formatter = new MaskFormatter("##-########");
                formatter.setPlaceholderCharacter('0');
            } catch (ParseException e) {
                e.printStackTrace();
                return;
            }

        formattedTextField = new JFormattedTextField(formatter);
        formattedTextField.setColumns(15);
        
        // Customize the font
        Font customFont = new Font("Arial", Font.PLAIN, fontSize);
        formattedTextField.setFont(customFont);

        Color borderColor = Color.GRAY;
        LineBorder customBorder = new LineBorder(borderColor, borderThickness);
        CompoundBorder compoundBorder = new CompoundBorder(customBorder, emptyBorder);
        formattedTextField.setBorder(compoundBorder);
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.fill = GridBagConstraints.BOTH;
        gbc2.weightx = 1;
        gbc2.weighty = 1;
        gbc2.insets = new Insets(0, 0, 0, 0);

        this.add(formattedTextField, gbc2);
        
    }
    
    String getText() {
        return formattedTextField.getText();
    }

    void setWrongInput() {

        Color borderColor = Color.RED;
        LineBorder customBorder = new LineBorder(borderColor, borderThickness);
        CompoundBorder compoundBorder = new CompoundBorder(customBorder, emptyBorder);
        formattedTextField.setBorder(compoundBorder);

    }

    void setCorrectInput() {

        Color borderColor = Color.GRAY;
        LineBorder customBorder = new LineBorder(borderColor, borderThickness);
        CompoundBorder compoundBorder = new CompoundBorder(customBorder, emptyBorder);
        formattedTextField.setBorder(compoundBorder);

    }
    
    void vaciar(){
        formattedTextField.setValue(null);
    }
       
}