package songs;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

/**
 * @author Evgeny Borisov
 */
@Configuration
@ComponentScan
@PropertySource("classpath:user.properties")
public class CommonConfig {
    @Autowired
    private JavaSparkContext sc;

    @Bean
    public SQLContext sqlContext(){
        return new SQLContext(sc);
    }
}
