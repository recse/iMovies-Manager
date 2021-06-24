package com.example.imoviesmanager;

public class API {
    public static final String URL_PELICULAS_TOP =
            "https://api.themoviedb.org/3/discover/movie?api_key=c9d9ab7e4e4b61d473a1e627b645e48d&language=es-ES&sort_by=popularity.desc&include_adult=false&include_video=false&page=1";
    public static final String URL_IMAGENES =
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    public static final String URL_PELICULAS_ACCION =
            "https://api.themoviedb.org/3/discover/movie?api_key=c9d9ab7e4e4b61d473a1e627b645e48d&language=es-ES&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28";
    public static final String URL_PELICULAS_COMEDIA =
            "https://api.themoviedb.org/3/discover/movie?api_key=c9d9ab7e4e4b61d473a1e627b645e48d&language=es-ES&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=35";
    public static final String URL_BUSQUEDA =
            "https://api.themoviedb.org/3/search/movie?api_key=c9d9ab7e4e4b61d473a1e627b645e48d&language=hindi&page=1&include_adult=false&query=";
    public static final String URL_SERIES_TOP =
            "https://api.themoviedb.org/3/discover/tv?api_key=c9d9ab7e4e4b61d473a1e627b645e48d&language=es-ES&sort_by=popularity.desc&page=1&timezone=Europe%2FMadrid&include_null_first_air_dates=false";
    public static final String URL_SERIES_ACCION =
            "https://api.themoviedb.org/3/discover/tv?api_key=c9d9ab7e4e4b61d473a1e627b645e48d&language=es-ES&sort_by=popularity.desc&page=1&timezone=Europe%2FMadrid&with_genres=10759&include_null_first_air_dates=false";
    public static final String URL_SERIES_COMEDIA =
            "https://api.themoviedb.org/3/discover/tv?api_key=c9d9ab7e4e4b61d473a1e627b645e48d&language=es-ES&sort_by=popularity.desc&page=1&timezone=Europe%2FMadrid&with_genres=35&include_null_first_air_dates=false";

}

