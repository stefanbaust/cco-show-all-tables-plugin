package dev.baust.showalltables;

import com.sap.scco.ap.plugin.BasePlugin;
import com.sap.scco.ap.plugin.annotation.ui.JSInject;

import java.io.InputStream;

/**
 * Makes "All tables" the default view of the table overview instead of "My tables".
 * All logic lives in the injected JavaScript ({@code showAllTablesPlugin.js});
 * this class only registers the plugin and serves the script.
 */
public class ShowAllTablesPlugin extends BasePlugin {

    @Override
    public String getId() {
        return "ShowAllTablesPlugin";
    }

    @Override
    public String getName() {
        return "Show all Tables by Default";
    }

    @Override
    public String getVersion() {
        // Implementation-Version from the jar manifest; null outside a jar (IDE, unit tests)
        String version = getClass().getPackage().getImplementationVersion();
        return version != null ? version : "dev";
    }

    @JSInject(targetScreen = "NGUI")
    public InputStream[] jsInject() {
        return new InputStream[]{getClass().getResourceAsStream("/showAllTablesPlugin.js")};
    }

}
