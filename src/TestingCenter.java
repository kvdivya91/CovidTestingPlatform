import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.util.List;
import java.util.Queue;
import java.util.UUID;

public class TestingCenter {
    UUID id;
    private int noOfTestingKits;
    private Location location;
    private Queue<User> waitlist;

    public TestingCenter() {}

    public TestingCenter(UUID id, int noOfTestingKits, Location location, Queue<User> waitlist) {
        this.id = id;
        this.noOfTestingKits = noOfTestingKits;
        this.location = location;
        this.waitlist = waitlist;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNoOfTestingKits() {
        return noOfTestingKits;
    }

    public void setNoOfTestingKits(int noOfTestingKits) {
        this.noOfTestingKits = noOfTestingKits;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Queue<User> getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(Queue<User> waitlist) {
        this.waitlist = waitlist;
    }

    public void receiveKit(String id, int noOfKits) {

    }

    public void reserveKit() {
        this.noOfTestingKits = this.noOfTestingKits - 1;
    }

    public boolean isKitsAvailable() {
        if(noOfTestingKits > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "TestingCenter{" +
                "id=" + id +
                ", noOfTestingKits=" + noOfTestingKits +
                ", location=" + location +
                ", waitlist=" + waitlist +
                '}';
    }
}
