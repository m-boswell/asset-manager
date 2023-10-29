
import java.util.Date;

public class Asset {
    private String name;
    private String path;
    private Date date;

    // Constructor
    public Asset(String name, String path, Date date) {
        this.name = name;
        this.path = path;
        this.date = date;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public Date getDate() {
        return date;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", date=" + date +
                '}';
    }
}