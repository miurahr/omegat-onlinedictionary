package tokyo.northside.omegat;

import org.omegat.core.dictionaries.DictionaryEntry;
import org.omegat.core.dictionaries.IDictionary;
import org.omegat.util.Language;

import java.util.ArrayList;
import java.util.List;

public class OnlineDictionaryDictionary implements IDictionary {
    private final List<IOnlineDictionaryDriver> drivers = new ArrayList<>();

    public OnlineDictionaryDictionary(final OnlineDictionaryService service,
                                      final Language source, final Language target) throws Exception {
        if ("omegawiki".equals(service.getName())) {
            drivers.add(new OmegawikiDriver(service.getEndpointUrl(), source, target));
        } else {
            throw new Exception("Unknown driver");
        }
    }

    @Override
    public List<DictionaryEntry> readArticles(final String word) throws Exception {
        List<DictionaryEntry> articles = new ArrayList<>();
        for (IOnlineDictionaryDriver driver : drivers) {
            for (String definition : driver.readDefinition(word)) {
                articles.add(new DictionaryEntry(word, definition));
            }
        }
        return articles;
    }

    @Override
    public List<DictionaryEntry> readArticlesPredictive(final String word) throws Exception {
        return null;
    }
}
