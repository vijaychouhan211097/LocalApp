package com.example.localapp.activities.activities;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.localapp.activities.adapters.CategoryAdapter;
import com.example.localapp.activities.adapters.ProductAdapter;
import com.example.localapp.activities.models.Category;
import com.example.localapp.activities.models.Product;
import com.example.localapp.activities.utils.Constants;
import com.example.localapp.databinding.ActivityMainBinding;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
        initSlider();
    }

    private void initSlider() {
        binding.carousel.addData(new CarouselItem("https://images.ctfassets.net/olh8eu5nhh4a/qwj5qCAv2QXjxcJ24130E/9650879e27d84f86c9d606c3174ed1f2/OU_Verified_Dealer_Program.png", "some caption"));
        binding.carousel.addData(new CarouselItem("https://thumbs.dreamstime.com/b/special-offer-37086976.jpg", "some caption2"));
        binding.carousel.addData(new CarouselItem("https://cdn.shopify.com/s/files/1/2690/0106/files/IMG_5358.jpg?v=1632553132", "some caption3"));
    }

    void initCategories() {
        categories = new ArrayList<>();

        // Adding dummy data to categories
        categories.add(new Category("Painting", "ic_painting", "#FF0000", "Various paintings by artists", 1));
        categories.add(new Category("Sculpture", "ic_sculpture", "#00FF00", "Handmade sculptures", 2));
        categories.add(new Category("Jewelry", "ic_jewelry", "#0000FF", "Artistic jewelry pieces", 3));
        categories.add(new Category("Crafts", "ic_crafts", "#FFFF00", "Handmade crafts", 4));

        getCategories();
        categoryAdapter = new CategoryAdapter(this, categories);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.categoriesList.setLayoutManager(layoutManager);
        binding.categoriesList.setAdapter(categoryAdapter);
    }
    void getCategories() {
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(Request.Method.GET, Constants.GET_CATEGORIES_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("err", response);
                    JSONObject mainObj = new JSONObject(response);
                    if(mainObj.getString("status").equals("success")) {
                        JSONArray categoriesArray = mainObj.getJSONArray("categories");
                        for(int i =0; i< categoriesArray.length(); i++) {
                            JSONObject object = categoriesArray.getJSONObject(i);
                            Category category = new Category(
                                    object.getString("name"),
                                    Constants.CATEGORIES_IMAGE_URL + object.getString("icon"),
                                    object.getString("color"),
                                    object.getString("brief"),
                                    object.getInt("id")
                            );
                            categories.add(category);
                        }
                        categoryAdapter.notifyDataSetChanged();
                    } else {
                        // DO nothing
                    }
                } catch ( JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(request);
    }
    void initProducts() {

        products = new ArrayList<>();

        // Adding dummy data to products
        products.add(new Product("Abstract Painting", "https://graamyam.com/wp-content/uploads/2021/08/Graamyam-Blog-Image-1-optimized.png", "Available", 299.99, 10, 5, 1));
        products.add(new Product("Wooden Sculpture", "https://www.shutterstock.com/image-photo/artistic-painted-colorful-handcrafted-products-260nw-2224567737.jpg", "Available", 159.99, 15, 10, 2));
        products.add(new Product("Silver Necklace", "https://kingcraftviet.com/uploads/ckfinder/images/how%20to%20clean%20the%20handicraft%20product%20(4).jpg", "Available", 99.99, 20, 8, 3));
        products.add(new Product("Handmade Vase", "https://cdn.shopify.com/s/files/1/2690/0106/files/DSC04066.jpg", "Out of Stock", 49.99, 0, 0, 4));

        productAdapter = new ProductAdapter(this, products);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        binding.productList.setLayoutManager(layoutManager);
        binding.productList.setAdapter(productAdapter);
    }
}