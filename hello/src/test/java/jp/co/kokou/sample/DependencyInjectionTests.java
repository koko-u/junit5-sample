package jp.co.kokou.sample;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("コンストラクタやメソッドのパラメータを解決する")
public class DependencyInjectionTests {

    @Nested
    @DisplayName("TestInfo が引数のタイプに与えられる場合")
    class TestInfoParameterResolverTest {

        @Test
        @Tag("my tag")
        @DisplayName("my test name")
        void 引数の解決をテストする(TestInfo testInfo) {
            SoftAssertions.assertSoftly(softly -> {
                softly.assertThat(testInfo.getDisplayName()).isEqualTo("my test name");
                softly.assertThat(testInfo.getTags()).containsExactly("my tag");
            });

        }
    }
}
