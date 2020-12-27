package tokyo.northside.omegat.onlinedictionary.drivers;

import org.omegat.util.Language;
import tokyo.northside.omegat.onlinedictionary.dtd.OnlineDictionaryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DictDriver implements  IOnlineDictionaryDriver {
    private final OnlineDictionaryService service;
    private final Language source;
    private final Language target;
    private static final List<String> commands = new ArrayList<>();

    static {
		// RFC2229 3.1 - Initial Connection
		commands.add("");
		// RFC2229 3.2 - DEFINE
		commands.add("DEFINE");
		// RFC2229 3.3 - MATCH
		commands.add("MATCH");
		// RFC2229 3.5 - SHOW command
        commands.add("SHOW");
		// RFC2229 3.6
		commands.add("CLIENT");
		// RFC2229 3.7
        commands.add("STATUS");
		// RFC2229 3.8
        commands.add("HELP");
        // RFC2229 3.9
        commands.add("QUIT");
        // RFC2229 3.10
        commands.add("OPTION");
	}

    public DictDriver(final OnlineDictionaryService service, final Language source, final Language target) {
        this.service = service;
        this.source = source;
        this.target = target;
    }

    @Override
    public String getName() {
        return service.getName();
    }

    @Override
    public Set<String> readEntries(String word) {
        return null;
    }

    private String queryDefine(final String word) {
        String queryString = "DEFINE" + word;
        return queryString;
    }
}
