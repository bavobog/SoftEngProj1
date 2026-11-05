package domain

trait RideRepository {

  def listAll: List[RideState]

}
