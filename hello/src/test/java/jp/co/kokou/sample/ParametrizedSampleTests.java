package jp.co.kokou.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ParametrizedSampleTests {

    @ParameterizedTest
    @ValueSource(strings = {"hello", "world"})
    void パラメータつきのテスト(String param) {
        assertThat(param).isNotNull();
    }

    @ParameterizedTest
    @EnumSource(TimeUnit.class)
    void Enumをソースにしたテスト(TimeUnit timeUnit) {
        assertThat(timeUnit.name()).isNotBlank();
    }

    // テストデータを作成するメソッド
    // static で引数なし、戻り値は Stream, Iterable, Iterator, 配列 のいずれか
    static Stream<String> stringProvider() {
        return Stream.of("foo", "bar");
    }
    @ParameterizedTest
    @MethodSource(names = {"stringProvider"})
    void テストデータを提供するメソッドからパラメータを受けとる(String name) {
        assertThat(name).hasSize(3);
    }

    // テストデータが複数必要な場合は Arguments を使う
    @Data
    @AllArgsConstructor
    static class StringAndInt implements Arguments {
        private String name;
        private int num;

        @Override
        public Object[] get() {
            return new Object[]{name, num};
        }
    }
    static Stream<StringAndInt> stringAndIntProvider() {
        return Stream.of(
                new StringAndInt("foo1", 1),
                new StringAndInt("bar2", 2)
        );
    }
    @ParameterizedTest
    @MethodSource(names = {"stringAndIntProvider"})
    void 複数のパラメータを受け取る(String name, int num) {
        assertThat(name).endsWith(String.valueOf(num));
    }

    // CSVファイルからデータを取りたいが、classpath からデータが取れん。
    @ParameterizedTest
    @CsvFileSource(resources = {"two-column.csv"})
    void CSVファイルからデータを取得する(String first, int second) {
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(first).isNullOrEmpty();
            softly.assertThat(second).isGreaterThan(0);
        });
    }
}
