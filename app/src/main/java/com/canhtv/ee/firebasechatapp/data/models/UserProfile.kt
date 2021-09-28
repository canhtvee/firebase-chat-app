package com.canhtv.ee.firebasechatapp.data.models

class UserProfile {

    var uid            : String? = null
    var username       : String? = null
    var avatarUrl      : String? = null
    var email          : String? = null
    var isOnline       : String? = null
    constructor() {}
    constructor(
        username       : String?  ,
        avatarUrl      : String?  ,
        email          : String?  ,
        isOnline       : String?  ,
    ){
        this.username  = username
        this.avatarUrl = avatarUrl
        this.email     = email
        this.isOnline  = isOnline
    }
}