package com.siblingscup.coffee.dto;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ProductDto {

    private String name;
        private double profitMargin;
    private List<ProductIngredientDTO> ingredients;

    @Override
  public   String toString()
    {
        return this.name+" "+profitMargin+"  "+ingredients.toString();
    }

    public double getProfitMargin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
