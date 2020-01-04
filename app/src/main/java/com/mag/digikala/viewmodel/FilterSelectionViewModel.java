package com.mag.digikala.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Repository.FilterRepository;

import java.util.List;

public class FilterSelectionViewModel extends BaseObservable {


    private MutableLiveData<List<FilterRepository.Attribute>> attributes;
    private MutableLiveData<FilterRepository.Attribute> selectedAttribute;
    private MutableLiveData<List<FilterRepository.Term>> terms;
    private MutableLiveData<List<FilterRepository.Term>> selectedTerms;


    public FilterSelectionViewModel() {

        this.attributes = new MutableLiveData<>();
        this.selectedAttribute = new MutableLiveData<>();
        this.terms = new MutableLiveData<>();

        this.attributes.postValue(FilterRepository.getInstance().getAttributes());
        Log.d("FilterSelectio", "FilterSelectionViewModel: " + getAttributes().getValue());
        if (attributes.getValue() != null && attributes.getValue().size() != 0) {
            this.selectedAttribute.postValue(attributes.getValue().get(0));
            this.terms.postValue(selectedAttribute.getValue().getTerms());
        }

    }


    public MutableLiveData<List<FilterRepository.Attribute>> getAttributes() {
        return attributes;
    }

    public MutableLiveData<FilterRepository.Attribute> getSelectedAttribute() {
        return selectedAttribute;
    }

}
