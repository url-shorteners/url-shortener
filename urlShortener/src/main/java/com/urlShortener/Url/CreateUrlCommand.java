package com.urlShortener.Url;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUrlCommand {
    private String originalUrl;
    private String shortenUrl;
}
