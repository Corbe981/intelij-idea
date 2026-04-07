class Screening(
    val date: String,
    val time: String,
    val seats: MutableList<Seat>,
    var totalSales: Double = 0.0
)