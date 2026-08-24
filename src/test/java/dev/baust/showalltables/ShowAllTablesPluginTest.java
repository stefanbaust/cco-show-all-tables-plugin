package dev.baust.showalltables;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShowAllTablesPluginTest {

    private final ShowAllTablesPlugin plugin = new ShowAllTablesPlugin();

    @Test
    void identity() {
        assertEquals("ShowAllTablesPlugin", plugin.getId());
        assertEquals("Show all Tables by Default", plugin.getName());
    }

    @Test
    void versionFallsBackToDevOutsideAJar() {
        assertEquals("dev", plugin.getVersion());
    }

    @Test
    void jsInjectServesTheScript() throws IOException {
        InputStream[] streams = plugin.jsInject();
        assertEquals(1, streams.length);
        assertNotNull(streams[0]);
        String js = new String(streams[0].readAllBytes(), StandardCharsets.UTF_8);
        assertTrue(js.contains("tableOverviewModifyTabs"));
        // a missing trailing newline breaks the loading of ALL NGUI plugin scripts
        assertTrue(js.endsWith("\n"), "plugin JS must end with a newline");
    }

}
