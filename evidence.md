
Run mvn test -Pregression -Dbrowser=firefox -Denv=stage

[INFO] Scanning for projects...
[WARNING]
[WARNING] Some problems were encountered while building the effective model for org.example:JavaPlaywrightFramework:jar:1.0-SNAPSHOT
[WARNING] 'dependencies.dependency.(groupId:artifactId:type:classifier)' must be unique: org.testng:testng:jar -> duplicate declaration of version 7.12.0 @ line 46, column 21
[WARNING]
[WARNING] It is highly recommended to fix these problems because they threaten the stability of your build.
[WARNING]
[WARNING] For this reason, future Maven versions might no longer support building such malformed projects.
[WARNING]
[INFO]
[INFO] ----------------< org.example:JavaPlaywrightFramework >-----------------
[INFO] Building JavaPlaywrightFramework 1.0-SNAPSHOT
[INFO]   from pom.xml
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- resources:3.4.0:resources (default-resources) @ JavaPlaywrightFramework ---
[INFO] Copying 6 resources from src/main/resources to target/classes
[INFO]
[INFO] --- compiler:3.15.0:compile (default-compile) @ JavaPlaywrightFramework ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- resources:3.4.0:testResources (default-testResources) @ JavaPlaywrightFramework ---
[INFO] skip non existing resourceDirectory /home/runner/work/JavaPlaywrightFramework/JavaPlaywrightFramework/src/test/resources
[INFO]
[INFO] --- compiler:3.15.0:testCompile (default-testCompile) @ JavaPlaywrightFramework ---
[INFO] Nothing to compile - all classes are up to date.
[INFO]
[INFO] --- surefire:3.6.0-M1:test (default-test) @ JavaPlaywrightFramework ---
[INFO] Using auto detected provider org.apache.maven.surefire.junitplatform.JUnitPlatformProvider
[INFO]
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
SLF4J(W): No SLF4J providers were found.
SLF4J(W): Defaulting to no-operation (NOP) logger implementation
SLF4J(W): See https://www.slf4j.org/codes.html#noProviders for further details.
[INFO] Running tests.FrameworkBasicsDataProviderTest
[ERROR] Tests run: 2, Failures: 0, Errors: 1, Skipped: 1, Time elapsed: 1.555 s <<< FAILURE! -- in tests.FrameworkBasicsDataProviderTest
[ERROR] tests.FrameworkBasicsDataProviderTest.initializationError -- Time elapsed: 1.555 s <<< ERROR!
com.microsoft.playwright.PlaywrightException:
Error {
message='Failed to launch the browser process.
Browser logs:
╔════════════════════════════════════════════════════════════════════════════════════════════════╗
║ Looks like you launched a headed browser without having a XServer running.                     ║
║ Set either 'headless: true' or use 'xvfb-run <your-playwright-app>' before running Playwright. ║
║                                                                                                ║
║ <3 Playwright Team                                                                             ║
╚════════════════════════════════════════════════════════════════════════════════════════════════╝
name='Error
stack='Error: Failed to launch the browser process.
Browser logs:
╔════════════════════════════════════════════════════════════════════════════════════════════════╗
║ Looks like you launched a headed browser without having a XServer running.                     ║
║ Set either 'headless: true' or use 'xvfb-run <your-playwright-app>' before running Playwright. ║
║                                                                                                ║
║ <3 Playwright Team                                                                             ║
╚════════════════════════════════════════════════════════════════════════════════════════════════╝
at Firefox._launchProcess (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39599:19)
at async Firefox._innerLaunch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39452:86)
at async Firefox._innerLaunchWithRetries (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39439:18)
at async BrowserTypeDispatcher.launch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:55160:25)
at async _ProgressController.run (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:12149:27)
at async DispatcherConnection.dispatch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:18775:27)
}
Call log:
-   - <launching> /home/runner/.cache/ms-playwright/firefox-1538/firefox/firefox -no-remote -wait-for-browser -foreground -profile /tmp/playwright_firefoxdev_profile-OznzGL -juggler-pipe -silent
-   - <launched> pid=5960
-   - [pid=5960][err] [5962] Sandbox: CanCreateUserNamespace() unshare(CLONE_NEWPID): EPERM
-   - [pid=5960][err] Error: no DISPLAY environment variable specified
-   - [pid=5960] <process did exit: exitCode=1, signal=null>
-   - [pid=5960] starting temporary directories cleanup
-   - [pid=5960] <gracefully close start>
-   - [pid=5960] <kill>
-   - [pid=5960] <skipped force kill spawnedProcess.killed=false processClosed=true>
-   - [pid=5960] finished temporary directories cleanup
-   - [pid=5960] <gracefully close end>
      at com.microsoft.playwright.impl.WaitableResult.get(WaitableResult.java:58)
      at com.microsoft.playwright.impl.ChannelOwner.runUntil(ChannelOwner.java:147)
      at com.microsoft.playwright.impl.Connection.sendMessage(Connection.java:136)
      at com.microsoft.playwright.impl.ChannelOwner.sendMessage(ChannelOwner.java:133)
      at com.microsoft.playwright.impl.BrowserTypeImpl.launch(BrowserTypeImpl.java:48)
      at com.microsoft.playwright.impl.BrowserTypeImpl.launch(BrowserTypeImpl.java:35)
      at tests.BaseTest.setUp(BaseTest.java:37)
      at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
      at java.base/java.lang.reflect.Method.invoke(Method.java:565)
      at org.testng.internal.invokers.MethodInvocationHelper.invokeMethod(MethodInvocationHelper.java:141)
      at org.testng.internal.invokers.MethodInvocationHelper.invokeMethodConsideringTimeout(MethodInvocationHelper.java:71)
      at org.testng.internal.invokers.ConfigInvoker.invokeConfigurationMethod(ConfigInvoker.java:400)
      at org.testng.internal.invokers.ConfigInvoker.invokeConfigurations(ConfigInvoker.java:333)
      at org.testng.internal.invokers.TestInvoker.runConfigMethods(TestInvoker.java:846)
      at org.testng.internal.invokers.TestInvoker.invokeMethod(TestInvoker.java:611)
      at org.testng.internal.invokers.TestInvoker.invokeTestMethod(TestInvoker.java:230)
      at org.testng.internal.invokers.MethodRunner.runInSequence(MethodRunner.java:63)
      at org.testng.internal.invokers.TestInvoker$MethodInvocationAgent.invoke(TestInvoker.java:1005)
      at org.testng.internal.invokers.TestInvoker.invokeTestMethods(TestInvoker.java:203)
      at org.testng.internal.invokers.TestMethodWorker.invokeTestMethods(TestMethodWorker.java:154)
      at org.testng.internal.invokers.TestMethodWorker.run(TestMethodWorker.java:134)
      at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
      at org.testng.TestRunner.privateRun(TestRunner.java:744)
      at org.testng.TestRunner.run(TestRunner.java:616)
      at org.testng.SuiteRunner.runTest(SuiteRunner.java:421)
      at org.testng.SuiteRunner.runSequentially(SuiteRunner.java:413)
      at org.testng.SuiteRunner.privateRun(SuiteRunner.java:373)
      at org.testng.SuiteRunner.run(SuiteRunner.java:312)
      at org.testng.SuiteRunnerWorker.runSuite(SuiteRunnerWorker.java:52)
      at org.testng.SuiteRunnerWorker.run(SuiteRunnerWorker.java:95)
      at org.testng.TestNG.runSuitesSequentially(TestNG.java:1274)
      at org.testng.TestNG.runSuitesLocally(TestNG.java:1208)
      at org.testng.TestNG.runSuites(TestNG.java:1112)
      at org.testng.TestNG.run(TestNG.java:1079)
      at org.junit.support.testng.engine.TestNGTestEngine.configureAndRun(TestNGTestEngine.java:202)
      at org.junit.support.testng.engine.TestNGTestEngine.execute(TestNGTestEngine.java:174)
      at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:108)
      at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:88)
      at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:54)
      at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:67)
      at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:52)
      at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:96)
      at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:75)
      at org.apache.maven.surefire.junitplatform.LauncherAdapter.executeWithoutCancellationToken(LauncherAdapter.java:60)
      at org.apache.maven.surefire.junitplatform.LauncherAdapter.execute(LauncherAdapter.java:52)
      at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.execute(JUnitPlatformProvider.java:279)
      at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invokeAllTests(JUnitPlatformProvider.java:245)
      at org.apache.maven.surefire.junitplatform.JUnitPlatformProvider.invoke(JUnitPlatformProvider.java:213)
      at org.apache.maven.surefire.booter.ForkedBooter.runSuitesInProcess(ForkedBooter.java:384)
      at org.apache.maven.surefire.booter.ForkedBooter.execute(ForkedBooter.java:174)
      at org.apache.maven.surefire.booter.ForkedBooter.run(ForkedBooter.java:501)
      at org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:489)
      Caused by: com.microsoft.playwright.impl.DriverException: Error {
      message='Failed to launch the browser process.
      Browser logs:
      ╔════════════════════════════════════════════════════════════════════════════════════════════════╗
      ║ Looks like you launched a headed browser without having a XServer running.                     ║
      ║ Set either 'headless: true' or use 'xvfb-run <your-playwright-app>' before running Playwright. ║
      ║                                                                                                ║
      ║ <3 Playwright Team                                                                             ║
      ╚════════════════════════════════════════════════════════════════════════════════════════════════╝
      name='Error
      stack='Error: Failed to launch the browser process.
      Browser logs:
      ╔════════════════════════════════════════════════════════════════════════════════════════════════╗
      ║ Looks like you launched a headed browser without having a XServer running.                     ║
      ║ Set either 'headless: true' or use 'xvfb-run <your-playwright-app>' before running Playwright. ║
      ║                                                                                                ║
      ║ <3 Playwright Team                                                                             ║
      ╚════════════════════════════════════════════════════════════════════════════════════════════════╝
      at Firefox._launchProcess (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39599:19)
      at async Firefox._innerLaunch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39452:86)
      at async Firefox._innerLaunchWithRetries (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39439:18)
      at async BrowserTypeDispatcher.launch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:55160:25)
      at async _ProgressController.run (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:12149:27)
      at async DispatcherConnection.dispatch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:18775:27)
      }
      Call log:
