package papler.projetologin.security;

import java.awt.desktop.SystemSleepEvent;
import java.util.concurrent.TimeUnit;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 86400000L;
    public static final String SCRET = "SenhadeSeguranca";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/login/salvar";


  //  public static void main(String[] args) {
    //    System.out.println(TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
   // }
}
