package file;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas on 29/04/2016.
 */
public class Ticket
{
    private String body;
    private String customerName;
    private String subject;
    private Map<String, Attachment> attachmentMap = new LinkedHashMap<>();

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Attachment getAttachmenMap(String name){
        return this.attachmentMap.get(name);
    }

    public Collection<Attachment> getArrachmentsMap()
    {
        return this.attachmentMap.values();
    }

    public void addAttachment(Attachment attachment){
        this.attachmentMap.put(attachment.getName(), attachment);
    }

    public int getNumberOfAttachments(){
        return this.attachmentMap.size();
    }

}
