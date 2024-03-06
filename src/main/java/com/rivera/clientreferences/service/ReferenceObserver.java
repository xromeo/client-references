package com.rivera.clientreferences.service;

import com.rivera.clientreferences.model.Referencia;

import java.util.List;

public interface ReferenceObserver {
    void actualizarEstadoCliente(List<Referencia> referencias);
}
