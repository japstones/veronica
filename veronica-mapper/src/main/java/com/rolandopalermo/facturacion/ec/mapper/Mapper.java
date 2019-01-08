package com.rolandopalermo.facturacion.ec.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Inteface Mapper.
 *
 * @param <SOURCE> Origen
 * @param <TARGET> Destino
 */
public interface Mapper<SOURCE, TARGET> {

    /**
     * Convierte un objeto.
     *
     * @param source Objeto origen
     * @return Objeto resultado.
     */
    TARGET convert(SOURCE source);

    /**
     * Convierte cada objeto de la lista.
     *
     * @param sources
     * @return
     */
    default List<TARGET> convertAll(final List<SOURCE> sources) {
        List<TARGET> targets = new ArrayList<>();
        Optional.ofNullable(sources)
                .ifPresent(list -> list.stream()
                        .map(this::convert).forEach(targets::add));
        return targets;
    }
}
