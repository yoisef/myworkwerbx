package customer.barcode.barcodewebx.productdatabasemodels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import customer.barcode.barcodewebx.converors.imageconvertor;

@Entity(tableName = "product_data")
public class Product {



    @PrimaryKey(autoGenerate = true)
    private int id;




    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("active")
    @Expose
    private Integer active;
    @SerializedName("reorder")
    @Expose
    private String reorder;
    @SerializedName("company_id")
    @Expose
    private String companyId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("unit_id")
    @Expose
    private String unitId;

    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    /*
    @SerializedName("company")
    @Expose
    private Company company;

    @TypeConverters(imageconvertor.class)
   private Image image;
    @SerializedName("unit")
    @Expose
    private Unit unit;
    @SerializedName("category")
    @Expose
    private Category category;
    */


    public Product(String name, String price, String barcode, String description, Integer active, String reorder, String companyId, String categoryId, String unitId, String createdAt, String updatedAt) {
        this.name = name;
        this.price = price;
        this.barcode = barcode;
        this.description = description;
        this.active = active;
        this.reorder = reorder;
        this.companyId = companyId;
        this.categoryId = categoryId;
        this.unitId = unitId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        /*
        this.company = company;
        this.image = image;
        this.unit = unit;
        this.category = category;
        */

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public String getReorder() {
        return reorder;
    }

    public void setReorder(String reorder) {
        this.reorder = reorder;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    /*

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
*/

}