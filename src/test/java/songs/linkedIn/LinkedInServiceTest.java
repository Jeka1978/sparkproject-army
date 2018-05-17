package songs.linkedIn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import songs.CommonConfig;

import static org.junit.Assert.*;

/**
 * @author Evgeny Borisov
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = CommonConfig.class)
@ActiveProfiles("local")
public class LinkedInServiceTest {
    @Autowired
    LinkedInService linkedInService;


    @Test
    public void linkedInServiceTest() throws Exception {
        linkedInService.proccessLinkedInData();













    }
}