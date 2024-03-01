package com.Antra.RestRewards.domain.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public final class Points {
    private final long userId;

    private final int points;
}
