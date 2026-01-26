package com.hooeats.api.repository;

import com.hooeats.api.entity.MadeWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class MadeWithRepository {

    private final JdbcTemplate jdbcTemplate;

    public MadeWithRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<MadeWith> rowMapper = (ResultSet rs, int rowNum) -> {
        MadeWith madeWith = new MadeWith();
        madeWith.setItemId(rs.getString("itemid").trim());
        madeWith.setIngredientId(rs.getString("ingredientid").trim());
        madeWith.setQuantity(rs.getInt("quantity"));
        return madeWith;
    };

    public List<MadeWith> findAll() {
        return jdbcTemplate.query("SELECT * FROM madewith ORDER BY itemid, ingredientid", rowMapper);
    }

    public Optional<MadeWith> findById(String itemId, String ingredientId) {
        List<MadeWith> results = jdbcTemplate.query(
                "SELECT * FROM madewith WHERE itemid = ? AND ingredientid = ?",
                rowMapper,
                itemId,
                ingredientId
        );
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }

    public List<MadeWith> findByItemId(String itemId) {
        return jdbcTemplate.query(
                "SELECT * FROM madewith WHERE itemid = ? ORDER BY ingredientid",
                rowMapper,
                itemId
        );
    }

    public List<MadeWith> findByIngredientId(String ingredientId) {
        return jdbcTemplate.query(
                "SELECT * FROM madewith WHERE ingredientid = ? ORDER BY itemid",
                rowMapper,
                ingredientId
        );
    }

    public int save(MadeWith madeWith) {
        return jdbcTemplate.update(
                "INSERT INTO madewith (itemid, ingredientid, quantity) VALUES (?, ?, ?)",
                madeWith.getItemId(),
                madeWith.getIngredientId(),
                madeWith.getQuantity()
        );
    }

    public int update(MadeWith madeWith) {
        return jdbcTemplate.update(
                "UPDATE madewith SET quantity = ? WHERE itemid = ? AND ingredientid = ?",
                madeWith.getQuantity(),
                madeWith.getItemId(),
                madeWith.getIngredientId()
        );
    }

    public int deleteById(String itemId, String ingredientId) {
        return jdbcTemplate.update(
                "DELETE FROM madewith WHERE itemid = ? AND ingredientid = ?",
                itemId,
                ingredientId
        );
    }
}
