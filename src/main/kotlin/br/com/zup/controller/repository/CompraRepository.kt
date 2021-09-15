package br.com.zup.controller.repository

import br.com.zup.model.Compra
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CompraRepository: JpaRepository<Compra, Long>
