package com.groupt.reader.model;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long msgId;
}
