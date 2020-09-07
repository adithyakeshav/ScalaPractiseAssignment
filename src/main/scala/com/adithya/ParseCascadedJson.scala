package com.adithya

import com.adithya.constants.SparkEssentials._
import org.apache.spark.sql.functions._
import spark.implicits._

object ParseCascadedJson extends App {
  val messageDf = getDfFromJson("sample_message.json", multiline = "true")

  val df = messageDf.select(
    col("EVENTPAYLOADINJSON.*")
  ).select(
    col("EVENT_DETAILS.*"),
    col("EVENT_NAME")
  ).withColumn("BATCH_DETAILS", explode($"BATCH_DETAILS"))
    .withColumn("DATA_FILE_DETAILS", $"BATCH_DETAILS"("DATA_FILE_DETAILS"))
    .withColumn("HOST_NAME", $"BATCH_DETAILS"("HOST_NAME"))
    .select(
      "EVENT_NAME",
      "PROCESS_NAME",
      "DATA_FILE_DETAILS.*",
      "HOST_NAME"
    ).withColumn("DATA_FILE_ATTRIBUTES", explode($"DATA_FILE_ATTRIBUTES"))
      .withColumn("DATA_FILE_NAME", $"DATA_FILE_ATTRIBUTES"("DATA_FILE_NAME"))

    df.select(
      col ("EVENT_NAME").as("eventName"),
      col("PROCESS_NAME").as("processName"),
      col("HOST_NAME").as("hostname"),
      col("DATA_FILE_PATH").as("filePath"),
      col("DATA_FILE_NAME").as("dataFileName")
    ).show(false)

}
