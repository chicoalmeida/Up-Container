package io.chico;

import org.testcontainers.containers.FixedHostPortGenericContainer;
import org.testcontainers.containers.GenericContainer;

/**
 * Created by Francisco Almeida.
 */
public class CouchBaseContainerRunner implements AutoCloseable{

    private static final String COUCHBASE_LATEST = "couchbase:latest";
    private static final String COUCHBASE_LATEST_LABEL = "couchbase:default";

    @Override
    public void close() throws Exception {
        couchbaseContainer.stop();
    }

    public enum Status {SUCCESS, FAILURE}


    private static GenericContainer couchbaseContainer = new FixedHostPortGenericContainer(COUCHBASE_LATEST)
            .withFixedExposedPort(8091, 8091)
            .withFixedExposedPort(8092, 8092)
            .withFixedExposedPort(8093, 8093)
            .withFixedExposedPort(8094, 8094)
            .withFixedExposedPort(11210, 11210);

    public static Status startUpDefault() {
        couchbaseContainer.setContainerId(COUCHBASE_LATEST_LABEL);

        couchbaseContainer.start();
        return couchbaseContainer.isRunning() ? Status.SUCCESS : Status.FAILURE;
    }

}
