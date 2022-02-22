package com.sebastianwrobel.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastianwrobel.recipe.domain.Recipe;
import com.sebastianwrobel.recipe.service.FileService;

@RestController
public class RecipesController {
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/gluten-free")
	public List<Recipe> getGlutenFree() throws IOException{
		 return fileService.readRecipes().stream()
		 			.filter(r -> r.getGlutenFree())
		 			.collect(Collectors.toList());
	}

	@GetMapping("/vegan")
	public List<Recipe> getVegan() throws IOException{
		return fileService.readRecipes().stream()
	 			.filter(r -> r.getVegan())
	 			.collect(Collectors.toList());
	}
	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getVeganAndGlutenFree() throws IOException{
		return fileService.readRecipes().stream()
	 			.filter(r -> r.getVegan() && r.getGlutenFree())
	 			.collect(Collectors.toList());
	}
	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarian() throws IOException{
		return fileService.readRecipes().stream()
	 			.filter(r -> r.getVegetarian())
	 			.collect(Collectors.toList());
	}
	@GetMapping("/all-recipes")
	public List<Recipe> getAllRecipes() throws IOException{
		return fileService.readRecipes();
	}
	
	
}
