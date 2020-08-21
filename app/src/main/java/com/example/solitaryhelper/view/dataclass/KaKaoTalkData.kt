package com.example.solitaryhelper.view.dataclass

//
data class KaKaoTalkData(

    val id:Long,
    val name:String,
    val image:String,
    val textBoxList:Array<Array<String>>,
    var itemLastText: String,
    var chatNotification: Int,
    var messageArrivalTime: Array<Array<String>>?,
    var visibleSettingList: Int?,
    var itemTimeLast:String?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KaKaoTalkData

        if (name != other.name) return false
        if (image != other.image) return false
        if (!textBoxList.contentDeepEquals(other.textBoxList)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + textBoxList.contentDeepHashCode()
        return result
    }
}
