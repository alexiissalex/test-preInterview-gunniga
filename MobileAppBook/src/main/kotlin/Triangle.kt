class Triangle (base: Int, height: Int){
    var base: Int = base
    var height: Int = height

    fun getArea() = 0.5*this.base*this.height
}

fun main(){
    val t = Triangle(5,10)
    println(t.getArea())
    t.base = 15
    t.height = 4
    println(t.getArea())
}