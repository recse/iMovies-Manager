package com.example.imoviesmanager;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PeliculasAdaptador extends RecyclerView.Adapter<PeliculasAdaptador.ViewHolder> {

    private List<Peliculas> LISTA_PELICULAS_TOP;
    private Context context;
    private Activity activity;

    //Creamos el adaptador que permitirá ordenar los resultados de las peliculas
    public PeliculasAdaptador(List<Peliculas> LISTA_PELICULAS_TOP, Activity activity) {
        this.LISTA_PELICULAS_TOP = LISTA_PELICULAS_TOP;
        this.activity = activity;
    }

    //Referenciamos los elementos con un ViewHolder y le pasamos in LayoutInflater para "inflar" nuestra vista
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_peliculas_top, parent, false);
        return new ViewHolder(vista);
    }

    //Utilizamos el metodo onBindViewHolder para crear más de las 10 vistas predeterminadas
    //que solo acepta un RecyclerView, de este modo, podemos tener un lista completo de
    //peliculas con más de 10 items conforme vamos haciendo scroll
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int posicion) {
        //Obtenemos la posicion de la pelicula seleccionada
        final Peliculas Peliculas = LISTA_PELICULAS_TOP.get(posicion);

        //Empezamos a diseñar la vista. Utilizamos en este caso la clase Picasso por comodidad.
        //Colocamos un cuado vacío por si falla alguna imagen no se descuadre el resto.
        //Las imagenes que reciba de la API de themoviedb se sobrepondrán encima de la imagen
        //pues mi imagen vacía tiene las mismas dims.
        Picasso.get()
                .load(API.URL_IMAGENES + Peliculas.getstPoster())
                .placeholder(R.drawable.recuadropelicula)
                .into(holder.imageView);

        //Recibimos la información de la API y lo colocamos en nuestra clase Peliculas
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, DetallesPeliculasActivity.class);
                intent.putExtra("MovieTitle", Peliculas.getstTituloPeliculas());
                intent.putExtra("MovieRating", Peliculas.getstPeliculasFavoritas());
                intent.putExtra("PosterPath", Peliculas.getstPoster());
                intent.putExtra("BackdropPath", Peliculas.getstFondoPeliculas());
                intent.putExtra("MovieOverview", Peliculas.getstVistaPeliculas());
                intent.putExtra("ReleaseDate", Peliculas.getstFechaEstreno());

                //Lanzamos la animacion de la transicion
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(activity, holder.imageView, "posterImage");

                //Comenzamos la actividad
                activity.startActivity(intent, activityOptions.toBundle());
            }
        });
    }

    //Obtenemos la cantidad de items que tendrá nuestra busqueda
    @Override
    public int getItemCount() {
        return LISTA_PELICULAS_TOP.size();
    }

    //Clase basica de Viewholder
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.vista_peliculas_top);
        }
    }
}
