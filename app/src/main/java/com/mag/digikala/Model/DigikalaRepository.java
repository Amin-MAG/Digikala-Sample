package com.mag.digikala.Model;

import java.util.List;

public class DigikalaRepository {

    // Singleton

    private static DigikalaRepository instance;

    public DigikalaRepository() {

    }

    public static DigikalaRepository getInstance() {
        if (instance == null)
            instance = new DigikalaRepository();
        return instance;
    }

    // Products

    private List<Merchandise> allProducts;

    public void setAllProducts(List<Merchandise> allProducts) {
        this.allProducts = allProducts;
    }

    public List<Merchandise> getAllProducts() {
        return allProducts;
    }

    public Merchandise getProductById(String id) {
        for (Merchandise merchandise : allProducts) {
            if (merchandise.getId().equals(id)) return merchandise;
        }
        return null;
    }


    // Navigation items

    private  List<DigikalaMenuItem> navigationItems;

    public List<DigikalaMenuItem> getNavigationItems() {
        return navigationItems;
    }

    public void setNavigationItems(List<DigikalaMenuItem> navigationItems) {
        this.navigationItems = navigationItems;
    }

}
