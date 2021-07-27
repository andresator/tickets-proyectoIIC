
package ticketSystem;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicLong;
import java.io.File;

/**
 *
 * @author Andres
 */
public class tests {
    
    public enum EventNumberSequenceGenerator {
    EVENT_SEQ(Calendar.getInstance().getTimeInMillis());
 
    private AtomicLong currentId;
 
    EventNumberSequenceGenerator(long timeInMillisRightNow) {
        this.currentId = new AtomicLong(timeInMillisRightNow);
    }
 
    public long getNext() {
        return currentId.incrementAndGet();
    }
}
    public static void main(String[] args) {
        //getNext();
        } 
    
    
 

}
