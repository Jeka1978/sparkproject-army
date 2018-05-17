package songs.linkedIn;

import org.apache.spark.sql.api.java.UDF1;
import org.springframework.stereotype.Component;

/**
 * @author Evgeny Borisov
 */
@Component
public class PesnionFunction implements UDF1<Long,String> {
    @Override
    public String call(Long age) throws Exception {
        if (age < 40) {
            return "RABAT";
        }else {
            return "SAMAL";
        }
    }
}
