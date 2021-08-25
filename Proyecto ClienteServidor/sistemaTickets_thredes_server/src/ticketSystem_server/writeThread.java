
package ticketSystem_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JOptionPane;


public class writeThread extends Thread{
    
    final DataInputStream dis;
    final Socket s;
      
    // Constructor
    public writeThread(Socket s, DataInputStream dis) {
        this.s = s;
        this.dis = dis;
    }
  
    @Override
    public void run(){
        String tiq, status, user, summry, prirty, phone,email,
                cMethod,description,assigned;
        while (true){
            try {
                // receive the answer from client
                tiq = dis.readUTF();
                status = dis.readUTF();
                user = dis.readUTF();
                summry = dis.readUTF();
                prirty = dis.readUTF();
                phone = dis.readUTF();
                email = dis.readUTF();
                cMethod = dis.readUTF();
                description = dis.readUTF();
                assigned = dis.readUTF();
                
                DataOutputStream ticketWrite=new DataOutputStream(
                        new FileOutputStream("ticket.dat",true));
                ticketWrite.writeUTF(tiq);
                ticketWrite.writeUTF(status);
                ticketWrite.writeUTF(user);
                ticketWrite.writeUTF(summry);
                ticketWrite.writeUTF(prirty);
                ticketWrite.writeUTF(phone);
                ticketWrite.writeUTF(email);
                ticketWrite.writeUTF(cMethod);
                ticketWrite.writeUTF(description);
                ticketWrite.writeUTF(assigned);
                JOptionPane.showMessageDialog(null,
                        "¡Tiquete creado exitosamente!","Tiquete creado",
                        JOptionPane.INFORMATION_MESSAGE);
                ticketWrite.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            try {
                this.dis.close();;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }       
}
    
