/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio_mensajeria;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;

import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;
import javax.swing.text.BadLocationException;

/**
 *
 * @author pc
 */
public class MyFirst_DocumentFilter extends DocumentFilter {
 
    @Override
    public void replace(FilterBypass fb, int i, int i_2, String string_ex, AttributeSet A_s) throws BadLocationException{
        
        for (int n = string_ex.length(); n > 0; n--) {
            
            char c = string_ex.charAt(n - 1);
            
            if (Character.isAlphabetic(c) | c == ' ') {
                
                super.replace(fb, i, i_2, string_ex.valueOf(c), A_s);
                
            } else {
                
                JOptionPane.showMessageDialog(
                        null, "Solo se aceptan caracteres en el campo de nombre", "ERROR!", JOptionPane.ERROR_MESSAGE);

                
            }
            
        }
        
    }
    
    @Override
    public void remove(FilterBypass fb, int i, int i_2) throws BadLocationException{
        super.remove(fb, i_2, i_2);
        
    }
    
    @Override
    public void insertString(FilterBypass fb, int i, String string_ex, AttributeSet A_s) throws BadLocationException{
        super.insertString(fb, i, string_ex, A_s);
        
    }
    
    
}
