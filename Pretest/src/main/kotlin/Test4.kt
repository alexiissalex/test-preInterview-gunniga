fun SumPoint(str: String): String{

    val Point: MutableMap<Char,Int> =
        mutableMapOf('a' to 1,'b' to 2,'c' to 3,'d' to 4,
            'e' to 5, 'f' to 6, 'g' to 7, 'h' to 8, 'i' to 9,
            'j' to 10, 'k' to 11, 'l' to 12, 'm' to 13, 'n' to 14,
            'o' to 15, 'p' to 16, 'q' to 17, 'r' to 18, 's' to 19,
            't' to 20, 'u' to 21, 'v' to 22,'w' to 23,'x' to 24,
            'y' to 25, 'z' to 26)

    val message = str.split(" ")
    var sum: Int = 0
    var sumTotal: Int = 0
    val arrKeyAndValue : MutableMap<Int,String> = mutableMapOf()

    for (x in 0 until message.size){
        sum = 0
        val arrStr = message[x]
        for (i in 0 until arrStr.length){
            var s = Point.get(arrStr[i].lowercaseChar())
            if (s != null) {
                sum += s
            }
        }
        arrKeyAndValue.put(sum,message[x])
//        if (sumTotal < sum){
//            sumTotal = sum
//            valueMax = message[x].lowercase()
//        }else{
//            continue
//        }
    }

    for (k in arrKeyAndValue.keys){
//        println(k)
        if (k>sumTotal){
//            println("ค่ามากที่สุดตอนนี้คือ ${k}")
            sumTotal = k
        }
    }
    return "=> ${arrKeyAndValue.get(sumTotal)?.lowercase()}"
//    println("ค่ามากที่สุด ${valueMax}")
//    return valueMax
}
fun main(){
    val maxvalue = SumPoint("this is a word")
    println(maxvalue)
}