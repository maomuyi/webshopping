package com.lfy.lfy202120201124;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.lfy.lfy202120201124.adapter.FavoriteAdapter;
import com.lfy.lfy202120201124.db.FavoriteDbHelper;
import com.lfy.lfy202120201124.entity.ProductInfo;

import java.util.List;

public class FavoritesActivity extends AppCompatActivity {
    private ListView listView;
    private FavoriteAdapter adapter;
    private List<ProductInfo> favoriteProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Toolbar toolbar = findViewById(R.id.toolbar); // 使用系统的 Toolbar
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = findViewById(R.id.favorites_list);
        favoriteProducts = FavoriteDbHelper.getInstance(this).getFavoriteProducts();
        adapter = new FavoriteAdapter(this, favoriteProducts);
        listView.setAdapter(adapter);
    }
}
