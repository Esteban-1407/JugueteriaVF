package services.toyimpl;

import config.DatabaseConnection;
import model.category;
import model.toy;
import services.RepositoryToy;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class ToyRepositoryJDBClmpl implements RepositoryToy<toy> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }
    private toy createToy(ResultSet resultSet) throws SQLException {
        toy t = new toy();
        t.setId(resultSet.getInt("id"));
        t.setName(resultSet.getString("name"));
        t.setPrice(resultSet.getInt("price"));

        t.setCategory(new category(
                resultSet.getInt("id_category"),
                resultSet.getString("category_name")

        ));
        return t;
    }



    @Override
    public List<toy> list() {
        List<toy>productosList=new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        SELECT t.*, c.type as category_name, c.id as category_id
                        FROM toy AS t 
                        INNER JOIN category AS c ON t.id_category = c.id;
                        """
            ))
        {
            while (resultSet.next()){
                toy t = createToy(resultSet);
                productosList.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productosList;
    }

    @Override
    public toy byId(int id) {
        toy t =null;
        try (PreparedStatement preparedStatement=getConnection()
                .prepareStatement(""" 
                                    SELECT t.*, c.name as category_name, c.id as category_id
                                    FROM toy AS t 
                                    INNER JOIN category AS c ON t.id_category = c.id
                                    WHERE t.id=?
                                    """)
        ) {
            preparedStatement.setLong(1,id);
            ResultSet resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                t =createToy(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return t;
    }



    @Override
    public void save(toy toy) {
        try (PreparedStatement pst = getConnection()
                .prepareStatement("""
                                          INSERT INTO toy(name,price,id_category) values (?,?,?)
                                          """)


                ){
            pst.setString(1, toy.getName());
            pst.setDouble(2,toy.getPrice());
            pst.setInt(3,toy.getCategory().getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                      DELETE FROM toy where id=?
                                      """)
        ){
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<toy> showByTYpe() {
        List<toy> typesList = new ArrayList<>();
        try(Statement statement=getConnection().createStatement();
            ResultSet resultSet=statement.executeQuery(
                    """
                        
                            SELECT id_category, COUNT(*) FROM toy
                        JOIN category on toy.id_category = category.id
                        GROUP BY category.type
                        """
            ))
        {
            while (resultSet.next()){
                toy t = createToy(resultSet);
                typesList.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typesList;
    }


    @Override
    public void update(toy toy) {
        try(PreparedStatement preparedStatement = getConnection()
                .prepareStatement("""
                                    UPDATE toy SET name = ?, price = ?,  id_category = ? WHERE id = ?;
                                      """
                )
        ){
            preparedStatement.setInt(1,toy.getId());
            preparedStatement.setString(2, toy.getName());
            preparedStatement.setDouble(3, toy.getPrice());
            preparedStatement.setInt(4,toy.getCategory().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);

    }
}
}
