
package com.softserve.academy.dao.implementation;

import com.softserve.academy.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

@Repository
@PropertySource("classpath:tables.properties")
public class StatusDao extends Dao<Status> {

    @Autowired    
    public void setTable(@Value("${status}") String table) {
        this.table = table;
    }
    
    @Autowired
    public void setMapper(RowMapper<Status> mapper) {
        this.mapper = mapper;
    }
    
     


//
//  private DataSource dataSource;
//  private String createQuery;
//  private String readQuery;
//  private String readAllQuery;
//  private String updateQuery;
//  private String deleteQuery;
//
//  @Autowired
//  public StatusDao(DataSource dataSource) {
//    this.dataSource = dataSource;
//    createQuery = "Insert into Status (name) values (?);";
//    readQuery = "Select * from Status where id=?;";
//    readAllQuery = "Select * from Status";
//    updateQuery = "Update Status Set name =? Where id=?";
//    deleteQuery = "Delete From Status Where id=?";
//  }
//
//
//
//  @Override
//  public List<Status> getAll() throws SQLException {
//    List<Status> statuses = new ArrayList();
//    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(readAllQuery);
//    ResultSet resultSet = preparedStatement.executeQuery();
//    while (resultSet.next()) {
//      Status status = new Status();
//      status.setId(resultSet.getInt(1));
//      status.setName(resultSet.getString(2));
//      statuses.add(status);
//    }
//    preparedStatement.close();
//    resultSet.close();
//    return statuses;
//  }
//
//  @Override
//  public Status findOne(int id) throws SQLException {
//    Status status = null;
//    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(readQuery);
//    preparedStatement.setInt(1, id);
//    ResultSet resultSet = preparedStatement.executeQuery();
//    while (resultSet.next()) {
//      status = new Status();
//      status.setId(resultSet.getInt(1));
//      status.setName(resultSet.getString(2));
//      break;
//    }
//    preparedStatement.close();
//    resultSet.close();
//    return status;
//  }
//
//  @Override
//  public boolean update(Status entity) throws SQLException {
//    int result = 0;
//    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(updateQuery);
//    if (entity != null & !entity.getName().equals(null) & entity.getId() >= 0) {
//      preparedStatement.setString(1, entity.getName());
//      preparedStatement.setInt(2, entity.getId());
//      result = preparedStatement.executeUpdate();
//    }
//    preparedStatement.close();
//    return result >= 1;
//  }
//
//  @Override
//  public boolean delete(int id) throws SQLException {
//    int result = 0;
//    PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(deleteQuery);
//    preparedStatement.setInt(1, id);
//    result = preparedStatement.executeUpdate();
//    preparedStatement.close();
//    return result >= 1;
//
//  }
//
//  @Override
//  public Status create(Status entity) throws SQLException {
//    PreparedStatement preparedStatement =
//        dataSource.getConnection().prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
//    preparedStatement.setString(1, entity.getName());
//    preparedStatement.executeUpdate();
//    ResultSet resultSet = preparedStatement.getGeneratedKeys();
//    if (resultSet.next()) {
//      entity.setId(resultSet.getInt(1));
//    }
//    preparedStatement.close();
//    resultSet.close();
//    return entity;
//  }

    @Override
    public Status create(Status entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Status entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
