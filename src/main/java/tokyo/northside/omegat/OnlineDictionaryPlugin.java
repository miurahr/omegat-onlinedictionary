package tokyo.northside.omegat;

import org.omegat.core.Core;
import org.omegat.core.CoreEvents;
import org.omegat.core.events.IApplicationEventListener;

public final class OnlineDictionaryPlugin {

    private OnlineDictionaryPlugin() { }

    /**
     * load plugin.
     */
    public static void loadPlugins() {
        CoreEvents.registerApplicationEventListener(new OnlineDictionaryApplicationEventListener());
    }

    /**
     * unload plugin.
     */
    public static void unloadPlugins() {
    }

    static class OnlineDictionaryApplicationEventListener implements IApplicationEventListener {
        @Override
        public void onApplicationStartup() {
            Core.getDictionaries().addDictionaryFactory(new OnlineDictionary());
        }

        @Override
        public void onApplicationShutdown() {
        }
    }
}
