package adapters.out

import com.opencsv.CSVReader
import domain.{RideRepository, RideState}

import java.io.FileReader
import java.nio.file.attribute.FileTime
import java.nio.file.{Files, Paths}
import java.time.LocalDateTime
import scala.collection.mutable.ListBuffer


class csvAdapter(filePath: String) extends RideRepository {
  private val path = Paths.get(filePath)

  private var times = List[RideState]()
  private var lastMod = Files.getLastModifiedTime(path)
  private def load(): List[RideState] = {
    val reader = new CSVReader(Files.newBufferedReader(path))
    val data = reader.readAll()
    var processed = List[RideState]()
    data.forEach(a => 
      if a.length == 6 then {
        if a(0).forall(_.isDigit) then
        processed = processed.appended(RideState(a(0).toInt, LocalDateTime.parse(a(4)+"T"+a(5)), a(1), a(2).toBoolean, a(3).toInt))
    } else {
      println("line not recognised: " + a.mkString("Array(", ", ", ")"))
    })
    processed
    /*
    if Files.exists(path) then
      val content = Files.readAllLines(path)
      if !content.isEmpty then {
        var data = ListBuffer[RideState]()
        content.forEach(line =>
          var escaped = false
          var elemB = ListBuffer[String]()
          var current = ""
          for (c <- line) {
            if c.equals(',') && !escaped then
              elemB = elemB.appended(current)
              current = ""
            else if c.equals('"') then
              escaped = !escaped
            else
              current += c
          }
          if !current.equals("") then
            elemB = elemB.appended(current)
          val elem = elemB.toArray
          if elem.length == 6 then {
            if elem(0).forall(_.isDigit) then
              data = data.appended(RideState(elem(0).toInt, LocalDateTime.parse(elem(4)+"T"+elem(5)), elem(1), elem(2).toBoolean, elem(3).toInt))
          } else {
            println("line not recognised: " + line)
            println(elem.length)
          }
        )
        data.toList
      } else List.empty
    else List.empty

     */
  }


  override def listAll: List[RideState] =
    if times.isEmpty then
      times = load()
      lastMod = Files.getLastModifiedTime(path)
      times
    else if Files.getLastModifiedTime(path).compareTo(lastMod) > 0 then
      println("Change detected, updating times")
      times = load()
      lastMod = Files.getLastModifiedTime(path)
      times
    else {
      println("no change")
      println("path: " + path)
      println("last mod: " + lastMod + "  ---   current:  " + Files.getLastModifiedTime(path))
      times
    }


}
