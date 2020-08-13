package com.example.solitaryhelper.localdb.data

data class KaKaoTalkData(
    val image:String,
    val name:String,
    val textList:Array<Array<String>>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KaKaoTalkData

        if (image != other.image) return false
        if (name != other.name) return false
        if (!textList.contentDeepEquals(other.textList)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = image.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + textList.contentDeepHashCode()
        return result
    }
}