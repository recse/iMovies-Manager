package com.example.imoviesmanager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.app.AppCompatActivity;
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

public class ResultadosBarraBusquedaActivity extends AppCompatActivity {

    //Creamos la vista de la busqueda, una lista con la clase Peliculas donde irá el resultado de la busqueda, y la query
    RecyclerView recyclerView;
    List<Peliculas> LIST_SEARCH_RESULTS;
    private String SEARCH_QUERY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados_barra_busqueda);

        //Enviamos la petición a la API y lo guardamos en nuestra query
        SEARCH_QUERY = getIntent().getStringExtra("SearchQuery");
        setTitle("Resultados de la búsqueda de: '" + SEARCH_QUERY +"'" );

        //Creamos las vistas de la busqueda
        recyclerView = findViewById(R.id.rv_search_results);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Procesamos la animacion
        LayoutAnimationController layoutAnimationController = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animacion_boton);
        recyclerView.setLayoutAnimation(layoutAnimationController);

        //Creamos un array list con el resultado de la busqueda y utilizamos nuestra funcion para cargar resultados
        LIST_SEARCH_RESULTS = new ArrayList<>();
        cargarResultadosBusqueda();
    }

    //Funcion para cargar y procesar los resultados
    private void cargarResultadosBusqueda(){
        SEARCH_QUERY = SEARCH_QUERY.replace(" ","%20");
        //Recibimos la informacion de la api y lo almacenamos en nuestra query
        String SEARCH_REQUEST_URL = API.URL_BUSQUEDA + SEARCH_QUERY;
        //Creamos los dialogos
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        //Procesamos los datos de la API que vamos a recibir
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                SEARCH_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    //Recorremos los resultados
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i=0; i<10; i++){
                                JSONObject currentMovie = results.getJSONObject(i);
                                Peliculas Peliculas = new Peliculas(
                                        currentMovie.getString("vote_average"),
                                        currentMovie.getString("original_title"),
                                        currentMovie.getString("poster_path"),
                                        currentMovie.getString("backdrop_path"),
                                        currentMovie.getString("overview"),
                                        currentMovie.getString("release_date"),
                                        "df"
                                );
                                progressDialog.dismiss();
                                LIST_SEARCH_RESULTS.add(Peliculas);

                                //Creamos nuestro objeto adaptador que ya viene definido en la clase Adaptador que hemos creado
                                ResultadoBarraBusquedaAdaptador ResultadoBarraBusquedaAdaptador
                                        = new ResultadoBarraBusquedaAdaptador(LIST_SEARCH_RESULTS, ResultadosBarraBusquedaActivity.this);
                                recyclerView.setAdapter(ResultadoBarraBusquedaAdaptador);
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

        RequestQueue requestQueue = Volley.newRequestQueue(ResultadosBarraBusquedaActivity.this);
        requestQueue.add(stringRequest);
    }
}
