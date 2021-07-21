/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketSystem;

import java.io.File;

/**
 *
 * @author Andres
 */
public class tests {
    public static void main(String[] args) {
        ticketActions ta = new ticketActions();
        File file = new File("ticket.dat");
        // calling method
        ta.readFromLast(file);
        //rf.reverseLines(file);
        } 
}
