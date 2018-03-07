package eu.strasbourg.utils;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtils {

	public static String createJWT(String subClaimValue, int secondsBeforeExpiration) {
		try {
			Date now = new Date();
			Calendar c = Calendar.getInstance();
	        c.setTime(now);
	        c.add(Calendar.SECOND, secondsBeforeExpiration);
	        Date expiresAt = c.getTime();

		    Algorithm algorithm = Algorithm.HMAC256(StrasbourgPropsUtil.getInternalSecret());
		    String token = JWT.create()
		        .withIssuer(StrasbourgPropsUtil.getInternalIssuer())
		        .withClaim("sub", subClaimValue)
		        .withExpiresAt(expiresAt)
		        .sign(algorithm);
		    return token;
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
			return "";
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims.
			return "";
		}
	}

	public static boolean checkJWT(String token, String secret, String issuer) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm)
					.acceptExpiresAt(60 * 60 * 24).withIssuer(issuer)
					.build();
			verifier.verify(token);
			return true;
		} catch (UnsupportedEncodingException exception) {
			// UTF-8 encoding not supported
			return false;
		} catch (JWTVerificationException exception) {
			// Invalid signature/claims
			return false;
		}
	}

	public static String getJWTClaim(String token, String claim, String secret, String issuer) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm)
					.acceptExpiresAt(60 * 60 * 24).withIssuer(issuer)
					.build();
			DecodedJWT jwt = verifier.verify(token);
			Claim jwtClaim = jwt.getClaim(claim);
			if (jwtClaim != null) {
				return jwtClaim.asString();
			}
			return "";
		} catch (UnsupportedEncodingException exception) {
			// UTF-8 encoding not supported
			return "";
		} catch (JWTVerificationException exception) {
			// Invalid signature/claims
			return "";
		}
	}
}
