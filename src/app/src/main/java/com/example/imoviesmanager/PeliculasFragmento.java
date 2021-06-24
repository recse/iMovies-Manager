package com.example.imoviesmanager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PeliculasFragmento extends Fragment {

    List<Peliculas> LIST_PELICULAS_TOP; //Lista para las pelis top
    List<Peliculas> LIST_PELICULAS_ACCION; //Lista para las pelis de accion
    List<Peliculas> LIST_PELICULAS_COMEDIA; //Lista para las pelis de comedia
    RecyclerView recyclerViewPeliculasTop; //Recycler para las pelis top
    RecyclerView RecyclerViewPeliculasAccion; //Recycler para las pelis de accion
    RecyclerView recyclerViewPeliculasComedia; //Recycler para las pelis de comedia

    //Creamos el contenedor de vistas
    @SuppressLint("InflateParams")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_peliculas, null);
    }

    //Creamos las vistas que irán dentro del contenedor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Como habrá bastantes LinearLayoutManager para organizar las vistas (recycleviews),
        //utilizaré un LinearLayoutManager para cada tipo de peliculas (de accion, comedia, top...)

        //Primer Layout: **PELIS TOP**, de forma horizontal para que los siguientes vayan uno debajo de otro.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPeliculasTop = view.findViewById(R.id.recycler_peliculas_top); //Aplicamos la vista
        recyclerViewPeliculasTop.setLayoutManager(linearLayoutManager); //Creamos el LayoutManager
        recyclerViewPeliculasTop.setHasFixedSize(false); //Fijamos el tamaño

        //Segundo Layout: **PELIS DE ACCION**, justo debajo de TOP y encima de Comedias
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerViewPeliculasAccion = view.findViewById(R.id.recycler_peliculas_accion);
        RecyclerViewPeliculasAccion.setLayoutManager(linearLayoutManager1);
        RecyclerViewPeliculasAccion.setHasFixedSize(false); //Fijamos el tamaño

        //Tercer Layout: **PELIS DE COMEDIA**, debajo de accion
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPeliculasComedia = view.findViewById(R.id.recycler_peliculas_comedia); //Aplicamos la vista
        recyclerViewPeliculasComedia.setLayoutManager(linearLayoutManager2); //Creamos el LayoutManager
        recyclerViewPeliculasComedia.setHasFixedSize(false); //Fijamos el tamaño

        //Iniciamos la lista de peliculas TOP y cargamos su contenido
        LIST_PELICULAS_TOP = new ArrayList<>();
        cargarPeliculasTop();

        //Iniciamos la lista de peliculas ACCION y cargamos su contenido
        LIST_PELICULAS_ACCION = new ArrayList<>();
        cargarPeliculasAccion();

        //Iniciamos la lista de peliculas COMEDIA y cargamos su contenido
        LIST_PELICULAS_COMEDIA = new ArrayList<>();
        cargarPeliculasComedia();
    }

    private void cargarPeliculasTop(){
        //DIALOGOS
        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        //La función principal del proyecto.
        //Utilizaré VOLLEY para la transimisión de datos entre la API de themoviedb y mi app

        //Inicio la petición de datos a la API de themoviedb
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_PELICULAS_TOP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String respuesta) {
                        //ALMACENAMOS LOS DATOS (RESULTADOS) COMO OBJETOS JSON
                        try {
                            JSONObject objeto = new JSONObject(respuesta);
                            JSONArray resultado = objeto.getJSONArray("results");
                            //Obtenemos los resultados mediante un for, para buscar y recorrer.
                            for (int i=0; i<10; i++){
                                //Le indicamos al resultado que es la pelicula actual seleccionada
                                JSONObject peliculaActual = resultado.getJSONObject(i);
                                //Mediante la clase Peliculas creada previamente le escribimos los datos
                                //que hemos recibido del resultado
                                Peliculas Peliculas = new Peliculas(
                                        peliculaActual.getString("vote_average"),
                                        peliculaActual.getString("original_title"),
                                        peliculaActual.getString("poster_path"),
                                        peliculaActual.getString("backdrop_path"),
                                        peliculaActual.getString("overview"),
                                        peliculaActual.getString("release_date"),
                                        "df"
                                );
                                progressDialog.dismiss(); //Cerramos el cuadro de dialogos
                                LIST_PELICULAS_TOP.add(Peliculas); //Añadimos a la lista las peliculas encontradas

                                //Cargamos los adaptadores en las vistas
                                PeliculasAdaptador PeliculasAdaptador
                                        = new PeliculasAdaptador(LIST_PELICULAS_TOP, getActivity());
                                recyclerViewPeliculasTop.setAdapter(PeliculasAdaptador);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    /* A CONTINUACION DE IGUAL FORMA QUE LAS PELICULAS TOP, CARGAMOS LAS PELICULAS DE ACCION Y COMEDIA*/

    private void cargarPeliculasAccion(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_PELICULAS_ACCION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray resultado = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject peliculaActual = resultado.getJSONObject(i);
                                Peliculas Peliculas = new Peliculas(
                                        peliculaActual.getString("vote_average"),
                                        peliculaActual.getString("original_title"),
                                        peliculaActual.getString("poster_path"),
                                        peliculaActual.getString("backdrop_path"),
                                        peliculaActual.getString("overview"),
                                        peliculaActual.getString("release_date"),
                                        "df"
                                );
                                LIST_PELICULAS_ACCION.add(Peliculas);
                                PeliculasAdaptador PeliculasAdaptador
                                        = new PeliculasAdaptador(LIST_PELICULAS_ACCION, getActivity());
                                RecyclerViewPeliculasAccion.setAdapter(PeliculasAdaptador);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void cargarPeliculasComedia(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_PELICULAS_COMEDIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray resultado = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject peliculaActual = resultado.getJSONObject(i);
                                Peliculas Peliculas = new Peliculas(
                                        peliculaActual.getString("vote_average"),
                                        peliculaActual.getString("original_title"),
                                        peliculaActual.getString("poster_path"),
                                        peliculaActual.getString("backdrop_path"),
                                        peliculaActual.getString("overview"),
                                        peliculaActual.getString("release_date"),
                                        "df"
                                );
                                LIST_PELICULAS_COMEDIA.add(Peliculas);
                                PeliculasAdaptador PeliculasAdaptador
                                        = new PeliculasAdaptador(LIST_PELICULAS_COMEDIA, getActivity());
                                recyclerViewPeliculasComedia.setAdapter(PeliculasAdaptador);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}

