Plugin.ShowAllTablesPlugin = class ShowAllTablesPlugin {
    constructor(pluginService, eventBus) {
        this.pluginService = pluginService;
        this.eventBus = eventBus;
        this.userStore = this.pluginService.getContextInstance('userStore');
        this.connectionStore = this.pluginService.getContextInstance('connectionStore');
        this.init();
    }

    init() {
        this.eventBus.subscribe(this, true);
        this.createPluginExits();
    }

    //event bus subscriber
    handleEvent(event) {
        if (event.getType() === 'WORKCENTER_LOADED' && event.getSource() !== this) {
            // pluginconfig could be loaded here
        }
    }

    createPluginExits() {
        this.pluginExits = {
            'salesWorkcenterAfterViewBuild': (source, params) => {
            },
            'tableOverviewModifyTabs': (source, params) => {
                if (source) {
                    // always go to the view "All Tables" after login / when building the tabs
                    source.viewSelectionModel?.setSelected(0);
                    source.filter(null);
                }
            }
        }
    }

}
