package ibf2022.assessment.paf.batch3.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

// DO NOT MODIFY THIS FILE.

public class Style {

    private int styleId;
    private String name;
    private int beerCount;    

    public Style(int styleId, String name, int beerCount) {
        this.styleId = styleId;
        this.name = name;
        this.beerCount = beerCount;
    }
    public int getStyleId() {
        return styleId;
    }
    public void setStyleId(int styleId) {
        this.styleId = styleId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getBeerCount() {
        return beerCount;
    }
    public void setBeerCount(int beerCount) {
        this.beerCount = beerCount;
    }

    @Override
    public String toString() {
        return "Style [styleId=" + styleId + ", name=" + name + ", beerCount=" + beerCount + "]";
    }
    public static Style create(SqlRowSet rs) {
        return new Style(
                    rs.getInt("styleId"),
                    rs.getString("name"),
                    rs.getInt("beerCount"));
    }

}
