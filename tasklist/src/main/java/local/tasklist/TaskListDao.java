package local.tasklist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import local.tasklist.HomeController.*;

import java.util.List;
import java.util.Map;

//DAO(DBとの受け渡し)
@Service
public class TaskListDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    TaskListDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //DBのレコード一覧表示
    public List<TaskItem> findAll(){
        //DBにSELECT文を発行し、データ一覧を取得する
        String query = "SELECT id , task , deadline , done FROM tasklist";
        List<Map<String,Object>> result = jdbcTemplate.queryForList(query);

        //データの一覧をTaskItemのListに変換して返す
        List<TaskItem> taskItems = result.stream()
                .map((Map<String,Object> row) -> new TaskItem(
                        row.get("id").toString(),
                        row.get("task").toString(),
                        row.get("deadline").toString(),
                        (Boolean)row.get("done")))
                .toList();

        return taskItems;
    }

    //DBへレコード追加
    public void add(TaskItem taskItem){
        int num = jdbcTemplate.update("INSERT INTO tasklist(id,task,deadline,done) VALUES(?,?,?,?)",
                                      taskItem.id(),
                                      taskItem.task(),
                                      taskItem.deadline(),
                                      taskItem.done()
        );
    }

    //DBのレコード削除
    public int delete(String id){
        int num = jdbcTemplate.update("DELETE FROM tasklist WHERE id = ?", id);
        return num;
    }

    //DBのレコード修正、上書き
    public int update(TaskItem taskItem){
        int num = jdbcTemplate.update(
                "UPDATE TASKLIST SET TASK = ?, DEADLINE = ?, DONE = ? WHERE ID = ?",
                taskItem.task(),
                taskItem.deadline(),
                taskItem.done(),
                taskItem.id());
        return num;
    }
}
