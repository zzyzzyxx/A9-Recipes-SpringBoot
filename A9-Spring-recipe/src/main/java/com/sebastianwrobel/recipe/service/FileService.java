package com.sebastianwrobel.recipe.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.sebastianwrobel.recipe.domain.Recipe;

@Service
@ComponentScan("com.sebastianwrobel")
public class FileService {
	
	private  ArrayList<Recipe> recipes = new ArrayList<>();
		
	@Bean
	public List<Recipe> readRecipes() throws IOException{
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader("recipe.txt"));	 
            CSVFormat.Builder csvBuilder = CSVFormat.Builder.create();

            Iterable<CSVRecord> csvRecords = csvBuilder.setIgnoreSurroundingSpaces(true)
                    .setSkipHeaderRecord(false)
                    .setHeader()
                    .setQuote('"') 	
                    .setEscape('\\')
                    .build()
                    .parse(bufferedReader);

            for (CSVRecord record : csvRecords){
            	
                Recipe recipe = new Recipe();
                recipe.setCookingMinutes(Integer.parseInt(record.get("Cooking Minutes"))); 
                recipe.setDairyFree(Boolean.parseBoolean(record.get("Dairy Free")));
                recipe.setGlutenFree(Boolean.parseBoolean(record.get("Gluten Free")));
                recipe.setInstructions(record.get("Instructions"));
                recipe.setPreparationMinutes(Double.parseDouble(record.get("Preparation Minutes")));
                recipe.setPricePerServing(Double.parseDouble(record.get("Price Per Serving")));
                recipe.setReadyInMinutes(Integer.parseInt(record.get("Ready In Minutes")));
                recipe.setServings(Integer.parseInt(record.get("Servings")));
                recipe.setSpoonacularScore(Double.parseDouble(record.get("Spoonacular Score")));
                recipe.setTitle(record.get("Title"));
                recipe.setVegan(Boolean.parseBoolean(record.get("Vegan")));
                recipe.setVegetarian(Boolean.parseBoolean(record.get("Vegetarian")));
                recipes.add(recipe);
            	
            	
            }
        
        return recipes;  		
	}
}
