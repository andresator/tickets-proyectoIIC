
package ticketSystem_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

public class searchThread extends Thread{
    
    final DataInputStream dis;
    final Socket s;
      
    // Constructor
    public searchThread(Socket s, DataInputStream dis) {
        this.s = s;
        this.dis=dis;
    }
  
    @Override
    public void run(){
        String tiq, status, user, summry, prirty, phone,email,
                cMethod,description,assigned, search;
        while (true){
            
            try {
                search=dis.readUTF();
                System.out.print("Buscar "+search);
                
                this.dis.close();
    
            } catch (IOException ex) {
                System.out.print("Error desconocido");
                ex.getStackTrace();
            }          
        }
    }
    
}
