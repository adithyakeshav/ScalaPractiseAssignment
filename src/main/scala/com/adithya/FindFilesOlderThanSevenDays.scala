package com.adithya

import java.io.File
import java.lang.Exception
import java.util.Date

object FindFilesOlderThanSevenDays extends App {

  val filePath = "C:\\Users\\adithya\\Documents\\files"

  def getLastModifiedInDays(file : File): Int = {
    val day = 24 * 60 * 60 * 1000
    val nameAndExtension: Array[String] = file.getName.split('.')
    if (nameAndExtension.length > 0) {
      if (nameAndExtension(nameAndExtension.length - 1).equals("txt") ) {
        val differenceInMillis = System.currentTimeMillis() - file.lastModified()
        val days = differenceInMillis / day
        return days.toInt
      }
    }
    throw new Exception("Unsupported File Format")
  }

  val folder = new File(filePath)
  if (folder.isDirectory()) {
    val files = folder.listFiles()

    for (file <- files) {
      try
      {
        val days = getLastModifiedInDays(file)
        if (days >= 7) {
          println(s"File ${file.getName} is older than 7 Days ($days days)")
        } else
          println(s"File ${file.getName} is New ($days days)")
      } catch {
        case _ : Exception=>
      }
    }
  }
}
