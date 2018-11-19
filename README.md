<h1 align="center">
<img src="https://raw.githubusercontent.com/rolandopalermo/Veronica/master/static/veronica.jpg" alt="Markdownify" width="420">
<p align="center">
  <a href="https://www.paypal.me/rolandopalermo" target="_blank"><img alt="PayPal Donate" src="http://ionicabizau.github.io/badges/paypal.svg"></a>
  <a href="blog.rolandopalermo.com" target="_blank"><img alt="PayPal Donate" src="https://img.shields.io/badge/plaform-windows%20%7C%20linux%20%7C%20macOS-blue.svg"></a>
  <a href="blog.rolandopalermo.com" target="_blank"><img alt="PayPal Donate" src="https://img.shields.io/badge/version-1.0.0-green.svg"></a>
</p>
<h4 align="center">E-Invoicing Rest API for the integration with "Servicio de Rentas Internas" Web services.</h4>
</h1>

<!-- TOC depthFrom:1 depthTo:2 withLinks:1 updateOnSave:1 orderedList:0 -->
Table of contents
=================
- [Veronica REST API](#veronica-rest-api)
	- [Preamble](#preamble)
	- [Startup Settings](#startup-settings)
	- [Deployment](#deployment)
	- [Documentation](#documentation)
	- [Postman API Reference](#postman-api-reference)
	- [Documentation history](#documentation-history)
	- [Request & Response Examples](#request--response-examples)
	- [Authors](#authors)

<!-- /TOC -->
## Preamble
`Veronica REST API` is a set of RESTful web services that provide an abstraction layer which allows for easy issue of electronic invoicing, according with the Ecuadorian regulations imposed by the "Servicio de Rentas Internas".

## Startup Settings
If you want to make modifications to `Veronica`, you must configure your Maven repository appropriately, making sure to use the following instructions:
1. You first need to go to the `Veronica`’s directory and after that, you have to move to additional_libs directory. For Linux, Windows or Mac use the command:
```bash
$ cd additional_libs
```
2. As second step, you must install all the JAR files from additional_libs to the local Maven repository using the following commands:
```bash
mvn install:install-file -Dfile=jss-4.2.5.jar -DgroupId=org.mozilla -DartifactId=jss -Dversion=4.2.5 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibAPI-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=api -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibCert-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=cert -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibOCSP-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=ocsp  -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibPolicy-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=policy -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibTrust-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=trust -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibTSA-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=tsa -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibXADES-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=xades -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=xmlsec-1.4.2-ADSI-1.0.jar -DgroupId=org.apache.xmlsec-adsi -DartifactId=xmlsec-adsi -Dversion=1.4.2 -Dpackaging=jar
```
3.- This project provides two maven profiles. Using the next command, you will  be able the choose the correct profile according to your environment (DEV or PRD). 
```bash
mvn clean install -P development
```

```bash
mvn clean install -P production
```
Change the content with appropriate values, according with your configuration.

## Deployment
```bash
$ cd veronica-web
$ mvn spring-boot:run
```

## Documentation
http://localhost:8080/veronica/swagger-ui.html

## Postman API Reference
https://documenter.getpostman.com/view/1388083/RzZCDHct

## Request & Response Examples
### API Resources
	- [POST /api/v1/generar/factura](#post-generar-factura)
	- [POST /api/v1/generar/retencion](#post-generar-retencion)
	- [POST /api/v1/firmar/factura](#post-firmar-factura)
	- [POST /api/v1/firmar/factura](#post-firmar-factura-content)
	- [POST /api/v1/sri/enviar/factura](#post-enviar-factura)
	- [POST /api/v1/sri/autorizar](#post-autorizar-factura)

### POST /api/v1/generar/factura
Example: http://localhost:8080/veronica/api/v1/generar/factura
Request body:
{
   "id":"comprobante",
   "version":"1.0.0",
   "infoTributaria":{
      "ambiente":"1",
      "tipoEmision":"1",
      "razonSocial":"Distribuidora de Suministros Nacional S.A.",
      "nombreComercial":"Empresa Importadora y Exportadora de Piezas",
      "ruc":"1792146739001",
      "codDoc":"01",
      "estab":"002",
      "ptoEmi":"001",
      "secuencial":"000000001",
      "dirMatriz":"Enrique Guerrero Portilla OE1-34 AV. Galo Plaza Lasso"
   },
   "infoFactura":{
      "fechaEmision":"21/10/2012",
      "dirEstablecimiento":"Sebastián Moreno S/N Francisco García",
      "contribuyenteEspecial":"5368",
      "obligadoContabilidad":"SI",
      "tipoIdentificacionComprador":"04",
      "guiaRemision":"001-001-000000001",
      "razonSocialComprador":"PRUEBAS SERVICIO DE RENTAS INTERNAS",
      "identificacionComprador":"1713328506001",
      "direccionComprador":"salinas y santiago",
      "totalSinImpuestos":295000.00,
      "totalDescuento":5005.00,
      "totalImpuesto":[
         {
         	"codigo":"3",
         	"codigoPorcentaje":"3072",
            "baseImponible":295000.00,
            "valor":14750.00
         },
         {
            "codigo":"2",
            "codigoPorcentaje":"2",
            "descuentoAdicional":5.00,
            "baseImponible":309750.00,
            "valor":37169.40
         },
         {
            "codigo":"5",
            "codigoPorcentaje":"5001",
            "baseImponible":12000.00,
            "valor":240.00
         }
      ],
      "propina":0,
      "importeTotal":347159.40,
      "moneda":"DOLAR",
      "pagos":[
         {
            "formaPago":"01",
            "total":347159.40,
            "plazo":"30",
            "unidadTiempo":"dias"
         }
      ],
      "valorRetIva":10620.00,
      "valorRetRenta":2950.00
   },
   "detalle":[
      {
         "codigoPrincipal":"125BJC-01",
         "codigoAuxiliar":"1234D56789-A",
         "descripcion":"CAMIONETA 4X4 DIESEL 3.7",
         "cantidad":10.00,
         "precioUnitario":300000.00,
         "descuento":5000.00,
         "precioTotalSinImpuesto":295000.00,
         "detAdicional":[
            {
               "nombre":"Marca Chevrolet",
               "valor":"Chevrolet"
            },
            {
               "nombre":"Modelo",
               "valor":"2012"
            },
            {
               "nombre":"Chasis",
               "valor":"8LDETA03V20003289"
            }
         ],
         "impuesto":[
            {
               "codigo":"3",
               "codigoPorcentaje":"3072",
               "tarifa":5,
               "baseImponible":295000.00,
               "valor":14750.00
            },
            {
               "codigo":"2",
               "codigoPorcentaje":"2",
               "tarifa":12,
               "baseImponible":309750.00,
               "valor":37170.00
            },
            {
               "codigo":"5",
               "codigoPorcentaje":"5001",
               "tarifa":0.02,
               "baseImponible":12000.00,
               "valor":240.00
            }
         ]
      }
   ],
   "campoAdicional":[
      {
         "nombre":"Codigo Impuesto ISD",
         "value":"4580"
      },
      {
         "nombre":"Impuesto ISD",
         "value":"15.42x"
      }
   ]
}
Response body:
{
    "content": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8ZmFjdHVyYSBpZD0iY29tcHJvYmFudGUiIHZlcnNpb249IjEuMC4wIj4KICAgIDxpbmZvVHJpYnV0YXJpYT4KICAgICAgICA8YW1iaWVudGU+MTwvYW1iaWVudGU+CiAgICAgICAgPHRpcG9FbWlzaW9uPjE8L3RpcG9FbWlzaW9uPgogICAgICAgIDxyYXpvblNvY2lhbD5EaXN0cmlidWlkb3JhIGRlIFN1bWluaXN0cm9zIE5hY2lvbmFsIFMuQS48L3Jhem9uU29jaWFsPgogICAgICAgIDxub21icmVDb21lcmNpYWw+RW1wcmVzYSBJbXBvcnRhZG9yYSB5IEV4cG9ydGFkb3JhIGRlIFBpZXphczwvbm9tYnJlQ29tZXJjaWFsPgogICAgICAgIDxydWM+MTc5MjE0NjczOTAwMTwvcnVjPgogICAgICAgIDxjbGF2ZUFjY2Vzbz4yMTEwMjAxMjAxMTc5MjE0NjczOTAwMTEwMDEwMDIwMDAwMDAwMDEwMTgyODc0MDEyPC9jbGF2ZUFjY2Vzbz4KICAgICAgICA8Y29kRG9jPjAxPC9jb2REb2M+CiAgICAgICAgPGVzdGFiPjAwMjwvZXN0YWI+CiAgICAgICAgPHB0b0VtaT4wMDE8L3B0b0VtaT4KICAgICAgICA8c2VjdWVuY2lhbD4wMDAwMDAwMDE8L3NlY3VlbmNpYWw+CiAgICAgICAgPGRpck1hdHJpej5FbnJpcXVlIEd1ZXJyZXJvIFBvcnRpbGxhIE9FMS0zNCBBVi4gR2FsbyBQbGF6YSBMYXNzbzwvZGlyTWF0cml6PgogICAgPC9pbmZvVHJpYnV0YXJpYT4KICAgIDxpbmZvRmFjdHVyYT4KICAgICAgICA8ZmVjaGFFbWlzaW9uPjIxLzEwLzIwMTI8L2ZlY2hhRW1pc2lvbj4KICAgICAgICA8ZGlyRXN0YWJsZWNpbWllbnRvPlNlYmFzdGnDoW4gTW9yZW5vIFMvTiBGcmFuY2lzY28gR2FyY8OtYTwvZGlyRXN0YWJsZWNpbWllbnRvPgogICAgICAgIDxjb250cmlidXllbnRlRXNwZWNpYWw+NTM2ODwvY29udHJpYnV5ZW50ZUVzcGVjaWFsPgogICAgICAgIDxvYmxpZ2Fkb0NvbnRhYmlsaWRhZD5TSTwvb2JsaWdhZG9Db250YWJpbGlkYWQ+CiAgICAgICAgPHRpcG9JZGVudGlmaWNhY2lvbkNvbXByYWRvcj4wNDwvdGlwb0lkZW50aWZpY2FjaW9uQ29tcHJhZG9yPgogICAgICAgIDxndWlhUmVtaXNpb24+MDAxLTAwMS0wMDAwMDAwMDE8L2d1aWFSZW1pc2lvbj4KICAgICAgICA8cmF6b25Tb2NpYWxDb21wcmFkb3I+UFJVRUJBUyBTRVJWSUNJTyBERSBSRU5UQVMgSU5URVJOQVM8L3Jhem9uU29jaWFsQ29tcHJhZG9yPgogICAgICAgIDxpZGVudGlmaWNhY2lvbkNvbXByYWRvcj4xNzEzMzI4NTA2MDAxPC9pZGVudGlmaWNhY2lvbkNvbXByYWRvcj4KICAgICAgICA8ZGlyZWNjaW9uQ29tcHJhZG9yPnNhbGluYXMgeSBzYW50aWFnbzwvZGlyZWNjaW9uQ29tcHJhZG9yPgogICAgICAgIDx0b3RhbFNpbkltcHVlc3Rvcz4yOTUwMDAuMDA8L3RvdGFsU2luSW1wdWVzdG9zPgogICAgICAgIDx0b3RhbERlc2N1ZW50bz41MDA1LjAwPC90b3RhbERlc2N1ZW50bz4KICAgICAgICA8dG90YWxDb25JbXB1ZXN0b3M+CiAgICAgICAgICAgIDx0b3RhbEltcHVlc3RvPgogICAgICAgICAgICAgICAgPGNvZGlnbz4zPC9jb2RpZ28+CiAgICAgICAgICAgICAgICA8Y29kaWdvUG9yY2VudGFqZT4zMDcyPC9jb2RpZ29Qb3JjZW50YWplPgogICAgICAgICAgICAgICAgPGJhc2VJbXBvbmlibGU+Mjk1MDAwLjAwPC9iYXNlSW1wb25pYmxlPgogICAgICAgICAgICAgICAgPHZhbG9yPjE0NzUwLjAwPC92YWxvcj4KICAgICAgICAgICAgPC90b3RhbEltcHVlc3RvPgogICAgICAgICAgICA8dG90YWxJbXB1ZXN0bz4KICAgICAgICAgICAgICAgIDxjb2RpZ28+MjwvY29kaWdvPgogICAgICAgICAgICAgICAgPGNvZGlnb1BvcmNlbnRhamU+MjwvY29kaWdvUG9yY2VudGFqZT4KICAgICAgICAgICAgICAgIDxiYXNlSW1wb25pYmxlPjMwOTc1MC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgIDx2YWxvcj4zNzE2OS40MDwvdmFsb3I+CiAgICAgICAgICAgIDwvdG90YWxJbXB1ZXN0bz4KICAgICAgICAgICAgPHRvdGFsSW1wdWVzdG8+CiAgICAgICAgICAgICAgICA8Y29kaWdvPjU8L2NvZGlnbz4KICAgICAgICAgICAgICAgIDxjb2RpZ29Qb3JjZW50YWplPjUwMDE8L2NvZGlnb1BvcmNlbnRhamU+CiAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4xMjAwMC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgIDx2YWxvcj4yNDAuMDA8L3ZhbG9yPgogICAgICAgICAgICA8L3RvdGFsSW1wdWVzdG8+CiAgICAgICAgPC90b3RhbENvbkltcHVlc3Rvcz4KICAgICAgICA8cHJvcGluYT4wPC9wcm9waW5hPgogICAgICAgIDxpbXBvcnRlVG90YWw+MzQ3MTU5LjQwPC9pbXBvcnRlVG90YWw+CiAgICAgICAgPG1vbmVkYT5ET0xBUjwvbW9uZWRhPgogICAgICAgIDxwYWdvcz4KICAgICAgICAgICAgPHBhZ28+CiAgICAgICAgICAgICAgICA8Zm9ybWFQYWdvPjAxPC9mb3JtYVBhZ28+CiAgICAgICAgICAgICAgICA8dG90YWw+MzQ3MTU5LjQwPC90b3RhbD4KICAgICAgICAgICAgICAgIDxwbGF6bz4zMDwvcGxhem8+CiAgICAgICAgICAgICAgICA8dW5pZGFkVGllbXBvPmRpYXM8L3VuaWRhZFRpZW1wbz4KICAgICAgICAgICAgPC9wYWdvPgogICAgICAgIDwvcGFnb3M+CiAgICAgICAgPHZhbG9yUmV0SXZhPjEwNjIwLjAwPC92YWxvclJldEl2YT4KICAgICAgICA8dmFsb3JSZXRSZW50YT4yOTUwLjAwPC92YWxvclJldFJlbnRhPgogICAgPC9pbmZvRmFjdHVyYT4KICAgIDxkZXRhbGxlcz4KICAgICAgICA8ZGV0YWxsZT4KICAgICAgICAgICAgPGNvZGlnb1ByaW5jaXBhbD4xMjVCSkMtMDE8L2NvZGlnb1ByaW5jaXBhbD4KICAgICAgICAgICAgPGNvZGlnb0F1eGlsaWFyPjEyMzRENTY3ODktQTwvY29kaWdvQXV4aWxpYXI+CiAgICAgICAgICAgIDxkZXNjcmlwY2lvbj5DQU1JT05FVEEgNFg0IERJRVNFTCAzLjc8L2Rlc2NyaXBjaW9uPgogICAgICAgICAgICA8Y2FudGlkYWQ+MTAuMDA8L2NhbnRpZGFkPgogICAgICAgICAgICA8cHJlY2lvVW5pdGFyaW8+MzAwMDAwLjAwPC9wcmVjaW9Vbml0YXJpbz4KICAgICAgICAgICAgPGRlc2N1ZW50bz41MDAwLjAwPC9kZXNjdWVudG8+CiAgICAgICAgICAgIDxwcmVjaW9Ub3RhbFNpbkltcHVlc3RvPjI5NTAwMC4wMDwvcHJlY2lvVG90YWxTaW5JbXB1ZXN0bz4KICAgICAgICAgICAgPGRldGFsbGVzQWRpY2lvbmFsZXM+CiAgICAgICAgICAgICAgICA8ZGV0QWRpY2lvbmFsIG5vbWJyZT0iTWFyY2EgQ2hldnJvbGV0IiB2YWxvcj0iQ2hldnJvbGV0Ii8+CiAgICAgICAgICAgICAgICA8ZGV0QWRpY2lvbmFsIG5vbWJyZT0iTW9kZWxvIiB2YWxvcj0iMjAxMiIvPgogICAgICAgICAgICAgICAgPGRldEFkaWNpb25hbCBub21icmU9IkNoYXNpcyIgdmFsb3I9IjhMREVUQTAzVjIwMDAzMjg5Ii8+CiAgICAgICAgICAgIDwvZGV0YWxsZXNBZGljaW9uYWxlcz4KICAgICAgICAgICAgPGltcHVlc3Rvcz4KICAgICAgICAgICAgICAgIDxpbXB1ZXN0bz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvPjM8L2NvZGlnbz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvUG9yY2VudGFqZT4zMDcyPC9jb2RpZ29Qb3JjZW50YWplPgogICAgICAgICAgICAgICAgICAgIDx0YXJpZmE+NTwvdGFyaWZhPgogICAgICAgICAgICAgICAgICAgIDxiYXNlSW1wb25pYmxlPjI5NTAwMC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgICAgICA8dmFsb3I+MTQ3NTAuMDA8L3ZhbG9yPgogICAgICAgICAgICAgICAgPC9pbXB1ZXN0bz4KICAgICAgICAgICAgICAgIDxpbXB1ZXN0bz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvPjI8L2NvZGlnbz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvUG9yY2VudGFqZT4yPC9jb2RpZ29Qb3JjZW50YWplPgogICAgICAgICAgICAgICAgICAgIDx0YXJpZmE+MTI8L3RhcmlmYT4KICAgICAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4zMDk3NTAuMDA8L2Jhc2VJbXBvbmlibGU+CiAgICAgICAgICAgICAgICAgICAgPHZhbG9yPjM3MTcwLjAwPC92YWxvcj4KICAgICAgICAgICAgICAgIDwvaW1wdWVzdG8+CiAgICAgICAgICAgICAgICA8aW1wdWVzdG8+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnbz41PC9jb2RpZ28+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnb1BvcmNlbnRhamU+NTAwMTwvY29kaWdvUG9yY2VudGFqZT4KICAgICAgICAgICAgICAgICAgICA8dGFyaWZhPjAuMDI8L3RhcmlmYT4KICAgICAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4xMjAwMC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgICAgICA8dmFsb3I+MjQwLjAwPC92YWxvcj4KICAgICAgICAgICAgICAgIDwvaW1wdWVzdG8+CiAgICAgICAgICAgIDwvaW1wdWVzdG9zPgogICAgICAgIDwvZGV0YWxsZT4KICAgIDwvZGV0YWxsZXM+CiAgICA8aW5mb0FkaWNpb25hbD4KICAgICAgICA8Y2FtcG9BZGljaW9uYWwgbm9tYnJlPSJDb2RpZ28gSW1wdWVzdG8gSVNEIj40NTgwPC9jYW1wb0FkaWNpb25hbD4KICAgICAgICA8Y2FtcG9BZGljaW9uYWwgbm9tYnJlPSJJbXB1ZXN0byBJU0QiPjE1LjQyeDwvY2FtcG9BZGljaW9uYWw+CiAgICA8L2luZm9BZGljaW9uYWw+CjwvZmFjdHVyYT4K"
}

## ## Documentation history

- V1: 2018-04-12, first draft.
- V2: 2018-04-27, enable maven profiles.
- V3: 2018-04-28, enable swagger2 for api documentation.
- V4: 2018-11-10, Invoice RIDE generation.
- V5: 2018-11-19, Postman collection.

## Authors

| [![](https://avatars1.githubusercontent.com/u/11875482?v=4&s=80)](https://github.com/rolandopalermo) |
|-|
| [@rolandopalermo](https://github.com/rolandopalermo) |
