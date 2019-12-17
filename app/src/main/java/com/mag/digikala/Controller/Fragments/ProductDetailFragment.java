package com.mag.digikala.Controller.Fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.mag.digikala.Model.Adapter.SliderViewPagerAdapter;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductImage;
import com.mag.digikala.Model.ProductsRepository;
import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class ProductDetailFragment extends Fragment {

    public static final String ARG_MECHANDICE = "arg_mechandice";
    private Product product;

    private TextView productName, productShortDescription, productDescription;
    private TextView productRegularPrice, productSalePrice;
    private ViewPager slider;
    private SliderViewPagerAdapter sliderAdapter;


    public static ProductDetailFragment newInstance(String merchandiceId) {

        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_MECHANDICE, merchandiceId);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        product = ProductsRepository.getInstance().getProductById(getArguments().getString(ARG_MECHANDICE));

        slider = view.findViewById(R.id.product_detail_activity__view_pager);
        productName = view.findViewById(R.id.product_detail_fragment__product_name);
        productShortDescription = view.findViewById(R.id.product_detail_fragment__product_short_description);
        productRegularPrice = view.findViewById(R.id.product_detail_fragment__product_regular_price);
        productSalePrice = view.findViewById(R.id.product_detail_fragment__product_sale_price);
        productDescription = view.findViewById(R.id.product_detail_fragment__product_long_description);


        productName.setText(getString(R.string.product_name) + " " + product.getName());
        productShortDescription.setText(Jsoup.parse(product.getShortDescription()).body().text());
        Element pTag;
        if ((pTag = Jsoup.parse(product.getDescription()).body().select("p").first()) != null)
            productDescription.setText(pTag.text());

        sliderInitializer();
        priceView();


    }

    private void sliderInitializer() {
        ArrayList<String> urls = new ArrayList<>();
        for (Product m : ProductsRepository.getInstance().getAllProducts())
            if (product.getId().equals(m.getId()))
                for (ProductImage image : m.getImages())
                    urls.add(image.getSrc());
        sliderAdapter = new SliderViewPagerAdapter(getFragmentManager(), urls);
        slider.setAdapter(sliderAdapter);
    }

    private void priceView() {
        String MONEY_STRING = Constants.SPACE_CHAR + getResources().getString(R.string.tomans);
        String priceString;
        String priceInvalidString = "";

        if (product.getSalePrice().equals(Constants.EMPTY_CHAR))
            priceString = product.getPrice() + MONEY_STRING;
        else {
            priceString = product.getSalePrice() + MONEY_STRING;
            priceInvalidString = product.getPrice() + MONEY_STRING;
        }

        productRegularPrice.setText(priceInvalidString);
        productRegularPrice.setPaintFlags(productSalePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        productSalePrice.setText(priceString);
    }


}
