package com.hooeats.api.repository;

import com.hooeats.api.entity.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Item> rowMapper = (ResultSet rs, int rowNum) -> {
        Item item = new Item();
        item.setItemId(rs.getString("itemid").trim());
        item.setName(rs.getString("name"));
        item.setPrice(rs.getBigDecimal("price"));
        item.setDateAdded(rs.getDate("dateadded") != null ? rs.getDate("dateadded").toLocalDate() : null);
        return item;
    };

    public List<Item> findAll() {
        return jdbcTemplate.query("SELECT * FROM items ORDER BY itemid", rowMapper);
    }

    public Optional<Item> findById(String itemId) {
        List<Item> items = jdbcTemplate.query(
                "SELECT * FROM items WHERE itemid = ?",
                rowMapper,
                itemId
        );
        return items.isEmpty() ? Optional.empty() : Optional.of(items.get(0));
    }

    public int save(Item item) {
        return jdbcTemplate.update(
                "INSERT INTO items (itemid, name, price, dateadded) VALUES (?, ?, ?, ?)",
                item.getItemId(),
                item.getName(),
                item.getPrice(),
                item.getDateAdded()
        );
    }

    public int update(Item item) {
        return jdbcTemplate.update(
                "UPDATE items SET name = ?, price = ?, dateadded = ? WHERE itemid = ?",
                item.getName(),
                item.getPrice(),
                item.getDateAdded(),
                item.getItemId()
        );
    }

    public int deleteById(String itemId) {
        return jdbcTemplate.update("DELETE FROM items WHERE itemid = ?", itemId);
    }

    public List<Item> findItemsByVendorId(String vendorId) {
        String sql = """
            SELECT DISTINCT i.* FROM items i
            JOIN madewith mw ON i.itemid = mw.itemid
            JOIN ingredients ing ON mw.ingredientid = ing.ingredientid
            WHERE ing.vendorid = ?
            ORDER BY i.itemid
            """;
        return jdbcTemplate.query(sql, rowMapper, vendorId);
    }
}
