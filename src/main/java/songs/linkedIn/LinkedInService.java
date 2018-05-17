package songs.linkedIn;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.functions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.*;

/**
 * @author Evgeny Borisov
 */
@Service
public class LinkedInService {
    @Autowired
    private SQLContext sqlContext;

    public void proccessLinkedInData() {
        DataFrame dataFrame = sqlContext.read().json("data/linkedIn/profiles.json");
    }
}
