package com.toread.core.bpmn;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.log4j.Logger;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 探路者
 * @since 2014年-07月-12日
 */
public class ProcessEnginesTest {
    public static final Logger LOGGER = Logger.getLogger(ProcessEnginesTest.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Deployment
    public void testInit(){
        RepositoryService repositoryService = activitiRule.getRepositoryService();
        repositoryService.createDeployment()
                .addClasspathResource("com/toread/core.bpmn/VacationRequest.bpmn20.xml")
                .deploy();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employeeName", "Kermit");
        variables.put("numberOfDays", new Integer(4));
        variables.put("vacationMotivation", "I'm really tired!");
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
// Verify that we started a new process instance
        LOGGER.info("Number of process instances: " + runtimeService.createProcessInstanceQuery().count());


        TaskService taskService = activitiRule.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            LOGGER.info("Task available: " + task.getName());
        }


        Task task = tasks.get(0);

        Map<String, Object> taskVariables = new HashMap<String, Object>();
        taskVariables.put("vacationApproved", "false");
        taskVariables.put("managerMotivation", "We have a tight deadline!");
        taskService.complete(task.getId(), taskVariables);

        repositoryService.suspendProcessDefinitionByKey("vacationRequest");
        try {
            runtimeService.startProcessInstanceByKey("vacationRequest");
        } catch (ActivitiException e) {
            e.printStackTrace();
        }

    }
}
