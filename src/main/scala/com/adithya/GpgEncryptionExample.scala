package com.adithya

import java.io.File
import com.adithya.constants.SparkEssentials.DATA_RESOURCE
import com.adithya.constants.FileEssentials.isFileType

import sys.process._

object GpgEncryptionExample extends App {

  // commandPrompt variable used as prefix to run commands on Windows Terminal
  val commandPrompt = "cmd.exe /c "

  // Passphrase can be dynamically fetched
  // Static value has been used for test purpose
  val passphrase = "1234"

  def getEncryptedFile(inputFileName : String) : String  = {
    if(new File(inputFileName).isFile) {
      println(s"File '$inputFileName' Being Encrypted")

      // User will be prompted to enter the Passphrase
      val status = (commandPrompt + "gpg -c " + inputFileName).!
      val outputFile = inputFileName + ".gpg"
      if(status == 0) {
        println("Encryption successful !")
        println(s"Output File : $outputFile")
      } else throw new Exception("Could not be encrypted")
      return outputFile
    }
    throw new IllegalArgumentException(s"File not found in given path : $inputFileName")
  }

  def getDecryptedFile(encryptedFile : String) : String = {
    if(new File( encryptedFile).isFile) {
      if(isFileType(encryptedFile, "gpg")) {
        println(s"File $encryptedFile Being Decrypted")

        val status = (commandPrompt + s"gpg --passphrase $passphrase " + encryptedFile ).!
        if(status == 0) {
          println("Decrypted Successfully")
        } else throw new Exception("Could not be decrypted")
        val splitArray = encryptedFile.split('.')
        var filename = splitArray(0)

        for(i <- 1 until splitArray.length - 1)
          filename +=( "." + splitArray(i))
        return filename
      }
      throw new IllegalArgumentException(s"File is not in GPG format : $encryptedFile")
    }
    throw new IllegalArgumentException(s"File not found : $encryptedFile")
  }

  try {
    // Deleting the encrypted file to avoid the 'file exists' conflict
    (commandPrompt + "del " + DATA_RESOURCE + "gpgtest.txt.gpg").!

    val encryptedFile = getEncryptedFile(DATA_RESOURCE + "gpgtest.txt")

    // Deleting the original file to avoid the 'file exists' conflict
    (commandPrompt + "del "+ DATA_RESOURCE+ "gpgtest.txt").!

    println("Original file deleted !")

    val decryptedFile = getDecryptedFile(encryptedFile)

    println("Decryption is completed ! Output File : " + decryptedFile)
  }
  catch  {
    case e: Exception => println(e getMessage)
  }

}
