package file_and_jsp;

/**
 * Created by Tomas on 07/05/2016.
 */
public class Attachments
{
    private String name;
    private byte [] contents;

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
