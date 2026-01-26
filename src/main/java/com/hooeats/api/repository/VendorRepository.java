package com.hooeats.api.repository;

import com.hooeats.api.entity.Vendor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Repository
public class VendorRepository {

    private final JdbcTemplate jdbcTemplate;

    public VendorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Vendor> rowMapper = (ResultSet rs, int rowNum) -> {
        Vendor vendor = new Vendor();
        vendor.setVendorId(rs.getString("vendorid").trim());
        vendor.setCompanyName(rs.getString("companyname"));
        vendor.setRepFname(rs.getString("repfname"));
        vendor.setRepLname(rs.getString("replname"));
        String referredBy = rs.getString("referredby");
        vendor.setReferredBy(referredBy != null ? referredBy.trim() : null);
        return vendor;
    };

    public List<Vendor> findAll() {
        return jdbcTemplate.query("SELECT * FROM vendors ORDER BY vendorid", rowMapper);
    }

    public Optional<Vendor> findById(String vendorId) {
        List<Vendor> vendors = jdbcTemplate.query(
                "SELECT * FROM vendors WHERE vendorid = ?",
                rowMapper,
                vendorId
        );
        return vendors.isEmpty() ? Optional.empty() : Optional.of(vendors.get(0));
    }

    public int save(Vendor vendor) {
        return jdbcTemplate.update(
                "INSERT INTO vendors (vendorid, companyname, repfname, replname, referredby) VALUES (?, ?, ?, ?, ?)",
                vendor.getVendorId(),
                vendor.getCompanyName(),
                vendor.getRepFname(),
                vendor.getRepLname(),
                vendor.getReferredBy()
        );
    }

    public int update(Vendor vendor) {
        return jdbcTemplate.update(
                "UPDATE vendors SET companyname = ?, repfname = ?, replname = ?, referredby = ? WHERE vendorid = ?",
                vendor.getCompanyName(),
                vendor.getRepFname(),
                vendor.getRepLname(),
                vendor.getReferredBy(),
                vendor.getVendorId()
        );
    }

    public int deleteById(String vendorId) {
        return jdbcTemplate.update("DELETE FROM vendors WHERE vendorid = ?", vendorId);
    }
}
