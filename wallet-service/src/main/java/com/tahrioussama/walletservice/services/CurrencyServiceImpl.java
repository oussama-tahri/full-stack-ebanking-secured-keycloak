package com.tahrioussama.walletservice.services;

import com.tahrioussama.walletservice.entities.Continent;
import com.tahrioussama.walletservice.entities.Country;
import com.tahrioussama.walletservice.entities.Currency;
import com.tahrioussama.walletservice.repositories.ContinentRepository;
import com.tahrioussama.walletservice.repositories.CountryRepository;
import com.tahrioussama.walletservice.repositories.CurrencyRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@Transactional
public class CurrencyServiceImpl {
    private CountryRepository countryRepository;
    private ContinentRepository continentRepository;
    private CurrencyRepository currencyRepository;

    public CurrencyServiceImpl(CountryRepository countryRepository, ContinentRepository continentRepository, CurrencyRepository currencyRepository) {
        this.countryRepository = countryRepository;
        this.continentRepository = continentRepository;
        this.currencyRepository = currencyRepository;
    }
    public void loadContinentsAndCountries() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("csv/countries_continents.csv");
        List<String> lines = Files.readAllLines(Path.of(classPathResource.getURI()));
        for(int i=1;i<lines.size();i++){
            String[] line=lines.get(i).split(";");
            Country country=new Country();
            country.setCountryName(line[1].toUpperCase());
            country.setIsoCode(line[2]);
            country.setM49Code(Integer.parseInt(line[3]));
            String continentName=line[6];
            Continent continent=continentRepository.findByContinentName(continentName);
            if(continent==null){
                Continent newContinent=new Continent();
                newContinent.setContinentName(continentName);
                continent=continentRepository.save(newContinent);
            }
            country.setContinent(continent);
            countryRepository.save(country);
        }
    }
    public void loadCurrencies() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("csv/countries_currencies.csv");
        List<String> lines = Files.readAllLines(Path.of(classPathResource.getURI()));
        for(int i=1;i<lines.size();i++){
            String[] line=lines.get(i).split(",");
            System.out.println(line[0]);
            Country country=countryRepository.findByCountryName(line[0].toUpperCase());
            String currencyCode=line[2];
            Currency currency=currencyRepository.findByCode(currencyCode);
            if(currency==null){
                currency=new Currency();
                currency.setName(line[1]);
                currency.setCode(line[2]);
                currency = currencyRepository.save(currency);
            }
            country.setCurrency(currency);
            countryRepository.save(country);
        }
    }
    public void setCurrenciesPrices() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("csv/currencies.csv");
        List<String> lines = Files.readAllLines(Path.of(classPathResource.getURI()));
        for(int i=1;i<lines.size();i++){
            String[] line=lines.get(i).split(";");
            String countryIsoCode=line[0];
            Country country=countryRepository.findByIsoCode(countryIsoCode);
            if(country!=null){
                Currency currency=country.getCurrency();
                if(currency!=null){
                   currency.setSalePrice(Double.parseDouble(line[6]));
                    currency.setPurchasePrice(currency.getSalePrice()*(1.0242));
                    currencyRepository.save(currency);
                }
            }
        }
    }

}