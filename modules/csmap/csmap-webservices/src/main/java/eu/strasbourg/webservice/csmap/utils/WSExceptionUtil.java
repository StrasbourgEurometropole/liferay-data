package eu.strasbourg.webservice.csmap.utils;

import eu.strasbourg.service.csmap.exception.NoSuchRefreshTokenException;
import eu.strasbourg.webservice.csmap.exception.authentication.AuthenticationFailedException;
import eu.strasbourg.webservice.csmap.exception.jwt.InvalidJWTException;
import eu.strasbourg.webservice.csmap.exception.jwt.NoJWTInHeaderException;
import eu.strasbourg.webservice.csmap.exception.jwt.NoSubInJWTException;
import eu.strasbourg.webservice.csmap.exception.refreshtoken.RefreshTokenExpiredException;

public class WSExceptionUtil {

    public static int getStatusCodeFromException(Exception e) {
        int statusCode = 0;

        if (e instanceof NoJWTInHeaderException)
            statusCode = 400;
        else if (e instanceof AuthenticationFailedException ||
            e instanceof InvalidJWTException ||
            e instanceof NoSubInJWTException ||
            e instanceof NoSuchRefreshTokenException ||
            e instanceof RefreshTokenExpiredException)
            statusCode = 401;
        else
            statusCode = 500;

        return statusCode;
    }

}
