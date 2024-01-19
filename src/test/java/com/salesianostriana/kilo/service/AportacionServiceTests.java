package com.salesianostriana.kilo.service;

public class AportacionServiceTests {
    //TEST realizado por Marco
    /*
    public Optional<Aportacion> cambiarKilosDetalle(DetalleAportacion detalle, double kgNuevos){

        double result = kgNuevos - detalle.getCantidadKg();
        double kilosActuales = detalle.getTipoAlimento().getKilosDisponibles().getCantidadDisponible();
        double kilosNuevos = (double) Math.round((kilosActuales + result) *100d) /100d;

        if(result < 0){
            if(kilosActuales < result*-1)
                return Optional.empty();
            else{
                detalle.getTipoAlimento().getKilosDisponibles().setCantidadDisponible(
                        kilosNuevos
                );
                detalle.setCantidadKg(kgNuevos);
                tipoAlimentoSaveService.save(detalle.getTipoAlimento());
                return Optional.of(aportacionRepository.save(detalle.getAportacion()));
            }
        }
        else{
            detalle.getTipoAlimento().getKilosDisponibles().setCantidadDisponible(
                    kilosNuevos
            );
            detalle.setCantidadKg(kgNuevos);
            tipoAlimentoSaveService.save(detalle.getTipoAlimento());
            return Optional.of(aportacionRepository.save(detalle.getAportacion()));
        }
    }
     */


}
