
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

public class readTicketThread extends Thread{
    
    final DataOutputStream dos;
    final Socket s;
      
    // Constructor
    public readTicketThread(Socket s, DataOutputStream dos) {
        this.s = s;
        this.dos=dos;  
    }
  
    @Override
    public void run(){
        String tiq, status, user, summry, prirty, phone,email,
                cMethod,description,assigned;
        while (true){
            try {
            DataInputStream rTicket = new DataInputStream(
                    new FileInputStream("ticket.dat"));
            try {
                while (true) {
                    tiq = rTicket.readUTF();
                    status = rTicket.readUTF();
                    user = rTicket.readUTF();
                    summry = rTicket.readUTF();
                    prirty = rTicket.readUTF();
                    phone = rTicket.readUTF();
                    email = rTicket.readUTF();
                    cMethod = rTicket.readUTF();
                    description = rTicket.readUTF();
                    assigned = rTicket.readUTF();
                    
                    dos.writeUTF(tiq);
                    dos.writeUTF(status);
                    dos.writeUTF(user);
                    dos.writeUTF(summry);
                    dos.writeUTF(prirty);
                    dos.writeUTF(phone);
                    dos.writeUTF(email);
                    dos.writeUTF(cMethod);
                    dos.writeUTF(description);
                    dos.writeUTF(assigned);
                    
                }
            } catch (EOFException ex) {
                rTicket.close();
                this.dos.close();
            }
            }catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null,
                        "Archivo no existe.", "Archivo no existe",
                        JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                ex.getStackTrace();
            }          
        }
    }
    
}
