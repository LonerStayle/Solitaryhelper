package com.example.solitaryhelper.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.solitaryhelper.database.localdb.SolitaryHelperDatabase
import com.example.solitaryhelper.database.localdb.dao.KaKaoChatDao
import com.example.solitaryhelper.database.localdb.dao.KaKaoDao
import com.example.solitaryhelper.database.localdb.entitiy.*


interface KaKaoChatDataSource {

    fun getAllList(): LiveData<List<KaKaoTalkChatData>>
    fun insert(kaoTalkChatData: KaKaoTalkChatData)

    fun getAllList1(): LiveData<List<KaKaoTalkChatData1>>
    fun insert1(kaoTalkChatData: KaKaoTalkChatData1)

    fun getAllList2(): LiveData<List<KaKaoTalkChatData2>>
    fun insert2(kaoTalkChatData: KaKaoTalkChatData2)

    fun getAllList3(): LiveData<List<KaKaoTalkChatData3>>
    fun insert3(kaoTalkChatData: KaKaoTalkChatData3)

    fun getAllList4(): LiveData<List<KaKaoTalkChatData4>>
    fun insert4(kaoTalkChatData: KaKaoTalkChatData4)

    fun getAllList5(): LiveData<List<KaKaoTalkChatData5>>
    fun insert5(kaoTalkChatData: KaKaoTalkChatData5)

    fun getAllList6(): LiveData<List<KaKaoTalkChatData6>>
    fun insert6(kaoTalkChatData: KaKaoTalkChatData6)

    fun getAllList7(): LiveData<List<KaKaoTalkChatData7>>
    fun insert7(kaoTalkChatData: KaKaoTalkChatData7)

    fun getAllList8(): LiveData<List<KaKaoTalkChatData8>>
    fun insert8(kaoTalkChatData: KaKaoTalkChatData8)

    fun getAllList9(): LiveData<List<KaKaoTalkChatData9>>
    fun insert9(kaoTalkChatData: KaKaoTalkChatData9)

    fun getAllList10(): LiveData<List<KaKaoTalkChatData10>>
    fun insert10(kaoTalkChatData: KaKaoTalkChatData10)

    fun getAllList11(): LiveData<List<KaKaoTalkChatData11>>
    fun insert11(kaoTalkChatData: KaKaoTalkChatData11)

    fun getAllList12(): LiveData<List<KaKaoTalkChatData12>>
    fun insert12(kaoTalkChatData: KaKaoTalkChatData12)

    fun getAllList13(): LiveData<List<KaKaoTalkChatData13>>
    fun insert13(kaoTalkChatData: KaKaoTalkChatData13)

    fun getAllList14(): LiveData<List<KaKaoTalkChatData14>>
    fun insert14(kaoTalkChatData: KaKaoTalkChatData14)

    fun getAllList15(): LiveData<List<KaKaoTalkChatData15>>
    fun insert15(kaoTalkChatData: KaKaoTalkChatData15)

    fun getAllList16(): LiveData<List<KaKaoTalkChatData16>>
    fun insert16(kaoTalkChatData: KaKaoTalkChatData16)

    fun getAllList17(): LiveData<List<KaKaoTalkChatData17>>
    fun insert17(kaoTalkChatData: KaKaoTalkChatData17)

    fun getAllList18(): LiveData<List<KaKaoTalkChatData18>>
    fun insert18(kaoTalkChatData: KaKaoTalkChatData18)

    fun getAllList19(): LiveData<List<KaKaoTalkChatData19>>
    fun insert19(kaoTalkChatData: KaKaoTalkChatData19)
}

class KaKaoChatDataRepository(private val databaseDao: KaKaoChatDao) : KaKaoChatDataSource {

    override fun getAllList(): LiveData<List<KaKaoTalkChatData>> {
        return databaseDao.getAllList()
    }


    override fun insert(kaoTalkChatData: KaKaoTalkChatData) {
        databaseDao.insert(kaoTalkChatData)
    }

    override fun getAllList1(): LiveData<List<KaKaoTalkChatData1>> {
        return databaseDao.getAllList1()
    }

    override fun insert1(kaoTalkChatData: KaKaoTalkChatData1) {
        databaseDao.insert1(kaoTalkChatData)
    }

    override fun getAllList2(): LiveData<List<KaKaoTalkChatData2>> {
        return databaseDao.getAllList2()
    }

    override fun insert2(kaoTalkChatData: KaKaoTalkChatData2) {
        databaseDao.insert2(kaoTalkChatData)
    }

    override fun getAllList3(): LiveData<List<KaKaoTalkChatData3>> {
        return databaseDao.getAllList3()
    }

