package songs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.context.annotation.*;

/**
 * @author Evgeny Borisov
 */
@Configuration
public class Conf {

    @Bean
    @Profile("local")
    public JavaSparkContext scLocal() {
        SparkConf conf = new SparkConf().setAppName("songs").setMaster("local[*]");
        return new JavaSparkContext(conf);
    }
    @Bean
    @Profile("prod")
    public JavaSparkContext scProd() {
        SparkConf conf = new SparkConf().setAppName("songs");
        return new JavaSparkContext(conf);
    }



}
