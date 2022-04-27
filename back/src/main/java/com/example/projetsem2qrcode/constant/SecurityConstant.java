package com.example.projetsem2qrcode.constant;

public class SecurityConstant {

    public static final long EXPIRATION_TIME = 432_000_000; // 5days
    public static final String TOKEN_PREFIX = "Bearer "; //ownership , no need more checks
    public static final String JWT_TOKEN_HEADER = "Jwt-Token";
    public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";
    public static final String GET_ARRAY_ISSUER = "Me";
    public static final String GET_ARRAY_ADMINISTRATION = "User Management Portal";
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE= "You need to log in to access this page";
    public static final String ACCESS_DENIED_MESSAGE= "You do not have the permission to access this page";
    public static final String OPTIONS_HTTP_METHOD = "OPTIONS";
//    public static final String [] PUBLIC_URLS= {"/user/login" , "user/register","user/restpassword/**","/user/imge/**"};
    public static final String [] PUBLIC_URLS= {"**"};
}
