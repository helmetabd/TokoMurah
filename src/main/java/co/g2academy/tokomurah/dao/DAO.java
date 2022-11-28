package co.g2academy.tokomurah.dao;

import co.g2academy.tokomurah.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DAO {

    @Autowired
    private DataSource dataSource;

    private static final String INSERT = "insert into t_product(name, description, price, stock) values(?, ?, ?, ?)";
    private static final String UPDATE = "update t_product set name = ?, description = ?, price = ?, stock = ? where id = ?";
    private static final String SELECT = "select * from t_product where id = ?";
    private static final String SELECT_ALL = "select * from t_product";
    private static final String DELETE = "delete from t_product where id = ? ";
    private static final String DELETE_ALL = "delete from t_product ";

    public void delete(Integer id) {
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement deletePs = c.prepareStatement(DELETE);
            deletePs.setInt(1, id);
            deletePs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAll() {
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement deleteAllPs = c.prepareStatement(DELETE_ALL);
            deleteAllPs.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void save(Product product) {
        try {
            Connection c = dataSource.getConnection();
            if (product.getId() != null) {
                PreparedStatement updatePs = c.prepareStatement(UPDATE);
                updatePs.setString(1, product.getName());
                updatePs.setString(2, product.getDescription());
                updatePs.setInt(3, product.getPrice());
                updatePs.setInt(4, product.getStock());
                updatePs.setInt(5, product.getId());
                updatePs.execute();
            } else {
                PreparedStatement insertPs = c.prepareStatement(INSERT);
                insertPs.setString(1, product.getName());
                insertPs.setString(2, product.getDescription());
                insertPs.setInt(3, product.getPrice());
                insertPs.setInt(4, product.getStock());
                insertPs.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public User getUser(User username) {
//        try {
//            selectUserPs.setString(1, username.getUsername());
//            ResultSet rs = selectUserPs.executeQuery();
//            if (rs.next()) {
//                User user = getUserFromResultSet(rs);
//                return user;
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
    public Product getProductById(Integer id) {
        Product product = new Product();
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement selectPs = c.prepareStatement(SELECT);
            selectPs.setInt(1, id);
            ResultSet rs = selectPs.executeQuery();
            if (rs.next()) {
                return getProductsFromResultSet(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return product;
    }

    public List<Product> getProductsData() {
        List<Product> products = new ArrayList<>();
        try {
            Connection c = dataSource.getConnection();
            PreparedStatement selectAllPs = c.prepareStatement(SELECT_ALL);
            ResultSet rs = selectAllPs.executeQuery();
            return getProductFromResultSet(rs);
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return products;
    }

    public Product getProductsFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));
        return product;
    }

//    public User getUserFromResultSet(ResultSet rs) throws SQLException {
//        User user = new User();
//        user.setId(rs.getInt("id"));
//        user.setUsername(rs.getString("username"));
//        user.setPassword(rs.getString("password"));
//        return user;
//    }
    public List<Product> getProductFromResultSet(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(getProductsFromResultSet(rs));
        }
        return products;
    }

}
