package com.zulhke.salesstore.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "STORE_ORDER")
public class Sales {

    @Id
    private Integer id;
    private String orderId;
    private String orderDate;
    private String shipDate;
    private String shipMode;
    private String customerId;
    private String customerName;
    private String segment;
    private String country;
    private String city;
    private String state;
    private Integer postalCode;
    private String region;
    private String productId;
    private String category;
    private String subCategory;
    private String productName;
    private BigDecimal sales;
    private Integer quantity;
    private Integer discount;
    private BigDecimal profit;
    private Date timestamp;

    public Sales(Integer id, String orderId, String orderDate, String shipDate, String shipMode, String customerId, String customerName, String segment, String country, String city, String state, Integer postalCode, String region, String productName, BigDecimal sales, Integer quantity, Integer discount, BigDecimal profit, Date timestamp) {
        this.id = id;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.shipMode = shipMode;
        this.customerId = customerId;
        this.customerName = customerName;
        this.segment = segment;
        this.country = country;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.region = region;
        this.productName = productName;
        this.sales = sales;
        this.quantity = quantity;
        this.discount = discount;
        this.profit = profit;
        this.timestamp = timestamp;
    }
    public Sales() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getShipMode() {
        return shipMode;
    }

    public void setShipMode(String shipMode) {
        this.shipMode = shipMode;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getSales() {
        return sales;
    }

    public void setSales(BigDecimal sales) {
        this.sales = sales;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", orderDate=" + orderDate +
                ", shipDate=" + shipDate +
                ", shipMode='" + shipMode + '\'' +
                ", customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", segment='" + segment + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode=" + postalCode +
                ", region='" + region + '\'' +
                ", productName='" + productName + '\'' +
                ", sales=" + sales +
                ", quantity=" + quantity +
                ", discount=" + discount +
                ", profit=" + profit +
                ", timestamp=" + timestamp +
                '}';
    }

}
