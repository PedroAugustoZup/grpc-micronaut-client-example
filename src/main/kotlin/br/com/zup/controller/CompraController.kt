package br.com.zup.controller

import br.com.zup.FreteRequest
import br.com.zup.FreteServiceGrpc
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue

@Controller("/compra")
class CompraController(val grpcClient:FreteServiceGrpc.FreteServiceBlockingStub) {

    @Get
    fun salvarCompra(@QueryValue cep: String): HttpResponse<Any>{
        val response = grpcClient.salvar(FreteRequest.newBuilder()
            .setCep(cep)
            .build())

        return HttpResponse.ok(response.valor)
    }
}