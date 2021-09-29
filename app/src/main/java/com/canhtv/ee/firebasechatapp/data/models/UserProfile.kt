package com.canhtv.ee.firebasechatapp.data.models

class UserProfile {

    var username       : String? = null
    var avatarUrl      : String? = null
    var email          : String? = null

    var isOnline       : String? = null

//    var uid            : String? = null
    constructor() {}
    constructor(
        email          : String?  ,
        username       : String?  ,
        avatarUrl      : String?  ,
        isOnline       : String?

    ){
        this.username  = username
        this.avatarUrl = avatarUrl
        this.email     = email
        this.isOnline  = isOnline
    }

}