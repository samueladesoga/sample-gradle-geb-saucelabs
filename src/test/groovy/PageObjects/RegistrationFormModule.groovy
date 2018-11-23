package PageObjects

import geb.Module

class RegistrationFormModule extends Module {
    static base = {$("div.show div.modal-content")}
    static content = {
        emailAddressField(wait: true) { $('input', name: 'user[email]')}
        passwordField { $('input', name: 'user[password]')}
        confirmPasswordField { $('input', name: 'user[password_confirmation]')}
        signUpButton { $('button', class: 'signup')}
    }
}
