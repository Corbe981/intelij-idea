class Screening(
    val date: String,
    val startTime: String,
    val seats: MutableList<Seat>,
    var totalTakings: Double = 0.0
)