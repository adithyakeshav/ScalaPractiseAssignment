package com.adithya

import java.io.File
import com.adithya.constants.FileEssentials._

object FindFilesOlderThanSevenDays extends App {

  val filePath = "C:\\Users\\adithya\\Documents\\files"
  val folder = new File(filePath)
  if (folder.isDirectory()) {
    val files = folder.listFiles()

    for (file <- files) {
      try
      {
        if( isFileType(file.getName, "txt") ){
          val days = getLastModifiedInDays(file)
          if (days >= 7) {
            println(s"File ${file.getName} is older than 7 Days ($days days)")
          } else
            println(s"File ${file.getName} is New ($days days)")
        }
      } catch {
        case e: Exception => println(e)
      }
    }
  }
}
