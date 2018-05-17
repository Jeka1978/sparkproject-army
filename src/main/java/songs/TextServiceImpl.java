package songs;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

/**
 * @author Evgeny Borisov
 */
@Service
public class TextServiceImpl implements TextService {
    @Override
    public List<String> topX(File path, int x) {
        return null;
    }
}
