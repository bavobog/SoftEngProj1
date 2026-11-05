import adapters.in.cmdAdapter
import adapters.out.{csvAdapter, memAdapter}
import application.QueueTimeService

object Main extends App:
  //val repo = new memAdapter
  val path = java.nio.file.Paths.get("src/main/scala/resources/queuetimes.csv")

  val repo = new csvAdapter(path.toString)
  val service = new QueueTimeService(repo)

  val controller = new cmdAdapter(service)
  controller.run()

