package jp.co.kokou.sample;


import jp.co.kokou.sample.factory.TestInterfaceDynamicTestsDemo;
import jp.co.kokou.sample.logger.TestLifecycleLogger;
import jp.co.kokou.sample.logger.TimeExecutionLogger;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestInterfaceTests
        implements TestLifecycleLogger, TestInterfaceDynamicTestsDemo, TimeExecutionLogger {

    @Test
    void インターフェイスから色々もらう() {
        assertThat(1).isEqualTo(1);
    }
}
