package com.example.data.dtos

class ComicsResponse(code: Int,
                     status: String,
                     copyright: String,
                     attributionText: String,
                     attributionHTML: String,
                     etag: String,
                     data: ListResponse<ComicsResponse>) :
        BaseResponse<ListResponse<ComicsResponse>>(code,
                status, copyright, attributionText, attributionHTML, etag, data)
