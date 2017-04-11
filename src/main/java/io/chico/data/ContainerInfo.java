package io.chico.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContainerInfo {

    private String address;
    /**
     * Return the mapped pair of ports
     * {CONTAINER_PORT}:{MAPPED_PORT}
     * like: 27017:27017
     */
    private List<String> mappedPorts;
    private boolean running;

    public List<Integer> getExposedPorts() {
        return mappedPorts
                .stream()
                .map(pair -> Integer.valueOf(pair.split(":")[1]))
                .collect(toList());
    }
}
