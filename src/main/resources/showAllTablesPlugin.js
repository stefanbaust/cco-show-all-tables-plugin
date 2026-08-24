Plugin.ShowAllTablesPlugin = class ShowAllTablesPlugin {
    constructor(pluginService, eventBus) {
        this.pluginService = pluginService;
        this.eventBus = eventBus;
        this.createPluginExits();
    }

    createPluginExits() {
        this.pluginExits = {
            'tableOverviewModifyTabs': (source, params) => {
                if (source) {
                    // always go to the view "All tables" after login / when building the tabs
                    source.viewSelectionModel?.setSelected(0);
                    source.filter(null);
                }
            }
        }
    }

}
