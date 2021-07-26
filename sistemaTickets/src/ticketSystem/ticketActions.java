
package ticketSystem;

import java.io.*;
import javax.swing.JOptionPane;

public class ticketActions extends userDashboard{
    
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
          DataOutputStream salida=new DataOutputStream(
                  new FileOutputStream("ticket.dat",true));
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
     
}
