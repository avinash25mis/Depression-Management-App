package com.dto;

import com.model.DayWiseContent;
import lombok.Data;

import java.util.List;

@Data
public class SelectiveDaysData {

    private List<DayWiseContent> data;
    private boolean hasAskEmail;
    private boolean hasAskFeedBack;
}
