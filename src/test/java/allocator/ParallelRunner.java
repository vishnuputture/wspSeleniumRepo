package allocator;

import com.winSupply.core.CoreScript;
import com.winSupply.framework.selenium.*;
import com.winSupply.framework.DataBaseOperation;
import com.winSupply.framework.FrameworkParameters;
import com.winSupply.framework.TestCaseBean;

/**
 * Class to facilitate parallel execution of test scripts
 * 
 * @author winSupply
 */
class ParallelRunner implements Runnable {
	private final SeleniumTestParameters testParameters;
	private int testBatchStatus = 0;

	/**
	 * Constructor to initialize the details of the test case to be executed
	 * 
	 * @param testParameters
	 *            The {@link SeleniumTestParameters} object (passed from the
	 *            {@link Allocator})
	 */
	ParallelRunner(SeleniumTestParameters testParameters) {
		super();

		this.testParameters = testParameters;
	}

	/**
	 * Function to get the overall test batch status
	 * 
	 * @return The test batch status (0 = Success, 1 = Failure)
	 */
	public int getTestBatchStatus() {
		return testBatchStatus;
	}

	@Override
	public void run() {
		TestCaseBean testCaseBean = new TestCaseBean();
		FrameworkParameters frameworkParameters = FrameworkParameters.getInstance();
		String testReportName, executionTime, testStatus;

		if (frameworkParameters.getStopExecution()) {
			testReportName = "N/A";
			executionTime = "N/A";
			testStatus = "Aborted";
			testBatchStatus = 1; // Non-zero outcome indicates failure
		} else {
			CoreScript coreScript = new CoreScript(this.testParameters);
			coreScript.driveTestExecution();

			testReportName = coreScript.getReportName();
			executionTime = coreScript.getExecutionTime();
			testStatus = coreScript.getTestStatus();
			testCaseBean = coreScript.getTestCaseBean();

			if ("failed".equalsIgnoreCase(testStatus)) {
				testBatchStatus = 1; // Non-zero outcome indicates failure
			}
		}

		ResultSummaryManager resultSummaryManager = ResultSummaryManager.getInstance();
		resultSummaryManager.updateResultSummary(testParameters, testReportName, executionTime, testStatus);
		/* DB-Updating reports to database */
		DataBaseOperation dbOperation = new DataBaseOperation();
		dbOperation.initializeTestParameters(testParameters);
		dbOperation.updateMongoDB("Run Manager", testCaseBean, executionTime, testStatus);

	}
}