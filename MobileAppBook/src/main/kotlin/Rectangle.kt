class Rectangle {
    var width: Int
    var height: Int

    constructor(width: Int, height: Int){
        this.width = if (width>0) width else 0
        this.height = if(height>0) height else 0
    }

    fun getArea() : Int = this.width*this.height

    constructor(width: Int){
        this.width = if(width>0)  width else 0
        this.height = if(width>0) width else 0 //จำเป็นต้องกำหนด เพราะ getArea() มีการเรียกใช้
    }
}



fun main(){
    val rect = Rectangle(width = 10, height = 15)
    println(rect.getArea())
    rect.height = 20
    println(rect.getArea())

    val rect2 = Rectangle(width = 10)
    println(rect2.getArea())
}