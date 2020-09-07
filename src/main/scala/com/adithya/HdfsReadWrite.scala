package com.adithya

import com.adithya.constants.SparkEssentials.{DATA_RESOURCE, spark}
import org.apache.spark.sql.SaveMode

object HdfsReadWrite extends App{
  val df = spark.read.csv("hdfs://localhost:9000/file.csv")

  df.printSchema()

  df.write
    .format("csv")
    .mode(SaveMode.Overwrite)
    .option("header", "true")
    .save(DATA_RESOURCE + "file.csv")
}
