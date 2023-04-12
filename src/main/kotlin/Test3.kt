fun reverse(str: String) : String{
    val message = str.split(" ")

    val arrData :Array<String?> = arrayOfNulls(message.count())

    for(i in 0 until message.count()){
        arrData[i] = message[i].reversed()
    }

    return arrData.joinToString(" ")
}

fun main(){
    println("===> ${reverse("This is an example!")}")
}