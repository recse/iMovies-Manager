package com.example.imoviesmanager;

public class Peliculas {

    private String stPeliculasFavoritas;
    private String stTituloPeliculas;
    private String stPoster;
    private String stFondoPeliculas;
    private String stVistaPeliculas;
    private String stFechaEstreno;
    private String stGeneroPeliculas;

    public Peliculas(){

    }

    public Peliculas(String stPeliculasFavoritas, String stTituloPeliculas, String stPoster, String stFondoPeliculas, String stVistaPeliculas, String stFechaEstreno, String stGeneroPeliculas) {
        this.stPeliculasFavoritas = stPeliculasFavoritas;
        this.stTituloPeliculas = stTituloPeliculas;
        this.stPoster = stPoster;
        this.stFondoPeliculas = stFondoPeliculas;
        this.stVistaPeliculas = stVistaPeliculas;
        this.stFechaEstreno = stFechaEstreno;
        this.stGeneroPeliculas = stGeneroPeliculas;
    }

    public String getstPeliculasFavoritas() {
        return stPeliculasFavoritas;
    }

    public String getstTituloPeliculas() {
        return stTituloPeliculas;
    }

    public String getstPoster() { return stPoster; }

    public String getstFondoPeliculas() { return stFondoPeliculas; }

    public String getstVistaPeliculas() {
        return stVistaPeliculas;
    }

    public String getstFechaEstreno() {
        return stFechaEstreno;
    }

    public String getstGeneroPeliculas(){
        return stGeneroPeliculas;
    }
}

