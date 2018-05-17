package songs;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Evgeny Borisov
 */
@Configuration
@ComponentScan
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
