package hn.edu.ujcv.p3.Proyecto3.utils;

public class Constants {
    private static final String URL_API_BASE            = "/api";
    private static final String URL_API_VERSION         = "/v1";
    private static final String URL_BASE                = URL_API_BASE + URL_API_VERSION;
    public static final  String URL_BASE_ALUMNO         = String.format("%s/Alumno",URL_BASE);
    public static final  String URL_BASE_AUTOR          = String.format("%s/Autor",URL_BASE);
    public static final  String URL_BASE_BIBLIOTECARIO  = String.format("%s/Bibliotecario",URL_BASE);
    public static final  String URL_BASE_EDITORIAL      = String.format("%s/Editorial",URL_BASE);
    public static final  String URL_BASE_LIBRO          = String.format("%s/Libro",URL_BASE);
    public static final  String URL_BASE_MULTA          = String.format("%s/Multa",URL_BASE);
    public static final  String URL_BASE_PRESTAMO       = String.format("%s/Prestamo",URL_BASE);
    public static final  String URL_BASE_REPOSICION     = String.format("%s/Reposicion",URL_BASE);
    public static final  String URL_BASE_SUSCRIPCION    = String.format("%s/Suscripcion", URL_BASE);
    public static final  String URL_BASE_TUTOR          = String.format("%s/Tutor", URL_BASE);
    public static final  String URL_BASE_TUTORIA        = String.format("%s/Tutoria", URL_BASE);
    public static final  String URL_BASE_USUARIO        = String.format("%s/Usuario", URL_BASE);
    public static final  String URL_BASE_VENTA          = String.format("%s/Venta", URL_BASE);

}
