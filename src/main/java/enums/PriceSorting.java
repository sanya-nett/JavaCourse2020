package enums;

public enum PriceSorting {
    UP("aprice"),
    DOWN("dprice");

    private final String dataSourceId;

    PriceSorting(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public static PriceSorting getSortByValue(String dataSourceId) {
        for (PriceSorting sorting : values()) {
            if (sorting.getDataSourceId().equals(dataSourceId)) {
                return sorting;
            }
        }
        throw new IllegalArgumentException(String.format("No enum found for data source id: %s", dataSourceId));
    }
}
