package com.adithya

import com.adithya.constants.SparkEssentials._
import org.apache.spark.sql.functions._

object InnerJoinDataframes extends App {

  // Fetch the data from CSV Files
  val bandsDf = getDfFromCsv("bands.csv")
  val guitaristsDf = getDfFromCsv("guitarists.csv")

  // Inner Join to merge data of Bands with the Guitarists
  val joinedDf = guitaristsDf.join(bandsDf,
    bandsDf.col("id") === guitaristsDf.col("band"),
    "inner")
    .drop(bandsDf.col("id"))
    .withColumnRenamed("id", "Guitarist_Id")
    .withColumnRenamed("band", "Band_Id")

  joinedDf.show()

}