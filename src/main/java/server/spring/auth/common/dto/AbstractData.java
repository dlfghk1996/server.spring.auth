package server.spring.auth.common.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class AbstractData {
    private Long id;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    private Boolean disabled;
}
