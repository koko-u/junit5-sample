package jp.co.kokou.sample.logger;

import org.junit.jupiter.api.*;

import java.util.logging.Logger;

public interface TestLifecycleLogger {

    static final Logger LOG = Logger.getLogger(TestLifecycleLogger.class.getName());

    @BeforeAll
    static void beforeAllTests() {
        LOG.info("Before all tests");
    }
    @AfterAll
    static void afterAllTests() {
        LOG.info("After all tests");
    }

    @BeforeEach
    default void beforeEachTest(TestInfo testInfo) {
        LOG.info(() -> String.format("About to execute [%s]", testInfo.getDisplayName()));
    }
    @AfterEach
    default void afterEachTest(TestInfo testInfo) {
        LOG.info(() -> String.format("Finishing execution [%s]", testInfo.getDisplayName()));
    }
}
