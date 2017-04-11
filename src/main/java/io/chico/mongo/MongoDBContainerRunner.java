package io.chico.mongo;

import io.chico.data.ContainerInfo;
import org.springframework.util.Assert;
import org.testcontainers.containers.FixedHostPortGenericContainer;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

public class MongoDBContainerRunner {

    private static final String LATEST_CONTAINER = "mongo:latest";

    public ContainerInfo startUpDefault() {
        FixedHostPortGenericContainer container = new MongoContainerBuilder()
                .setVersion(LATEST_CONTAINER)
                .setContainerPorts(singletonList(new ContainerBinder(27017, 27017)))
                .build();
        container.start();
        return createContainerInfo(container);
    }


    private ContainerInfo createContainerInfo(final FixedHostPortGenericContainer container) {
        System.out.println(container.getContainerInfo());
        return ContainerInfo
                .builder()
                .address(container.getContainerIpAddress())
                .mappedPorts(container.getPortBindings())
                .running(container.isRunning())
                .build();
    }

    public ContainerInfo startUPWithVersionAndPort(final String mongoVersion, final List<ContainerBinder> binders) {
        final String containerVersion = "mongo:" + mongoVersion;
        FixedHostPortGenericContainer container = new MongoContainerBuilder()
                .setVersion(containerVersion)
                .setContainerPorts(binders)
                .build();
        container.start();
        return createContainerInfo(container);
    }


    public static class MongoContainerBuilder {

        private String version;
        private Optional<List<ContainerBinder>> binders;

        public MongoContainerBuilder setVersion(final String version) {
            this.version = version;
            return this;
        }

        public MongoContainerBuilder setContainerPorts(final List<ContainerBinder> binders) {
            this.binders = Optional.of(binders);
            return this;

        }

        public FixedHostPortGenericContainer build() {
            Assert.notNull(version, "You need to specify a container version");

            FixedHostPortGenericContainer container = new FixedHostPortGenericContainer(this.version);
            binders.ifPresent(binderList -> binderList.forEach(binder -> container.withFixedExposedPort(binder.getFrom(), binder.getTo())));
            return container;
        }


    }
}
