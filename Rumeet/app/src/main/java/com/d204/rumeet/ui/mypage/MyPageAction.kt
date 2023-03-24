package com.d204.rumeet.ui.mypage

sealed class MyPageAction {
    object RunningRecord : MyPageAction()
    object MatchingHistory : MyPageAction()
    object FriendList : MyPageAction()
    object BadgeList : MyPageAction()
    object EditPofile : MyPageAction()
    object Setting : MyPageAction()
    object Notice : MyPageAction()

    //MyPage Setting
    object MyAccount : MyPageAction()
    object SettingNotification : MyPageAction()
    object Privacy : MyPageAction()
    object ServiceTerms : MyPageAction()
    object LogOut : MyPageAction()
}
