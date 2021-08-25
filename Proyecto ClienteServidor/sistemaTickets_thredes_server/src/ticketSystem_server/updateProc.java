
package ticketSystem_server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class updateProc extends Thread{
    
    final DataInputStream dis;
    final Socket s;
      
    // Constructor
    public updateProc(Socket s, DataInputStream dis) {
        this.s = s;
        this.dis = dis;
    }
    
    public void editData(String ctiq,String cstatus,String cuser,String csummry,
            String cprirty, String cphone, String cemail, String ccMethod,
            String cdescription, String cassigned) {
        System.out.println("Running editData");
        String tiq, status, user, summry, prirty, phone, email, cMethod, description, assigned;
        ticketActions ticket=new ticketActions();

        try {
            DataInputStream entrada = new DataInputStream(new FileInputStream("ticket.dat"));
            
            try {
                while (true) {
                    tiq = entrada.readUTF();
                    status = entrada.readUTF();
                    user = entrada.readUTF();
                    summry = entrada.readUTF();
                    prirty = entrada.readUTF();
                    phone = entrada.readUTF();
                    email = entrada.readUTF();
                    cMethod = entrada.readUTF();
                    description = entrada.readUTF();
                    assigned = entrada.readUTF();
                    System.out.print(ctiq);
                    
                    if(tiq.equals(ctiq)){
                        System.out.println("true");
                        ticket.agregarTemp(ctiq, cuser, cphone, cemail, ccMethod, 
                                cprirty, csummry, cdescription, cstatus, cassigned);
                    } else {
                        System.out.println("False no changes");
                        ticket.agregarTemp(tiq, user, phone, email, cMethod, 
                                prirty, summry, description, status, assigned);
                    }
                }  
                
            } catch (EOFException ex) {
                entrada.close();
                replaceTicket();
               
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no existe, edit");
        } catch (IOException ex) {
            System.out.print("Error desconocido edit");
        }
    }
    
    
    public void replaceTicket(){
        System.out.print("Start replace");
        File file = new File("ticket.dat");
        file.delete();
        
        
        String tiq, status, user, summry, prirty, phone, email, cMethod, description, assigned;
        ticketActions ticket=new ticketActions();


        try {
            DataInputStream entrada = new DataInputStream(new FileInputStream("ticket_temp.dat"));
            DataOutputStream salida = new DataOutputStream(new FileOutputStream("ticket.dat"));

            try {
                while (true) {
                    tiq = entrada.readUTF();
                    status = entrada.readUTF();
                    user = entrada.readUTF();
                    summry = entrada.readUTF();
                    prirty = entrada.readUTF();
                    phone = entrada.readUTF();
                    email = entrada.readUTF();
                    cMethod = entrada.readUTF();
                    description = entrada.readUTF();
                    assigned = entrada.readUTF();
                    System.out.print(tiq);
           
                    salida.writeUTF(tiq);
                    salida.writeUTF(status);
                    salida.writeUTF(user);
                    salida.writeUTF(summry);
                    salida.writeUTF(prirty);
                    salida.writeUTF(phone);
                    salida.writeUTF(email);
                    salida.writeUTF(cMethod);
                    salida.writeUTF(description);
                    salida.writeUTF(assigned);
                    
                    System.out.println("Agregado a ticket.dat");
                }

            } catch (EOFException ex) {
                entrada.close();
                salida.close();
                File fileTemp = new File("ticket_temp.dat");
                fileTemp.delete();
                System.out.print("Update complete");
            }
        } catch (FileNotFoundException ex) {
             System.out.println("Archivo no existe");
        } catch (IOException ex) {
            System.out.print("Error desconocido");
        } 
    }
  
    @Override
    public void run(){
        System.out.println("Ejecutando update run");
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
                System.out.println("Recibido "+tiq);
                editData(tiq, status, user, summry, prirty, phone,email,
                cMethod,description,assigned);
                
                
                dis.close();
                System.out.println("Run complete");
            }catch(IOException ex){
                ex.getStackTrace();
            }
                
        }
    }   
    
}
