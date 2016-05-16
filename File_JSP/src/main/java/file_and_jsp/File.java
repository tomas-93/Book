package file_and_jsp;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tomas on 07/05/2016.
 */
public class File {
    private String customerName, subject, body;
    private Map<String, Attachments> attachmentMap = new LinkedHashMap<>();

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

    public String getCustomerName() {

        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Attachments getAttachment(String customerName) {
        return this.attachmentMap.get(customerName);
    }

    public void addAttachment(Attachments attachments) {
        this.attachmentMap.put(attachments.getName(), attachments);
    }

    public int getNumberOfAttachment()
    {
        return this.attachmentMap.size();
    }
    public Collection<Attachments> getAttachments()
    {
        return this.attachmentMap.values();
    }

}
