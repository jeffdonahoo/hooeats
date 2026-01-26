package com.hooeats.api.repository;

import com.hooeats.api.entity.Ingredient;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class IngredientRepository {

    private final JdbcTemplate jdbcTemplate;

    public IngredientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Ingredient> rowMapper = (ResultSet rs, int rowNum) -> {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(rs.getString("ingredientid").trim());
        ingredient.setName(rs.getString("name"));
        String unit = rs.getString("unit");
        ingredient.setUnit(unit != null ? unit.trim() : null);
        ingredient.setUnitPrice(rs.getBigDecimal("unitprice"));
        String foodGroup = rs.getString("foodgroup");
        ingredient.setFoodGroup(foodGroup != null ? foodGroup.trim() : null);
        ingredient.setInventory(rs.getInt("inventory"));
        String vendorId = rs.getString("vendorid");
        ingredient.setVendorId(vendorId != null ? vendorId.trim() : null);
        return ingredient;
    };

    public List<Ingredient> findAll() {
        return jdbcTemplate.query("SELECT * FROM ingredients ORDER BY ingredientid", rowMapper);
    }

    public Optional<Ingredient> findById(String ingredientId) {
        List<Ingredient> ingredients = jdbcTemplate.query(
                "SELECT * FROM ingredients WHERE ingredientid = ?",
                rowMapper,
                ingredientId
        );
        return ingredients.isEmpty() ? Optional.empty() : Optional.of(ingredients.get(0));
    }

    public int save(Ingredient ingredient) {
        return jdbcTemplate.update(
                "INSERT INTO ingredients (ingredientid, name, unit, unitprice, foodgroup, inventory, vendorid) VALUES (?, ?, ?, ?, ?, ?, ?)",
                ingredient.getIngredientId(),
                ingredient.getName(),
                ingredient.getUnit(),
                ingredient.getUnitPrice(),
                ingredient.getFoodGroup(),
                ingredient.getInventory(),
                ingredient.getVendorId()
        );
    }

    public int update(Ingredient ingredient) {
        return jdbcTemplate.update(
                "UPDATE ingredients SET name = ?, unit = ?, unitprice = ?, foodgroup = ?, inventory = ?, vendorid = ? WHERE ingredientid = ?",
                ingredient.getName(),
                ingredient.getUnit(),
                ingredient.getUnitPrice(),
                ingredient.getFoodGroup(),
                ingredient.getInventory(),
                ingredient.getVendorId(),
                ingredient.getIngredientId()
        );
    }

    public int deleteById(String ingredientId) {
        return jdbcTemplate.update("DELETE FROM ingredients WHERE ingredientid = ?", ingredientId);
    }
}
