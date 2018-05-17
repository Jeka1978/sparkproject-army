package songs;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Evgeny Borisov
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);
        TextService textService = context.getBean(TextService.class);
        List<String> list = textService.topX("beatles", 3);
        System.out.println(list);
    }
}
