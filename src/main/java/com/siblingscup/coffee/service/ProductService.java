package com.siblingscup.coffee.service;

import com.siblingscup.coffee.dto.ProductDto;
import com.siblingscup.coffee.dto.ProductIngredientDTO;
import com.siblingscup.coffee.model.Ingredient;
import com.siblingscup.coffee.model.Product;
import com.siblingscup.coffee.model.ProductIngredient;
import com.siblingscup.coffee.repository.IngredientRepository;
import com.siblingscup.coffee.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final String IMAGE_DIRECTORY = "/var/local/siblingscup/product-images/";

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product createProduct(ProductDto productDto, String imageUrl) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setProfitMargin(productDto.getProfitMargin());

        // Save Image
        product.setImageUrl(imageUrl);
        double priceDemo = 0;
        List<ProductIngredient> ingredients = new ArrayList<>();
        for (ProductIngredientDTO dto : productDto.getIngredients()) {
            Optional<Ingredient> ingredientOpt = ingredientRepository.findById(dto.getIngredientId());
            if (ingredientOpt.isPresent()) {
                ProductIngredient ingredient = new ProductIngredient();
                ingredient.setProduct(product);
                ingredient.setIngredient(ingredientOpt.get());
                ingredient.setQuantityRequired(dto.getQuantityRequired());
                ingredients.add(ingredient);
                priceDemo += ingredient.getIngredient().getPrice() * ingredient.getQuantityRequired();
            }
        }

        product.setIngredients(ingredients);

        // Calculate Price
        product.setPrice(priceDemo + productDto.getProfitMargin());

        return productRepository.save(product);
    }

    public String saveImage(MultipartFile file, String productName) {
        try {
            Path dirPath = Paths.get(IMAGE_DIRECTORY);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            String fileName = productName.replaceAll("\\s", "_").toLowerCase() + ".jpg";
            Path filePath = dirPath.resolve(fileName);

            // Resize and save using Thumbnailator
            InputStream inputStream = file.getInputStream();
            OutputStream outputStream = Files.newOutputStream(filePath);
            Thumbnails.of(inputStream)
                    .size(500, 500) // Resize to max 500x500 while maintaining aspect ratio
                    .outputFormat("jpg")
                    .toOutputStream(outputStream);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage());
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
