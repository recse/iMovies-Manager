package com.example.imoviesmanager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DetallesPeliculasActivity extends AppCompatActivity {

    private TextView tvMovieDetailLargeText;
    private TextView tvMovieDetailMovieTitle;
    private ImageView ivMovieDetailBackdropPath;
    private ImageView ivMovieDetailPosterPath;

    private ImageView imagenEstrellaPrimera;
    private ImageView imagenEstrellaSegunda;
    private ImageView imagenEstrellaTercera;
    private ImageView imagenEstrellaCuarta;
    private ImageView imagenEstrellaQuinta;

    private Button btMovieDetailAddToFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Cargamos la barra
        setContentView(R.layout.activity_detalles_peliculas);
        Toolbar toolbar = findViewById(R.id.movie_detail_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Hacemos el boton clickable
        getSupportActionBar().setDisplayShowHomeEnabled(true); // Lo Mostramos

        //Recogemos los datos de la API de themoviedb y los almacenamos en Strings
        final String stNombrePelicula = getIntent().getStringExtra("MovieTitle");
        final String stValoracionPelicula = getIntent().getStringExtra("MovieRating");
        final String stRutaPoster = getIntent().getStringExtra("PosterPath");
        final String stRutaFondo = getIntent().getStringExtra("BackdropPath");
        final String stVistaPeliculas = getIntent().getStringExtra("MovieOverview");
        final String stFechaEstreno = getIntent().getStringExtra("ReleaseDate");

        setTitle(stNombrePelicula);

        //Cargamos el boton de favoritos
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mostramos textos
                ProgressDialog progressDialog = new ProgressDialog(DetallesPeliculasActivity.this);
                progressDialog.setMessage("Añadiendo a favoritos...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                //Nos conectamos a Firebase y almacenamos en su base de datos lo que guardemos en favoritos
                //Para que pueda ser recordado
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();

                //Creamos el objeto Peliculas de la clase Peliculas y almacenamos los detalles en la misma
                Peliculas Peliculas = new Peliculas(stValoracionPelicula, stNombrePelicula, stRutaPoster, stRutaFondo, stVistaPeliculas, stFechaEstreno, " ");

                //Correccion de erorres en caso que no haya fecha de estreno
                if (stFechaEstreno.equals("Not Provided")) {
                    databaseReference.child("users").child(InicioActivity.user.getStUserId()).child("favorites").child("TVShows").child(stNombrePelicula).setValue(Peliculas);
                } else {
                    databaseReference.child("users").child(InicioActivity.user.getStUserId()).child("favorites").child("Movies").child(stNombrePelicula).setValue(Peliculas);
                }
                progressDialog.dismiss();
                Snackbar.make(findViewById(R.id.cl_movie_details_activity), "Añadido a favoritos", Snackbar.LENGTH_LONG).show();
            }
        });

        //Cargamos los detalles de las peliculas
        tvMovieDetailLargeText = findViewById(R.id.tv_movie_detail_large_text);
        tvMovieDetailLargeText.setText(stVistaPeliculas);

        //Dibujamos con Picasso el fondo donde irán los posters de las peliculas
        ivMovieDetailBackdropPath = findViewById(R.id.iv_movie_detail_backdrop_image);
        Picasso.get()
                .load(API.URL_IMAGENES + stRutaFondo)
                .resize(540,180)
                .centerCrop()
                .noPlaceholder()
                .error(R.drawable.recuadropelicula)
                .into(ivMovieDetailBackdropPath);

        //Creamos el boton de añadir a favoritos
        btMovieDetailAddToFav = findViewById(R.id.bt_movie_detail_add_to_fav);

        //Creamos los titulos de las peliculas
        tvMovieDetailMovieTitle = findViewById(R.id.tv_movie_detail_title);
        tvMovieDetailMovieTitle.setText(stNombrePelicula);

        //Dibujamos con Picasso los posters de las peliculas
        ivMovieDetailPosterPath = findViewById(R.id.iv_movie_detail_poster_path);
        Picasso.get()
                .load(API.URL_IMAGENES + stRutaPoster)
                .placeholder(R.drawable.recuadropelicula)
                .error(R.drawable.recuadropelicula)
                .into(ivMovieDetailPosterPath);

        //Creamos las estrellas de puntuacion
        imagenEstrellaPrimera = findViewById(R.id.imagenEstrellaPrimera);
        imagenEstrellaSegunda = findViewById(R.id.imagenEstrellaSegunda);
        imagenEstrellaTercera = findViewById(R.id.imagenEstrellaTercera);
        imagenEstrellaCuarta = findViewById(R.id.imagenEstrellaCuarta);
        imagenEstrellaQuinta = findViewById(R.id.imagenEstrellaQuinta);

        //Creamos el codigo de valoracion de las pelis
        Float valoracion = Float.parseFloat(stValoracionPelicula); //Parseamos el valor a string
        int valoracionPelicula = Math.round(valoracion); //Redondeamos
        valoracionPelicula = valoracionPelicula/2;
        //Hacemos un switch donde ocultaremos y mostraremos las estrellas de puntuacion segun la misma
        switch (valoracionPelicula){
            case 1:
                imagenEstrellaPrimera.setVisibility(View.VISIBLE);
                imagenEstrellaSegunda.setVisibility(View.GONE);
                imagenEstrellaTercera.setVisibility(View.GONE);
                imagenEstrellaCuarta.setVisibility(View.GONE);
                imagenEstrellaQuinta.setVisibility(View.GONE);
                break;
            case 2:
                imagenEstrellaPrimera.setVisibility(View.VISIBLE);
                imagenEstrellaSegunda.setVisibility(View.VISIBLE);
                imagenEstrellaTercera.setVisibility(View.GONE);
                imagenEstrellaCuarta.setVisibility(View.GONE);
                imagenEstrellaQuinta.setVisibility(View.GONE);
                break;
            case 3:
                imagenEstrellaPrimera.setVisibility(View.VISIBLE);
                imagenEstrellaSegunda.setVisibility(View.VISIBLE);
                imagenEstrellaTercera.setVisibility(View.VISIBLE);
                imagenEstrellaCuarta.setVisibility(View.GONE);
                imagenEstrellaQuinta.setVisibility(View.GONE);
                break;
            case 4:
                imagenEstrellaPrimera.setVisibility(View.VISIBLE);
                imagenEstrellaSegunda.setVisibility(View.VISIBLE);
                imagenEstrellaTercera.setVisibility(View.VISIBLE);
                imagenEstrellaCuarta.setVisibility(View.VISIBLE);
                imagenEstrellaQuinta.setVisibility(View.GONE);
                break;
            case 5:
                imagenEstrellaPrimera.setVisibility(View.VISIBLE);
                imagenEstrellaSegunda.setVisibility(View.VISIBLE);
                imagenEstrellaTercera.setVisibility(View.VISIBLE);
                imagenEstrellaCuarta.setVisibility(View.VISIBLE);
                imagenEstrellaQuinta.setVisibility(View.VISIBLE);
                break;
        }

        //Le damos la accion al boton para que pueda añadir a favoritos
        btMovieDetailAddToFav = findViewById(R.id.bt_movie_detail_add_to_fav);
        btMovieDetailAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Mostramos textos
                ProgressDialog progressDialog = new ProgressDialog(DetallesPeliculasActivity.this);
                progressDialog.setMessage("Añadiendo a favoritos...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                //Nos conectamos a Firebase y almacenamos en su base de datos lo que guardemos en favoritos
                //Para que pueda ser recordado
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();

                //Creamos el objeto Peliculas de la clase Peliculas y almacenamos los detalles en la misma
                Peliculas Peliculas = new Peliculas(stValoracionPelicula, stNombrePelicula, stRutaPoster, stRutaFondo, stVistaPeliculas, stFechaEstreno, " ");

                //Correccion de erorres en caso que no haya fecha de estreno
                if (stFechaEstreno.equals("Not Provided")) {
                    databaseReference.child("users").child(InicioActivity.user.getStUserId()).child("favorites").child("TVShows").child(stNombrePelicula).setValue(Peliculas);
                } else {
                    databaseReference.child("users").child(InicioActivity.user.getStUserId()).child("favorites").child("Movies").child(stNombrePelicula).setValue(Peliculas);
                }
                progressDialog.dismiss();
                Snackbar.make(findViewById(R.id.cl_movie_details_activity), "Añadido a favoritos", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
