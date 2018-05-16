package com.army;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import scala.Tuple2;

import java.time.LocalDate;
import java.util.List;

import static org.apache.spark.api.java.StorageLevels.MEMORY_AND_DISK;
import static org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK;

/**
 * @author Evgeny Borisov
 */

public class Taxi {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setAppName("taxi").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> rddLines = sc.textFile("data/taxi/taxi_order.txt");
        System.out.println(rddLines.persist(MEMORY_AND_DISK()).count());

        JavaRDD<Trip> tripRdd = rddLines.map(Trip::new).persist(MEMORY_AND_DISK());
        long bostonLongTrips = tripRdd
                .filter(trip -> trip.getCity().equals("boston"))
                .filter(trip -> trip.getKm() > 10).count();
        System.out.println("bostonLongTrips = " + bostonLongTrips);

        Double totalToBoston = tripRdd.filter(trip -> trip.getCity().equals("boston")).mapToDouble(Trip::getKm).sum();
        System.out.println("totalToBoston = " + totalToBoston);

        List<Tuple2<Integer, String>> top3 = tripRdd.mapToPair(trip -> new Tuple2<>(trip.getId(), trip.getKm()))
                .reduceByKey(Integer::sum)
                .mapToPair(Tuple2::swap)
                .sortByKey(false)
                .take(3);

        JavaPairRDD<String,Integer> top3Rdd = sc.parallelize(top3).mapToPair(Tuple2::swap);

        JavaPairRDD<String, String> driversRdd = sc.textFile("data/taxi/drivers.txt")
                .map(line -> line.split(", "))
                .mapToPair(split -> new Tuple2<>(split[0], split[1]));
        List<String> topNames = top3Rdd.join(driversRdd).map(tuple -> tuple._2()._2()).collect();
        System.out.println("topNames = " + topNames);

    }

}
