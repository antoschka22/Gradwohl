package at.gradwohl.website.config;

import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "uaSxxcb3eMvgxd8syX2ff45GKOhAs9gSFjUBa3ETEaZwGix3kZ8yFRMmuHDDZRsreWHwhNV3NyM+sUAB5Meu5Ic1jNOQOEtNbvdppahzy7pIpbffhJMw52KdP4T1XNCJ0If5vKuhL520DGgsD9ANkvPdrw3WhvbfjJtDwnL4MxyLyxzLXZrtH+iuSchxzD7WIhwW9dAnNmkIVGZZFD2i672+bYjtuk8MduqU4chazNfVU8UfTLAEbMepHWyz3wZjMpBciO1vjwyCYow2un24p6IydZO69wOmdRLWGtUeFTli+M9X2YyEzmEGGv50bf8r1z0G2/MpiwOEBEdujbn9kpE8Jeln4eGMlaOuV0psDd4=";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String getRole(String token) {
        Claims claims = extractAllClaims(token);
        Map<String, Object> claimsMap = (Map<String, Object>) claims.get("Role");
        return (String) claimsMap.get("role");
    }

    public boolean getRoleIsZentrale(String token){
        return Objects.equals(getRole(token), "Zentrale");
    }

    public boolean getRoleIsVerkauf(String token){
        return Objects.equals(getRole(token), "Verkauf") || getRoleIsLeiter(token) || getRoleIsLeiter(token);
    }

    public boolean getRoleIsLeiter(String token){
        return Objects.equals(getRole(token), "Leiter") || getRoleIsZentrale(token);
    }

    public String generateToken(Mitarbeiter userDetails){
        HashMap<String, Object> test = new HashMap<>();
        test.put("Role", userDetails.getRole());
        return generateToken(test, userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, Mitarbeiter userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
