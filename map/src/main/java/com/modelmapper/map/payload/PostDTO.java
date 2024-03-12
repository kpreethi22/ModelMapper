package com.modelmapper.map.payload;

import lombok.Data;


import java.util.Set;

    @Data
    public class PostDTO {
        private String id;

        private String title;

        private String description;


        private String content;
}
