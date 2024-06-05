package com.hrs.hotelbooking.application;

import java.util.List;
import javax.swing.text.AbstractDocument.Content;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
public class Slice<ContentType> {
    private int page;
    private boolean isLast;
    private List<ContentType> content;
}
