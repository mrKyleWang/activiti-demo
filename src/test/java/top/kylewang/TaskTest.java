package top.kylewang;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 任务操作Test
 * @author Kyle.Wang
 * 2018/2/24 0024 16:38
 */
public class TaskTest {

    /**
     * 获取processEngine
     */
    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();


    /**
     * 执行流程
     */
    @Test
    public void startProcess(){
        // 流程key
        String processDefiKey = "leave";
        // 取runtimeService 运行时服务
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //设置变量
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("apply","张三");
        map.put("approve","李四");
        // 取得流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefiKey,map);
        System.out.println("流程实例id"+processInstance.getId());
        System.out.println("流程定义id"+processInstance.getProcessDefinitionId());
    }

    /**
     * 查询任务
     */
    @Test
    public void queryTaskTest(){
        // 任务的办理人
        String assignee = "李四";
        // 取taskService 任务服务
        TaskService taskService = processEngine.getTaskService();
        // 获取办理人的任务列表
        List<Task> taskList = taskService.createTaskQuery()   //创建一个任务查询对象
                .taskAssignee(assignee)   //指定代理人
                .list();
        // 遍历任务列表
        if(taskList!=null && taskList.size()>0){
            for (Task task : taskList) {
                System.out.println("任务的办理人: "+task.getAssignee());
                System.out.println("任务的id: "+task.getId());
                System.out.println("任务的name: "+task.getName());
            }
        }

    }

    /**
     * 完成任务1
     */
    @Test
    public void completeTest1(){
        // taskId
        String taskId = "10006";
        // 根据taskId完成任务
        processEngine.getTaskService().complete(taskId);
        System.out.println("当前任务执行完毕");
    }

    /**
     * 完成任务2
     */
    @Test
    public void completeTest2(){
        // taskId
        String taskId = "12502";
        // 设置变量
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pass",true); //同意申请
        // 根据taskId完成任务
        processEngine.getTaskService().complete(taskId,map);
        System.out.println("当前任务执行完毕");
    }
}
