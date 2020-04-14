public class Domain {

    private int id;
    private String type;

    public Domain() {}

    public Domain(String type) {
        this.type = type;
    }

    public Domain(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String toString() {
        return "Domain [id=" + id + ", type=" + type + "]";
    }
}
