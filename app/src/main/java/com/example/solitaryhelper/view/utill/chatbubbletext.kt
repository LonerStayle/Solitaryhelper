package com.example.solitaryhelper.view.utill

fun chatTextListControl(textList: Array<String>): MutableList<String> {


    var index = 0
    while (index < textList.size) {
        for (length in 21 until textList[index].length step 21) {
            textList[index] =
                textList[index].substring(0..length) + "\n" + textList[index].substring(
                    length + 1, textList[index].length)
        }
        index++
    }
    return textList.toMutableList()
}


fun chatTextControl(text: String): String {

    var newText = text
    for (length in 21 until text.length step 21) {
        newText = text.substring(0..length) + "\n" + text.substring(
            length + 1,
            text.length
        )
    }
    return newText
}



