/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fingerprints;

import fingerprints.FingerPrint.direction;
import gui.MainFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.*;

public class FingerPrintEngine implements MainFrameListener

{
       public static String s;
       String skey;
       

    FingerPrintEngine() {
      ///  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        //---------------------------------------------------------- CONSTANTS --//
        private class computeThread extends Thread
        {
                public void run()
                {
                    try {
                         compute();
                    } catch (IOException ex) {
                        Logger.getLogger(FingerPrintEngine.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FingerPrintEngine.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(FingerPrintEngine.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
        }
        
        //---------------------------------------------------------- VARIABLES --//     
        private MainFrame mainWindow;
        private String filename;
        private FingerPrint fingerprint;
        private computeThread thread;
        
        //------------------------------------------------------- CONSTRUCTORS --//     
        
        //------------------------------------------------------------ METHODS --//     
        
        public FingerPrintEngine(MainFrame mainWindow) 
        {
                this.mainWindow = mainWindow;
                thread = new computeThread();
                
        }
        
        public void close(MainFrame mainWindow){
             this.mainWindow = mainWindow;
            
        }
//        public  FingerPrintEngine( ){
//            
//            System.out.println("workinggggggggggggggggggggggggggg");
//             try{
//            s= compute();
//                 System.out.println("demooo key"+s);
//             }
//             catch(IOException ex){
//               Logger.getLogger(FingerPrintEngine.class.getName()).log(Level.SEVERE, null, ex);
//             }
////           return s;
//            
//        }
        
        //---------------------------------------------------- PRIVATE METHODS --//
ArrayList<Point> endPoints;
        private void compute() throws IOException, ClassNotFoundException, SQLException
        {
            database d= new database();
                // Disable buttons
                mainWindow.setEnableButtons(false);
        
                // Create binaryPicture
                fingerprint = new FingerPrint(filename);
                
                BufferedImage buffer;
                
                // Print original image
                mainWindow.setIsWorking(0, true);
                buffer = fingerprint.getOriginalImage();
                mainWindow.setImage(0, buffer);
                mainWindow.setIsWorking(0, false);
                
                // Print binary result
                mainWindow.setIsWorking(1, true);
                fingerprint.setColors(Color.black, Color.green);
                fingerprint.binarizeMean();
                buffer = fingerprint.toBufferedImage();
                mainWindow.setIsWorking(1, false);
                mainWindow.setImage(1, buffer);
                
                // Print binary local result
                mainWindow.setIsWorking(2, true);
                fingerprint.setColors(Color.black, Color.green);
                fingerprint.binarizeLocalMean();
                buffer = fingerprint.toBufferedImage();
                mainWindow.setIsWorking(2, false);
                mainWindow.setImage(2,buffer);
                
                // Remove noise
                mainWindow.setIsWorking(3, true);
                fingerprint.addBorders(1);
                fingerprint.removeNoise();
                fingerprint.removeNoise();
                fingerprint.removeNoise();
                buffer = fingerprint.toBufferedImage();
                mainWindow.setIsWorking(3, false);
                mainWindow.setImage(3,buffer);
                
                // Skeletonization
                mainWindow.setIsWorking(4, true);
                fingerprint.skeletonize();
                mainWindow.setIsWorking(4, false);
                mainWindow.setImage(4,fingerprint.toBufferedImage());
                
                // Direction
                mainWindow.setIsWorking(5, true);
                direction [][] dirMatrix = fingerprint.getDirections();
                buffer = fingerprint.directionToBufferedImage(dirMatrix);
                mainWindow.setIsWorking(5, false);
                mainWindow.setImage(5,buffer);
                
                // Core
                mainWindow.setIsWorking(6, true);
                buffer = fingerprint.directionToBufferedImage(dirMatrix);
                Point core = fingerprint.getCore(dirMatrix);
                mainWindow.setImage(6,buffer);
                int coreRadius = buffer.getWidth() / 3;
                mainWindow.setIsWorking(6, false);
                mainWindow.setCore(6,core, coreRadius);
                
                // Minutiaes
                mainWindow.setIsWorking(7, true);
                buffer = fingerprint.directionToBufferedImage(dirMatrix);
                
//                ImageIO.write(buffer,"jpg", file1);
                mainWindow.setCore(7,core, coreRadius);
                ArrayList<Point> intersections = fingerprint.getMinutiaeIntersections(core, coreRadius);
               endPoints = fingerprint.getMinutiaeEndpoints(core, coreRadius);
                
              
                
                
                
                
                
                
                // Add intersections
                Graphics g = buffer.getGraphics();
                g.setColor(Color.magenta);
                for (Point point : intersections)
                {
                        g.fillOval(point.x-2, point.y-2, 5, 5);
                }
                
                g.setColor(Color.blue);
                for (Point point : endPoints)
                {
                        g.fillOval(point.x-2, point.y-2, 5,5);
                }
                
                // Add to buffer
                mainWindow.setImage(7,buffer);
                 File file1=new File("D:\\java projects\\fingerprint\\hai.jpg");
                  ImageIO.write(buffer, "jpg", file1);
                mainWindow.setIsWorking(7, false);   
                
                
               
                
                
                
//              try 
//              {
//                     
//              } 
//              catch (IOException e) 
//              {
//                      // TODO ENLEVER TOUT
//                      e.printStackTrace();
//              }
              
              // Enable buttons
                mainWindow.setEnableButtons(true);
                 
                s = extract();
                
                System.out.println("skey"+s);
                int id=login.id;
                int r=d.Key(id, s);
               
                   mainWindow.setVisible(false);
          
        }
        int x1,y1,x2,y2,x3,y3,d12,d23,d31,i12,i23,i31;
        float t12,t23,t31,r12,r23,r31,al12,al23,al31,fv;
        String sv,d121,d231,d311,al121,al231,al311;
//        angle 
        public String extract(){
            for(int a=0;a<endPoints.size();a++){
//                for(int b=0;b<endPoints.size();b++){
//                    for(int c=0;c<=endPoints.size();c++){
//                        if(a!=b&&b!=c&&a!=c){
                Point demo = endPoints.get(a);
           x1=  demo.x;
              y1=demo.y;
             
             Point demo1=endPoints.get(a+55);
              x2=demo1.x;
              y2=demo1.y;
          
               
             Point demo2=endPoints.get(a+105);
              x3=demo2.x;
              y3=demo2.y;
//             System.out.println("endpoints"+x1+x2+x3+y1+y2+y3);
//              if(x1!=x2||x2!=x3||y1!=y2||y2!=y3){
             d12=(int)(Math.sqrt(((Math.pow((x1-x2),2))-(Math.pow((y1-y2),2))))) ;
               d23=(int)(Math.sqrt(((Math.pow((x2-x3),2))-(Math.pow((y2-y3),2))))) ;
                 d31=(int)(Math.sqrt(((Math.pow((x3-x1),2))-(Math.pow((y3-y1),2))))) ;
             
              System.out.println(a+"endpoints"+x1+" "+y1 +" "+x2+" "+y2+" "+x3+" "+y3+" "+d12+" "+d23+" "+d31);
              if(d12!=0&&d23!=0&&d31!=0){
                  System.out.println("index"+a+d12+" "+d23+" "+d31);  
                  i12=((d23*d23)+(d31*d31)-(d12*d12))/(2*d23*d31);
                  i23=((d31*d31)+(d12*d12)-(d23*d23))/(2*d31*d12);
                  i31=((d12*d12)+(d23*d23)-(d31*d31))/(2*d23*d12);
                  System.out.println("intermediate"+i12+" "+i23+" "+i31);
                  if((i12<(-1)) || (i12>1)){
                      i12=0;
                  }
                  if((i23<(-1)) || (i23>1)){
                      i23=0;
                  }
                  if((i31<(-1)) || (i31>1)){
                      i31=0;
                  }
                  System.out.println("test"+( Math.acos(1)*180/(Math.PI)));
                  t12 = (float) ((float)180- ((( Math.acos(i12)))*(180/Math.PI))) ;
                  
                  t23 = (float) ((float)180- ((( Math.acos(i23)))*(180/Math.PI))) ;
                  t31 = (float) ((float)180- ((( Math.acos(i31)))*(180/Math.PI))) ;
                  System.out.println("angles"+t12+" "+t23+" "+t31);
                 al12= (float) ((Math.atan((y1-y2)/(x1-x2)))-t12);
                  al23= (float) ((Math.atan((y2-y3)/(x2-x3)))-t23);
                  al31= (float) ((Math.atan((y3-y1)/(x3-x1)))-t31);
                  System.out.println("alll"+al12+" "+al23+" "+al31);
                  d121=d12+"";d231=d23+"";d311=d31+"";al121=al12+"";al231=al23+"";al311=t31+"";
//                  fv=d12+d23+d31+al12+al23+al31;
                  sv=d121+"_"+d231+"_"+d311+"_"+al121+"_"+al231+"_"+al311;
                  System.out.println("secretvalue"+sv);
                  break;
                  
                  
              }
//                System.out.println("index11111"+a+d12+" "+d23+" "+d31);  
              
                    }
//            }
            return sv;
                    }
//                   public static double angleBetween2Lines(Line2D line1, Line2D line2)
//{
//    double angle1 = Math.atan2(line1.getY1() - line1.getY2(),
//                               line1.getX1() - line1.getX2());
//    double angle2 = Math.atan2(line2.getY1() - line2.getY2(),
//                               line2.getX1() - line2.getX2());
//    return angle1-angle2;
//}

//                }
//            }
//        }
        
        @Override
        public void startExtraction() 
        {       
                thread = new computeThread();
                thread.start();
        }
        
        @Override
        public void newPictureFile(String filename) 
        {
                this.filename = filename;
                mainWindow.init();
        }

}