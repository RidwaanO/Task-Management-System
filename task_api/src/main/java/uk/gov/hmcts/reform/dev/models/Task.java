package uk.gov.hmcts.reform.dev.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonProperty;



@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false)
    private UUID id;


    @NotBlank(message = "Title cannot be blank")
    @Size(max = 255, message = "Title must be not be more than 255 characters")
    @Column(nullable = false)
    private String title;

    @Size(max = 2000, message = "Description must be ≤ 2000 characters")
    @Column(length = 2000)
    private String description;

    @NotNull(message = "Status cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status;

    @NotNull(message = "Due date/time cannot be null")
    @Future(message = "Due date must be in the future")
    @Column(nullable = false)
    private LocalDateTime dueDateTime;


    public enum Status {
        OPEN,
        IN_PROGRESS,
        COMPLETED,
        CANCELLED
    }
}
