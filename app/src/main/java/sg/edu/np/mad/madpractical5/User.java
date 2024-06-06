package sg.edu.np.mad.madpractical5;

public class User {

    public int id;

    public String name;

    public String description;

    public boolean followed;

    public void setId(int id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setDescription(String description) {this.description = description;}

    public void setFollowed(boolean followed) {this.followed = followed;}

    public int getId() {return id;}

    public String getName() {return name;}

    public String getDescription() {return description;}

    public boolean getFollowed() {return followed;}

    public User(int id, String name, String description, boolean followed) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.followed = followed;
    }
}
