
package ticketSystem_client;


public class ticketInfo {
    protected String Number;
    protected String Reporter;
    protected String Phone;
    protected String Email;
    protected String contactMethod;
    protected String Priority;
    protected String Summary;
    protected String Description;
    protected String Status;
    protected String assignedTo;
    

    public ticketInfo() {
        this.Number = "";
        this.Reporter = "";
        this.Phone = "";
        this.Email = "";
        this.contactMethod = "";
        this.Priority = "";
        this.Summary = "";
        this.Description = "";
        this.assignedTo="";
        this.Status="";
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getReporter() {
        return Reporter;
    }

    public void setReporter(String Reporter) {
        this.Reporter = Reporter;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String Priority) {
        this.Priority = Priority;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
    
}
