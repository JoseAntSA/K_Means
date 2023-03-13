/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2_clasificador_kmeans;

/**
 *
 * @author anton
 */
public class Punto{

    private float x, y, dist;
    private int clase;
    
    public Punto(){
        clase = 0;
    }
    public void setPx( float x ){
        this.x = x;
    }//setPx
    public float getPx(){
      return x;
    }//getPx

    public void setPy( float y ){
        this.y = y;
    }//setPy
    public float getPy(){
      return y;
    }//getPy
   
    public void setClase( int clase ){
        this.clase = clase;
    }//setClase
    public int getClase(){
      return clase;
    }//getClase
    

 
 
    // incluye getters y setters restantes

}//Clase Review
