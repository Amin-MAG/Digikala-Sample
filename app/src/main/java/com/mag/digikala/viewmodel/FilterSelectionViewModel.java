package com.mag.digikala.viewmodel;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.mag.digikala.Repository.FilterRepository;

import java.util.ArrayList;
import java.util.List;

public class FilterSelectionViewModel extends BaseObservable {


    private MutableLiveData<List<FilterRepository.Attribute>> attributes;
    private MutableLiveData<FilterRepository.Attribute> selectedAttribute;
    private MutableLiveData<List<FilterRepository.Term>> selectedTerms;


    public FilterSelectionViewModel() {

        this.attributes = new MutableLiveData<>();
        this.selectedAttribute = new MutableLiveData<>();
        this.selectedTerms = new MutableLiveData<>();

        this.attributes.setValue(FilterRepository.getInstance().getAttributes());
        this.selectedAttribute.setValue(attributes.getValue().get(0));
        this.selectedTerms.setValue(new ArrayList<>());

    }


    public MutableLiveData<List<FilterRepository.Attribute>> getAttributes() {
        return attributes;
    }

    public MutableLiveData<FilterRepository.Attribute> getSelectedAttribute() {
        return selectedAttribute;
    }

    public void onAttributeClicked(FilterRepository.Attribute attribute) {
        this.selectedAttribute.setValue(attribute);
    }

    public void onTermClicked(FilterRepository.Term term, boolean isExist) {
        List<FilterRepository.Term> terms = selectedTerms.getValue();
        if (isExist) {
            terms.add(term);
            selectedTerms.setValue(terms);
        } else {
            terms.remove(term);
            selectedTerms.setValue(terms);
        }
        Log.d("onTermClicked", "onTermClicked: " + terms);
    }

    public void clearSelectedTerms() {
        List<FilterRepository.Term> newValue = selectedTerms.getValue();
        newValue.clear();
        selectedTerms.setValue(newValue);
    }

    public void filter() {

    }

}
