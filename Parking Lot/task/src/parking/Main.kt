package parking
const val NUMBER_OF_SPOTS: Int = 20
val spots: Array<Car?> = arrayOfNulls(NUMBER_OF_SPOTS)

fun main() {
    while (true) {
        val userInput = readln().split(' ')
        when (userInput[0]) {
            "park" -> park(userInput[1], userInput[2])
            "leave" -> leave(userInput[1].toInt())
            "exit" -> break
        }

    }
}

fun leave(spot: Int) {

    spots[spot - 1]?.let {
        spots[spot-1] = null
        println("Spot $spot is free.")
    } ?: run {
        println("There is no car in spot $spot.")
    }
}

data class Car(val registration: String, val color: String) {
    init {
        require(!registration.contains(" ")) { "Registration cannot contain whitespaces" }
    }
}

fun park(registration: String, color: String) {
    spots.forEachIndexed { index, value ->
        if (value == null) {
            spots[index] = Car(registration, color)
            println("${spots[index]!!.color} car parked in spot ${index + 1}.")
            return
        }
    }
    println("Sorry, the parking lot is full.")


}
