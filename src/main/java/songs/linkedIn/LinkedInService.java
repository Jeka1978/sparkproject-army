package songs.linkedIn;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import static org.apache.spark.sql.functions.*;

/**
 * @author Evgeny Borisov
 */
@Service
public class LinkedInService {
    private static final String SALARY = "salary";
    private static final String AGE = "age";
    private static final String KEYWORDS = "keywords";
    private static final String KEYWORD = "keyword";
    private static final String AMOUNT = "amount";
    private static final String DARGA = "darga";
    @Autowired
    private SQLContext sqlContext;

    @Autowired
    private PesnionFunction pesnionFunction;

    @PostConstruct
    public void registerUDF() {
        sqlContext.udf().register(pesnionFunction.getClass().getName(), pesnionFunction, DataTypes.StringType);
    }

    public void proccessLinkedInData() {
        DataFrame dataFrame = sqlContext.read().json("data/linkedIn/profiles.json");
        dataFrame.show();
        dataFrame.printSchema();
        StructField[] fields = dataFrame.schema().fields();
        for (StructField field : fields) {
            System.out.println(field.name());
            System.out.println(field.nullable());
            System.out.println(field.dataType());
        }
        dataFrame=dataFrame.withColumn(SALARY, col(AGE).multiply(10).multiply(size(col(KEYWORDS))));
        dataFrame.show();
        DataFrame keywordDF = dataFrame.withColumn(KEYWORD, explode(col(KEYWORDS))).select(KEYWORD);
        keywordDF.show();
        Row row = keywordDF.groupBy(KEYWORD).agg(count(KEYWORD).as(AMOUNT))
                .orderBy(desc(AMOUNT)).head();
        String mostPopular = row.getAs(KEYWORD);
        System.out.println("mostPopular = " + mostPopular);

        dataFrame.filter(col(SALARY).leq(1200))
                .filter(array_contains(col(KEYWORDS),mostPopular)).show();

        dataFrame.withColumn(DARGA,callUDF(PesnionFunction.class.getName(),col(AGE))).show();


    }
}
