package com.adithya

import java.io.File

object FindFilesOlderThanSevenDays extends App {

  def isType(file : File , fileType : String) : Boolean = {
    val nameAndExtension: Array[String] = file.getName.split('.')
    if(nameAndExtension.length > 0) {
      return nameAndExtension(nameAndExtension.length - 1).equals(fileType)
    }
    false
  }

  // Function that returns the Last Modified property in integer Days
  def getLastModifiedInDays(file : File): Int = {
    val day = 24 * 60 * 60 * 1000
    val differenceInMillis = System.currentTimeMillis() - file.lastModified()
    val days = differenceInMillis / day
    days.toInt
  }

  val filePath = "C:\\Users\\adithya\\Documents\\files"
  val folder = new File(filePath)
  if (folder.isDirectory()) {
    val files = folder.listFiles()

    for (file <- files) {
      try
      {
        if( isType(file, "txt") ){
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
