package com.tahrioussama.walletservice.web;

import com.tahrioussama.walletservice.entities.*;
import com.tahrioussama.walletservice.repositories.*;
import com.tahrioussama.walletservice.services.WalletServiceImpl;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
public class CurrencyGraphQLController {
  private CurrencyRepository currencyRepository;
  private CountryRepository countryRepository;
  private ContinentRepository continentRepository;
  private WalletServiceImpl walletService;
  private WalletRepository walletRepository;
  private WalletTransactionRepository walletTransactionRepository;


    public CurrencyGraphQLController(CurrencyRepository currencyRepository, CountryRepository countryRepository, ContinentRepository continentRepository, WalletServiceImpl walletService, WalletRepository walletRepository, WalletTransactionRepository walletTransactionRepository) {
        this.currencyRepository = currencyRepository;
        this.countryRepository = countryRepository;
        this.continentRepository = continentRepository;
        this.walletService = walletService;
        this.walletRepository = walletRepository;
        this.walletTransactionRepository = walletTransactionRepository;
    }
    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<Currency> currencies(){
        return  currencyRepository.findAll();
    }
    @QueryMapping
    public List<Currency> currenciesByNameContains(@Argument String name){
        return  currencyRepository.findByNameContains(name);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<Country> countries(){

        return  countryRepository.findAll();
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<Country> countriesByContinent(@Argument Long id){
        return  countryRepository.findByContinentId(id);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<Continent> continents(){
        return  continentRepository.findAll();
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public Continent continentById(@Argument String continentName){
        Continent continent=continentRepository.findByContinentName(continentName);
        return continent;
    }


    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public Country countryByIsoCode(@Argument String isoCode){
        return countryRepository.findByIsoCode(isoCode);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public Currency currencyByCode(@Argument String code){
        return currencyRepository.findByCode(code);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Currency updateCurrencyPrice(@Argument CurrencyPriceRequest currencyPriceRequest){
        Currency currency=currencyRepository.findByCode(currencyPriceRequest.code());
        currency.setSalePrice(currencyPriceRequest.salePrice());
        currency.setPurchasePrice(currency.getSalePrice()*1.0242);
        return currencyRepository.save(currency);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public Wallet addWallet(@Argument String currencyCode, @Argument double initialBalance, Principal principal){
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken token = authenticationToken.getAccount().getKeycloakSecurityContext().getToken();
        String username = token.getPreferredUsername();
        return walletService.newWallet(currencyCode,initialBalance,username);
    }

    @MutationMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Wallet> walletTransfer(@Argument String sourceWalletId,@Argument String destinationWalletId, @Argument double amount) throws Exception {
         return walletService.walletTransfer(sourceWalletId,destinationWalletId, amount);
    }
    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<Wallet> userWallets(Principal principal){
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) principal;
        AccessToken token = authenticationToken.getAccount().getKeycloakSecurityContext().getToken();
        String username = token.getPreferredUsername();
        return walletRepository.findByUserId(username);
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public Wallet walletById(@Argument String id){
        return walletRepository.findById(id).orElseThrow();
    }

    @QueryMapping
    @PreAuthorize("hasAuthority('USER')")
    public List<WalletTransaction> walletTransactions(@Argument String walletId){
        return walletTransactionRepository.findByWalletId(walletId);
    }

}
record CurrencyPriceRequest(String code, double salePrice){}
