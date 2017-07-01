package jp.co.kokou.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstJUnit5Tests {

    @Test
    void はじめてのテスト() {
        assertThat(1 + 1).isEqualTo(2);
    }
}
