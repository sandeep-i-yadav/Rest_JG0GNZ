import com.soi.JaxbContextResolver;
import com.soi.controller.MovieController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/resources/MovieDatabase")
public class RestApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(MovieController.class);
        classes.add(JaxbContextResolver.class);
        return classes;
    }
}
