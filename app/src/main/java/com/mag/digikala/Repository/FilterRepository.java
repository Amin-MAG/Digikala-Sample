package com.mag.digikala.Repository;

import android.os.Build;

import androidx.lifecycle.MutableLiveData;

import org.w3c.dom.Attr;

import java.util.ArrayList;
import java.util.List;

public class FilterRepository {

    private static FilterRepository instance;

    private FilterRepository() {

    }

    public static FilterRepository getInstance() {
        if (instance == null)
            instance = new FilterRepository();
        return instance;
    }

    // Filter

    private MutableLiveData<Attribute> filterAttribute;

    public MutableLiveData<Attribute> getFilterAttribute() {
        return filterAttribute;
    }


    // Default Attributes

    private List<Attribute> attributes;

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public Attribute getAttributeById(String id) {
        for (Attribute attribute : attributes)
            if (attribute.getId().equals(id))
                return attribute;
        return null;
    }

    // Classes

    public class Attribute {

        private String id;
        private String name;
        private String slug;
        private List<Term> terms;
        private List<Term> selectedTerms;

        public void setTerms(List<Term> terms) {
            this.terms = terms;
            selectedTerms = new ArrayList<>();
        }


        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSlug() {
            return slug;
        }

        public List<Term> getTerms() {
            return terms;
        }

        public void addToSelected(Term term) {
            filterAttribute.postValue(this);
            selectedTerms.add(term);
        }

        public void removeFromSelected(Term term) {
            if (selectedTerms.contains(term))
                selectedTerms.remove(term);
        }

        public List<Term> getSelectedTerm() {
            return selectedTerms;
        }

        public String getSelectedTermString() {
            List<String> strings = new ArrayList<>();
            for (Term term : selectedTerms)
                strings.add(term.id);
            return String.join(",", strings);
        }


    }

    public class Term {

        private String id;
        private String name;
        private String slug;
        private int count;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSlug() {
            return slug;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

    }

}
