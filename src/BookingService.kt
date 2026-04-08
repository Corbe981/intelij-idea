class BookingService {

    fun searchFilms(films: List<Film>): Film? {
        println("Search film by title or genre:")
        val input = readLine()?.lowercase() ?: ""
        val results = films.filter {
            it.title.lowercase().contains(input) ||
            it.genre.lowercase().contains(input)
        }

        if (results.isEmpty()) {
            println("No films found.")
            return null
        }

        results.forEachIndexed { i, film ->
            println("${i + 1}. ${film.title} (${film.genre})")
        }

        println("Select a film:")
        val choice = readLine()?.toIntOrNull()

        return if (choice != null && choice in 1..results.size) {
            results[choice - 1]
        } else {
            println("Invalid choice.")
            null
        }
    }

    fun selectScreening(film: Film): Screening? {
        println("Available screenings:")

        film.screenings.forEachIndexed { i, screening ->
            println("${i + 1}. ${screening.date} ${screening.startTime}")
        }

        println("Select screening:")
        val choice = readLine()?.toIntOrNull()

        return if (choice != null && choice in 1..film.screenings.size) {
            film.screenings[choice - 1]
        } else {
            println("Invalid choice.")
            null
        }
    }

    fun bookSeats(screening: Screening, film: Film) {
        println("Available seats:")

        screening.seats.filter { it.isAvailable }.forEach { seat ->
            print("${seat.seatNumber} ")
        }

        println("\nEnter seats (e.g. A1,A2):")
        val input = readLine()?.split(",") ?: return

        val selectedSeats = screening.seats.filter { seat ->
            input.map { it.trim() }.contains(seat.seatNumber) && seat.isAvailable
        }

        if (selectedSeats.isEmpty()) {
            println("No valid seats selected.")
            return
        }

        val totalPrice = selectedSeats.size * film.basePrice

        println("Total price: £$totalPrice")
        println("Enter payment:")

        val payment = readLine()?.toDoubleOrNull() ?: return

        if (payment < totalPrice) {
            println("Not enough money.")
            return
        }

        selectedSeats.forEach { seat ->
            seat.isAvailable = false
        }
        screening.totalTakings += totalPrice

        printTicket(film, screening, selectedSeats, totalPrice)
    }

    private fun printTicket(
        film: Film,
        screening: Screening,
        seats: List<Seat>,
        price: Double
    ) {
        println("*******************************")
        println("CINEMA NAME")
        println("Film: ${film.title}")
        println("Date: ${screening.date}")
        println("Time: ${screening.startTime}")
        println("Seats: ${seats.map { it.seatNumber }}")
        println("Price: £$price")
        println("*******************************")
    }
}