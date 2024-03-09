package repository.sale_detailsimpl;

import config.DatabaseConnection;
import model.*;
import repository.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SaleDetailsRepositoryJDBCimpl implements Repository<sale_details> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    private sale_details createDetails(ResultSet resultSet) throws SQLException {
        sale_details st = new sale_details();
        st.setId(resultSet.getInt("id"));
        sale sale = new sale();
        sale.setId(resultSet.getInt("sale_id"));
        st.setSale(sale);
        toy toy = new toy();
        toy.setId(resultSet.getInt("toy_id"));
        toy.setName(resultSet.getString("toy_name"));
        toy.setPrice(resultSet.getInt("toy_price"));
        st.setToy(toy);
        st.setCantidad(resultSet.getInt("cantidad"));
        st.setPrice(resultSet.getInt("price"));

        return st;
    }
    @Override
    public List<sale_details> list() {
        return null;
    }

    @Override
    public sale_details byId(int id) {
        return null;
    }

    @Override
    public void save(sale_details saleDetails) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(sale_details saleDetails) {

    }
}
