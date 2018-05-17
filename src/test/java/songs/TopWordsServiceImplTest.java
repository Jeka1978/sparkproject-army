package songs;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

/**
 * @author Evgeny Borisov
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Conf.class)
@ActiveProfiles("local")
public class TopWordsServiceImplTest {
    @Autowired
    JavaSparkContext sc;

    @Autowired
    TopWordsService topWordsService;

    @Test
    public void testTopWordIsCorrect() throws Exception {
        JavaRDD<String> rddLines = sc.parallelize(asList("java java java", "scala", "groovy,groovy"));
        List<String> list = topWordsService.topX(rddLines, 1);
        Assert.assertEquals(1,list.size());
        Assert.assertEquals("java",list.get(0));
    }
}






