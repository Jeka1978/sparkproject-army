package songs;

import org.apache.spark.api.java.JavaRDD;

import java.io.Serializable;
import java.util.List;

/**
 * @author Evgeny Borisov
 */
public interface TopWordsService extends Serializable {
    List<String> topX(JavaRDD<String> lines, int x);

}
