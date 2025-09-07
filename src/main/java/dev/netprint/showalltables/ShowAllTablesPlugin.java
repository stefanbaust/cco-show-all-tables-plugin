package dev.netprint.showalltables;

import com.sap.scco.ap.plugin.BasePlugin;
import com.sap.scco.ap.plugin.PluginConfigurationDTO;
import com.sap.scco.ap.plugin.annotation.ui.JSInject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.jar.Manifest;

public class ShowAllTablesPlugin extends BasePlugin {

    private static final Logger logger = LoggerFactory.getLogger(ShowAllTablesPlugin.class);

    private String version;

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
        if (version == null) {
            readVersion();
        }
        return version;
    }

    public void readVersion() {
        URLClassLoader cl = (URLClassLoader) getClass().getClassLoader();
        try {
            Enumeration<URL> url = cl.findResources("META-INF/MANIFEST.MF");
            while (url.hasMoreElements()) {
                URL u = url.nextElement();
                InputStream is = u.openStream();
                Manifest manifest = new Manifest(is);
                String packageStr = manifest.getMainAttributes().getValue("Package");
                if (StringUtils.isNotEmpty(packageStr) && packageStr.equals(getClass().getPackage().getName())) {
                    String version = manifest.getMainAttributes().getValue("version");
                    if (version != null) {
                        this.version = version;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error while reading manifest", e);
            this.version = "latest";
        }
    }

    @Override
    public List<PluginConfigurationDTO> getPluginPropertyConfiguration() {
        return List.of();
    }

    @Override
    public boolean persistPropertiesToDB() {
        return true;
    }

    @Override
    public List<String> getProperitesToExport() {
        return List.of();
    }


    @JSInject(targetScreen = "NGUI")
    public InputStream[] jsInject() {
        return new InputStream[]{this.getClass().getResourceAsStream("/showAllTablesPlugin.js")};
    }

}
