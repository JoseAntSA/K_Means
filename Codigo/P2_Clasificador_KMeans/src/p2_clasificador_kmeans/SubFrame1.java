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
import java.awt.event.*;

public class SubFrame1 extends JDialog{
	
    //Atributos
    private JPanel pnl1;
    private JLabel etqXmin, etqXmax, etqYmin, etqYmax, etqDatos, etqClases;
    private JLabel etqDivXmin, etqDivXmax, etqDivYmin, etqDivYmax, etqDivDatos, etqDivClases;
    private JTextField txtXmin, txtXmax, txtYmin, txtYmax, txtDatos, txtClases;
    private JButton btnOk;
    private boolean val;
    
    //Constructor
    public SubFrame1(JFrame padre){
        super(padre, true);
        setTitle("Inicializacion de datos");
        setSize(245,285);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponets();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public float getXmin(){
        return Float.valueOf(txtXmin.getText());
    }
    public float getXmax(){
        return Float.valueOf(txtXmax.getText());
    }
    public float getYmin(){
        return Float.valueOf(txtYmin.getText());
    }
    public float getYmax(){
        return Float.valueOf(txtYmax.getText());
    }
    public int getDatos(){
        return Integer.parseInt(txtDatos.getText());
    }
    public int getClases(){
        return Integer.parseInt(txtClases.getText());
    }
    public boolean getVal(){
        return val;
    }

    public void initComponets(){

        //CODIGO ATRIBUTOS
        Font fuenteLet2 = new Font( "Tw Cen MT", 1, 15 );
        Font fuenteLet = new Font( "Tw Cen MT", 1, 13 );
        Color colorFondo = new Color(51,53,58);
        Color colorLetra = new Color(155,158,158);
        Color colorBtn = new Color(31,33,38);
        UIManager.put("Label.font", fuenteLet);
        UIManager.put("Label.foreground", colorLetra);
        UIManager.put("TextField.Fond", fuenteLet2);
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("TextField.background", colorFondo);
        UIManager.put("TextField.border", BorderFactory.createLineBorder(colorFondo));
        UIManager.put("TextField.caretForeground", Color.WHITE);
        UIManager.put("OptionPane.background", colorFondo);
        UIManager.put("Panel.background", colorFondo);
        UIManager.put("OptionPane.messageForeground", colorLetra);

        //CODIGO PANEL CENTRAL
        pnl1 = new JPanel();
        this.getContentPane().add(pnl1);
        pnl1.setBackground(colorFondo);
        pnl1.setLayout(null);

    	//CODIGO X MINIMA
        txtXmin = new JTextField();
        txtXmin.setBounds(35,25,70,20);
        pnl1.add(txtXmin);

        etqDivXmin = new JLabel("__________");
        etqDivXmin.setBounds(35,30,75,20);
        pnl1.add(etqDivXmin);

        etqXmin = new JLabel("X min.");
        etqXmin.setBounds(35,46,75,20);
        pnl1.add(etqXmin);

        //CODIGO X MAXIMA
        txtXmax = new JTextField ();
        txtXmax.setBounds(135,25,70,20);
        pnl1.add(txtXmax);

        etqDivXmax = new JLabel("__________");
        etqDivXmax.setBounds(135,30,75,20);
        pnl1.add(etqDivXmax);
        
        etqXmax = new JLabel("X max.");
        etqXmax.setBounds(135,46,75,20);
        pnl1.add(etqXmax);
                
        //CODIGO Y MINIMA
        txtYmin = new JTextField ();
        txtYmin.setBounds(35,75,70,20);
        pnl1.add(txtYmin);

        etqDivYmin = new JLabel("__________");
        etqDivYmin.setBounds(35,80,75,20);
        pnl1.add(etqDivYmin);

        etqYmin = new JLabel("Y min.");
        etqYmin.setBounds(35,96,75,20);
        pnl1.add(etqYmin);

        //CODIGO Y MAXIMO
        txtYmax = new JTextField();
        txtYmax.setBounds(135,75,70,20);
        pnl1.add(txtYmax);

        etqDivYmax = new JLabel("__________");
        etqDivYmax.setBounds(135,80,75,20);
        pnl1.add(etqDivYmax);

        etqYmax = new JLabel("Y max.");
        etqYmax.setBounds(135,96,75,20);
        pnl1.add(etqYmax);

        //CODIGO DATOS
        txtDatos = new JTextField("100");
        txtDatos.setBounds(35,130,70,20);
        pnl1.add(txtDatos);

        etqDivDatos = new JLabel("__________");
        etqDivDatos.setBounds(35,135,75,20);
        pnl1.add(etqDivDatos);

        etqDatos = new JLabel("No. Datos");
        etqDatos.setBounds(35,151,75,20);
        pnl1.add(etqDatos);

        //CODIGO CLASES
        txtClases = new JTextField();
        txtClases.setBounds(135,130,70,20);
        pnl1.add(txtClases);

        etqDivClases = new JLabel("__________");
        etqDivClases.setBounds(135,135,75,20);
        pnl1.add(etqDivClases);

        etqClases = new JLabel("No. Clases");
        etqClases.setBounds(135,151,75,20);
        pnl1.add(etqClases);

        //CODIGO BOTON GUARDAR
        btnOk = new JButton("Aceptar");
        btnOk.setBounds(82,200,80,30);
        btnOk.setFont(fuenteLet);
        btnOk.setBackground(colorBtn);
        btnOk.setForeground(Color.WHITE);
        btnOk.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, colorLetra));
        btnOk.setFocusPainted(false);
        btnOk.addActionListener(new EventoBoton());

        pnl1.add(btnOk);
    }	

    //CODIGO EVENTO ACTION
    public class EventoBoton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent ev){
            val = true;
            if(txtXmin.getText().equals("") || txtXmax.getText().equals("") || txtYmin.getText().equals("")){
                val = false;
            }
            if(txtYmax.getText().equals("") || txtDatos.getText().equals("") || txtClases.getText().equals("")){
                val = false;
            }
            if(txtXmin.getText().equals("-") || txtXmax.getText().equals("-") || txtYmin.getText().equals("-") || txtYmax.getText().equals("-")){
                val = false;
            }
            if(!val){
                JOptionPane.showMessageDialog(null, "Existe un campo vacio o mal escrito","Inicializacion de atractores",JOptionPane.WARNING_MESSAGE);
            }if(val){ 
                if(getXmax() <= getXmin() || getYmax() <= getYmin()){
                    JOptionPane.showMessageDialog(null, "Los valores de X o Y son incorrectos","Valores incorrectos",JOptionPane.WARNING_MESSAGE);
                    val = false;
                }
                if( getDatos() <=0 || getClases() <= 0){
                    JOptionPane.showMessageDialog(null, "El no. de datos y clases debe ser mayor a cero","Valores incorrectos",JOptionPane.WARNING_MESSAGE);
                    val = false;
                }
                if( getDatos() < getClases() ){
                    JOptionPane.showMessageDialog(null, "El no. de datos debe ser mayor al no. de clases","Valores incorrectos",JOptionPane.WARNING_MESSAGE);
                    val = false;
                }
                if(val){
                    dispose();
                }
            }
        }//evento
    }//clase interna
}
