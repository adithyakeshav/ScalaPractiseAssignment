package com.adithya.constants

import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import com.adithya.constants.SqlPublicVariables._

object SparkEssentials {
  val spark = SparkSession.builder()
    .appName("CreateTablesInDatabase")
    .config("spark.master", "local")
    .getOrCreate()

  val DATA_RESOURCE = "src\\main\\resources\\data\\"

  def getDfFromJson (fileName : String, multiline : String = "") =
    spark.read
      .options(Map(
        "inferSchema"-> "true",
        "multiline" -> multiline
      )).json(DATA_RESOURCE + fileName)

  def getDfFromCsv(fileName : String) = spark.read
      .option("inferSchema", "true")
      .option("header", "true")
      .csv(DATA_RESOURCE + fileName)

  def getDfFromDatabase(tableName : String) = spark.read
    .format("jdbc")
    .option("driver", DATABASE_DRIVER)
    .option("url", DATABASE_URL)
    .option("user", USER)
    .option("password", PASSWORD)
    .option("dbtable", tableName)
    .load()

  def writeDataframeToCsv (dataFrame: DataFrame, filename : String) : Unit = dataFrame.write
    .format("csv")
    .mode(SaveMode.Overwrite)
    .option("header", "true")
    .save(DATA_RESOURCE + filename)

  def writeDataframeToJson (dataframe :DataFrame ,fileName :  String ) : Unit  =   dataframe.write
    .format("json")
    .mode(SaveMode.Overwrite)
    .save(DATA_RESOURCE + fileName)

  def writeDataframeToDatabase (dataframe :DataFrame, table_name: String) : Unit  =  dataframe.write
    .format("jdbc")
    .mode(SaveMode.Overwrite)
    .option("driver", DATABASE_DRIVER)
    .option("url", DATABASE_URL)
    .option("user", USER)
    .option("password", PASSWORD)
    .option("dbtable", table_name)
    .save()

}
