package com.canhtv.ee.firebasechatapp.data.models

class Message {

    var messageId :String? =null
    var senderId :String? =null
    var text :String? =null
    constructor() {}
    constructor(
        text :String?,
        senderId :String?,
    ){
        this.text = text
        this.senderId = senderId
    }
}