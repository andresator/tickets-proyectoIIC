
package ticketSystem_server;

import java.io.*;
import javax.swing.JOptionPane;
import java.net.Socket;


public class ticketActions{
    
    public void agregar(String number,String user,String phone,String email,
           String cMethod, String priority,String summary, String description, 
           String status, String assignment){
        
        ticketInfo ticket=new ticketInfo();
        ticket.setNumber(number);
        ticket.setReporter(user);
        ticket.setPhone(phone);
        ticket.setEmail(email);
        ticket.setContactMethod(cMethod);
        ticket.setPriority(priority);        
        ticket.setSummary(summary);
        ticket.setDescription(description);
        ticket.setStatus(status);
        ticket.setAssignedTo(assignment);
                
      try{
          Socket cs=new Socket("127.0.0.1",7575);
          DataOutputStream salida=new DataOutputStream(cs.getOutputStream());
          salida.writeUTF(ticket.getNumber());
          salida.writeUTF(ticket.getStatus());
          salida.writeUTF(ticket.getReporter());
          salida.writeUTF(ticket.getSummary());
          salida.writeUTF(ticket.getPriority());
          salida.writeUTF(ticket.getPhone());
          salida.writeUTF(ticket.getEmail());
          salida.writeUTF(ticket.getContactMethod());
          salida.writeUTF(ticket.getDescription());
          salida.writeUTF(ticket.getAssignedTo());
          JOptionPane.showMessageDialog(null,
                  "¡Tiquete creado exitosamente!","Tiquete creado",
                  JOptionPane.INFORMATION_MESSAGE);
          salida.close();
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,
                  "¡Error al crear tiquete!","Error",JOptionPane.ERROR_MESSAGE);
      }
   }
    
    public void agregarTemp(String number, String user, String phone, String email,String cMethod, String priority, String summary, String description, String status, String assignment) {
        ticketInfo ticket = new ticketInfo();
        ticket.setNumber(number);
        ticket.setReporter(user);
        ticket.setPhone(phone);
        ticket.setEmail(email);
        ticket.setContactMethod(cMethod);
        ticket.setPriority(priority);
        ticket.setSummary(summary);
        ticket.setDescription(description);
        ticket.setStatus(status);
        ticket.setAssignedTo(assignment);
        try {
            DataOutputStream salida = new DataOutputStream(new FileOutputStream("ticket_temp.dat", true));
            salida.writeUTF(ticket.getNumber());
            salida.writeUTF(ticket.getStatus());
            salida.writeUTF(ticket.getReporter());
            salida.writeUTF(ticket.getSummary());
            salida.writeUTF(ticket.getPriority());
            salida.writeUTF(ticket.getPhone());
            salida.writeUTF(ticket.getEmail());
            salida.writeUTF(ticket.getContactMethod());
            salida.writeUTF(ticket.getDescription());
            salida.writeUTF(ticket.getAssignedTo());
            //JOptionPane.showMessageDialog(null, "¡Tiquete creado exitosamente!", "Tiquete temp creado", JOptionPane.INFORMATION_MESSAGE);
            salida.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "¡Error al crear tiquete!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    public void initTicketNum() {
        String num="100000";
        try {
            DataOutputStream salida = new DataOutputStream(new FileOutputStream(
                    "number.dat"));
            try {
                    salida.writeInt(Integer.parseInt(num));    
                    salida.close();
                    
            }catch(EOFException ex){
                
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
    
    public String getTicketNumber(){
       generateTicketNum();
       int tNumber = 0;
        try{
           DataInputStream entrada=new DataInputStream(new FileInputStream
        ("number.dat"));
        try{
           while(true){
               tNumber=entrada.readInt();
           }
           
        }catch(EOFException ex){
           entrada.close();
        }   
      }catch(FileNotFoundException ex){
         JOptionPane.showMessageDialog(null,"¡No se encontró el archivo!",
                 "Archivo no encontrado",JOptionPane.ERROR_MESSAGE);
      }catch(IOException ex){
         JOptionPane.showMessageDialog(null,"¡Error en el dispositivo!",
                 "Error en el dispositivo",JOptionPane.ERROR_MESSAGE);
      }
        return String.valueOf(tNumber);
    } 
     
}
