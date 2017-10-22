package com.platzi.platzigram.login.presenter;

/**
 * Created by Diana Melgarejo on 21/10/2017.
 */

public interface LoginPresenter {
    void signIn(String username, String password); //interactor
    void loginSuccess();
    void loginError(String error);
}
