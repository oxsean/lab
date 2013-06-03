package cn.lvu.model;

/**
 * .
 * <p/>
 *
 * @author <a href="mailto:oxsean@gmail.com">sean yang</a>
 * @version V1.0, 11-6-10
 */
public enum RegionLevel {
    PROVINCE("省"),
    CITY("市"),
    DISTRICT("区"),
    STREET("街道"),
    NEIGHBORHOOD("街坊"),
    COUNTY("县"),
    TOWN("乡镇"),
    VILAGE("村");

    private String label;

    RegionLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
