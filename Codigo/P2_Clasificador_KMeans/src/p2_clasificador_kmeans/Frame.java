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
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Frame extends JFrame{
	
    //Atributos
    private JFrame frame;
    private JPanel pnl1, pnl2;
    private JLabel etqTitle, etqDiv, etqK, etqDiv2;
    private JButton btnDatos, btnClasificar;
    private JRadioButton rdBtn;
    private JTextField txtK;
    private ArrayList<Punto> lista;
    private ArrayList<Centroide> centroides;
    private float xMin, xMax, yMin, yMax;
    private int noDatos, noClases=3, cont;
    private boolean val;
    private Plano pl;
    private EventoPanel mouse;

    //Constructor
    public Frame(){
        frame = this;
        mouse = new EventoPanel();;
        setTitle("Clasificador KNN");
        setSize(875,680);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponets();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addWindowListener(new EventoJFrame());
    }


    public void initComponets(){

        //CODIGO ATRIBUTOS
        Font fuenteEqt = new Font( "Tw Cen MT", 1, 26 );
        Font fuenteLet = new Font( "Tw Cen MT", 1, 13 );
        Color colorFondo = new Color(51,53,58);
        Color colorLetra = new Color(155,158,158);
        Color colorBtn = new Color(31,33,38);
        UIManager.put("OptionPane.background", colorFondo);
        UIManager.put("Panel.background", colorFondo);
        UIManager.put("OptionPane.messageForeground", colorLetra);
        UIManager.put("Button.font", fuenteLet);
        UIManager.put("Button.background", colorBtn);
        UIManager.put("Button.focus", colorBtn);
        UIManager.put("Button.foreground", Color.WHITE);

        //CODIGO PANEL PRINCIPAL
        pnl1 = new JPanel();
        this.getContentPane().add(pnl1);
        pnl1.setBackground(colorFondo);
        pnl1.setLayout(null);


        //CODIGO GRAFICA
        pnl2 = new JPanel();
        pnl2.setBackground(Color.WHITE);
        pnl2.setLayout(null);
        pnl2.setBounds(30,110,800,500);
        pnl2.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
        pnl1.add(pnl2);


        //CODIGO TITULO
        etqTitle = new JLabel("Clasificacion usando K-Means Clustering");
        etqTitle.setForeground(colorLetra);
        etqTitle.setBounds(30,30,800,20);
        etqTitle.setFont( fuenteEqt );
        pnl1.add(etqTitle);

        etqDiv = new JLabel();
        etqDiv.setBounds(30,55,800,3);
        etqDiv.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, colorLetra));
        pnl1.add(etqDiv);


        //CODIGO BOTON DATOS
        btnDatos = new JButton("Inicializar datos");
        btnDatos.setBounds(30,70,140,30);
        btnDatos.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, colorLetra)); 
        btnDatos.setFocusPainted(false);
        btnDatos.addActionListener(new EventoBotonInicio());
        pnl1.add(btnDatos);
        
        //CODIGO BOTON GUARDAR
        btnClasificar = new JButton("Clasificar");
        btnClasificar.setBounds(200,70,100,30);
        btnClasificar.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, colorLetra)); 
        btnClasificar.setFocusPainted(false);
        btnClasificar.addActionListener(new EventoBotonClasificar());
        btnClasificar.setEnabled(false);
        pnl1.add(btnClasificar);
    }
    
    //CODIGO EVENTO WINDOW
	public class EventoJFrame implements WindowListener{
	    public void windowActivated(WindowEvent e){
                //Evento nulo
	    }

	    public void windowClosed(WindowEvent e){
	        //Evento nulo
	    }

	    public void windowClosing(WindowEvent e){
	        //Evento nulo
	    }

	    public void windowDeactivated(WindowEvent e){
	        //Evento nulo
	    }

	    public void windowDeiconified(WindowEvent e){
	        //Evento nulo
	    }

	    public void windowIconified(WindowEvent e){
	     	//Evento nulo   
	    }

            @Override
	    public void windowOpened(WindowEvent e){
                String s = "1. Presione el boton 'Inicializar datos'.";
                s += "\n2. Introduza los limites de los ejes, el no. de datos";
                s += "\n    y el no. de clases para la clasificacion.";
                s += "\n3. Despues presione el boton 'Clasificar'.";
                JOptionPane.showMessageDialog(frame, s,"Modo de uso",JOptionPane.INFORMATION_MESSAGE);
                
                pl = new Plano(pnl2, -5, 5, -5, 5 );
                pl.dibujarCartesiano();
                
	    }
	}
    
    //CODIGO EVENTO ACTION
    public class EventoBotonInicio implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            SubFrame1 vtn = new SubFrame1(frame);
            
            if (vtn.getVal()){
                val = true;
                xMin = vtn.getXmin();
                xMax = vtn.getXmax();
                yMin = vtn.getYmin();
                yMax = vtn.getYmax();
                noDatos = vtn.getDatos();
                noClases = vtn.getClases();
            
                if(val){
                    vtn.dispose();
                    iniciarDatos(noDatos);
                    pl = new Plano(pnl2, xMin, xMax, yMin, yMax );
                    pl.limpiar();
                    pl.crearColores(noClases);
                    graficarDatos();

                    String s = "Seleccione " + noClases + " atractores";
                    s += "\ndando clic dentro de la grafica";
                    JOptionPane.showMessageDialog(frame, s,"Inicializacion de atractores",JOptionPane.INFORMATION_MESSAGE);

                    cont = 0;
                    centroides = new ArrayList<>();
                    pnl2.addMouseListener(mouse);

                    btnDatos.setEnabled(false);
                }
            }
        }//evento
    }//clase interna
    
    public class EventoBotonClasificar implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            btnClasificar.setEnabled(false);
            try {
                algoritmoKMeans();
            } catch (InterruptedException ex) {
                Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }//evento
    }//clase interna
        
    //CODIGO EVENTO MOUSE
    public class EventoPanel implements MouseListener{

        @Override
        public void mousePressed(MouseEvent e){
            //Se invoca cuando el botón del ratón fué pulsado.
            Centroide centro = new Centroide();
            
            centro.setPx( (e.getX()-pl.getCentX())*pl.getPixX() );
            centro.setPy( (-1)*(e.getY()-pl.getCentY())*pl.getPixY() );
            centro.setClase(cont+1);
            
            pl.dibujarCentroide(centro.getPx(), centro.getPy());
            centroides.add(centro);
            
            cont++;
            if(cont==noClases){
                pnl2.removeMouseListener(mouse);
                btnClasificar.setEnabled(true);
            }
            
        }
        @Override
        public void mouseReleased(MouseEvent e){
            //Se invoca cuando el botón del ratón fué soltado.
        }
        @Override
        public void mouseClicked(MouseEvent e){
            //Se invoca cuando el botón del ratón fué pulsado y soltado.
        }
        @Override
        public void mouseEntered(MouseEvent e){
            //Se invoca cuando el cursor del ratón entró a un componente.
        }      
        @Override
        public void mouseExited(MouseEvent e){
            //Se invoca cuando el cursor del ratón salió de un componente.
        }
    }//clase interna*/

    //METODOS 
    public void iniciarDatos( int nP ){
        //random()*(xMax-xMin)+xMin
        float x, y;
        Punto ptn;
        lista = new ArrayList<>();
        
        for(int i=0 ; i<nP ; i++ ){
            ptn = new Punto();
            
            x = (float)Math.random()*(xMax-xMin)+(xMin);
            y = (float)Math.random()*(yMax-yMin)+(yMin);

            ptn.setPx(x);
            ptn.setPy(y); 
            lista.add(ptn);
        }
    }
    public void graficarDatos( ){
        float x, y;
        int clase;
        
        pl.dibujarCartesiano();
        if(lista.size()>=80000){
            for(int i=0 ; i<80000 ; i++){
                x = lista.get(i).getPx();
                y = lista.get(i).getPy();
                clase = lista.get(i).getClase();

                pl.dibujarPunto(x,y,clase);
            }
        }else{
            for(Punto ptnTemp: lista){
                x = ptnTemp.getPx();
                y = ptnTemp.getPy();
                clase = ptnTemp.getClase();

                pl.dibujarPunto(x,y,clase);
            }
        }
        
        
    }
    
    public void algoritmoKMeans() throws InterruptedException{

        ArrayList<Centroide> tmp;
	float dist, x1, y1 ,x2 ,y2;
        int comp, iter=0;
        do{
            comp=0;
            iter++;
            for(Centroide cto: centroides){
                cto.inicializarSumas();
            }
            
            for(Punto ptn: lista){
        
                x1 = ptn.getPx();
                y1 = ptn.getPy();
                for(Centroide cto: centroides){
                    x2 = cto.getPx();
                    y2 = cto.getPy();
                    dist = (float)Math.sqrt( Math.pow((double)(x2-x1),2.0) + Math.pow((double)(y2-y1),2.0) );
                    cto.setDist(dist);
                }
                Collections.sort(centroides);
                ptn.setClase(centroides.get(0).getClase());

                centroides.get(0).setSumX(centroides.get(0).getSumX()+x1);
                centroides.get(0).setSumY(centroides.get(0).getSumY()+y1);
                centroides.get(0).setCont(centroides.get(0).getCont()+1);
            }

            tmp = cloneArrayList(centroides);

            for(Centroide cto: centroides){
                cto.setPx(cto.getSumX()/((float)cto.getCont()));
                cto.setPy(cto.getSumY()/((float)cto.getCont()));
            }
            pl.limpiar();
            for(int i=0 ; i<noClases ; i++){
                //System.out.print("\n"+tmp.get(i).getPx() + " " + centroides.get(i).getPx());
                if(Float.compare(tmp.get(i).getPx(),centroides.get(i).getPx())!=0){
                    comp++;
                }
                else if(Float.compare(tmp.get(i).getPy(),centroides.get(i).getPy())!=0){
                    comp++;
                }
                pl.dibujarCentroide(tmp.get(i).getPx(), tmp.get(i).getPy());
            
            }
            if(iter<10){
                graficarDatos();
                Thread.sleep(2000);
            }
            
        }while(comp!=0);
        
        graficarDatos();
        Thread.sleep(2000);
        
        String s = "La clasificacion ha terminado,";
        s += "\nse necesitaron " + iter + " iteraciones.";
        
        JOptionPane.showMessageDialog(frame, s,"Proceso terminado",JOptionPane.INFORMATION_MESSAGE);
        btnDatos.setEnabled(true);
    }
    
    public ArrayList<Centroide> cloneArrayList(ArrayList<Centroide> orig){
        ArrayList<Centroide> clon = new ArrayList<>(orig.size());
        for(Centroide item: orig){
            clon.add((Centroide)item.clone()); 
        }
        return clon; 
    }

    
}

