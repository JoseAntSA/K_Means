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
public class Centroide implements Comparable<Centroide>, Cloneable{
    private float x, y, dist, sumX, sumY;
    private int clase, cont;
    
    public Centroide(){
        clase = 0;
        sumX = 0;
        sumY = 0;
        cont = 0;
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
    
    public void setDist( float dist ){
        this.dist = dist;
    }//setDist
    public float getDist(){
      return dist;
    }//getDist
    
    public void setSumX( float sumX ){
        this.sumX = sumX;
    }//setSumX
    public float getSumX(){
      return sumX;
    }//getSumX
    
    public void setSumY( float sumY ){
        this.sumY = sumY;
    }//setSumy
    public float getSumY(){
      return sumY;
    }//getSumY
    
    public void setClase( int clase ){
        this.clase = clase;
    }//setClase
    public int getClase(){
      return clase;
    }//getClase
    
    public void setCont( int cont ){
        this.cont = cont;
    }//setCont
    public int getCont(){
      return cont;
    }//getCont

    public void inicializarSumas(){
        sumX = 0;
        sumY = 0;
        cont = 0;
    }
    //Metodo sobreescritos de la interfaz
    @Override
    public int compareTo( Centroide obj ){

        if(this.getDist() < obj.getDist()){
            return -1;
        }
        else if(this.getDist() > obj.getDist()){
            return 1;
        }
        else{
            return 0;
        }
    }
    @Override
     public Object clone(){
        Centroide obj = null;
        try{
            obj = (Centroide)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println(" no se puede duplicar");
        }
        return obj;
    }
 
 
    // incluye getters y setters restantes  
}
