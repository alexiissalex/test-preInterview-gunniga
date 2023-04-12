//Not finished

/*
import java.lang.Exception
import java.util.ArrayList
import java.util.Random

class OddEvenList<T>(value: T){
    private  var v = value as Any
    private var list = mutableListOf<T>()
    fun add(input:T):Int{
        list.add(input)
        return list.size-1
    }
    fun remove(index: Int):Boolean {
        return try {
            list.removeAt(index)
            println("ลบข้อมูลสำเร็จ")
            true
        }catch (ex: Exception){
            println("ไม่มีลำดับที่ลบในรายการ")
            false
            }
//           if (index <= list.size-1){
//                list.removeAt(index)
//                println("ลบข้อมูลสำเร็จ")
//            }else{
//                println("ไม่มีลำดับที่ลบในรายการ")
//            }
        }
    fun getOdd(): Array<T> {
         list.filterNot { it % 2 == 0 }
    }
    fun getEven(): Array<T> = list.filter { it % 2 == 0}.toTypedArray()
    fun getRandom(): Array<T>
fun main(){
    val OddEvent = OddEvenList(2)
    OddEvent.add(3)

}*/
