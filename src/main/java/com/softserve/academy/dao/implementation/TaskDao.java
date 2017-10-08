package com.softserve.academy.dao.implementation;

import com.softserve.academy.dao.mappers.TaskMapper;

import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@PropertySource("classpath:tables.properties")
public class TaskDao extends Dao<Task> {

    @Autowired
    public void setTable(@Value("${task}")String table) {
        this.table = table;
    }
    @Autowired
    public void setMapper(RowMapper<Task> mapper) {
        this.mapper = mapper;
    }


    /*@Autowired
    DataSource datasource;

    @Autowired
    public TaskDao(@Value("${task}") String taskTable, @Autowired RowMapper<Task> taskMapper) {
        super(taskTable, taskMapper);
    }*/

    @Override
    public List<Task> getAll() {
        String sql = "SELECT * FROM " + table;
        List<Task> tasks = operations.query(sql, new TaskMapper());

        return tasks;

        /*try (Statement stmt = datasource.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            ArrayList<Task> tasks = new ArrayList<Task>();
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(1));
                task.setName(rs.getString(2));
                task.setCreated_date(rs.getDate(3));
                task.setStart_date(rs.getDate(4));
                task.setEnd_date(rs.getDate(5));
                task.setEstimate_time(rs.getTime(6));
                task.setAssign_to(rs.getInt(7));
                task.setStatus_id(rs.getInt(8));
                task.setPriority_id(rs.getInt(9));
                task.setParent_id(rs.getInt(10));
                tasks.add(task);
            }
            return tasks;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;*/
    }


    @Override
    public Task findOne(int id) {
        String query = "SELECT id, name, created_date, start_date, end_date, estimate_time, "
                + "assign_to, status_id, priority_id, parent_id"
                + " FROM " + table + " WHERE tmw.task.id = :id";

        List<Task> tasks = operations.query(query, new MapSqlParameterSource("id", id), new TaskMapper());
        Task task = tasks.get(0);
        return task;

/*try (Statement stmt = datasource.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query);){

            Task task = new Task();
            while (rs.next()) {
                task.setId(rs.getInt(1));
                task.setName(rs.getString(2));
                task.setCreated_date(rs.getDate(3));
                task.setStart_date(rs.getDate(4));
                task.setEnd_date(rs.getDate(5));
                task.setEstimate_time(rs.getTime(6));
                task.setAssign_to(rs.getInt(7));
                task.setStatus_id(rs.getInt(8));
                task.setPriority_id(rs.getInt(9));
                task.setParent_id(rs.getInt(10));
            }

            return task;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;*/

    }

    @Override
    public boolean update(Task task) {
        int countUpdate = 0;
        MapSqlParameterSource param = new MapSqlParameterSource();
        java.sql.Timestamp created = new java.sql.Timestamp(task.getCreated_date().getTime());
        java.sql.Timestamp start = new java.sql.Timestamp(task.getStart_date().getTime());
        java.sql.Timestamp end = new java.sql.Timestamp(task.getEnd_date().getTime());
        java.sql.Time estimate = new java.sql.Time(task.getEstimate_time().getTime());
        String sql = "UPDATE " + table + " SET name=:name, created_date=:created_date, start_date=:start_date, " +
                "end_date=:end_date, estimate_time=:estimate_time, assign_to=:assign_to, status_id=:status_id, " +
                "priority_id=:priority_id, parent_id=:parent_id WHERE id=:id";

        param.addValue("name", task.getName());
        param.addValue("created_date", created);
        param.addValue("start_date", start);
        param.addValue("end_date", end);
        param.addValue("estimate_time", estimate);
        param.addValue("assign_to", task.getAssign_to());
        param.addValue("status_id", task.getStatus_id());
        param.addValue("priority_id", task.getPriority_id());
        param.addValue("parent_id", task.getParent_id());
        param.addValue("id", task.getId());

        return operations.update(sql, param) == 1;

/*try (PreparedStatement ps = datasource.getConnection().prepareStatement(sql);) {
            ps.setString(1, task.getName());
            ps.setTimestamp(2, created);
            ps.setTimestamp(3, start);
            ps.setTimestamp(4, end);
            ps.setTime(5, estimate);
            ps.setInt(6, task.getAssign_to());
            ps.setInt(7, task.getStatus_id());
            ps.setInt(8, task.getPriority_id());
            ps.setInt(9, task.getParent_id());
            ps.setInt(10, task.getId());
            ps.executeUpdate();
            return countUpdate == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;*/

    }

