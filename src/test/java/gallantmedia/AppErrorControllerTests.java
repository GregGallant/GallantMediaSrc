package gallantmedia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

/**
 * Created by greg on 7/28/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppErrorController.class)
public class AppErrorControllerTests {

    @Test
    public void contextLoads() {
        assertTrue(true);
    }

}
