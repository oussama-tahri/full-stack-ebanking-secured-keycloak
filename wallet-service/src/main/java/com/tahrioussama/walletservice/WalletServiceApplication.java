package com.tahrioussama.walletservice;

import com.tahrioussama.walletservice.entities.Currency;
import com.tahrioussama.walletservice.entities.Wallet;
import com.tahrioussama.walletservice.repositories.CurrencyRepository;
import com.tahrioussama.walletservice.repositories.WalletRepository;
import com.tahrioussama.walletservice.repositories.WalletTransactionRepository;
import com.tahrioussama.walletservice.services.CurrencyServiceImpl;
import com.tahrioussama.walletservice.services.WalletServiceImpl;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class WalletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletServiceApplication.class, args);
	}

	// To communicate with the other microservice we use RestTemplate
	/* @Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	} */

	// Now we have our microservices secured, se to have a communication between'm we need the access_token
	// Keycloak have the solution within this method
	// Before using this method we need to inject KeycloakSecurityComponents.class in our securityConfig class
	// Like this : @ComponentScan(basePackageClasses = KeycloakSecurityComponents.class)
	@Bean
	KeycloakRestTemplate keycloakRestTemplate(KeycloakClientRequestFactory keycloakClientRequestFactory) {
		return new KeycloakRestTemplate(keycloakClientRequestFactory);
	}


	@Bean
	CommandLineRunner start(CurrencyServiceImpl currencyService,
							WalletServiceImpl walletService,
							CurrencyRepository currencyRepository,
							WalletRepository walletRepository,
							WalletTransactionRepository walletTransactionRepository){
		return args -> {
			currencyService.loadContinentsAndCountries();
			currencyService.loadCurrencies();
			currencyService.setCurrenciesPrices();
			List<String> currencies= Arrays.asList("MAD","EUR","USD","CAD");
			currencies.forEach(cur->{
				Currency currency=currencyRepository.findByCode(cur);
				Wallet wallet= Wallet.builder()
						.id(UUID.randomUUID().toString())
						.currency(currency)
						.balance(1000)
						.createdAt(System.currentTimeMillis())
						.userId("user1")
						.build();
				wallet=walletRepository.save(wallet);
			});
			var walletList = walletRepository.findAll();
			for (int i = 0; i < walletList.size()-1; i++) {
				var wal1=walletList.get(i);
				var wal2=walletList.get(i+1);
				//walletService.walletTransfer(wal1.getId(), wal2.getId(),100);
			}
			walletRepository.findAll().forEach(wallet -> {
				System.out.println("*********************");
				System.out.println(wallet.getId());
				System.out.println(wallet.getBalance());
				System.out.println("*******************");
			});
		};
	}

}
