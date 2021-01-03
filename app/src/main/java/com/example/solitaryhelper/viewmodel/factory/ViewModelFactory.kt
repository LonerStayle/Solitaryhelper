package com.example.solitaryhelper.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.solitaryhelper.database.localdb.dao.KaKaoChatDao
import com.example.solitaryhelper.database.localdb.dao.KaKaoDao
import com.example.solitaryhelper.database.localdb.dao.SmsDao
import com.example.solitaryhelper.database.localdb.dao.UserDao
import com.example.solitaryhelper.repository.*
import com.example.solitaryhelper.viewmodel.*


class ViewModelFactory(
    kakaoChatDataSource: KaKaoChatDao,
    kaKaoTalkDataSource: KaKaoDao,
    smsDataDao: SmsDao,
    dataSource: UserDao
) : ViewModelProvider.Factory {

    private val userRepository = UserRepository(dataSource)
    private val kakaoChatDataRepository = KaKaoChatDataRepository(kakaoChatDataSource)
    private val kakaoTalkRepository = KaKaoTalkRepository(kaKaoTalkDataSource)
    private val smsRepository = SmsRepository(smsDataDao)
    private val topicRepository = NaverApiRepository()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        
        when{
            modelClass.isAssignableFrom(KaKaoChatViewModel::class.java) -> {
                @Suppress("unchecked_cast")
                return KaKaoChatViewModel(kakaoChatDataRepository) as T
            }
            modelClass.isAssignableFrom(KaKaoTalkViewModel::class.java) -> {
                @Suppress("unchecked_cast")
                return KaKaoTalkViewModel(kakaoTalkRepository) as T
            }
            modelClass.isAssignableFrom(SmsViewModel::class.java) -> {
                @Suppress("unchecked_cast")
                return SmsViewModel(smsRepository) as T
            }
            modelClass.isAssignableFrom(TopicViewModel::class.java)->{
                @Suppress("unchecked_cast")
                return TopicViewModel(topicRepository) as T
            }
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                @Suppress("unchecked_cast")
                return MainViewModel(userRepository) as T
            }
            modelClass.isAssignableFrom(SharedViewModel::class.java) -> {
                @Suppress("unchecked_cast")
                return SharedViewModel() as T
            }

        }

        throw IllegalAccessException("Unknown ViewModel")
    }
}