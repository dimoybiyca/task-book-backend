package com.lemoncat.tbtasksrservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_title")
    private String taskTitle;

    @CreatedDate
    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "deadline_date")
    private LocalDateTime deadlineDate;

    @Column(name = "completed")
    private LocalDateTime completed;

    @ManyToOne
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
