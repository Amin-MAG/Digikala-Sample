package com.mag.digikala.Controller.Fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;
import com.mag.digikala.Controller.Activities.CardActivity;
import com.mag.digikala.Model.Adapter.SliderViewPagerAdapter;
import com.mag.digikala.Model.Adapter.SpinnerAdapter;
import com.mag.digikala.Model.Product;
import com.mag.digikala.Model.ProductAttributesRepository;
import com.mag.digikala.Model.ProductImage;
import com.mag.digikala.Network.RetrofitApi;
import com.mag.digikala.Network.RetrofitInstance;
import com.mag.digikala.R;
import com.mag.digikala.Repository.CardRepository;
import com.mag.digikala.Var.Constants;
import com.mag.digikala.databinding.FragmentProductDetailBinding;
import com.mag.digikala.viewmodel.ProductViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailFragment extends Fragment {

    public static final String ARG_MECHANDICE = "arg_mechandice";

    private FragmentProductDetailBinding binding;
    private ProductViewModel viewModel;

    private RetrofitApi retrofitApi;
    private Realm realm;

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

        realm = Realm.getDefaultInstance();

        retrofitApi = RetrofitInstance.getInstance().create(RetrofitApi.class);

        requestToGetProduct();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_product_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ProductViewModel();
        binding.setProductViewModel(viewModel);

        setSpinners();
        sliderInitializer();
        priceView();

        binding.productDetailFragmentCardBtn.setOnClickListener(view1 -> {

            CardRepository.getInstance().addToCard(viewModel.getProduct());
            getActivity().startActivity(CardActivity.newIntent(getContext()));

        });

    }

    private void setSpinners() {
        List<String> spinnerColorArray = new ArrayList<>();
        for (ProductAttributesRepository.Term term : ProductAttributesRepository.getInstance().getAttributeById("3").getTerms())
            spinnerColorArray.add(term.getName());
        SpinnerAdapter colorAdapter = new SpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_item,
                spinnerColorArray
        );
        binding.productDetailFragmentColorSpinner.setAdapter(colorAdapter);


        List<String> spinnerSizeArray = new ArrayList<>();
        for (ProductAttributesRepository.Term term : ProductAttributesRepository.getInstance().getAttributeById("4").getTerms())
            spinnerSizeArray.add(term.getName());
        SpinnerAdapter sizeAdapter = new SpinnerAdapter(
                getActivity(),
                android.R.layout.simple_spinner_item,
                spinnerSizeArray
        );
        binding.productDetailFragmentSizeSpinner.setAdapter(sizeAdapter);
    }


    private void sliderInitializer() {
        ArrayList<String> urls = new ArrayList<>();
        if (viewModel.getProduct() != null) {
            for (ProductImage image : viewModel.getImages())
                urls.add(image.getSrc());
        }
        sliderAdapter = new SliderViewPagerAdapter(getFragmentManager(), urls);
        binding.productDetailActivityViewPager.setAdapter(sliderAdapter);
    }


    private void requestToGetProduct() {
        retrofitApi.getProductById(getArguments().getString(ARG_MECHANDICE)).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if (response.isSuccessful()) {

                    Product product = response.body();
                    viewModel.setProduct(product);
                    sliderInitializer();
//                    updateDetailFragment();

                }

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {

            }
        });
    }

    private void priceView() {
        binding.productDetailFragmentProductRegularPrice.setPaintFlags(binding.productDetailFragmentProductSalePrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }


    private void updateDetailFragment() {

//        binding.productDetailFragmentProductName.setText(getString(R.string.product_name) + " " + product.getName());
//        Element pTag;
//        if ((pTag = Jsoup.parse(product.getDescription()).body().select("p").first()) != null)
//            binding.productDetailFragmentProductShortDescription.setText(pTag.text());


    }

}
