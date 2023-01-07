import java.util.UUID;

public class User {
    UUID id;
    private Location location;

    public User() {}

    public User(UUID id, Location location) {
        this.id = id;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
