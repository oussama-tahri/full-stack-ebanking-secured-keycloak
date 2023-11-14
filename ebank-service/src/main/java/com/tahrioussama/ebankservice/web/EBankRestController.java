package com.tahrioussama.ebankservice.web;

import com.tahrioussama.ebankservice.dto.CurrencyTransferResponse;
import com.tahrioussama.ebankservice.dto.NewWalletTransferRequest;
import com.tahrioussama.ebankservice.entities.CurrencyDeposit;
import com.tahrioussama.ebankservice.service.EBankServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class EBankRestController {

    private EBankServiceImpl eBankService;

    @PostMapping("/currencyTransfer")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CurrencyTransferResponse currencyTransfer(@RequestBody NewWalletTransferRequest request){
        return this.eBankService.newWalletTransaction(request);
    }
    @GetMapping("/currencyDeposits")
    @PreAuthorize("hasAuthority('USER')")
    public List<CurrencyDeposit> currencyDepositList(){
        return eBankService.currencyDeposits();
    }
}
