data class Client(val name: String, val age: Int, val balance: Int){
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other !is Client) return false
        return age == other.age && this.name == other.name
    }
}

fun main() {
    //implement your code here
    val name:String = readln()
    val age:Int = readln().toInt()
    val balance:Int = readln().toInt()
    val name2:String = readln()
    val age2:Int = readln().toInt()
    val balance2:Int = readln().toInt()
    val client: Client = Client(name,age,balance)
    val client2: Client = Client(name2,age2,balance2)
    println(client.equals(client2))

}