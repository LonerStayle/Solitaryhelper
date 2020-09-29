package com.example.solitaryhelper.database.localdb.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.solitaryhelper.database.localdb.entitiy.*

@Dao
interface KaKaoChatDao {

    @Query("SELECT * FROM KaKaoTalkChatData")
    fun getAllList():LiveData<List<KaKaoTalkChatData>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(kaoTalkChatData: KaKaoTalkChatData)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert(kaoTalkChatData: List<KaKaoTalkChatData>)


    @Query("SELECT * FROM KaKaoTalkChatData1")
    fun getAllList1():LiveData<List<KaKaoTalkChatData1>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert1(kaoTalkChatData: KaKaoTalkChatData1)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert1(kaoTalkChatData: List<KaKaoTalkChatData1>)


    @Query("SELECT * FROM KaKaoTalkChatData2")
    fun getAllList2():LiveData<List<KaKaoTalkChatData2>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert2(kaoTalkChatData: KaKaoTalkChatData2)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert2(kaoTalkChatData: List<KaKaoTalkChatData2>)


    @Query("SELECT * FROM KaKaoTalkChatData3")
    fun getAllList3():LiveData<List<KaKaoTalkChatData3>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert3(kaoTalkChatData: KaKaoTalkChatData3)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert3(kaoTalkChatData: List<KaKaoTalkChatData3>)


    @Query("SELECT * FROM KaKaoTalkChatData4")
    fun getAllList4():LiveData<List<KaKaoTalkChatData4>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert4(kaoTalkChatData: KaKaoTalkChatData4)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert4(kaoTalkChatData: List<KaKaoTalkChatData4>)


    @Query("SELECT * FROM KaKaoTalkChatData5")
    fun getAllList5():LiveData<List<KaKaoTalkChatData5>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert5(kaoTalkChatData: KaKaoTalkChatData5)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert5(kaoTalkChatData: List<KaKaoTalkChatData5>)


    @Query("SELECT * FROM KaKaoTalkChatData6")
    fun getAllList6():LiveData<List<KaKaoTalkChatData6>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert6(kaoTalkChatData: KaKaoTalkChatData6)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert6(kaoTalkChatData: List<KaKaoTalkChatData6>)


    @Query("SELECT * FROM KaKaoTalkChatData7")
    fun getAllList7():LiveData<List<KaKaoTalkChatData7>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert7(kaoTalkChatData: KaKaoTalkChatData7)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert7(kaoTalkChatData: List<KaKaoTalkChatData7>)


    @Query("SELECT * FROM KaKaoTalkChatData8")
    fun getAllList8():LiveData<List<KaKaoTalkChatData8>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert8(kaoTalkChatData: KaKaoTalkChatData8)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert8(kaoTalkChatData: List<KaKaoTalkChatData8>)


    @Query("SELECT * FROM KaKaoTalkChatData9")
    fun getAllList9():LiveData<List<KaKaoTalkChatData9>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert9(kaoTalkChatData: KaKaoTalkChatData9)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert9(kaoTalkChatData: List<KaKaoTalkChatData9>)


    @Query("SELECT * FROM KaKaoTalkChatData10")
    fun getAllList10():LiveData<List<KaKaoTalkChatData10>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert10(kaoTalkChatData: KaKaoTalkChatData10)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert10(kaoTalkChatData: List<KaKaoTalkChatData10>)


    @Query("SELECT * FROM KaKaoTalkChatData11")
    fun getAllList11():LiveData<List<KaKaoTalkChatData11>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert11(kaoTalkChatData: KaKaoTalkChatData11)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert11(kaoTalkChatData: List<KaKaoTalkChatData11>)


    @Query("SELECT * FROM KaKaoTalkChatData12")
    fun getAllList12():LiveData<List<KaKaoTalkChatData12>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert12(kaoTalkChatData: KaKaoTalkChatData12)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert12(kaoTalkChatData: List<KaKaoTalkChatData12>)


    @Query("SELECT * FROM KaKaoTalkChatData13")
    fun getAllList13():LiveData<List<KaKaoTalkChatData13>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert13(kaoTalkChatData: KaKaoTalkChatData13)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert13(kaoTalkChatData: List<KaKaoTalkChatData13>)


    @Query("SELECT * FROM KaKaoTalkChatData14")
    fun getAllList14():LiveData<List<KaKaoTalkChatData14>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert14(kaoTalkChatData: KaKaoTalkChatData14)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert14(kaoTalkChatData: List<KaKaoTalkChatData14>)


    @Query("SELECT * FROM KaKaoTalkChatData15")
    fun getAllList15():LiveData<List<KaKaoTalkChatData15>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert15(kaoTalkChatData: KaKaoTalkChatData15)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert15(kaoTalkChatData: List<KaKaoTalkChatData15>)


    @Query("SELECT * FROM KaKaoTalkChatData16")
    fun getAllList16():LiveData<List<KaKaoTalkChatData16>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert16(kaoTalkChatData: KaKaoTalkChatData16)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert16(kaoTalkChatData: List<KaKaoTalkChatData16>)


    @Query("SELECT * FROM KaKaoTalkChatData17")
    fun getAllList17():LiveData<List<KaKaoTalkChatData17>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert17(kaoTalkChatData: KaKaoTalkChatData17)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert17(kaoTalkChatData: List<KaKaoTalkChatData17>)


    @Query("SELECT * FROM KaKaoTalkChatData18")
    fun getAllList18():LiveData<List<KaKaoTalkChatData18>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert18(kaoTalkChatData: KaKaoTalkChatData18)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert18(kaoTalkChatData: List<KaKaoTalkChatData18>)


    @Query("SELECT * FROM KaKaoTalkChatData19")
    fun getAllList19():LiveData<List<KaKaoTalkChatData19>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert19(kaoTalkChatData: KaKaoTalkChatData19)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun allInsert19(kaoTalkChatData: List<KaKaoTalkChatData19>)

}