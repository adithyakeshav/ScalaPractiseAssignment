package com.adithya

import com.adithya.constants.SparkEssentials._
import com.adithya.constants.SqlPublicVariables.INSURANCE_TABLE

object CsvToDatabase extends App {

  // Functions used are modularized in the 'constants' package
  // Fetch the Data from CSV and store in the Dataframe

  val df = getDfFromCsv("flinsurance.csv")
  df.show()

  df.printSchema()

  /*
   * Print Schema Of the Insurance CSV File :
   * root
     |-- policyID: integer (nullable = true)
     |-- statecode: string (nullable = true)
     |-- county: string (nullable = true)
     |-- eq_site_limit: double (nullable = true)
     |-- hu_site_limit: double (nullable = true)
     |-- fl_site_limit: double (nullable = true)
     |-- fr_site_limit: double (nullable = true)
     |-- tiv_2011: double (nullable = true)
     |-- tiv_2012: double (nullable = true)
     |-- eq_site_deductible: double (nullable = true)
     |-- hu_site_deductible: double (nullable = true)
     |-- fl_site_deductible: double (nullable = true)
     |-- fr_site_deductible: integer (nullable = true)
     |-- point_latitude: double (nullable = true)
     |-- point_longitude: double (nullable = true)
     |-- line: string (nullable = true)
     |-- construction: string (nullable = true)
     |-- point_granularity: integer (nullable = true)

   */

  // Writes the Dataframe to MySQL Database
  writeDataframeToDatabase(df, INSURANCE_TABLE)

}
