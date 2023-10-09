package com.lemoncat.tbstatisticrservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticNumbers {
        private int created;
        private int completed;
        private int deleted;
}
