package com.mag.digikala.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductImage;
import com.mag.digikala.Var.Constants;

import org.jsoup.Jsoup;

public class ProductViewModel extends BaseObservable {

    private Product product;

    public ProductViewModel() {
    }

    @Bindable
    public String getTitle() {
        return product.getName();
    }

    // Should be changed
    @Bindable
    public String getTitleForProductDetail() {
        return product.getName() + "نام محصول : ";
    }

    @Bindable
    public String getImageSrc() {
        if (product.getImages().length != 0)
            return product.getImages()[0].getSrc();
        return null;
    }

    @Bindable
    public ProductImage[] getImages() {
        if (product.getImages().length != 0)
            return product.getImages();
        return  null;
    }

    @Bindable
    public String getRegularPrice() {
        String MONEY_STRING = Constants.SPACE_CHAR + "تومان";
        return !product.isOnSale() ? product.getRegularPrice() + MONEY_STRING : product.getSalePrice() + MONEY_STRING;
    }

    @Bindable
    public String getSalerPrice() {
        String MONEY_STRING = Constants.SPACE_CHAR + "تومان";
        return product.isOnSale() ? product.getRegularPrice() + MONEY_STRING : "";
    }

    @Bindable
    public String getProductShortDescription() {
        return Jsoup.parse(product.getShortDescription()).body().text();
    }


    @Bindable
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        notifyChange();
    }

}