    @Override
    public boolean delete(int id) {
        int countUpdate = 0;
        String sql = "DELETE FROM " + table + " WHERE id=:id";

        return operations.update(sql, new MapSqlParameterSource("id", id)) > 0;

/*try (Statement stmt = datasource.getConnection().createStatement();) {
            stmt.executeUpdate(sql);
            return countUpdate == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  false;*/

    }

    @Override
    public Task create(Task task) {
        int id = 0;
        MapSqlParameterSource param = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        java.sql.Timestamp created = new java.sql.Timestamp(task.getCreated_date().getTime());
        java.sql.Timestamp start = new java.sql.Timestamp(task.getStart_date().getTime());
        java.sql.Timestamp end = new java.sql.Timestamp(task.getEnd_date().getTime());
        java.sql.Time estimate = new java.sql.Time(task.getEstimate_time().getTime());

        String sql = "INSERT INTO " + table + " (name, created_date, start_date, end_date, estimate_time, assign_to, status_id, "
                + "priority_id, parent_id) VALUES (:name, :created_date, :start_date, :end_date, :estimate_time, " +
                ":assign_to, :status_id, :priority_id, :parent_id)";

        param.addValue("name", task.getName());
        param.addValue("created_date", created);
        param.addValue("start_date", start);
        param.addValue("end_date", end);
        param.addValue("estimate_time", estimate);
        param.addValue("assign_to", 1);
        param.addValue("status_id", 1);
        param.addValue("priority_id", 1);
        param.addValue("parent_id", 0);

        operations.update(sql, param, keyHolder);
        task.setId(keyHolder.getKey().intValue());
        return task;

        /*try (Statement stmt = datasource.getConnection().createStatement()) {
            stmt.executeUpdate((sql), Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

            String query = "SELECT task_id, name, created_date, start_date, end_date, estimate_time, "
                    + "assign_to, status_id, priority_id, parent_id"
                    + " FROM tmw.task WHERE tmw.task.task_id=" + id;

            ResultSet rs2 = stmt.executeQuery(query);

            Task task2 = new Task();
            while (rs2.next()) {
                task2.setId(rs2.getInt(1));
                task2.setName(rs2.getString(2));
                task2.setCreated_date(rs2.getDate(3));
                task2.setStart_date(rs2.getDate(4));
                task2.setEnd_date(rs2.getDate(5));
                task2.setEstimate_time(rs2.getTime(6));
                task2.setAssign_to(rs2.getInt(7));
                task2.setStatus_id(rs2.getInt(8));
                task2.setPriority_id(rs2.getInt(9));
                task2.setParent_id(rs2.getInt(10));
            }

            return task2;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  null;*/

    }

/*
/@Override
    public ArrayList<Task> getTaskByStatus(int statusId) {
        String query = "SELECT task_id, name, created_date, start_date, end_date, estimate_time, "
                + "assign_to, status_id, priority_id, parent_id "
                + "FROM tmw.task WHERE status_id=" + statusId;

        return executeSelect(query, datasource);
    }

    //@Override
    public ArrayList<Task> getTaskByPriority(int priorityId) {
        String query = "SELECT task_id, name, created_date, start_date, end_date, estimate_time, "
                + "assign_to, status_id, priority_id, parent_id "
                + "FROM tmw.task WHERE priority_id=" + priorityId;

        return executeSelect(query, datasource);
    }

    //@Override
    public ArrayList<Task> getTasksCreatedByUser(int userId) {
        String query = "SELECT tmw.task.task_id, tmw.task.name, "
                + "tmw.task.created_date, tmw.task.start_date, "
                + "tmw.task.end_date, tmw.task.estimate_time, "
                + "tmw.task.assign_to, tmw.task.status_id, "
                + "tmw.task.priority_id, tmw.task.parent_id "
                + "FROM tmw.task INNER JOIN tmw.users_tasks "
                + "ON tmw.task.task_id=tmw.users_tasks.task_id "
                + "WHERE tmw.users_tasks.role_id=1 "
                + "AND tmw.users_tasks.user_id=" + userId;

        return executeSelect(query, datasource);
    }

    //@Override
    public ArrayList<Task> getTasksAssignToUser(int userId) {
        String query = "SELECT task_id, name, created_date, start_date, end_date, estimate_time, "
                + "assign_to, status_id, priority_id, parent_id "
                + "FROM tmw.task WHERE assign_to=" + userId;

        return executeSelect(query, datasource);
    }

    //@Override
    public ArrayList<Task> getTasksForToday() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String query = "SELECT task_id, name, created_date, start_date, end_date, estimate_time, "
                + "assign_to, status_id, priority_id, parent_id "
                + "FROM tmw.task WHERE start_date='" + sqlDate + "'";

        return executeSelect(query, datasource);
    }

    //@Override
    public ArrayList<Task> getTasksByTag(int tagId) {
        String query = "SELECT tmw.task.task_id, tmw.task.name, "
                + "tmw.task.created_date, tmw.task.start_date, "
                + "tmw.task.end_date, tmw.task.estimate_time, "
                + "tmw.task.assign_to, tmw.task.status_id, "
                + "tmw.task.priority_id, tmw.task.parent_id "
                + "FROM tmw.task INNER JOIN tmw.tags_tasks "
                + "ON tmw.task.task_id=tmw.tags_tasks.task_id "
                + "WHERE tmw.tags_tasks.tag_id=" + tagId;

        return executeSelect(query, datasource);
    }

    //@Override
    public  ArrayList<Task> treeOfTasks(int taskId) {
        String query = "SELECT task_id, name, created_date, start_date, end_date, estimate_time, "
                + "assign_to, status_id, priority_id, parent_id "
                + "FROM tmw.task WHERE parent_id=" + taskId;

        return executeSelect(query, datasource);
    }

    //@Override
    public User getAuthorOfTask(int taskId) {
        User user = new User();
        String query = "SELECT tmw.user.user_id, tmw.user.name "
                + "FROM tmw.user INNER JOIN tmw.users_tasks "
                + "ON tmw.user.user_id=tmw.users_tasks.user_id "
                + "WHERE tmw.users_tasks.role_id=1 "
                + "AND tmw.users_tasks.task_id = " + taskId;

        try (Statement stmt = datasource.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query);){

            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    //@Override
    public ArrayList<Tag> getTagsOfTask(int taskId) {
        String query = "SELECT tmw.tag.tag_id, tmw.tag.name "
                + "FROM tmw.tag INNER JOIN tmw.tags_tasks  "
                + "ON tmw.tag.tag_id=tmw.tags_tasks.tag_id  "
                + "WHERE tmw.tags_tasks.task_id = " + taskId;

        ArrayList<Tag> listOfTags = new ArrayList<Tag>();

        try (Statement stmt = datasource.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query);){


            while (rs.next()) {
                Tag tag = new Tag();
                tag.setId(rs.getInt(1));
                tag.setName(rs.getString(2));
                listOfTags.add(tag);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listOfTags;
    }


@Override
    public ArrayList<Comment> getCommentsOfTask(int taskId) {

        String query = "SELECT comment_id, comment FROM tmw.comment "
                + "WHERE task_id = " + taskId;

        ArrayList<Comment> listOfComments = new ArrayList<Comment>();

        try (Statement stmt = datasource.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(query);){

            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt(1));
                comment.setCommentText(rs.getString(2));
                listOfComments.add(comment);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listOfComments;
    }



    public static ArrayList<Task> executeSelect(String query, DataSource datasource) {

        ArrayList<Task> listOfTasks = new ArrayList<Task>();

        try (Statement statement = datasource.getConnection().createStatement();
             ResultSet rs = statement.executeQuery(query);){


            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(1));
                task.setName(rs.getString(2));
                task.setCreated_date(rs.getDate(3));
                task.setStart_date(rs.getDate(4));
                task.setEnd_date(rs.getDate(5));
                task.setEstimate_time(rs.getTime(6));
                task.setAssign_to(rs.getInt(7));
                task.setStatus_id(rs.getInt(8));
                task.setPriority_id(rs.getInt(9));
                task.setParent_id(rs.getInt(10));
                listOfTasks.add(task);
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return listOfTasks;
    }
*/

}
