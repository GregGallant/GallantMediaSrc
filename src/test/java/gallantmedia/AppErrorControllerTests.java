package gallantmedia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * Created by greg on 7/28/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppErrorController.class)
@WebAppConfiguration
public class AppErrorControllerTests {

    @Test
    public void contextLoads() {
        assertTrue(true);
    }

}
