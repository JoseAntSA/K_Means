package p2_clasificador_kmeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anton
 */
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

public class Plano{

    private JPanel pnl;
    private float xMin, xMax, yMin, yMax;
    private float vtX, vtY, spX, spY, xPix, yPix;
    private int w, h, centX, centY, sep;
    private ArrayList<Color> colores;

    //Constructor
    public Plano(JPanel pnl, float xMin, float xMax, float yMin, float yMax){
        this.pnl = pnl;
        sep = 50;
        w = pnl.getWidth()-sep;
        h = pnl.getHeight()-sep;
        this.xMin = -xMax;
        this.xMax = -xMin;
        this.yMin = yMin;
        this.yMax = yMax;
        calPix();
    }

    public float getCentX(){
        return centX;
    }
    public float getCentY(){
        return centY;
    }
    public float getPixX(){
        return xPix;
    }
    public float getPixY(){
        return yPix;
    }
    
    //Metodos
    public void calPix(){
        
        vtX = xMax - xMin;
        vtY = yMax - yMin;
        
        spX = vtX/10.0f;
        spY = vtY/10.0f;
    
        xPix = 1.0f/((float)w/vtX);
        yPix = 1.0f/((float)h/vtY);
        
        centX = (int)(xMax/xPix)+(sep/2);
        centY = (int)(yMax/yPix)+(sep/2);     
    }
    
    public void dibujarCartesiano(){
        Graphics2D g = (Graphics2D) pnl.getGraphics();
        g.setFont(new Font( "sans-serif", 0, 12 ));  
        g.setColor(new Color(230,230,230));
        
        int it;
            
        for(float i=centX; i<w+sep ; i+=(spX/xPix)){
            it = (int)Math.round(i);
            g.drawLine(it, 3, it, h-4+sep);
        }
        for(float i=centX ; i>0 ; i-=(spX/xPix)){
            it = (int)Math.round(i);
            g.drawLine(it, 3, it, h-4+sep);
        }
        for(float i=centY ; i<h+sep ; i+=(spY/yPix)){
            it = (int)Math.round(i);
            g.drawLine(3, it, w-4+sep, it);
        }
        for(float i=centY ; i>0 ; i-=(spY/yPix)){
            it = (int)Math.round(i);
            g.drawLine(3, it, w-4+sep, it);
        }

        g.setColor(Color.BLACK);

        g.drawLine(centX, 0, centX, h+sep);
        g.drawLine(0, centY, w+sep, centY); 
        
        int cont = 0;
        for(float i=centX ; i<w+sep ; i+=(spX/xPix)){
            it = (int)Math.round(i);
            g.drawString(String.format("%.2f",cont*spX), it+2,h-10+sep);
            cont++;
        }
        cont = 0;
        for(float i=centX ; i>0 ; i-=(spX/xPix)){
            it = (int)Math.round(i);
            g.drawString(String.format("%.2f",cont*spX), it+2,h-10+sep);
            cont--;
        }
        cont = 0;
        for(float i=centY ; i<h+sep ; i+=(spY/yPix)){
            g.drawString(String.format("%.2f",cont*spY*-1), 7,i-2);
            cont++;
        }
        cont = 0;
        for(float i=centY ; i>0 ; i-=(spY/yPix)){
            g.drawString(String.format("%.2f",cont*spY*-1), 7,i-2);
            cont--;
        }
    }

    public void dibujarPunto(float x, float y, int clase){
        Graphics2D g = (Graphics2D) pnl.getGraphics();

        g.setColor(colores.get(clase));//colores.get(clase));
        
        int xDigital, yDigital;
        
        xDigital = centX + (int)Math.round(x/xPix);
        yDigital = centY - (int)Math.round(y/yPix);

        //System.out.print("\n\n" + xPix + "  " + yPix);
        //System.out.print("\n" + xDigital + "  " + yDigital);
        
        g.fillOval(xDigital-3, yDigital-3, 6, 6); 
    }
    public void dibujarCentroide(float x, float y){
        Graphics2D g = (Graphics2D) pnl.getGraphics();

        g.setColor(Color.BLACK);
        g.setFont(new Font( "sans-serif", 1, 20 )); 
        
        int xDigital, yDigital;

        xDigital = centX + (int)Math.round(x/xPix);
        yDigital = centY - (int)Math.round(y/yPix);

        g.drawString("x",xDigital-5, yDigital+5);
    }
    
    public void limpiar(){
        Graphics2D g = (Graphics2D) pnl.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(3,4,w+sep-6,h+sep-8);
    }
    
    public void crearColores(int clases){
        colores = new ArrayList<>();
        int r, g, b;
        
        colores.add(Color.BLACK);
        colores.add(new Color(149,188,17));
        colores.add(new Color(250,121,4));
        colores.add(new Color(11,152,208));
        colores.add(new Color(12,66,100));
        colores.add(new Color(255,222,23));
        
        for(int i=0 ; i<clases; i++){
            r = (int)(Math.random()*255);       
            g = (int)(Math.random()*255);
            b = (int)(Math.random()*255);

            Color color = new Color(r,g,b);
            colores.add(color);
        }
    }
}	

