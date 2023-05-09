package parking

import java.util.*

var spots: Array<Car?>? = null


fun sorry() {
    println("Sorry, a parking lot has not been created.")
}

fun main() {
    var parkingLotCreated = false
    while (true) {
        val userInput = readln().split(' ')
        if (!parkingLotCreated && userInput[0] != "create" && userInput[0] != "exit") {
            sorry()
            continue
        }
        when (userInput[0]) {
            "park" -> park(userInput[1], userInput[2])
            "leave" -> leave(userInput[1].toInt())
            "status" -> printStatus()
            "reg_by_color" -> regByColor(userInput[1])
            "spot_by_color" -> spotByColor(userInput[1])
            "spot_by_reg" -> spotByReg(userInput[1])
            "create" -> {
                createSpot(userInput[1].toInt())
                parkingLotCreated = true
            }
            "exit" -> break
        }
    }
}


fun spotByReg(reg: String) {
    for (i in spots!!.indices) {
        if (spots!![i]?.registration.equals(reg, true)) {
            println(i+1)
            return
        }
    }
    println("No cars with registration number $reg were found.")


}

fun spotByColor(color: String) {
    val coloredCars = mutableListOf<Int>()
    for (i in spots!!.indices) {
        if (spots!![i]?.color.equals(color, true)) {
            spots!![i]?.let { coloredCars.add(i+1) }
        }
    }
    if (coloredCars.isEmpty()) {
        println("No cars with color ${color.uppercase(Locale.getDefault())} were found.")
    } else {
        println(coloredCars.joinToString(separator = ", ") { it.toString() })
    }

}


fun regByColor(color: String) {
    val coloredCars = mutableListOf<String>()
    for (s in spots!!) {
        if (s?.color.equals(color, true)) {
            s?.registration?.let { coloredCars.add(it) }
        }
    }
    if (coloredCars.isEmpty()) {
        println("No cars with color ${color.uppercase(Locale.getDefault())} were found.")
    } else {
        println(coloredCars.joinToString(separator = ", ") { it })
    }

}

fun printStatus() {
    var empty = 0

    for (i in spots!!.indices) {
        if (spots!![i] == null) {
            empty++
        } else println("${i + 1} ${spots!![i]?.registration} ${spots!![i]?.color}")
    }
    if (empty == spots!!.size) {
        println("Parking lot is empty.")
    }


}

fun createSpot(size: Int) {
    spots = arrayOfNulls(size)
    println("Created a parking lot with $size spots.")
}

fun leave(spot: Int) {

    spots?.get(spot - 1)?.let {
        spots!![spot - 1] = null
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

    for (i in spots?.indices!!) {
        if (spots!![i] == null) {
            spots!![i] = Car(registration, color)
            println("${spots!![i]?.color} car parked in spot ${i + 1}.")
            return
        }
    }
    println("Sorry, the parking lot is full.")


}
