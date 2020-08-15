package com.example.solitaryhelper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.solitaryhelper.localdb.data.KaKaoTalkChatData


class KaKaoChatViewModel() : ViewModel() {


     val myChatText= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText2= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText3= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText4= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText5= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText6= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText7= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText8= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText9= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText10= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText11= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText12= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText13= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText14= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText15= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText16= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText17= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText18= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText19= MutableLiveData<MutableList<KaKaoTalkChatData>>()
     val myChatText20= MutableLiveData<MutableList<KaKaoTalkChatData>>()



    // 전체 리스트 인서트
    fun insertAllList(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText.postValue(myChatList)
    }
    fun insertAllList2(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText2.postValue(myChatList)
    }
    fun insertAllList3(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText3.postValue(myChatList)
    }
    fun insertAllList4(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText4.postValue(myChatList)
    }
    fun insertAllList5(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText5.postValue(myChatList)
    }
    fun insertAllList6(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText6.postValue(myChatList)
    }
    fun insertAllList7(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText7.postValue(myChatList)
    }
    fun insertAllList8(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText8.postValue(myChatList)
    }
    fun insertAllList9(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText9.postValue(myChatList)
    }
    fun insertAllList10(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText10.postValue(myChatList)
    }
    fun insertAllList11(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText11.postValue(myChatList)
    }
    fun insertAllList12(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText12.postValue(myChatList)
    }
    fun insertAllList13(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText13.postValue(myChatList)
    }
    fun insertAllList14(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText14.postValue(myChatList)
    }
    fun insertAllList15(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText15.postValue(myChatList)
    }
    fun insertAllList16(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText16.postValue(myChatList)
    }
    fun insertAllList17(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText17.postValue(myChatList)
    }
    fun insertAllList18(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText18.postValue(myChatList)
    }

    fun insertAllList19(myChatList:MutableList<KaKaoTalkChatData>){
        myChatText19.postValue(myChatList)
    }
    fun insertAllList20(myChatList:MutableList<KaKaoTalkChatData>) {
        myChatText20.postValue(myChatList)

    }
    //하나씩 인서트

    fun insertItemAdd(kakao: KaKaoTalkChatData) {
        myChatText.value?.add(kakao)
        myChatText.value = myChatText.value
    }
    fun insertItemAdd2(kakao: KaKaoTalkChatData) {
        myChatText2.value?.add(kakao)
        myChatText2.value = myChatText2.value
    }
    fun insertItemAdd3(kakao: KaKaoTalkChatData) {
        myChatText3.value?.add(kakao)
        myChatText3.value = myChatText3.value
    }
    fun insertItemAdd4(kakao: KaKaoTalkChatData) {
        myChatText4.value?.add(kakao)
        myChatText4.value = myChatText4.value
    }
    fun insertItemAdd5(kakao: KaKaoTalkChatData) {
        myChatText5.value?.add(kakao)
        myChatText5.value = myChatText5.value
    }
    fun insertItemAdd6(kakao: KaKaoTalkChatData) {
        myChatText6.value?.add(kakao)
        myChatText6.value = myChatText6.value
    }
    fun insertItemAdd7(kakao: KaKaoTalkChatData) {
        myChatText7.value?.add(kakao)
        myChatText7.value = myChatText7.value
    }
    fun insertItemAdd8(kakao: KaKaoTalkChatData) {
        myChatText8.value?.add(kakao)
        myChatText8.value = myChatText8.value
    }
    fun insertItemAdd9(kakao: KaKaoTalkChatData) {
        myChatText9.value?.add(kakao)
        myChatText9.value = myChatText9.value
    }
    fun insertItemAdd10(kakao: KaKaoTalkChatData) {
        myChatText10.value?.add(kakao)
        myChatText10.value = myChatText10.value
    }
    fun insertItemAdd11(kakao: KaKaoTalkChatData) {
        myChatText11.value?.add(kakao)
        myChatText11.value = myChatText11.value
    }
    fun insertItemAdd12(kakao: KaKaoTalkChatData) {
        myChatText12.value?.add(kakao)
        myChatText12.value = myChatText12.value
    }
    fun insertItemAdd13(kakao: KaKaoTalkChatData) {
        myChatText13.value?.add(kakao)
        myChatText13.value = myChatText13.value
    }

    fun insertItemAdd14(kakao: KaKaoTalkChatData) {
        myChatText14.value?.add(kakao)
        myChatText14.value = myChatText14.value
    }
    fun insertItemAdd15(kakao: KaKaoTalkChatData) {
        myChatText15.value?.add(kakao)
        myChatText15.value = myChatText15.value
    }
    fun insertItemAdd16(kakao: KaKaoTalkChatData) {
        myChatText16.value?.add(kakao)
        myChatText16.value = myChatText16.value
    }
    fun insertItemAdd17(kakao: KaKaoTalkChatData) {
        myChatText17.value?.add(kakao)
        myChatText17.value = myChatText17.value
    }
    fun insertItemAdd18(kakao: KaKaoTalkChatData) {
        myChatText18.value?.add(kakao)
        myChatText18.value = myChatText18.value
    }

    fun insertItemAdd19(kakao: KaKaoTalkChatData) {
        myChatText19.value?.add(kakao)
        myChatText19.value = myChatText19.value
    }

    fun insertItemAdd20(kakao: KaKaoTalkChatData) {
        myChatText20.value?.add(kakao)
        myChatText20.value = myChatText20.value
    }

}

