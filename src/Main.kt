

fun main() {
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

    val films = listOf(film1)

    val service = BookingService()

    val selectedFilm = service.searchFilms(films) ?: return
    val selectedScreening = service.selectScreening(selectedFilm) ?: return

    service.bookSeats(selectedScreening, selectedFilm)
}