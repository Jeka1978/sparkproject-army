package create_df_from_csv;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;

import java.util.Arrays;

/**
 * @author Evgeny Borisov
 */
public class Main {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("army").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new SQLContext(sc);
        JavaRDD<String> rdd = sc.textFile("data/data.csv");
        System.out.println(rdd.count());
        JavaRDD<Row> rowJavaRDD = rdd.map(line -> RowFactory.create(Arrays.asList(line.split(";"))));
        DataFrame dataFrame = sqlContext.createDataFrame(rowJavaRDD, buildSchema());
        dataFrame.show();


    }

    private static StructType buildSchema() {

        return DataTypes.createStructType(new StructField[]{
                DataTypes.createStructField("name", DataTypes.StringType, false),
                DataTypes.createStructField("age", DataTypes.StringType, false)
        });

    }
}








