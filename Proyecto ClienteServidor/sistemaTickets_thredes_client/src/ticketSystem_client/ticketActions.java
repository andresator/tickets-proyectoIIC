
package ticketSystem_client;

import java.io.*;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ticketActions {
    
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
        String reqType="3";
      try{
          Socket cs=new Socket("127.0.0.1",7575);
            DataOutputStream request=new DataOutputStream(cs.getOutputStream());
                request.writeUTF(reqType);
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
          
          salida.close();
          request.close();
      }catch(Exception ex){
          JOptionPane.showMessageDialog(null,
                  "¡Error al crear tiquete!","Error",JOptionPane.ERROR_MESSAGE);
      }
      JOptionPane.showMessageDialog(null,
                  "¡Tiquete creado exitosamente!","Tiquete creado",
                  JOptionPane.INFORMATION_MESSAGE);
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
      
    public String getTicketNumber(){
       String tNumber = "0";
       String reqType="1";
        try{
            Socket cs=new Socket("127.0.0.1",7575);
            DataOutputStream request=new DataOutputStream(cs.getOutputStream());
                request.writeUTF(reqType);
                request.close();
            DataInputStream inputNumber=new DataInputStream(cs.getInputStream());
            try{
                while(true){   
                    tNumber=String.valueOf(inputNumber.readInt());
                    inputNumber.close();
               }
                
            }catch(IOException ex){
                ex.getStackTrace();
            }   
        }catch(IOException ex){
            ex.getStackTrace();
      }
        return tNumber;
    }
    
    
    
    
}

