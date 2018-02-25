package top.kylewang;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
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

    /**
     * 获取流程实例的状态
     */
    @Test
    public void getProcessInstanceStateTest(){
        String processInstanceId = "10001";
        ProcessInstance processInstance = processEngine.getRuntimeService()
                .createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();    //返回的数据要么是单行,要么是空,否则报错
        // 判断流程实例的状态
        if(processInstance!=null){
            System.out.println("该流程实例"+processInstanceId+"正在运行..."+
                    "当前活动的任务:"+processInstance.getActivityId());
        }else{
            System.out.println("当前的流程实例"+processInstanceId+"已经结束!");
        }
    }

    /**
     * 查看历史执行流程实例信息
     */
    @Test
    public void queryHistoryProcInst(){
        List<HistoricProcessInstance> processInstanceList = processEngine.getHistoryService()
                .createHistoricProcessInstanceQuery()
                .list();
        if(processInstanceList!=null&&processInstanceList.size()>0){
            for (HistoricProcessInstance historicProcessInstance : processInstanceList) {
                System.out.println("历史流程实例id:"+historicProcessInstance.getId());
                System.out.println("历史流程实例定义id:"+historicProcessInstance.getProcessDefinitionId());
                System.out.println("历史流程实例开始时间--结束时间:"
                        +historicProcessInstance.getStartTime()
                        +"--->"+historicProcessInstance.getEndTime());
            }
        }
    }

    /**
     * 查看历史执行流程任务信息
     */
    @Test
    public void queryHistoryTask(){
        String processInstanceId = "10001";
        List<HistoricTaskInstance> taskInstanceList = processEngine.getHistoryService()
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();
        if(taskInstanceList!=null&&taskInstanceList.size()>0){
            for (HistoricTaskInstance historicTaskInstance : taskInstanceList) {
                System.out.println("历史流程实例任务id:"+historicTaskInstance.getId());
                System.out.println("历史流程实例定义id:"+historicTaskInstance.getProcessDefinitionId());
                System.out.println("历史流程实例任务名称:"+historicTaskInstance.getName());
                System.out.println("历史流程实例任务处理人:"+historicTaskInstance.getAssignee());
                System.out.println("历史流程实例任务开始时间--结束时间:"
                        +historicTaskInstance.getStartTime()
                        +"--->"+historicTaskInstance.getEndTime());
            }
        }
    }
}
