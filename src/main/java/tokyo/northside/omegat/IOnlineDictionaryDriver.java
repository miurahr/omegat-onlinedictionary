package tokyo.northside.omegat;

import java.util.List;

public interface IOnlineDictionaryDriver {

    String getName();

    List<String> readDefinition(final String word);

}
