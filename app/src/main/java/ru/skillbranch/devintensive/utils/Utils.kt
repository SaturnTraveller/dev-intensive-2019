package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?): Pair<String?,String?>{
        val parts: List<String>? = fullName?.split(" ")

        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        if(firstName == " ") firstName = null
        if(firstName == "") firstName = null
        if(lastName == " ") lastName = null
        if(lastName == "") lastName = null

        return firstName to lastName
    }

    fun toInitials(firstName:String?,lastName:String?):String?{
        var result: String? = "${firstName?.get(0)}${lastName?.get(0)}"
        if(firstName == null){
            if(lastName == null) {
                result = "null"
            } else {
                result = lastName.get(0).toString()
            }
        } else{
            if(lastName == null){
                result = firstName.get(0).toString()
            }
        }
        return result


    }

    fun transliteration(payload: String,divider:String = " "):String{
        val alphaBet = mapOf("а" to "a","б" to "b","в" to "v","г" to "g","д" to "d","е" to "e","ё" to "e","ж" to "zh","з" to "z","и" to "i","й" to "i","к" to "k","л" to "l","м" to "m","н" to "n","о" to "o","п" to "p","р" to "r","с" to "s","т" to "t","у" to "u","ф" to "f","х" to "h","ц" to "c","ч" to "ch","ш" to "sh","щ" to "sh'","ъ" to "","ы" to "i","ь" to "","э" to "e","ю" to "yu","я" to "ya")
        var result: String = ""
        for(i in 0..(payload.length-1)){

            if(payload.get(i).isUpperCase()){
                result = result + "${(alphaBet.get(payload.get(i).lowercase()))?.uppercase()}"
                continue
            }
            // if((payload.get(i).toString() == "")||(payload.get(i).toString() == " ")) continue
            if(payload.get(i).toString() == " ") {
                result = result + "${divider}"
                continue
            }
            result = result + "${alphaBet.get(payload.get(i).toString())}"
        }
        println(payload.length)
        return result
    }
}