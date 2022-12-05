package datamodel.viewdatamodel

enum class AuthViewDataModel(var resourceId: Int? = null) {
    INITIAL_STATE,
    SHOW_LOADING,
    HIDE_LOADING,
    SHOW_SIGN_IN,
    SHOW_ERROR,
    OPEN_MAIN_MENU
}