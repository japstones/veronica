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
	- [Request & Response Examples](#request--response-examples)
	- [Documentation history](#documentation-history)
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
$ cd veronica-web
$ mvn spring-boot:run -Pdevelopment
```

```bash
$ cd veronica-web
$ mvn spring-boot:run -Pproduction
```

Change the content with appropriate values, according with your configuration.

## Documentation
http://localhost:8080/veronica/swagger-ui.html

## Postman API Reference
https://documenter.getpostman.com/view/1388083/RzZCDHct

## Request & Response Examples
### API Resources

- [POST /api/v1/generar/factura](#post-apiv1generarfactura)
- [POST /api/v1/generar/retencion](#post-apiv1generarretencion)
- [POST /api/v1/generar/guia-remision](#post-apiv1generarguia-remision)
- [POST /api/v1/firmar/factura](#post-apiv1firmarfactura)
- [POST /api/v1/sri/enviar/factura](#post-apiv1srienviarfactura)
- [POST /api/v1/sri/autorizar](#post-apiv1sriautorizar)

### POST /api/v1/generar/factura
Example: http://localhost:8080/veronica/api/v1/generar/factura

Request body:
```
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
```
Response body:
```
{
    "content": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8ZmFjdHVyYSBpZD0iY29tcHJvYmFudGUiIHZlcnNpb249IjEuMC4wIj4KICAgIDxpbmZvVHJpYnV0YXJpYT4KICAgICAgICA8YW1iaWVudGU+MTwvYW1iaWVudGU+CiAgICAgICAgPHRpcG9FbWlzaW9uPjE8L3RpcG9FbWlzaW9uPgogICAgICAgIDxyYXpvblNvY2lhbD5EaXN0cmlidWlkb3JhIGRlIFN1bWluaXN0cm9zIE5hY2lvbmFsIFMuQS48L3Jhem9uU29jaWFsPgogICAgICAgIDxub21icmVDb21lcmNpYWw+RW1wcmVzYSBJbXBvcnRhZG9yYSB5IEV4cG9ydGFkb3JhIGRlIFBpZXphczwvbm9tYnJlQ29tZXJjaWFsPgogICAgICAgIDxydWM+MTc5MjE0NjczOTAwMTwvcnVjPgogICAgICAgIDxjbGF2ZUFjY2Vzbz4yMTEwMjAxMjAxMTc5MjE0NjczOTAwMTEwMDEwMDIwMDAwMDAwMDEwMTgyODc0MDEyPC9jbGF2ZUFjY2Vzbz4KICAgICAgICA8Y29kRG9jPjAxPC9jb2REb2M+CiAgICAgICAgPGVzdGFiPjAwMjwvZXN0YWI+CiAgICAgICAgPHB0b0VtaT4wMDE8L3B0b0VtaT4KICAgICAgICA8c2VjdWVuY2lhbD4wMDAwMDAwMDE8L3NlY3VlbmNpYWw+CiAgICAgICAgPGRpck1hdHJpej5FbnJpcXVlIEd1ZXJyZXJvIFBvcnRpbGxhIE9FMS0zNCBBVi4gR2FsbyBQbGF6YSBMYXNzbzwvZGlyTWF0cml6PgogICAgPC9pbmZvVHJpYnV0YXJpYT4KICAgIDxpbmZvRmFjdHVyYT4KICAgICAgICA8ZmVjaGFFbWlzaW9uPjIxLzEwLzIwMTI8L2ZlY2hhRW1pc2lvbj4KICAgICAgICA8ZGlyRXN0YWJsZWNpbWllbnRvPlNlYmFzdGnDoW4gTW9yZW5vIFMvTiBGcmFuY2lzY28gR2FyY8OtYTwvZGlyRXN0YWJsZWNpbWllbnRvPgogICAgICAgIDxjb250cmlidXllbnRlRXNwZWNpYWw+NTM2ODwvY29udHJpYnV5ZW50ZUVzcGVjaWFsPgogICAgICAgIDxvYmxpZ2Fkb0NvbnRhYmlsaWRhZD5TSTwvb2JsaWdhZG9Db250YWJpbGlkYWQ+CiAgICAgICAgPHRpcG9JZGVudGlmaWNhY2lvbkNvbXByYWRvcj4wNDwvdGlwb0lkZW50aWZpY2FjaW9uQ29tcHJhZG9yPgogICAgICAgIDxndWlhUmVtaXNpb24+MDAxLTAwMS0wMDAwMDAwMDE8L2d1aWFSZW1pc2lvbj4KICAgICAgICA8cmF6b25Tb2NpYWxDb21wcmFkb3I+UFJVRUJBUyBTRVJWSUNJTyBERSBSRU5UQVMgSU5URVJOQVM8L3Jhem9uU29jaWFsQ29tcHJhZG9yPgogICAgICAgIDxpZGVudGlmaWNhY2lvbkNvbXByYWRvcj4xNzEzMzI4NTA2MDAxPC9pZGVudGlmaWNhY2lvbkNvbXByYWRvcj4KICAgICAgICA8ZGlyZWNjaW9uQ29tcHJhZG9yPnNhbGluYXMgeSBzYW50aWFnbzwvZGlyZWNjaW9uQ29tcHJhZG9yPgogICAgICAgIDx0b3RhbFNpbkltcHVlc3Rvcz4yOTUwMDAuMDA8L3RvdGFsU2luSW1wdWVzdG9zPgogICAgICAgIDx0b3RhbERlc2N1ZW50bz41MDA1LjAwPC90b3RhbERlc2N1ZW50bz4KICAgICAgICA8dG90YWxDb25JbXB1ZXN0b3M+CiAgICAgICAgICAgIDx0b3RhbEltcHVlc3RvPgogICAgICAgICAgICAgICAgPGNvZGlnbz4zPC9jb2RpZ28+CiAgICAgICAgICAgICAgICA8Y29kaWdvUG9yY2VudGFqZT4zMDcyPC9jb2RpZ29Qb3JjZW50YWplPgogICAgICAgICAgICAgICAgPGJhc2VJbXBvbmlibGU+Mjk1MDAwLjAwPC9iYXNlSW1wb25pYmxlPgogICAgICAgICAgICAgICAgPHZhbG9yPjE0NzUwLjAwPC92YWxvcj4KICAgICAgICAgICAgPC90b3RhbEltcHVlc3RvPgogICAgICAgICAgICA8dG90YWxJbXB1ZXN0bz4KICAgICAgICAgICAgICAgIDxjb2RpZ28+MjwvY29kaWdvPgogICAgICAgICAgICAgICAgPGNvZGlnb1BvcmNlbnRhamU+MjwvY29kaWdvUG9yY2VudGFqZT4KICAgICAgICAgICAgICAgIDxiYXNlSW1wb25pYmxlPjMwOTc1MC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgIDx2YWxvcj4zNzE2OS40MDwvdmFsb3I+CiAgICAgICAgICAgIDwvdG90YWxJbXB1ZXN0bz4KICAgICAgICAgICAgPHRvdGFsSW1wdWVzdG8+CiAgICAgICAgICAgICAgICA8Y29kaWdvPjU8L2NvZGlnbz4KICAgICAgICAgICAgICAgIDxjb2RpZ29Qb3JjZW50YWplPjUwMDE8L2NvZGlnb1BvcmNlbnRhamU+CiAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4xMjAwMC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgIDx2YWxvcj4yNDAuMDA8L3ZhbG9yPgogICAgICAgICAgICA8L3RvdGFsSW1wdWVzdG8+CiAgICAgICAgPC90b3RhbENvbkltcHVlc3Rvcz4KICAgICAgICA8cHJvcGluYT4wPC9wcm9waW5hPgogICAgICAgIDxpbXBvcnRlVG90YWw+MzQ3MTU5LjQwPC9pbXBvcnRlVG90YWw+CiAgICAgICAgPG1vbmVkYT5ET0xBUjwvbW9uZWRhPgogICAgICAgIDxwYWdvcz4KICAgICAgICAgICAgPHBhZ28+CiAgICAgICAgICAgICAgICA8Zm9ybWFQYWdvPjAxPC9mb3JtYVBhZ28+CiAgICAgICAgICAgICAgICA8dG90YWw+MzQ3MTU5LjQwPC90b3RhbD4KICAgICAgICAgICAgICAgIDxwbGF6bz4zMDwvcGxhem8+CiAgICAgICAgICAgICAgICA8dW5pZGFkVGllbXBvPmRpYXM8L3VuaWRhZFRpZW1wbz4KICAgICAgICAgICAgPC9wYWdvPgogICAgICAgIDwvcGFnb3M+CiAgICAgICAgPHZhbG9yUmV0SXZhPjEwNjIwLjAwPC92YWxvclJldEl2YT4KICAgICAgICA8dmFsb3JSZXRSZW50YT4yOTUwLjAwPC92YWxvclJldFJlbnRhPgogICAgPC9pbmZvRmFjdHVyYT4KICAgIDxkZXRhbGxlcz4KICAgICAgICA8ZGV0YWxsZT4KICAgICAgICAgICAgPGNvZGlnb1ByaW5jaXBhbD4xMjVCSkMtMDE8L2NvZGlnb1ByaW5jaXBhbD4KICAgICAgICAgICAgPGNvZGlnb0F1eGlsaWFyPjEyMzRENTY3ODktQTwvY29kaWdvQXV4aWxpYXI+CiAgICAgICAgICAgIDxkZXNjcmlwY2lvbj5DQU1JT05FVEEgNFg0IERJRVNFTCAzLjc8L2Rlc2NyaXBjaW9uPgogICAgICAgICAgICA8Y2FudGlkYWQ+MTAuMDA8L2NhbnRpZGFkPgogICAgICAgICAgICA8cHJlY2lvVW5pdGFyaW8+MzAwMDAwLjAwPC9wcmVjaW9Vbml0YXJpbz4KICAgICAgICAgICAgPGRlc2N1ZW50bz41MDAwLjAwPC9kZXNjdWVudG8+CiAgICAgICAgICAgIDxwcmVjaW9Ub3RhbFNpbkltcHVlc3RvPjI5NTAwMC4wMDwvcHJlY2lvVG90YWxTaW5JbXB1ZXN0bz4KICAgICAgICAgICAgPGRldGFsbGVzQWRpY2lvbmFsZXM+CiAgICAgICAgICAgICAgICA8ZGV0QWRpY2lvbmFsIG5vbWJyZT0iTWFyY2EgQ2hldnJvbGV0IiB2YWxvcj0iQ2hldnJvbGV0Ii8+CiAgICAgICAgICAgICAgICA8ZGV0QWRpY2lvbmFsIG5vbWJyZT0iTW9kZWxvIiB2YWxvcj0iMjAxMiIvPgogICAgICAgICAgICAgICAgPGRldEFkaWNpb25hbCBub21icmU9IkNoYXNpcyIgdmFsb3I9IjhMREVUQTAzVjIwMDAzMjg5Ii8+CiAgICAgICAgICAgIDwvZGV0YWxsZXNBZGljaW9uYWxlcz4KICAgICAgICAgICAgPGltcHVlc3Rvcz4KICAgICAgICAgICAgICAgIDxpbXB1ZXN0bz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvPjM8L2NvZGlnbz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvUG9yY2VudGFqZT4zMDcyPC9jb2RpZ29Qb3JjZW50YWplPgogICAgICAgICAgICAgICAgICAgIDx0YXJpZmE+NTwvdGFyaWZhPgogICAgICAgICAgICAgICAgICAgIDxiYXNlSW1wb25pYmxlPjI5NTAwMC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgICAgICA8dmFsb3I+MTQ3NTAuMDA8L3ZhbG9yPgogICAgICAgICAgICAgICAgPC9pbXB1ZXN0bz4KICAgICAgICAgICAgICAgIDxpbXB1ZXN0bz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvPjI8L2NvZGlnbz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvUG9yY2VudGFqZT4yPC9jb2RpZ29Qb3JjZW50YWplPgogICAgICAgICAgICAgICAgICAgIDx0YXJpZmE+MTI8L3RhcmlmYT4KICAgICAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4zMDk3NTAuMDA8L2Jhc2VJbXBvbmlibGU+CiAgICAgICAgICAgICAgICAgICAgPHZhbG9yPjM3MTcwLjAwPC92YWxvcj4KICAgICAgICAgICAgICAgIDwvaW1wdWVzdG8+CiAgICAgICAgICAgICAgICA8aW1wdWVzdG8+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnbz41PC9jb2RpZ28+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnb1BvcmNlbnRhamU+NTAwMTwvY29kaWdvUG9yY2VudGFqZT4KICAgICAgICAgICAgICAgICAgICA8dGFyaWZhPjAuMDI8L3RhcmlmYT4KICAgICAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4xMjAwMC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgICAgICA8dmFsb3I+MjQwLjAwPC92YWxvcj4KICAgICAgICAgICAgICAgIDwvaW1wdWVzdG8+CiAgICAgICAgICAgIDwvaW1wdWVzdG9zPgogICAgICAgIDwvZGV0YWxsZT4KICAgIDwvZGV0YWxsZXM+CiAgICA8aW5mb0FkaWNpb25hbD4KICAgICAgICA8Y2FtcG9BZGljaW9uYWwgbm9tYnJlPSJDb2RpZ28gSW1wdWVzdG8gSVNEIj40NTgwPC9jYW1wb0FkaWNpb25hbD4KICAgICAgICA8Y2FtcG9BZGljaW9uYWwgbm9tYnJlPSJJbXB1ZXN0byBJU0QiPjE1LjQyeDwvY2FtcG9BZGljaW9uYWw+CiAgICA8L2luZm9BZGljaW9uYWw+CjwvZmFjdHVyYT4K"
}
```

### POST /api/v1/generar/retencion
Example: http://localhost:8080/veronica/api/v1/generar/retencion

Request body:
```
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
   "infoRetencion":{
      "fechaEmision":"15/01/2012",
      "dirEstablecimiento":"Rodrigo Moreno S/N Francisco García",
      "contribuyenteEspecial":"5368",
      "obligadoContabilidad":"SI",
      "tipoIdentificacionSujetoRetenido":"04",
      "razonSocialSujetoRetenido":"Juan Pablo Chávez Núñez",
      "identificacionSujetoRetenido":"1713328506001",
      "periodoFiscal":"03/2012"
   },
   "impuesto":[
      {
         "codigo":"2",
         "codigoRetencion":"1",
         "baseImponible":101.94,
         "porcentajeRetener":30,
         "valorRetenido":30.58,
         "codDocSustento":"01",
         "numDocSustento":"002001000000001",
         "fechaEmisionDocSustento":"20/01/2012"
      },
      {
         "codigo":"1",
         "codigoRetencion":"323B1",
         "baseImponible":10904.50,
         "porcentajeRetener":2,
         "valorRetenido":218.09,
         "codDocSustento":"01",
         "numDocSustento":"002001000000001",
         "fechaEmisionDocSustento":"20/01/2012"
      },
      {
         "codigo":"6",
         "codigoRetencion":"4580",
         "baseImponible":2000,
         "porcentajeRetener":5,
         "valorRetenido":100,
         "codDocSustento":"12",
         "numDocSustento":"002001000000001",
         "fechaEmisionDocSustento":"20/01/2012"
      }
   ],
   "campoAdicional":[
      {
         "nombre":"ConvenioDobleTributacion",
         "value":"MA123456"
      },
      {
         "nombre":"documentoIFIS",
         "value":"BP2010-01-0014"
      },
      {
         "nombre":"valorpagadoIRsociedaddividendos",
         "value":"20000"
      }
   ]
}
```
Response body:
```
{
    "content": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8Y29tcHJvYmFudGVSZXRlbmNpb24gaWQ9ImNvbXByb2JhbnRlIiB2ZXJzaW9uPSIxLjAuMCI+CiAgICA8aW5mb1RyaWJ1dGFyaWE+CiAgICAgICAgPGFtYmllbnRlPjE8L2FtYmllbnRlPgogICAgICAgIDx0aXBvRW1pc2lvbj4xPC90aXBvRW1pc2lvbj4KICAgICAgICA8cmF6b25Tb2NpYWw+RGlzdHJpYnVpZG9yYSBkZSBTdW1pbmlzdHJvcyBOYWNpb25hbCBTLkEuPC9yYXpvblNvY2lhbD4KICAgICAgICA8bm9tYnJlQ29tZXJjaWFsPkVtcHJlc2EgSW1wb3J0YWRvcmEgeSBFeHBvcnRhZG9yYSBkZSBQaWV6YXM8L25vbWJyZUNvbWVyY2lhbD4KICAgICAgICA8cnVjPjE3OTIxNDY3MzkwMDE8L3J1Yz4KICAgICAgICA8Y2xhdmVBY2Nlc28+MTUwMTIwMTIwMTE3OTIxNDY3MzkwMDExMDAxMDAyMDAwMDAwMDAxNTg5ODY5NDExMTwvY2xhdmVBY2Nlc28+CiAgICAgICAgPGNvZERvYz4wMTwvY29kRG9jPgogICAgICAgIDxlc3RhYj4wMDI8L2VzdGFiPgogICAgICAgIDxwdG9FbWk+MDAxPC9wdG9FbWk+CiAgICAgICAgPHNlY3VlbmNpYWw+MDAwMDAwMDAxPC9zZWN1ZW5jaWFsPgogICAgICAgIDxkaXJNYXRyaXo+RW5yaXF1ZSBHdWVycmVybyBQb3J0aWxsYSBPRTEtMzQgQVYuIEdhbG8gUGxhemEgTGFzc288L2Rpck1hdHJpej4KICAgIDwvaW5mb1RyaWJ1dGFyaWE+CiAgICA8aW5mb0NvbXBSZXRlbmNpb24+CiAgICAgICAgPGZlY2hhRW1pc2lvbj4xNS8wMS8yMDEyPC9mZWNoYUVtaXNpb24+CiAgICAgICAgPGRpckVzdGFibGVjaW1pZW50bz5Sb2RyaWdvIE1vcmVubyBTL04gRnJhbmNpc2NvIEdhcmPDrWE8L2RpckVzdGFibGVjaW1pZW50bz4KICAgICAgICA8Y29udHJpYnV5ZW50ZUVzcGVjaWFsPjUzNjg8L2NvbnRyaWJ1eWVudGVFc3BlY2lhbD4KICAgICAgICA8b2JsaWdhZG9Db250YWJpbGlkYWQ+U0k8L29ibGlnYWRvQ29udGFiaWxpZGFkPgogICAgICAgIDx0aXBvSWRlbnRpZmljYWNpb25TdWpldG9SZXRlbmlkbz4wNDwvdGlwb0lkZW50aWZpY2FjaW9uU3VqZXRvUmV0ZW5pZG8+CiAgICAgICAgPHJhem9uU29jaWFsU3VqZXRvUmV0ZW5pZG8+SnVhbiBQYWJsbyBDaMOhdmV6IE7DusOxZXo8L3Jhem9uU29jaWFsU3VqZXRvUmV0ZW5pZG8+CiAgICAgICAgPHBlcmlvZG9GaXNjYWw+MDMvMjAxMjwvcGVyaW9kb0Zpc2NhbD4KICAgIDwvaW5mb0NvbXBSZXRlbmNpb24+CiAgICA8aW1wdWVzdG9zPgogICAgICAgIDxpbXB1ZXN0bz4KICAgICAgICAgICAgPGNvZGlnbz4yPC9jb2RpZ28+CiAgICAgICAgICAgIDxjb2RpZ29SZXRlbmNpb24+MTwvY29kaWdvUmV0ZW5jaW9uPgogICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4xMDEuOTQ8L2Jhc2VJbXBvbmlibGU+CiAgICAgICAgICAgIDxwb3JjZW50YWplUmV0ZW5lcj4zMDwvcG9yY2VudGFqZVJldGVuZXI+CiAgICAgICAgICAgIDx2YWxvclJldGVuaWRvPjMwLjU4PC92YWxvclJldGVuaWRvPgogICAgICAgICAgICA8Y29kRG9jU3VzdGVudG8+MDE8L2NvZERvY1N1c3RlbnRvPgogICAgICAgICAgICA8bnVtRG9jU3VzdGVudG8+MDAyMDAxMDAwMDAwMDAxPC9udW1Eb2NTdXN0ZW50bz4KICAgICAgICAgICAgPGZlY2hhRW1pc2lvbkRvY1N1c3RlbnRvPjIwLzAxLzIwMTI8L2ZlY2hhRW1pc2lvbkRvY1N1c3RlbnRvPgogICAgICAgIDwvaW1wdWVzdG8+CiAgICAgICAgPGltcHVlc3RvPgogICAgICAgICAgICA8Y29kaWdvPjE8L2NvZGlnbz4KICAgICAgICAgICAgPGNvZGlnb1JldGVuY2lvbj4zMjNCMTwvY29kaWdvUmV0ZW5jaW9uPgogICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4xMDkwNC41MDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgPHBvcmNlbnRhamVSZXRlbmVyPjI8L3BvcmNlbnRhamVSZXRlbmVyPgogICAgICAgICAgICA8dmFsb3JSZXRlbmlkbz4yMTguMDk8L3ZhbG9yUmV0ZW5pZG8+CiAgICAgICAgICAgIDxjb2REb2NTdXN0ZW50bz4wMTwvY29kRG9jU3VzdGVudG8+CiAgICAgICAgICAgIDxudW1Eb2NTdXN0ZW50bz4wMDIwMDEwMDAwMDAwMDE8L251bURvY1N1c3RlbnRvPgogICAgICAgICAgICA8ZmVjaGFFbWlzaW9uRG9jU3VzdGVudG8+MjAvMDEvMjAxMjwvZmVjaGFFbWlzaW9uRG9jU3VzdGVudG8+CiAgICAgICAgPC9pbXB1ZXN0bz4KICAgICAgICA8aW1wdWVzdG8+CiAgICAgICAgICAgIDxjb2RpZ28+NjwvY29kaWdvPgogICAgICAgICAgICA8Y29kaWdvUmV0ZW5jaW9uPjQ1ODA8L2NvZGlnb1JldGVuY2lvbj4KICAgICAgICAgICAgPGJhc2VJbXBvbmlibGU+MjAwMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgPHBvcmNlbnRhamVSZXRlbmVyPjU8L3BvcmNlbnRhamVSZXRlbmVyPgogICAgICAgICAgICA8dmFsb3JSZXRlbmlkbz4xMDA8L3ZhbG9yUmV0ZW5pZG8+CiAgICAgICAgICAgIDxjb2REb2NTdXN0ZW50bz4xMjwvY29kRG9jU3VzdGVudG8+CiAgICAgICAgICAgIDxudW1Eb2NTdXN0ZW50bz4wMDIwMDEwMDAwMDAwMDE8L251bURvY1N1c3RlbnRvPgogICAgICAgICAgICA8ZmVjaGFFbWlzaW9uRG9jU3VzdGVudG8+MjAvMDEvMjAxMjwvZmVjaGFFbWlzaW9uRG9jU3VzdGVudG8+CiAgICAgICAgPC9pbXB1ZXN0bz4KICAgIDwvaW1wdWVzdG9zPgogICAgPGluZm9BZGljaW9uYWw+CiAgICAgICAgPGNhbXBvQWRpY2lvbmFsIG5vbWJyZT0iQ29udmVuaW9Eb2JsZVRyaWJ1dGFjaW9uIj5NQTEyMzQ1NjwvY2FtcG9BZGljaW9uYWw+CiAgICAgICAgPGNhbXBvQWRpY2lvbmFsIG5vbWJyZT0iZG9jdW1lbnRvSUZJUyI+QlAyMDEwLTAxLTAwMTQ8L2NhbXBvQWRpY2lvbmFsPgogICAgICAgIDxjYW1wb0FkaWNpb25hbCBub21icmU9InZhbG9ycGFnYWRvSVJzb2NpZWRhZGRpdmlkZW5kb3MiPjIwMDAwPC9jYW1wb0FkaWNpb25hbD4KICAgIDwvaW5mb0FkaWNpb25hbD4KPC9jb21wcm9iYW50ZVJldGVuY2lvbj4K"
}
```

### POST /api/v1/generar/guia-remision
Example: http://localhost:8080/veronica/api/v1/generar/guia-remision

Request body:
```
{
   "id":"comprobante",
   "version":"1.1.0",
   "infoTributaria":{
      "ambiente":"1",
      "codDoc":"06",
      "dirMatriz":"ALPALLANA",
      "estab":"001",
      "nombreComercial":"EMPRESA PUBLICA DE HIDROCARBUROS DEL ECUADOR EP PETROECUADOR",
      "ptoEmi":"501",
      "razonSocial":"EMPRESA PUBLICA DE HIDROCARBUROS DEL ECUADOR EP PETROECUADOR",
      "ruc":"1760013210001",
      "secuencial":"000000008",
      "tipoEmision":"1"
   },
   "infoGuiaRemisionDTO":{
      "contribuyenteEspecial":"5368",
      "dirEstablecimiento":"ALPALLANA",
      "dirPartida":"Av. Eloy Alfaro 34 y Av. Libertad Esq",
      "fechaFinTransporte":"20/11/2018",
      "fechaIniTransporte":"19/11/2018",
      "obligadoContabilidad":"SI",
      "placa":"MCL0827",
      "razonSocialTransportista":"Transportes S.A",
      "rise":"Contribuyente Regimen Simplificado RISE",
      "rucTransportista":"1796875790001",
      "tipoIdentificacionTransportista":"04"
   },
   "destinatario":[
      {
         "codDocSustento":"01",
         "codEstabDestino":"001",
         "detalle":[
            {
               "cantidad":10.254632,
               "codigoAdicional":"1234D56789-A",
               "codigoInterno":"125BJC-01",
               "descripcion":"DIESEL",
               "detAdicional":[
                  {
                     "nombre":"ABCD",
                     "valor":"EFGH"
                  },
                  {
                     "nombre":"ABCD",
                     "valor":"EFGH"
                  },
                  {
                     "nombre":"ABCD",
                     "valor":"EFGH"
                  }
               ]
            }
         ],
         "dirDestinatario":"Av. Simón Bolívar S/N Intercambiador",
         "docAduaneroUnico":"0041324846887",
         "fechaEmisionDocSustento":"19/11/2018",
         "identificacionDestinatario":"1716849140001",
         "motivoTraslado":"Venta de Maquinaria de Impresión",
         "numAutDocSustento":"2110201116302517921467390011234567891",
         "numDocSustento":"002-001-000000001",
         "razonSocialDestinatario":"Alvarez Mina John Henry",
         "ruta":"Quito – Cayambe - Otavalo"
      }
   ],
   "campoAdicional":[
      {
         "nombre":"TELEFONO",
         "value":"098568541"
      },
      {
         "nombre":"E-MAIL",
         "value":"info@organizacion.com"
      },
      {
         "nombre":"SUCURSAL 03",
         "value":"Guayaquil–12 de Octubre y Universo"
      }
   ]
}
```
Response body:
```
{
    "content": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8Z3VpYVJlbWlzaW9uIGlkPSJjb21wcm9iYW50ZSIgdmVyc2lvbj0iMS4xLjAiPgogICAgPGluZm9UcmlidXRhcmlhPgogICAgICAgIDxhbWJpZW50ZT4xPC9hbWJpZW50ZT4KICAgICAgICA8dGlwb0VtaXNpb24+MTwvdGlwb0VtaXNpb24+CiAgICAgICAgPHJhem9uU29jaWFsPkVNUFJFU0EgUFVCTElDQSBERSBISURST0NBUkJVUk9TIERFTCBFQ1VBRE9SIEVQIFBFVFJPRUNVQURPUjwvcmF6b25Tb2NpYWw+CiAgICAgICAgPG5vbWJyZUNvbWVyY2lhbD5FTVBSRVNBIFBVQkxJQ0EgREUgSElEUk9DQVJCVVJPUyBERUwgRUNVQURPUiBFUCBQRVRST0VDVUFET1I8L25vbWJyZUNvbWVyY2lhbD4KICAgICAgICA8cnVjPjE3NjAwMTMyMTAwMDE8L3J1Yz4KICAgICAgICA8Y2xhdmVBY2Nlc28+MTkxMTIwMTgwNjE3NjAwMTMyMTAwMDExNTAxMDAxMDAwMDAwMDA4NTk4ODA4ODYxMzwvY2xhdmVBY2Nlc28+CiAgICAgICAgPGNvZERvYz4wNjwvY29kRG9jPgogICAgICAgIDxlc3RhYj4wMDE8L2VzdGFiPgogICAgICAgIDxwdG9FbWk+NTAxPC9wdG9FbWk+CiAgICAgICAgPHNlY3VlbmNpYWw+MDAwMDAwMDA4PC9zZWN1ZW5jaWFsPgogICAgICAgIDxkaXJNYXRyaXo+QUxQQUxMQU5BPC9kaXJNYXRyaXo+CiAgICA8L2luZm9UcmlidXRhcmlhPgogICAgPGluZm9HdWlhUmVtaXNpb24+CiAgICAgICAgPGRpckVzdGFibGVjaW1pZW50bz5BTFBBTExBTkE8L2RpckVzdGFibGVjaW1pZW50bz4KICAgICAgICA8ZGlyUGFydGlkYT5Bdi4gRWxveSBBbGZhcm8gMzQgeSBBdi4gTGliZXJ0YWQgRXNxPC9kaXJQYXJ0aWRhPgogICAgICAgIDxyYXpvblNvY2lhbFRyYW5zcG9ydGlzdGE+VHJhbnNwb3J0ZXMgUy5BPC9yYXpvblNvY2lhbFRyYW5zcG9ydGlzdGE+CiAgICAgICAgPHRpcG9JZGVudGlmaWNhY2lvblRyYW5zcG9ydGlzdGE+MDQ8L3RpcG9JZGVudGlmaWNhY2lvblRyYW5zcG9ydGlzdGE+CiAgICAgICAgPHJ1Y1RyYW5zcG9ydGlzdGE+MTc5Njg3NTc5MDAwMTwvcnVjVHJhbnNwb3J0aXN0YT4KICAgICAgICA8cmlzZT5Db250cmlidXllbnRlIFJlZ2ltZW4gU2ltcGxpZmljYWRvIFJJU0U8L3Jpc2U+CiAgICAgICAgPG9ibGlnYWRvQ29udGFiaWxpZGFkPlNJPC9vYmxpZ2Fkb0NvbnRhYmlsaWRhZD4KICAgICAgICA8Y29udHJpYnV5ZW50ZUVzcGVjaWFsPjUzNjg8L2NvbnRyaWJ1eWVudGVFc3BlY2lhbD4KICAgICAgICA8ZmVjaGFJbmlUcmFuc3BvcnRlPjE5LzExLzIwMTg8L2ZlY2hhSW5pVHJhbnNwb3J0ZT4KICAgICAgICA8ZmVjaGFGaW5UcmFuc3BvcnRlPjIwLzExLzIwMTg8L2ZlY2hhRmluVHJhbnNwb3J0ZT4KICAgICAgICA8cGxhY2E+TUNMMDgyNzwvcGxhY2E+CiAgICA8L2luZm9HdWlhUmVtaXNpb24+CiAgICA8ZGVzdGluYXRhcmlvcz4KICAgICAgICA8ZGVzdGluYXRhcmlvPgogICAgICAgICAgICA8aWRlbnRpZmljYWNpb25EZXN0aW5hdGFyaW8+MTcxNjg0OTE0MDAwMTwvaWRlbnRpZmljYWNpb25EZXN0aW5hdGFyaW8+CiAgICAgICAgICAgIDxyYXpvblNvY2lhbERlc3RpbmF0YXJpbz5BbHZhcmV6IE1pbmEgSm9obiBIZW5yeTwvcmF6b25Tb2NpYWxEZXN0aW5hdGFyaW8+CiAgICAgICAgICAgIDxkaXJEZXN0aW5hdGFyaW8+QXYuIFNpbcOzbiBCb2zDrXZhciBTL04gSW50ZXJjYW1iaWFkb3I8L2RpckRlc3RpbmF0YXJpbz4KICAgICAgICAgICAgPG1vdGl2b1RyYXNsYWRvPlZlbnRhIGRlIE1hcXVpbmFyaWEgZGUgSW1wcmVzacOzbjwvbW90aXZvVHJhc2xhZG8+CiAgICAgICAgICAgIDxkb2NBZHVhbmVyb1VuaWNvPjAwNDEzMjQ4NDY4ODc8L2RvY0FkdWFuZXJvVW5pY28+CiAgICAgICAgICAgIDxjb2RFc3RhYkRlc3Rpbm8+MDAxPC9jb2RFc3RhYkRlc3Rpbm8+CiAgICAgICAgICAgIDxydXRhPlF1aXRvIOKAkyBDYXlhbWJlIC0gT3RhdmFsbzwvcnV0YT4KICAgICAgICAgICAgPGNvZERvY1N1c3RlbnRvPjAxPC9jb2REb2NTdXN0ZW50bz4KICAgICAgICAgICAgPG51bURvY1N1c3RlbnRvPjAwMi0wMDEtMDAwMDAwMDAxPC9udW1Eb2NTdXN0ZW50bz4KICAgICAgICAgICAgPG51bUF1dERvY1N1c3RlbnRvPjIxMTAyMDExMTYzMDI1MTc5MjE0NjczOTAwMTEyMzQ1Njc4OTE8L251bUF1dERvY1N1c3RlbnRvPgogICAgICAgICAgICA8ZmVjaGFFbWlzaW9uRG9jU3VzdGVudG8+MTkvMTEvMjAxODwvZmVjaGFFbWlzaW9uRG9jU3VzdGVudG8+CiAgICAgICAgICAgIDxkZXRhbGxlcz4KICAgICAgICAgICAgICAgIDxkZXRhbGxlPgogICAgICAgICAgICAgICAgICAgIDxjb2RpZ29JbnRlcm5vPjEyNUJKQy0wMTwvY29kaWdvSW50ZXJubz4KICAgICAgICAgICAgICAgICAgICA8Y29kaWdvQWRpY2lvbmFsPjEyMzRENTY3ODktQTwvY29kaWdvQWRpY2lvbmFsPgogICAgICAgICAgICAgICAgICAgIDxkZXNjcmlwY2lvbj5ESUVTRUw8L2Rlc2NyaXBjaW9uPgogICAgICAgICAgICAgICAgICAgIDxjYW50aWRhZD4xMC4yNTQ2MzI8L2NhbnRpZGFkPgogICAgICAgICAgICAgICAgICAgIDxkZXRhbGxlc0FkaWNpb25hbGVzPgogICAgICAgICAgICAgICAgICAgICAgICA8ZGV0QWRpY2lvbmFsIG5vbWJyZT0iQUJDRCIgdmFsb3I9IkVGR0giLz4KICAgICAgICAgICAgICAgICAgICAgICAgPGRldEFkaWNpb25hbCBub21icmU9IkFCQ0QiIHZhbG9yPSJFRkdIIi8+CiAgICAgICAgICAgICAgICAgICAgICAgIDxkZXRBZGljaW9uYWwgbm9tYnJlPSJBQkNEIiB2YWxvcj0iRUZHSCIvPgogICAgICAgICAgICAgICAgICAgIDwvZGV0YWxsZXNBZGljaW9uYWxlcz4KICAgICAgICAgICAgICAgIDwvZGV0YWxsZT4KICAgICAgICAgICAgPC9kZXRhbGxlcz4KICAgICAgICA8L2Rlc3RpbmF0YXJpbz4KICAgIDwvZGVzdGluYXRhcmlvcz4KICAgIDxpbmZvQWRpY2lvbmFsPgogICAgICAgIDxjYW1wb0FkaWNpb25hbCBub21icmU9IlRFTEVGT05PIj4wOTg1Njg1NDE8L2NhbXBvQWRpY2lvbmFsPgogICAgICAgIDxjYW1wb0FkaWNpb25hbCBub21icmU9IkUtTUFJTCI+aW5mb0Bvcmdhbml6YWNpb24uY29tPC9jYW1wb0FkaWNpb25hbD4KICAgICAgICA8Y2FtcG9BZGljaW9uYWwgbm9tYnJlPSJTVUNVUlNBTCAwMyI+R3VheWFxdWls4oCTMTIgZGUgT2N0dWJyZSB5IFVuaXZlcnNvPC9jYW1wb0FkaWNpb25hbD4KICAgIDwvaW5mb0FkaWNpb25hbD4KPC9ndWlhUmVtaXNpb24+Cg=="
}
```

### POST /api/v1/firmar/factura
Example: http://localhost:8080/veronica/api/v1/firmar/factura

Request body:
```
{
   "rutaArchivoPkcs12":"D:\\Privado\\certificado.p12",
   "claveArchivopkcs12":"MAte2910",
   "comprobanteAsObj":{
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
}
```
Response body:
```
{
    "content": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPGZhY3R1cmEgaWQ9ImNvbXByb2JhbnRlIiB2ZXJzaW9uPSIxLjAuMCI+CiAgICA8aW5mb1RyaWJ1dGFyaWE+CiAgICAgICAgPGFtYmllbnRlPjE8L2FtYmllbnRlPgogICAgICAgIDx0aXBvRW1pc2lvbj4xPC90aXBvRW1pc2lvbj4KICAgICAgICA8cmF6b25Tb2NpYWw+RGlzdHJpYnVpZG9yYSBkZSBTdW1pbmlzdHJvcyBOYWNpb25hbCBTLkEuPC9yYXpvblNvY2lhbD4KICAgICAgICA8bm9tYnJlQ29tZXJjaWFsPkVtcHJlc2EgSW1wb3J0YWRvcmEgeSBFeHBvcnRhZG9yYSBkZSBQaWV6YXM8L25vbWJyZUNvbWVyY2lhbD4KICAgICAgICA8cnVjPjE3OTIxNDY3MzkwMDE8L3J1Yz4KICAgICAgICA8Y2xhdmVBY2Nlc28+MjExMDIwMTIwMTE3OTIxNDY3MzkwMDExMDAxMDAyMDAwMDAwMDAxNDg5NjQyNDkxMTwvY2xhdmVBY2Nlc28+CiAgICAgICAgPGNvZERvYz4wMTwvY29kRG9jPgogICAgICAgIDxlc3RhYj4wMDI8L2VzdGFiPgogICAgICAgIDxwdG9FbWk+MDAxPC9wdG9FbWk+CiAgICAgICAgPHNlY3VlbmNpYWw+MDAwMDAwMDAxPC9zZWN1ZW5jaWFsPgogICAgICAgIDxkaXJNYXRyaXo+RW5yaXF1ZSBHdWVycmVybyBQb3J0aWxsYSBPRTEtMzQgQVYuIEdhbG8gUGxhemEgTGFzc288L2Rpck1hdHJpej4KICAgIDwvaW5mb1RyaWJ1dGFyaWE+CiAgICA8aW5mb0ZhY3R1cmE+CiAgICAgICAgPGZlY2hhRW1pc2lvbj4yMS8xMC8yMDEyPC9mZWNoYUVtaXNpb24+CiAgICAgICAgPGRpckVzdGFibGVjaW1pZW50bz5TZWJhc3Rpw6FuIE1vcmVubyBTL04gRnJhbmNpc2NvIEdhcmPDrWE8L2RpckVzdGFibGVjaW1pZW50bz4KICAgICAgICA8Y29udHJpYnV5ZW50ZUVzcGVjaWFsPjUzNjg8L2NvbnRyaWJ1eWVudGVFc3BlY2lhbD4KICAgICAgICA8b2JsaWdhZG9Db250YWJpbGlkYWQ+U0k8L29ibGlnYWRvQ29udGFiaWxpZGFkPgogICAgICAgIDx0aXBvSWRlbnRpZmljYWNpb25Db21wcmFkb3I+MDQ8L3RpcG9JZGVudGlmaWNhY2lvbkNvbXByYWRvcj4KICAgICAgICA8Z3VpYVJlbWlzaW9uPjAwMS0wMDEtMDAwMDAwMDAxPC9ndWlhUmVtaXNpb24+CiAgICAgICAgPHJhem9uU29jaWFsQ29tcHJhZG9yPlBSVUVCQVMgU0VSVklDSU8gREUgUkVOVEFTIElOVEVSTkFTPC9yYXpvblNvY2lhbENvbXByYWRvcj4KICAgICAgICA8aWRlbnRpZmljYWNpb25Db21wcmFkb3I+MTcxMzMyODUwNjAwMTwvaWRlbnRpZmljYWNpb25Db21wcmFkb3I+CiAgICAgICAgPGRpcmVjY2lvbkNvbXByYWRvcj5zYWxpbmFzIHkgc2FudGlhZ288L2RpcmVjY2lvbkNvbXByYWRvcj4KICAgICAgICA8dG90YWxTaW5JbXB1ZXN0b3M+Mjk1MDAwLjAwPC90b3RhbFNpbkltcHVlc3Rvcz4KICAgICAgICA8dG90YWxEZXNjdWVudG8+NTAwNS4wMDwvdG90YWxEZXNjdWVudG8+CiAgICAgICAgPHRvdGFsQ29uSW1wdWVzdG9zPgogICAgICAgICAgICA8dG90YWxJbXB1ZXN0bz4KICAgICAgICAgICAgICAgIDxjb2RpZ28+MzwvY29kaWdvPgogICAgICAgICAgICAgICAgPGNvZGlnb1BvcmNlbnRhamU+MzA3MjwvY29kaWdvUG9yY2VudGFqZT4KICAgICAgICAgICAgICAgIDxiYXNlSW1wb25pYmxlPjI5NTAwMC4wMDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgIDx2YWxvcj4xNDc1MC4wMDwvdmFsb3I+CiAgICAgICAgICAgIDwvdG90YWxJbXB1ZXN0bz4KICAgICAgICAgICAgPHRvdGFsSW1wdWVzdG8+CiAgICAgICAgICAgICAgICA8Y29kaWdvPjI8L2NvZGlnbz4KICAgICAgICAgICAgICAgIDxjb2RpZ29Qb3JjZW50YWplPjI8L2NvZGlnb1BvcmNlbnRhamU+CiAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4zMDk3NTAuMDA8L2Jhc2VJbXBvbmlibGU+CiAgICAgICAgICAgICAgICA8dmFsb3I+MzcxNjkuNDA8L3ZhbG9yPgogICAgICAgICAgICA8L3RvdGFsSW1wdWVzdG8+CiAgICAgICAgICAgIDx0b3RhbEltcHVlc3RvPgogICAgICAgICAgICAgICAgPGNvZGlnbz41PC9jb2RpZ28+CiAgICAgICAgICAgICAgICA8Y29kaWdvUG9yY2VudGFqZT41MDAxPC9jb2RpZ29Qb3JjZW50YWplPgogICAgICAgICAgICAgICAgPGJhc2VJbXBvbmlibGU+MTIwMDAuMDA8L2Jhc2VJbXBvbmlibGU+CiAgICAgICAgICAgICAgICA8dmFsb3I+MjQwLjAwPC92YWxvcj4KICAgICAgICAgICAgPC90b3RhbEltcHVlc3RvPgogICAgICAgIDwvdG90YWxDb25JbXB1ZXN0b3M+CiAgICAgICAgPHByb3BpbmE+MDwvcHJvcGluYT4KICAgICAgICA8aW1wb3J0ZVRvdGFsPjM0NzE1OS40MDwvaW1wb3J0ZVRvdGFsPgogICAgICAgIDxtb25lZGE+RE9MQVI8L21vbmVkYT4KICAgICAgICA8cGFnb3M+CiAgICAgICAgICAgIDxwYWdvPgogICAgICAgICAgICAgICAgPGZvcm1hUGFnbz4wMTwvZm9ybWFQYWdvPgogICAgICAgICAgICAgICAgPHRvdGFsPjM0NzE1OS40MDwvdG90YWw+CiAgICAgICAgICAgICAgICA8cGxhem8+MzA8L3BsYXpvPgogICAgICAgICAgICAgICAgPHVuaWRhZFRpZW1wbz5kaWFzPC91bmlkYWRUaWVtcG8+CiAgICAgICAgICAgIDwvcGFnbz4KICAgICAgICA8L3BhZ29zPgogICAgICAgIDx2YWxvclJldEl2YT4xMDYyMC4wMDwvdmFsb3JSZXRJdmE+CiAgICAgICAgPHZhbG9yUmV0UmVudGE+Mjk1MC4wMDwvdmFsb3JSZXRSZW50YT4KICAgIDwvaW5mb0ZhY3R1cmE+CiAgICA8ZGV0YWxsZXM+CiAgICAgICAgPGRldGFsbGU+CiAgICAgICAgICAgIDxjb2RpZ29QcmluY2lwYWw+MTI1QkpDLTAxPC9jb2RpZ29QcmluY2lwYWw+CiAgICAgICAgICAgIDxjb2RpZ29BdXhpbGlhcj4xMjM0RDU2Nzg5LUE8L2NvZGlnb0F1eGlsaWFyPgogICAgICAgICAgICA8ZGVzY3JpcGNpb24+Q0FNSU9ORVRBIDRYNCBESUVTRUwgMy43PC9kZXNjcmlwY2lvbj4KICAgICAgICAgICAgPGNhbnRpZGFkPjEwLjAwPC9jYW50aWRhZD4KICAgICAgICAgICAgPHByZWNpb1VuaXRhcmlvPjMwMDAwMC4wMDwvcHJlY2lvVW5pdGFyaW8+CiAgICAgICAgICAgIDxkZXNjdWVudG8+NTAwMC4wMDwvZGVzY3VlbnRvPgogICAgICAgICAgICA8cHJlY2lvVG90YWxTaW5JbXB1ZXN0bz4yOTUwMDAuMDA8L3ByZWNpb1RvdGFsU2luSW1wdWVzdG8+CiAgICAgICAgICAgIDxkZXRhbGxlc0FkaWNpb25hbGVzPgogICAgICAgICAgICAgICAgPGRldEFkaWNpb25hbCBub21icmU9Ik1hcmNhIENoZXZyb2xldCIgdmFsb3I9IkNoZXZyb2xldCI+PC9kZXRBZGljaW9uYWw+CiAgICAgICAgICAgICAgICA8ZGV0QWRpY2lvbmFsIG5vbWJyZT0iTW9kZWxvIiB2YWxvcj0iMjAxMiI+PC9kZXRBZGljaW9uYWw+CiAgICAgICAgICAgICAgICA8ZGV0QWRpY2lvbmFsIG5vbWJyZT0iQ2hhc2lzIiB2YWxvcj0iOExERVRBMDNWMjAwMDMyODkiPjwvZGV0QWRpY2lvbmFsPgogICAgICAgICAgICA8L2RldGFsbGVzQWRpY2lvbmFsZXM+CiAgICAgICAgICAgIDxpbXB1ZXN0b3M+CiAgICAgICAgICAgICAgICA8aW1wdWVzdG8+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnbz4zPC9jb2RpZ28+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnb1BvcmNlbnRhamU+MzA3MjwvY29kaWdvUG9yY2VudGFqZT4KICAgICAgICAgICAgICAgICAgICA8dGFyaWZhPjU8L3RhcmlmYT4KICAgICAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT4yOTUwMDAuMDA8L2Jhc2VJbXBvbmlibGU+CiAgICAgICAgICAgICAgICAgICAgPHZhbG9yPjE0NzUwLjAwPC92YWxvcj4KICAgICAgICAgICAgICAgIDwvaW1wdWVzdG8+CiAgICAgICAgICAgICAgICA8aW1wdWVzdG8+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnbz4yPC9jb2RpZ28+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnb1BvcmNlbnRhamU+MjwvY29kaWdvUG9yY2VudGFqZT4KICAgICAgICAgICAgICAgICAgICA8dGFyaWZhPjEyPC90YXJpZmE+CiAgICAgICAgICAgICAgICAgICAgPGJhc2VJbXBvbmlibGU+MzA5NzUwLjAwPC9iYXNlSW1wb25pYmxlPgogICAgICAgICAgICAgICAgICAgIDx2YWxvcj4zNzE3MC4wMDwvdmFsb3I+CiAgICAgICAgICAgICAgICA8L2ltcHVlc3RvPgogICAgICAgICAgICAgICAgPGltcHVlc3RvPgogICAgICAgICAgICAgICAgICAgIDxjb2RpZ28+NTwvY29kaWdvPgogICAgICAgICAgICAgICAgICAgIDxjb2RpZ29Qb3JjZW50YWplPjUwMDE8L2NvZGlnb1BvcmNlbnRhamU+CiAgICAgICAgICAgICAgICAgICAgPHRhcmlmYT4wLjAyPC90YXJpZmE+CiAgICAgICAgICAgICAgICAgICAgPGJhc2VJbXBvbmlibGU+MTIwMDAuMDA8L2Jhc2VJbXBvbmlibGU+CiAgICAgICAgICAgICAgICAgICAgPHZhbG9yPjI0MC4wMDwvdmFsb3I+CiAgICAgICAgICAgICAgICA8L2ltcHVlc3RvPgogICAgICAgICAgICA8L2ltcHVlc3Rvcz4KICAgICAgICA8L2RldGFsbGU+CiAgICA8L2RldGFsbGVzPgogICAgPGluZm9BZGljaW9uYWw+CiAgICAgICAgPGNhbXBvQWRpY2lvbmFsIG5vbWJyZT0iQ29kaWdvIEltcHVlc3RvIElTRCI+NDU4MDwvY2FtcG9BZGljaW9uYWw+CiAgICAgICAgPGNhbXBvQWRpY2lvbmFsIG5vbWJyZT0iSW1wdWVzdG8gSVNEIj4xNS40Mng8L2NhbXBvQWRpY2lvbmFsPgogICAgPC9pbmZvQWRpY2lvbmFsPgo8ZHM6U2lnbmF0dXJlIHhtbG5zOmRzPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjIiB4bWxuczpldHNpPSJodHRwOi8vdXJpLmV0c2kub3JnLzAxOTAzL3YxLjMuMiMiIElkPSJTaWduYXR1cmU0NTk0NTgiPgo8ZHM6U2lnbmVkSW5mbyBJZD0iU2lnbmF0dXJlLVNpZ25lZEluZm8xOTQwNDIiPgo8ZHM6Q2Fub25pY2FsaXphdGlvbk1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnL1RSLzIwMDEvUkVDLXhtbC1jMTRuLTIwMDEwMzE1Ij48L2RzOkNhbm9uaWNhbGl6YXRpb25NZXRob2Q+CjxkczpTaWduYXR1cmVNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjcnNhLXNoYTEiPjwvZHM6U2lnbmF0dXJlTWV0aG9kPgo8ZHM6UmVmZXJlbmNlIElkPSJTaWduZWRQcm9wZXJ0aWVzSUQ1ODQ0MDUiIFR5cGU9Imh0dHA6Ly91cmkuZXRzaS5vcmcvMDE5MDMjU2lnbmVkUHJvcGVydGllcyIgVVJJPSIjU2lnbmF0dXJlNDU5NDU4LVNpZ25lZFByb3BlcnRpZXM4NTgwNjUiPgo8ZHM6RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI3NoYTEiPjwvZHM6RGlnZXN0TWV0aG9kPgo8ZHM6RGlnZXN0VmFsdWU+SzFQN3JzaUlqZzIvVGdtczYzSDJZNDlsc2VRPTwvZHM6RGlnZXN0VmFsdWU+CjwvZHM6UmVmZXJlbmNlPgo8ZHM6UmVmZXJlbmNlIFVSST0iI0NlcnRpZmljYXRlMTQ4MTExMCI+CjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjc2hhMSI+PC9kczpEaWdlc3RNZXRob2Q+CjxkczpEaWdlc3RWYWx1ZT5YV2FMUERjR05vbzdmOStRanRSaGJoS0d3R3M9PC9kczpEaWdlc3RWYWx1ZT4KPC9kczpSZWZlcmVuY2U+CjxkczpSZWZlcmVuY2UgSWQ9IlJlZmVyZW5jZS1JRC01MTQwNCIgVVJJPSIjY29tcHJvYmFudGUiPgo8ZHM6VHJhbnNmb3Jtcz4KPGRzOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJlIj48L2RzOlRyYW5zZm9ybT4KPC9kczpUcmFuc2Zvcm1zPgo8ZHM6RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8wOS94bWxkc2lnI3NoYTEiPjwvZHM6RGlnZXN0TWV0aG9kPgo8ZHM6RGlnZXN0VmFsdWU+NkVuRHEzZ3ZKYS9IQzJZcGF0Q09PL0hpSEpBPTwvZHM6RGlnZXN0VmFsdWU+CjwvZHM6UmVmZXJlbmNlPgo8L2RzOlNpZ25lZEluZm8+CjxkczpTaWduYXR1cmVWYWx1ZSBJZD0iU2lnbmF0dXJlVmFsdWU0ODM2NjIiPgpHaFovUmdwc09maXBVZ2E5RC9XWDFjNURaYzlzcFpkaDBBdks5UmVzUTl5TXdidTNBUkphejdjMnRLSjhFY3BrL2V3bVc4czZCMFcvClpFeHlOT3I3SURqQnhZYWlNUmk0Rm40RVVPZHJDWWdXSlNCTWg4REhpdHc0L2wrSE5JT1BPbnZyTGc3bmMycDUvd2tCeUhHbXMvVjUKMEZJbWFzNW9HOUtOdXNEaDEyeUY2U05vRmgrMHFTN0xtZkdqTGQ5aHRoUFgwSEp3NllXM2NvYjU5ZzdlR2EzK2ZTWlJLdEpOdlBVbQpiT1JkTEZZcnlWaldIUDl0RUJ2WEJZMHErT3lBTEJTOGxWdFdmdldEclZDZmh0OXJGSWZqMWI1N1grR01MTytrNlhuRDhiMDkzVlRICnNyQjIrMXEzcmRsc1RLbVgvU2dFelBmdkNmV3ZWN0VWMTk5aUhBPT0KPC9kczpTaWduYXR1cmVWYWx1ZT4KPGRzOktleUluZm8gSWQ9IkNlcnRpZmljYXRlMTQ4MTExMCI+CjxkczpYNTA5RGF0YT4KPGRzOlg1MDlDZXJ0aWZpY2F0ZT4KTUlJSlRUQ0NCeldnQXdJQkFnSUVUa1dldWpBTkJna3Foa2lHOXcwQkFRc0ZBRENCb1RFTE1Ba0dBMVVFQmhNQ1JVTXhJakFnQmdOVgpCQW9UR1VKQlRrTlBJRU5GVGxSU1FVd2dSRVZNSUVWRFZVRkVUMUl4TnpBMUJnTlZCQXNUTGtWT1ZFbEVRVVFnUkVVZ1EwVlNWRWxHClNVTkJRMGxQVGlCRVJTQkpUa1pQVWsxQlEwbFBUaTFGUTBsQ1EwVXhEakFNQmdOVkJBY1RCVkZWU1ZSUE1TVXdJd1lEVlFRREV4eEIKUXlCQ1FVNURUeUJEUlU1VVVrRk1JRVJGVENCRlExVkJSRTlTTUI0WERURTFNVEl3T0RJd05USXpPRm9YRFRFM01USXdPREl4TWpJegpPRm93Z2E4eEN6QUpCZ05WQkFZVEFrVkRNU0l3SUFZRFZRUUtFeGxDUVU1RFR5QkRSVTVVVWtGTUlFUkZUQ0JGUTFWQlJFOVNNVGN3Ck5RWURWUVFMRXk1RlRsUkpSRUZFSUVSRklFTkZVbFJKUmtsRFFVTkpUMDRnUkVVZ1NVNUdUMUpOUVVOSlQwNHRSVU5KUWtORk1RNHcKREFZRFZRUUhFd1ZSVlVsVVR6RXpNQkVHQTFVRUJSTUtNREF3TURBNU16YzNOREFlQmdOVkJBTVRGMGxXUVU0Z1JFRlNTVThnUkVsQgpXaUJCUjFWSlVsSkZNSUlCSWpBTkJna3Foa2lHOXcwQkFRRUZBQU9DQVE4QU1JSUJDZ0tDQVFFQXlBbmdHWmh3UFhYSU5nMXBZaE84CmRtZm92ei9yZ0Q1RjRoU1dtRU01VThzRFZ3SUxnR3gxSW5yM2ErVFBkajNiLzhpaWhoYXF0T2RWVG04L2p6VEdsZVh2UVMzS0xMNUsKekxzMjNVNkVveTFNSFJEUm1idjVaNm1yYVJ6VUQ3a2ZvOHhqbGtzZnZCblFDNTF2RFozMU5SN2ljZ3hTUEIvWXI4ODJTKzBHYUpMVApmTmNrY3JrZTVKdi9LWVVkM28rTEhqTFNOeDJtdU4rZlBTRkdOc2NoamhhMmpMSzlzdnl4azlNTVVBbGQ0dzgrc3poaHZ2ZDRCczlWClFzTTg3TXkralQxOTViQkNab0h3NDZPRkRVaUhpZDZhNjV5b3FUZTNYK3l5b1ZDcGFGSFJoQVpYZDg4VnYyaDNEQ3I1SSt2ZVlWMEoKVkV3VzBsMS94OG5mcnViMlZ3SURBUUFCbzRJRWV6Q0NCSGN3Z1pFR0NDc0dBUVVGQndFQkJJR0VNSUdCTUQ0R0NDc0dBUVVGQnpBQgpoakpvZEhSd09pOHZiMk56Y0M1bFkya3VZbU5sTG1WakwyVnFZbU5oTDNCMVlteHBZM2RsWWk5emRHRjBkWE12YjJOemNEQS9CZ2dyCkJnRUZCUWN3QVlZemFIUjBjRG92TDI5amMzQXhMbVZqYVM1aVkyVXVaV012WldwaVkyRXZjSFZpYkdsamQyVmlMM04wWVhSMWN5OXYKWTNOd01Cc0dDaXNHQVFRQmdxZzdBd29FRFJNTFRVRllTVUZWVkU4Z1UwRXdIUVlLS3dZQkJBR0NxRHNEQ3dRUEV3MHhOemt4TWpZeApNVFV4TURBeE1Cb0dDaXNHQVFRQmdxZzdBd0VFREJNS01UY3lNVFF5TXpNeE5UQWFCZ29yQmdFRUFZS29Pd01DQkF3VENrbFdRVTRnClJFRlNTVTh3RkFZS0t3WUJCQUdDcURzREF3UUdFd1JFU1VGYU1CY0dDaXNHQVFRQmdxZzdBd1FFQ1JNSFFVZFZTVkpTUlRBYUJnb3IKQmdFRUFZS29Pd01GQkF3VENsTlZRa2RGVWtWT1ZFVXdOd1lLS3dZQkJBR0NxRHNEQndRcEV5ZEZURWxCVXlCSFQwUlBXU0JDVDBSRgpSMEVnVGs4Z015QlpJRVZOU1V4SlR5QlBRa0ZPUkU4d0dRWUtLd1lCQkFHQ3FEc0RDQVFMRXdrd01qSXdNak0yT1RNd0ZRWUtLd1lCCkJBR0NxRHNEQ1FRSEV3VlJkV2wwYnpBWEJnb3JCZ0VFQVlLb093TU1CQWtUQjBWRFZVRkVUMUl3SUFZS0t3WUJCQUdDcURzRE13UVMKRXhCVFQwWlVWMEZTUlMxQlVrTklTVlpQTUNZR0ExVWRFUVFmTUIyQkczTjFZbWRsY21WdVkybGhRRzFoZUdsaGRYUnZMbU52YlM1bApZekNDQWQ4R0ExVWRId1NDQWRZd2dnSFNNSUlCenFDQ0FjcWdnZ0hHaG9IVmJHUmhjRG92TDJKalpYRnNaR0Z3YzNWaWNERXVZbU5sCkxtVmpMMk51UFVOU1RESTRNU3hqYmoxQlF5VXlNRUpCVGtOUEpUSXdRMFZPVkZKQlRDVXlNRVJGVENVeU1FVkRWVUZFVDFJc2JEMVIKVlVsVVR5eHZkVDFGVGxSSlJFRkVKVEl3UkVVbE1qQkRSVkpVU1VaSlEwRkRTVTlPSlRJd1JFVWxNakJKVGtaUFVrMUJRMGxQVGkxRgpRMGxDUTBVc2J6MUNRVTVEVHlVeU1FTkZUbFJTUVV3bE1qQkVSVXdsTWpCRlExVkJSRTlTTEdNOVJVTS9ZMlZ5ZEdsbWFXTmhkR1ZTClpYWnZZMkYwYVc5dVRHbHpkRDlpWVhObGhqUm9kSFJ3T2k4dmQzZDNMbVZqYVM1aVkyVXVaV012UTFKTUwyVmphVjlpWTJWZlpXTmYKWTNKc1ptbHNaV052YldJdVkzSnNwSUcxTUlHeU1Rc3dDUVlEVlFRR0V3SkZRekVpTUNBR0ExVUVDaE1aUWtGT1EwOGdRMFZPVkZKQgpUQ0JFUlV3Z1JVTlZRVVJQVWpFM01EVUdBMVVFQ3hNdVJVNVVTVVJCUkNCRVJTQkRSVkpVU1VaSlEwRkRTVTlPSUVSRklFbE9SazlTClRVRkRTVTlPTFVWRFNVSkRSVEVPTUF3R0ExVUVCeE1GVVZWSlZFOHhKVEFqQmdOVkJBTVRIRUZESUVKQlRrTlBJRU5GVGxSU1FVd2cKUkVWTUlFVkRWVUZFVDFJeER6QU5CZ05WQkFNVEJrTlNUREk0TVRBTEJnTlZIUThFQkFNQ0JTQXdId1lEVlIwakJCZ3dGb0FVR1BudworK1l5SEpsbU9TcktpN0pwZlVrbnY4NHdIUVlEVlIwT0JCWUVGRFovNFpzNG9DWmFsOEtIZmJyV2lOTGRjNTZUTUFrR0ExVWRFd1FDCk1BQXdHUVlKS29aSWh2WjlCMEVBQkF3d0Noc0VWamd1TVFNQ0JKQXdEUVlKS29aSWh2Y05BUUVMQlFBRGdnSUJBSkRwOTFNRitDU0oKM05FUUk1TVBpMFJqSUdYbzV3eEJlNVVSMldOR0puTFVyRERacnAzdWovOGxyQ3RWcXZMcGJkNHFHaXRrd1VRcll3eGowdjlSK3ZpMwpaRTlzTDNBK3pRU090QU4zZFQ5S1JLdWxuVXpIOWdFV2V4M3JZN2xrekt6bUZxc3lnNFYxQ1RxZnpnQ1FEYXNGc2xBbHdVUEJ2Umg0Ck95RGlMR2wvZDVoN1c4SGlQUld2TWxxQUp0ZDlOWUtZRlZLZ010M3V0a2FqWDl4L1IrZVhsMEpOMUFtZVBzVlV1ZmxmbG5hdmNES20KSTRSNWhoNWwvTUY1ZEVYZ1p4bjN2a0VodWZCdkY5bVlJVlFVL0xrNGFQS0I5dXdWTW1vVms2eFl4R1NkMUhWYytJWTQvejJtTW1NNQpzMzYvQU8yQWpjdklTL2paVTFUUTVUeVNZajM1TzdSQWRaQjVNVGhVU1hNaDFRb1dBMjd4T3YwQ0paSkJjY1Fhb0V0K2FvOG9QUW9vCnN2emtuQUlEZm43M3FaQ2o2VzJsUStCS0Y3dm9kSXZCc1VQcjNTV3RLYmMwdTd2RDZSQUIyaXdPTENVWnNKelpTdzhDU0tqVVJzL1EKSXRWSVA1TlpUU242SVVFemRTRVN6Zm9SVEtqV2lVVGk4MEpOcHJGeFB1NWVBTmZpNytDNzZvQTVKNzZRSDJ0elFSRlM3d0xsb0QrWQpJMFRrcnRuR3NlcGhkR1F5cHliRUEyWUZjeVE0ZENPZzJrNWtHc3Q2b1VJWjNMZ21IL2Nhd0trTHRhblptdXVtcTNxZlF3aDlKQ1FmCjk5QXd5MlUxSitieWVKTDYwMlRhRVhCanpPakd5a3p2SFozdThNTHMyVWowVFhFOERhb01wUGNRVWxqekVGNVoKPC9kczpYNTA5Q2VydGlmaWNhdGU+CjwvZHM6WDUwOURhdGE+CjxkczpLZXlWYWx1ZT4KPGRzOlJTQUtleVZhbHVlPgo8ZHM6TW9kdWx1cz4KeUFuZ0daaHdQWFhJTmcxcFloTzhkbWZvdnovcmdENUY0aFNXbUVNNVU4c0RWd0lMZ0d4MUlucjNhK1RQZGozYi84aWloaGFxdE9kVgpUbTgvanpUR2xlWHZRUzNLTEw1S3pMczIzVTZFb3kxTUhSRFJtYnY1WjZtcmFSelVEN2tmbzh4amxrc2Z2Qm5RQzUxdkRaMzFOUjdpCmNneFNQQi9Zcjg4MlMrMEdhSkxUZk5ja2Nya2U1SnYvS1lVZDNvK0xIakxTTngybXVOK2ZQU0ZHTnNjaGpoYTJqTEs5c3Z5eGs5TU0KVUFsZDR3OCtzemhodnZkNEJzOVZRc004N015K2pUMTk1YkJDWm9IdzQ2T0ZEVWlIaWQ2YTY1eW9xVGUzWCt5eW9WQ3BhRkhSaEFaWApkODhWdjJoM0RDcjVJK3ZlWVYwSlZFd1cwbDEveDhuZnJ1YjJWdz09CjwvZHM6TW9kdWx1cz4KPGRzOkV4cG9uZW50PkFRQUI8L2RzOkV4cG9uZW50Pgo8L2RzOlJTQUtleVZhbHVlPgo8L2RzOktleVZhbHVlPgo8L2RzOktleUluZm8+CjxkczpPYmplY3QgSWQ9IlNpZ25hdHVyZTQ1OTQ1OC1PYmplY3QzOTgxMjgiPjxldHNpOlF1YWxpZnlpbmdQcm9wZXJ0aWVzIFRhcmdldD0iI1NpZ25hdHVyZTQ1OTQ1OCI+PGV0c2k6U2lnbmVkUHJvcGVydGllcyBJZD0iU2lnbmF0dXJlNDU5NDU4LVNpZ25lZFByb3BlcnRpZXM4NTgwNjUiPjxldHNpOlNpZ25lZFNpZ25hdHVyZVByb3BlcnRpZXM+PGV0c2k6U2lnbmluZ1RpbWU+MjAxOC0xMS0xOVQxMTowMjozMi0wNTowMDwvZXRzaTpTaWduaW5nVGltZT48ZXRzaTpTaWduaW5nQ2VydGlmaWNhdGU+PGV0c2k6Q2VydD48ZXRzaTpDZXJ0RGlnZXN0PjxkczpEaWdlc3RNZXRob2QgQWxnb3JpdGhtPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwLzA5L3htbGRzaWcjc2hhMSI+PC9kczpEaWdlc3RNZXRob2Q+PGRzOkRpZ2VzdFZhbHVlPmZlMWRrMXRQbkhDaGZ3VkR5RkxPejlvbUFQRT08L2RzOkRpZ2VzdFZhbHVlPjwvZXRzaTpDZXJ0RGlnZXN0PjxldHNpOklzc3VlclNlcmlhbD48ZHM6WDUwOUlzc3Vlck5hbWU+Q049QUMgQkFOQ08gQ0VOVFJBTCBERUwgRUNVQURPUixMPVFVSVRPLE9VPUVOVElEQUQgREUgQ0VSVElGSUNBQ0lPTiBERSBJTkZPUk1BQ0lPTi1FQ0lCQ0UsTz1CQU5DTyBDRU5UUkFMIERFTCBFQ1VBRE9SLEM9RUM8L2RzOlg1MDlJc3N1ZXJOYW1lPjxkczpYNTA5U2VyaWFsTnVtYmVyPjEzMTMxODU0NjY8L2RzOlg1MDlTZXJpYWxOdW1iZXI+PC9ldHNpOklzc3VlclNlcmlhbD48L2V0c2k6Q2VydD48L2V0c2k6U2lnbmluZ0NlcnRpZmljYXRlPjwvZXRzaTpTaWduZWRTaWduYXR1cmVQcm9wZXJ0aWVzPjxldHNpOlNpZ25lZERhdGFPYmplY3RQcm9wZXJ0aWVzPjxldHNpOkRhdGFPYmplY3RGb3JtYXQgT2JqZWN0UmVmZXJlbmNlPSIjUmVmZXJlbmNlLUlELTUxNDA0Ij48ZXRzaTpEZXNjcmlwdGlvbj5Eb2N1bWVudG8gZGUgZWplbXBsbzwvZXRzaTpEZXNjcmlwdGlvbj48ZXRzaTpNaW1lVHlwZT50ZXh0L3htbDwvZXRzaTpNaW1lVHlwZT48L2V0c2k6RGF0YU9iamVjdEZvcm1hdD48L2V0c2k6U2lnbmVkRGF0YU9iamVjdFByb3BlcnRpZXM+PC9ldHNpOlNpZ25lZFByb3BlcnRpZXM+PC9ldHNpOlF1YWxpZnlpbmdQcm9wZXJ0aWVzPjwvZHM6T2JqZWN0PjwvZHM6U2lnbmF0dXJlPjwvZmFjdHVyYT4="
}
```

### POST /api/v1/sri/enviar/factura
Example: http://localhost:8080/veronica/api/v1/sri/enviar/factura

Request body:
```
{
   "rutaArchivoPkcs12":"D:\\Privado\\certificado.p12",
   "claveArchivopkcs12":"MAte2910",
   "comprobanteAsObj":{
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
         "secuencial":"000000005",
         "dirMatriz":"Enrique Guerrero Portilla OE1-34 AV. Galo Plaza Lasso"
      },
      "infoFactura":{
         "fechaEmision":"19/11/2018",
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
}
```
Response body:
```
{
    "estado": "RECIBIDA",
    "claveAcceso": "1911201801179214673900110010020000000053325722518",
    "comprobantes": []
}
```

### POST /api/v1/sri/autorizar
Example: http://localhost:8080/veronica/api/v1/sri/autorizar

Request body:
```
{
  "claveAcceso": "1911201801179214673900110010020000000053325722518"
}
```
Response body:
```
{
    "claveAccesoConsultada": "1911201801179214673900110010020000000053325722518",
    "numeroComprobantes": "1",
    "autorizaciones": [
        {
            "estado": "NO AUTORIZADO",
            "numeroAutorizacion": null,
            "fechaAutorizacion": "19/11/2018 12:25:18",
            "ambiente": "PRUEBAS",
            "comprobante": "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<factura id=\"comprobante\" version=\"1.0.0\">\n    <infoTributaria>\n        <ambiente>1</ambiente>\n        <tipoEmision>1</tipoEmision>\n        <razonSocial>Distribuidora de Suministros Nacional S.A.</razonSocial>\n        <nombreComercial>Empresa Importadora y Exportadora de Piezas</nombreComercial>\n        <ruc>1792146739001</ruc>\n        <claveAcceso>1911201801179214673900110010020000000053325722518</claveAcceso>\n        <codDoc>01</codDoc>\n        <estab>002</estab>\n        <ptoEmi>001</ptoEmi>\n        <secuencial>000000005</secuencial>\n        <dirMatriz>Enrique Guerrero Portilla OE1-34 AV. Galo Plaza Lasso</dirMatriz>\n    </infoTributaria>\n    <infoFactura>\n        <fechaEmision>19/11/2018</fechaEmision>\n        <dirEstablecimiento>Sebastián Moreno S/N Francisco García</dirEstablecimiento>\n        <contribuyenteEspecial>5368</contribuyenteEspecial>\n        <obligadoContabilidad>SI</obligadoContabilidad>\n        <tipoIdentificacionComprador>04</tipoIdentificacionComprador>\n        <guiaRemision>001-001-000000001</guiaRemision>\n        <razonSocialComprador>PRUEBAS SERVICIO DE RENTAS INTERNAS</razonSocialComprador>\n        <identificacionComprador>1713328506001</identificacionComprador>\n        <direccionComprador>salinas y santiago</direccionComprador>\n        <totalSinImpuestos>295000.00</totalSinImpuestos>\n        <totalDescuento>5005.00</totalDescuento>\n        <totalConImpuestos>\n            <totalImpuesto>\n                <codigo>3</codigo>\n                <codigoPorcentaje>3072</codigoPorcentaje>\n                <baseImponible>295000.00</baseImponible>\n                <valor>14750.00</valor>\n            </totalImpuesto>\n            <totalImpuesto>\n                <codigo>2</codigo>\n                <codigoPorcentaje>2</codigoPorcentaje>\n                <baseImponible>309750.00</baseImponible>\n                <valor>37169.40</valor>\n            </totalImpuesto>\n            <totalImpuesto>\n                <codigo>5</codigo>\n                <codigoPorcentaje>5001</codigoPorcentaje>\n                <baseImponible>12000.00</baseImponible>\n                <valor>240.00</valor>\n            </totalImpuesto>\n        </totalConImpuestos>\n        <propina>0</propina>\n        <importeTotal>347159.40</importeTotal>\n        <moneda>DOLAR</moneda>\n        <pagos>\n            <pago>\n                <formaPago>01</formaPago>\n                <total>347159.40</total>\n                <plazo>30</plazo>\n                <unidadTiempo>dias</unidadTiempo>\n            </pago>\n        </pagos>\n        <valorRetIva>10620.00</valorRetIva>\n        <valorRetRenta>2950.00</valorRetRenta>\n    </infoFactura>\n    <detalles>\n        <detalle>\n            <codigoPrincipal>125BJC-01</codigoPrincipal>\n            <codigoAuxiliar>1234D56789-A</codigoAuxiliar>\n            <descripcion>CAMIONETA 4X4 DIESEL 3.7</descripcion>\n            <cantidad>10.00</cantidad>\n            <precioUnitario>300000.00</precioUnitario>\n            <descuento>5000.00</descuento>\n            <precioTotalSinImpuesto>295000.00</precioTotalSinImpuesto>\n            <detallesAdicionales>\n                <detAdicional nombre=\"Marca Chevrolet\" valor=\"Chevrolet\"></detAdicional>\n                <detAdicional nombre=\"Modelo\" valor=\"2012\"></detAdicional>\n                <detAdicional nombre=\"Chasis\" valor=\"8LDETA03V20003289\"></detAdicional>\n            </detallesAdicionales>\n            <impuestos>\n                <impuesto>\n                    <codigo>3</codigo>\n                    <codigoPorcentaje>3072</codigoPorcentaje>\n                    <tarifa>5</tarifa>\n                    <baseImponible>295000.00</baseImponible>\n                    <valor>14750.00</valor>\n                </impuesto>\n                <impuesto>\n                    <codigo>2</codigo>\n                    <codigoPorcentaje>2</codigoPorcentaje>\n                    <tarifa>12</tarifa>\n                    <baseImponible>309750.00</baseImponible>\n                    <valor>37170.00</valor>\n                </impuesto>\n                <impuesto>\n                    <codigo>5</codigo>\n                    <codigoPorcentaje>5001</codigoPorcentaje>\n                    <tarifa>0.02</tarifa>\n                    <baseImponible>12000.00</baseImponible>\n                    <valor>240.00</valor>\n                </impuesto>\n            </impuestos>\n        </detalle>\n    </detalles>\n    <infoAdicional>\n        <campoAdicional nombre=\"Codigo Impuesto ISD\">4580</campoAdicional>\n        <campoAdicional nombre=\"Impuesto ISD\">15.42x</campoAdicional>\n    </infoAdicional>\n<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:etsi=\"http://uri.etsi.org/01903/v1.3.2#\" Id=\"Signature582961\">\n<ds:SignedInfo Id=\"Signature-SignedInfo927546\">\n<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></ds:CanonicalizationMethod>\n<ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></ds:SignatureMethod>\n<ds:Reference Id=\"SignedPropertiesID1048147\" Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#Signature582961-SignedProperties840558\">\n<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n<ds:DigestValue>pbFso9W7Y9rpLWWc4aliF6teDkQ=</ds:DigestValue>\n</ds:Reference>\n<ds:Reference URI=\"#Certificate1961370\">\n<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n<ds:DigestValue>/BbDtmptaI55ZlxCgyfiK0m79AM=</ds:DigestValue>\n</ds:Reference>\n<ds:Reference Id=\"Reference-ID-604083\" URI=\"#comprobante\">\n<ds:Transforms>\n<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></ds:Transform>\n</ds:Transforms>\n<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n<ds:DigestValue>8z+KDR0qqpoqWHWCuJ/XWsh1or4=</ds:DigestValue>\n</ds:Reference>\n</ds:SignedInfo>\n<ds:SignatureValue Id=\"SignatureValue1015583\">\nG55EHH4iPxy+52HNQ0bV2fm21Xmwj1q7oPLAudLaZjTDyUrfYUnm/9aFSgwr1dO1/6Q55EANdo3y\ncScMytTKPK4o2WQK3++Axt4NVKTRtYsMrLDRe8ykBaVMvn7z+4DjuhQZQS8MegxvyGV8TOnfX0kO\npGMyhFLDfpzqlC8MrxyqZW7++BB8Ntwg4B+IkwcAc1c2MyxN5mOVIwnbwnqIsKTIbEfInnDA4WxP\nJR7VyYGgytRo7LF6HKzZ/BmVc0l6lW5vRiXpHbgW6EX+hERmGCnOBhNoJfwzm2DsH3XeATYLWR/x\nh+VPz0EhVXypuEOQEWFhEs3ehCl72FJlDWQvBw==\n</ds:SignatureValue>\n<ds:KeyInfo Id=\"Certificate1961370\">\n<ds:X509Data>\n<ds:X509Certificate>\nMIIJTTCCBzWgAwIBAgIETkWeujANBgkqhkiG9w0BAQsFADCBoTELMAkGA1UEBhMCRUMxIjAgBgNV\nBAoTGUJBTkNPIENFTlRSQUwgREVMIEVDVUFET1IxNzA1BgNVBAsTLkVOVElEQUQgREUgQ0VSVElG\nSUNBQ0lPTiBERSBJTkZPUk1BQ0lPTi1FQ0lCQ0UxDjAMBgNVBAcTBVFVSVRPMSUwIwYDVQQDExxB\nQyBCQU5DTyBDRU5UUkFMIERFTCBFQ1VBRE9SMB4XDTE1MTIwODIwNTIzOFoXDTE3MTIwODIxMjIz\nOFowga8xCzAJBgNVBAYTAkVDMSIwIAYDVQQKExlCQU5DTyBDRU5UUkFMIERFTCBFQ1VBRE9SMTcw\nNQYDVQQLEy5FTlRJREFEIERFIENFUlRJRklDQUNJT04gREUgSU5GT1JNQUNJT04tRUNJQkNFMQ4w\nDAYDVQQHEwVRVUlUTzEzMBEGA1UEBRMKMDAwMDA5Mzc3NDAeBgNVBAMTF0lWQU4gREFSSU8gRElB\nWiBBR1VJUlJFMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyAngGZhwPXXINg1pYhO8\ndmfovz/rgD5F4hSWmEM5U8sDVwILgGx1Inr3a+TPdj3b/8iihhaqtOdVTm8/jzTGleXvQS3KLL5K\nzLs23U6Eoy1MHRDRmbv5Z6mraRzUD7kfo8xjlksfvBnQC51vDZ31NR7icgxSPB/Yr882S+0GaJLT\nfNckcrke5Jv/KYUd3o+LHjLSNx2muN+fPSFGNschjha2jLK9svyxk9MMUAld4w8+szhhvvd4Bs9V\nQsM87My+jT195bBCZoHw46OFDUiHid6a65yoqTe3X+yyoVCpaFHRhAZXd88Vv2h3DCr5I+veYV0J\nVEwW0l1/x8nfrub2VwIDAQABo4IEezCCBHcwgZEGCCsGAQUFBwEBBIGEMIGBMD4GCCsGAQUFBzAB\nhjJodHRwOi8vb2NzcC5lY2kuYmNlLmVjL2VqYmNhL3B1YmxpY3dlYi9zdGF0dXMvb2NzcDA/Bggr\nBgEFBQcwAYYzaHR0cDovL29jc3AxLmVjaS5iY2UuZWMvZWpiY2EvcHVibGljd2ViL3N0YXR1cy9v\nY3NwMBsGCisGAQQBgqg7AwoEDRMLTUFYSUFVVE8gU0EwHQYKKwYBBAGCqDsDCwQPEw0xNzkxMjYx\nMTUxMDAxMBoGCisGAQQBgqg7AwEEDBMKMTcyMTQyMzMxNTAaBgorBgEEAYKoOwMCBAwTCklWQU4g\nREFSSU8wFAYKKwYBBAGCqDsDAwQGEwRESUFaMBcGCisGAQQBgqg7AwQECRMHQUdVSVJSRTAaBgor\nBgEEAYKoOwMFBAwTClNVQkdFUkVOVEUwNwYKKwYBBAGCqDsDBwQpEydFTElBUyBHT0RPWSBCT0RF\nR0EgTk8gMyBZIEVNSUxJTyBPQkFORE8wGQYKKwYBBAGCqDsDCAQLEwkwMjIwMjM2OTMwFQYKKwYB\nBAGCqDsDCQQHEwVRdWl0bzAXBgorBgEEAYKoOwMMBAkTB0VDVUFET1IwIAYKKwYBBAGCqDsDMwQS\nExBTT0ZUV0FSRS1BUkNISVZPMCYGA1UdEQQfMB2BG3N1YmdlcmVuY2lhQG1heGlhdXRvLmNvbS5l\nYzCCAd8GA1UdHwSCAdYwggHSMIIBzqCCAcqgggHGhoHVbGRhcDovL2JjZXFsZGFwc3VicDEuYmNl\nLmVjL2NuPUNSTDI4MSxjbj1BQyUyMEJBTkNPJTIwQ0VOVFJBTCUyMERFTCUyMEVDVUFET1IsbD1R\nVUlUTyxvdT1FTlRJREFEJTIwREUlMjBDRVJUSUZJQ0FDSU9OJTIwREUlMjBJTkZPUk1BQ0lPTi1F\nQ0lCQ0Usbz1CQU5DTyUyMENFTlRSQUwlMjBERUwlMjBFQ1VBRE9SLGM9RUM/Y2VydGlmaWNhdGVS\nZXZvY2F0aW9uTGlzdD9iYXNlhjRodHRwOi8vd3d3LmVjaS5iY2UuZWMvQ1JML2VjaV9iY2VfZWNf\nY3JsZmlsZWNvbWIuY3JspIG1MIGyMQswCQYDVQQGEwJFQzEiMCAGA1UEChMZQkFOQ08gQ0VOVFJB\nTCBERUwgRUNVQURPUjE3MDUGA1UECxMuRU5USURBRCBERSBDRVJUSUZJQ0FDSU9OIERFIElORk9S\nTUFDSU9OLUVDSUJDRTEOMAwGA1UEBxMFUVVJVE8xJTAjBgNVBAMTHEFDIEJBTkNPIENFTlRSQUwg\nREVMIEVDVUFET1IxDzANBgNVBAMTBkNSTDI4MTALBgNVHQ8EBAMCBSAwHwYDVR0jBBgwFoAUGPnw\n++YyHJlmOSrKi7JpfUknv84wHQYDVR0OBBYEFDZ/4Zs4oCZal8KHfbrWiNLdc56TMAkGA1UdEwQC\nMAAwGQYJKoZIhvZ9B0EABAwwChsEVjguMQMCBJAwDQYJKoZIhvcNAQELBQADggIBAJDp91MF+CSJ\n3NEQI5MPi0RjIGXo5wxBe5UR2WNGJnLUrDDZrp3uj/8lrCtVqvLpbd4qGitkwUQrYwxj0v9R+vi3\nZE9sL3A+zQSOtAN3dT9KRKulnUzH9gEWex3rY7lkzKzmFqsyg4V1CTqfzgCQDasFslAlwUPBvRh4\nOyDiLGl/d5h7W8HiPRWvMlqAJtd9NYKYFVKgMt3utkajX9x/R+eXl0JN1AmePsVUuflflnavcDKm\nI4R5hh5l/MF5dEXgZxn3vkEhufBvF9mYIVQU/Lk4aPKB9uwVMmoVk6xYxGSd1HVc+IY4/z2mMmM5\ns36/AO2AjcvIS/jZU1TQ5TySYj35O7RAdZB5MThUSXMh1QoWA27xOv0CJZJBccQaoEt+ao8oPQoo\nsvzknAIDfn73qZCj6W2lQ+BKF7vodIvBsUPr3SWtKbc0u7vD6RAB2iwOLCUZsJzZSw8CSKjURs/Q\nItVIP5NZTSn6IUEzdSESzfoRTKjWiUTi80JNprFxPu5eANfi7+C76oA5J76QH2tzQRFS7wLloD+Y\nI0TkrtnGsephdGQypybEA2YFcyQ4dCOg2k5kGst6oUIZ3LgmH/cawKkLtanZmuumq3qfQwh9JCQf\n99Awy2U1J+byeJL602TaEXBjzOjGykzvHZ3u8MLs2Uj0TXE8DaoMpPcQUljzEF5Z\n</ds:X509Certificate>\n</ds:X509Data>\n<ds:KeyValue>\n<ds:RSAKeyValue>\n<ds:Modulus>\nyAngGZhwPXXINg1pYhO8dmfovz/rgD5F4hSWmEM5U8sDVwILgGx1Inr3a+TPdj3b/8iihhaqtOdV\nTm8/jzTGleXvQS3KLL5KzLs23U6Eoy1MHRDRmbv5Z6mraRzUD7kfo8xjlksfvBnQC51vDZ31NR7i\ncgxSPB/Yr882S+0GaJLTfNckcrke5Jv/KYUd3o+LHjLSNx2muN+fPSFGNschjha2jLK9svyxk9MM\nUAld4w8+szhhvvd4Bs9VQsM87My+jT195bBCZoHw46OFDUiHid6a65yoqTe3X+yyoVCpaFHRhAZX\nd88Vv2h3DCr5I+veYV0JVEwW0l1/x8nfrub2Vw==\n</ds:Modulus>\n<ds:Exponent>AQAB</ds:Exponent>\n</ds:RSAKeyValue>\n</ds:KeyValue>\n</ds:KeyInfo>\n<ds:Object Id=\"Signature582961-Object494299\"><etsi:QualifyingProperties Target=\"#Signature582961\"><etsi:SignedProperties Id=\"Signature582961-SignedProperties840558\"><etsi:SignedSignatureProperties><etsi:SigningTime>2018-11-19T12:25:03-05:00</etsi:SigningTime><etsi:SigningCertificate><etsi:Cert><etsi:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod><ds:DigestValue>fe1dk1tPnHChfwVDyFLOz9omAPE=</ds:DigestValue></etsi:CertDigest><etsi:IssuerSerial><ds:X509IssuerName>CN=AC BANCO CENTRAL DEL ECUADOR,L=QUITO,OU=ENTIDAD DE CERTIFICACION DE INFORMACION-ECIBCE,O=BANCO CENTRAL DEL ECUADOR,C=EC</ds:X509IssuerName><ds:X509SerialNumber>1313185466</ds:X509SerialNumber></etsi:IssuerSerial></etsi:Cert></etsi:SigningCertificate></etsi:SignedSignatureProperties><etsi:SignedDataObjectProperties><etsi:DataObjectFormat ObjectReference=\"#Reference-ID-604083\"><etsi:Description>Documento de ejemplo</etsi:Description><etsi:MimeType>text/xml</etsi:MimeType></etsi:DataObjectFormat></etsi:SignedDataObjectProperties></etsi:SignedProperties></etsi:QualifyingProperties></ds:Object></ds:Signature></factura>",
            "mensajes": [
                {
                    "identificador": "56",
                    "mensaje": "ERROR ESTABLECIMIENTO CERRADO",
                    "informacionAdicional": "El establecimiento 002 está cerrado",
                    "tipo": "ERROR"
                }
            ]
        }
    ]
}
```

## Documentation history

- V1: 2018-04-12, first draft.
- V2: 2018-04-27, enable maven profiles.
- V3: 2018-04-28, enable swagger2 for api documentation.
- V4: 2018-11-10, Invoice RIDE generation.
- V5: 2018-11-19, Postman collection.

## Authors

| [![](https://avatars1.githubusercontent.com/u/11875482?v=4&s=80)](https://github.com/rolandopalermo) |
|-|
| [@rolandopalermo](https://github.com/rolandopalermo) |
