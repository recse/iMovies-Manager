package com.example.imoviesmanager;

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


public class SeriesFragmento extends Fragment {

    RecyclerView recyclerViewTV;
    RecyclerView recyclerViewActionTvshows;
    RecyclerView recyclerViewComedyTvshows;

    List<Peliculas> LIST_TRENDING_TVSHOWS;
    List<Peliculas> LIST_ACTION_TVSHOWS;
    List<Peliculas> LIST_COMEDY_TVSHOWS;

    //Creamos el contenedor de vistas

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_series_fragmento, null);
    }

    //Creamos las vistas que irán dentro del contenedor
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Como habrá bastantes LinearLayoutManager para organizar las vistas (recycleviews),
        //utilizaré un LinearLayoutManager para cada tipo de series (de accion, comedia, top...)

        //Primer Layout: **SERIES TOP**, de forma horizontal para que los siguientes vayan uno debajo de otro.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTV = view.findViewById(R.id.recycler_view_trending_tvshows);
        recyclerViewTV.setLayoutManager(linearLayoutManager);
        recyclerViewTV.setHasFixedSize(false);

        //Segundo Layout: **SERIES DE ACCION**, justo debajo de TOP y encima de Comedias
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewActionTvshows = view.findViewById(R.id.recycler_view_action_tvshows);
        recyclerViewActionTvshows.setLayoutManager(linearLayoutManager1);
        recyclerViewActionTvshows.setHasFixedSize(false);

        //Tercer Layout: **SERIES DE COMEDIA**, debajo de accion
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewComedyTvshows = view.findViewById(R.id.recycler_view_comedy_tvshows);
        recyclerViewComedyTvshows.setLayoutManager(linearLayoutManager2);
        recyclerViewComedyTvshows.setHasFixedSize(false);

        //Iniciamos la lista de series TOP y cargamos su contenido
        LIST_TRENDING_TVSHOWS = new ArrayList<>();
        loadTrendingTvshowsData();

        //Iniciamos la lista de series ACCION y cargamos su contenido
        LIST_ACTION_TVSHOWS = new ArrayList<>();
        loadActionTvshowsData();

        //Iniciamos la lista de series COMEDIA y cargamos su contenido
        LIST_COMEDY_TVSHOWS = new ArrayList<>();
        loadComedyTvshowsData();
    }

    private void loadTrendingTvshowsData(){
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_SERIES_TOP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject currentMovie = results.getJSONObject(i);
                                Peliculas movieClass = new Peliculas(
                                        currentMovie.getString("vote_average"),
                                        currentMovie.getString("original_name"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        "Not Provided",
                                        "df"
                                );
                                progressDialog.dismiss();
                                LIST_TRENDING_TVSHOWS.add(movieClass);
                                PeliculasAdaptador trendingMoviesAdapter
                                        = new PeliculasAdaptador(LIST_TRENDING_TVSHOWS, getActivity());
                                recyclerViewTV.setAdapter(trendingMoviesAdapter);
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

    private void loadActionTvshowsData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_SERIES_ACCION,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject currentMovie = results.getJSONObject(i);
                                Peliculas movieClass = new Peliculas(
                                        currentMovie.getString("vote_average"),
                                        currentMovie.getString("original_name"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        "Not Provided",
                                        "df"
                                );
                                LIST_ACTION_TVSHOWS.add(movieClass);
                                PeliculasAdaptador trendingMoviesAdapter
                                        = new PeliculasAdaptador(LIST_ACTION_TVSHOWS, getActivity());
                                recyclerViewActionTvshows.setAdapter(trendingMoviesAdapter);
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

    private void loadComedyTvshowsData(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                API.URL_SERIES_COMEDIA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject currentMovie = results.getJSONObject(i);
                                Peliculas movieClass = new Peliculas(
                                        currentMovie.getString("vote_average"),
                                        currentMovie.getString("original_name"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        "Not Provided",
                                        "df"
                                );
                                LIST_COMEDY_TVSHOWS.add(movieClass);
                                PeliculasAdaptador trendingMoviesAdapter
                                        = new PeliculasAdaptador(LIST_COMEDY_TVSHOWS, getActivity());
                                recyclerViewComedyTvshows.setAdapter(trendingMoviesAdapter);
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
