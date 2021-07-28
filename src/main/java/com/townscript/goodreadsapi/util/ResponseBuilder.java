package com.townscript.goodreadsapi.util;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponseBuilder {

    public ApiResponse createSuccessResponse(Object data){
      ApiResponse apiResponse = new ApiResponse("success","success message",data);
      return apiResponse;//return
    }

    public ApiResponse createErrorResponse(String message){
        ApiResponse apiResponse = new ApiResponse("error",message,null);
        return apiResponse;//return
    }

}
