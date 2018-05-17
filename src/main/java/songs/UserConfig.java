package songs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Evgeny Borisov
 */
@Component
public class UserConfig implements Serializable{

    @Getter
    private List<String> garbage;

    @Value("${garbage}")
    private void setGarbage(String[] words) {
        garbage = asList(words);
    }
}
