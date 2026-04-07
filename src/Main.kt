

fun main() {
    // Film 1
    val seats1 = MutableList(10) { Seat("A${it + 1}") }
    val seats2 = MutableList(10) { Seat("B${it + 1}") }

    val screening1 = Screening("2026-04-10", "10:00", seats1)
    val screening2 = Screening("2026-04-10", "18:00", seats2)

    val film1 = Film(
        "Avatar",
        "Sci-Fi",
        10.0,
        mutableListOf(screening1, screening2)
    )

    // Film 2
    val seats3 = MutableList(10) { Seat("C${it + 1}") }
    val seats4 = MutableList(10) { Seat("D${it + 1}") }

    val screening3 = Screening("2026-04-11", "12:00", seats3)
    val screening4 = Screening("2026-04-11", "20:00", seats4)

    val film2 = Film(
        "Inception",
        "Action",
        12.0,
        mutableListOf(screening3, screening4)
    )

    val films = listOf(film1, film2)

    val service = BookingService()

    val selectedFilm = service.searchFilms(films) ?: return
    val selectedScreening = service.selectScreening(selectedFilm) ?: return

    service.bookSeats(selectedScreening, selectedFilm)
}