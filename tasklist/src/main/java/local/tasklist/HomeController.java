package local.tasklist;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class HomeController {
    @Autowired
    private HttpSession session; //セッション用(TomCat)
    private final TaskListDao dao;
    record TaskItem(String id, String task, String deadline, boolean done){}

    @Autowired
    HomeController(TaskListDao dao){
        this.dao = dao;
    }

    @RequestMapping("/list")
    String listItems(Model model){
        List<TaskItem> taskItems = dao.findAll();
        model.addAttribute("taskList", taskItems);
        return "home.html";
    }

    @RequestMapping("/add")
    String addItem(@RequestParam("task") String task,
                   @RequestParam("deadline") String deadline){
        String id = UUID.randomUUID().toString().substring(0,8);
        TaskItem item = new TaskItem(id,task,deadline,false);
        dao.add(item);

        return "redirect:/list";
    }

    @RequestMapping("/delete")
    String deleteItem(@RequestParam("id") String id){
        dao.delete(id);
        return "redirect:/list";
    }

    @RequestMapping("/update")
    String updateItem(@RequestParam("id") String id,
                      @RequestParam("task") String task,
                      @RequestParam("deadline") String deadline,
                      @RequestParam("done") boolean done)
    {
        TaskItem taskItem = new TaskItem(id,task,deadline,done);
        dao.update(taskItem);
        return "redirect:/list";
    }
}