-   - <launching> /home/runner/.cache/ms-playwright/firefox-1538/firefox/firefox -no-remote -wait-for-browser -foreground -profile /tmp/playwright_firefoxdev_profile-OznzGL -juggler-pipe -silent
-   - <launched> pid=5960
-   - [pid=5960][err] [5962] Sandbox: CanCreateUserNamespace() unshare(CLONE_NEWPID): EPERM
-   - [pid=5960][err] Error: no DISPLAY environment variable specified
-   - [pid=5960] <process did exit: exitCode=1, signal=null>
-   - [pid=5960] starting temporary directories cleanup
-   - [pid=5960] <gracefully close start>
-   - [pid=5960] <kill>
-   - [pid=5960] <skipped force kill spawnedProcess.killed=false processClosed=true>
-   - [pid=5960] finished temporary directories cleanup
-   - [pid=5960] <gracefully close end>
      at com.microsoft.playwright.impl.Connection.dispatch(Connection.java:277)
      at com.microsoft.playwright.impl.Connection.processOneMessage(Connection.java:229)
      at com.microsoft.playwright.impl.ChannelOwner.runUntil(ChannelOwner.java:145)
      ... 50 more
      [INFO]
      [INFO] Results:
      [INFO]
      [ERROR] Errors:
      [ERROR]   FrameworkBasicsDataProviderTest>BaseTest.setUp:37 » Playwright Error {
      message='Failed to launch the browser process.
      Browser logs:
      ╔════════════════════════════════════════════════════════════════════════════════════════════════╗
      ║ Looks like you launched a headed browser without having a XServer running.                     ║
      ║ Set either 'headless: true' or use 'xvfb-run <your-playwright-app>' before running Playwright. ║
      ║                                                                                                ║
      ║ <3 Playwright Team                                                                             ║
      ╚════════════════════════════════════════════════════════════════════════════════════════════════╝
      name='Error
      stack='Error: Failed to launch the browser process.
      Browser logs:
      ╔════════════════════════════════════════════════════════════════════════════════════════════════╗
      ║ Looks like you launched a headed browser without having a XServer running.                     ║
      ║ Set either 'headless: true' or use 'xvfb-run <your-playwright-app>' before running Playwright. ║
      ║                                                                                                ║
      ║ <3 Playwright Team                                                                             ║
      ╚════════════════════════════════════════════════════════════════════════════════════════════════╝
      at Firefox._launchProcess (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39599:19)
      at async Firefox._innerLaunch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39452:86)
      at async Firefox._innerLaunchWithRetries (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:39439:18)
      at async BrowserTypeDispatcher.launch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:55160:25)
      at async _ProgressController.run (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:12149:27)
      at async DispatcherConnection.dispatch (/tmp/playwright-java-1065333829882638291/package/lib/coreBundle.js:18775:27)
      }
      Call log:
