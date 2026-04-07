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

        film.screenings.forEachIndexed { i, s ->
            println("${i + 1}. ${s.date} ${s.time}")
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

        screening.seats.filter { it.isAvailable }.forEach {
            print("${it.number} ")
        }

        println("\nEnter seats (e.g. A1,A2):")
        val input = readLine()?.split(",") ?: return

        val selectedSeats = screening.seats.filter {
            input.map { it.trim() }.contains(it.number) && it.isAvailable
        }

        if (selectedSeats.isEmpty()) {
            println("No valid seats selected.")
            return
        }

        val totalPrice = selectedSeats.size * film.price

        println("Total price: £$totalPrice")
        println("Enter payment:")

        val payment = readLine()?.toDoubleOrNull() ?: return

        if (payment < totalPrice) {
            println("Not enough money.")
            return
        }

        selectedSeats.forEach { it.isAvailable = false }
        screening.totalSales += totalPrice

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
        println("Time: ${screening.time}")
        println("Seats: ${seats.map { it.number }}")
        println("Price: £$price")
        println("*******************************")
    }
}