package com.example.lin.mt.recycleviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<Fruit> fruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        // LinearLayoutManager用于制定RecyclerView的布局方式，这里使用的LinearLayoutManager是线性布局的意思，可以
        // 实现和ListView类似的效果，除此之外RecyclerView还给我们提供了GridLayoutManager 和 StaggeredGridLayoutManager
        // 这个两种内置的布局排列方式。GridLayoutManager可以用于实现网格布局，StaggeredGridLayoutManager可以用于实现瀑布流布局。
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(fruitAdapter);
    }
    private void initFruits(){
        for (int i = 0; i < 2; i++){
//            Fruit apple = new Fruit("Apple", R.drawable.apple_pic);
//            fruitList.add(apple);
//            Fruit banana = new Fruit("Banana", R.drawable.banana_pic);
//            fruitList.add(banana);
//            Fruit orange = new Fruit("Orange", R.drawable.orange_pic);
//            fruitList.add(orange);
//            Fruit watermelon = new Fruit("Watermelon", R.drawable.watermelon_pic);
//            fruitList.add(watermelon);
//            Fruit pear = new Fruit("Pear", R.drawable.pear_pic);
//            fruitList.add(pear);
//            Fruit grape = new Fruit("Grape", R.drawable.grape_pic);
//            fruitList.add(grape);
//            Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple_pic);
//            fruitList.add(pineapple);
//            Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry_pic);
//            fruitList.add(strawberry);
//            Fruit cherry = new Fruit("Cherry", R.drawable.cherry_pic);
//            fruitList.add(cherry);
//            Fruit mango = new Fruit("Mango", R.drawable.mango_pic);
//            fruitList.add(mango);

            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

    private String getRandomLengthName(String name){
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++){
            builder.append(name);
        }
        return builder.toString();
    }
}
