//  TASK ENGINE 

import de.hybris.platform.task.TaskEngine;

taskService = spring.getBean'taskService'

TaskEngine engine = taskService.getEngine();

// check state
boolean running = engine.isRunning();

// to stop
engine.stop()

// to start
engine.start()

println running
