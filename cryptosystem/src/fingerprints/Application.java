/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fingerprints;

import javax.swing.JFrame;
import javax.swing.UIManager;

import gui.MainFrame;

public class Application 
{
        //---------------------------------------------------------- CONSTANTS --//

        //---------------------------------------------------------- VARIABLES --//     
        private static MainFrame mainWindow;                            // Main window
        private static FingerPrintEngine fingerPrintEngine;     // Fingerprint engine
        
        //------------------------------------------------------- CONSTRUCTORS --//     

        //------------------------------------------------------------ METHODS --//
        /**
         * Launch the application
         * 
         * @param args application arguments
         */
        public static void main(String[] args)
        {
                // Set style
                setStyle();
            
                // Create the main frame
                mainWindow = new MainFrame();
                
                // Create objects
                fingerPrintEngine = new FingerPrintEngine(mainWindow);
                mainWindow.addMainFrameListener(fingerPrintEngine);     
                
                // Set full screen
                mainWindow.setExtendedState(mainWindow.getExtendedState()|JFrame.MAXIMIZED_BOTH);
                
                // Show the window
                mainWindow.setVisible(true);
        }
        
        //---------------------------------------------------- PRIVATE METHODS --//
        /**
         * Set the style of the application
         */
        private static void setStyle()
        {       
            try 
            {           
                UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackStarLookAndFeel");
            } 
            catch (Exception e) 
            {
                e.printStackTrace();
            }
        }
}