package com.exam.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Message {
    private Integer id;
    private Integer temp_id;

    private String title;

    private String content;

  //  @JsonFormat(pattern = "yyyy-MM-dd", timezone="GMT+8")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone="EST")
    private Date time;

    List<Replay> replays;
}