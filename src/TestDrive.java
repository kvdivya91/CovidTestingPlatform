import java.util.*;

public class TestDrive {
    public static void main(String[] args) {
        List<TestingCenter> testingCenters = new LinkedList<>();
        TestDrive testDrive = new TestDrive();
        for(int i = 0; i < 5; i++) {
            TestingCenter tc = testDrive.createTestingCenterAtLocation(i+1, i+2, i);
            testingCenters.add(tc);
            System.out.println(tc);
        }

//        1. Onboard Testing Centers
        CovidTestPlatform platform = new CovidTestPlatform();
        platform.onboardTestingCenter(testingCenters);

//        2. Book testing kit
        User user = new User(UUID.randomUUID(), new Location(1, 2));
        UUID tcId = platform.bookTestingKit(user);

//       3. Receive kit
        TestingCenter tc = testingCenters.get(0);
        UUID testingCenterId = tc.getId();
        int noOfTestKits = 50;
        platform.receiveKits(testingCenterId, noOfTestKits);


    }

    private TestingCenter createTestingCenterAtLocation(int x, int y, int kits) {
        Queue<User> waitlist = new LinkedList<>();
        Location location = new Location(x, y);
        TestingCenter tc = new TestingCenter(UUID.randomUUID(), kits, location,  waitlist);
        return tc;
    }
}
