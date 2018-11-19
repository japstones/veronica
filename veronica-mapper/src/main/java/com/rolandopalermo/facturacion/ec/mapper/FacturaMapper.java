package com.rolandopalermo.facturacion.ec.mapper;

import com.rolandopalermo.facturacion.ec.common.sri.ClaveDeAcceso;
import com.rolandopalermo.facturacion.ec.common.util.DateUtils;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.DetAdicionalDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.FacturaDetalleDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.ImpuestoDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.PagoDTO;
import com.rolandopalermo.facturacion.ec.dto.comprobantes.TotalImpuestoDTO;
import com.rolandopalermo.facturacion.ec.modelo.DetAdicional;
import com.rolandopalermo.facturacion.ec.modelo.Impuesto;
import com.rolandopalermo.facturacion.ec.modelo.InfoTributaria;
import com.rolandopalermo.facturacion.ec.modelo.factura.Factura;
import com.rolandopalermo.facturacion.ec.modelo.factura.FacturaDetalle;
import com.rolandopalermo.facturacion.ec.modelo.factura.InfoFactura;
import com.rolandopalermo.facturacion.ec.modelo.factura.Pago;
import com.rolandopalermo.facturacion.ec.modelo.factura.TotalImpuesto;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FacturaMapper extends AbstractComprobanteMapper<FacturaDTO, Factura> {

    private static final Logger logger = Logger.getLogger(FacturaMapper.class);

    public Factura toModel(FacturaDTO facturaDTO) {
        InfoTributaria infoTributaria = buildInfoTributaria(facturaDTO);
        Factura factura = new Factura();
        factura.setCampoAdicional(buildCamposAdicionales(facturaDTO));
        factura.setId(facturaDTO.getId());
        factura.setVersion(facturaDTO.getVersion());

        //Procesar lista de impuestos totales
        List<TotalImpuestoDTO> totalImpuestosDTO = facturaDTO.getInfoFactura().getTotalImpuesto();
        List<TotalImpuesto> totalImpuestos = totalImpuestosDTO.stream()
                .map(totalImpuestoDTO -> {
                    TotalImpuesto totalImpuesto = new TotalImpuesto();
                    totalImpuesto.setCodigo(totalImpuestoDTO.getCodigo());
                    totalImpuesto.setCodigoPorcentaje(totalImpuestoDTO.getCodigoPorcentaje());
                    totalImpuesto.setBaseImponible(totalImpuestoDTO.getBaseImponible());
                    totalImpuesto.setValor(totalImpuestoDTO.getValor());
                    return totalImpuesto;
                })
                .collect(Collectors.toList());
        //Procesar lista de pagos
        List<PagoDTO> pagosDTO = facturaDTO.getInfoFactura().getPagos();
        List<Pago> pagos = pagosDTO.stream()
                .map(pagoDTO -> {
                    Pago pago = new Pago();
                    pago.setFormaPago(pagoDTO.getFormaPago());
                    pago.setTotal(pagoDTO.getTotal());
                    pago.setPlazo(pagoDTO.getPlazo());
                    pago.setUnidadTiempo(pagoDTO.getUnidadTiempo());
                    return pago;
                })
                .collect(Collectors.toList());
        //Procesar detalles de factura
        List<FacturaDetalleDTO> detallesDTO = facturaDTO.getDetalle();
        List<FacturaDetalle> detalles = detallesDTO.stream()
                .map(detalle -> {
                    List<DetAdicionalDTO> detallesAdicionalesDTO = detalle.getDetAdicional();
                    List<DetAdicional> detallesAdicionales = detallesAdicionalesDTO.stream()
                            .map(detAdicionalDTO -> {
                                DetAdicional detAdicional = new DetAdicional();
                                detAdicional.setNombre(detAdicionalDTO.getNombre());
                                detAdicional.setValor(detAdicionalDTO.getValor());
                                return detAdicional;
                            })
                            .collect(Collectors.toList());
                    List<ImpuestoDTO> impuestosDTO = detalle.getImpuesto();
                    List<com.rolandopalermo.facturacion.ec.modelo.Impuesto> impuestos = impuestosDTO.stream()
                            .map(impuestoDTO -> {
                                Impuesto impuesto = new Impuesto();
                                impuesto.setCodigo(impuestoDTO.getCodigo());
                                impuesto.setCodigoPorcentaje(impuestoDTO.getCodigoPorcentaje());
                                impuesto.setTarifa(impuestoDTO.getTarifa());
                                impuesto.setBaseImponible(impuestoDTO.getBaseImponible());
                                impuesto.setValor(impuestoDTO.getValor());
                                return impuesto;
                            })
                            .collect(Collectors.toList());
                    FacturaDetalle facturaDetalle = new FacturaDetalle();
                    facturaDetalle.setCodigoPrincipal(detalle.getCodigoPrincipal());
                    facturaDetalle.setCodigoAuxiliar(detalle.getCodigoAuxiliar());
                    facturaDetalle.setDescripcion(detalle.getDescripcion());
                    facturaDetalle.setCantidad(detalle.getCantidad());
                    facturaDetalle.setPrecioUnitario(detalle.getPrecioUnitario());
                    facturaDetalle.setDescuento(detalle.getDescuento());
                    facturaDetalle.setPrecioTotalSinImpuesto(detalle.getPrecioTotalSinImpuesto());
                    facturaDetalle.setDetAdicional(detallesAdicionales);
                    facturaDetalle.setImpuesto(impuestos);
                    return facturaDetalle;
                })
                .collect(Collectors.toList());

        InfoFactura infoFactura = new InfoFactura();
        infoFactura.setFechaEmision(facturaDTO.getInfoFactura().getFechaEmision());
        infoFactura.setDirEstablecimiento(facturaDTO.getInfoFactura().getDirEstablecimiento());
        infoFactura.setContribuyenteEspecial(facturaDTO.getInfoFactura().getContribuyenteEspecial());
        infoFactura.setObligadoContabilidad(facturaDTO.getInfoFactura().getObligadoContabilidad());
        infoFactura.setTipoIdentificacionComprador(facturaDTO.getInfoFactura().getTipoIdentificacionComprador());
        infoFactura.setGuiaRemision(facturaDTO.getInfoFactura().getGuiaRemision());
        infoFactura.setRazonSocialComprador(facturaDTO.getInfoFactura().getRazonSocialComprador());
        infoFactura.setIdentificacionComprador(facturaDTO.getInfoFactura().getIdentificacionComprador());
        infoFactura.setDireccionComprador(facturaDTO.getInfoFactura().getDireccionComprador());
        infoFactura.setTotalSinImpuestos(facturaDTO.getInfoFactura().getTotalSinImpuestos());
        infoFactura.setTotalDescuento(facturaDTO.getInfoFactura().getTotalDescuento());
        infoFactura.setTotalImpuesto(totalImpuestos);
        infoFactura.setPropina(facturaDTO.getInfoFactura().getPropina());
        infoFactura.setImporteTotal(facturaDTO.getInfoFactura().getImporteTotal());
        infoFactura.setMoneda(facturaDTO.getInfoFactura().getMoneda());
        infoFactura.setPago(pagos);
        infoFactura.setValorRetIva(facturaDTO.getInfoFactura().getValorRetIva());
        infoFactura.setValorRetRenta(facturaDTO.getInfoFactura().getValorRetRenta());
        factura.setInfoFactura(infoFactura);

        factura.setDetalle(detalles);

        StringBuilder sb = new StringBuilder(infoTributaria.getPtoEmi());
        sb.append(infoTributaria.getEstab());
        String serie = sb.toString();
        String codigoNumerico = RandomStringUtils.randomNumeric(8);
        String claveAcceso = "";
        try {
            claveAcceso = ClaveDeAcceso.builder()
                    .fechaEmision(DateUtils.getFechaFromStringddMMyyyy(infoFactura.getFechaEmision()))
                    .ambiente(infoTributaria.getAmbiente())
                    .codigoNumerico(codigoNumerico)
                    .numeroComprobante(infoTributaria.getSecuencial())
                    .ruc(infoTributaria.getRuc())
                    .serie(serie)
                    .tipoComprobante(infoTributaria.getCodDoc())
                    .tipoEmision(infoTributaria.getTipoEmision())
                    .build()
                    .generarClaveAcceso();
        } catch (ParseException e) {
            logger.error("FacturaMapper", e);
        }
        infoTributaria.setClaveAcceso(claveAcceso);
        factura.setInfoTributaria(infoTributaria);
        return factura;
    }
}
