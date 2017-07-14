package com.example.data.datasources.dtos

class ComicsResponse(code: Int,
                     status: String,
                     copyright: String,
                     attributionText: String,
                     attributionHTML: String,
                     etag: String,
                     data: ListResponse<ComicResponse>) :
        BaseResponse<ListResponse<ComicResponse>>(code,
                status, copyright, attributionText, attributionHTML, etag, data)
