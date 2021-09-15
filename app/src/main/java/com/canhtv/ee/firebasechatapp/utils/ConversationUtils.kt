package com.canhtv.ee.firebasechatapp.utils

class ConversationUtils {
    fun checkConversationId(user1: String, user2: String): String {
        return if (user1 < user2) user1 + user2 else user2 + user1
    }
}