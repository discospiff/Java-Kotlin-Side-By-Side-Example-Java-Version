package edu.uc.jonesbr.myapplication.dao;

import java.util.List;

import edu.uc.jonesbr.myapplication.dto.FoodType;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IFoodDAO {
    @GET("/discospiff/PlantPlacesKotlin/master/foodtypes.json")
    Call<List<FoodType>> getFoodTypes();
}
