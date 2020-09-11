package com.adithya.constants

import java.io.File

object FileEssentials {
  def isFileType(fileName : String , fileType : String) : Boolean = {
    val nameAndExtension: Array[String] = fileName.split('.')
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

}
