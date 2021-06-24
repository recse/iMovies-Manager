package com.example.imoviesmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ResultadoBarraBusquedaAdaptador extends RecyclerView.Adapter<ResultadoBarraBusquedaAdaptador.ViewHolder> {

    //Creamos nuestra lista donde almacenaremos los resultados de busque y un contexto que utilizaremos para acceder
    List<Peliculas> LIST_SEARCH_RESULTS;
    Context context;

    public ResultadoBarraBusquedaAdaptador(List<Peliculas> LIST_SEARCH_RESULTS, Context context) {
        this.LIST_SEARCH_RESULTS = LIST_SEARCH_RESULTS;
        this.context = context;
    }

    //"Inflamos" la vista y cargamos el layout

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_resultado_busqueda, parent, false);
        return new ViewHolder(view);
    }

    //Dibujamos con Picasso el lugar donde irán los posters de las peliculas
    //Cargamos en nuestra Lista de busqueda la informacion de la API y lo mandamos a nuestro objeto Peliculas
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Peliculas Peliculas = LIST_SEARCH_RESULTS.get(position);
        Picasso.get()
                .load(API.URL_IMAGENES + Peliculas.getstPoster())
                .placeholder(R.drawable.recuadropelicula) //Colocamos la imagen sobre el recuadro
                .error(R.drawable.recuadropelicula) //Control de errores
                .into(holder.ivMovieImage);

        //Recogogemos el titulo de la pelicula y calculamos su valoracion
        holder.tvMovieTitle.setText(Peliculas.getstTituloPeliculas());
        Float rating = Float.parseFloat(Peliculas.getstPeliculasFavoritas());
        int movieRating = Math.round(rating);
        movieRating = movieRating/2;

        //Hacemos un switch para ver los casos de las puntuaciones
        //Ocultamos y mostramos estrellas segun su puntuacion
        switch (movieRating){
            case 1:
                holder.imagenPrimeraEstrella.setVisibility(View.VISIBLE);
                holder.imagenSegundaEstrella.setVisibility(View.GONE);
                holder.imagenTerceraEstrella.setVisibility(View.GONE);
                holder.imagenCuartaEstrella.setVisibility(View.GONE);
                holder.imagenQuintaEstrella.setVisibility(View.GONE);
                break;
            case 2:
                holder.imagenPrimeraEstrella.setVisibility(View.VISIBLE);
                holder.imagenSegundaEstrella.setVisibility(View.VISIBLE);
                holder.imagenTerceraEstrella.setVisibility(View.GONE);
                holder.imagenCuartaEstrella.setVisibility(View.GONE);
                holder.imagenQuintaEstrella.setVisibility(View.GONE);
                break;
            case 3:
                holder.imagenPrimeraEstrella.setVisibility(View.VISIBLE);
                holder.imagenSegundaEstrella.setVisibility(View.VISIBLE);
                holder.imagenTerceraEstrella.setVisibility(View.VISIBLE);
                holder.imagenCuartaEstrella.setVisibility(View.GONE);
                holder.imagenQuintaEstrella.setVisibility(View.GONE);
                break;
            case 4:
                holder.imagenPrimeraEstrella.setVisibility(View.VISIBLE);
                holder.imagenSegundaEstrella.setVisibility(View.VISIBLE);
                holder.imagenTerceraEstrella.setVisibility(View.VISIBLE);
                holder.imagenCuartaEstrella.setVisibility(View.VISIBLE);
                holder.imagenQuintaEstrella.setVisibility(View.GONE);
                break;
            case 5:
                holder.imagenPrimeraEstrella.setVisibility(View.VISIBLE);
                holder.imagenSegundaEstrella.setVisibility(View.VISIBLE);
                holder.imagenTerceraEstrella.setVisibility(View.VISIBLE);
                holder.imagenCuartaEstrella.setVisibility(View.VISIBLE);
                holder.imagenQuintaEstrella.setVisibility(View.VISIBLE);
                break;
        }

        //A nuestro cardview le enviamos la informacion de la API
        holder.cvSearchREsults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetallesPeliculasActivity.class);
                intent.putExtra("MovieTitle", Peliculas.getstTituloPeliculas());
                intent.putExtra("MovieRating", Peliculas.getstPeliculasFavoritas());
                intent.putExtra("PosterPath", Peliculas.getstPoster());
                intent.putExtra("BackdropPath", Peliculas.getstFondoPeliculas());
                intent.putExtra("MovieOverview", Peliculas.getstVistaPeliculas());
                intent.putExtra("ReleaseDate", Peliculas.getstFechaEstreno());
                context.startActivity(intent);
            }
        });


        //Creamos el boton de borrar de favoritos
        holder.btAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog = new ProgressDialog(context);
                progressDialog.setMessage("Añadiendo a favoritos...");
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference();

                //Control de errores en caso que la pelicula no se haya estrenado aun
                if (Peliculas.getstFechaEstreno().equals("Not Provided")){
                    databaseReference.child("users").child(InicioActivity.user.getStUserId()).child("favorites").child("TVShows").child(Peliculas.getstTituloPeliculas()).removeValue();
                } else {
                    databaseReference.child("users").child(InicioActivity.user.getStUserId()).child("favorites").child("Movies").child(Peliculas.getstTituloPeliculas()).removeValue();
                }
                progressDialog.dismiss();
                Toast.makeText(context, "Borrado de favoritos.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    //Funcion para contar los items de nuestra lista y calcular su tamaño
    @Override
    public int getItemCount() {
        return LIST_SEARCH_RESULTS.size();
    }


    //Creamos nuestra clase ViewHolder y aplicamos las visstas
    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivMovieImage;
        private TextView tvMovieTitle;
        private ImageView imagenPrimeraEstrella;
        private ImageView imagenSegundaEstrella;
        private ImageView imagenTerceraEstrella;
        private ImageView imagenCuartaEstrella;
        private ImageView imagenQuintaEstrella;
        private Button btAddToFav;
        private Button btWatchTrailer;
        private CardView cvSearchREsults;

        public ViewHolder(View itemView) {
            super(itemView);
            ivMovieImage = itemView.findViewById(R.id.iv_search_results_image);
            imagenPrimeraEstrella = itemView.findViewById(R.id.imagen_primera_estrella_resultado_busqueda);
            imagenSegundaEstrella = itemView.findViewById(R.id.imagen_segunda_estrella_resultado_busqueda);
            imagenTerceraEstrella = itemView.findViewById(R.id.imagen_tercera_estrella_resultado_busqueda);
            imagenCuartaEstrella = itemView.findViewById(R.id.imagen_cuarta_estrella_resultado_busqueda);
            imagenQuintaEstrella = itemView.findViewById(R.id.imagen_quinta_estrella_resultado_busqueda);
            tvMovieTitle = itemView.findViewById(R.id.tv_search_results_movie_title);
            btAddToFav = itemView.findViewById(R.id.bt_search_results_add_to_fav);
            btWatchTrailer = itemView.findViewById(R.id.bt_search_results_watch_trailer);
            cvSearchREsults = itemView.findViewById(R.id.cv_search_results);
        }
    }


}

