package parking

import java.util.*

var spots: Array<Car?>? = null

fun main() {
    while(true){
            val userInput = readln().split(' ')
            when (userInput[0]){
                "park"-> park(userInput[1], userInput[2])
                "leave"-> leave(userInput[1].toInt())
                "create" -> createSpot(userInput[1].toInt())
                "status" -> printStatus()
                "exit" -> break
             }

    }
}

fun printStatus() {
    var empty: Int = 0
    if(spots == null){
        println("Sorry, a parking lot has not been created.")
    }else{
        for (i in spots!!.indices){
            if(spots!![i] == null){
                empty++
            }else println("${i +1 } ${spots!![i]?.registration} ${spots!![i]?.color}")
        }
        if(empty == spots!!.size){
            println("Parking lot is empty.")
        }
    }

}

fun createSpot(size: Int) {
    spots = arrayOfNulls<Car>(size)
    println("Created a parking lot with $size spots.")
}

fun leave(spot: Int) {
    if(spots == null){
        println("Sorry, a parking lot has not been created.")
    }else{
        spots?.get(spot-1)?.let{
            spots!![spot-1] = null
            println("Spot $spot is free.")
        } ?: run{
            println("There is no car in spot $spot.")
        }
    }

}

data class Car(val registration: String, val color: String) {
    init {
        require(!registration.contains(" ")) { "Registration cannot contain whitespaces" }
    }

    val capitalizedColor: String
        get() = color.capitalize(Locale.getDefault())
}

fun park(registration: String, color: String){
    if(spots == null){
        println("Sorry, a parking lot has not been created.")
    }else{
        for (i in spots?.indices!!){
            if(spots!![i] == null){
                spots!![i] = Car(registration, color)
                println("${spots!![i]?.color} car parked in spot ${i+1}.")
                return
            }
        }
        println("Sorry, the parking lot is full.")

    }


}
