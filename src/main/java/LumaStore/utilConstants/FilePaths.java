package LumaStore.utilConstants;

public enum FilePaths {
	CONFIG("./src/test/resources/config.properties"),
	SCREENSHOT("./extent-reports/failed-screenshots/{methodName}_{timestamp}.png"),
	REPORT("./extent-reports/reports/LumaTest-report_{timestamp}.html"),
	REPORTCONFIG("./src/test/resources/report-config.json");

    private final String pathTemplate;

    FilePaths(String pathTemplate) {
        this.pathTemplate = pathTemplate;
    }

    public String getPath() {
        return pathTemplate;
    }
    
    public String getPath(String timestamp) {
        return pathTemplate.replace("{timestamp}", timestamp);
    }

    public String getFormattedPath(String methodName, String timestamp) {
        return pathTemplate.replace("{methodName}", methodName).replace("{timestamp}", timestamp);
    }
}