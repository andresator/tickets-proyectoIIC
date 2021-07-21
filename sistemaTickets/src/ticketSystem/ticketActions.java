
package ticketSystem;

import java.io.*;
import javax.swing.JOptionPane;

public class ticketActions{
   
    // Read last line of the file
    public void readFromLast(File file){  
      int lines = 0;
      StringBuilder builder = new StringBuilder();
      RandomAccessFile randomAccessFile = null;
      try {
        randomAccessFile = new RandomAccessFile(file, "r");
        long fileLength = file.length() - 1;
        // Set the pointer at the last of the file
        randomAccessFile.seek(fileLength);
        for(long pointer = fileLength; pointer >= 0; pointer--){
          randomAccessFile.seek(pointer);
          char c;
          // read from the last one char at the time
          c = (char)randomAccessFile.read(); 
          // break when end of the line
          if(c == '\n'){
            break;
          }
          builder.append(c);
        }
        // Since line is read from the last so it 
        // is in reverse so use reverse method to make it right
        builder.reverse();
        System.out.println("Line - " + builder.toString());
      } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }finally{
        if(randomAccessFile != null){
          try {
            randomAccessFile.close();
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
      }
    }
    
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
          salida.writeUTF(ticket.getReporter());
          salida.writeUTF(ticket.getPhone());
          salida.writeUTF(ticket.getEmail());
          salida.writeUTF(ticket.getContactMethod());
          salida.writeUTF(ticket.getPriority());
          salida.writeUTF(ticket.getSummary());
          salida.writeUTF(ticket.getDescription());
          salida.writeUTF(ticket.getStatus());
          salida.writeUTF(ticket.getNumber());
          salida.writeUTF(ticket.getNumber());
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
