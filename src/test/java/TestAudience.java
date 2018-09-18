import http.models.SegmentStream;
import http.models.Visitor;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import web.driver.DriverManager;
import web.driver.Steps;
import web.pages.models.Audience;

public class TestAudience {
    @BeforeClass
    public static void setUp() {
        Steps.openBrowser();
        Steps.login();
    }

    @AfterClass
    public static void tearDown() {
        DriverManager.getInstance().close();
    }

    @Test
    public void testUserVisiting() {
        String connName = Steps.createConnector();
        String engName = Steps.createEngagement();
        Audience audience = Steps.createAudience(connName, engName);
        Visitor visitor = Steps.emulateVisiting(audience.getApiIdentifier());
        SegmentStream segmentStream = new SegmentStream(visitor.getUserId(), audience.getApiIdentifier());
        Assert.assertEquals(segmentStream.generateJson(), visitor.getStreamResponse());
        Steps.removeAudience(audience.getName());
        Steps.removeConnector(connName);
        Steps.removeEngagement(engName);
    }
}