-   - <launching> /home/runner/.cache/ms-playwright/firefox-1538/firefox/firefox -no-remote -wait-for-browser -foreground -profile /tmp/playwright_firefoxdev_profile-OznzGL -juggler-pipe -silent
-   - <launched> pid=5960
-   - [pid=5960][err] [5962] Sandbox: CanCreateUserNamespace() unshare(CLONE_NEWPID): EPERM
-   - [pid=5960][err] Error: no DISPLAY environment variable specified
-   - [pid=5960] <process did exit: exitCode=1, signal=null>
-   - [pid=5960] starting temporary directories cleanup
-   - [pid=5960] <gracefully close start>
-   - [pid=5960] <kill>
-   - [pid=5960] <skipped force kill spawnedProcess.killed=false processClosed=true>
-   - [pid=5960] finished temporary directories cleanup
-   - [pid=5960] <gracefully close end>
      [INFO]
      [ERROR] Tests run: 2, Failures: 0, Errors: 1, Skipped: 1
      [INFO]
      [INFO] ------------------------------------------------------------------------
      [INFO] BUILD FAILURE
      [INFO] ------------------------------------------------------------------------
      [INFO] Total time:  5.969 s
      [INFO] Finished at: 2026-08-19T12:26:40Z
      [INFO] ------------------------------------------------------------------------
      [ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.6.0-M1:test (default-test) on project JavaPlaywrightFramework:
      [ERROR]
      [ERROR] See /home/runner/work/JavaPlaywrightFramework/JavaPlaywrightFramework/target/surefire-reports for the individual test results.
      [ERROR] See dump files (if any exist) [date].dump, [date]-jvmRun[N].dump and [date].dumpstream.
      [ERROR] -> [Help 1]
      [ERROR]
      [ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
      [ERROR] Re-run Maven using the -X switch to enable full debug logging.
      [ERROR]
      [ERROR] For more information about the errors and possible solutions, please read the following articles:
      [ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException
      Error: Process completed with exit code 1.
