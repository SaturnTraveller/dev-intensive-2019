package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR


fun Date.format(pattern:String="HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value:Int, units: TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time

    time += when(units){
        TimeUnits.SECOND-> value * SECOND
        TimeUnits.MINUTE-> value * MINUTE
        TimeUnits.HOUR->value * HOUR
        TimeUnits.DAY->value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    var time = this.time
    var currenttime = Date().time
    var nesdate = (currenttime - time)/1000
    var result: String
    when{
        ((nesdate < 2)&&(nesdate>=0)) -> result = "только что"
        nesdate in 2..45 -> result = "несколько секунд назад"
        nesdate in 46..75 -> result = "минуту назад"
        nesdate in 76..2700 -> result = "${nesdate/60} минут назад"
        nesdate in 2701..4500 -> result = "час назад"
        nesdate in 4501..79200 -> result = "${nesdate/3600} часов назад"
        nesdate in 79201..93600 -> result = "день назад"
        nesdate in 93601..31104000 -> result = "${nesdate/86400} дней назад"
        (nesdate > 31104000) -> result = "более года назад"

        else -> result = "humanizeException"
    }

    return result
}


enum class TimeUnits{
    SECOND{
        override var changename = "секунд"
        override var ending1 = "секунду"
        override var ending2 = "ы"
        override var ending3 = ""
          },
    MINUTE{
        override var changename = "минут"
        override var ending1 = "минуту"
        override var ending2 = "ы"
        override var ending3 = ""
        },
    HOUR{
        override var changename = "час"
        override var ending1 = "час"
        override var ending2 = "а"
        override var ending3 = "ов"
        },
    DAY{
        override var changename = "дн"
        override var ending1 = "день"
        override var ending2 = "я"
        override var ending3 = "ей"
    };
    open var changename = ""
    open var ending1 = ""
    open var ending2 = ""
    open var ending3 = ""
    open fun plural(value:Int):String = when(value%100){
        0 -> "$value $changename$ending3"
        1 -> "$value $ending1"
        in 2..4 -> "$value $changename$ending2"
        in 5..20 -> "$value $changename$ending3"
        in 21..99 -> {
            when(value%10){
                0 -> "$value $changename$ending3"
                1 -> "$value $ending1"
                in 2..4 -> "$value $changename$ending2"
                in 5..9 -> "$value $changename$ending3"
                else -> "Exception"
            }
        }
        else -> "Exception"
    }
}

fun Date.retest(value:Int, units: TimeUnits){
    println(units.plural(value))
}
