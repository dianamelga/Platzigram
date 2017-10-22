package com.platzi.platzigram.login.repository;

import com.platzi.platzigram.login.presenter.LoginPresenter;

/**
 * Created by Diana Melgarejo on 21/10/2017.
 */

public class LoginRepositoryImpl implements LoginRepository {

    LoginPresenter presenter;

    public LoginRepositoryImpl(LoginPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void signIn(String username, String password) {
        boolean success = true;
        if (success) {
            presenter.loginSuccess();

        }else{
            presenter.loginError("Ocurrio un error");
        }
    }
}
