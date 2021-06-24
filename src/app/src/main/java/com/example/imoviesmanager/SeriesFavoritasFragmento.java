package com.example.imoviesmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SeriesFavoritasFragmento extends Fragment {

    //Creamos las vistas, nuestra lista con las peliculas y una barra de progreso
    RecyclerView recyclerSeriesFavoritasFragmento;
    List<Peliculas> LIST_SERIES_FAVORITAS;
    ProgressBar barraProgreso;

    //Asignamos nuestro layout e "inflamos"
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_series_favoritas_fragmento, null);
    }

    //Aplicamos el layout manager a nuestro recycler
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerSeriesFavoritasFragmento = view.findViewById(R.id.recycler_series_favoritas_fragmento_ver);
        recyclerSeriesFavoritasFragmento.setHasFixedSize(false);
        recyclerSeriesFavoritasFragmento.setLayoutManager(new LinearLayoutManager(getActivity()));

        //Cargamos la barra de progreso
        barraProgreso = view.findViewById(R.id.barra_progreso_series_favoritas);

        //Creamos el array donde irán las series favoritas y utilizamos nuestra funcion para cargarlas

        LIST_SERIES_FAVORITAS = new ArrayList<>();
        cargarSeriesFavoritas();
    }

    private void cargarSeriesFavoritas(){

        //Cremos los objetos de FireBase donde almacenaremos las series favoritas
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("favorites")
                .child("TVShows")
                .addValueEventListener(new ValueEventListener() {
                    @Override

                    //Hacemos un SnapShot del lugar de la base de datos de Firebase donde estará
                    //alojado las peliculas que marquemos como favoritas
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Peliculas Peliculas = snapshot.getValue(Peliculas.class);
                            LIST_SERIES_FAVORITAS.add(Peliculas);
                        }
                        ResultadoBarraBusquedaAdaptador resultadoBarraBusquedaAdaptador = new ResultadoBarraBusquedaAdaptador(LIST_SERIES_FAVORITAS, getActivity());
                        recyclerSeriesFavoritasFragmento.setAdapter(resultadoBarraBusquedaAdaptador);
                        barraProgreso.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }
}
   