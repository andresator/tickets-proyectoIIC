
package ticketSystem_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;

public class numberRequest{
    
    final DataOutputStream dos;
    final Socket s;
      
    // Constructor
    public numberRequest(Socket s, DataOutputStream dos) {
        this.s = s;
        this.dos=dos;  
    }
    
        public void generateTicketNum() {
        int tNumber=0;
        try {
            DataInputStream entrada = new DataInputStream(new FileInputStream(
                    "number.dat"));
            DataOutputStream salida = new DataOutputStream(new FileOutputStream(
                    "numberUpdate.dat"));
            try {
                while (true) {
                    tNumber = ((entrada.readInt())+1);
                    salida.writeInt(tNumber);    
                }
            }catch(EOFException ex){
                entrada.close();
                salida.close();
                intercambiar();
            }
        }catch(FileNotFoundException ex){
            JOptionPane.showMessageDialog(null,
                    "¡Archivo no encontrado!","Archivo no encontrado",
                    JOptionPane.ERROR_MESSAGE);
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,
                    "¡Error en el dispositivo!","Error en el dispositivo",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void intercambiar(){
        int tNumber;
        try{
           DataInputStream entrada=new DataInputStream(new FileInputStream
        ("numberUpdate.dat"));
        DataOutputStream salida=new DataOutputStream(new FileOutputStream
           ("number.dat"));
        try{
           while(true){
               tNumber=entrada.readInt();
               salida.writeInt(tNumber);
           }
           
        }catch(EOFException ex){
           entrada.close();
           salida.close();
        }   
      }catch(FileNotFoundException ex){
         JOptionPane.showMessageDialog(null,"¡No se encontró el archivo!",
                 "Archivo no encontrado",JOptionPane.ERROR_MESSAGE);
      }catch(IOException ex){
         JOptionPane.showMessageDialog(null,"¡Error en el dispositivo!",
                 "Error en el dispositivo",JOptionPane.ERROR_MESSAGE);
      }
    }
    
    public void postTicketNum(){
        generateTicketNum();
       int tNumber = 0;
        try{
           DataInputStream entrada=new DataInputStream(new FileInputStream
        ("number.dat"));
        try{
           while(true){
               tNumber=entrada.readInt();
               dos.writeInt(tNumber);
           }
           
        }catch(EOFException ex){
           entrada.close();
           this.dos.close();
        }   
      }catch(FileNotFoundException ex){
         JOptionPane.showMessageDialog(null,"¡No se encontró el archivo!",
                 "Archivo no encontrado",JOptionPane.ERROR_MESSAGE);
      }catch(IOException ex){
         JOptionPane.showMessageDialog(null,"¡Error en el dispositivo!",
                 "Error en el dispositivo",JOptionPane.ERROR_MESSAGE);
      }                
    }
}
