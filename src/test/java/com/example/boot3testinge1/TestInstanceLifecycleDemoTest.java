package com.example.boot3testinge1;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class TestInstanceLifecycleDemoTest {

    @Test
    @DisplayName("Given a person to save with existing email then it should throw Exception")
    @Tag("unit")
    @Timeout(value = 5, unit = TimeUnit.SECONDS)
    void test1() throws InterruptedException {
        System.out.println("test1:" + this.hashCode());
        Thread.sleep(3000);
    }

    @Test
    @Tag("integration")
    @Disabled
    void test2() {
        System.out.println("test2:" + this.hashCode());
    }

}