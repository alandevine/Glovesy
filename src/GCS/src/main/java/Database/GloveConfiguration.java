package Database;

import org.bson.Document;

import java.util.HashMap;

/**
 * Object comprised of low-level glove related settings.
 */
public class GloveConfiguration {
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
    private double minRingKnuckleResistance;
    private double maxRingKnuckleResistance;
    private double minRingNeutroResistance;
    private double maxRingNeutroResistance;
    private double minPinkyResistance;
    private double maxPinkyResistance;
    private double initialGyroX;
    private double initialGyroY;
    private double initialGyroZ;

    public GloveConfiguration() {
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
        this.minRingKnuckleResistance = 0;
        this.maxRingKnuckleResistance = 0;
        this.minRingNeutroResistance = 0;
        this.maxRingNeutroResistance = 0;
        this.minPinkyResistance = 0;
        this.maxPinkyResistance = 0;
        this.initialGyroX = 0;
        this.initialGyroY = 0;
        this.initialGyroZ = 0;
    }

    public double getMinThumbResistance() {
        return minThumbResistance;
    }

    public Document toBson() {
        Document config = new Document();

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
        config.append("minRingKnuckleResistance",   this.minRingKnuckleResistance);
        config.append("maxRingKnuckleResistance",   this.maxRingKnuckleResistance);
        config.append("minRingNeutroResistance",    this.minRingNeutroResistance);
        config.append("maxRingNeutroResistance",    this.maxRingNeutroResistance);
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
        config.put("minRingKnuckleResistance",   this.minRingKnuckleResistance);
        config.put("maxRingKnuckleResistance",   this.maxRingKnuckleResistance);
        config.put("minRingNeutroResistance",    this.minRingNeutroResistance);
        config.put("maxRingNeutroResistance",    this.maxRingNeutroResistance);
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
        this.minRingKnuckleResistance   = Double.parseDouble(config.getString("minRingKnuckleResistance"));
        this.maxRingKnuckleResistance   = Double.parseDouble(config.getString("maxRingKnuckleResistance"));
        this.minRingNeutroResistance    = Double.parseDouble(config.getString("minRingNeutroResistance"));
        this.maxRingNeutroResistance    = Double.parseDouble(config.getString("maxRingNeutroResistance"));
        this.minPinkyResistance         = Double.parseDouble(config.getString("minPinkyResistance"));
        this.maxPinkyResistance         = Double.parseDouble(config.getString("maxPinkyResistance"));
        this.initialGyroX               = Double.parseDouble(config.getString("initialGyroX"));
        this.initialGyroY               = Double.parseDouble(config.getString("initialGyroY"));
        this.initialGyroZ               = Double.parseDouble(config.getString("initialGyroZ"));
    }

    public void setMinThumbResistance(double minThumbResistance) {
        this.minThumbResistance = minThumbResistance;
    }

    public double getMaxThumbResistance() {
        return maxThumbResistance;
    }

    public void setMaxThumbResistance(double maxThumbResistance) {
        this.maxThumbResistance = maxThumbResistance;
    }

    public double getMinIndexKnuckleResistance() {
        return minIndexKnuckleResistance;
    }

    public void setMinIndexKnuckleResistance(double minIndexKnuckleResistance) {
        this.minIndexKnuckleResistance = minIndexKnuckleResistance;
    }

    public double getMaxIndexKnuckleResistance() {
        return maxIndexKnuckleResistance;
    }

    public void setMaxIndexKnuckleResistance(double maxIndexKnuckleResistance) {
        this.maxIndexKnuckleResistance = maxIndexKnuckleResistance;
    }

    public double getMinIndexNeutroResistance() {
        return minIndexNeutroResistance;
    }

    public void setMinIndexNeutroResistance(double minIndexNeutroResistance) {
        this.minIndexNeutroResistance = minIndexNeutroResistance;
    }

    public double getMaxIndexNeutroResistance() {
        return maxIndexNeutroResistance;
    }

    public void setMaxIndexNeutroResistance(double maxIndexNeutroResistance) {
        this.maxIndexNeutroResistance = maxIndexNeutroResistance;
    }

    public double getMinMiddleKnuckleResistance() {
        return minMiddleKnuckleResistance;
    }

    public void setMinMiddleKnuckleResistance(double minMiddleKnuckleResistance) {
        this.minMiddleKnuckleResistance = minMiddleKnuckleResistance;
    }

    public double getMaxMiddleKnuckleResistance() {
        return maxMiddleKnuckleResistance;
    }

    public void setMaxMiddleKnuckleResistance(double maxMiddleKnuckleResistance) {
        this.maxMiddleKnuckleResistance = maxMiddleKnuckleResistance;
    }

    public double getMinMiddleNeutroResistance() {
        return minMiddleNeutroResistance;
    }

    public void setMinMiddleNeutroResistance(double minMiddleNeutroResistance) {
        this.minMiddleNeutroResistance = minMiddleNeutroResistance;
    }

    public double getMaxMiddleNeutroResistance() {
        return maxMiddleNeutroResistance;
    }

    public void setMaxMiddleNeutroResistance(double maxMiddleNeutroResistance) {
        this.maxMiddleNeutroResistance = maxMiddleNeutroResistance;
    }

    public double getMinRingKnuckleResistance() {
        return minRingKnuckleResistance;
    }

    public void setMinRingKnuckleResistance(double minRingKnuckleResistance) {
        this.minRingKnuckleResistance = minRingKnuckleResistance;
    }

    public double getMaxRingKnuckleResistance() {
        return maxRingKnuckleResistance;
    }

    public void setMaxRingKnuckleResistance(double maxRingKnuckleResistance) {
        this.maxRingKnuckleResistance = maxRingKnuckleResistance;
    }

    public double getMinRingNeutroResistance() {
        return minRingNeutroResistance;
    }

    public void setMinRingNeutroResistance(double minRingNeutroResistance) {
        this.minRingNeutroResistance = minRingNeutroResistance;
    }

    public double getMaxRingNeutroResistance() {
        return maxRingNeutroResistance;
    }

    public void setMaxRingNeutroResistance(double maxRingNeutroResistance) {
        this.maxRingNeutroResistance = maxRingNeutroResistance;
    }

    public double getMinPinkyResistance() {
        return minPinkyResistance;
    }

    public void setMinPinkyResistance(double minPinkyResistance) {
        this.minPinkyResistance = minPinkyResistance;
    }

    public double getMaxPinkyResistance() {
        return maxPinkyResistance;
    }

    public void setMaxPinkyResistance(double maxPinkyResistance) {
        this.maxPinkyResistance = maxPinkyResistance;
    }

    public double getInitialGyroX() {
        return initialGyroX;
    }

    public void setInitialGyroX(double initialGyroX) {
        this.initialGyroX = initialGyroX;
    }

    public double getInitialGyroY() {
        return initialGyroY;
    }

    public void setInitialGyroY(double initialGyroY) {
        this.initialGyroY = initialGyroY;
    }

    public double getInitialGyroZ() {
        return initialGyroZ;
    }

    public void setInitialGyroZ(double initialGyroZ) {
        this.initialGyroZ = initialGyroZ;
    }
}
