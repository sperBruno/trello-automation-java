import org.testng.Assert;
import org.testng.annotations.Test;
import org.trello.utils.PropertiesInfo;

public class TestProperties {

    @Test
    public void testGetEnvFromProperties() {
        Assert.assertTrue(PropertiesInfo.getInstance().getBaseApi().length() > 1);
    }
}
