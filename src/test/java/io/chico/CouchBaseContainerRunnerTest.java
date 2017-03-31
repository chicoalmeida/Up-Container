package io.chico;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Francisco Almeida.
 */
public class CouchBaseContainerRunnerTest {

    @Test
    public void bringUp_ShouldStartupAValidContainer() throws Exception {

        CouchBaseContainerRunner.Status status = CouchBaseContainerRunner.startUpDefault();

        assertThat(status).isEqualByComparingTo(CouchBaseContainerRunner.Status.SUCCESS);

    }
}