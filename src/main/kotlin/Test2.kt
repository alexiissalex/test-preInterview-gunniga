import java.text.DecimalFormat
import kotlin.math.pow

interface Squ{
    fun SquareDigits(n:Int) :Int
}

fun main(){
    var sq = object :Squ{
        override fun SquareDigits(n: Int): Int {
            val pattern = "#,#"
            val decimalFormat = DecimalFormat()
            decimalFormat.applyPattern(pattern)
            val str = decimalFormat.format(n)

            val s = str.split(",")
            val result = s.map { it.toDouble() }
            var sum = ""
            for (x in result) {
                sum += (x.pow(2).toInt()).toString()
            }
            return sum.toInt()
        }
    }
    print(sq.SquareDigits(9119))
}