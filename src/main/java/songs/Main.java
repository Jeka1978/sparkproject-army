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
        JavaSparkContext sc = context.getBean(JavaSparkContext.class);
        TopWordsService service = context.getBean(TopWordsService.class);
        JavaRDD<String> rddLines = sc.parallelize(asList("java java java", "scala", "groovy,groovy"));
        List<String> list = service.topX(rddLines, 1);
        System.out.println(list);
    }
}
