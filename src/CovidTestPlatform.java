import java.util.*;

public class CovidTestPlatform {
    private List<TestingCenter> testingCenters;

    public CovidTestPlatform() {}
    public CovidTestPlatform(List<TestingCenter> testingCenters) {
        this.testingCenters = testingCenters;
    }

    public List<TestingCenter> getTestingCenters() {
        return testingCenters;
    }

    public void setTestingCenters(List<TestingCenter> testingCenters) {
        this.testingCenters = testingCenters;
    }

    public void onboardTestingCenter(List<TestingCenter> testingCenters) {
        this.testingCenters = testingCenters;
    }

    public void receiveKits(UUID testingCenterId, int noOfTestKits) {
        System.out.println("Adding test " + noOfTestKits + " kits to :: " + testingCenterId);
        TestingCenter testingCenter = null;
        for(TestingCenter tc : testingCenters) {
            if(tc.getId().equals(testingCenterId)) {
                testingCenter = tc;
                break;
            }
        }
//        Replenish Test Kits
        if(testingCenter != null) {
            int totalTestKits = testingCenter.getNoOfTestingKits() + noOfTestKits;
            testingCenter.setNoOfTestingKits(totalTestKits);
        }
        System.out.println("No of test kits in :: " + testingCenter.getId() + " :: is " + testingCenter.getNoOfTestingKits());

//        Remove users from this waitlist
        List<User> reservedUsers = removeUsersFromWaitlist(testingCenter);

        removeUsersFromAllWaitlist(reservedUsers);
    }

    public List<User> removeUsersFromWaitlist(TestingCenter testingCenter) {
        List<User> reservedUsers = new LinkedList<>();
        Queue<User> waitlist = testingCenter.getWaitlist();
        int testKits = testingCenter.getNoOfTestingKits();
        while(!waitlist.isEmpty() && testKits > 0) {
            User user = waitlist.remove();
            System.out.println("User :: " + user.getId() + " :: received a kit!!");
            testingCenter.reserveKit();
            reservedUsers.add(user);
        }
        return reservedUsers;

    }

    public void removeUsersFromAllWaitlist(List<User> reservedUsers) {
        for(User user : reservedUsers) {
            for(TestingCenter tc : testingCenters) {
                Queue<User> waitlist = tc.getWaitlist();
                if(waitlist.contains(user)) {
                    waitlist.remove(user);
                }
            }
        }
    }

    public UUID bookTestingKit(User user) {
        int x1 = user.getLocation().getX();
        int y1 = user.getLocation().getY();
        Map<TestingCenter, Integer> tcDistanceMap = new HashMap<>();
        PriorityQueue<TestingCenter> pq = new PriorityQueue<>(Comparator.comparingInt(tcDistanceMap::get));
        for(TestingCenter tc : testingCenters) {
            int x2 = tc.getLocation().getX();
            int y2 = tc.getLocation().getY();
            int distance = (int) Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
            tcDistanceMap.put(tc, distance);
        }

        TestingCenter nearestTestingCenter = null;
        for(TestingCenter tc : pq) {
            if(tc.isKitsAvailable()) {
                nearestTestingCenter =  tc;
                tc.reserveKit();
            }
        }
        if(nearestTestingCenter == null) {
            addUserToWaitlist(user);
            System.out.println("Test kits not available! User :: " + user.getId() + " :: is waitlisted!!");
        }

        return (nearestTestingCenter != null) ? nearestTestingCenter.getId() : null;
    }

    public void addUserToWaitlist(User user) {
        for(TestingCenter tc : testingCenters) {
            tc.getWaitlist().add(user);
        }
    }
}
