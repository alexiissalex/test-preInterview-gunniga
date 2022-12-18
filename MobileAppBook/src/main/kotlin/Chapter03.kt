/*
import java.text.DecimalFormat
import java.text.NumberFormat
fun main(){
        //Format by NumberFormat
        */
/*
        val numFormate = NumberFormat.getInstance()
        numFormate.minimumIntegerDigits = 10
        numFormate.minimumFractionDigits = 4
        val str = numFormate.format(1234567.89)
        println(str)

        val num = numFormate.parse(str) //chang string type to primitive type
        val x = num.toDouble()
        println(x)
        *//*


        */
/*
        //Format by DecimalFormat
        val pattern = "#,#"
        var decimalFormat = DecimalFormat()
//        decimalFormat.applyPattern(pattern)
        var str = decimalFormat.format(1234567)
        println(str)

        decimalFormat.applyPattern("00000")
        str = decimalFormat.format(123)
        println(str)

        decimalFormat.applyPattern("#.##%")
        str = decimalFormat.format(0.086)
        println(str)

        decimalFormat.applyPattern("00.00")
        str = decimalFormat.format(1.2)
        println(01.20)
        *//*

        repeatString("FirstA",3,"SecondB")
        thaiLongDate(2,1,2018)
}

fun repeatString(str: String, times: Int, separator: String){
        if(str != "" && times >0){
                var text = ""
                for(i in 1..times){
                        if(i>1){
                                text +=separator
                        }
                        text += str
                }
                println(text)
        }
}

fun thaiLongDate(day: Int, month: Int, yearCE: Int){
        val m = arrayOf("มกราคม","กุมภาพันธ์","มีนาคม","ธันวาคม")
        var yearBE = yearCE
        yearBE += 543
        print("$day ${m[month-1]} $yearBE")
}*/
