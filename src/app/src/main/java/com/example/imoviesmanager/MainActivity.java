package com.example.imoviesmanager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animaciones
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        final TextView textoImovies = findViewById(R.id.textoImovies);
        final TextView textoAdrian = findViewById(R.id.textoAdrian);
        final ImageView imagenLogo = findViewById(R.id.imagen_logo);

        textoImovies.setAnimation(animacion2);
        textoAdrian.setAnimation(animacion2);
        imagenLogo.setAnimation(animacion1);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
                if (user != null && account != null) {
                    Intent intent = new Intent (MainActivity.this, InicioActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    //Hacemos 2 pares para las transiciones
                    Pair[] pairs = new Pair[2];
                    pairs[0] = new Pair<View, String>(imagenLogo, "logoImageTrans");
                    pairs[1] = new Pair<View, String>(imagenLogo, "textTrans");

                    //Checkeamos la version de android pues en Lollipop e inferiores NO funcionan las transiciones
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                        //En caso que la version sea superior lanzamos la animacion
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                        startActivity(intent, options.toBundle());
                    } else {
                        //En caso contrario
                        startActivity(intent);
                        finish();
                     }
                    }
                }
        }, 4000);

    }
}