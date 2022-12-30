package ru.dashkevich.authorization.screens.login.model

import ru.dashkevich.utility.tools.ScreenState

data class LoginState(
    val screenState: ScreenState = ScreenState.Waiting
)
