class Data<T>(value:T) {
    private var v = value as Any
    fun type() = v::class.simpleName
    fun value() : T = v as T
    fun setValue(value: T){
        v =value as Any
    }
}

fun main(){
    val data1: Data<Int> = Data<Int>(100)
    val data2: Data<Double> = Data(12.34)
    val data3 = Data<String>("Hello")
    val data4 = Data(true)
    println(data1.type())
    println(data2.type())
    println(data3.type())
    println(data4.type())
}