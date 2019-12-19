package com.mag.digikala.Model;

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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }

    public class Attribute {

        private String id;
        private String name;
        private String slug;
        private List<Term> terms;

        public void setTerms(List<Term> terms) {
            this.terms = terms;
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
