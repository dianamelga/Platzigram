package com.platzi.platzigram.login.view;

import android.view.View;

/**
 * Created by Diana Melgarejo on 20/10/2017.
 */

public interface LoginView {
    void enableInputs();
    void disableInputs();

    void showProgressBar();
    void hideProgressBar();

    void loginError(String error);

    void goCreateAccount();
    void goHome();
}
