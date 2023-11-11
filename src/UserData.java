import java.io.Serializable;
import java.util.List;
// ido not know what to comment here
public class UserData implements Serializable {
    private String username;
    private String password;
    private int level;
    private int exp;
    private List<String> materials;
    private List<String> items;

    public UserData(String username, String password, int level, int exp, List<String> materials, List<String> items) {
        this.username = username;
        this.password = password;
        this.level = level;
        this.exp = exp;
        this.materials = materials;
        this.items = items;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return this.level;
    }

    public int getExp() {
        return this.exp;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public List<String> getItems() {
        return items;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void addItem(String newItem){
        items.add(newItem);}
    public boolean hasItem(String item) {
        return items.contains(item);
    }
    public void addMaterial(String newMaterial) {
        materials.add(newMaterial);
    }
    public boolean hasMaterial(String material) {
        return materials.contains(material);
    }

    public void addExp(int exp) {
        this.exp += exp;
        while (this.exp >= 100) {
            this.level++;
            this.exp -= 100;
        }
    }
}