    override fun insert3(kaoTalkChatData: KaKaoTalkChatData3) {
        databaseDao.insert3(kaoTalkChatData)
    }

    override fun getAllList4(): LiveData<List<KaKaoTalkChatData4>> {
        return databaseDao.getAllList4()
    }

    override fun insert4(kaoTalkChatData: KaKaoTalkChatData4) {
        databaseDao.insert4(kaoTalkChatData)
    }

    override fun getAllList5(): LiveData<List<KaKaoTalkChatData5>> {
        return databaseDao.getAllList5()
    }

    override fun insert5(kaoTalkChatData: KaKaoTalkChatData5) {
        databaseDao.insert5(kaoTalkChatData)
    }

    override fun getAllList6(): LiveData<List<KaKaoTalkChatData6>> {
        return databaseDao.getAllList6()
    }

    override fun insert6(kaoTalkChatData: KaKaoTalkChatData6) {
        databaseDao.insert6(kaoTalkChatData)
    }

    override fun getAllList7(): LiveData<List<KaKaoTalkChatData7>> {
        return databaseDao.getAllList7()
    }

    override fun insert7(kaoTalkChatData: KaKaoTalkChatData7) {
        databaseDao.insert7(kaoTalkChatData)
    }

    override fun getAllList8(): LiveData<List<KaKaoTalkChatData8>> {
        return databaseDao.getAllList8()
    }

    override fun insert8(kaoTalkChatData: KaKaoTalkChatData8) {
        databaseDao.insert8(kaoTalkChatData)
    }

    override fun getAllList9(): LiveData<List<KaKaoTalkChatData9>> {
        return databaseDao.getAllList9()
    }

    override fun insert9(kaoTalkChatData: KaKaoTalkChatData9) {
        databaseDao.insert9(kaoTalkChatData)
    }

    override fun getAllList10(): LiveData<List<KaKaoTalkChatData10>> {
        return databaseDao.getAllList10()
    }

    override fun insert10(kaoTalkChatData: KaKaoTalkChatData10) {
        databaseDao.insert10(kaoTalkChatData)
    }

    override fun getAllList11(): LiveData<List<KaKaoTalkChatData11>> {
        return databaseDao.getAllList11()
    }

    override fun insert11(kaoTalkChatData: KaKaoTalkChatData11) {
        databaseDao.insert11(kaoTalkChatData)
    }

    override fun getAllList12(): LiveData<List<KaKaoTalkChatData12>> {
        return databaseDao.getAllList12()
    }

    override fun insert12(kaoTalkChatData: KaKaoTalkChatData12) {
        databaseDao.insert12(kaoTalkChatData)
    }

    override fun getAllList13(): LiveData<List<KaKaoTalkChatData13>> {
        return databaseDao.getAllList13()
    }

    override fun insert13(kaoTalkChatData: KaKaoTalkChatData13) {
        databaseDao.insert13(kaoTalkChatData)
    }

    override fun getAllList14(): LiveData<List<KaKaoTalkChatData14>> {
        return databaseDao.getAllList14()
    }

    override fun insert14(kaoTalkChatData: KaKaoTalkChatData14) {
        databaseDao.insert14(kaoTalkChatData)
    }

    override fun getAllList15(): LiveData<List<KaKaoTalkChatData15>> {
        return databaseDao.getAllList15()
    }

    override fun insert15(kaoTalkChatData: KaKaoTalkChatData15) {
        databaseDao.insert15(kaoTalkChatData)
    }

    override fun getAllList16(): LiveData<List<KaKaoTalkChatData16>> {
        return databaseDao.getAllList16()
    }

    override fun insert16(kaoTalkChatData: KaKaoTalkChatData16) {
        databaseDao.insert16(kaoTalkChatData)
    }

    override fun getAllList17(): LiveData<List<KaKaoTalkChatData17>> {
        return databaseDao.getAllList17()
    }

    override fun insert17(kaoTalkChatData: KaKaoTalkChatData17) {
        databaseDao.insert17(kaoTalkChatData)
    }

    override fun getAllList18(): LiveData<List<KaKaoTalkChatData18>> {
        return databaseDao.getAllList18()
    }

    override fun insert18(kaoTalkChatData: KaKaoTalkChatData18) {
        databaseDao.insert18(kaoTalkChatData)
    }

    override fun getAllList19(): LiveData<List<KaKaoTalkChatData19>> {
        return databaseDao.getAllList19()
    }

    override fun insert19(kaoTalkChatData: KaKaoTalkChatData19) {
        databaseDao.insert19(kaoTalkChatData)
    }

}