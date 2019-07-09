package com.training.auth.dto;

import com.training.auth.util.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse<O> {

    private Status success;

    private O data;
}
