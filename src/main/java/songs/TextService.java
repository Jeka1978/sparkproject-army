package songs;

import java.io.File;
import java.util.List;

/**
 * @author Evgeny Borisov
 */
public interface TextService {
    List<String> topX(String artistName, int x);

}
