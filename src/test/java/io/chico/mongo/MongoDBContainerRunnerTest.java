package io.chico.mongo;

import io.chico.data.ContainerInfo;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MongoDBContainerRunnerTest {

    private MongoDBContainerRunner target = new MongoDBContainerRunner();

    @Test
    public void startUpDefault_WhenInvoked_ShouldStartupMongoContainer() throws Exception {
        ContainerInfo result = target.startUpDefault();
        assertThat(result.isRunning()).isTrue();
    }

    @Test
    public void startUpWithCustomVersionAndPort_WhenInvoked_ShouldStartUpTheContainerWithGivenPortAndVersion() throws Exception {
        List<ContainerBinder> binder = Collections.singletonList(new ContainerBinder(27017, 27017));
        ContainerInfo result = target.startUPWithVersionAndPort("3.5", binder);
        assertThat(result.isRunning()).isTrue();
        assertThat(result.getExposedPorts()).contains(binder.get(0).getTo());
    }
}