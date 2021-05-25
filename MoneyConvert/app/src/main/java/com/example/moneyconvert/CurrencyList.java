package com.example.moneyconvert;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.R)
public class CurrencyList {

    public static ArrayList<Currency> listCurrency = new ArrayList<>(
            List.of(
                    new Currency("AUD","Australian Dollar"),
                    new Currency("DZD","Algerian Dinar"),
                    new Currency("GBP","British Pound"),
                    new Currency("BHD","Bahraini Dinar"),
                    new Currency("BBD","Barbados Dollar"),
                    new Currency("BTN","Bhutan Ngultrum"),
                    new Currency("BWP","Botswana Pula"),
                    new Currency("BND","Brunei Dollar"),
                    new Currency("BIF","Burundi Franc"),
                    new Currency("CNY","Chinese Yuan"),
                    new Currency("CVE","Cape Verde Escudo"),
                    new Currency("XAF","CFA Franc (BEAC)"),
                    new Currency("COP","Colombian Peso"),
                    new Currency("CRC","Costa Rica Colon"),
                    new Currency("CUP","Cuban Peso"),
                    new Currency("EUR","Euro"),
                    new Currency("DJF","Dijbouti Franc"),
                    new Currency("XCD","East Caribbean Dollar"),
                    new Currency("ETB","Ethiopian Birr"),
                    new Currency("HKD","Hong Kong Dollar"),
                    new Currency("INR","Indian Rupee"),
                    new Currency("GTQ","Guatemala Quetzal"),
                    new Currency("GYD","Guyana Dollar"),
                    new Currency("HNL","Honduras Lempira"),
                    new Currency("ISK","Iceland krona"),
                    new Currency("IQD","Iraqi Dinar"),
                    new Currency("JPY","Japanese Yen"),
                    new Currency("JOD","Jordanian Dinar"),
                    new Currency("KES","Kenyan Shilling"),
                    new Currency("KWD","Kuwaiti Dinar"),
                    new Currency("LBP","Lebanese Pound"),
                    new Currency("LRD","Liberian Dollar"),
                    new Currency("MOP","Macau Pataca"),
                    new Currency("MWK","Malawi kwacha"),
                    new Currency("MYR","Maldies Rufiyaa"),
                    new Currency("MUR","Mauritius Rupee"),
                    new Currency("MDL","Moldovan Leu"),
                    new Currency("MAD","Moroccan Dirham"),
                    new Currency("NAD","Namibian Dollar"),
                    new Currency("NZD","New Zealand Dollar"),
                    new Currency("NGN","Nigerian Naira"),
                    new Currency("NOK","Norwegian Krone"),
                    new Currency("PKR","Pakistani Rupee"),
                    new Currency("PYG","Paraguayan Guarani"),
                    new Currency("PHP","Philippine Peso"),
                    new Currency("QAR","Quatar Rial"),
                    new Currency("RUB","Russian Rouble"),
                    new Currency("CHF","Swiss Franc"),
                    new Currency("SAR","Saudi Arabian Riyal"),
                    new Currency("SLL","Sierra Leone Leone"),
                    new Currency("SBD","Solomon Islands Dollar"),
                    new Currency("ZAR","South Africa Rand"),
                    new Currency("SDG","Sudanese Pound"),
                    new Currency("SEK","Swedish Krona"),
                    new Currency("USD","United State Dollar"),
                    new Currency("TRY","Turish Lira"),
                    new Currency("TZS","Tanzanian Shiling"),
                    new Currency("TTD","Trinidad Tobago Dollar"),
                    new Currency("AED","UAE Dirham"),
                    new Currency("UAH","Ukrane hryvnia"),
                    new Currency("VUV","Vanuatu Vatu"),
                    new Currency("YER","Yemen Riyal"),
                    new Currency("KGS","Kyrgyzstan Som"),
                    new Currency("BYN","Belarusian ruble"),
                    new Currency("AOA","Angolan kwanza"),
                    new Currency("AZN","Azerbaijani manat"),
                    new Currency("CDF","Congolese Franc"),
                    new Currency("GEL","Georgian lari"),
                    new Currency("MZN","Mozambican metical"),
                    new Currency("SRD","Surinamese dollar"),
                    new Currency("TMT","Turkmenistan manat"),
                    new Currency("ALL","Albanian Lek"),
                    new Currency("ARS","Argentine Peso"),
                    new Currency("BSD","Bahamian Dollar"),
                    new Currency("BDT","Bangladesh Taka"),
                    new Currency("BZD","Belize Dollar"),
                    new Currency("BOB","Bolivian Boliviano"),
                    new Currency("BRL","Brazilian Real"),
                    new Currency("BGN","Bulgarian Lev"),
                    new Currency("CAD","Cadian Dollar"),
                    new Currency("KHR","Cambodia Riel"),
                    new Currency("XOF","CFA Franc (BCEAO)"),
                    new Currency("CLP","Chilean Peso"),
                    new Currency("KMF","Comoros Franc"),
                    new Currency("HRK","Croatia Kuna"),
                    new Currency("CZK","Czech Koruna"),
                    new Currency("DKK","Danish Krone"),
                    new Currency("DOP","Dominican Peso"),
                    new Currency("EGP","Egyptian Pound"),
                    new Currency("FJD","Fiji Dollar"),
                    new Currency("IDR","Indonesian Rupial"),
                    new Currency("GMD","Gambian Dalasi"),
                    new Currency("GNF","Guinea Franc"),
                    new Currency("HTG","Haiti Gourde"),
                    new Currency("IRR","Iran rial"),
                    new Currency("ILS","Israeli Shekel"),
                    new Currency("JMD","Jamaican Dollar"),
                    new Currency("KZT","Kazakhstan Tenge"),
                    new Currency("KRW","Korean Won"),
                    new Currency("LAK","Lao Kip"),
                    new Currency("LSL","Lesotho Loti"),
                    new Currency("LYD","Libyan Dinar"),
                    new Currency("MKD","Macedonian Denar"),
                    new Currency("MYR","Malaysian Ringgit"),
                    new Currency("MRO","Mauritania Ougulya"),
                    new Currency("MXN","Mexician peso"),
                    new Currency("MNT","Mongolian Tugrik"),
                    new Currency("NPR","Nepalese Rupee"),
                    new Currency("NIO","Nicaragua Cordoba"),
                    new Currency("KPW","North Korean Won"),
                    new Currency("OMR","Omani Rial"),
                    new Currency("PGK","Australian Dollar"),
                    new Currency("PEN","Peruvian Nuevo Sol"),
                    new Currency("PLN","Polish Zloty"),
                    new Currency("RON","Romanian New Leu"),
                    new Currency("RWF","Rwanda Franc"),
                    new Currency("WST","Samoa Tala"),
                    new Currency("SCR","Seychelles Rupee"),
                    new Currency("SGD","Singapore Dollar"),
                    new Currency("SOS","Somali Shilling "),
                    new Currency("LKR","Sri Lanka Rupee"),
                    new Currency("SZL","Swaziland Lilageni"),
                    new Currency("SYP","Syrian Pound"),
                    new Currency("THB","Thai baht"),
                    new Currency("TWD","Taiwan Dollar"),
                    new Currency("TOP","Tongan pa'anga"),
                    new Currency("TND","Tunisian Dinar"),
                    new Currency("UGX","Ugandan shilling"),
                    new Currency("UYU","Uruguayan New Peso"),
                    new Currency("VND","Vietnam Dong"),
                    new Currency("UZS","Uzbekistan Sum"),
                    new Currency("GHS","Ghanaian Cedi"),
                    new Currency("AFN","Afgan afghani"),
                    new Currency("AMD","Armenian dram"),
                    new Currency("BAM","Convertiable mark"),
                    new Currency("ERN","Eritrean nakfa"),
                    new Currency("MGA","Malagasy ariary"),
                    new Currency("RSD","Serbian dinar"),
                    new Currency("TJS","Taijkistani somoni"),
                    new Currency("ZMW","Zambian kwacha")
            )
    );
}

