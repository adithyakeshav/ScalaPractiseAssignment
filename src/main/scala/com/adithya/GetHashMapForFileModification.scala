package com.adithya

import java.io.File
import java.util.Date

import com.adithya.FindFilesOlderThanSevenDays.{getLastModifiedInDays}

import scala.collection.mutable.HashMap


object GetHashMapForFileModification extends App{

  def getHashMap(directoryPath : String ) : HashMap[String, Date] = {
    var map  = new HashMap[String, Date]()

    val folder = new File(directoryPath)
    if(folder.isDirectory()) {
      val files = folder.listFiles()

      for (file <- files) {
        try
          {
            val days = getLastModifiedInDays(file)
            if (days >= 7)
              map(file.getName) =  new Date(file.lastModified())
          } catch {
          case e:Exception =>
        }
      }
    }
    map
  }

  val filePath = "C:\\Users\\adithya\\Documents\\files"
  println(getHashMap(filePath))


}
