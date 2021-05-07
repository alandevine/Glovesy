package Database;

import org.bson.Document;

import java.util.HashMap;

/**
 * Object comprised of low-level glove related settings.
 */
public class GloveConfiguration {
    private boolean defaultConfig;
    private double minThumbResistance;
    private double maxThumbResistance;
    private double minIndexKnuckleResistance;
    private double maxIndexKnuckleResistance;
    private double minIndexNeutroResistance;
    private double maxIndexNeutroResistance;
    private double minMiddleKnuckleResistance;
    private double maxMiddleKnuckleResistance;
    private double minMiddleNeutroResistance;
    private double maxMiddleNeutroResistance;
    private double minRingResistance;
    private double maxRingResistance;
    private double minPinkyResistance;
    private double maxPinkyResistance;
    private double initialGyroX;
    private double initialGyroY;
    private double initialGyroZ;

    public GloveConfiguration() {
        this.defaultConfig = true;
        this.minThumbResistance = 0;
        this.maxThumbResistance = 0;
        this.minIndexKnuckleResistance = 0;
        this.maxIndexKnuckleResistance = 0;
        this.minIndexNeutroResistance = 0;
        this.maxIndexNeutroResistance = 0;
        this.minMiddleKnuckleResistance = 0;
        this.maxMiddleKnuckleResistance = 0;
        this.minMiddleNeutroResistance = 0;
        this.maxMiddleNeutroResistance = 0;
        this.minRingResistance = 0;
        this.maxRingResistance = 0;
        this.minPinkyResistance = 0;
        this.maxPinkyResistance = 0;
        this.initialGyroX = 0;
        this.initialGyroY = 0;
        this.initialGyroZ = 0;
    }

    public Document toBson() {
        Document config = new Document();

        config.append("defaultConfig",              false);
        config.append("minThumbResistance",         this.minThumbResistance);
        config.append("maxThumbResistance",         this.maxThumbResistance);
        config.append("minIndexKnuckleResistance",  this.minIndexKnuckleResistance);
        config.append("maxIndexKnuckleResistance",  this.maxIndexKnuckleResistance);
        config.append("minIndexNeutroResistance",   this.minIndexNeutroResistance);
        config.append("maxIndexNeutroResistance",   this.maxIndexNeutroResistance);
        config.append("minMiddleKnuckleResistance", this.minMiddleKnuckleResistance);
        config.append("maxMiddleKnuckleResistance", this.maxMiddleKnuckleResistance);
        config.append("minMiddleNeutroResistance",  this.minMiddleNeutroResistance);
        config.append("maxMiddleNeutroResistance",  this.maxMiddleNeutroResistance);
        config.append("minRingResistance",          this.minRingResistance);
        config.append("maxRingResistance",          this.maxRingResistance);
        config.append("minPinkyResistance",         this.minPinkyResistance);
        config.append("maxPinkyResistance",         this.maxPinkyResistance);
        config.append("initialGyroX",               this.initialGyroX);
        config.append("initialGyroY",               this.initialGyroY);
        config.append("initialGyroZ",               this.initialGyroZ);

        return config;
    }

    public HashMap<String, Double> toHashMap() {
        HashMap<String, Double> config = new HashMap<>();

        config.put("minThumbResistance",         this.minThumbResistance);
        config.put("maxThumbResistance",         this.maxThumbResistance);
        config.put("minIndexKnuckleResistance",  this.minIndexKnuckleResistance);
        config.put("maxIndexKnuckleResistance",  this.maxIndexKnuckleResistance);
        config.put("minIndexNeutroResistance",   this.minIndexNeutroResistance);
        config.put("maxIndexNeutroResistance",   this.maxIndexNeutroResistance);
        config.put("minMiddleKnuckleResistance", this.minMiddleKnuckleResistance);
        config.put("maxMiddleKnuckleResistance", this.maxMiddleKnuckleResistance);
        config.put("minMiddleNeutroResistance",  this.minMiddleNeutroResistance);
        config.put("maxMiddleNeutroResistance",  this.maxMiddleNeutroResistance);
        config.put("minRingResistance",          this.minRingResistance);
        config.put("maxRingResistance",          this.maxRingResistance);
        config.put("minPinkyResistance",         this.minPinkyResistance);
        config.put("maxPinkyResistance",         this.maxPinkyResistance);
        config.put("initialGyroX",               this.initialGyroX);
        config.put("initialGyroY",               this.initialGyroY);
        config.put("initialGyroZ",               this.initialGyroZ);

        return config;
    }

    public void fromBson(Document config) {

        this.minThumbResistance         = Double.parseDouble(config.getString("minThumbResistance"));
        this.maxThumbResistance         = Double.parseDouble(config.getString("maxThumbResistance"));
        this.minIndexKnuckleResistance  = Double.parseDouble(config.getString("minIndexKnuckleResistance"));
        this.maxIndexKnuckleResistance  = Double.parseDouble(config.getString("maxIndexKnuckleResistance"));
        this.minIndexNeutroResistance   = Double.parseDouble(config.getString("minIndexNeutroResistance"));
        this.maxIndexNeutroResistance   = Double.parseDouble(config.getString("maxIndexNeutroResistance"));
        this.minMiddleKnuckleResistance = Double.parseDouble(config.getString("minMiddleKnuckleResistance"));
        this.maxMiddleKnuckleResistance = Double.parseDouble(config.getString("maxMiddleKnuckleResistance"));
        this.minMiddleNeutroResistance  = Double.parseDouble(config.getString("minMiddleNeutroResistance"));
        this.maxMiddleNeutroResistance  = Double.parseDouble(config.getString("maxMiddleNeutroResistance"));
        this.minRingResistance          = Double.parseDouble(config.getString("minRingResistance"));
        this.maxRingResistance          = Double.parseDouble(config.getString("maxRingResistance"));
        this.minPinkyResistance         = Double.parseDouble(config.getString("minPinkyResistance"));
        this.maxPinkyResistance         = Double.parseDouble(config.getString("maxPinkyResistance"));
        this.initialGyroX               = Double.parseDouble(config.getString("initialGyroX"));
        this.initialGyroY               = Double.parseDouble(config.getString("initialGyroY"));
        this.initialGyroZ               = Double.parseDouble(config.getString("initialGyroZ"));
    }

    public void setMaxValues(Document minValues) {
        this.maxThumbResistance         = minValues.getDouble("thumb");
        this.maxIndexKnuckleResistance  = minValues.getDouble("indexKnuckle");
        this.maxIndexNeutroResistance   = minValues.getDouble("indexNeutro");
        this.maxMiddleKnuckleResistance = minValues.getDouble("middleKnuckle");
        this.maxMiddleNeutroResistance  = minValues.getDouble("middleKnuckle");
        this.maxRingResistance          = minValues.getDouble("ring");
        this.maxPinkyResistance         = minValues.getDouble("pinky");
    }

    public void setMinValues(Document minValues) {
        this.minThumbResistance         = minValues.getDouble("thumb");
        this.minIndexKnuckleResistance  = minValues.getDouble("indexKnuckle");
        this.minIndexNeutroResistance   = minValues.getDouble("indexNeutro");
        this.minMiddleKnuckleResistance = minValues.getDouble("middleKnuckle");
        this.minMiddleNeutroResistance  = minValues.getDouble("middleKnuckle");
        this.minRingResistance          = minValues.getDouble("ring");
        this.minPinkyResistance         = minValues.getDouble("pinky");
    }
}
