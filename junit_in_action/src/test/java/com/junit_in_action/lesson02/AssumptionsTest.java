package com.junit_in_action.lesson02;

import com.junit_in_action.lesson02.ch02.assumptions.Job;
import com.junit_in_action.lesson02.ch02.assumptions.SUT;
import com.junit_in_action.lesson02.ch02.assumptions.environment.JavaSpecification;
import com.junit_in_action.lesson02.ch02.assumptions.environment.OperationSystem;
import com.junit_in_action.lesson02.ch02.assumptions.environment.TestsEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

public class AssumptionsTest {

    private static final String EXPECTED_JAVA_VERSION = "25";

    private TestsEnvironment env = TestsEnvironment.builder()
            .javaSpecification(JavaSpecification.builder()
                    .version(System.getProperty("java.vm.specification.version"))
                    .build())
            .operationSystem(OperationSystem.builder()
                    .name(System.getProperty("os.name"))
                    .architecture(System.getProperty("os.arch"))
                    .build())
            .build();

    private SUT systemUnderTest = new SUT();

    @BeforeEach
    void setUp() {
        assumeTrue(env.isWindows());
    }

    @Test
    void noJobToRun() {
        assumingThat(
                () -> EXPECTED_JAVA_VERSION.equals(env.getJavaVersion()),
                () -> assertFalse(systemUnderTest.hasJobToRun())
        );
    }

    @Test
    void oneJobToRun() {
        systemUnderTest.run(new Job());
        assumingThat(
                env.isAmd64Architecture(),
                () -> assertTrue(systemUnderTest.hasJobToRun())
        );
    }

    @Disabled
    @Test
    void envTest() {
        var os = "Linux";
        var architecture = "amd64";
        var name = System.getProperty("os.name");
        var arch = System.getProperty("os.arch");
        System.out.println("os system" + name);
        System.out.println("architecture" + arch);
        assertEquals(os, name);
        assertEquals(architecture, arch);
    }
}
