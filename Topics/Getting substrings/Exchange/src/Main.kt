fun main() {
    // put your code here
    val input = readln()
    val first = input.first().toString()
    val last = input.last().toString()
    val output: String = last + input.subSequence(1, input.length-1) + first
    println(output)
}