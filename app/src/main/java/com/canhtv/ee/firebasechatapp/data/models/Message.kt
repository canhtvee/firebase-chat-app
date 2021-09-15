package com.canhtv.ee.firebasechatapp.data.models

class Message {

    var senderId :String? =null
    var text :String? =null
    constructor() {}
    constructor(
        senderId :String?,
        text :String?,
    ){
        this.senderId = senderId
        this.text = text
    }
}