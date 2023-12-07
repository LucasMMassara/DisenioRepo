
package GUI;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.util.Date;
import java.util.Locale;
import javax.swing.JPanel;



public class PanelDatePicker extends JPanel{
   
    JDateChooser dateChooser = new JDateChooser();
    
    public PanelDatePicker() {
        
        dateChooser.setDateFormatString("yyyy-MM-dd");

        // Set the minimum date to January 1, 2020
        dateChooser.setMinSelectableDate(java.sql.Date.valueOf("2020-01-01"));

        // Set the maximum date to December 31, a future year (e.g., 2028)
        dateChooser.setMaxSelectableDate(java.sql.Date.valueOf("2028-12-31"));
       
        // Set the locale to Spanish
        dateChooser.setLocale(new Locale("sp", "SP"));
        
        dateChooser.setDateFormatString("dd MMMM yyyy"); // Customize the date format
        
        dateChooser.setBackground(Color.YELLOW); // Set the background color
        dateChooser.setForeground(Color.BLUE);   // Set the text color

        /*
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            Date selectedDate = dateChooser.getDate();
            if (selectedDate != null) {
                //System.out.println("Selected Date: " + selectedDate);
            } else {
                //System.out.println("No date selected.");
            }
        });
        */
        add(dateChooser);
        //add(submitButton);
        
        
    }
    
    public Date getDate(){
        return dateChooser.getDate();
    }
    
    public void setMinSelectableDate(String date){
        dateChooser.setMinSelectableDate(java.sql.Date.valueOf(date));
    }
    
    public void setMaxSelectableDate(String date){
        dateChooser.setMaxSelectableDate(java.sql.Date.valueOf(date));
    }
    
    public void setMinSelectableDate(Date date){
        dateChooser.setMinSelectableDate(date);
    }
    
    public void setMaxSelectableDate(Date date){
        dateChooser.setMaxSelectableDate(date);
    }
    
}
