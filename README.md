Projekt aplikacji konsolidującej kredyty z wykorzystaniem komunikacji SOAP.

Aplikacja udostępnia 3 metody:

* `getUserLoans` - przyjmuje id użytkownika, a następnie zwraca posiadane przez niego kredyty.

* `calculateOffer` - przyjmuje subset kredytów zwróconych z `getUserLoans` dla których ma być wyliczona oferta, a następnie wylicza najlepszą ofertę.
* `acceptOffer` - pozwala użytkownikowi zaakceptować warunki umowy. Przyjmuje umowę zwróconą z `calculateOffer`. Zwraca czy operacja się powiodła.


Przykładowe wejścia/wyjścia:

* `getUserLoans`

IN:
```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:demo="demo.example.com">
   <soapenv:Header/>
   <soapenv:Body>
      <demo:getUserLoansRequest>
         <demo:userId>1</demo:userId>
      </demo:getUserLoansRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

OUT:
```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:getUserLoansResponse xmlns:ns2="demo.example.com">
         <ns2:loans>
            <ns2:accountNumber>70200170060344035954117333</ns2:accountNumber>
            <ns2:remainingAmount>32039.24</ns2:remainingAmount>
            <ns2:currency>PLN</ns2:currency>
         </ns2:loans>
         <ns2:loans>
            <ns2:accountNumber>55728599511057946736133360</ns2:accountNumber>
            <ns2:remainingAmount>12790.45</ns2:remainingAmount>
            <ns2:currency>PLN</ns2:currency>
         </ns2:loans>
         <ns2:loans>
            <ns2:accountNumber>13924469100346563946900063</ns2:accountNumber>
            <ns2:remainingAmount>34304.73</ns2:remainingAmount>
            <ns2:currency>PLN</ns2:currency>
         </ns2:loans>
      </ns2:getUserLoansResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```



* `calculateOffer`

IN:

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:demo="demo.example.com">
   <soapenv:Header/>
   <soapenv:Body>
      <demo:calculateOfferRequest>
        <demo:selectedLoans>
            <demo:accountNumber>70200170060344035954117333</demo:accountNumber>
            <demo:remainingAmount>32039.24</demo:remainingAmount>
            <demo:currency>PLN</demo:currency>
         </demo:selectedLoans>
         <demo:selectedLoans>
            <demo:accountNumber>55728599511057946736133360</demo:accountNumber>
            <demo:remainingAmount>12790.45</demo:remainingAmount>
            <demo:currency>PLN</demo:currency>
         </demo:selectedLoans>
         <demo:selectedLoans>
            <demo:accountNumber>13924469100346563946900063</demo:accountNumber>
            <demo:remainingAmount>34304.73</demo:remainingAmount>
            <demo:currency>PLN</demo:currency>
         </demo:selectedLoans>
      </demo:calculateOfferRequest>
   </soapenv:Body>
</soapenv:Envelope>
```

OUT:

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:calculateOfferResponse xmlns:ns2="demo.example.com">
         <ns2:offer>
            <ns2:loanAmount>79134.42</ns2:loanAmount>
            <ns2:numberOfPayments>18</ns2:numberOfPayments>
            <ns2:monthlyAmount>4396.36</ns2:monthlyAmount>
         </ns2:offer>
      </ns2:calculateOfferResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
* `acceptOffer`

IN:

```
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:demo="demo.example.com">
   <soapenv:Header/>
   <soapenv:Body>
      <demo:acceptOfferRequest>
         <demo:offer>
            <demo:loanAmount>79134.42</demo:loanAmount>
            <demo:numberOfPayments>18</demo:numberOfPayments>
            <demo:monthlyAmount>4396.36</demo:monthlyAmount>
         </demo:offer>
      </demo:acceptOfferRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
OUT:

```
<SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
   <SOAP-ENV:Header/>
   <SOAP-ENV:Body>
      <ns2:acceptOfferResponse xmlns:ns2="demo.example.com">
         <ns2:accepted>true</ns2:accepted>
      </ns2:acceptOfferResponse>
   </SOAP-ENV:Body>
</SOAP-ENV:Envelope>
```
