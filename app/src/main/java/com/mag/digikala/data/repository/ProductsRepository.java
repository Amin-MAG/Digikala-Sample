package com.mag.digikala.data.repository;

import com.mag.digikala.data.model.CategoryGroup;
import com.mag.digikala.data.model.MenuItem;
import com.mag.digikala.data.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsRepository {

    // Singleton

    private static ProductsRepository instance;

    public ProductsRepository() {

    }

    public static ProductsRepository getInstance() {
        if (instance == null)
            instance = new ProductsRepository();
        return instance;
    }

    // Products //

    // Test
    private List<Product> allProducts = new ArrayList<>();
    // Offered
    private List<Product> offeredProducts = new ArrayList<>();
    // Best Seller
    private List<Product> topRatingProducts = new ArrayList<>();
    // Most View
    private List<Product> popularProducts = new ArrayList<>();

    public void setAllProducts(List<Product> allProducts) {
        this.allProducts = allProducts;
    }

    public void setOfferedProducts(List<Product> offeredProducts) {
        this.offeredProducts = offeredProducts;
    }

    public void setTopRatingProducts(List<Product> tppRatingProducts) {
        this.topRatingProducts = tppRatingProducts;
    }

    public void setPopularProducts(List<Product> popularProducts) {
        this.popularProducts = popularProducts;
    }

    public List<Product> getAllProducts() {
        return allProducts;
    }

    public Product getProductById(String id) {
        for (Product product : allProducts) {
            if (product.getId().equals(id)) return product;
        }
        return null;
    }

    public List<Product> getOfferedProducts() {
        return offeredProducts;
    }

    public List<Product> getTopRatingProducts() {
        return topRatingProducts;
    }

    public List<Product> getPopularProducts() {
        return popularProducts;
    }

    // Categories

    private Map<String, CategoryGroup> categoryMap;
    private List<CategoryGroup> parentCategory;

    public void setCategoryMap(Map<String, CategoryGroup> categoryMap) {
        this.categoryMap = categoryMap;
        this.parentCategory = new ArrayList<>(ProductsRepository.getInstance().getCategoryMap().values());
    }

    public List<CategoryGroup> getParentCategory() {
        return parentCategory;
    }

    public Map<String, CategoryGroup> getCategoryMap() {
        return categoryMap;
    }

    // Navigation items


}
