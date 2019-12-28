package com.mag.digikala.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ProductAttributesRepository {

    private static ProductAttributesRepository instance;

    private ProductAttributesRepository() {

    }

    public static ProductAttributesRepository getInstance() {
        if (instance == null)
            instance = new ProductAttributesRepository();
        return instance;
    }


    // Attributes

    private List<Attribute> attributes;

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;

        for (Attribute attribute : attributes) {
            Log.d("attributes", attribute.getName()  + " " + attribute.getId()+ " [ ");
            for (Term term : attribute.getTerms())
                Log.d("attributes", term.getName());

            Log.d("attributes", " ] ");
        }
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
            selectedTerms.add(term);
        }

        public void removeFromSelected(Term term) {
            if (selectedTerms.contains(term))
                selectedTerms.remove(term);
        }

        public List<Term> getSelectedTerms() {
            return selectedTerms;
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
