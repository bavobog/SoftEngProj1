package domain

import java.time.LocalDateTime

case class RideState(id: Int, dateTime: LocalDateTime, rideName: String, isOpen: Boolean, waitTime: Int)
