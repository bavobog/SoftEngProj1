package adapters.out

import domain.{RideRepository, RideState}

import java.time.LocalDateTime
import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class memAdapter extends RideRepository {
  private val rides = List(
    RideState(2706, LocalDateTime.parse( "2025-10-29T08:00:00" ),	"Disneyland Railroad Discoveryland Station", true,	0) ,
    RideState(4, LocalDateTime.parse( "2025-10-29T08:00:00" ),	"Autopia, presented by Avis", true,	5),
    RideState(13  , LocalDateTime.parse( "2025-10-29T08:00:00" ),	"Meet Mickey Mouse", true,	0)
  )
  //private val sortedRides = rides.sortBy(_.dateTime).reverse


  override def listAll: List[RideState] = rides


}
