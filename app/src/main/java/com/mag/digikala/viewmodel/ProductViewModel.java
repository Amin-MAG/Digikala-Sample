package com.mag.digikala.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.mag.digikala.Model.Product;
import com.mag.digikala.Var.Constants;

public class ProductViewModel extends BaseObservable {

    private Product product;

    public ProductViewModel() {
    }

    @Bindable
    public String getTitle() {
        return product.getName();
    }

    @Bindable
    public String getImageSrc() {
        if (product.getImages().length != 0)
            return product.getImages()[0].getSrc();
        return null;
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

    public void setProduct(Product product) {
        this.product = product;
    }

}
