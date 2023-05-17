package gov.abc.driverScript;

import org.apache.log4j.Logger;
import org.testng.ITestResult;

public class ReportUtil {
	public ReportUtil() {
		
	}
	
	Logger log = Logger.getLogger(ReportUtil.class.getName());

	public void testScenarioExecutionResult(ITestResult result) {
		try {
			switch(result.getStatus()) {
			case ITestResult.SUCCESS:
				GlobalVariables.projectCurrentStepResult = "PASS";
				log.info("--------- SCRIPT PASS ---------");
				break;
			case ITestResult.FAILURE:
				GlobalVariables.projectCurrentStepResult = "FAIL";
				log.info("--------- SCRIPT FAIL ---------");
				break;
			}
		}catch(Exception e) {
			log.error("----EXCEPTION --------- : in method testScenarioExecutionResult(ITestResult result)");
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
