package com.tahrioussama.ebankservice.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class CurrencyTransferResponse {
    private String sourceTransactionId;
    private String destinationTransactionId;
}
