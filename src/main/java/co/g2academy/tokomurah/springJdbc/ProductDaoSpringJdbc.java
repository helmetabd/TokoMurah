/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.g2academy.tokomurah.springJdbc;

import co.g2academy.tokomurah.model.Product;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author personal
 */
@Component
public class ProductDaoSpringJdbc {

    private DataSource dataSource;
    private static final String INSERT = "insert into t_product(name, description, price, stock) values(?, ?, ?, ?)";
    private static final String UPDATE = "update t_product set name = ?, description = ?, price = ?, stock = ? where id = ?";
    private static final String SELECT = "select * from t_product where id = ?";
    private static final String SELECT_ALL = "select * from t_product";
    private static final String DELETE = "delete from t_product where id = ? ";
    private static final String DELETE_ALL = "delete from t_product ";
    private final ProductRowMapper mapper = new ProductRowMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> getProductsData() {
        return jdbcTemplate.query(SELECT_ALL, mapper);
    }

    public Product getProductById(Integer id) {
        return jdbcTemplate.queryForObject(SELECT, mapper, id);
    }

    public void save(Product product) {
        if (product.getId() != null) {
            jdbcTemplate.update(UPDATE, product.getName(), product.getDescription(), product.getPrice(), product.getStock(), product.getId());
        } else {
            jdbcTemplate.update(INSERT, product.getName(), product.getDescription(), product.getPrice(), product.getStock());
        }
    }
    
    public void delete(Integer id){
        jdbcTemplate.update(DELETE, id);
    }
    
    public void deleteAll(){
        jdbcTemplate.update(DELETE_ALL);
    }
}
