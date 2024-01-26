object CommonValidator {
    fun isValidEmailId(email: String): Boolean {
        val emailPattern = Regex("[a-zA-Z0–9._-]+@[a-z]+\\.+[a-z]+")
        return emailPattern.matches(email) //&& passwordPattern.matches(password)
    }

    fun isValidPassword(password: String) : Boolean {
        // min 8 characters long with one letter and one number
        //^(?=.*[0–9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$
        val passwordPattern = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$")
        return passwordPattern.matches(password)
    }
}