

package ticketSystem_server;
import java.net.*;
import java.io.*;

public class ticketServer {

  public static void main (String [] args ) throws IOException {
        
        ServerSocket ss = new ServerSocket(7575);

        // running infinite loop for getting
        // client request
        while (true) {
            Socket s = null;
            String requestType;
            try {
                s = ss.accept();
                 
                // obtaining input and out streams
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataInputStream req = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());
                
                requestType=req.readUTF();
                
                switch(requestType){
                    case "1":
                        System.out.print("Generar ticket number");
                        numberRequest nr=new numberRequest(s, dos);
                        nr.postTicketNum(); //Thread para generar ticket number
                        System.out.println("Case 1 closed");

                        break;
                    
                    case "2":
                        System.out.print("Leer tickets en tabla");
                        Thread rt = new readTicketThread(s, dos); 
                        rt.start(); //thread para leer tickets
                        System.out.println("Case 2 closed");
                        break;
                        
                    case "3":
                        System.out.print("Escribir ticket");
                        Thread wt = new writeThread(s, dis); //thread para escribir tickets
                        wt.start();
                        System.out.println("Case 3 closed");
                        break;
                        
                    case "4":
                        System.out.print("Actualizar ticket");
                        Thread up = new updateProc(s,dis);
                        up.start();
                        System.out.println("Case 4 closed");
                        break;
                }
                      
            }
            catch (Exception e){
                s.close();
                e.printStackTrace();
            } 
        }
    }
}
