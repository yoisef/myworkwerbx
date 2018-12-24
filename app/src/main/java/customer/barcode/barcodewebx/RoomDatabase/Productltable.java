package customer.barcode.barcodewebx.RoomDatabase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import customer.barcode.barcodewebx.productmodels.Category;
import customer.barcode.barcodewebx.productmodels.Company;
import customer.barcode.barcodewebx.productmodels.Image;
import customer.barcode.barcodewebx.productmodels.Unit;

@Entity(tableName = "products_table")
public class Productltable {


    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "barcode")
    private String barcode;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "active")
    private Integer active;

    @ColumnInfo(name = "reorder")
    private String reorder;

    @ColumnInfo
    private String companyId;

    private String categoryId;

    private String unitId;

    private Object subcateogryId;

    private String createdAt;

    private String updatedAt;

    private Company company;

    private Image image;

    private Unit unit;

    public Productltable(int id, String name, String price, String barcode,
                         String description, Integer active, String reorder,
                         String companyId, String categoryId, String unitId,
                         Object subcateogryId, String createdAt, String updatedAt,
                         Company company, Image image, Unit unit, Category category,
                         Object subCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.barcode = barcode;
        this.description = description;
        this.active = active;
        this.reorder = reorder;
        this.companyId = companyId;
        this.categoryId = categoryId;
        this.unitId = unitId;
        this.subcateogryId = subcateogryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.company = company;
        this.image = image;
        this.unit = unit;
        this.category = category;
        this.subCategory = subCategory;
    }

    @SerializedName("category")
    @Expose
    private Category category;
    @SerializedName("sub_category")
    @Expose
    private Object subCategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Object getSubcateogryId() {
        return subcateogryId;
    }

    public void setSubcateogryId(Object subcateogryId) {
        this.subcateogryId = subcateogryId;
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

    public Object getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(Object subCategory) {
        this.subCategory = subCategory;
    }

}