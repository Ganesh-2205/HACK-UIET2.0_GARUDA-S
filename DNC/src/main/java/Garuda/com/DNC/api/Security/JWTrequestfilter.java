package Garuda.com.DNC.api.Security;

import Garuda.com.DNC.Model.DAO.VendorDAO;
import Garuda.com.DNC.Model.DAO.userDAO;
import Garuda.com.DNC.Model.LocalUser;
import Garuda.com.DNC.Model.Vendor;
import Garuda.com.DNC.service.JWTservice;
import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTrequestfilter extends OncePerRequestFilter {


    public JWTrequestfilter(VendorDAO vend, userDAO user, JWTservice jwtservice) {
        this.vend = vend;
        this.user = user;
        this.jwtservice = jwtservice;
    }

    private VendorDAO vend;


private userDAO user;
    private JWTservice jwtservice;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
 String  tokenheader=request.getHeader("Authorization");
          if(tokenheader!=null&&tokenheader.startsWith("Bearer1")) {
              String token = tokenheader.substring(8);
              try {
                  String username = jwtservice.getUsername(token);
                  Optional<LocalUser> opuser = user.findByUserIgnoreCase(username);
                  if (opuser.isPresent()) {
                      LocalUser cust = opuser.get();
                      UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(cust, null, new ArrayList());
                      auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                      SecurityContextHolder.getContext().setAuthentication(auth);
                  }

              }catch(JWTDecodeException e){

              }
          } if(tokenheader!=null&&tokenheader.startsWith("Bearer2")) {
            String token = tokenheader.substring(8);
            try {
                String username = jwtservice.getUsername(token);
                Optional<Vendor> opuser = vend.findByUserIgnoreCase(username);
                if (opuser.isPresent()) {
                    Vendor cust = opuser.get();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(cust, null, new ArrayList());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

            }catch(JWTDecodeException e){

            }
        }filterChain.doFilter(request, response);
    }
}
