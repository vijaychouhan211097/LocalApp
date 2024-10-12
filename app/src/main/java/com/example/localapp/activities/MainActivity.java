package com.example.localapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.localapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

import adapters.CategoryAdapter;
import adapters.ProductAdapter;
import model.Category;
import model.Product;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    CategoryAdapter categoryAdapter;
    ArrayList<Category> categories;

    ProductAdapter productAdapter;
    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initCategories();
        initProducts();

    }
    void initCategories() {
        categories = new ArrayList<>();

        // Adding dummy data to categories
        categories.add(new Category("Painting", "ic_painting", "#FF0000", "Various paintings by artists", 1));
        categories.add(new Category("Sculpture", "ic_sculpture", "#00FF00", "Handmade sculptures", 2));
        categories.add(new Category("Jewelry", "ic_jewelry", "#0000FF", "Artistic jewelry pieces", 3));
        categories.add(new Category("Crafts", "ic_crafts", "#FFFF00", "Handmade crafts", 4));

        categoryAdapter = new CategoryAdapter(this, categories);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }
    void initProducts() {

        products = new ArrayList<>();

        // Adding dummy data to products
        products.add(new Product("Abstract Painting", "https://example.com/painting1.jpg", "Available", 299.99, 10, 5, 1));
        products.add(new Product("Wooden Sculpture", "https://example.com/sculpture1.jpg", "Available", 159.99, 15, 10, 2));
        products.add(new Product("Silver Necklace", "image_silver_necklace", "Available", 99.99, 20, 8, 3));
        products.add(new Product("Handmade Vase", "image_handmade_vase", "Out of Stock", 49.99, 0, 0, 4));

        productAdapter = new ProductAdapter(this, products);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
    }
}