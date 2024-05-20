package provider;

import lombok.Builder;
import lombok.Data;

    @Data
    @Builder
    public class Produtos {
        String title;
        String description;
        int price;
        double discountPercentage;
        double rating;
        int stock;
        String brand;
        String category;
        String thumbnail;
    }



