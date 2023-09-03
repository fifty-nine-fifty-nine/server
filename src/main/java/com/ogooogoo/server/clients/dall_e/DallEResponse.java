package com.ogooogoo.server.clients.dall_e;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter @Setter
@NoArgsConstructor
public class DallEResponse {
    private Long created;
    private List<Map<String, String>> data;

    public DallEResponse(Long created, List<Map<String, String>> data) {
        this.created = created;
        this.data = data;
    }

    public Optional<String> getUrl(){
        if (data == null || data.isEmpty())
            return Optional.empty();

        Map<String, String> firstEntry = data.get(0);
        return Optional.of(firstEntry.get("url"));
    }

    @Override
    public String toString() {
        return "DallEResponse{" +
                "created=" + created +
                ", data=" + data +
                '}';
    }
}
