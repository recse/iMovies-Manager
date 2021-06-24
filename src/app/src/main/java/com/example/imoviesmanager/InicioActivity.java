package com.example.imoviesmanager;

import android.app.UiAutomation;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    String stNombreUsuario;
    String stUsuarioEmail;
    String stUserId;
    public static usuario user;

    public static FirebaseDatabase firebaseDatabase;
    public static DatabaseReference databaseReference;
    private UiAutomation ScreenShotter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        //Barra de navegación
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Titulo
        setTitle(R.string.nombreApp);

            //Control de excepciones para el login.
            try {
            //Creamos la autentificacion y el usuario y los almacenamos en sus correspondientes variables
            //Las cuales posteriormente serán añadidas al objeto "user" de mi clase usuario
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            stNombreUsuario = firebaseUser.getDisplayName();
            stUsuarioEmail = firebaseUser.getEmail();
            stUserId = firebaseUser.getUid();
            user = new usuario(stNombreUsuario, stUsuarioEmail, stUserId);
        } catch (NullPointerException e){
            Toast.makeText(this, "Ha ocurrido un error.", Toast.LENGTH_SHORT).show();
            stNombreUsuario = "Nombre de Usuario";
            stUsuarioEmail = "tuemail@gmail.com";
        }

        //Gestor de conexiones.
        ConnectivityManager connectivityManager = (ConnectivityManager)getApplicationContext().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        //Control de excepciones durante la conexión.
        if (networkInfo == null){
            Snackbar.make(findViewById(R.id.ct_inicio_activity), "Por favor, comprueba la conexión a internet.", Snackbar.LENGTH_LONG).show();
        }

        //Creamos la conexión de la base de datos de Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //Relacionamos el nombre de usuario, con la contraseña y su ID, heredado de la clase Usuario.
        //Aplicamos getters y setters correspondientes.
        databaseReference.child("users").child(user.getStUserId()).child("stUsuarioEmail").setValue(user.getstUsuarioEmail());
        databaseReference.child("users").child(user.getStUserId()).child("stNombreUsuario").setValue(user.getstNombreUsuario());
        databaseReference.child("users").child(user.getStUserId()).child("stUserId").setValue(user.getStUserId());

        //Lanzamos el fragmento de las peliculas nada más comience (será la primera pantalla en mostrarse).
        Fragment fragment = new PeliculasFragmento();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_inicio_activity, fragment);
        fragmentTransaction.commit();

        //Creamos el boton flotante de favoritos y le damos una accion
        FloatingActionButton favorito = findViewById(R.id.fav);
        favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "¿Tienes una sugerencia? Escríbeme: adrianbelmonte94@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("¿Tienes una sugerencia? Escríbeme: adrianbelmonte94@gmail.com", null).show();
            }
        });

        //Dibujamos la barra lateral que será el menu de usuarfio
        DrawerLayout drawer = findViewById(R.id.layout_menu);
        //Le damos funcionalidad a la barra lateral creando un objeto toggle y añadiendole un listener
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.abrir_menu, R.string.cerrar_menu);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Creamos la vista de la barra de navegacion
        NavigationView navigationView = findViewById(R.id.ver_barra);
        navigationView.setNavigationItemSelectedListener(this);

        //Creamos la vista de la parte superior de la barra
        View headerView = navigationView.getHeaderView(0);

        //Creamos los nombres de usuario y constraseña
        TextView nombreUsuario = headerView.findViewById(R.id.usuario_barra_navegacion);
        TextView contraUsuario = headerView.findViewById(R.id.contrasena_barra_navegacion);

        //Recibimos de la clase usuario el nombre y la contraseña que haya introducido
        nombreUsuario.setText(user.getstNombreUsuario());
        contraUsuario.setText(user.getstUsuarioEmail());

    }

    //Le damos funcionalidad al boton de volver desde la barra lateral al inicio
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.layout_menu);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // "Inflamos" el menu para añadir elementos a la barra de accion SOLO si estan presentes

        getMenuInflater().inflate(R.menu.header_menu, menu);

        //Creamos la barra de búsqueda y le damos funcionalidad
        SearchView barraBusqueda = (SearchView) menu.findItem(R.id.barra_busqueda).getActionView();
        barraBusqueda.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            //Creamos la query que envía el texto que escribamos
            public boolean onQueryTextSubmit(String s) {
                Intent intent = new Intent(InicioActivity.this, ResultadosBarraBusquedaActivity.class);
                intent.putExtra("SearchQuery", s);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem elementosMenuAccion) {
        //Aqui van los clicks de los elementos de la barra de acciones.
        //La barra de accion controlará automaticamentes los clickjs que hagamos en el boton de Inicio/Arriba
        switch(elementosMenuAccion.getItemId()){
            case R.id.barra_busqueda:
                return true;
        }

        return super.onOptionsItemSelected(elementosMenuAccion);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem elementosMenuAccion) {
        // Aqui manejaremos los clicks de las vistas de los elementos de navegacion.
        int id = elementosMenuAccion.getItemId();

        Fragment fragment = null;

        switch (id){
            case R.id.menu_pelis:
                //Cargamos nuestro fragmento de pelis
                fragment = new PeliculasFragmento();
                break;
            case R.id.menu_series:
                //Cargamos nuestro fragmento de series
                fragment = new SeriesFragmento();
                break;
            case R.id.menu_favoritos:
                //Cargamos los favoritos
                startActivity(new Intent(InicioActivity.this, FavoritesActivity.class));
                break;
            case R.id.menu_cerrar:
                //Nos deslogueamos, cerramos FireBase y volvemos a la actividad Principal
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(InicioActivity.this, LoginActivity.class));
                break;
        }

        //En caso de que el fragmento no esté vacío (es decir, que haya opciones y no haya fallado nada)
        if (fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //Cargamos el contenido del fragmento
            fragmentTransaction.replace(R.id.frame_layout_inicio_activity, fragment);
            fragmentTransaction.commit();
        }

        //Cargamos el menu
        DrawerLayout drawer = findViewById(R.id.layout_menu);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
