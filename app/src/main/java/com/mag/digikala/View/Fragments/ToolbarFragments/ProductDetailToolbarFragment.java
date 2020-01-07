package com.mag.digikala.View.Fragments.ToolbarFragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.mag.digikala.Controller.Activities.CardActivity;
import com.mag.digikala.R;
import com.mag.digikala.Var.Constants;
import com.mag.digikala.databinding.FragmentProductDetailToolbarBinding;
import com.mag.digikala.viewmodel.MainToolbarViewModel;


public class ProductDetailToolbarFragment extends Fragment {

    private MainToolbarViewModel viewModel;

    private FragmentProductDetailToolbarBinding binding;

    private TextView cardNumber;

    public static ProductDetailToolbarFragment newInstance() {

        Bundle args = new Bundle();

        ProductDetailToolbarFragment fragment = new ProductDetailToolbarFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProductDetailToolbarFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(MainToolbarViewModel.class);
        viewModel.loadData();
        viewModel.getNumberOfCardProducts().observe(this, numberOfCardProducts -> {
            if (numberOfCardProducts == 0) {
                cardNumber.setBackgroundColor(getResources().getColor(R.color.nothing));
                cardNumber.setText(Constants.EMPTY_CHAR);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    cardNumber.setBackground(getResources().getDrawable(R.drawable.cart_counter));
                }
                cardNumber.setText(String.valueOf(numberOfCardProducts));
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_product_detail_toolbar, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardNumber = binding.productDetailToolbarFragmentCardNumber;

        setEvents();

    }

    private void setEvents() {
        binding.productDetailToolbarFragmentCardBtn.setOnClickListener(cardNumberView -> getActivity().startActivity(CardActivity.newIntent(getContext())));
        binding.toolbarFragmentBackBtn.setOnClickListener(backBtnView -> getActivity().finish());
    }


}
