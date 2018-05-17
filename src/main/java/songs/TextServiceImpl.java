package songs;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author Evgeny Borisov
 */
@Service
public class TextServiceImpl implements TextService {
    @Autowired
    private TopWordsService wordsService;
    @Autowired
    private JavaSparkContext sc;
    @Override
    public List<String> topX(String artistName, int x) {
        JavaRDD<String> rdd = sc.textFile("data/songs/" + artistName.toLowerCase() + "/*");
        return wordsService.topX(rdd, x);
    }
}
