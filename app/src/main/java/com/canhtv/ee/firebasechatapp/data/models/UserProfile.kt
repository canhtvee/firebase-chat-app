package com.canhtv.ee.firebasechatapp.data.models

class UserProfile {

    var username       : String? = null
    var avatarUrl      : String? = null
    var email          : String? = null
    var onlineStatus       : String? = null
    var uid            : String? = null
    constructor() {}
    constructor(
        email          : String?  ,
        username       : String?  ,
        avatarUrl      : String?  ,
        onlineStatus   : String?

    ){
        this.username  = username
        this.avatarUrl    = avatarUrl
        this.email        = email
        this.onlineStatus = onlineStatus
    }

}