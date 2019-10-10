package br.com.marcosouza.justamobile.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import br.com.marcosouza.justamobile.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initialScreen();
            }
        }, 2000);

    }

    // TODO Verificar conexão ao entrar no aplicativo
    private void initialScreen() {
        Intent intent = new Intent(SplashActivity.this, SigninActivity.class);
        startActivity(intent);
        finish();
    }
}
